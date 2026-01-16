package com.devsuperior.dsmeta.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT obj FROM Sale obj " +
            "JOIN obj.seller seller WHERE obj.date " +
            "BETWEEN :minDate AND :maxDate AND LOWER(seller.name)LIKE LOWER(CONCAT('%', :name,'%'))")
    Page<Sale> searchLastSales (LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

}
