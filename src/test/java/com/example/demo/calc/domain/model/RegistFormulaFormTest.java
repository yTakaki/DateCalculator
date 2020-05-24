package com.example.demo.calc.domain.model;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@SpringBootTest
public class RegistFormulaFormTest {

	@Autowired
	Validator validator;

	private RegistFormulaForm form = new RegistFormulaForm();
	private BindingResult bind = new BindException(form,"RegistFormulaFrom");

	@BeforeEach
	void BeforeEachTest() throws Exception {
		form.setFormulaId("99999");
		form.setFormulaName("testdata");
		form.setValueYear(0);
		form.setValueMonth(0);
		form.setValueDay(1);
		form.setDesignerDay(0);
	}

	@Test
	void noError() throws Exception {
		validator.validate(form, bind);
		assertThat(bind.getFieldError(),is(nullValue()));
	}

	@Test
	void formulaIdがnull() throws Exception {
		form.setFormulaId(null);
		validator.validate(form, bind);
		assertThat(bind.getFieldError().getField(),is("formulaId"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void formulaIdが6文字() throws Exception {
		form.setFormulaId("999999");
		validator.validate(form, bind);
		assertThat(bind.getFieldError().getField(),is("formulaId"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("length must be between 0 and 5"));
	}

	@Test
	void formulaNameが空文字() throws Exception {
		form.setFormulaName("");
		validator.validate(form, bind);
		assertThat(bind.getFieldError().getField(),is("formulaName"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("must not be blank"));
	}

	@Test
	void formulaNameが51文字() throws Exception {
		form.setFormulaName("testdatatestdatatestdatatestdatatestdatatestdatatestdatatestdatates");
		validator.validate(form, bind);
		assertThat(bind.getFieldError().getField(),is("formulaName"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("length must be between 0 and 50"));
	}

	@Test
	void designerDayが負の数() throws Exception {
		form.setDesignerDay(-1);
		validator.validate(form, bind);
		assertThat(bind.getFieldError().getField(),is("designerDay"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("must be between 0 and 29"));
	}

	@Test
	void designerDayが30() throws Exception {
		form.setDesignerDay(30);
		validator.validate(form, bind);
		assertThat(bind.getFieldError().getField(),is("designerDay"));
		assertThat(bind.getFieldError().getDefaultMessage(),is("must be between 0 and 29"));
	}

}
