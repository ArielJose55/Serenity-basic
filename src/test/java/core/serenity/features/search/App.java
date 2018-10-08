package core.serenity.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import core.serenity.tasks.Pasos;


@RunWith(SerenityRunner.class)
public class App {


    @Managed(driver="chrome") //driver que va hacer manejado con serenity
    public WebDriver driver;

    @Steps
    Pasos pasos;

    @Test
    public void send_email_to_persons() throws InterruptedException{
    	pasos.paso1(driver);
    	pasos.paso2();
    	pasos.paso3();
    }
}