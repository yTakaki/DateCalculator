package com.example.demo.calc.domain.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.repository.FormulaMapper;

@SpringJUnitConfig(classes = FormulaServiceTest.Config.class)
public class FormulaServiceTest {

	@ComponentScan({
		"com.example.demo.calc.domain.service",
		"com.example.demo.calc.domain.repository"
	})
	static class Config {
	}
	@Autowired
	private FormulaService sut;

	@MockBean
	private FormulaMapper mapper;

	@Test
	void insertメソッドが実行されること() throws Exception {
		when(mapper.insert(any())).thenReturn(true);
		sut.insert(any());

		verify(mapper,times(1)).insert(any());
	}

	@Test
	void selectOneメソッドが実行されること() throws Exception {
		when(mapper.selectOne("test")).thenReturn(any());
		sut.selectOne("test");

		verify(mapper,times(1)).selectOne(anyString());
	}

	@Test
	void selectAllメソッドが実行されること() throws Exception {
		Formula f = new Formula("99999","testdata",0,0,1,0);
		List<Formula> expect = new ArrayList<>();
		expect.add(f);
		when(mapper.selectAll()).thenReturn(expect);
		sut.selectAll();

		verify(mapper,times(1)).selectAll();
	}

	@Test
	void updateメソッドが実行されること() throws Exception {
		when(mapper.update(any())).thenReturn(true);
		sut.update(any());

		verify(mapper,times(1)).update(any());
	}

	@Test
	void deleteメソッドが実行されること() throws Exception {
		when(mapper.delete(anyString())).thenReturn(true);
		sut.delete(anyString());

		verify(mapper,times(1)).delete(anyString());
	}
}
