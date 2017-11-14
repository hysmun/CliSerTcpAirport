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

    @Override
    public int getCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
