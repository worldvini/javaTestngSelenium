package com.uolet.casos.teste;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.omg.CORBA.COMM_FAILURE;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EfetuandoTentativasInvalidasLogin {
	public String baseUrl = "http://console.uolet.com/editor/index.html";
	private StringBuffer verificationErrors = new StringBuffer();
	WebDriver driver;
	
	@BeforeClass
	public void antesTeste(){
		System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	    fail(verificationErrorString);
	    }
	}
	@BeforeMethod
	public void limpandoCampos () {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("merchant")).clear();
	}
	@AfterMethod
	public void verifyMessageErrorExistAndClose () {
		try {
			assertTrue(isElementPresent(By.id("login-error")));
		} catch (Error e) {
			verificationErrors.append(e.toString()); 
		}
		try {
			assertTrue(isElementPresent(By.id("btn-dismiss")));
		} catch (Error e) {
			verificationErrors.append(e.toString()); 
		}
		driver.findElement(By.id("btn-dismiss")).click();
		try {
			assertFalse(isElementPresent(By.id("login-error")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
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
	private void logar(String nome, String senha, String sigla) throws InterruptedException{		
		//driver.findElement(By.id("username")).sendKeys(nome);
		//driver.findElement(By.id("password")).sendKeys(senha);
		//driver.findElement(By.id("merchant")).sendKeys(sigla);
		//driver.findElement(By.id("btn-login")).click();
	}	
	com.uolet.pages.console.PaginaLogin paginaLogar = new com.uolet.pages.console.PaginaLogin(driver);
	
	@Test(priority = 0, description = "Tentativa de logar com [todos] os campos vazios")
	public void tentandoLogarSemPreecherNenhumCampo() {
		paginaLogar.logar("", "", "");
	}
	@Test(priority = 1, description = "Tentativa de logar com [nome] preenchido e [senha, sigla] vazio")
	public void tentandoLogarPreenchendoSomenteNome(){
		paginaLogar.logar("uolet","","");
	}
	@Test(priority = 2, description = "Tentativa de logar com [senha] preenchido e [nome, sigla] vazio")
	public void tentandoLogarPreenchendoSomenteSenha(){
		paginaLogar.logar("","12345","");
	}
	@Test(priority = 3, description = "Tentativa de logar com [sigla] preenchido e [nome, senha] vazio")
	public void tentandoLogarPreenchendoSomenteSigla(){
		paginaLogar.logar("","","UQA");
	}
	@Test(priority = 4, description = "Tentativa de logar com [nome, senha] preenchido e [sigla] vazio")
	public void tentandoLogarPreenchendoSomenteNomeSenha(){
		paginaLogar.logar("uolet","12345","");
	}
	@Test(priority = 5, description = "Tentativa de logar com [nome, sigla] preenchido e [senha] vazio")
	public void tentandoLogarPreenchendoSomenteNomeSigla(){
		paginaLogar.logar("uolet","","UQA");
	}
	@Test(priority = 6, description = "Tentativa de logar com [senha, sigla] preenchido e [nome] vazio")
	public void tentandoLogarPreenchendoSomenteSenhaSigla(){
		paginaLogar.logar("","12345","UQA");
	}
	@Test(priority = 7, description = "Tentativa de logar com [nome, sigla, senha] preenchido")
	public void tentandoLogarPreenchendoTodosCamposUsuarioInvalido() throws InterruptedException{
		logar("uolet","12345","UQA");
		for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (!isElementPresent(By.xpath("//button[@class='disabled']"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }
	}
}
