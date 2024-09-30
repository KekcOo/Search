package searchengine.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Page")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
            //todo
    SiteInfo siteId;

    String path;
    Integer code ;
    String content;
}
