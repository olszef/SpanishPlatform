package com.home.spanishplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.spanishplatform.dao.NumberNameRepository;
import com.home.spanishplatform.entity.NumberName;

@Service
public class NumberNameServiceImpl implements NumberNameService {

	@Autowired
	private NumberNameRepository numberNameRepository;

	@Override
	public List<NumberName> findAllGivenNumbersById(List<Integer> numbersIds) {
		return numberNameRepository.findAllById(numbersIds);
	}

}
