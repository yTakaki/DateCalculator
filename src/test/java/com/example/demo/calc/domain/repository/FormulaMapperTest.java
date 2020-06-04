package com.example.demo.calc.domain.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.TestPropertySource;

import com.example.demo.calc.domain.model.Formula;

@MybatisTest
@TestPropertySource(locations = "classpath:test.properties")
public class FormulaMapperTest {

	@Autowired
	private FormulaMapper sut;

	@Test
	void 適正な日付IDを指定して指定検索ができること() throws Exception {
		Formula actual = sut.selectOne("00001");
		assertThat(actual.getFormulaId(),is("00001"));
		assertThat(actual.getFormulaName(),is("testdata1"));
		assertThat(actual.getValueYear(),is(0));
		assertThat(actual.getValueMonth(),is(0));
		assertThat(actual.getValueDay(),is(1));
		assertThat(actual.getDesignerDay(),is(0));
	}

	@Test
	void 未登録データを指定検索するとNullが返されること() throws Exception {
		Formula actual = sut.selectOne("NoData");
		assertThat(actual,is(nullValue())); // Service should not return "Null".
	}

	@Test
	void 全件検索ができること() throws Exception {
		List<Formula> actual = sut.selectAll();
		assertThat(actual.size(),is(1));
	}

	@Test
	void 未登録データを登録できること() throws Exception {
		Formula f = new Formula("99999","insertdata",0,0,0,29);
		sut.insert(f);
		Formula actual = sut.selectOne("99999");
		assertThat(actual,is(f));
	}

	@Test
	void 登録済みデータを登録しようとするとDuplicateKeyExceptionを返すこと() throws Exception {
		Formula f = new Formula("00001","testdata1",0,0,1,0);
		assertThrows(DuplicateKeyException.class,() -> sut.insert(f));
	}

	@Test
	void Nullで登録しようとするとDataIntegrityViolationExceptionを返すこと() throws Exception {
		assertThrows(DataIntegrityViolationException.class,() -> sut.insert(null));
	}

	@Test
	void 登録済みデータを更新できること() throws Exception {
		Formula f = new Formula("00001","testformula1",0,0,1,0); // change formulaName.
		sut.update(f);
		Formula actual = sut.selectOne("00001");
		assertThat(actual,is(f));
	}

	@Test
	void 登録済みデータを削除できること() throws Exception {
		sut.delete("00001");
		List<Formula> actual = sut.selectAll();
		assertThat(actual.size(),is(0));
	}

}
