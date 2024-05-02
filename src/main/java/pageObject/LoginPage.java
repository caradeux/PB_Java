package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ambientes.AbstractScreen;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static java.awt.SystemColor.window;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginPage extends AbstractScreen {
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }
    private final static Logger LOGGER = Logger.getLogger("utilities.log.Log");
    private Map<String, Object> vars;
    JavascriptExecutor js;


    @FindBy( xpath = "//input[@id='j_username']")
    public WebElement user;

    @FindBy( xpath = "//input[@id='j_password']")
    public WebElement pass;

    @FindBy( xpath = "//button[@id='logOnFormSubmit']/div")
    public WebElement ingresar;

    @FindBy(xpath = "//*[@id=\"__item0-anchorNavigationBar-4-inner\"]")
    public  WebElement mnuDisponibilidadAngularV2;
    @FindBy(partialLinkText = "Disponibilidad Angular")
    public  WebElement mnuDisponibilidadAngular;

    @FindBy(xpath = "/html/body/app-root/app-list/div/div/div/fd-busy-indicator/fd-layout-panel/fd-layout-panel-body/div/fd-tab-list/div[2]/fd-tab[1]/div/div/disponibilidades-tile[1]/a/fd-tile/div")
    public WebElement camiones;


    @FindBy (xpath = "//a[@id='nuevos']/span")
    public WebElement nuevos;

    @FindBy (xpath = "//fdp-table[@id='fdp-table-1']/fd-busy-indicator/div/div/table/tbody/tr/td/div")
    public WebElement modelo;

    @FindBy (css = ".fd-table__row--activable:nth-child(1) > .fdp-table__col--ChassisCode > .fd-table__text")
    public WebElement opcionlista;
    @FindBy (linkText = "Cotizar")
    public  WebElement btnCotizar;

    @FindBy (id = "application-v2cotizacion-Display-component---master--vendedorId-vhi")
    public  WebElement idVendedor;
    @FindBy (id = "application-v2cotizacion-Display-component---master--vendorNameId-inner")
    public  WebElement SelecVendedor;
    @FindBy (xpath ="//td[@id='__dialog12-table-rows-row2-col1']")
    public  WebElement SelecPony;
    @FindBy (id = "application-v2cotizacion-Display-component---master--clienteId-vhi")
    public  WebElement idCustomer;
    @FindBy (id = "application-v2cotizacion-Display-component---master--inputCustomerName-inner")
    public  WebElement nameCustomer;
    @FindBy (xpath ="//*[@id=\"__dialog13-table-rows-row0-col1\"]")
    public  WebElement selecCustomer;
    @FindBy (id = "__mbox-btn-0-inner")
    public  WebElement msgPopup;
    @FindBy (id = "application-v2cotizacion-Display-component---master--oficinaVentaId-arrow")
    public  WebElement selecSucursal;

    @FindBy (xpath = "//*[@id=\"application-v2cotizacion-Display-component---master--oficinaVentaId-hiddenSelect\"]")
    public WebElement OfVentas;
    @FindBy (id = "application-v2cotizacion-Display-component---master--oficinaVentaId-hiddenSelect")
    public WebElement cboSucursal;

    @FindBy (xpath = "//*[@id=\"application-v2cotizacion-Display-component---master--fragmentQuotationState-inner\"]")
    public WebElement cboEstado;

    @FindBy(xpath = "/html/body/div[1]/div[1]/section/div/div/div/div[2]/div[1]/div[2]/div/div[3]/div[1]/div[4]/div[1]/div/table/tbody/tr[1]/td[1]/div")
    public WebElement itemTabla;

    @FindBy(xpath = "//*[@id=\"application-v2cotizacion-Display-component---master--btEditarId-BDI-content\"]")
    public WebElement btnEditar;

    @FindBy (xpath = "//*[@id=\"__bar0-btnGo\"]")
    public  WebElement btnIR;
    @FindBy (xpath = "//*[@id=\"__filter2-icon\"]")
    public WebElement Accesorios;


    public String waitForWindow(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> whNow = driver.getWindowHandles();
        Set<String> whThen = (Set<String>) vars.get("window_handles");
        if (whNow.size() > whThen.size()) {
            whNow.removeAll(whThen);
        }
        return whNow.iterator().next();
    }

    @Step("Login")
    public void LoginSistema() throws Exception {
        waitForElement(user);
        user.click();
        user.sendKeys("jcaradeux");
        waitForElement(pass);
        pass.click();
        pass.sendKeys("kaufmann.01");
        Captura("Ingreso Datos Login");
        Thread.sleep(500);
        waitForElement(ingresar);
        ingresar.click();
        Thread.sleep(3000);

    }


    public void IngresoCotizacion() throws Exception {
        Thread.sleep(3000);
        waitForElement(mnuDisponibilidadAngularV2);
        mnuDisponibilidadAngularV2.click();
        Captura("Menu");
        Thread.sleep(6000);
        System.out.println("Paso");
        Thread.sleep(6000);
        waitForElement(mnuDisponibilidadAngular);
        Captura("Menu");
        String parentWindowHandler = driver.getWindowHandle(); // Almacena tu ventana actual
        String subWindowHandler = null;

        waitForElement(mnuDisponibilidadAngular);
        mnuDisponibilidadAngular.click();
        Captura("Menu");
        Thread.sleep(9500);


        Set<String> handles = driver.getWindowHandles(); // Obten todas las ventana abiertas
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler); // Cámbiate a la ultima ventana (tu pop-up)
        Thread.sleep(15000);
        // Aquí rellena la información referente a tu pop-up
        waitForElement(camiones);
        camiones.click();
        Captura("Camiones");
        Thread.sleep(15000);
        waitForElement(nuevos);
        nuevos.click();
        Thread.sleep(3000);
        waitForElement(modelo);
        modelo.click();
        Thread.sleep(12000);
        waitForElement(opcionlista);
        opcionlista.click();
        Captura("Opcion Lista");
        Thread.sleep(500);
        waitForElement(btnCotizar).click();
        Captura("Cotizar");
        Thread.sleep(12000);
        Set<String> handles2 = driver.getWindowHandles(); // Obten todas las ventana abiertas
        Iterator<String> iterator2 = handles2.iterator();
        while (iterator2.hasNext()){
            subWindowHandler = iterator2.next();
        }
        Thread.sleep(8000);
        driver.switchTo().window(subWindowHandler); // Cámbiate a la ultima ventana (tu pop-up)
        Thread.sleep(20000);
        selecSucursal.click();
        Thread.sleep(2000);
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_DOWN);
        r.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        r.keyPress(KeyEvent.VK_DOWN);
        r.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        waitForElement(idVendedor).click();
        Thread.sleep(3000);
        waitForElement(SelecVendedor).click();
        Thread.sleep(1000);
        waitForElement(SelecVendedor).sendKeys("francisco");
        Thread.sleep(1000);
        Captura("Vendedor");
        waitForElement(SelecVendedor).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        waitForElement(SelecPony).click();
        Thread.sleep(1000);
        waitForElement(idCustomer).click();
        Thread.sleep(1000);
        waitForElement(nameCustomer).click();
        Thread.sleep(1000);
        waitForElement(nameCustomer).sendKeys("jose");
        Captura("Cliente");
        Thread.sleep(2000);
        waitForElement(nameCustomer).sendKeys(Keys.ENTER);
        Thread.sleep(10000);
        waitForElement(selecCustomer).click();
        Thread.sleep(5000);
        waitForElement(msgPopup).click();
        Thread.sleep(5000);
        waitForElement(selecSucursal);
        Thread.sleep(8000);

        driver.findElement(By.id("__button1")).click();
        Captura("Form Completo");

        driver.findElement(By.id("application-v2cotizacion-Display-component---master--btGuardarId-BDI-content")).click();

        Thread.sleep(12000);
        driver.findElement(By.id("__mbox-btn-1-BDI-content")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("__mbox-btn-2-BDI-content")).click();


  }


    public void AgregarServicios() throws Exception {
        Thread.sleep(9000);
        waitForElement(mnuDisponibilidadAngularV2);
        Thread.sleep(3000);
        mnuDisponibilidadAngularV2.click();
        Thread.sleep(8000);
        System.out.println("Paso");
        driver.findElement(By.xpath("//*[@id=\"__tile11\"]")).click();
        Thread.sleep(22000);

        driver.findElement(By.xpath("//*[@id=\"application-v2cotizacion-Display-component---master--cotizacionId-inner\"]")).click();
        Thread.sleep(5000);

        waitForElement(cboEstado);
        cboEstado.sendKeys("CREA - Cotización Creada");
        Thread.sleep(6000);

        btnIR.click();
        Thread.sleep(6000);

        //Tabla
        itemTabla.click();
        Thread.sleep(12000);

        //Btn Editar
        waitForElement(btnEditar);
        btnEditar.click();
        Thread.sleep(15000);

        System.out.println("Paso 2");
        driver.findElement(By.xpath("//div[12]/div/span[2]")).click();
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_DOWN);
        r.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(400);
        r.keyPress(KeyEvent.VK_DOWN);
        r.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(400);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[@id = '__filter2-icon']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//td[5]/div/div/div/div[2]/span/span")).click();
        Thread.sleep(1000);
        //Presiono +
        driver.findElement(By.xpath("//button[2]/span/span")).click();
        Thread.sleep(8000);
        driver.findElement(By.xpath("//*[@id=\"application-v2cotizacion-Display-component---master--btGuardarId-BDI-content\"]")).click();
        Thread.sleep(12000);
        driver.findElement(By.xpath("//*[@id=\"__mbox-btn-0-inner\"]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"__mbox-btn-1-BDI-content\"]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"application-v2cotizacion-Display-component---master--btAceptarProformaId-BDI-content\"]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"__mbox-btn-2-inner\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"__mbox-btn-4-inner\"]")).click();
        Thread.sleep(12000);

    }

    public void AgregarFinanciamiento() throws Exception {

        Thread.sleep(9000);
        waitForElement(mnuDisponibilidadAngularV2);
        Thread.sleep(3000);
        mnuDisponibilidadAngularV2.click();
        Thread.sleep(4000);
        System.out.println("Paso");
        driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div/main/div/section/div/div[2]/div[2]/div/div/div/div/section/div/div[5]/div/div/ul/li[4]/div/div/div/a/div[1]")).click();
        Thread.sleep(22000);

        driver.findElement(By.xpath("//*[@id=\"application-v2cotizacion-Display-component---master--cotizacionId-inner\"]")).click();
        Thread.sleep(5000);

        waitForElement(cboEstado);
        cboEstado.sendKeys("CREA - Cotización Creada");
        Thread.sleep(6000);

        btnIR.click();
        Thread.sleep(6000);

        //Tabla
        itemTabla.click();
        Thread.sleep(12000);

        //Btn Editar
        waitForElement(btnEditar);
        btnEditar.click();
        Thread.sleep(15000);

        System.out.println("Paso 2");
        driver.findElement(By.xpath("//div[12]/div/span[2]")).click();
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_DOWN);
        r.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(400);
        r.keyPress(KeyEvent.VK_DOWN);
        r.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(400);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        //************************
        driver.findElement(By.xpath("//*[@id=\"__filter5-icon\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id = '__input30-inner' and @type = 'number']")).sendKeys("30");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id = '__input31-inner' and @type = 'number']")).sendKeys("48");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"__button3-BDI-content\"]")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"__button25-BDI-content\"]")).click();
        Thread.sleep(8000);
        //************************
        Thread.sleep(8000);
        driver.findElement(By.xpath("//*[@id=\"application-v2cotizacion-Display-component---master--btGuardarId-BDI-content\"]")).click();
        Thread.sleep(12000);
        driver.findElement(By.xpath("//*[@id=\"__mbox-btn-0-inner\"]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"__mbox-btn-1-BDI-content\"]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"application-v2cotizacion-Display-component---master--btAceptarProformaId-BDI-content\"]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"__mbox-btn-2-inner\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"__mbox-btn-4-inner\"]")).click();
        Thread.sleep(12000);


    }
}



