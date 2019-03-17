package com.unknow.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.unknow.model.Usuario;

public class HomePage extends BasePage{
	//WebElement para verificar creación de cuenta correcto
	@FindBy(xpath = "//div[@id='center_column']/h1[@class='page-heading']")
	private WebElement pageHeading;
	
	//WebElement para ir a modificar correo
	
	@FindBy(css="[title='Information'] span")
	private WebElement identityButton;
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public String getPageHeadingText() {
		isElementPresent(15, pageHeading);
		return pageHeading.getText();
	}
	
	public <T> T modify(Class<T> returnPage, Usuario usuario) {
		isElementPresent(15, identityButton);
		System.out.println(this.identityButton.isDisplayed());
		
		this.identityButton.click();
		return PageFactory.initElements(driver, returnPage);
	}
}
