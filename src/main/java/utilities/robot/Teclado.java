package utilities.robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author mvilches
 */
public class Teclado {

    private String texto;
    private String comandos;
    private Robot robotTeclado;

    public Teclado(String texto, String comandos) {
        this.texto = texto;
        this.comandos = comandos;
    }

    public Teclado() {
    }

    public Teclado(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getComandos() {
        return comandos;
    }

    public void setComandos(String comandos) {
        this.comandos = comandos;
    }

    public boolean teclearTexto() {
        try {
            robotTeclado = new Robot();
            for (int x = 0; x < this.texto.length(); x++) {

                robotTeclado.keyPress(this.texto.toUpperCase().codePointAt(x));
                robotTeclado.keyRelease(this.texto.toUpperCase().codePointAt(x));
            }
            return true;
        } catch (AWTException awEx) {
            System.out.println("Ha ocurrido un problema al manipular el teclado!");
            return false;
        }
    }
    public static void main (String [] args) throws InterruptedException, AWTException{
            
        Thread.sleep(7000);
        Teclado teclado = new Teclado();
        teclado.teclearTexto();

        Thread.sleep(7000);
        Robot robot = new Robot();     

        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);      
    
        robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_B);
            robot.keyRelease(KeyEvent.VK_B);
        robot.keyRelease(KeyEvent.VK_SHIFT);

        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);

        robot.keyPress(KeyEvent.VK_N);
        robot.keyRelease(KeyEvent.VK_N);

        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);

        robot.keyPress(KeyEvent.VK_O);
        robot.keyRelease(KeyEvent.VK_O);

        robot.keyPress(KeyEvent.VK_0);
        robot.keyRelease(KeyEvent.VK_0);
        robot.keyPress(KeyEvent.VK_1);
        robot.keyRelease(KeyEvent.VK_1);

        robot.keyPress(KeyEvent.VK_DECIMAL);
        robot.keyRelease(KeyEvent.VK_DECIMAL);            

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(8000);
    }
}