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
    
    public static void main(String[] args) {
        
        try
        {
            Socket cs=null;
            ObjectOutputStream oos;
            ObjectInputStream ois;
            try
            {
                cs = new Socket("localhost",3580);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(applicMain.class.getName()).log(Level.SEVERE, null, ex);
                }
                oos = new ObjectOutputStream(cs.getOutputStream());
                ois = new ObjectInputStream(cs.getInputStream());
                 
            }
            catch (IOException e)
            {
                System.out.println("Erreur connexion client -> serveur : " + e.getMessage());
            }
            
            System.out.println("Crea login");
            LoginForm lf = new LoginForm(null,true);
            lf.CS = cs;
            lf.setVisible(true);
            
            
            while(lf.isVisible() == true)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(applicMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            ClientBagages cb = new ClientBagages(cs);
            cb.setVisible(true);
            cb.refreshListBagage();
            
        }
        catch (Exception ex)
        {
            Logger.getLogger(applicMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
