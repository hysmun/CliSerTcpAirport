/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProtocoleLUGAP;
import java.io.*;
import java.util.StringTokenizer;
import requetepoolthreads.Reponse;
/**
 *
 * @author 'Toine
 */
public class ReponseLUGAP implements Reponse, Serializable{
    public static int CONNECTION_OK     = 201;
    public static int CONNECTION_KO     = 501;
    public static int LISTVOLS          = 202;
    public static int LISTBAGAGE        = 203;
    
    public static char sepChamp = '#';
    public static char sepList = '|';

    private int codeRetour;
    private String chargeUtile;
    private transient StringTokenizer strTok;

    public ReponseLUGAP(int codeRetour, String chargeUtile) {
        this.codeRetour = codeRetour;
        this.chargeUtile = chargeUtile;
        strTok = new StringTokenizer(chargeUtile, sepChamp+"");
    }
    
    public ReponseLUGAP(int codeRetour) {
        this.codeRetour = codeRetour;
        this.chargeUtile = null;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter et Setter">
    @Override
    public int getCode() {
        return codeRetour;
    }
    
    public void addChargeUtile(String charge)
    {
        if(chargeUtile!= null)
            this.chargeUtile += ""+sepChamp+charge;
        else
            this.chargeUtile = charge;
    }

    public String nextToken()
    {
        if(strTok == null)
            strTok = new StringTokenizer(chargeUtile, sepChamp+"");
        if(strTok != null)
        {
            return strTok.nextToken();
        }
        return null;
    }
    
    public String nextTokenListe()
    {
        if(strTok == null)
            strTok = new StringTokenizer(chargeUtile, sepChamp+"");
        if(strTok != null)
        {
            return strTok.nextToken(sepList+"");
        }
        return null;
    }
    
    public void addSepListe()
    {
        this.chargeUtile += ""+sepList;
    }
    
    public int getCodeRetour() {
        return codeRetour;
    }
    
    public String getChargeUtile() {
        return chargeUtile;
    }
    
    public void setChargeUtile(String chargeUtile) {
        this.chargeUtile = chargeUtile;
    }
    //</editor-fold>
}
