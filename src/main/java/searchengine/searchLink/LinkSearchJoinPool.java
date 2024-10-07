package searchengine.searchLink;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


public class LinkSearchJoinPool extends RecursiveAction {
    private final String url;
    private final String file;
    private final int depth;
    private final Set<String> strings;

    public LinkSearchJoinPool(String url, String file, int depth, Set<String> strings) {
        this.url = url;
        this.file = file;
        this.depth = depth;
        this.strings = strings;
    }

    public static void main(String[] args) {
        Set<String> strings = new HashSet<>();
        String startUrl = "https://skillbox.ru";
        String fileName = "links2.txt";


        try (ForkJoinPool pool = new ForkJoinPool(2)) {
            pool.invoke(new LinkSearchJoinPool(startUrl, fileName, 0, strings));
        }
    }

    @Override
    protected void compute() {
        if (strings.add(url)) {

            try {
                Thread.sleep(550);

                Document doc = Jsoup.connect(url)
                        .followRedirects(true)
                        .timeout(50_000)
                        .get();
                Elements links = doc.select("a[href]");

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                    writer.write("\t".repeat(depth) + url);
                    writer.newLine();
                }

                List<LinkSearchJoinPool> subTasks = new ArrayList<>();
                for (Element link : links) {
                    String href = link.attr("abs:href");
                    if (isValidLink(href, url)) {
                        subTasks.add(new LinkSearchJoinPool(href, file, depth + 1, strings));
                    }
                }

                for (LinkSearchJoinPool task : invokeAll(subTasks)) {
                    task.join();
                }

            } catch (SocketTimeoutException e) {
                System.err.println("Проблемы c  URL: " + url + " слишком долгий ответ от сервера.");
            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода проблема с файлом");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isValidLink(String href, String url) {
        return href.startsWith("http") &&
                href.contains(url) &&
                !href.contains("#") &&
                !strings.contains(href) &&
                href.endsWith("/");
    }
}
