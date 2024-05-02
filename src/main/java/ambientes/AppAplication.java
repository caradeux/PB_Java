package ambientes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.*;


public class AppAplication {

    public WebDriver driver;
    public WebDriverWait wait;

    //Constructor
    public AppAplication(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,35);
        return;
    }
    public LoginPage PantallaLogin() {

        return new LoginPage(driver);
    }


   
    





}


