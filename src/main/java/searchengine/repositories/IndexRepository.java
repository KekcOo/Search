package searchengine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import searchengine.entity.Index;
@Repository
public interface IndexRepository extends JpaRepository<Index,Long> {
}
