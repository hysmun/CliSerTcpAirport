/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProtocoleLUGAP;
import BDUtilities.BDUtilities;
import serveurpoolthreads.*;
import requetepoolthreads.Requete;
import java.io.*;
import java.util.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 'Toine
 */
public class RequeteLUGAP implements Requete, Serializable
{
    //<editor-fold defaultstate="collapsed" desc="Connection BD">
    static private BDUtilities BDConnection;
    static
    {
        try {
            BDConnection = new BDUtilities("localhost", 5555);
        } catch (Exception ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>
    
    public static int REQUEST_LOGIN         = 1;
    public static int REQUEST_LISTEVOLS     = 2;
    public static int REQUEST_LISTEBAGAGE   = 3;
    public static int REQUEST_RECEPBAGAGE   = 4;
    public static int REQUEST_VERIFBAGAGE   = 4;
    public static int REQUEST_CHARGBAGAGE   = 4;
    
    public static char sepChamp = '#';
    public static char sepList = '|';
    
    private int type;
    private String chargeUtile;
    private Socket socketClient;
    

    public RequeteLUGAP(int type, String chargeUtile) {
        this.type = type;
        this.chargeUtile = chargeUtile;
    }
    
    public RequeteLUGAP(String login, String motdepasse) {
        /*faire le digest salé ici*/
        this.type = REQUEST_LOGIN;
    }
    
    public RequeteLUGAP(int type, String chargeUtile, Socket socketClient) {
        this.type = type;
        this.chargeUtile = chargeUtile;
        this.socketClient = socketClient;
    }
    
    @Override
    public Runnable createRunnable(Socket s, ConsoleServeur cs) {
        if (type==REQUEST_LOGIN)
            return new Runnable()
            {
                public void run()
                {
                    traiteRequeteLogin(s, cs);
                }
            };
        if (type==REQUEST_LISTEVOLS)
            return new Runnable()
            {
                public void run()
                {
                    traiteRequeteListeVols(s, cs);
                }
            };
        /*
        if(type == )
            return new Runnable()
            {
                public void run()
                {
                    traiteRequeteKey(s, cs);
                }
            };
        */
        return null;
    }
    
   private void traiteRequeteLogin(Socket sock, ConsoleServeur cs)
   {
       
   }
   
   private void traiteRequeteListeVols(Socket sock, ConsoleServeur cs)
   {
       
   }

//<editor-fold defaultstate="collapsed" desc="Getter et Setter">
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getChargeUtile() {
        return chargeUtile;
    }
    
    public void setChargeUtile(String chargeUtile) {
        this.chargeUtile = chargeUtile;
    }
    
    public Socket getSocketClient() {
        return socketClient;
    }
    
    public void setSocketClient(Socket socketClient) {
        this.socketClient = socketClient;
    }
//</editor-fold>
}
