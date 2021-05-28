package io.stockmanager.api.service;

import io.stockmanager.api.dto.StockDTO;
import io.stockmanager.api.exception.EntityNotFoundException;
import io.stockmanager.api.exception.StockBusinessException;
import io.stockmanager.api.mapper.StockMapper;
import io.stockmanager.api.model.Stock;
import io.stockmanager.api.repository.StockRepository;
import io.stockmanager.api.util.message.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Transactional
    public StockDTO save (StockDTO stockDTO) {

        Optional<Stock> stockFounded = stockRepository.findByNameAndDate(stockDTO.getName(), stockDTO.getDate());
        if (stockFounded.isPresent()) {
            throw new StockBusinessException(MessagesUtil.STOCK_ALREADY_EXISTS);
        }

        Stock persistedStock = stockRepository.save(stockMapper.toEntity(stockDTO));
        return stockMapper.toDTO(persistedStock);
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return stockMapper.toDTO(stockRepository.findAll());
    }

    @Transactional
    public StockDTO update(StockDTO stockDTO) {

        Optional<Stock> stockFounded = stockRepository.findByNameAndDateToUpdate(stockDTO.getName(), stockDTO.getDate(), stockDTO.getId());
        if (stockFounded.isPresent()) {
            throw new StockBusinessException(MessagesUtil.STOCK_ALREADY_EXISTS);
        }

        Stock stock = stockRepository.save(stockMapper.toEntity(stockDTO));
        return stockMapper.toDTO(stock);

    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return stockRepository.findById(id).map(stockMapper::toDTO).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public StockDTO deleteById(Long id) {
        StockDTO stockDTO = this.findById(id);
        stockRepository.deleteById(stockDTO.getId());
        return stockDTO;
    }

}
