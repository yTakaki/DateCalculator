package com.example.demo.calc.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.calc.domain.model.Formula;

@Mapper
public interface FormulaMapper {

	public boolean insert(Formula formula);

	public Formula selectOne(String formulaId);

	public List<Formula> selectAll();

	public boolean update(Formula formula);

	public boolean delete(String formulaId);
}
