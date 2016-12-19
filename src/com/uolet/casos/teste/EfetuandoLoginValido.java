package com.uolet.casos.teste;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EfetuandoLoginValido {
	public String baseUrl = "http://console.uolet.com/editor/index.html";
	private StringBuffer verificationErrors = new StringBuffer();
	WebDriver driver;
	
	@BeforeClass
	public void antesTeste(){
		System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		// new paginalogin
	}
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	    fail(verificationErrorString);
	    }
	}
	private boolean isElementPresent(By by) {
		   try {
		      driver.findElement(by);
		      return true;
		   } catch (NoSuchElementException e) {
		      return false;
		   }
		} 
  @Test(priority = 0 , description = "Entrando na pagina do editor, logando na pagina login com usuario valido")
  public void entrandoPaginaEditorUsuarioValido() throws InterruptedException {
	  com.uolet.pages.console.PaginaLogin paginaLogin = new com.uolet.pages.console.PaginaLogin(driver);
	  com.uolet.pages.console.PaginaHomeEditor homeEditor = paginaLogin.LogandoUsuario("worldvini@hotmail.com", "webvinis.123", "UQA");
	  Assert.assertEquals(driver.findElement(By.id("btn-logoff")).getText(), "Uolet - Q&A\\Vinícius da Silva fleury");
  }
}
