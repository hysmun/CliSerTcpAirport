/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CliSerBagages;

import java.util.logging.Level;
import java.util.logging.Logger;
import serveurpoolthreads.ConsoleServeur;

/**
 *
 * @author Rémy
 */
public class applicMain {


    public static void main(String[] args) {
        LoginForm lf = new LoginForm(null,true);
        lf.setVisible(true);
        
        while(lf.isVisible() == true)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(applicMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ClientBagages cb = new ClientBagages(lf.CSocket);
        cb.setVisible(true);
        cb.refreshListBagage();
        
    }
    
}
