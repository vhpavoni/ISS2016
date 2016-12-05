/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.swing.JLabel;
import view.Inicial;
 
public class Timer extends Thread {
 
	public JLabel label;
	private int segundos;
        private Inicial inicial;
        private DateFormat formato;
 
	public Timer(int s, Inicial i) {
		label = new JLabel();
		formato = new SimpleDateFormat("hh:mm:ss");
                segundos=s;
                inicial=i;
	}
 
	@Override
	public void run() {
 
		while (true) {
                    try {
                        
                        Timer.sleep(segundos*1000);
                        inicial.verficarDecCredito();
                        if(inicial.getValor()==4){
                            inicial.atualizarTempo();
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
	}
 
}