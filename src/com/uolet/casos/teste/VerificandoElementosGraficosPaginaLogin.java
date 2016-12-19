package com.uolet.casos.teste;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerificandoElementosGraficosPaginaLogin {
	
	public String baseUrl = "http://console.uolet.com/editor/index.html";
	private StringBuffer verificationErrors = new StringBuffer();
	WebDriver driver;
	
	@BeforeClass
	public void antesTeste(){
		System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}
	
	
	@Test(priority = 0 , description = "Pagina index (login) está com o titulo correto")
	public void verificandoTituloPaginaLoginEstaCorreto() {
		Assert.assertEquals(driver.getTitle(), "Uolet - editor de ofertas");
	}
	@Test(priority = 1, description = "Verificando se o INPUT nome esta presente na pagina index")
	public void verificandoInputNomePresentePaginaIndex () {
		assertTrue(isElementPresent(By.xpath("//input[@id='username']")));
		assertEquals(driver.findElement(By.cssSelector("#username")).getAttribute("placeholder"), "Usuário");
	}
	@Test(priority = 2, description = "Verificando se o INPUT senha esta presente na pagina index")
	public void verificandoInputSenhaPresentePaginaIndex (){
		assertTrue(isElementPresent(By.xpath("//input[@id='password']")));
		assertEquals(driver.findElement(By.cssSelector("#password")).getAttribute("placeholder"), "Senha");
	}
	@Test(priority = 3, description = "Verificando se o INPUT sigla está presente na pagina Index")
	public void verificandoInputSiglaPresentePaginaIndex (){
		assertTrue(isElementPresent(By.xpath("//input[@id='merchant']")));
		assertEquals(driver.findElement(By.cssSelector("#merchant")).getAttribute("placeholder"), "Lojista (sigla)");
	}
	@Test(priority = 4, description = "Verificando se o BUTTON entrar está presente na pagina Index")
	public void verificandoButtonEntrarPresentePaginaIndex() {
		assertTrue(isElementPresent(By.xpath("//button[@id='btn-login']")));
	    assertEquals(driver.findElement(By.xpath("//button[@id='btn-login']")).getText(), "Entrar");
	}
	@Test(priority = 5, description = "Verificando se o LOGO UOLET está apresentando a foto correta")
	public void verificandoLogoUoletEstaCorreto (){
		assertTrue(isElementPresent(By.xpath("//img[@src='images/uolet-icon-120.png']")));
	}
	
	
	 //verificacao de erro apos a classe
	 @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {	 
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	//verificando os elementos
		private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }
	 
	  
}

