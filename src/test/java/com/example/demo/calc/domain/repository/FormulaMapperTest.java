package com.example.demo.calc.domain.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.jdbc.Sql;

import com.example.demo.calc.domain.model.Formula;
import com.example.demo.calc.domain.repository.FormulaMapper;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // ←デフォルトの組込DBではなく本番と同様のMySQLでの稼働テストにするためのConfigure
public class FormulaMapperTest {

	@Autowired
	private FormulaMapper mapper;

	@Autowired
	private NamedParameterJdbcOperations jdbcOperations;

	@Test
	void insertTest() throws Exception {
		{
			// setup
			Formula f = new Formula();
			f.setFormulaId("99999");
			f.setFormulaName("testdata");
			f.setValueYear(0);
			f.setValueMonth(0);
			f.setValueDay(0);
			f.setDesignerDay(1);
			mapper.delete("99999");
			// execute
			mapper.insert(f); // testdataのinsertを実行
			Formula actual = jdbcOperations.queryForObject("SELECT * FROM formula WHERE formula_id = :formula_id",
					new MapSqlParameterSource("formula_id",f.getFormulaId()),
                    new BeanPropertyRowMapper<>(Formula.class));
			// assertion
			assertThat(actual.getFormulaId(),is("99999"));
			assertThat(actual.getFormulaName(),is("testdata"));
			assertThat(actual.getValueYear(),is(0));
			assertThat(actual.getValueMonth(),is(0));
			assertThat(actual.getValueDay(),is(0));
			assertThat(actual.getDesignerDay(),is(1));
		}
	}

	@Test
	@Sql(statements = {
			"DELETE FROM formula WHERE formula_id = '99999'",
			"INSERT INTO formula VALUES ('99999','testdata',0,0,0,1)"
	})
	void selectOneTest() throws Exception {
		{
			//setup
			String formulaId = "99999";
			// execute
			Formula actual = mapper.selectOne(formulaId);
			// assertion
			assertThat(actual.getFormulaId(),is("99999"));
			assertThat(actual.getFormulaName(),is("testdata"));
			assertThat(actual.getValueYear(),is(0));
			assertThat(actual.getValueMonth(),is(0));
			assertThat(actual.getValueDay(),is(0));
			assertThat(actual.getDesignerDay(),is(1));
		}
	}

	@Test
	@Sql(statements = {
			"DELETE FROM formula",
			"INSERT INTO formula VALUES ('99999','testdata',0,0,0,1)"
	})
	void selectAllTest() throws Exception {
		{
			// execute
			List<Formula> actual = mapper.selectAll();
			// assertion
			assertThat(actual.size(),is(1));
			assertThat(actual.get(0).getFormulaId(),is("99999"));
			assertThat(actual.get(0).getFormulaName(),is("testdata"));
			assertThat(actual.get(0).getValueYear(),is(0));
			assertThat(actual.get(0).getValueMonth(),is(0));
			assertThat(actual.get(0).getValueDay(),is(0));
			assertThat(actual.get(0).getDesignerDay(),is(1));
		}
	}

	@Test
	@Sql(statements = {
			"DELETE FROM formula WHERE formula_id = '99999'",
			"INSERT INTO formula VALUES ('99999','testdata',0,0,0,1)"
	})
	void updateTest() throws Exception {
		{
			// setup
			Formula f = new Formula();
			f.setFormulaId("99999");
			f.setFormulaName("testdata");
			f.setValueYear(0);
			f.setValueMonth(0);
			f.setValueDay(1); // ←updateによる変更テスト対象
			f.setDesignerDay(1);
			// execute
			mapper.update(f);
			Formula actual = jdbcOperations.queryForObject("SELECT * FROM formula WHERE formula_id = :formula_id",
					new MapSqlParameterSource("formula_id",f.getFormulaId()),
                    new BeanPropertyRowMapper<>(Formula.class));
			// assertion
			assertThat(actual.getValueDay(),is(1));
		}
	}

	@Test
	@Sql(statements = {
			"DELETE FROM formula WHERE formula_id = '99999'",
			"INSERT INTO formula VALUES ('99999','testdata',0,0,0,1)"
	})
	void deleteTest() throws Exception {
		{
			//execute
			mapper.delete("99999");
		}
	}

}
