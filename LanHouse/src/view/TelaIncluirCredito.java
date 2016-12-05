package view;

import Controle.GerenciarCliente;
import Controle.GerenciarCredito;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import modelo.Cliente;

public class TelaIncluirCredito extends javax.swing.JFrame {
    
    Inicial inicial;
    Cliente cliente;
    TelaUsoMaquina uso;
    MaskFormatter mTempo = new MaskFormatter();
    MaskFormatter mReais = new MaskFormatter();
    int cont = 0;
    int cont2 = 0;
    
    public TelaIncluirCredito(Cliente cliente, Inicial inicial, TelaUsoMaquina uso) {
        this.inicial = inicial;
        this.cliente = cliente;
        this.uso=uso;
        try {
            mTempo.setMask("##:##");
            mTempo.setPlaceholderCharacter('0');
            mReais.setMask("###.##");
            mReais.setPlaceholderCharacter('0');
        } catch (ParseException ex) {
            
        }
        initComponents();
        this.setLocationRelativeTo(null);
        nome.setText(cliente.getNome());
        nome.setEnabled(false);
        credito.setFocusable(true);
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        credito = new JFormattedTextField(mTempo);
        reais = new JFormattedTextField(mReais);
        cancelar = new javax.swing.JButton();
        incluir = new javax.swing.JButton();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Incluir Credito");

        jLabel1.setText("Crédito:");

        jLabel2.setText("R$:");

        credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditoActionPerformed(evt);
            }
        });
        credito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                creditoKeyPressed(evt);
            }
        });

        reais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reaisKeyPressed(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        incluir.setText("Incluir");
        incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(incluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(credito, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reais, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(nome))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(credito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(reais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelar)
                    .addComponent(incluir))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reaisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reaisKeyPressed
        Character num1, num2, num3;
        if (evt.getKeyCode() >= KeyEvent.VK_0 && evt.getKeyCode() <= KeyEvent.VK_9 && cont2 < 4){
            Character troca = evt.getKeyChar();
            switch(cont2){
                case 0:
                   reais.setText("000.0"+troca);
                   break;
                case 1:
                    num1 = reais.getText().charAt(5);
                    reais.setText("000."+num1+""+troca);
                    break;
                case 2:
                    num1 = reais.getText().charAt(4);
                    num2 = reais.getText().charAt(5);
                    reais.setText("00"+num1+"."+num2+""+troca);
                    break;
                case 3:
                    num1 = reais.getText().charAt(2);
                    num2 = reais.getText().charAt(4);
                    num3 = reais.getText().charAt(5);
                    reais.setText("0"+num1+""+num2+"."+num3+""+troca);
                    break;
            }
            cont2++;
            credito.setText(GerenciarCredito.calcularHora(Float.parseFloat(reais.getText())));
            
            
        }
        if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE && cont2 > 0){
            cont2 = 0;
            cont = 0;
            reais.setText("000.00");
            credito.setText("00:00");
        }
        if (cont2 >= 3){
            String backup = reais.getText();
            reais.setText(backup);
        }

    }//GEN-LAST:event_reaisKeyPressed

    private void creditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_creditoKeyPressed
        Character num1, num2, num3;
        if (evt.getKeyCode() >= KeyEvent.VK_0 && evt.getKeyCode() <= KeyEvent.VK_9 && cont < 4){
            Character troca = evt.getKeyChar();
            switch(cont){
                case 0:
                credito.setText("00:0"+troca);
                break;
                    
                case 1:
                num1 = credito.getText().charAt(4);
                credito.setText("00:"+num1+""+troca);
                break;
                    
                case 2:
                num1 = credito.getText().charAt(3);
                num2 = credito.getText().charAt(4);
                credito.setText("0"+num1+":"+num2+""+troca);
                break;
                    
                case 3:
                num1 = credito.getText().charAt(1);
                num2 = credito.getText().charAt(3);
                num3 = credito.getText().charAt(4);
                credito.setText(num1+""+num2+":"+num3+""+troca);
                break;
            }
            cont++;
            Date data = null;
            DateFormat sdf = new SimpleDateFormat("HH:mm");
            try {
                data = (Date)sdf.parse(credito.getText());
            } catch (ParseException ex) { }
            String rs = GerenciarCredito.calcularPreco(data).replace(",", ".");
            reais.setText(rs);
                
        }
        if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE && cont > 0){
            cont = 0;
            cont2 = 0;
            credito.setText("00:00");
            reais.setText("000.00");
        }
        if (cont >= 3){
            String backup = credito.getText();
            credito.setText(backup);
        }
        //System.out.println(cont);
    }//GEN-LAST:event_creditoKeyPressed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void incluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incluirActionPerformed
        if (!credito.getText().equals("00:00")){
            if (GerenciarCliente.incluirCredito(cliente, Float.parseFloat(reais.getText()))){
                JOptionPane.showMessageDialog(null, "Credito incluido com sucesso!");
                this.dispose();
                if(inicial.getValor()!=4){
                    inicial.preencheTabelaCliente();
                }else{
                    inicial.preencheTabelaUsoMaquina();
                    if(uso!=null){
                        uso.preencheTabelaCliente();
                    }
                }
                
            }
        }else {
            JOptionPane.showMessageDialog(null, "Insira o valor do crédito");
        }
    }//GEN-LAST:event_incluirActionPerformed

    private void creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creditoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(final Cliente cliente, final Inicial inicial, final TelaUsoMaquina uso) {
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
            java.util.logging.Logger.getLogger(TelaIncluirCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaIncluirCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaIncluirCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaIncluirCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaIncluirCredito(cliente, inicial, uso).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JFormattedTextField credito;
    private javax.swing.JButton incluir;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField nome;
    private javax.swing.JFormattedTextField reais;
    // End of variables declaration//GEN-END:variables
}
