/*
* Requete.java
*/
package requetepoolthreads;
import java.net.*;
import serveurpoolthreads.ConsoleServeur;

/**
* @author 'Toine
*/
public interface Requete
{
// Ce qui va être exécuté doit connaître la socket du client distant
// ainsi que le GUI qui affiche les traces
    public Runnable createRunnable (Socket s, ConsoleServeur cs);
}