package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

import javax.security.sasl.SaslException;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> basicReport(String minDate, String maxDate, String name, Pageable pageable) {

		LocalDate max;
		LocalDate min;

		if (maxDate == null){
			max = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			max = LocalDate.parse(maxDate);
		}

		if (minDate == null){
			min = max.minusYears(1L);
		} else {
			min = LocalDate.parse(minDate);
		}

		if (name == null){
			name = "";
		}

		Page<Sale> report = repository.searchLastSales(min, max, name, pageable);
		return report.map(x -> new SaleReportDTO(x));
	}
}
