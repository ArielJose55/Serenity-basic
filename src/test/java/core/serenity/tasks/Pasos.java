package core.serenity.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.propertyeditors.ReaderEditor;

import core.serenity.models.Person;
import core.serenity.util.ReaderBundle;
import core.serenity.util.ReaderExcel;
import net.thucydides.core.annotations.Step;



public class Pasos {
	
	private ReaderExcel reader;

	public Pasos() {
		try {
			reader = new ReaderExcel();
		} catch (Exception e) {
			System.err.println("No se puede obtener los registros del file Excel: "+e.getMessage());
			System.exit(0);
		}
	}
	
	private WebDriver driver;
	
	@Step("get login in gmail")
	public void paso1(WebDriver driver) throws InterruptedException {
		this.driver = driver;
		driver.get(ReaderBundle.getReader().getStringKey("url"));
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(ReaderBundle.getReader().getStringKey("email").concat(" \n"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(ReaderBundle.getReader().getStringKey("password").concat(" \n"));
		WebElement redactar = driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']"));
		assertTrue(redactar.getText().compareToIgnoreCase("REDACTAR") == 0);
	}
	
	@Step("send emails")
	public void paso2() throws InterruptedException {
		String sendString = null;
		for(Person person : reader.getData()) {
			driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']")).click();
			driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(person.getEmail());
			driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(ReaderBundle.getReader().getStringKey("subjectbox")
					.concat(" ").concat(person.getName()));
			driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).sendKeys(person.getNote());
			WebElement send = driver.findElement(By.xpath("//div[text()='Enviar']"));
			//sendString = send.getText();
			send.click();
		}
		
		//assertTrue(sendString != null && sendString.compareToIgnoreCase("Enviar") == 0);
	}
	
	@Step("verify in mail in sent")
	public void paso3() throws InterruptedException{
		driver.findElement(By.xpath("//a[@title='Enviados' and text()='Enviados']")).click();
		List<WebElement> listItems = driver.findElements(By.xpath("//div[@class='yW' and text()='Para: ']")).subList(0, 2);
		//assertTrue(isNamesSendEmails(listItems, "nislon.nieto") && isNamesSendEmails(listItems, "ricardo.nieto"));
	}
	
	private boolean isNamesSendEmails(List<WebElement> list, String name1){
		List<String> listSend = new ArrayList<>();
		for(int i = 0; i < list.size() ; i++) {
			System.out.println();
			listSend.add(list.get(i).getText().substring(0, list.get(i).getText().length() - 4));
		}
		return listSend.contains(name1);
	}
}
