package com.tcs.paybills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tcs.paybills.model.Biller;

@Repository
public interface BillerRepository extends JpaRepository<Biller, Long> {
}
