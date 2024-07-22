package com.vasquez.infraccionservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vasquez.infraccionservice.entity.Pago;
import com.vasquez.infraccionservice.repository.PagoRepository;
import com.vasquez.infraccionservice.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService{

	@Autowired
    private PagoRepository repo;

	@Override
	@Transactional(readOnly = true)
	public List<Pago> listar(Pageable page) {
		try {
			return repo.findAll(page).toList();
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Pago findById(int id) {
		try {
			return repo.findById(id).orElse(null);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pago> findByDni(String dni, Pageable page) {
		try {
			return repo.findByDniContaining(dni,page);
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	@Transactional
	public Pago save(Pago pago) {
		try {
            return repo.save(pago);
        } catch (Exception e) {
            throw e;
        }
	}

	@Override
	@Transactional
	public Pago anular(int id) {
		try {
	        Pago pago = repo.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("El id no existe: " + id));
	        pago.setStatus("ANULADA");
	        return repo.save(pago);
	    } catch (Exception e) {
	    	throw e;
	    }
	}
	

}
