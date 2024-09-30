package searchengine.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Index;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "pages", indexes = {@Index(name = "idx_path", columnList = "path")})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false )
    private Site siteId;

    @Column(name = "path",nullable = false,columnDefinition = "TEXT")
    private String path;

    @Column(name = "code",nullable = false)
    private Integer code;

    @Column(name = "content",nullable = false,columnDefinition = "MEDIUMTEXT")
    private String content;

    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<searchengine.Entity.Index> indexes;
}
