package com.example.demo.calc.domain.service;
import java.util.List;

import com.example.demo.calc.domain.model.Formula;

public interface RestService {

	public Formula selectOne(String formulaId);

	public List<Formula> selectAll();
}
