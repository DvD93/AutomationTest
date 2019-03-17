package com.unknow.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class BaseTest {

	protected WebDriver driver;
	final String GRID_HOST = "http://192.168.56.1:4444";
	private String browser;

	@BeforeMethod
	@Parameters(value = { "browser" })
	public void setUp(@Optional String browser) throws MalformedURLException {
		if (StringUtils.isBlank(browser)) {
			browser = "remoteWindowsFirefox";
		}
		this.browser = browser;
		
		System.out.println("Opening browser: " + browser);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		switch (browser) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			// Ocultar el mensaje de guardar credenciales
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			chromeOptions.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(chromeOptions);	
			break;
		case "firefox":
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel");
			profile.setPreference("browser.download.dir", SystemUtils.IS_OS_WINDOWS ? "C:\\" : "~/");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setProfile(profile);
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "opera":
			driver = new OperaDriver();
			break;
		case "remoteLinuxChrome":
            capabilities.setBrowserName("chrome");
    		capabilities.setCapability("platformName","linux");
		    driver = new RemoteWebDriver(new URL(GRID_HOST + "/wd/hub"), capabilities);
		    break;
		case "remoteLinuxFirefox":
            capabilities.setBrowserName("firefox");
    		capabilities.setCapability("platformName","linux");
		    driver = new RemoteWebDriver(new URL(GRID_HOST + "/wd/hub"), capabilities);
		    break;
		case "remoteMacChrome":
            capabilities.setBrowserName("chrome");
    		capabilities.setCapability("platformName","mac");
		    driver = new RemoteWebDriver(new URL(GRID_HOST + "/wd/hub"), capabilities);
		    break;
		case "remoteWindowsChrome":
			System.out.println("Ejecutando en Chrome");
			System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
            capabilities.setBrowserName("chrome");
    		capabilities.setCapability("platformName","win10");
		    driver = new RemoteWebDriver(new URL(GRID_HOST + "/wd/hub"), capabilities);
		    break;
		case "remoteWindowsFirefox":
			System.out.println("Ejecutando en Firefox");
			System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
            capabilities = DesiredCapabilities.firefox();
		    driver = new RemoteWebDriver(new URL(GRID_HOST + "/wd/hub"), capabilities);
		    break;
		case "remoteAndroidChrome":
            capabilities.setBrowserName("chrome");
    		capabilities.setCapability("platformName","Android");
    		capabilities.setCapability("deviceName", "Galaxy J7 Prime");
		    driver = new RemoteWebDriver(new URL(GRID_HOST + "/wd/hub"), capabilities);
		    break;
		default:
			throw new IllegalArgumentException("Browser " + browser + " not supported");
		}
	}


	@AfterMethod
	public void tearDown() {
		System.out.println("Cerrando browser: " + browser);
		if (null != driver) {
			driver.quit();
		}
	}
}
