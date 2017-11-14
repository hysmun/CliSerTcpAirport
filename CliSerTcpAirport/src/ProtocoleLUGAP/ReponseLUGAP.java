/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProtocoleLUGAP;
import java.io.*;
import requetepoolthreads.Reponse;
/**
 *
 * @author 'Toine
 */
public class ReponseLUGAP implements Reponse, Serializable{
    public static int CONNECTION_OK = 201;


    private int codeRetour;
    private String chargeUtile;

    public ReponseLUGAP(int codeRetour, String chargeUtile) {
        this.codeRetour = codeRetour;
        this.chargeUtile = chargeUtile;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter et Setter">
    @Override
    public int getCode() {
        return codeRetour;
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
