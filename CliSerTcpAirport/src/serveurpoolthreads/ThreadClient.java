package serveurpoolthreads;

import BDUtilities.BDUtilities;
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
    
    
    public ThreadClient(SourceTaches st, String n )
    {
        tachesAExecuter = st;
        nom = n;
    }
    
    public void run()
    {
        while (!isInterrupted())
        {
            try
            {
                System.out.println("Tread client avant get");
                //this.wait();
                tacheEnCours = tachesAExecuter.getTache();
            }
            catch (InterruptedException e)
            {
                System.out.println("Interruption thread num "+ this.nom + " : " + e.getMessage());
                Thread.currentThread().interrupt();
            }
            System.out.println("run de tachesencours");
            tacheEnCours.run();
        }
    }
}