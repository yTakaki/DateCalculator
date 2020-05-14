package com.example.demo.calc.controller;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.calc.domain.service.RestService;

@SpringBootTest
@AutoConfigureMockMvc
public class FormulaRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RestService restService;

	@Ignore
	@Test
	public void 計算式情報一覧をRESTサービスで取得するテスト() throws Exception {
		fail("まだ定義されていません");
	}
}
