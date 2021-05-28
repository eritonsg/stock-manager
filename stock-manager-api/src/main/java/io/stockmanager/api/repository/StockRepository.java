package io.stockmanager.api.repository;

import io.stockmanager.api.dto.StockDTO;
import io.stockmanager.api.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByNameAndDate(String name, LocalDate date);

    @Query("SELECT s FROM Stock s WHERE s.name = :name and s.date = :date and s.id <> :id")
    Optional<Stock> findByNameAndDateToUpdate(String name, LocalDate date, Long id);

}
