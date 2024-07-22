package com.vasquez.infraccionservice.repository;

import com.vasquez.infraccionservice.entity.Pago;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer>{
    List<Pago> findByDniContaining(String dni, Pageable pageable);

}
