package com.example.demo.calc.domain.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.calc.domain.model.Formula;

@SpringBootTest
@Transactional
public class FormulaServiceTest {

	@Autowired
	private FormulaService service;

	@Autowired
	private NamedParameterJdbcOperations jdbcOperations;

	@Test
	void insertTest() throws Exception {
		{
			Formula f = new Formula();
			f.setFormulaId("99999");
			f.setFormulaName("testdata");
			f.setValueDay(1);
			service.insert(f);
			Formula actual = jdbcOperations.queryForObject("sELECT * FROM formula WHERE formula_id = :formula_id",
					new MapSqlParameterSource("formula_id",f.getFormulaId()),
					new BeanPropertyRowMapper<>(Formula.class));
			assertThat(actual.getFormulaId(),is("99999"));
			assertThat(actual.getFormulaName(),is("testdata"));
			assertThat(actual.getValueYear(),is(0));
			assertThat(actual.getValueMonth(),is(0));
			assertThat(actual.getValueDay(),is(1));
			assertThat(actual.getDesignerDay(),is(0));
		}
	}

	@Test
	@Sql(statements = "INSERT INTO formula VALUES ('99999','testdata',0,0,0,1)")
	void selectOneTest() throws Exception {
		{
			//setup
			String formulaId = "99999";
			// execute
			Formula actual = service.selectOne(formulaId);
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
	@Sql(statements= {
			"DELETE FROM formula",
			"INSERT INTO formula VALUES ('99999','testdata',0,0,1,0)"
	})
	void selectAllTest() throws Exception {
		List<Formula> actual = service.selectAll();
		assertThat(actual.size(),is(1));
		assertThat(actual.get(0).getFormulaId(),is("99999"));
		assertThat(actual.get(0).getFormulaName(),is("testdata"));
		assertThat(actual.get(0).getValueYear(),is(0));
		assertThat(actual.get(0).getValueMonth(),is(0));
		assertThat(actual.get(0).getValueDay(),is(1));
		assertThat(actual.get(0).getDesignerDay(),is(0));
	}

	@Test
	@Sql(statements = "INSERT INTO formula VALUES ('99999','testdata',0,0,0,1)")
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
			service.update(f);
			Formula actual = jdbcOperations.queryForObject("SELECT * FROM formula WHERE formula_id = :formula_id",
					new MapSqlParameterSource("formula_id",f.getFormulaId()),
                    new BeanPropertyRowMapper<>(Formula.class));
			// assertion
			assertThat(actual.getValueDay(),is(1));
		}
	}

	@Test
	@Sql(statements = "INSERT INTO formula VALUES ('99999','testdata',0,0,0,1)")
	void deleteTest() throws Exception {
		{
			//execute
			service.delete("99999");
		}
	}
}
