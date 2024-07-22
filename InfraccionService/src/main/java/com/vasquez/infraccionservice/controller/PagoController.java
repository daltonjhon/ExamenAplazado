package com.vasquez.infraccionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vasquez.infraccionservice.entity.Pago;
import com.vasquez.infraccionservice.service.PagoService;

@RestController
@RequestMapping(value = "api/v1/pagos")
public class PagoController {
	@Autowired
    private PagoService service;

    @GetMapping
    public ResponseEntity<List<Pago>> findAll(
            @RequestParam(value = "name", required = false, defaultValue = "") String dni,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offSet,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int limit) {

    	Pageable page = PageRequest.of(offSet, limit);
        List<Pago> pagos;

        pagos = dni != null ? service.findByDni(dni, page) : service.listar(page);

        if (pagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }
    
    
    @GetMapping("/usuario/{dni}")
    public ResponseEntity<List<Pago>> findByUsuario(@PathVariable("dni") String dni) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        List<Pago> pagos = service.findByDni(dni, pageable);
        if (pagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }
    
    @PostMapping
    public ResponseEntity<Pago> create(@RequestBody Pago pago) {
    	try {
            Pago registro = service.save(pago);
            return ResponseEntity.ok(registro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pago> update(@PathVariable("id") int id) {
        try {
            Pago pago = service.anular(id);
            return ResponseEntity.ok(pago);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
}

