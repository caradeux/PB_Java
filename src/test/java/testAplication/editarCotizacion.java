package testAplication;

import ambientes.AbstractTestAplication;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class editarCotizacion extends AbstractTestAplication {



    @Attachment
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Edicion + Accesorio")
    @Description("Descripcion")
    public void CotizacionAccesorio() throws Exception {

        app.PantallaLogin().LoginSistema();
        app.PantallaLogin().AgregarServicios();

    }

    @Attachment
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Edicion + Financiamiento")
    @Description("Descripcion")
    public void CotizacionFinanciamiento() throws Exception {

        app.PantallaLogin().LoginSistema();
        app.PantallaLogin().AgregarFinanciamiento();

    }



    @AfterMethod
    public void teardown() {

       //driver.quit();
    }





}
