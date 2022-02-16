package com.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.springboot.model.Costomer;
import com.springboot.repository.CostomerRepository;

@CrossOrigin(origins ="http://localhost:3000" )
@RestController
@RequestMapping("/api")
public class CostomerController {
 
	@Autowired
	private CostomerRepository costomerRepository;
	
	
	//get all costomers
	@GetMapping("/costomers")
	public List<Costomer> getAllCostomers(){
		return costomerRepository.findAll();
	}
	
	
	//create a rest customer
	@PostMapping("/costomers")
	public Costomer createCostomer(@RequestBody Costomer costomer) {
		return costomerRepository.save(costomer);
	}
	
	//get costomer by id
	@GetMapping("/costomers/{id}")
	public ResponseEntity<Costomer> getCostomerById(@PathVariable Long id) {
		Costomer costomer = costomerRepository.findById(id).orElseThrow(()->
				new ResourceNotFoundException("Cliente no existe: " +id));
		return ResponseEntity.ok(costomer);
		
	}
	
	
	//update customer
	@PutMapping("/costomers/{id}")
	public ResponseEntity<Costomer> updateCostoner(@PathVariable Long id, @RequestBody Costomer costomerDetails){
		Costomer costomer = costomerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cliente no existe: "+id));

		costomer.setNombre(costomerDetails.getNombre());
		costomer.setApellido(costomerDetails.getApellido());
		costomer.setEmail(costomerDetails.getEmail());
		costomer.setDireccion(costomerDetails.getDireccion());
		costomer.setTelefono(costomerDetails.getTelefono());
		
		Costomer updateCostomer = costomerRepository.save(costomer);
		return ResponseEntity.ok(updateCostomer);
	}
	
	
	//DELETE costomer REST API
	@DeleteMapping("/costomers/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCostomer( @PathVariable Long id){
		
		Costomer costomer = costomerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cliente no existe: "+id));
		costomerRepository.delete(costomer);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}

}
