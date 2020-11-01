package workshop.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import workshop.db.entity.drivetrain.Chain;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "chain", path = "chain")
public interface ChainRepository extends PagingAndSortingRepository<Chain, Long> {

    boolean existsByProductId (String productId);

    Optional<Chain> findByProductId (String productId);

}
