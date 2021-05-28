package io.stockmanager.api.mapper;

import io.stockmanager.api.dto.StockDTO;
import io.stockmanager.api.model.Stock;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    public Stock toEntity(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setId(stockDTO.getId());
        stock.setName(stockDTO.getName());
        stock.setDescription(stockDTO.getDescription());
        stock.setEnterpriseName(stockDTO.getEnterpriseName());
        stock.setPrice(stockDTO.getPrice());
        stock.setVariation(stockDTO.getVariation());
        stock.setDate(stockDTO.getDate());
        return stock;
    }

    public StockDTO toDTO(Stock stock) {
        return StockDTO.builder()
                .id(stock.getId())
                .name(stock.getName())
                .description(stock.getDescription())
                .enterpriseName(stock.getEnterpriseName())
                .price(stock.getPrice())
                .variation(stock.getVariation())
                .date(stock.getDate())
                .build();
    }

    public List<StockDTO> toDTO(List<Stock> stockList) {
        return stockList.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
