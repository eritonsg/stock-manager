package io.stockmanager.api.repository;

import io.stockmanager.api.dto.StockDTO;
import io.stockmanager.api.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
