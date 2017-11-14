/*
* ThreadServeur.java
*/
package serveurpoolthreads;
import java.net.*;
import java.io.*;
import requetepoolthreads.Requete;
/**
* @author 'Toine
*/
public class ThreadServeur extends Thread
{
    private int port_bagages;
    private int port_checkin;
    private SourceTaches tachesAExecuter;
    private ConsoleServeur guiApplication;
    private ServerSocket SSocket = null;
    private int nbrPool;
    
    public ThreadServeur(int p, SourceTaches st, ConsoleServeur fs, int nbrPool)
    {
        port_bagages = p; tachesAExecuter = st; guiApplication = fs;
        this.nbrPool = nbrPool;
    }
    public ThreadServeur(int p, SourceTaches st, ConsoleServeur fs)
    {
        port_bagages = p; tachesAExecuter = st; guiApplication = fs;
        this.nbrPool = 3;
    }
    public void run()
    {
        try
        {
            SSocket = new ServerSocket(port_bagages);
        }
        catch (IOException e)
        {
            System.err.println("Erreur de port d'écoute ! ? [" + e + "]"); System.exit(1);
        }
        // Démarrage du pool de threads
        for (int i=0; i<3; i++) // 3 devrait être constante ou une propriété du fichier de config
        {
            ThreadClient thr = new ThreadClient (tachesAExecuter, "Thread du pool n°" +String.valueOf(i));
            thr.start();
        }
        // Mise en attente du serveur
        Socket CSocket = null;
        while (!isInterrupted())
        {
            try
            {
                System.out.println("************ Serveur en attente");
                CSocket = SSocket.accept();
                guiApplication.TraceEvenements(CSocket.getRemoteSocketAddress().toString()+"#accept#thread serveur");
            }
            catch (IOException e)
            {
                System.err.println("Erreur d'accept ! ? [" + e.getMessage() + "]"); System.exit(1);
            }
            ObjectInputStream ois=null;
            Requete req = null;
            try
            {
                ois = new ObjectInputStream(CSocket.getInputStream());
                req = (Requete)ois.readObject();
                System.out.println("Requete lue par le serveur, instance de " +req.getClass().getName());
            }
            catch (ClassNotFoundException e)
            {
                System.err.println("Erreur de def de classe [" + e.getMessage() + "]");
            }
            catch (IOException e)
            {
                System.err.println("Erreur ? [" + e.getMessage() + "]");
            }
            Runnable travail = req.createRunnable(CSocket, guiApplication);
            if (travail != null)
            {
                tachesAExecuter.recordTache(travail);
                System.out.println("Travail mis dans la file");
            }
            else 
                System.out.println("Pas de mise en file");
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Setter et Getter">
    public int getPortBag() {
        return port_bagages;
    }
    
    public int getPortCheck() {
        return port_checkin;
    }
    private void setPortBag(int port) {
        this.port_bagages = port;
    }
    
    private void setPortCheckin(int port) {
        this.port_checkin = port;
    }
    
    public SourceTaches getTachesAExecuter() {
        return tachesAExecuter;
    }
    
    private void setTachesAExecuter(SourceTaches tachesAExecuter) {
        this.tachesAExecuter = tachesAExecuter;
    }
    
    public ConsoleServeur getGuiApplication() {
        return guiApplication;
    }
    
    private void setGuiApplication(ConsoleServeur guiApplication) {
        this.guiApplication = guiApplication;
    }
    
    public ServerSocket getSSocket() {
        return SSocket;
    }
    
    private void setSSocket(ServerSocket SSocket) {
        this.SSocket = SSocket;
    }
    
    public int getNbrPool() {
        return nbrPool;
    }
    
    public void setNbrPool(int nbrPool) {
        this.nbrPool = nbrPool;
    }
    //</editor-fold>
}