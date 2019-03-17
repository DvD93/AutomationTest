package com.unknow.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.unknow.model.Usuario;
import com.unknow.pages.AccessPage;
import com.unknow.pages.RegisterPage;
import com.unknow.pages.HomePage;
import com.unknow.pages.IdentityPage;


public class AccessTest extends BaseTest{
	
	
	
	@Test(dataProvider = "data1")
	public void registWithRequiredParametersTest( Usuario usuario){
		//Access->Registro->Home
		driver.get("http://automationpractice.com/index.php?controller=authentication&;back=my-account");
		
		
		AccessPage accessPage = new AccessPage(driver);
		
		
		RegisterPage registerPage = accessPage.accessToRegister(driver,usuario);
		HomePage homePage = registerPage.register(HomePage.class, usuario);
		
		//Estoy logueado
		assertEquals(homePage.getPageHeadingText(), "MY ACCOUNT");

	}
	
	@Test(dataProvider = "data2")//(dependsOnMethods = { "registWithRequiredParametersTest" })
	public void modifyEmailTest(Usuario usuario, String nuevoEmail) {
		// Access->Home->Identity
		driver.get("http://automationpractice.com/index.php?controller=authentication&;back=my-account");
		
		
		AccessPage accessPage = new AccessPage(driver);
		
		HomePage homepage = accessPage.accessToHome(driver, usuario);
		IdentityPage identityPage = homepage.modify(IdentityPage.class, usuario);
		
		assertEquals(identityPage.getSuccessChange(usuario,nuevoEmail),"Your personal information has been successfully updated.");
	}
	
	@Test(dependsOnMethods = { "modifyEmailTest" },dataProvider = "data3")
	public void registWithFullParametersTest(Usuario usuario) {
		//Access->Registro->Home
		driver.get("http://automationpractice.com/index.php?controller=authentication&;back=my-account");
				
				
				AccessPage accessPage = new AccessPage(driver);
				
				RegisterPage registerPage = accessPage.accessToRegister(driver,usuario);
				HomePage homePage = registerPage.registerFullParams(HomePage.class, usuario);
				
				//Estoy logueado
				assertEquals(homePage.getPageHeadingText(), "MY ACCOUNT");
	}
	@DataProvider(name = "data1")
	public static Object[][] parametrosRequeridos() {
		return new Object[][] { { 
			new Usuario(1, "Jose", "Gomez", "usuariotest001@gmail.com", "302-203-8778", 
			"test1234", 3, 6, 1989, "4037 Callison Lane", "Newark", 8,19711)},{
	  		new Usuario(2, "Maria", "Garcia", "usuariotest0002@gmail.com", "832-205-8607", 
			"test12345", 8, 12, 2000, "2661 Wines Lane", "Houston", 43,77036) } };
	}
	public static int rmd() {
		int numero = (int) (Math.random() * 9999) + 1;
		return numero;
	}
	
	@DataProvider(name = "data2")
	public static Object[][] parametrosModificarEmail() {
		return new Object[][] { { 
			new Usuario("usuariotest001@gmail.com","test1234"),"usuariotest"+rmd()+"@gmail.com"},{
	  		new Usuario("usuariotest0002@gmail.com","test12345"),"usuariotest"+rmd()+"@gmail.com" } };
			}
	
	
	@DataProvider(name = "data3")
	public static Object[][] parametrosCompletos() {
		return new Object[][] { { 
			new Usuario(1, "Jose", "Gomez", "usuariotest001@gmail.com", "302-203-8778", 
			"test1234", 3, 6, 1989, "4037 Callison Lane", "Newark", 8,19711,"PIXAR",1,1,"Apartment 2B",
			"Estoy interesado en ropa","302-203-7887")},{
	  		new Usuario(2, "Maria", "Garcia", "usuariotest0002@gmail.com", "832-205-8607", 
			"test12345", 8, 12, 2000, "2661 Wines Lane", "Houston", 43,77036,"Apple",0,1,"Unit 53",
			"Me gustan los vestidos","832-365-4914") } };
			}
}
