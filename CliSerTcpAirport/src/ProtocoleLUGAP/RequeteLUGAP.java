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
import java.security.*;
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
        long temps = (new Date()).getTime();
        double alea = Math.random();
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream bdos = new DataOutputStream(baos);
            bdos.writeLong(temps); bdos.writeDouble(alea);
            MessageDigest md = MessageDigest.getInstance("SHA-1", "BC");
            md.update(motdepasse.getBytes());
            md.update(baos.toByteArray());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RequeteLUGAP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RequeteLUGAP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequeteLUGAP.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        if (type==REQUEST_LISTEBAGAGE)
            return new Runnable()
            {
                public void run()
                {
                    traiteRequeteListeVols(s, cs);
                }
            };
        if (type==REQUEST_RECEPBAGAGE)
            return new Runnable()
            {
                public void run()
                {
                    traiteRequeteReceptionBagage(s, cs);
                }
            };
        if (type==REQUEST_VERIFBAGAGE)
            return new Runnable()
            {
                public void run()
                {
                    traiteRequeteVerificationBagage(s, cs);
                }
            };
        if (type==REQUEST_CHARGBAGAGE)
            return new Runnable()
            {
                public void run()
                {
                    traiteRequeteChargementBagage(s, cs);
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
   
   private void traiteRequeteReceptionBagage(Socket sock, ConsoleServeur cs)
   {
       
   }
   
   private void traiteRequeteVerificationBagage(Socket sock, ConsoleServeur cs)
   {
       
   }
   
   private void traiteRequeteChargementBagage(Socket sock, ConsoleServeur cs)
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
    
    public void addChargeUtile(String charge)
    {
        this.chargeUtile += sepChamp+charge;
    }
    
    public Socket getSocketClient() {
        return socketClient;
    }
    
    public void setSocketClient(Socket socketClient) {
        this.socketClient = socketClient;
    }
//</editor-fold>
}
