package com.tech_tribe.billservice.repository;

import com.tech_tribe.billservice.entity.Biller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillerRepository extends JpaRepository<Biller, Long> {
    Optional<Biller> findByReferenceId(String referenceId);

}
