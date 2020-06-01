package com.example.demo.calc.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.repository.FormulaMapper;

@Transactional
@Service
public class RestService {

	@Autowired
	FormulaMapper formulaMapper;

	public Formula selectOne(String formulaId) {
		return formulaMapper.selectOne(formulaId);
	}

	public List<Formula> selectAll() {
		return formulaMapper.selectAll();
	}
}
