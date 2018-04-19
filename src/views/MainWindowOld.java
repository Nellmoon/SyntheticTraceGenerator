/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;

/**
 *
 * @author Luis
 */
public class MainWindowOld extends javax.swing.JFrame {

    private final Dimension desktopDimension;
    private boolean firstRun = true;
    private ArrayList<javax.swing.JTextField> jtfields;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindowOld() {
        desktopDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();        
        initTextFields();
        setUpTextFieldListeners();
        showServerConnectionDialog();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogMngConnection = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jPanelServerInfo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtfServerName = new javax.swing.JTextField();
        jtfServerAddress = new javax.swing.JTextField();
        jtfServerUsername = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jPanelButtons = new javax.swing.JPanel();
        jButtonCancelCon = new javax.swing.JButton();
        jButtonAcceptCon = new javax.swing.JButton();
        jButtonTestCon = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBarApp = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemMngServer = new javax.swing.JMenuItem();
        jSeparatorExit = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuAlgorithm = new javax.swing.JMenu();
        jMenuItemCurrentAlg = new javax.swing.JMenuItem();
        jMenuItemAddAlg = new javax.swing.JMenuItem();
        jMenuTraces = new javax.swing.JMenu();
        jMenuItemCurrentTraces = new javax.swing.JMenuItem();
        jMenuItemAddTraces = new javax.swing.JMenuItem();
        jMenuStats = new javax.swing.JMenu();
        jMenuItemStats = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemHelpContent = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemAbout = new javax.swing.JMenuItem();

        jDialogMngConnection.setSize(382, 275);
        jDialogMngConnection.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialogMngConnection.setTitle("Server Connection Manager");
        jDialogMngConnection.setAlwaysOnTop(true);
        jDialogMngConnection.setResizable(false);
        jDialogMngConnection.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jDialogMngConnectionWindowClosing(evt);
            }
        });

        jLabel3.setText("Server Name: ");

        jLabel4.setText("Server Address:");

        jLabel5.setText("Server Username:");

        jLabel6.setText("Server Password:");

        javax.swing.GroupLayout jPanelServerInfoLayout = new javax.swing.GroupLayout(jPanelServerInfo);
        jPanelServerInfo.setLayout(jPanelServerInfoLayout);
        jPanelServerInfoLayout.setHorizontalGroup(
            jPanelServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServerInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(45, 45, 45)
                .addGroup(jPanelServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jtfServerAddress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(jtfServerUsername, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfServerName)
                    .addComponent(jPasswordField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelServerInfoLayout.setVerticalGroup(
            jPanelServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServerInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfServerAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtfServerUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelServerInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jButtonCancelCon.setText("Cancel");
        jButtonCancelCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelConActionPerformed(evt);
            }
        });

        jButtonAcceptCon.setText("Accept");
        jButtonAcceptCon.setEnabled(false);
        jButtonAcceptCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcceptConActionPerformed(evt);
            }
        });

        jButtonTestCon.setText("Test Connection");
        jButtonTestCon.setEnabled(false);
        jButtonTestCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTestConActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonTestCon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jButtonAcceptCon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelCon)
                .addContainerGap())
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelCon)
                    .addComponent(jButtonAcceptCon)
                    .addComponent(jButtonTestCon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelServerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelServerInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogMngConnectionLayout = new javax.swing.GroupLayout(jDialogMngConnection.getContentPane());
        jDialogMngConnection.getContentPane().setLayout(jDialogMngConnectionLayout);
        jDialogMngConnectionLayout.setHorizontalGroup(
            jDialogMngConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMngConnectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialogMngConnectionLayout.setVerticalGroup(
            jDialogMngConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMngConnectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Experiment Simulator Design");

        jLabel1.setText("Server Status: Connected");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/server_online.png"))); // NOI18N

        jMenuFile.setText("File");

        jMenuItemMngServer.setText("Manage Server Connection");
        jMenuItemMngServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMngServerActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemMngServer);
        jMenuFile.add(jSeparatorExit);

        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBarApp.add(jMenuFile);

        jMenuAlgorithm.setText("Algorithms");

        jMenuItemCurrentAlg.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCurrentAlg.setText("Current Algorithms");
        jMenuAlgorithm.add(jMenuItemCurrentAlg);

        jMenuItemAddAlg.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAddAlg.setText("Add Algorithms");
        jMenuAlgorithm.add(jMenuItemAddAlg);

        jMenuBarApp.add(jMenuAlgorithm);

        jMenuTraces.setText("Traces");

        jMenuItemCurrentTraces.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCurrentTraces.setText("Current Traces");
        jMenuTraces.add(jMenuItemCurrentTraces);

        jMenuItemAddTraces.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAddTraces.setText("Add Traces");
        jMenuTraces.add(jMenuItemAddTraces);

        jMenuBarApp.add(jMenuTraces);

        jMenuStats.setText("Statistics");

        jMenuItemStats.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemStats.setText("Current Analisys");
        jMenuStats.add(jMenuItemStats);

        jMenuBarApp.add(jMenuStats);

        jMenuHelp.setText("Help");

        jMenuItemHelpContent.setText("Help Content");
        jMenuHelp.add(jMenuItemHelpContent);
        jMenuHelp.add(jSeparator2);

        jMenuItemAbout.setText("About...");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);

        jMenuBarApp.add(jMenuHelp);

        setJMenuBar(jMenuBarApp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(687, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(579, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(855, 674));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemMngServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMngServerActionPerformed
        showServerConnectionDialog();
    }//GEN-LAST:event_jMenuItemMngServerActionPerformed

    private void jButtonCancelConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelConActionPerformed
        if(firstRun){
            System.exit(0);
        }else{
            jDialogMngConnection.dispose();
        }
    }//GEN-LAST:event_jButtonCancelConActionPerformed

    private void jButtonAcceptConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcceptConActionPerformed
        if(firstRun){
            //check and establish connection (TO BE COMPLETED)
            
            //hide dialong and display main window if successfully established the connection
            firstRun = false;
            jDialogMngConnection.dispose();
            this.setVisible(true);            
        }else{
            //check and update connection (TO BE COMPLETED)
            
            //hide dialog if successfully updated connection
            jDialogMngConnection.dispose();
        }
    }//GEN-LAST:event_jButtonAcceptConActionPerformed

    private void jButtonTestConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTestConActionPerformed
                
        //test connection (TO BE COMPLETED)
        boolean connected;
        String name = jtfServerName.getText();
        String address = jtfServerAddress.getText();
        String username = jtfServerUsername.getText();
        char[] password = jPasswordField.getPassword();
        
        //zero out password field for security reasons
        for(int i = 0; i < password.length; i++){
            password[i] = '0';
        }
        
        //set url and message variables depending on the conection status
        String url;
        if(true){
            url = "/views/images/connection_successful.png";
        }else{
            url = "/views/images/connection_unsuccessful.png";
        }
        ImageIcon icon = new ImageIcon(getClass().getResource(url));
        int message_type = (true) ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;
        String message = (true) ? "Connection Successful" : "Connection Unsuccessful";
        
        //display message
        JOptionPane.showMessageDialog(jDialogMngConnection, message, "Connection Information", message_type, icon);
    }//GEN-LAST:event_jButtonTestConActionPerformed

    private void jDialogMngConnectionWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialogMngConnectionWindowClosing
        if(firstRun){
            System.exit(0);
        }
    }//GEN-LAST:event_jDialogMngConnectionWindowClosing

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

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
                    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                    //javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindowOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindowOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindowOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindowOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindowOld().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAcceptCon;
    private javax.swing.JButton jButtonCancelCon;
    private javax.swing.JButton jButtonTestCon;
    private javax.swing.JDialog jDialogMngConnection;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenuAlgorithm;
    private javax.swing.JMenuBar jMenuBarApp;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemAddAlg;
    private javax.swing.JMenuItem jMenuItemAddTraces;
    private javax.swing.JMenuItem jMenuItemCurrentAlg;
    private javax.swing.JMenuItem jMenuItemCurrentTraces;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemHelpContent;
    private javax.swing.JMenuItem jMenuItemMngServer;
    private javax.swing.JMenuItem jMenuItemStats;
    private javax.swing.JMenu jMenuStats;
    private javax.swing.JMenu jMenuTraces;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelServerInfo;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparatorExit;
    private javax.swing.JTextField jtfServerAddress;
    private javax.swing.JTextField jtfServerName;
    private javax.swing.JTextField jtfServerUsername;
    // End of variables declaration//GEN-END:variables

    private void showServerConnectionDialog() {
        int width = jDialogMngConnection.getWidth();
        int height = jDialogMngConnection.getHeight();
        jDialogMngConnection.setLocation(desktopDimension.width/2-width/2, desktopDimension.height/2-height/2);
        jDialogMngConnection.setVisible(true);
    }

    private void initTextFields(){
        jtfields = new ArrayList<>();
        jtfields.add(jtfServerAddress);
        jtfields.add(jtfServerName);
        jtfields.add(jtfServerUsername);
    }
    
    private void setUpTextFieldListeners() {                
        jtfields.forEach((jft) -> {
            jft.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    textChanged();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    textChanged();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    textChanged();
                }
                
                public void textChanged(){
                    boolean enable = !jtfServerAddress.getText().isEmpty() && !jtfServerName.getText().isEmpty() && !jtfServerUsername.getText().isEmpty() && !(jPasswordField.getPassword().length == 0) ;
                    jButtonAcceptCon.setEnabled(enable);
                    jButtonTestCon.setEnabled(enable); 
                }
            });
        });
        
        jPasswordField.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                textChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                textChanged();
            }

            public void textChanged(){
                boolean enable = !jtfServerAddress.getText().isEmpty() && !jtfServerName.getText().isEmpty() && !jtfServerUsername.getText().isEmpty() && !(jPasswordField.getPassword().length == 0) ;
                jButtonAcceptCon.setEnabled(enable);
                jButtonTestCon.setEnabled(enable); 
            }            
        });
    }
}
