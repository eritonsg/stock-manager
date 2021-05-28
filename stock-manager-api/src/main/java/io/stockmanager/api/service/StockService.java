package io.stockmanager.api.service;

import io.stockmanager.api.dto.StockDTO;
import io.stockmanager.api.mapper.StockMapper;
import io.stockmanager.api.model.Stock;
import io.stockmanager.api.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Transactional
    public StockDTO save (StockDTO stockDTO) {
        Stock persistedStock = stockRepository.save(stockMapper.toEntity(stockDTO));
        return stockMapper.toDTO(persistedStock);
    }

}
