package com.unknow.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.unknow.model.Usuario;

public class IdentityPage extends BasePage{
	
	public IdentityPage(WebDriver driver) {
		super(driver);
	}

	//WebElements para modificar correo		
		@FindBy(id="email")
		private WebElement email;
		
		@FindBy(id="old_passwd")
		private WebElement pass;
		
		@FindBy(xpath="/html//div[@id='center_column']//form[@action='http://automationpractice.com/index.php?controller=identity']//button[@name='submitIdentity']")
		private WebElement saveButton;
		
		@FindBy(xpath="//div[@id='center_column']//p[@class='alert alert-success']")
		private WebElement success;
		
		

	public String getSuccessChange(Usuario usuario, String newPass) {
		isElementPresent(15, email);
		this.email.clear();
		this.email.sendKeys(newPass);
		this.pass.sendKeys(usuario.getPassword());
		this.saveButton.click();
		return success.getText();
	}

}
