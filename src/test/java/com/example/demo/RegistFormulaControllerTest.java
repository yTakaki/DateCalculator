package com.example.demo;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistFormulaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void 計算式登録画面表示確認() throws Exception {
		mockMvc.perform(get("/regist"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("計算式登録")));
	}

}
