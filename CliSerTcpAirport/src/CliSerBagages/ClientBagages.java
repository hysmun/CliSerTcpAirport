/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CliSerBagages;

import ProtocoleLUGAP.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Rémy
 */
public class ClientBagages extends javax.swing.JFrame {

    /**
     * Creates new form ClientBagages
     */
    public Socket CS;
    public ObjectOutputStream oos=null;
    public ObjectInputStream ois=null;
    
    public ClientBagages() {
        try {
            initComponents();
            setTitle("Interface client");
            setLocationRelativeTo(null);
            CS = new Socket("localhost",3580);
            refreshListBagage();
        } catch (IOException ex) {
            Logger.getLogger(ClientBagages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ClientBagages(Socket tcs) {
        initComponents();
        setTitle("Interface client");
        setLocationRelativeTo(null);
        CS = tcs;
        refreshListBagage();
    }
    public ClientBagages(Socket tcs, ObjectOutputStream toos, ObjectInputStream tois) {
        initComponents();
        setTitle("Interface client");
        setLocationRelativeTo(null);
        CS = tcs;
        oos = toos;
        ois = tois;
        refreshListBagage();
    }
    
    public void refreshListBagage()
    {
        try {
            
            String stringRes;
            ArrayList<ArrayList> listOfLists = null;
            TableModel tdm;
            RequeteLUGAP req = new RequeteLUGAP(RequeteLUGAP.REQUEST_LISTEVOLS);
            ReponseLUGAP rep = null;
            System.out.println("crea flux");
            if(oos == null)
                oos = new ObjectOutputStream(CS.getOutputStream());
            oos.writeObject(req);oos.flush();
            if(ois == null)
                ois = new ObjectInputStream(CS.getInputStream());
            rep = (ReponseLUGAP)ois.readObject();
            System.out.println("Liste result obtenu"+rep.getChargeUtile());
            stringRes = rep.getChargeUtile();
            
            System.out.println("Liste result obtenu2");
            tdm = FlightTable.getModel();
            int nombre = Integer.parseInt(rep.nextToken());
            for(int i=0; i<nombre; i++)
            {
                tdm.setValueAt(rep.nextToken(), i, 0);
                tdm.setValueAt(rep.nextToken(), i, 1);
            }
            FlightTable.setModel(tdm);
            System.out.println("fin dtm");
        } catch (IOException ex) {
            Logger.getLogger(ClientBagages.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientBagages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CurrentUserLabel = new javax.swing.JLabel();
        FlightsListLabel = new javax.swing.JLabel();
        FlightTableScrollPane = new javax.swing.JScrollPane();
        FlightTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CurrentUserLabel.setText("Utilisateur :");

        FlightsListLabel.setText("Liste des vols :");

        FlightTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "idVol", "Nom"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        FlightTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FlightTableMouseClicked(evt);
            }
        });
        FlightTableScrollPane.setViewportView(FlightTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CurrentUserLabel)
                    .addComponent(FlightsListLabel)
                    .addComponent(FlightTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CurrentUserLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FlightsListLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FlightTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FlightTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FlightTableMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() > 1)
        {
            System.out.println("Evenement Click");
            JTable jt = (JTable)evt.getSource();
            int selRow = jt.getSelectedRow();
            GestionValise gv = new GestionValise(this, true, (String)jt.getValueAt(selRow, 0));
            gv.setVisible(true);
        }
    }//GEN-LAST:event_FlightTableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientBagages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientBagages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientBagages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientBagages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientBagages().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CurrentUserLabel;
    private javax.swing.JTable FlightTable;
    private javax.swing.JScrollPane FlightTableScrollPane;
    private javax.swing.JLabel FlightsListLabel;
    // End of variables declaration//GEN-END:variables
}
