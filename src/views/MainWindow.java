
package views;

import controllers.PollutionCounter;
import controllers.SyntecticGenerator;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class MainWindow extends javax.swing.JFrame {
    
    private String resultExp = "";

    private final Dimension desktopDimension;
    
    
    public MainWindow() {
        initComponents();
        desktopDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();        
        runExpButton.setAction(new ShowLoadingExperiment("Run Experiment"));
        jbRunExpPollution.setAction(new ShowLoadingPollution("Run Experiment"));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        traceDialog = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jcbNoiseLevel = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jftTraceSize = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        lru1RB = new javax.swing.JRadioButton();
        lfu1RB = new javax.swing.JRadioButton();
        arc1RB = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        lru2RB = new javax.swing.JRadioButton();
        lfu2RB = new javax.swing.JRadioButton();
        arc2RB = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jcbFirstStarts = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jcbCacheSize = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jcbMaxReqSize = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jcbNumChanges = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jtfDescription = new javax.swing.JTextField();
        runExpButton = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jtfFilename = new javax.swing.JTextField();
        fileDialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfAddress = new javax.swing.JTextField();
        jbFind = new javax.swing.JButton();
        jbRunExpPollution = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jftCacheSize = new javax.swing.JFormattedTextField();
        fileChooser = new javax.swing.JFileChooser();
        bGroupFirstExp = new javax.swing.ButtonGroup();
        bgGroupSecondExp = new javax.swing.ButtonGroup();
        runningGifDialog = new javax.swing.JDialog();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        labelGif = new javax.swing.JLabel();
        aboutDialog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        traceDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        traceDialog.setTitle("Synthetic Trace Generator");
        traceDialog.setResizable(false);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Experiment Configuration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel7.setText("Noise Level: ");

        jcbNoiseLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "5", "10" }));

        jLabel8.setText("Trace Size: ");

        jftTraceSize.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jcbNoiseLevel, 0, 119, Short.MAX_VALUE)
                    .addComponent(jftTraceSize))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jcbNoiseLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jftTraceSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "First Expert", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        bGroupFirstExp.add(lru1RB);
        lru1RB.setText("LRU");
        lru1RB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lru1RBStateChanged(evt);
            }
        });

        bGroupFirstExp.add(lfu1RB);
        lfu1RB.setText("LFU");
        lfu1RB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lfu1RBStateChanged(evt);
            }
        });

        bGroupFirstExp.add(arc1RB);
        arc1RB.setText("ARC");
        arc1RB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                arc1RBStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lru1RB)
                    .addComponent(lfu1RB)
                    .addComponent(arc1RB))
                .addGap(38, 38, 38))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lru1RB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lfu1RB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(arc1RB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Second Expert", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        bgGroupSecondExp.add(lru2RB);
        lru2RB.setText("LRU");
        lru2RB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lru2RBStateChanged(evt);
            }
        });

        bgGroupSecondExp.add(lfu2RB);
        lfu2RB.setText("LFU");
        lfu2RB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lfu2RBStateChanged(evt);
            }
        });

        bgGroupSecondExp.add(arc2RB);
        arc2RB.setText("ARC");
        arc2RB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                arc2RBStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lru2RB)
                    .addComponent(lfu2RB)
                    .addComponent(arc2RB))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lru2RB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lfu2RB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(arc2RB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Experts Configuration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jcbFirstStarts.setText("   First Expert Starts");

        jLabel4.setText("Cache Size: ");

        jcbCacheSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "50", "100", "500", "1000", "5000" }));
        jcbCacheSize.setSelectedIndex(3);

        jLabel5.setText("Max Req Size: ");

        jcbMaxReqSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "100x", "50x", "10x", "5x" }));

        jLabel6.setText("No. Changes:");

        jcbNumChanges.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "4", "6", "8", "10", "20", "50", "100" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbFirstStarts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jcbMaxReqSize, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbCacheSize, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbNumChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jcbFirstStarts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcbCacheSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcbMaxReqSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcbNumChanges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Description", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtfDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jtfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        runExpButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        runExpButton.setText("Run Experiment");

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "File Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel9.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtfFilename)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jtfFilename, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout traceDialogLayout = new javax.swing.GroupLayout(traceDialog.getContentPane());
        traceDialog.getContentPane().setLayout(traceDialogLayout);
        traceDialogLayout.setHorizontalGroup(
            traceDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(traceDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(traceDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(traceDialogLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(traceDialogLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(traceDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(traceDialogLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(traceDialogLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(runExpButton)))))
                .addContainerGap())
            .addGroup(traceDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(traceDialogLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        traceDialogLayout.setVerticalGroup(
            traceDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(traceDialogLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(traceDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(traceDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(traceDialogLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(runExpButton))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(traceDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(traceDialogLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(259, Short.MAX_VALUE)))
        );

        fileDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        fileDialog.setTitle("Pollution Counter");
        fileDialog.setResizable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Select Pollution Counter File:");

        jbFind.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbFind.setText("Find File");
        jbFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFindActionPerformed(evt);
            }
        });

        jbRunExpPollution.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbRunExpPollution.setText("Finish");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Cache Size: ");

        jftCacheSize.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jftCacheSize, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbRunExpPollution, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addComponent(jbFind, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbFind))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jftCacheSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbRunExpPollution))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout fileDialogLayout = new javax.swing.GroupLayout(fileDialog.getContentPane());
        fileDialog.getContentPane().setLayout(fileDialogLayout);
        fileDialogLayout.setHorizontalGroup(
            fileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        fileDialogLayout.setVerticalGroup(
            fileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        runningGifDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        runningGifDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        runningGifDialog.setUndecorated(true);

        jLayeredPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(19, 22, 142));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("EXPERIMENT RUNNING. PLEASE WAIT...");
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.MODAL_LAYER);
        jLayeredPane1.add(jLabel3);
        jLabel3.setBounds(0, 0, 400, 20);

        labelGif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/loading 2.gif"))); // NOI18N
        jLayeredPane1.setLayer(labelGif, javax.swing.JLayeredPane.MODAL_LAYER);
        jLayeredPane1.add(labelGif);
        labelGif.setBounds(-70, 0, 550, 320);

        javax.swing.GroupLayout runningGifDialogLayout = new javax.swing.GroupLayout(runningGifDialog.getContentPane());
        runningGifDialog.getContentPane().setLayout(runningGifDialogLayout);
        runningGifDialogLayout.setHorizontalGroup(
            runningGifDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        runningGifDialogLayout.setVerticalGroup(
            runningGifDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout aboutDialogLayout = new javax.swing.GroupLayout(aboutDialog.getContentPane());
        aboutDialog.getContentPane().setLayout(aboutDialogLayout);
        aboutDialogLayout.setHorizontalGroup(
            aboutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );
        aboutDialogLayout.setVerticalGroup(
            aboutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Experiment Helper");
        setResizable(false);

        jPanel1.setLayout(new java.awt.GridLayout(3, 1, 0, 40));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Trace Generator");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("Pollution Counter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setText("About");
        jPanel1.add(jButton4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        initTraceDialog();
        traceDialog.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        initFileDialog();
        fileDialog.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jbFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFindActionPerformed
        initFileChooser();
        int result = fileChooser.showOpenDialog(fileDialog);
        if(result == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            jtfAddress.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_jbFindActionPerformed

    private void lru1RBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lru1RBStateChanged
        if(lru1RB.isSelected()){
            lru2RB.setEnabled(false);
        }else{
            lru2RB.setEnabled(true);
        }
    }//GEN-LAST:event_lru1RBStateChanged

    private void lfu1RBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lfu1RBStateChanged
        if(lfu1RB.isSelected()){
            lfu2RB.setEnabled(false);
        }else{
            lfu2RB.setEnabled(true);
        }
    }//GEN-LAST:event_lfu1RBStateChanged

    private void arc1RBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_arc1RBStateChanged
        if(arc1RB.isSelected()){
            arc2RB.setEnabled(false);
        }else{
            arc2RB.setEnabled(true);
        }
    }//GEN-LAST:event_arc1RBStateChanged

    private void lru2RBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lru2RBStateChanged
        if(lru2RB.isSelected()){
            lru1RB.setEnabled(false);
        }else{
            lru1RB.setEnabled(true);
        }
    }//GEN-LAST:event_lru2RBStateChanged

    private void lfu2RBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lfu2RBStateChanged
        if(lfu2RB.isSelected()){
            lfu1RB.setEnabled(false);
        }else{
            lfu1RB.setEnabled(true);
        }
    }//GEN-LAST:event_lfu2RBStateChanged

    private void arc2RBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_arc2RBStateChanged
        if(arc2RB.isSelected()){
            arc1RB.setEnabled(false);
        }else{
            arc1RB.setEnabled(true);
        }
    }//GEN-LAST:event_arc2RBStateChanged

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
                    //javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog aboutDialog;
    private javax.swing.JRadioButton arc1RB;
    private javax.swing.JRadioButton arc2RB;
    private javax.swing.ButtonGroup bGroupFirstExp;
    private javax.swing.ButtonGroup bgGroupSecondExp;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JDialog fileDialog;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbFind;
    private javax.swing.JButton jbRunExpPollution;
    private javax.swing.JComboBox<String> jcbCacheSize;
    private javax.swing.JCheckBox jcbFirstStarts;
    private javax.swing.JComboBox<String> jcbMaxReqSize;
    private javax.swing.JComboBox<String> jcbNoiseLevel;
    private javax.swing.JComboBox<String> jcbNumChanges;
    private javax.swing.JFormattedTextField jftCacheSize;
    private javax.swing.JFormattedTextField jftTraceSize;
    private javax.swing.JTextField jtfAddress;
    private javax.swing.JTextField jtfDescription;
    private javax.swing.JTextField jtfFilename;
    private javax.swing.JLabel labelGif;
    private javax.swing.JRadioButton lfu1RB;
    private javax.swing.JRadioButton lfu2RB;
    private javax.swing.JRadioButton lru1RB;
    private javax.swing.JRadioButton lru2RB;
    private javax.swing.JButton runExpButton;
    private javax.swing.JDialog runningGifDialog;
    private javax.swing.JDialog traceDialog;
    // End of variables declaration//GEN-END:variables

       
    private void initLoadingGif(){
        int x = 400;
        int y = 300;
        runningGifDialog.setLocation(desktopDimension.width/2 - x/2, desktopDimension.height/2 -y/2);
        runningGifDialog.setSize(x,y);
    }
    
    private void initTraceDialog() {
        int x = 520;
        int y = 390;
        traceDialog.setLocation(desktopDimension.width/2 - x/2, desktopDimension.height/2 -y/2);
        traceDialog.setSize(x,y);
        
        jtfDescription.setText("");
        bGroupFirstExp.clearSelection();
        bgGroupSecondExp.clearSelection();
        jcbFirstStarts.setSelected(false);
        jcbCacheSize.setSelectedIndex(3);
        jcbMaxReqSize.setSelectedIndex(0);
        jcbNumChanges.setSelectedIndex(0);
        jcbNoiseLevel.setSelectedIndex(0);
        jftTraceSize.setText("");
        jtfFilename.setText("");
    }
    
    private void initFileDialog() {
        int x = 500;
        int y = 210;
        jtfAddress.setText("");
        fileDialog.setLocation(desktopDimension.width/2 - x/2, desktopDimension.height/2 -y/2);
        fileDialog.setSize(x,y);
        jtfAddress.setText("");
    }

    private void initFileChooser() {
        int x = 640;
        int y = 480;
        fileChooser.setLocation(desktopDimension.width/2 - x/2, desktopDimension.height/2 -y/2);
        fileChooser.setSize(x,y);
    }

    private void exportConfigFile() throws FileNotFoundException {
                
        String filename = jtfFilename.getText();        
            
        //DESCRIPTION
        String description = jtfDescription.getText();

        //FIRST_EXPERT
        int first_expert;
        if(lru1RB.isSelected()){
            first_expert = 1;
        }else if(lfu1RB.isSelected()){
            first_expert = 2;
        }else{
            first_expert = 3;
        }            

        //SECOND_EXPERT
        int second_expert;
        if(lru2RB.isSelected()){
            second_expert = 1;
        }else if(lfu2RB.isSelected()){
            second_expert = 2;
        }else{
            second_expert = 3;
        }

        //CACHE_SIZE
        String cache_size = jcbCacheSize.getSelectedItem().toString();

        //NUM_CHANGES
        String num_changes = jcbNumChanges.getSelectedItem().toString();

        //MAX_REQUEST_SIZE
        String req_size = Integer.parseInt(cache_size) * Integer.parseInt(jcbMaxReqSize.getSelectedItem().toString().substring(0, jcbMaxReqSize.getSelectedItem().toString().length() - 1)) +"";

        //FIRST_EXPERT_STARTS
        String first_starts = jcbFirstStarts.isSelected() ? "Y" : "N";

        //LEVEL_NOISE
        String noise_level = jcbNoiseLevel.getSelectedItem().toString();

        //NEEDED_SEQ
        String trace_size = jftTraceSize.getText();

        try (PrintWriter out = new PrintWriter("Input\\"+filename+".txt")) {    
            //WRITE TO FILE
            out.println("DESCRIPTION:"+description);
            out.println("FIRST_EXPERT:"+first_expert);
            out.println("SECOND_EXPERT:"+second_expert);
            out.println("CACHE_SIZE:"+cache_size);
            out.println("NUM_CHANGES:"+num_changes);
            out.println("MAX_REQUEST_SIZE:"+req_size);
            out.println("FIRST_EXPERT_STARTS:"+first_starts);
            out.println("LEVEL_NOISE:"+noise_level);
            out.println("NEEDED_SEQ:"+trace_size);                     
        }
        
    }

    private String runExperiment() {
        try {
            
            exportConfigFile();
            SyntecticGenerator st = new SyntecticGenerator(jtfFilename.getText());
            st.run();
            
            if (st.result.equals("The Experiment Concluded Successfully")){
                return st.result + "\nExpert 1 had " + st.hits1 + " hits.\nExpert 2 had " + st.hits2 + " hits.";
            }else{
                return st.result;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot create file. Wrong address.");
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return "The experiment failed while cretaing the file";
        }
    }

    private String runPollution() {
        try {
            int cacheSize = Integer.parseInt(jftCacheSize.getText());
            PollutionCounter pc = new PollutionCounter(jtfAddress.getText(), cacheSize);
            pc.run();
            
            return "Pollution Counter finished running succesfully";
            
        } catch(Exception e){
            System.err.println(e.getMessage());
            return "The Pollution Counter failed to open the file";
        }
    }
    
    private boolean isValidExperiment() {
            
        String description = jtfDescription.getText();        
        if(description.equals("")){            
            JOptionPane.showMessageDialog(traceDialog, "Please enter Description of the configuration file.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;             
        }

        if(!lru1RB.isSelected() && !lfu1RB.isSelected() && !arc1RB.isSelected()){
            JOptionPane.showMessageDialog(traceDialog, "Please select the First Expert.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }  

        if(!lru2RB.isSelected() && !lfu2RB.isSelected() && !arc2RB.isSelected()){
            JOptionPane.showMessageDialog(traceDialog, "Please select the Second Expert.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }  

        String trace_size = jftTraceSize.getText();
        if (trace_size.equals("")){
            JOptionPane.showMessageDialog(traceDialog, "Please enter a numeric value for Trace Size.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String filename = jtfFilename.getText();        
        if(filename.equals("")){            
            JOptionPane.showMessageDialog(traceDialog, "Please enter Filename of the configuration file.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;             
        }

        return true;
    }
    
    private boolean isValidPollution(){
        return !jftCacheSize.getText().equals("") && !jtfAddress.getText().equals("");
    }
    
    class ShowLoadingExperiment extends AbstractAction{
        
        public ShowLoadingExperiment(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            
            if(isValidExperiment()){
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
                    String results = "";
                    @Override
                    protected Void doInBackground() throws Exception {
                        System.out.println("About to start experiment..." );                        
                        resultExp = runExperiment();
                        return null;
                    }
                };

                worker.addPropertyChangeListener((PropertyChangeEvent evt1) -> {
                    if (evt1.getPropertyName().equals("state")) {
                        if (evt1.getNewValue() == SwingWorker.StateValue.DONE) {
                            System.out.println("Experiment ended.");
                            runningGifDialog.dispose();
                            traceDialog.dispose();                      
                            JOptionPane.showMessageDialog(traceDialog, resultExp , "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                
                worker.execute();
                initLoadingGif();
                runningGifDialog.setVisible(true);                
            }
        }        
    }
    
    class ShowLoadingPollution extends AbstractAction{
        
        public ShowLoadingPollution(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if(isValidPollution()){
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
                    String results = "";
                    @Override
                    protected Void doInBackground() throws Exception {
                        System.out.println("About to start experiment..." );                        
                        resultExp = runPollution();
                        return null;
                    }
                };

                worker.addPropertyChangeListener((PropertyChangeEvent evt1) -> {
                    if (evt1.getPropertyName().equals("state")) {
                        if (evt1.getNewValue() == SwingWorker.StateValue.DONE) {
                            System.out.println("Experiment ended.");
                            runningGifDialog.dispose();
                            fileDialog.dispose();                      
                            JOptionPane.showMessageDialog(fileDialog, resultExp , "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });

                worker.execute();
                initLoadingGif();
                runningGifDialog.setVisible(true);   
                
            }else{
                JOptionPane.showMessageDialog(fileDialog, "Please enter valid address and numeric value for cache size" , "Information", JOptionPane.ERROR_MESSAGE);
            }
        }        
    }
}
