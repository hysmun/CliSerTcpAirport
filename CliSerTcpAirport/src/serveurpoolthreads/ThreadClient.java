package serveurpoolthreads;

import BDUtilities.BDUtilities;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* @author 'Toine
*/
public class ThreadClient extends Thread
{
    private SourceTaches tachesAExecuter;
    private String nom;
    private Runnable tacheEnCours;
    private Socket ss;
    
    
    public ThreadClient(SourceTaches st, String n )
    {
        tachesAExecuter = st;
        nom = n;
    }

    public Socket getSs() {
        return ss;
    }

    public void setSs(Socket ss) {
        this.ss = ss;
    }
    
    public ThreadClient(SourceTaches st, String n, Socket tss)
    {
        tachesAExecuter = st;
        nom = n;
        ss = tss;
    }
    
    public void run()
    {
        while (!isInterrupted())
        {
            try
            {
                //System.out.println("Tread client avant get" + nom);
                //this.wait();
                tacheEnCours = tachesAExecuter.getTache();
            }
            catch (InterruptedException e)
            {
                System.out.println("Interruption thread num "+ this.nom + " : " + e.getMessage());
                Thread.currentThread().interrupt();
            }
            //System.out.println("run de tachesencours"+nom);
            tacheEnCours.run();
        }
    }
}