package com.example.demo.calc.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.repository.mybatis.FormulaMapper;
import com.example.demo.calc.domain.service.RestService;

@Transactional
@Service("RestServiceMybatisImpl")
public class RestServiceMybatisImpl implements RestService {

	@Autowired
	FormulaMapper formulaMapper;

	@Override
	public Formula selectOne(String formulaId) {
		return formulaMapper.selectOne(formulaId);
	}

	@Override
	public List<Formula> selectAll() {
		return formulaMapper.selectAll();
	}
}
