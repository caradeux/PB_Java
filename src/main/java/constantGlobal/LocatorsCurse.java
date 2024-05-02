package constantGlobal;

import ambientes.AbstractScreen;
import org.openqa.selenium.WebDriver;

public class LocatorsCurse extends AbstractScreen {
    public LocatorsCurse(WebDriver driver) {
        super(driver);

    }
    public static final String Comercial = "//*[@id=\"sidebar_ul\"]/li[4]/a";
    public static final String TrabajarSolicitud = "//*[@id=\"18\"]";
    public static final String SelectBuscar = "//*[@id=\"vDYNAMICFILTERSSELECTOR1\"]";
    public static final String InputRut = "//*[@id=\"vCLIRUT1\"]";
    public static final String BtnBuscar = "//*[@id=\"BTN_SEARCH\"]";
    public static final String Editar = "//*[@id=\"vTRABAJARCON_0001\"]";
    public static final String BtnCurseExpress = "//*[@id=\"BTNCURSEEXPRESS\"]";
    public static final String BtnAceptarCurseExpress = "//*[@id=\"DVELOP_CONFIRMPANEL_BTNCURSEEXPRESSContainer_SaveButton\"]";

}
