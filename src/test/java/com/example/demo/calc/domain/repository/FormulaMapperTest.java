package com.example.demo.calc.domain.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.repository.mybatis.FormulaMapper;
import com.example.demo.calc.test.util.MapperTestApplication;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@DbUnitConfiguration(dataSetLoader=CsvDataSetLoader.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class
})
@SpringBootTest(classes = {MapperTestApplication.class},webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class FormulaMapperTest {

	@Autowired
	FormulaMapper mapper;

	@Test
	@DatabaseSetup(value="/testdata/")
	public void 全件取得したデータ件数を確認するテスト() throws Exception {
		List<Formula> list = mapper.selectAll();
		assertThat(list.size(),is(2));
	}

}
