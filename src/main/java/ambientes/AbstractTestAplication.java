package ambientes;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class AbstractTestAplication extends AbstractBase{

    public AppAplication app;

    private final static Logger LOGGER = Logger.getLogger("utilities.log.Log");

    @BeforeMethod
    public void connectApp() {

        try {
            //Inicio de la Ejecución
            LOGGER.info("Inicio de Ejecucion: " + fechaEjecucion);
            LOGGER.info("Browser: " + System.getProperty("os.name"));
            LOGGER.info("URL Ambiente: " + baseUrl);

            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.INFO);

            if (config.getProperty("browserType").equals("firefox")) {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);



                driver.get(baseUrl);

            } else if (config.getProperty("browserType").equals("chrome")) {

                ChromeOptions options = new ChromeOptions();
                options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                options.addArguments("disable-infobars");
                options.addArguments("ignore-certificate-errors");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--allow-insecure-localhost");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                driver.get(baseUrl);


            }

        } catch (WebDriverException wex) {
            System.out.println(wex);
            LOGGER.warn(" WebDriverException Catch Finex Max :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: " + wex);
        } catch (Exception e) {
            LOGGER.info("Utilizando carga m\u00e1xima de p\u00e1gina por omisi\u00f3n");
            System.out.println(e);
            throw e;

        }
        app = new AppAplication(driver);
    }
}
