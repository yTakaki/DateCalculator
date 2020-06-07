package com.example.demo.calc.view.page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class UpdateFormulaPage {

	@FindBy(id = "return_homebtn")
	private SelenideElement return_homebtn;

	@FindBy(id = "update_btn")
	private SelenideElement update_btn;

	public String title() {
		return Selenide.title();
	}

	public HomeCalculatorPage returnHomeCalculatorPage() {
		return_homebtn.click();
		return page(HomeCalculatorPage.class);
	}

	public HomeCalculatorPage clickUpdateButton() {
		update_btn.click();
		return page(HomeCalculatorPage.class);
	}

}
