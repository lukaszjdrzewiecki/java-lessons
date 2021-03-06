package com.workshop.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.workshop.db.entity.BicyclePart;

import java.util.List;
import java.util.Optional;

@Repository
public interface BicyclePartRepository extends JpaRepository<BicyclePart, Long>, JpaSpecificationExecutor<BicyclePart> {

    boolean existsByProductId (String productId);

    Optional<BicyclePart> findByProductId (String productId);

    // For test only
    List<BicyclePart> findAllByIsOfficialTrue();

}
