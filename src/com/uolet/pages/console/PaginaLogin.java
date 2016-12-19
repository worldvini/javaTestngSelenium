package com.uolet.pages.console;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class PaginaLogin {
	private WebDriver driver;
	public PaginaLogin (WebDriver driver) {
		this.driver = driver;
		if (!driver.getCurrentUrl().equals("http://console.uolet.com/editor/index.html")){
			throw new IllegalStateException("Você não esta na pagina index, sua pagina atual é: " + driver.getCurrentUrl());
		}	
	}
	public PaginaHomeEditor LogandoUsuario(String nome, String senha, String sigla) throws InterruptedException {
		logar(nome, senha, sigla);
		for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (!isElementPresent(By.xpath("//button[@class='disabled']"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }
		return new PaginaHomeEditor(driver);		
	}
	public void logar(String nome, String senha, String sigla){
		driver.findElement(By.id("username")).sendKeys(nome);
		driver.findElement(By.id("password")).sendKeys(senha);
		driver.findElement(By.id("merchant")).sendKeys(sigla);
		driver.findElement(By.id("btn-login")).click();
	}
	private boolean isElementPresent(By by) {
	   try {
	      driver.findElement(by);
	      return true;
	   } catch (NoSuchElementException e) {
	      return false;
	   }
	} 
}