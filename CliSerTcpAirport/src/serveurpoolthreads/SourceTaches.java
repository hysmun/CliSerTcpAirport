/*
* SourceTaches.java
*/
package serveurpoolthreads;
/**
* @author 'Toine
*/
public interface SourceTaches
// synchronized ne s'utilise pas dans un interface
{
    public Runnable getTache() throws InterruptedException;
    public boolean existTaches();
    public void recordTache (Runnable r);
}