package com.bootcamp.santander.service;

import com.bootcamp.santander.exceptions.BusinessException;
import com.bootcamp.santander.exceptions.IncorrectFormatException;
import com.bootcamp.santander.exceptions.NotFoundException;
import com.bootcamp.santander.mapper.StockMapper;
import com.bootcamp.santander.model.Stock;
import com.bootcamp.santander.model.dto.StockDTO;
import com.bootcamp.santander.repository.StockRepository;
import com.bootcamp.santander.util.MessageUtils;
import com.bootcamp.santander.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
	@Autowired
	private StockRepository repository;

	@Autowired
	private StockMapper mapper;

	@Transactional
	public StockDTO save(StockDTO dto) {
		Optional<Stock> optionalStock = repository
				.findByNameAndDate(dto.getName(), dto.getDate());

		if(optionalStock.isPresent() == true) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}

		Stock stock = mapper.toEntity(dto);
		repository.save(stock);
		return mapper.toDto(stock);
	}

	public StockDTO update(StockDTO dto) {
		Optional<Stock> optionalStock = repository
				.findByStock(dto.getId(), dto.getName(), dto.getDate());

		if(optionalStock.isPresent() == true) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}

		Stock stock = mapper.toEntity(dto);
		repository.save(stock);
		return mapper.toDto(stock);
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findAll() {
		List<Stock> list = repository.findAll();
		return mapper.toDto(list);
	}

	@Transactional(readOnly = true)
	public StockDTO findById(Long id) {
		Optional<Stock> optionalStock = repository.findById(id);

		if(optionalStock.isPresent() == false) {
			throw new NotFoundException(MessageUtils.NO_RECORDS_FOUND);
		}

		return mapper.toDto(optionalStock.get());
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findByDate(String date) {
		if(ValidationUtils.dateValidator(date) == false) {
			throw new IncorrectFormatException(MessageUtils.INCORRECT_FORMAT);
		}

		Optional<List<Stock>> optionalStock = repository.findByDate(LocalDate.parse(date));

		if(optionalStock.isPresent() == false || optionalStock.get().size() == 0) {
			throw new NotFoundException(MessageUtils.NO_RECORDS_FOUND);
		}
		return mapper.toDto(optionalStock.get());
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findByToday() {
		Optional<List<Stock>> optionalStock = repository.findByDate(LocalDate.now());

		if(optionalStock.isPresent() == false || optionalStock.get().size() == 0) {
			throw new NotFoundException(MessageUtils.NO_RECORDS_FOUND);
		}
		return mapper.toDto(optionalStock.get());
	}

	@Transactional
	public StockDTO delete(Long id) {
		StockDTO dto = this.findById(id);
		repository.deleteById(id);
		return dto;
	}
}
