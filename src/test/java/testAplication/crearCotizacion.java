package testAplication;

import ambientes.AbstractTestAplication;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class crearCotizacion extends AbstractTestAplication {



    @Attachment
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Generacion de Cotizacion en Sistema")
    @Description("Descripcion del Caso")

    public void CrearCotizacion() throws Exception {

        app.PantallaLogin().LoginSistema();
        app.PantallaLogin().IngresoCotizacion();
    }



    @AfterMethod
    public void teardown() {

        driver.quit();
    }





}
