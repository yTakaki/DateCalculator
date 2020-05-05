package com.example.demo.calc.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.repository.mybatis.FormulaMapper;
import com.example.demo.calc.domain.service.FormulaService;

@Transactional
@Service("FormulaServiceMybatisImpl")
public class FormulaServiceMybatisImpl implements FormulaService {

	@Autowired
	FormulaMapper formulaMapper;

	@Override
	public boolean insert(Formula formula) {
		return formulaMapper.insert(formula);
	}

	@Override
	public Formula selectOne(String formulaId) {
		return formulaMapper.selectOne(formulaId);
	}

	@Override
	public List<Formula> selectAll() {
		return formulaMapper.selectAll();
	}

	@Override
	public boolean update(Formula formula) {
		return formulaMapper.update(formula);
	}

	@Override
	public boolean delete(String formulaId) {
		return formulaMapper.delete(formulaId);
	}
}
