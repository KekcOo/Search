package searchengine.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

    //TODO check how to do enum
    @Enumerated
    @NonNull
    @Column(name = "status")
    SiteEnum status;
    @Column(name = "status_time")
    LocalDateTime statusTime;
    @Column(name= "last_error")
    String errorText;
    @Column(name= "url")
    String url;
    @Column(name= "name")
    String name;


}
