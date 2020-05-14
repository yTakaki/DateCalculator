package com.example.demo.calc.domain.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.calc.domain.repository.mybatis.FormulaMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FormulaMapperTest {

	@Autowired
	FormulaMapper mapper;

	String formulaId = "00001";

	@Test // 登録済みデータでの検証
	public void 日付IDが00001の計算式データを検索するテスト(){
		assertThat(mapper.selectOne(formulaId).getFormulaId(),is("00001"));
	}

	@Test // DBにデータが登録・削除されると件数が変わる不安定なテスト
	public void 現在7件ある計算式データを全件取得するテスト() {
		assertThat(mapper.selectAll().size(),is(7));
	}

}
