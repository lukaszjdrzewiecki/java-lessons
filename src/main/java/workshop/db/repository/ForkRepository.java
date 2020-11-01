package workshop.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import workshop.db.entity.Frame;
import workshop.db.entity.suspension.Fork;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "suspensionFork", path = "suspensionFork")
public interface ForkRepository extends PagingAndSortingRepository<Fork, Long> {

    boolean existsByProductId (String productId);

    Optional<Fork> findByProductId (String productId);

    List<Fork> findAllByIsOfficialTrue();

}
