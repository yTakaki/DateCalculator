package com.example.demo.calc.view.page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class HomeCalculatorPage {

	private static final String URL = "http://localhost:8080/home";

	@FindBy(id = "calcDate")
	private SelenideElement calcDate;

	@FindBy(id = "calc_button")
	private SelenideElement calc_button;

	@FindBy(id = "regist_button")
	private SelenideElement regist_button;

	public static HomeCalculatorPage open() {
		return Selenide.open(URL, HomeCalculatorPage.class);
	}

	public String title() {
		return Selenide.title();
	}

	public HomeCalculatorPage calcDate(String date) {
		calcDate.setValue(date);
		return page(HomeCalculatorPage.class);
	}

	public HomeCalculatorPage calculation() {
		calc_button.click();
		return page(HomeCalculatorPage.class);
	}

	public RegistFormulaPage moveToRegistFormulaPage() {
		regist_button.click();
		return page(RegistFormulaPage.class);
	}

	public UpdateFormulaPage moveToUpdateFormulaPage(String formulaId) {
		$(By.id(formulaId + "_" + "updatebtn")).click();
		return page(UpdateFormulaPage.class);
	}

	public HomeCalculatorPage clickDeleteButton(String formulaId) {
		$(By.id(formulaId + "_" + "deletebtn")).click();
		return page(HomeCalculatorPage.class);
	}

	public SelenideElement calcResult() {
		return $(By.cssSelector(".table td"));
	}

	public int countCalcResult() {
		return $(By.cssSelector(".table")).findElements(By.tagName("td")).size();
	}

}
