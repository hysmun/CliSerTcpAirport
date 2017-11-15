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
import org.bouncycastle.asn1.crmf.POPOPrivKey;
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
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            try
            {
                cs = new Socket("localhost",3580);
                /*
                ois = new ObjectInputStream(cs.getInputStream());
                oos = new ObjectOutputStream(cs.getOutputStream());
                */
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
            oos = lf.oos;
            ois = lf.ois;
            
            ClientBagages cb = new ClientBagages(cs, oos, ois);
            cb.oos = oos;
            cb.ois = ois;
            cb.setVisible(true);
            
        }
        catch (Exception ex)
        {
            Logger.getLogger(applicMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
