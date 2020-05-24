package com.example.demo.calc.domain.repository.mybatis;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // ←デフォルトの組込DBではなく本番と同様のMySQLでの稼働テストにするためのConfigure
public class FormulaMapperTest {

	@Autowired
	private FormulaMapper mapper;

	@Autowired
	private NamedParameterJdbcOperations jdbcOperations;

	@Test
	void テストデータのinsert実行テスト() throws Exception {
		{
			// setup
			Formula f = new Formula();
			f.setFormulaId("99999");
			f.setFormulaName("testdata");
			f.setValueYear(0);
			f.setValueMonth(0);
			f.setValueDay(0);
			f.setDesignerDay(1);
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
	@Sql(statements = "INSERT INTO formula (formula_id,formula_name,value_year,value_month,value_day,designer_day)"
			+ " VALUES ('99999','testdata',0,0,0,1)")
	void テストデータのselectOne実行テスト() throws Exception {
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
	@Sql(statements = "INSERT INTO formula VALUES ('99999','testdata',0,0,0,1)")
	void テストデータのupdate実行テスト() throws Exception {
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
	@Sql(statements = "INSERT INTO formula VALUES ('99999','testdata',0,0,0,1)")
	void テストデータのdelete実行テスト() throws Exception {
		{
			//execute
			mapper.delete("99999");
		}
	}

}