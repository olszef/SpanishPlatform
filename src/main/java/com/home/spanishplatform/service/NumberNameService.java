package com.home.spanishplatform.service;

import java.util.List;

import com.home.spanishplatform.entity.NumberName;

public interface NumberNameService {

	public List<NumberName> findAllGivenNumbersById(List<Integer> numbersIds);

}
