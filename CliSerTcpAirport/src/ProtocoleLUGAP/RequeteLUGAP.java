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
    static private BDUtilities BDConnection;
    static
    {
        try {
            BDConnection = new BDUtilities("localhost", 5555);
        } catch (Exception ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Runnable createRunnable(Socket s, ConsoleServeur cs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
