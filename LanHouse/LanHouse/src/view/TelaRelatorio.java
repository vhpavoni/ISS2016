package view;

import Controle.GerenciarRelatorio;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TelaRelatorio extends JFrame{
    String nomeArquivo;
    
    public TelaRelatorio() {
        initComponents();
        grupoFrequencia.add(radioAnual);
        grupoFrequencia.add(radioDiario);
        grupoFrequencia.add(radioMensal);
        radioDiario.setSelected(true);
        textArea.setEditable(false);
        gerarPDFbutton.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoFrequencia = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextPane();
        comboTipo = new javax.swing.JComboBox();
        comboMes = new javax.swing.JComboBox();
        comboAno = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        radioDiario = new javax.swing.JRadioButton();
        radioMensal = new javax.swing.JRadioButton();
        radioAnual = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        comboDia = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        gerarPDFbutton = new javax.swing.JButton();
        Buttongerar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(650, 590));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatório"));
        jScrollPane2.setViewportView(textArea);

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Produtos cadastrados", "Vendas realizadas", "Aniversariantes", "Clientes cadastrados",
            "Funcionarios cadastrados", "Uso de máquinas", "Ordem de serviço", "Lucros"}));
comboTipo.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboTipoActionPerformed(evt);
    }
    });
    comboTipo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            comboTipoPropertyChange(evt);
        }
    });

    comboMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));

comboAno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013" }));

jLabel1.setText("Tipo:");

jLabel2.setText("Mês:");

jLabel3.setText("Ano:");

radioDiario.setText("Diário");
radioDiario.addChangeListener(new javax.swing.event.ChangeListener() {
public void stateChanged(javax.swing.event.ChangeEvent evt) {
    radioDiarioStateChanged(evt);
    }
    });

    radioMensal.setText("Mensal");
    radioMensal.addChangeListener(new javax.swing.event.ChangeListener() {
        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            radioMensalStateChanged(evt);
        }
    });

    radioAnual.setText("Anual");
    radioAnual.addChangeListener(new javax.swing.event.ChangeListener() {
        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            radioAnualStateChanged(evt);
        }
    });

    jLabel4.setText("Dia:");

    comboDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
        "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));

jLabel5.setText("Frequencia:");

gerarPDFbutton.setText("Gerar PDF");
gerarPDFbutton.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
    gerarPDFbuttonActionPerformed(evt);
    }
    });

    Buttongerar.setText("Gerar");
    Buttongerar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ButtongerarActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(comboDia, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(radioDiario)
                                    .addGap(18, 18, 18)
                                    .addComponent(radioMensal)
                                    .addGap(18, 18, 18)
                                    .addComponent(radioAnual))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(comboAno, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Buttongerar))))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createSequentialGroup()
                    .addGap(264, 264, 264)
                    .addComponent(gerarPDFbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(radioDiario)
                .addComponent(radioAnual)
                .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(radioMensal)
                .addComponent(jLabel5))
            .addGap(17, 17, 17)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2)
                .addComponent(jLabel3)
                .addComponent(comboAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4)
                .addComponent(comboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Buttongerar))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(gerarPDFbutton)
            .addContainerGap(16, Short.MAX_VALUE))
    );

    pack();
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void radioDiarioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radioDiarioStateChanged
        if (radioDiario.isSelected()){
            comboDia.setEnabled(true);
            comboMes.setEnabled(true);
            comboAno.setEnabled(true);
        }
    }//GEN-LAST:event_radioDiarioStateChanged

    private void radioMensalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radioMensalStateChanged
        if (radioMensal.isSelected()){
            comboDia.setEnabled(false);
            comboMes.setEnabled(true);
            comboAno.setEnabled(true);
        }
    }//GEN-LAST:event_radioMensalStateChanged

    private void radioAnualStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radioAnualStateChanged
        if (radioAnual.isSelected()){
            comboDia.setEnabled(false);
            comboMes.setEnabled(false);
            comboAno.setEnabled(true);
        }
    }//GEN-LAST:event_radioAnualStateChanged

    private void gerarPDFbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerarPDFbuttonActionPerformed
        Date d = new Date();
        nomeArquivo += "_"+d.getDate()+"-"+(d.getMonth()+1)+"-"+(d.getYear()+1900);
        GerenciarRelatorio.GerarPDF(nomeArquivo, textArea.getText());
    }//GEN-LAST:event_gerarPDFbuttonActionPerformed

    private void ButtongerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtongerarActionPerformed
        nomeArquivo = (String)comboTipo.getSelectedItem();
        String relatorio = "";
        switch ((String)comboTipo.getSelectedItem()){
            case "Produtos cadastrados":
                if (radioDiario.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioProdutos((String)comboDia.getSelectedItem(),
                                      (String)comboMes.getSelectedItem(), (String)comboAno.getSelectedItem());
                if (radioMensal.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioProdutos(null,(String)comboMes.getSelectedItem(),
                                                                             (String)comboAno.getSelectedItem());
                if (radioAnual.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioProdutos(null , null, (String
                                                                        )comboAno.getSelectedItem());
            break;
                
            case "Vendas realizadas":
                if (radioDiario.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioVendas((String)comboDia.getSelectedItem(),
                                      (String)comboMes.getSelectedItem(), (String)comboAno.getSelectedItem());
                if (radioMensal.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioVendas(null,(String)comboMes.getSelectedItem(),
                                                                             (String)comboAno.getSelectedItem());
                if (radioAnual.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioVendas(null , null, (String
                                                                        )comboAno.getSelectedItem());
            break;
                
            case "Aniversariantes":
                if (radioDiario.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioAniversariantes((String)comboDia.getSelectedItem(),
                                      (String)comboMes.getSelectedItem(), (String)comboAno.getSelectedItem());
                if (radioMensal.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioAniversariantes(null,(String)comboMes.getSelectedItem(),
                                                                             (String)comboAno.getSelectedItem());
                if (radioAnual.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioAniversariantes(null , null, (String
                                                                        )comboAno.getSelectedItem());
            break;
                
            case "Clientes cadastrados":
                relatorio = GerenciarRelatorio.gerarRelatorioClientes();
            break;
                
            case "Funcionarios cadastrados":
                relatorio = GerenciarRelatorio.gerarRelatorioFuncionarios();
            break;
                
            case "Uso de máquinas":
                if (radioDiario.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioUsoMaquina((String)comboDia.getSelectedItem(),
                                      (String)comboMes.getSelectedItem(), (String)comboAno.getSelectedItem());
                if (radioMensal.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioUsoMaquina(null,(String)comboMes.getSelectedItem(),
                                                                             (String)comboAno.getSelectedItem());
                if (radioAnual.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioUsoMaquina(null , null, (String
                                                                        )comboAno.getSelectedItem());
            break;
                
            case "Ordem de serviço":
                if (radioDiario.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioOrdemServico((String)comboDia.getSelectedItem(),
                                      (String)comboMes.getSelectedItem(), (String)comboAno.getSelectedItem());
                if (radioMensal.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioOrdemServico(null,(String)comboMes.getSelectedItem(),
                                                                             (String)comboAno.getSelectedItem());
                if (radioAnual.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioOrdemServico(null , null, (String
                                                                        )comboAno.getSelectedItem());
            break;
                
            case "Lucros":
                if (radioDiario.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioLucro((String)comboDia.getSelectedItem(),
                                      (String)comboMes.getSelectedItem(), (String)comboAno.getSelectedItem());
                if (radioMensal.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioLucro(null,(String)comboMes.getSelectedItem(),
                                                                             (String)comboAno.getSelectedItem());
                if (radioAnual.isSelected())
                    relatorio = GerenciarRelatorio.gerarRelatorioLucro(null , null, (String
                                                                        )comboAno.getSelectedItem());
            break;
        }        
        textArea.setText(relatorio);
        if (!textArea.getText().trim().equals("")){
            gerarPDFbutton.setEnabled(true);
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum dados gerados! dados não encontrados");
            gerarPDFbutton.setEnabled(false);
        }
    }//GEN-LAST:event_ButtongerarActionPerformed

    private void comboTipoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_comboTipoPropertyChange

    }//GEN-LAST:event_comboTipoPropertyChange

    private void comboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoActionPerformed
        comboDia.setEnabled(true);
        comboMes.setEnabled(true);
        comboAno.setEnabled(true);
        radioDiario.setEnabled(true);
        radioMensal.setEnabled(true);
        radioAnual.setEnabled(true);        
        switch ((String)comboTipo.getSelectedItem()){
            case "Clientes cadastrados":
                radioDiario.setEnabled(false);
                radioMensal.setEnabled(false);
                radioAnual.setEnabled(false);
                comboDia.setEnabled(false);
                comboMes.setEnabled(false);
                comboAno.setEnabled(false);
                break;
            case "Funcionarios cadastrados":
                radioDiario.setEnabled(false);
                radioMensal.setEnabled(false);
                radioAnual.setEnabled(false);
                comboDia.setEnabled(false);
                comboMes.setEnabled(false);
                comboAno.setEnabled(false);
                break;
        }
    }//GEN-LAST:event_comboTipoActionPerformed

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
            java.util.logging.Logger.getLogger(TelaRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRelatorio().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buttongerar;
    private javax.swing.JComboBox comboAno;
    private javax.swing.JComboBox comboDia;
    private javax.swing.JComboBox comboMes;
    private javax.swing.JComboBox comboTipo;
    private javax.swing.JButton gerarPDFbutton;
    private javax.swing.ButtonGroup grupoFrequencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton radioAnual;
    private javax.swing.JRadioButton radioDiario;
    private javax.swing.JRadioButton radioMensal;
    private javax.swing.JTextPane textArea;
    // End of variables declaration//GEN-END:variables
}
