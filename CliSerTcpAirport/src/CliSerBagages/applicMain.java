/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CliSerBagages;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            try
            {
                cs = new Socket("192.168.1.3",3580);
                /*
                ois = new ObjectInputStream(cs.getInputStream());
                oos = new ObjectOutputStream(cs.getOutputStream());*/
                
            }
            catch (IOException e)
            {
                System.out.println("Erreur connexion client -> serveur : " + e.getMessage());
                Logger.getLogger(applicMain.class.getName()).log(Level.SEVERE, null, e);
            }
            
            System.out.println("Crea login");
            LoginForm lf = new LoginForm(null,true, cs);
            
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
            oos = lf.oos;
            ois = lf.ois;
            
            ClientBagages cb = new ClientBagages();
            /*cb.oos = oos;
            cb.ois = ois;*/
            cb.setVisible(true);
            
        }
        catch (Exception ex)
        {
            Logger.getLogger(applicMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
