package com.unknow.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.unknow.pages.BasePage;
import com.unknow.model.Usuario;


public class AccessPage extends BasePage{
	//WebElements para acceder al registro
	@FindBy(xpath="/html//input[@id='email_create']")
	private WebElement email;
	
	@FindBy(xpath="//button[@id='SubmitCreate']/span")
	private WebElement createButton;
	
	//WebElements para acceder al Home
	
	@FindBy(xpath="/html//input[@id='email']")
	private WebElement logEmail;
	
	@FindBy(xpath="/html//input[@id='passwd']")
	private WebElement logpass;
	
	@FindBy(xpath="//button[@id='SubmitLogin']/span")
	private WebElement submitButton;
	
	
	
	public AccessPage(WebDriver driver) {
		super(driver);
	}
	
	public RegisterPage accessToRegister(WebDriver driver, Usuario usuario) {
		System.out.println(String.format("Accediendo a registro con correo: %s", 
				usuario.getEmail()));
		this.email.sendKeys(usuario.getEmail());
		this.createButton.click();
		return new RegisterPage(driver);
		
	}
	
	public HomePage accessToHome(WebDriver driver, Usuario usuario) {
		System.out.println(String.format("Accediendo a home page con correo: %s y pass: %s", 
				usuario.getEmail(), usuario.getEmail()));
		this.logEmail.sendKeys(usuario.getEmail());
		this.logpass.sendKeys(usuario.getPassword());
		this.submitButton.click();
		return new HomePage(driver);
	}
}
