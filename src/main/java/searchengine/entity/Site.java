package searchengine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sites")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;


    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private SiteEnum status;

    @Column(name = "status_time",nullable = false)
    private LocalDateTime statusTime;

    @Column(name= "last_error",columnDefinition = "TEXT")
    private String errorText;

    @Column(name= "url",nullable = false,length = 255)
    private String url;

    @Column(name= "name",nullable = false,length = 255)
    private String name;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Page> pages;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lemma> lemmas;


}
