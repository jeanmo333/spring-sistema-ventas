package com.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springboot.model.Product;
import com.springboot.repository.ProductRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
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




@CrossOrigin(origins ="http://localhost:3000" )
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    //get all products
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    //create a rest product
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    //get product by id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Producto no existe: "+id));
        return ResponseEntity.ok(product);

    }


    //update producto
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Producto no existe: "+id));

        product.setNombre(productDetails.getNombre());
        product.setPrecio(productDetails.getPrecio());
        product.setCantidad(productDetails.getCantidad());
        product.setEstado(productDetails.getEstado());


        Product updateProduct = productRepository.save(product);
        return ResponseEntity.ok(updateProduct);
    }


    //DELETE product REST API
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct( @PathVariable Long id){

        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Producto no existe: "+id));
        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }


}