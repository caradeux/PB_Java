package ambientes;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.ExtentReports.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static ambientes.AbstractBase.*;

public class AbstractScreen {

    private int timeout;
    public WebDriver driver;
    private ExtentTest extentTest;
    private ExtentReports extentReports;

    String workingDir = System.getProperty("user.dir");
    String filepath = workingDir+"\\Reporte\\ScreenShots\\";
    private final static Logger LOGGER = Logger.getLogger("utilities.log.Log");
    private int sleepTime = 1000;

    public AbstractScreen(WebDriver driver) {
        this.driver = driver;
    }

    private int getTimeout() {

        if (timeout == 0) {
            try {
                timeout = Integer.parseInt(timeout_Element);
                if (timeout == 0) {
                    timeout = 15;
                }
            } catch (Exception error) {
                timeout = 15;
            }
        }
        return timeout;
    }



    public WebElement waitForElement(By locator) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 50);

        try {

            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        } catch (ElementNotInteractableException e) {
            LOGGER.warn("No se interactua con el elemento de la pagina " + e.getMessage());
            Assert.fail("Elemento no encontrado" + e.getMessage());
            throw e;
        }
    }

    public WebElement waitForElement(WebElement webElement) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 35);

        try {

            //return wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (ElementNotInteractableException e) {
            LOGGER.warn("No se interactua con el elemento de la pagina " + e.getMessage());
            Assert.fail("Elemento no encontrado" + e.getMessage());
            throw e;
        }
    }
    
    public WebElement waitForDropdownElement(WebElement webElement, By locator) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 35);

        try {
            return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(webElement, locator));
        } catch (ElementNotInteractableException e) {
            LOGGER.warn("No se interactua con el elemento de la pagina " + e.getMessage());
            Assert.fail("Elemento no encontrado" + e.getMessage());
            throw e;
        }
    }

    public boolean waitUntilElementGone(By locator) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 35);

        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (ElementNotInteractableException e) {
            LOGGER.warn("No se interactua con el elemento de la pagina " + e.getMessage());
            Assert.fail("Elemento no encontrado" + e.getMessage());
            throw e;
        }
    }

    public void selectFromDropDownByValue(WebElement element, String texto) {
        WebDriverWait wait = new WebDriverWait(driver, 50);

        try{
            if (wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()){
                Select select = new Select(element);
                select.selectByValue(texto);
            }
        } catch (ElementNotInteractableException e) {
            LOGGER.warn("No se interactua con el elemento de la pagina " + e.getMessage());
            Assert.fail("Elemento no encontrado" + e.getMessage());
            throw e;
        }
    }

    public void selectFromDropDownByIndex(WebElement element, int index) {
        WebDriverWait wait = new WebDriverWait(driver, 50);

        try{
            if (wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()){
                Select select = new Select(element);
                select.selectByIndex(index);
            }
        } catch (ElementNotInteractableException e) {
            LOGGER.warn("No se interactua con el elemento de la pagina " + e.getMessage());
            Assert.fail("Elemento no encontrado" + e.getMessage());
            throw e;
        }

    }
    
    public void selectFromDropDownByVisibleText(WebElement element, String index) {
        WebDriverWait wait = new WebDriverWait(driver, 35);

        try{
            if (wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()){
                Select select = new Select(element);
                select.selectByVisibleText(index);
            }
        } catch (ElementNotInteractableException e) {
            LOGGER.warn("No se interactua con el elemento de la pagina " + e.getMessage());
            Assert.fail("Elemento no encontrado" + e.getMessage());
            throw e;
        }

    }
    
    public void trySelectFromDropdownByVisibleText(WebElement element, String optionName) throws InterruptedException {
    	int retries = 5;
    	WebDriverWait wait = new WebDriverWait(driver, 35);
    	for (int i = 0; i <= retries; i++ ) {
    		try {
    			if (wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()){
                    Select select = new Select(element);
                    select.selectByVisibleText(optionName);
                }
    		}catch (ElementNotInteractableException|NoSuchElementException e) {
    			LOGGER.warn("No se ineractua con el elemento de la pagina " + e.getMessage());
    			if(i == retries) {
    				Assert.fail("Elemento no encontrado" + e.getMessage());
    				throw e;
    			}
    		}
    		Thread.sleep(sleepTime);
    		if (element.getAttribute("value") != null && element.getAttribute("value") != "0") {
    			break;
    		}
    	}
    	
    }

    public void selectOptionFromDropDownByVisibleText(WebElement element, String optionName) {
        WebDriverWait wait = new WebDriverWait(driver, 35);
        String optionXpath = ".//option[normalize-space(.) = " + Quotes.escape(optionName) + "]";
        try{
            if (wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, By.xpath(optionXpath))).isDisplayed()){
                WebElement option = element.findElement(By.xpath(optionXpath));

                option.click();
            }
        } catch (ElementNotInteractableException e) {
            LOGGER.warn("No se interactua con el elemento de la pagina " + e.getMessage());
            Assert.fail("Elemento no encontrado" + e.getMessage());
            throw e;
        }

    }

    public void waitForFieldToBeFilled(WebElement inputText){
        WebDriverWait wait = new WebDriverWait(driver, 35);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return inputText.getAttribute("value").length() != 0;
            }
        });
    }

    public WebElement waitForElementForClic(By locator) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 45);

        try {

            return wait.until(ExpectedConditions.elementToBeClickable(locator));

        } catch (ElementNotInteractableException e) {
            LOGGER.warn("No se interactua con el elemento de la pagina " + e.getMessage());
            Assert.fail("Elemento no encontrado" + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Método para capturar evidencia de flujos automatizados
     * recibe el @nombre de la imagen
     */
    public void sacarPantallazo(String nombre) throws Exception {

        try {

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            //File screenshot = driver.getScreenshotAs(OutputType.FILE);
            File directory = new File("build/reports/");

            if (!directory.exists()) {
                FileUtils.forceMkdir(directory);

            }
            DateTime timeStamp = new DateTime();
            //Se guardan la carpeta screenshots dentro de carpeta definida en TEST_NAME
            directory = new File(directory.getPath() + "/" + rutaEvidencia + "/" + fechaEjecucion2);

            if (!directory.exists()) {
                FileUtils.forceMkdir(directory);
            }
            directory = new File(directory.getPath() + "/" + rutaEvidencia + "/" + fechaEjecucion + "/screenshots");
            if (!directory.exists()) {
                FileUtils.forceMkdir(directory);
            }
            File filename = new File(directory.getPath() + "/" + timeStamp.toString("yyyyMMdd-HHmmss") + "-" + nombre + ".png");
            FileUtils.copyFile(screenshot, filename);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warn("Exception while saving the file " + e);
        }
    }

    public void Captura(String name) {
        try {
            // Captura la captura de pantalla utilizando Selenium WebDriver
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Copia la captura de pantalla a una ubicación específica
            String screenshotPath = filepath;
            String screenshotName = name + "-" + System.currentTimeMillis() + ".png";
            File destinationFile = new File(screenshotPath, screenshotName);
            FileUtils.copyFile(screenshot, destinationFile);
            String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).
                    getScreenshotAs(OutputType.BASE64);

            ExtentTestManager.getTest().log(LogStatus.PASS, name ,ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public WebElement scrollElement(By locator) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        try {

            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail("Elemento no encontrado" + e.getMessage());
            sacarPantallazo("Elemento no encontrado");
            throw e;
        }
    }

    //Métodos para capturar Elementos ShadowRoot
    public static WebElement getShadowRoot(WebDriver driver, WebElement shadowHost) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);
    }

    public static WebElement getShadowElement(WebDriver driver, WebElement shadowHost, String cssOfShadowElement) {
        WebElement shardowRoot = getShadowRoot(driver, shadowHost);
        return shardowRoot.findElement(By.cssSelector(cssOfShadowElement));
    }
    public class WindowSwitcher {
        private WebDriver driver;

        public WindowSwitcher(WebDriver driver) {
            this.driver = driver;
        }
        public void switchToNewWindows() {
            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();

            for (String handle : handles) {
                if (!handle.equals(currentHandle)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
        }

        public void closeNewWindow() {
            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();

            for (String handle : handles) {
                if (!handle.equals(currentHandle)) {
                    driver.switchTo().window(handle);
                    driver.close();
                }
            }
            driver.switchTo().window(currentHandle);
        }
    }

}
