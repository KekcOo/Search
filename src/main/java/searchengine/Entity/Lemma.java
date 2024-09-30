package searchengine.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "lemmas", uniqueConstraints = {@UniqueConstraint(columnNames = {"site_id", "lemma"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lemma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false )
    private Site siteId;


    @Column(name = "lemma",nullable = false)
    private String lemma;

    @Column(name = "frequency",nullable = false)
    private Integer frequency;
}
