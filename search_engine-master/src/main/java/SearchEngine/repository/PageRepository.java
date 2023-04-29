package SearchEngine.repository;

import SearchEngine.model.Lemma;
import SearchEngine.model.Page;
import SearchEngine.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {
    List<Page> findBySite(Site site);
    Long countBySite(Site site);

    @Query(value = "SELECT p FROM Page p JOIN Index i ON p = i.page WHERE i.lemma IN :lemmas")
    List<Page> findByLemmaList(@Param("lemmas") Collection<Lemma> lemmaList);
}
