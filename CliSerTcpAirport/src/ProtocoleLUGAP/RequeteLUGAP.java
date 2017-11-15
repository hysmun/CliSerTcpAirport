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
            BDConnection = new BDUtilities("localhost", 5500);
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
    private StringTokenizer strTok;
    

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
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(motdepasse.getBytes());
            md.update(baos.toByteArray());
            setChargeUtile(login);
            addChargeUtile(String.valueOf(temps));
            addChargeUtile(String.valueOf(alea));
            addChargeUtile(new String(md.digest()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RequeteLUGAP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequeteLUGAP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.type = REQUEST_LOGIN;
        strTok = new StringTokenizer(chargeUtile, sepChamp+"");
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
        String loginT="user", mdpT="user", digest = "";
        long temps = 0;
        double alea = 0;
        int ttype = ReponseLUGAP.CONNECTION_KO;
        String adresseDistante = sock.getRemoteSocketAddress().toString();
        /* recherche login mdp*/
        cs.TraceEvenements(adresseDistante+"#charge utile "+ getChargeUtile()+"#"+Thread.currentThread().getName());


        if(loginT.equals(nextToken()))
        {
            //login ok
            //creation du digest
             try {
                  ByteArrayOutputStream baos = new ByteArrayOutputStream();
                  DataOutputStream bdos = new DataOutputStream(baos);
                  bdos.writeLong(temps); bdos.writeDouble(alea);
                  MessageDigest md = MessageDigest.getInstance("SHA-1");
                  md.update(mdpT.getBytes());
                  md.update(baos.toByteArray());
                  digest = new String(md.digest());
              } catch (NoSuchAlgorithmException ex) {
                  Logger.getLogger(RequeteLUGAP.class.getName()).log(Level.SEVERE, null, ex);
              } catch (IOException ex) {
                Logger.getLogger(RequeteLUGAP.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(digest.equals(nextToken()))
            {
                //digest le même
                ttype = ReponseLUGAP.CONNECTION_OK;
            }
        }
        
        
        //reponse !
        
        ReponseLUGAP repLugap = new ReponseLUGAP(ttype, chargeUtile);
        ObjectOutputStream oos;
        try
        {
            oos = new ObjectOutputStream(sock.getOutputStream());
            oos.writeObject(repLugap); oos.flush();
            oos.close();
        }
        catch (IOException e)
        {
            System.err.println("Erreur réseau ? [" + e.getMessage() + "]");
        }
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
    
    public String nextToken()
    {
        if(strTok != null)
        {
            return strTok.nextToken();
        }
        return null;
    }
    
    public String nextTokenListe()
    {
        if(strTok != null)
        {
            return strTok.nextToken(sepList+"");
        }
        return null;
    }
//</editor-fold>
}
