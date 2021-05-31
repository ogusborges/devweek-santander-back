package com.bootcamp.santander.controller;

import com.bootcamp.santander.model.dto.StockDTO;
import com.bootcamp.santander.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/stock")
public class StockController {
	@Autowired
	private StockService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> save(@Valid @RequestBody StockDTO dto) {
		return ResponseEntity.ok(service.save(dto));
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> update(@Valid @RequestBody StockDTO dto) {
		return ResponseEntity.ok(service.update(dto));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/{id}",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping(value = "/{date}")
	public ResponseEntity<List<StockDTO>> findByDate(@PathVariable String date) {
		return ResponseEntity.ok(service.findByDate(date));
	}

	@GetMapping(value = "/today")
	public ResponseEntity<List<StockDTO>> findByToday() {
		return ResponseEntity.ok(service.findByToday());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<StockDTO> delete(@PathVariable Long id) {
		return ResponseEntity.ok(service.delete(id));
	}
}
