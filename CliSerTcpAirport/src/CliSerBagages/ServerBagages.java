/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CliSerBagages;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import serveurpoolthreads.ConsoleServeur;
import serveurpoolthreads.ListeTaches;
import serveurpoolthreads.ThreadServeur;

/**
 *
 * @author Rémy
 */
public class ServerBagages extends javax.swing.JFrame implements ConsoleServeur {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy(hh:mm) : ");
    
    private ThreadServeur threadServ;
    
    public ServerBagages() {
        initComponents();
        setTitle("Interface serveur");
        setLocationRelativeTo(null);
    }

    @Override
    public void TraceEvenements(String message) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        LogsArea.insert(sdf.format(ts)+message, LogsArea.getCaretPosition());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrinc = new javax.swing.JPanel();
        StartServerButton = new javax.swing.JButton();
        ScrollPaneLogServer = new javax.swing.JScrollPane();
        LogsArea = new javax.swing.JTextArea();
        LabelLogs = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelPrinc.setName("Interface serveur"); // NOI18N

        StartServerButton.setText("Démarrer serveur");
        StartServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartServerButtonActionPerformed(evt);
            }
        });

        LogsArea.setColumns(20);
        LogsArea.setRows(5);
        ScrollPaneLogServer.setViewportView(LogsArea);

        LabelLogs.setText("Fenêtre de log :");

        javax.swing.GroupLayout PanelPrincLayout = new javax.swing.GroupLayout(PanelPrinc);
        PanelPrinc.setLayout(PanelPrincLayout);
        PanelPrincLayout.setHorizontalGroup(
            PanelPrincLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(PanelPrincLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelLogs)
                    .addGroup(PanelPrincLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ScrollPaneLogServer, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincLayout.createSequentialGroup()
                            .addComponent(StartServerButton)
                            .addGap(242, 242, 242))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        PanelPrincLayout.setVerticalGroup(
            PanelPrincLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(StartServerButton)
                .addGap(37, 37, 37)
                .addComponent(LabelLogs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPaneLogServer, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelPrinc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelPrinc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartServerButtonActionPerformed
        TraceEvenements("Nombre threads : " + Thread.activeCount()+"\n");
        TraceEvenements("Démarrage serveur demandé\n");
        threadServ = new ThreadServeur(3580,new ListeTaches(),this);
        threadServ.start();
        TraceEvenements("Nombre threads : " + Thread.activeCount()+"\n");
    }//GEN-LAST:event_StartServerButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ServerBagages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerBagages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerBagages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerBagages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerBagages().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelLogs;
    private javax.swing.JTextArea LogsArea;
    private javax.swing.JPanel PanelPrinc;
    private javax.swing.JScrollPane ScrollPaneLogServer;
    private javax.swing.JButton StartServerButton;
    // End of variables declaration//GEN-END:variables
}
