/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CliSerBagages;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveurpoolthreads.*;

/**
 *
 * @author Rémy
 */


public class applicMain {

    
    public static Socket CS;
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;
    
    public static void main(String[] args) {
        
        try
        {
            CS = new Socket("localhost",3580);
            oos = new ObjectOutputStream(CS.getOutputStream());
            ois = new ObjectInputStream(CS.getInputStream());
        }
        catch (IOException e)
        {
            System.out.println("Erreur connexion client -> serveur : " + e.getMessage());
        }
        
        
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
        
        ClientBagages cb = new ClientBagages();
        cb.setVisible(true);
        cb.refreshListBagage();
        
    }
    
}
