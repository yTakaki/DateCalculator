package com.example.demo.calc.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.repository.FormulaMapper;

@Transactional
@Service
public class FormulaService {

	@Autowired
	FormulaMapper mapper;

	public boolean insert(Formula formula) {
		return mapper.insert(formula);
	}

	public Formula selectOne(String formulaId) {
		return mapper.selectOne(formulaId);
	}

	public List<Formula> selectAll() {
		return mapper.selectAll();
	}

	public boolean update(Formula formula) {
		return mapper.update(formula);
	}

	public boolean delete(String formulaId) {
		return mapper.delete(formulaId);
	}
}
