/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AirportClasses;

/**
 *
 * @author 'Toine
 */
public class Bagages {
    
    //<editor-fold defaultstate="collapsed" desc="Variables">
    private String Identifiant;
    private double Poids;
    private String Type;
    private boolean Receptionne;
    private char ChargeSoute;
    private boolean VerifieDouane;
    private String Remarques;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter et Getter">
    public String getRemarques() {
        return Remarques;
    }
    
    public void setRemarques(String Remarques) {
        this.Remarques = Remarques;
    }
    
    public boolean isVerifieDouane() {
        return VerifieDouane;
    }
    
    public void setVerifieDouane(boolean VerifieDouane) {
        this.VerifieDouane = VerifieDouane;
    }
    
    public char getChargeSoute() {
        return ChargeSoute;
    }
    
    public void setChargeSoute(char ChargeSoute) {
        this.ChargeSoute = ChargeSoute;
    }
    
    public boolean isReceptionne() {
        return Receptionne;
    }
    
    public void setReceptionne(boolean Receptionne) {
        this.Receptionne = Receptionne;
    }
    
    public String getType() {
        return Type;
    }
    
    public void setType(String Type) {
        this.Type = Type;
    }
    
    public double getPoids() {
        return Poids;
    }
    
    public void setPoids(double Poids) {
        this.Poids = Poids;
    }
    
    public String getIdentifiant() {
        return Identifiant;
    }
    
    public void setIdentifiant(String Identifiant) {
        this.Identifiant = Identifiant;
    }
    //</editor-fold>

}
