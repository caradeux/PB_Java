package ambientes;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import java.io.*;
import java.util.Date;
import java.util.Properties;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Listeners;
import utilities.ExtentReports.ExtentTestNGITestListener;

@Listeners(ExtentTestNGITestListener.class)

public class AbstractBase {

    public WebDriver driver;
    public String baseUrl = null;
    public static Properties config = null;
    public static String rutaEvidencia;
    public static String fechaEjecucion;
    public static String fechaEjecucion2;
    public static String timeout_Element;
    public static String urlDataElementos;
    public static String urlDriverMac;
    public static String urlDriverChrome;
    public static String urlDriverLinux;


    public WebDriver getDriver() {
        return driver;
    }

    private final static Logger LOGGER = Logger.getLogger("utilities.log.Log");

    @BeforeMethod
    public void connect() {

        leerProperties();
        DateTime timeStamp = new DateTime();
        fechaEjecucion = timeStamp.toString("yyyyMMdd-HHmmss");
        fechaEjecucion2 = timeStamp.toString("yyyyMMdd");

        try {

            if (System.getProperty("os.name").startsWith("Linux")) {
                WebDriverManager.chromedriver().setup();
                WebDriverManager.firefoxdriver().setup();
                WebDriverManager.edgedriver().setup();


            }
            if (System.getProperty("os.name").startsWith("Mac")) {
                WebDriverManager.chromedriver().setup();
                WebDriverManager.firefoxdriver().setup();
                WebDriverManager.edgedriver().setup();
            }
            if (System.getProperty("os.name").startsWith("Windows")) {
                WebDriverManager.chromedriver().setup();
                WebDriverManager.firefoxdriver().setup();
                WebDriverManager.edgedriver().setup();
            }

        } catch (WebDriverException wex) {
            LOGGER.warn(" WebDriverException Catch 1 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: " + wex);
            wex.printStackTrace();
        } catch (Exception e) {

            LOGGER.info("Utilizando carga m\u00e1xima de p\u00e1gina por omisi\u00f3n");
            e.printStackTrace();
            throw e;
        }

    }

    @BeforeMethod
    private void leerProperties() {

        try {
            DateTime timeStamp = new DateTime();            
            fechaEjecucion = timeStamp.toString("yyyyMMdd-HHmmss");
            fechaEjecucion2 = timeStamp.toString("yyyyMMdd");

            config = new Properties();            
            config.load(getClass().getClassLoader().getResourceAsStream("allure.properties"));

            baseUrl = config.getProperty("siteUrl");
            urlDriverChrome = config.getProperty("urlChromeDriverWindows");
            urlDriverLinux = config.getProperty("urlChromeDriverLinux");
            urlDriverMac = config.getProperty("urlChromeDriverMac");
            rutaEvidencia = config.getProperty("TEST_NAME");
            timeout_Element = config.getProperty("TIMEOUT_POR_ELEMENTO");
        } catch (FileNotFoundException e) {
            System.out.println("Error, El archivo no exite");
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Error, No se puede leer el archivo");
        }
    }

    private void analyzeLog() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }
    }

    @AfterClass
    public void teardown() {
       // analyzeLog();
        driver.quit();
    }
}
