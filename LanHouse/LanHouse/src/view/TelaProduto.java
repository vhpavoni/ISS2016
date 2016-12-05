package view;

import Controle.GerenciarProduto;
import java.util.ArrayList;
import modelo.Produto;
import javax.swing.JOptionPane;

public class TelaProduto extends javax.swing.JFrame {

    Produto produto;
    Inicial janelaPai;
    
    public TelaProduto(Inicial janela, Produto produto) {
        initComponents();
        this.setLocationRelativeTo(null);
        
        this.produto = produto;
        this.janelaPai = janela;
        if (produto != null){
            campoNome.setText(produto.getCategoria());
            campoMarca.setText(produto.getMarca());
            campoModelo.setText(produto.getModelo());
            campoDesc.setText(produto.getDescricao());
            campoPreco.setText(String.valueOf(produto.getPreco()));
            campoQtde.setText(String.valueOf(produto.getEstoque()));
            campoQtde.setEnabled(false);
            botaoIncluir.setVisible(true);
        } else {
            botaoIncluir.setVisible(false);
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

        jLabel1 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoMarca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        campoPreco = new javax.swing.JTextField();
        campoQtde = new javax.swing.JTextField();
        botaoIncluir = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();
        campoModelo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        campoDesc = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro - Produto");

        jLabel1.setText("Nome:");

        jLabel2.setText("Marca:");

        jLabel3.setText("Preço:(R$)");

        jLabel4.setText("Quantidade:");

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        campoPreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoPrecoKeyPressed(evt);
            }
        });

        botaoIncluir.setText("Incluir");
        botaoIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIncluirActionPerformed(evt);
            }
        });

        jLabel5.setText("Modelo:");

        jLabel6.setText("Descrição:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(37, 37, 37)
                        .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)
                        .addComponent(campoMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(30, 30, 30)
                        .addComponent(campoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(campoDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(campoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(10, 10, 10)
                        .addComponent(campoQtde, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoIncluir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(92, 92, 92)
                        .addComponent(jButton2)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(campoMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(campoModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addComponent(campoDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(campoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(campoQtde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botaoIncluir))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String msg = "";
        if (campoNome.getText().trim().equals("")){
            msg += "Campo [Nome] Obrigatório\n";
        }
        if (campoMarca.getText().trim().equals("")){
            msg += "Campo [Descrição] Obrigatório\n";
        }
        if (campoModelo.getText().trim().equals("")){
            msg += "Campo [Marca] Obrigatório\n";
        }
        if (campoDesc.getText().trim().equals("")){
            msg += "Campo [Modelo] Obrigatório\n";
        }        
        if (campoPreco.getText().trim().equals("")){
            msg += "Campo [Preço] Obrigatório\n";
        } else {
            try{
                Float.parseFloat(campoPreco.getText().trim());
            }catch (Exception e){
                msg += "Campo [Preço] Formato Inválido\n";
            }
        }
        if (campoQtde.getText().trim().equals("")){
            msg += "Campo [Quantidade] Obrigatório\n";
        } else {
            try{
                Integer.parseInt(campoQtde.getText().trim());
            }catch (Exception e){
                msg += "Campo [Quantidade] Formato Inválido\n";
            }
        }
        
        if(!msg.equals("")){
            JOptionPane.showMessageDialog(this, msg);
        } else {
       
            
            String retorno;
            ArrayList lista = new ArrayList();
            if (!botaoIncluir.isVisible()){
                lista.add(null);
                lista.add(campoNome.getText());
                lista.add(campoMarca.getText());
                lista.add(campoModelo.getText());
                lista.add(campoDesc.getText());
                lista.add(campoQtde.getText());
                lista.add(campoPreco.getText());
                
                retorno = GerenciarProduto.verificarDuplicidade(lista);
            } else {
                lista.add(produto.getCod());
                lista.add(campoNome.getText());
                lista.add(campoMarca.getText());
                lista.add(campoModelo.getText());
                lista.add(campoDesc.getText());
                lista.add(campoQtde.getText());
                lista.add(campoPreco.getText());
                        
                retorno = GerenciarProduto.verificarDuplicidade(lista);
            }

            if (retorno != null){
                JOptionPane.showMessageDialog(this, "Produto já cadastrado! código : "+ retorno);
            } else {
                this.dispose();
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
            }
            janelaPai.preencheTabelaProd();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed
        try {
            int x = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade a inserir:"));
            if(x <= 0){
                JOptionPane.showMessageDialog(this, "Valor deve ser maior que 0");
            } else {
                campoQtde.setText(String.valueOf(Integer.parseInt(campoQtde.getText()) + x));
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Valor invalido");
        }
    }//GEN-LAST:event_botaoIncluirActionPerformed

    private void campoPrecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPrecoKeyPressed

    }//GEN-LAST:event_campoPrecoKeyPressed

    
    /**
     * @param args the command line arguments
     */
    public static void main(final Inicial frame, final Produto produto) {
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
            java.util.logging.Logger.getLogger(TelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaProduto(frame, produto).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton botaoIncluir;
    private javax.swing.JTextField campoDesc;
    private javax.swing.JTextField campoMarca;
    private javax.swing.JTextField campoModelo;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoPreco;
    private javax.swing.JTextField campoQtde;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}