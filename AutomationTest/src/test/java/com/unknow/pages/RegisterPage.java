package com.unknow.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.unknow.pages.BasePage;
import com.unknow.model.Usuario;

public class RegisterPage extends BasePage{
	
	
	@FindBy(css="#account-creation_form .radio-inline:nth-child(3) [type]")
	private WebElement genderButton1;
	
	@FindBy(css="#account-creation_form .radio-inline:nth-child(4) [type]")
	private WebElement genderButton2;
	
	@FindBy(xpath="/html//input[@id='customer_firstname']")
	private WebElement nombre;
	
	@FindBy(xpath="/html//input[@id='customer_lastname']")
	private WebElement apellido;
	
	@FindBy(xpath = "/html//input[@id='passwd']")
	private WebElement password;
	
	@FindBy(xpath="/html//select[@id='days']")
	private WebElement dia;
	
	@FindBy(xpath="/html//select[@id='months']")
	private WebElement mes;
	
	@FindBy(xpath="/html//select[@id='years']")
	private WebElement anio;
	
	@FindBy(xpath="/html//form[@id='account-creation_form']//input[@name='address1']")
	private WebElement direccion;
	
	@FindBy(xpath="/html//input[@id='city']")
	private WebElement ciudad;
	
	@FindBy(xpath="/html//select[@id='id_state']")
	private WebElement estado;
	
	@FindBy(xpath="/html//input[@id='postcode']")
	private WebElement codPostal;
	
	@FindBy(xpath="/html//input[@id='phone_mobile']")
	private WebElement telefono;
	
	@FindBy(xpath="/html//input[@id='alias']")
	private WebElement alias;
	
	@FindBy(xpath = "//button[@id='submitAccount']/span")
	private WebElement submitButton;
	
	//optionals WebElements
	@FindBy(xpath="/html//input[@id='company']")
	private WebElement company;
	
	@FindBy(xpath="/html//input[@id='newsletter']")
	private WebElement news;
	
	@FindBy(xpath="/html//input[@id='optin']")
	private WebElement offers;
	
	@FindBy(xpath="/html//form[@id='account-creation_form']//input[@name='address2']")
	private WebElement adrress2;
	
	@FindBy(xpath="/html//textarea[@id='other']")
	private WebElement info;
	
	@FindBy(xpath="/html//input[@id='phone']")
	private WebElement telefono2;
	
	
	
	public RegisterPage(WebDriver driver) {
		super(driver);
		isElementPresent(15, genderButton1);
	}
	public <T> T register(Class<T> returnPage, Usuario usuario) {
		
		System.out.println(this.genderButton1.isDisplayed());
		if (usuario.getTitle() == 1) {
			this.genderButton1.click();
		}else {
			this.genderButton2.click();
		}
		this.nombre.sendKeys(usuario.getNombre());
		this.apellido.sendKeys(usuario.getApellido());
		this.password.sendKeys(usuario.getPassword());
		Select diaSelect = new Select(dia);
		diaSelect.selectByValue(String.format("%s", usuario.getDiaNac()));
		Select mesSelect = new Select(mes);
		mesSelect.selectByValue(String.format("%s", usuario.getMesNac()));
		Select anioSelect = new Select(anio);
		anioSelect.selectByValue(String.format("%s", usuario.getAnioNac()));
		this.direccion.sendKeys(usuario.getDireccion());
		this.ciudad.sendKeys(usuario.getCuidad());
		Select estadoSelect = new Select(estado);
		estadoSelect.selectByValue(String.format("%s", usuario.getEstado()));
		this.codPostal.sendKeys(String.format("%s", usuario.getCodPostal()));
		this.telefono.sendKeys(usuario.getTelefono());
		this.alias.sendKeys(usuario.getDireccion());
		this.submitButton.click();
		
		return PageFactory.initElements(driver, returnPage);
	}
	
public <T> T registerFullParams(Class<T> returnPage, Usuario usuario) {
		
		System.out.println(this.genderButton1.isDisplayed());
		if (usuario.getTitle() == 1) {
			this.genderButton1.click();
		}else {
			this.genderButton2.click();
		}
		this.nombre.sendKeys(usuario.getNombre());
		this.apellido.sendKeys(usuario.getApellido());
		this.password.sendKeys(usuario.getPassword());
		Select diaSelect = new Select(dia);
		diaSelect.selectByValue(String.format("%s", usuario.getDiaNac()));
		Select mesSelect = new Select(mes);
		mesSelect.selectByValue(String.format("%s", usuario.getMesNac()));
		Select anioSelect = new Select(anio);
		anioSelect.selectByValue(String.format("%s", usuario.getAnioNac()));
		if(usuario.getNews() == 1) {
			this.news.click();
		}
		if(usuario.getOffers() == 1) {
			this.offers.click();
		}
		this.company.sendKeys(usuario.getCompany());
		this.direccion.sendKeys(usuario.getDireccion());
		this.adrress2.sendKeys(usuario.getFullAddress());
		this.ciudad.sendKeys(usuario.getCuidad());
		Select estadoSelect = new Select(estado);
		estadoSelect.selectByValue(String.format("%s", usuario.getEstado()));
		this.info.sendKeys(usuario.getInfo());
		this.codPostal.sendKeys(String.format("%s", usuario.getCodPostal()));
		this.telefono.sendKeys(usuario.getTelefono());
		this.telefono2.sendKeys(usuario.getTelefono2());
		this.alias.sendKeys(usuario.getDireccion());
		this.submitButton.click();
		
		return PageFactory.initElements(driver, returnPage);
	}

}
