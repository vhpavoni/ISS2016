
package view;

import BancoDeDados.Persistencia;
import Controle.ColorRender;
import Controle.GerenciarCliente;
import Controle.GerenciarProduto;
import Controle.Timer;
import modelo.Cliente;
import modeloDAO.ClienteDAO;
import modelo.Funcionario;
import modeloDAO.FuncionarioDAO;
import modelo.Produto;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import modelo.Maquina;
import modelo.OrdemServico;
import modelo.UsoMaquina;
import modeloDAO.MaquinaDAO;
import modeloDAO.OrdemServicoDAO;
import modeloDAO.UsoMaquinaDAO;
import modeloDAO.ValorHoraDAO;


public class Inicial extends javax.swing.JFrame {
    int valor=0;
    private DefaultTableModel modelo = new DefaultTableModel();
    private float tempominimo;
    private static float minimo=(float) 0.25;
    Timer timer;
    private DateFormat formato;
    
    
    
    
    public Inicial() {
        initComponents();
        formato = new SimpleDateFormat("hh:mm:ss");
        tabela.setName("tabela");
        ValorHoraDAO thd= new ValorHoraDAO();
        tempominimo=(60/(thd.valorhora()/minimo));
        botaoCadastrar.setText("Liberar Uso");
        botaoRemover.setText("Encerrar Uso");
        botaoConsultar.setVisible(false);
        botaoIncluirCredito.setVisible(true);
        botaoVendaProduto.setVisible(true);
        modelo.setColumnCount(0);
        modelo.addColumn("Máquina");
        modelo.addColumn("Login");
        modelo.addColumn("Inicio");
        modelo.addColumn("Tempo");
        modelo.addColumn("Tempo Restante");
        tabela.setModel(modelo);
        preencheTabelaUsoMaquina();
        valor=4;
        this.setTitle(null);
        this.setLocationRelativeTo(null);
        timer = new Timer(1,this);
	timer.start();
    }

        public void preencheTabelaUsoMaquina(){
        List<Maquina> maquinas = Persistencia.listar(new Maquina());
        GregorianCalendar gaux;
        UsoMaquinaDAO umd=new UsoMaquinaDAO();
        tabela.setDefaultRenderer(Object.class, new ColorRender(this));
        UsoMaquina um=null;
        int i = 0;
        DefaultTableModel tableModel = (DefaultTableModel)tabela.getModel();
        tableModel.setNumRows(0);
        Object[] linha = {1};
        
        for (Maquina maquina : maquinas){
            if(!maquina.isRemovido()){
                tableModel.addRow(linha);
                tabela.setValueAt(maquina.getId(), i, 0);
                
                if(maquina.getUsado()!=0){
                    um=umd.buscarPorChave(maquina.getUsado());
                    if(um!=null){
                        tabela.setValueAt(um.getCliente().getLogin(), i, 1);
                        tabela.setValueAt(um.tempo(), i, 2);
                        gaux=new GregorianCalendar();
                        tabela.setValueAt(Inicial.segundosString(this.tempoEntreDatas(gaux,um.getInicio())),i,3);
                        tabela.setValueAt(Inicial.segundosString(this.segCliente(um.getCliente())-this.tempoEntreDatas(gaux,um.getFim())),i,4);

                    }
                }
                i++;
            }
        }
        
        if (i < 30){
            for (i=i; i<30; i++){
                tableModel.addRow(linha);
                tabela.setValueAt(null, i, 0);
            }
        }
    }
    public long segCliente(Cliente c){
       ValorHoraDAO vhd=new ValorHoraDAO();
       return (long) ((c.getCredito()/vhd.valorhora())*60*60);
    }
    public void atualizarTempo(){
         List<Maquina> maquinas = Persistencia.listar(new Maquina());
        int i=0;
        long aux;
        GregorianCalendar gaux;
        UsoMaquinaDAO umd=new UsoMaquinaDAO();
        
 // aplica o ColorRender na tabela
        
            tabela.setDefaultRenderer(Object.class, new ColorRender(this));
        
        UsoMaquina um=null;
        for (Maquina maquina : maquinas){
            if(!maquina.isRemovido()){
                if(maquina.getUsado()!=0){
                    um=umd.buscarPorChave(maquina.getUsado());
                    if(um!=null){
                        gaux=new GregorianCalendar();
                        tabela.setValueAt(Inicial.segundosString(this.tempoEntreDatas(gaux,um.getInicio())),i,3);
                        aux=this.segCliente(um.getCliente())-this.tempoEntreDatas(gaux,um.getFim());
                        tabela.setValueAt(Inicial.segundosString(aux),i,4);
                        if(aux<=0){
                            encerrarUso(um);
                        }
                    }
                }
                i++;
            }
        }
    }
    public static String segundosString(long i){
        int h=(int)(i/(60*60));
        int m=(int)((i-(h*60*60))/(60));
        int s=(int)(i-(h*60*60)-(m*60));
        return hora(h,m,s);
        
    }
    public void verficarDecCredito(){
        List<Maquina> maquinas = Persistencia.listar(new Maquina());
        GregorianCalendar gc=new GregorianCalendar();
        UsoMaquinaDAO umd=new UsoMaquinaDAO();
        ClienteDAO cd=new  ClienteDAO();
        Cliente aux;
        long dif;
//        List<UsoMaquina> usos=umd.listarAtivas();
        UsoMaquina um=null;
        for (Maquina maquina : maquinas){
            if(!maquina.isRemovido()){
                
                if(maquina.getUsado()!=0){
                    um=umd.buscarPorChave(maquina.getUsado());
                    if(um!=null){
                       dif= (long) ((gc.getTimeInMillis()-um.getFim().getTimeInMillis())/(1000));
                       if(dif>=(tempominimo*60)){
                           um.incAux();
                           atualizarCredito(um);
                       }
                    }
                }
            }
        }
    }
    public void atualizarCredito(UsoMaquina um){
        GregorianCalendar gc=new GregorianCalendar();
        UsoMaquinaDAO umd=new UsoMaquinaDAO();
        ClienteDAO cd=new  ClienteDAO();
        Cliente aux;
        aux=um.getCliente();
        aux.setCredito(aux.getCredito()-minimo);
        
        cd.atualizar(aux);
        um.setFim(gc);
        umd.atualizar(um);
    }
    public static String hora(int h,int m, int s){
         String sh = null,sm = null,ss = null;
        
        if(h<10){
            sh="0"+h;
        }else{
            sh=""+h;
        }
        if(m<10){
            sm="0"+m;
        }else{
            sm=""+m;
        }
        if(s<10){
            ss="0"+s;
        }else{
            ss=""+s;
        }
        return sh+":"+sm+":"+ss;
    }
    public long tempoEntreDatas(GregorianCalendar a,GregorianCalendar b){
        return (long)((a.getTimeInMillis()-b.getTimeInMillis())/1000);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        ScrollPane = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        campoBusca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        botaoCadastrar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();
        botaoConsultar = new javax.swing.JButton();
        botaoIncluirCredito = new javax.swing.JButton();
        botaoVendaProduto = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Cod", "Nome", "Marca", "Modelo", "Descrição", "Preço(un)", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ScrollPane.setViewportView(tabela);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 558, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 0));

        campoBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoBuscaActionPerformed(evt);
            }
        });
        campoBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoBuscaKeyPressed(evt);
            }
        });

        jLabel3.setText("Localizar:");

        botaoCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Cadastrar.png"))); // NOI18N
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        botaoRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Remover.png"))); // NOI18N
        botaoRemover.setText("Remover");
        botaoRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverActionPerformed(evt);
            }
        });

        botaoConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Consultar.png"))); // NOI18N
        botaoConsultar.setText("Consultar");
        botaoConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConsultarActionPerformed(evt);
            }
        });

        botaoIncluirCredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/Incluir credito.png"))); // NOI18N
        botaoIncluirCredito.setText("Incluir Credito");
        botaoIncluirCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIncluirCreditoActionPerformed(evt);
            }
        });

        botaoVendaProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/carrinho.png"))); // NOI18N
        botaoVendaProduto.setText("Venda de Produtos");
        botaoVendaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVendaProdutoActionPerformed(evt);
            }
        });

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(botaoCadastrar)
                .addGap(44, 44, 44)
                .addComponent(botaoRemover)
                .addGap(45, 45, 45)
                .addComponent(botaoConsultar)
                .addGap(34, 34, 34)
                .addComponent(botaoIncluirCredito)
                .addGap(35, 35, 35)
                .addComponent(botaoVendaProduto)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoIncluirCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoVendaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jMenu1.setText("Gerenciar Clientes");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenu1.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jMenu1MenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Gerenciar Máquina");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Gerenciar Produto");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Gerenciar O.S");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Gerenciar Funcionário");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenu6.setText("Valor Hora");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        jMenu7.setText("Monitorar Máquinas");
        jMenu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu7MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu7);

        jMenu8.setText("Relatórios");
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu8MouseClicked(evt);
            }
        });
        jMenu8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu8ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);
        jMenuBar1.getAccessibleContext().setAccessibleParent(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void preencheTabelaProd(){
        List<Produto> produtos = Persistencia.listar(new Produto());
        int i = 0;
        DefaultTableModel tableModel = (DefaultTableModel)tabela.getModel();
        tableModel.setNumRows(0);
        Object[] linha = {1};
        
        for (Produto produto : produtos){
            if(!produto.isRemovido()){
                tableModel.addRow(linha);
                tabela.setValueAt(produto.getCod(), i, 0);
                tabela.setValueAt(produto.getCategoria(), i, 1);
                tabela.setValueAt(produto.getMarca(), i, 2);
                tabela.setValueAt(produto.getModelo(), i, 3);
                tabela.setValueAt(produto.getDescricao(), i, 4);
                tabela.setValueAt(Inicial.dinheiro(produto.getPreco()), i, 5);
                tabela.setValueAt(produto.getEstoque(), i, 6);
                i++;
            }
        }
        if (i < 30){
            for (i=i; i<30; i++){
                tableModel.addRow(linha);
                tabela.setValueAt(null, i, 0);
            }
        }
              
     // botaoVendaProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/carrinho.png"))); 
    }

    private void campoBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoBuscaActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu1MenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jMenu1MenuKeyPressed
        // TODO add your handling code here:
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jMenu1MenuKeyPressed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        botaoCadastrar.setText("Cadastrar");
        botaoRemover.setText("Remover");
        this.botaoConsultar.setVisible(true);
        this.jMenu1.setBackground(new Color(255,255,0));
        this.botaoIncluirCredito.setVisible(true);
        this.botaoVendaProduto.setVisible(true);
        modelo.setColumnCount(0);
        modelo.addColumn("Login");
        modelo.addColumn("Nome");
        modelo.addColumn("Telefone");
        modelo.addColumn("Crédito");
        tabela.setModel(modelo);
        preencheTabelaCliente();
        valor=0;
    }//GEN-LAST:event_jMenu1MouseClicked

    private void botaoConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConsultarActionPerformed
       switch( valor ){
            case 0:
                    if (tabela.getSelectedRow() != -1){
                    int linha = tabela.getSelectedRow();
                        if (tabela.getValueAt(linha, 0) == null ){
                            break;
                        }
                        
                        //Cliente cliente = (Cliente)Persistencia.buscarProChave("cliente", (String)tabelaCliente.getValueAt(linha, 0));
                        List<Cliente> clientes = Persistencia.listar(new Cliente());
                        for (Cliente cliente: clientes){
                            if (cliente.getLogin().equals((String)tabela.getValueAt(linha, 0))){
                                
                                 TelaCliente.main(this, cliente, null);
                                 break;
                            }
                                    }
                       
                }
                   
                    break;

            case 1:
                if (tabela.getSelectedRow() != -1){
                    int linha = tabela.getSelectedRow();
                        if (tabela.getValueAt(linha, 0) == null){
                            break;
                        }
                        
                        Produto produto = GerenciarProduto.buscarProduto((Long)tabela.getValueAt(linha, 0));
                        TelaProduto.main(this, produto);
                }
        break;
                
                    case 3:
                    if (tabela.getSelectedRow() != -1){
                    int linha = tabela.getSelectedRow();
                        if (tabela.getValueAt(linha, 0) == null ){
                            break;
                        }
                        
                        //Cliente cliente = (Cliente)Persistencia.buscarProChave("cliente", (String)tabelaCliente.getValueAt(linha, 0));
                        List<Funcionario> funcs = Persistencia.listar(new Funcionario());
                        for (Funcionario f: funcs){
                            if (f.getLogin().equals((String)tabela.getValueAt(linha, 0))){
                                
                                 TelaFuncionario.main(this, f);
                                 break;
                            }
                        }
                       
                }
                   
                    break;
                            
                    case 5:
                    if (tabela.getSelectedRow() != -1){
                    int linha = tabela.getSelectedRow();
                        if (tabela.getValueAt(linha, 0) == null ){
                            break;
                        }
                    OrdemServicoDAO osd=new OrdemServicoDAO();
                    OrdemServico f=osd.busca((Long)tabela.getValueAt(linha, 0));
                    TelaOS.main(null, f.getCliente(), this, f);
                }
                   
                    break;
         case 2:
                if (tabela.getSelectedRow() != -1){
                    int linha = tabela.getSelectedRow();
                    
                        if (tabela.getValueAt(linha, 0) == null){
                            break;
                        }
                        MaquinaDAO md=new MaquinaDAO();
                        Maquina maquina =md.buscarPorChave((String)tabela.getValueAt(linha, 0));
                        TelaMaquina.main(null, maquina);
                }                   

}
    }//GEN-LAST:event_botaoConsultarActionPerformed
    
    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // Menu Produto
        botaoCadastrar.setText("Cadastrar");
        botaoRemover.setText("Remover");
        this.botaoConsultar.setVisible(true);
        this.botaoIncluirCredito.setVisible(false);
        this.botaoVendaProduto.setVisible(false);
        this.jMenu3.setBackground(Color.YELLOW);
        modelo.setColumnCount(0);
        modelo.addColumn("Código");
        modelo.addColumn("Categoria");
        modelo.addColumn("Marca");
        modelo.addColumn("Modelo");
        modelo.addColumn("Descrição");
        modelo.addColumn("Preço");
        modelo.addColumn("Quantidade");
        tabela.setModel(modelo);
        preencheTabelaProd();
        valor=1;
    }//GEN-LAST:event_jMenu3MouseClicked

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        // TODO add your handling code here:
    switch( valor ){
        case 0:
            TelaCliente.main(this,null,null);
            break;

        case 1:
            TelaProduto.main(this,null);
            break;
            
        case 2:
                TelaMaquina.main(null,null);
               
            break;
        case 3:
                TelaFuncionario.main(this,null);
            break;
        case 4:
                TelaUsoMaquina.main(null, this);
            break;
        case 5:
                TelaOS.main(null, null, this,null);
            break;
            
}
    }//GEN-LAST:event_botaoCadastrarActionPerformed
    
    public static String dinheiro(Float f){
        
        String sr = null,sc = null;
        
        if(f.intValue()<10){
            sr="0"+f.intValue();
        }else{
            sr=""+f.intValue();
        }
        if(((f-f.intValue())*100)<10){
            sc="0"+(int)((f-f.intValue())*100);
        }else{
            sc=""+(int)((f-f.intValue())*100);
        }
        return "R$ "+sr+","+sc;
    }
    private void botaoIncluirCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirCreditoActionPerformed
        if (tabela.getSelectedRow() != -1){
            int linha = tabela.getSelectedRow();
            if(valor!=4){
                if (tabela.getValueAt(linha, 0) != null){
                    Cliente cliente = GerenciarCliente.buscarCliente((String)tabela.getValueAt(linha, 0));
                    if(cliente!=null){
                        TelaIncluirCredito.main(cliente, this,null);
                    }else{
                        JOptionPane.showMessageDialog(this, "Não foi selecionado um cliente");
                    }
                }
            }else{
                if (tabela.getValueAt(linha, 0) != null){
                    Cliente cliente = GerenciarCliente.buscarCliente((String)tabela.getValueAt(linha, 1));
                    if(cliente!=null){
                        TelaIncluirCredito.main(cliente, this,null);
                    }else{
                        JOptionPane.showMessageDialog(this, "Não foi selecionado um cliente");
                    }
                }
            }
            
        }
    }//GEN-LAST:event_botaoIncluirCreditoActionPerformed

    private void botaoVendaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVendaProdutoActionPerformed
        Cliente cliente = null;
        if (tabela.getSelectedRow() != -1){
            int linha = tabela.getSelectedRow();
            if(this.valor==4){
                if (tabela.getValueAt(linha, 0) != null){
                    cliente = GerenciarCliente.buscarCliente((String)tabela.getValueAt(linha, 1));
                }
            }else{
                if (tabela.getValueAt(linha, 0) != null){
                    cliente = GerenciarCliente.buscarCliente((String)tabela.getValueAt(linha, 0));
                }
            }
            
        }
        TelaVenda.main(null, cliente, this);
    }//GEN-LAST:event_botaoVendaProdutoActionPerformed
    public void encerrarUso(){
        UsoMaquinaDAO umd=new UsoMaquinaDAO();
        MaquinaDAO md=new MaquinaDAO();
        if (tabela.getSelectedRow() != -1){
            int linha = tabela.getSelectedRow();
            if (tabela.getValueAt(linha, 0) == null ){
            }
            List<Maquina> maqs = Persistencia.listar(new Maquina());
            for (Maquina m: maqs){
                if (m.getId().equals((String)tabela.getValueAt(linha, 0))){
                    if(m.getUsado()!=0){
                        UsoMaquina uso=umd.buscarPorChave(m.getUsado());
                        uso.setAtivo(false);
                        m.setUsado(new Long(0));
                        md.atualizar(m);
                        atualizarCredito(uso);
                        preencheTabelaUsoMaquina();
                        break;  
                    }else{
                        JOptionPane.showMessageDialog(this, "Não tem um cliente usando esta máquina!!");
                    }
                }
            }

        }
    }
    public void encerrarUso(UsoMaquina uso){
        UsoMaquinaDAO umd=new UsoMaquinaDAO();
        MaquinaDAO md=new MaquinaDAO();
        Cliente c=uso.getCliente();
        c.setCredito(minimo);
        uso.setCliente(c);
        uso.setAtivo(false);
        Maquina m = uso.getMaquina();
        m.setUsado(new Long(0));
        md.atualizar(m);
        uso.setMaquina(m);
        atualizarCredito(uso);
        preencheTabelaUsoMaquina();
    }
    public boolean clienteUsado(Cliente cliente){
        UsoMaquinaDAO umd=new UsoMaquinaDAO();
        UsoMaquina uso;
        List<Maquina> maqs = Persistencia.listar(new Maquina());
        for (Maquina m: maqs){
            uso=umd.buscarPorChave(m.getUsado());
            if(uso!=null){
                if(uso.getCliente().getLogin().equals(cliente.getLogin())){
                    return true;
                }
            }
        }
        return false;
    }
    public void liberarMaquina(Cliente cliente){
        UsoMaquinaDAO umd=new UsoMaquinaDAO();
            if (tabela.getSelectedRow() != -1){
                int linha = tabela.getSelectedRow();
                if (tabela.getValueAt(linha, 0) == null ){
                }
                List<Maquina> maqs = Persistencia.listar(new Maquina());
                for (Maquina m: maqs){
                    if (m.getId().equals((String)tabela.getValueAt(linha, 0))){
                        UsoMaquina uso=new UsoMaquina();
                        uso.setCliente(cliente);
                        uso.setMaquina(m);
                        GregorianCalendar c=new GregorianCalendar();
                        uso.setInicio(c);
                        uso.setFim(c);
                        uso.setAtivo(true);
                        if(!umd.salvar(uso)){
                            JOptionPane.showMessageDialog(this, "Já tem um cliente usando esta máquina!!");
                        }else{
                            
                            preencheTabelaUsoMaquina();
                        }
                        break;
                    }
                }

            }

    }
    private void botaoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverActionPerformed
    switch( valor ){
        case 0:
                 if (tabela.getSelectedRow() != -1){
                    int linha = tabela.getSelectedRow();
                        if (tabela.getValueAt(linha, 0) == null ){
                            break;
                        }
                       int x=JOptionPane.showConfirmDialog(this, "Deseja remover o cliente?");;
                        if(x==0){
                               List<Cliente> clientes = Persistencia.listar(new Cliente());
                        for (Cliente cliente: clientes){
                            if (cliente.getLogin().equals((String)tabela.getValueAt(linha, 0))){
                                
                                 ClienteDAO c =new ClienteDAO();
                                 c.excluir(cliente);
                               
                                 break;
                            }
                                    }
                        }
                        else{
                            break;
                        }
                        preencheTabelaCliente();
                       
                                          
                }
            break;

        case 1:
            if (tabela.getSelectedRow() != -1){
                int linha = tabela.getSelectedRow();
                
                if (tabela.getValueAt(linha, 0) != null){
                    GerenciarProduto.confirmarExclusao(this, (Long)tabela.getValueAt(linha, 0));
                }
            }
            break;
                    case 3:
                    if (tabela.getSelectedRow() != -1){
                    int linha = tabela.getSelectedRow();
                        if (tabela.getValueAt(linha, 0) != null ){
                            int op = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o Funcionario?");
                            if (op == 0){
                            FuncionarioDAO fd = new FuncionarioDAO();    
                            
                            Funcionario f=null;
                            List<Funcionario> fs=fd.listar();
                            for (Funcionario fa : fs){
                                if(fa.getLogin().equals((String)tabela.getValueAt(linha, 0))){
                                    f=fa;
                                    break;
                                }
                            }
                            fd.excluir(f);
                            preencheTabelaFunc();
                            }
                        }
                    }   
               break;
                    case 2:
                       if (tabela.getSelectedRow() != -1){
                    int linha = tabela.getSelectedRow();
                        if (tabela.getValueAt(linha, 0) == null ){
                            break;
                        }
                       int x=JOptionPane.showConfirmDialog(this, "Deseja remover a maquina?");;
                        if(x==0){
                               List<Maquina> maquinas = Persistencia.listar(new Maquina());
                        for (Maquina maquina: maquinas){
                            if (maquina.getId().equals((String)tabela.getValueAt(linha, 0))){
                                
                                 MaquinaDAO c =new MaquinaDAO();
                                 maquina.setRemovido(true);
                                 c.atualizar(maquina);
                               
                                 break;
                            }
                                    }
                        }
                        else{
                            break;
                        }
                        preencheTabelaMaquina();
                       
                                          
                }
            
                break;
               case 4: this.encerrarUso();
               case 5: 
                if (tabela.getSelectedRow() != -1){
                    int linha = tabela.getSelectedRow();
                    if (tabela.getValueAt(linha, 0) == null ){
                         break;
                    }
                    int x=JOptionPane.showConfirmDialog(this, "Deseja finalizar a Ordem de serviço?");;
                    if(x==0){
                        OrdemServicoDAO osd=new OrdemServicoDAO();
                        OrdemServico f=osd.busca((Long)tabela.getValueAt(linha, 0));

                        f.setRemovido(true);
                        f.setTerminada(new Date());
                        osd.atualizar(f);

                               
                        
                    }else{
                            break;
                    }
                    preencheTabelaOS();
                       
                                          
                }
}
    }//GEN-LAST:event_botaoRemoverActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        botaoCadastrar.setText("Cadastrar");
        botaoRemover.setText("Remover");
        this.botaoConsultar.setVisible(true);
        this.botaoIncluirCredito.setVisible(false);
        this.botaoVendaProduto.setVisible(false);
        this.jMenu3.setBackground(Color.YELLOW);
        modelo.setColumnCount(0);
        modelo.addColumn("ID");
        modelo.addColumn("IP");
        tabela.setModel(modelo);
        preencheTabelaMaquina();
        valor=2;
        
    }//GEN-LAST:event_jMenu2MouseClicked

    private void campoBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBuscaKeyPressed
    switch( valor ){
        case 0:
                if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                String chave = campoBusca.getText().trim();
                List<Cliente> clientes = Persistencia.listar(new Cliente());

                if (chave.equals("")){
                    preencheTabelaCliente();
                }else {
                    DefaultTableModel tableModel = (DefaultTableModel)tabela.getModel();
                    tableModel.setNumRows(0);
                    Object[] linha = {1};
                    int i = 0;
                    for (Cliente cliente : clientes){
                        if(!cliente.isRemovido() && cliente.getLogin().equalsIgnoreCase(chave)){
                            tableModel.addRow(linha);
                            tabela.setValueAt(cliente.getLogin(),i,0);
                            tabela.setValueAt(cliente.getNome(),i,1);
                            tabela.setValueAt(cliente.getTelefone(),i,2);
                                                 
                           
                            i++;
                        }
                    }
                    if (i < 30){
                        for (i=i; i<30; i++){
                            tableModel.addRow(linha);
                            tabela.setValueAt(null, i, 0);
                        }
                    }
                }
            }
            break;

        case 1:
            if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                String chave = campoBusca.getText().trim();
                DefaultTableModel tableModel = (DefaultTableModel)tabela.getModel();
                GerenciarProduto.buscarPorNome(this, chave, tableModel, tabela);
            }
            break;
            case 3:
               if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                String chave = campoBusca.getText().trim();
                List<Funcionario> clientes = Persistencia.listar(new Funcionario());

                if (chave.equals("")){
                    preencheTabelaCliente();
                }else {
                    DefaultTableModel tableModel = (DefaultTableModel)tabela.getModel();
                    tableModel.setNumRows(0);
                    Object[] linha = {1};
                    int i = 0;
                    for (Funcionario f : clientes){
                        if(!f.isRemovido() && f.getLogin().equalsIgnoreCase(chave)){
                            tableModel.addRow(linha);
                            tabela.setValueAt(f.getLogin(), i, 0);
                            tabela.setValueAt(f.getNome(), i, 1);
                            tabela.setValueAt(f.getCelular(), i, 2);
                            tabela.setValueAt(f.getEmail(), i, 3);
                            i++;
                        }
                    }
                    if (i < 30){
                        for (i=i; i<30; i++){
                            tableModel.addRow(linha);
                            tabela.setValueAt(null, i, 0);
                        }
                    }
                }
            }
            break;
            
    }        
    }//GEN-LAST:event_campoBuscaKeyPressed
    public void preencheTabelaCliente(){
        List<Cliente> clientes = Persistencia.listar(new Cliente());
        int i = 0;
        DefaultTableModel tableModel = (DefaultTableModel)tabela.getModel();
        tableModel.setNumRows(0);
        Object[] linha = {1};
        
        for (Cliente cliente : clientes){
            if(!cliente.isRemovido()){
                tableModel.addRow(linha);
                tabela.setValueAt(cliente.getLogin(), i, 0);
                tabela.setValueAt(cliente.getNome(), i, 1);
                tabela.setValueAt(cliente.getTelefone(), i, 2);
                tabela.setValueAt(Inicial.dinheiro(cliente.getCredito()), i, 3);
                
               
                i++;
            }
        }
        if (i < 30){
            for (i=i; i<30; i++){
                tableModel.addRow(linha);
                tabela.setValueAt(null, i, 0);
            }
        }
    }
        /**
     *
     */
    public void preencheTabelaMaquina(){
        List<Maquina> maquinas = Persistencia.listar(new Maquina());
        int i = 0;
        DefaultTableModel tableModel = (DefaultTableModel)tabela.getModel();
        tableModel.setNumRows(0);
        Object[] linha = {1};
        
        for (Maquina maquina : maquinas){
            if(!maquina.isRemovido()){
                tableModel.addRow(linha);
                tabela.setValueAt(maquina.getId(), i, 0);
                tabela.setValueAt(maquina.getIp(), i, 1);
                i++;
            }
        }
        if (i < 30){
            for (i=i; i<30; i++){
                tableModel.addRow(linha);
                tabela.setValueAt(null, i, 0);
            }
        }
    }
    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        botaoCadastrar.setText("Cadastrar");
        botaoRemover.setText("Remover");
        this.botaoConsultar.setVisible(true);
        this.botaoIncluirCredito.setVisible(false);
        this.botaoVendaProduto.setVisible(false);
        this.jMenu3.setBackground(Color.YELLOW);
        modelo.setColumnCount(0);
        modelo.addColumn("Login");
        modelo.addColumn("Nome");
        modelo.addColumn("Celular");
        modelo.addColumn("Email");
        tabela.setModel(modelo);

        preencheTabelaFunc();
        valor = 3;
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        TelaValorHora v = new TelaValorHora(null);
        v.setVisible(true);
        
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Login i = new Login();
        i.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu7MouseClicked
        // TODO add your handling code here:
        botaoCadastrar.setText("Liberar Uso");
        botaoRemover.setText("Encerrar Uso");
        botaoConsultar.setVisible(false);
        botaoIncluirCredito.setVisible(true);
        botaoVendaProduto.setVisible(true);
        modelo.setColumnCount(0);
        modelo.addColumn("Máquina");
        modelo.addColumn("Login");
        modelo.addColumn("Inicio");
        modelo.addColumn("Tempo");
        modelo.addColumn("Tempo Restante");
        tabela.setModel(modelo);
        preencheTabelaUsoMaquina();
        valor =4;
        
    }//GEN-LAST:event_jMenu7MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        // TODO add your handling code here:
        botaoCadastrar.setText("Cadastrar");
        botaoRemover.setText("Finalizar");
        this.botaoConsultar.setVisible(true);
        this.botaoIncluirCredito.setVisible(false);
        this.botaoVendaProduto.setVisible(false);
        modelo.setColumnCount(0);
        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("Máquina");
        modelo.addColumn("Data Solicitação");
        modelo.addColumn("Obs");
        tabela.setModel(modelo);
        preencheTabelaOS();
        valor = 5;
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu8ActionPerformed
        TelaRelatorio.main(null);
    }//GEN-LAST:event_jMenu8ActionPerformed

    private void jMenu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MouseClicked
        TelaRelatorio.main(null);
    }//GEN-LAST:event_jMenu8MouseClicked
    public void preencheTabelaFunc(){
        List<Funcionario> funcs = Persistencia.listar(new Funcionario());
        int i = 0;
        DefaultTableModel tableModel = (DefaultTableModel)tabela.getModel();
        tableModel.setNumRows(0);
        Object[] linha = {1};
        
        for (Funcionario f : funcs){
            if(!f.isRemovido()){
                tableModel.addRow(linha);
                tabela.setValueAt(f.getLogin(), i, 0);
                tabela.setValueAt(f.getNome(), i, 1);
                tabela.setValueAt(f.getCelular(), i, 2);
                tabela.setValueAt(f.getEmail(), i, 3);
                i++;
            }
        }
        if (i < 30){
            for (i=i; i<30; i++){
                tableModel.addRow(linha);
                tabela.setValueAt(null, i, 0);
            }
        }
    }
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
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicial().setVisible(true);
            }
            
        });
        
    }

    public JMenu getjMenu5() {
        return jMenu5;
    }

    public void setjMenu5(JMenu jMenu5) {
        this.jMenu5 = jMenu5;
    }

    public JMenu getjMenu6() {
        return jMenu6;
    }

    public void setjMenu6(JMenu jMenu6) {
        this.jMenu6 = jMenu6;
    }

    public JMenu getjMenu8() {
        return jMenu8;
    }

    public void setjMenu8(JMenu jMenu8) {
        this.jMenu8 = jMenu8;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public float getTempominimo() {
        return tempominimo;
    }

    public void setTempominimo(float tempominimo) {
        this.tempominimo = tempominimo;
    }

    public void preencheTabelaOS() {
        List<OrdemServico> funcs = Persistencia.listar(new OrdemServico());
        int i = 0;
        DefaultTableModel tableModel = (DefaultTableModel)tabela.getModel();
        tableModel.setNumRows(0);
        Object[] linha = {1};
        
        for (OrdemServico f : funcs){
            if(!f.isRemovido()){
                tableModel.addRow(linha);
                tabela.setValueAt(f.getId(), i, 0);
                tabela.setValueAt(f.getCliente().getLogin(), i, 1);
                tabela.setValueAt(f.getMaquina(), i, 2);
                tabela.setValueAt(f.getSolicitada(), i, 3);
                tabela.setValueAt(f.getObs(), i, 4);
                i++;
            }
        }
        if (i < 30){
            for (i=i; i<30; i++){
                tableModel.addRow(linha);
                tabela.setValueAt(null, i, 0);
            }
        } //To change body of generated methods, choose Tools | Templates.
    }
    
    class Colorir extends JLabel implements TableCellRenderer  {  
    public Component getTableCellRendererComponent(  
     JTable table,   
     Object value, boolean isSelected, boolean hasFocus,  
        int row, int column){  
      
     if(row == table.getRowCount()){  
       setBackground(Color.YELLOW);   
     }  
     else{  
       setBackground(table.getBackground());          
     }       
              
     return this;         
  } 
}  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoConsultar;
    private javax.swing.JButton botaoIncluirCredito;
    private javax.swing.JButton botaoRemover;
    private javax.swing.JButton botaoVendaProduto;
    private javax.swing.JTextField campoBusca;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
