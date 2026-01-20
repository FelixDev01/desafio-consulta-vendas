package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleReportDTO>> getReport(
			@RequestParam(required = false)String minDate,
			@RequestParam(required = false)String maxDate,
			@RequestParam(required = false)String name,
			Pageable pageable) {
		Page<SaleReportDTO> report = service.basicReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(report);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleSummaryDTO>> getSummary(
			@RequestParam(required = false) String minDate,
			@RequestParam(required = false) String maxDate,
			@RequestParam(required = false) String name,
			Pageable pageable) {
		Page<SaleSummaryDTO> result = service.summary(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(result);
	}
}
