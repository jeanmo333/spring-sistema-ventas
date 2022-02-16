package com.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springboot.model.Supplier;
import com.springboot.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Costomer;
import com.springboot.repository.CostomerRepository;

@CrossOrigin(origins ="http://localhost:3000" )
@RestController
@RequestMapping("/api")
public class SupplierController {

    @Autowired
    private SupplierRepository  supplierRepository;


    //get all suppliers
    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();
    }


    //create a rest supplier
    @PostMapping("/suppliers")
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    //get supplier by id
    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(supplier);

    }


    //update supplier
    @PutMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplierDetails){
        Supplier supplier = supplierRepository.findById(id).orElseThrow();

        supplier.setNombre(supplierDetails.getNombre());
        supplier.setEmail(supplierDetails.getEmail());
        supplier.setDireccion(supplierDetails.getDireccion());
        supplier.setTelefono(supplierDetails.getTelefono());
        supplier.setWeb(supplierDetails.getWeb());

        Supplier updateSupplier = supplierRepository.save(supplier);
        return ResponseEntity.ok(updateSupplier);
    }


    //DELETE suplier REST API
    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSuplier( @PathVariable Long id){

        Supplier supplier = supplierRepository.findById(id).orElseThrow();
        supplierRepository.delete(supplier);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Supplier deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }



}