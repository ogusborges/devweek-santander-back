package com.bootcamp.santander.repository;

import com.bootcamp.santander.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	Optional<Stock> findByNameAndDate(String name, LocalDate date);

	@Query("SELECT stock " +
			"FROM Stock stock " +
			"WHERE stock.id <> :id AND stock.name = :name AND stock.date = :date")
	Optional<Stock> findByStock(Long id, String name, LocalDate date);

	@Query("SELECT stock " +
			"FROM Stock stock " +
			"WHERE stock.date = :date")
	Optional<List<Stock>> findByDate(LocalDate date);
}
