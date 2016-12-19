package com.uolet.pages.console;

import org.openqa.selenium.WebDriver;

public class PaginaHomeEditor {
	private WebDriver driver;
	public PaginaHomeEditor(WebDriver driver){
		if (!driver.getCurrentUrl().equals("http://console.uolet.com/editor/index.html")){
			throw new IllegalStateException("Você não esta na pagina index, sua pagina atual é: " + driver.getCurrentUrl());
		}	
	}
	public PaginaHomeEditor gerenciarPerfil() {
		return new PaginaHomeEditor(driver);
	}

}
