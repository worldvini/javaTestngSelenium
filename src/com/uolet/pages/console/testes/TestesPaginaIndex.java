package com.uolet.pages.console.testes;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.uolet.pages.console.Index;

public class TestesPaginaIndex {
	private StringBuffer verificationErrors = new StringBuffer();
	private WebDriver driver;
	
	public TestesPaginaIndex(WebDriver driver) {
		this.driver = driver;
		if (!driver.getCurrentUrl().equals("http://console.uolet.com/editor/index.html")){
			throw new IllegalStateException("Você não esta na pagina index, sua pagina atual é: " + driver.getCurrentUrl());
		}
	}
	public Index verificandoTituloPaginaIndex() {
		Assert.assertEquals(driver.getTitle(), "Uolet - editor de ofertas");		
		return new Index(driver);
	}
	public Index verificandoInputNomePresente() {		
		assertTrue(isElementPresent(By.xpath("//input[@id='username']")));
		try {
			assertEquals(driver.findElement(By.cssSelector("#username")).getAttribute("placeholder"), "Usuário");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		return new Index(driver);
	}
	public Index verificandoInputSenhaPresente() {
		assertTrue(isElementPresent(By.xpath("//input[@id='password']")));
		try {
			assertEquals(driver.findElement(By.cssSelector("#password")).getAttribute("placeholder"), "Senha");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		return new Index(driver);
	}
	public Index verificandoInputSiglaPresente(){
		assertTrue(isElementPresent(By.xpath("//input[@id='merchant']")));
		try {
			assertEquals(driver.findElement(By.cssSelector("#merchant")).getAttribute("placeholder"), "Lojista (sigla)");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		return new Index(driver);
	}
	public Index verificandoButtonLogarPresente(){
		assertTrue(isElementPresent(By.xpath("//button[@id='btn-login']")));
	    try {
	      assertEquals(driver.findElement(By.xpath("//button[@id='btn-login']")).getText(), "Entrar");
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    return new Index(driver);
	}
	public Index buscaLogo(){
		try {
			assertTrue(isElementPresent(By.xpath("//img[@src='images/uolet-icon-120.png']")));
		} catch (Error e){
			verificationErrors.append(e.toString());
		}
		return new Index(driver);
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
