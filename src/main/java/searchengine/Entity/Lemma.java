package searchengine.Entity;

import javax.persistence.*;

@Entity
public class Lemma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    SiteInfo siteId;
    String lemma;
    Integer frequency;
}
