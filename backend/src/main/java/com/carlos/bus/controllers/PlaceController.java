package com.carlos.bus.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carlos.bus.dtos.PlaceDTO;
import com.carlos.bus.services.PlaceService;

@RestController
@RequestMapping(value = "/places")
public class PlaceController {

	@Autowired
	private PlaceService service;
	
	@GetMapping
	public ResponseEntity<Page<PlaceDTO>> findAll(
		@RequestParam(value = "name", defaultValue = "") String name,Pageable pageable) {
		
		Page<PlaceDTO> list = service.findAllPaged(name.trim(), pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PlaceDTO> findById(@PathVariable Long id) {
		PlaceDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<PlaceDTO> insert(@RequestBody PlaceDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
