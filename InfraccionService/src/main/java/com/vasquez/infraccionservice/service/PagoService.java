package com.vasquez.infraccionservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vasquez.infraccionservice.entity.Pago;

public interface PagoService {
    List<Pago> findByDni(String dni, Pageable page);
    Pago save(Pago pago);
    Pago anular(int id);
	List<Pago> listar(Pageable page);
    Pago findById(int id);

}
