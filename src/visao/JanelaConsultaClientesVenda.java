/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.FachadaControle;
import controle.GerenciadorClientes;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.entidades.Cliente;
import persistencia.entidades.Produto;
import persistencia.entidades.Veiculo;

/**
 *
 * @author Bruno
 */
public class JanelaConsultaClientesVenda extends javax.swing.JDialog {
    
    private List<Cliente> lista;
    private JFrame pai;
    private Cliente cliente = null;
        
    /**
     * Creates new form JanelaConsultaProdutos
     */
    public JanelaConsultaClientesVenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.pai = (JFrame) parent;
        
        this.setLocationRelativeTo(null);
        jbutaoAdicionar.setVisible(false);
        try {
            this.consultar();
        } catch (Exception ex) {
            Logger.getLogger(JanelaConsultaClientesVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public JanelaConsultaClientesVenda(java.awt.Frame parent, boolean modal, Cliente cliente){
        super(parent, modal);
        initComponents();
        this.pai = (JFrame) parent;
        
        this.setLocationRelativeTo(null);
        jbutaoAdicionar.setVisible(true);
        this.cliente = cliente;
        try {
            this.consultar();
        } catch (Exception ex) {
            Logger.getLogger(JanelaConsultaClientesVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
//    public JanelaConsultaClientes(java.awt.Frame parent, boolean modal,Produto produto) {
//        super(parent, modal);
//        initComponents();
//        this.setLocationRelativeTo(null);
//        
//        this.jChave.requestFocus();
//        jbutaoAdicionar.setVisible(true);
//        jbutaoEditar.setEnabled(false);
//        
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jChave = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabelaclientes = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jbutaoAdicionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTA DE CLIENTES");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa por Nome ou Apelido"));
        jPanel1.setToolTipText("Pesquisa");

        jChave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jChave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChaveActionPerformed(evt);
            }
        });
        jChave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jChaveKeyPressed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisa.png"))); // NOI18N
        jButton7.setText("BUSCAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jChave, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addGap(371, 371, 371))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados dos Clientes"));

        jtabelaclientes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtabelaclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Apelido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabelaclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabelaclientesMouseClicked(evt);
            }
        });
        jtabelaclientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtabelaclientesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtabelaclientes);
        if (jtabelaclientes.getColumnModel().getColumnCount() > 0) {
            jtabelaclientes.getColumnModel().getColumn(0).setResizable(false);
            jtabelaclientes.getColumnModel().getColumn(0).setPreferredWidth(10);
            jtabelaclientes.getColumnModel().getColumn(1).setResizable(false);
            jtabelaclientes.getColumnModel().getColumn(1).setPreferredWidth(300);
            jtabelaclientes.getColumnModel().getColumn(2).setResizable(false);
            jtabelaclientes.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Parar.png"))); // NOI18N
        jButton6.setText("FECHAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jbutaoAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Add.png"))); // NOI18N
        jbutaoAdicionar.setText("Adicionar");
        jbutaoAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaoAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbutaoAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jbutaoAdicionar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            this.consultar();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jtabelaclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabelaclientesMouseClicked
       

    }//GEN-LAST:event_jtabelaclientesMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jtabelaclientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtabelaclientesKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.mandarclienteVenda();
        }if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jtabelaclientesKeyPressed

    private void jChaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jChaveKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            try {
                this.consultar();
            } catch (Exception ex) {
                Logger.getLogger(JanelaConsultaClientesVenda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN){
            jtabelaclientes.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jChaveKeyPressed

    private void jChaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChaveActionPerformed
        
    }//GEN-LAST:event_jChaveActionPerformed

    private void jbutaoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaoAdicionarActionPerformed
        this.mandarclienteVenda();
    }//GEN-LAST:event_jbutaoAdicionarActionPerformed

   public void mandarclienteVenda(){
       int i = jtabelaclientes.getSelectedRow();

        Cliente c = this.lista.get(i);

        //        c.setVeiculos(new ArrayList<Veiculo>());
        //        c.getVeiculos().add(v);

        cliente.setCodigo(c.getCodigo());
        cliente.setNome(c.getNome());
        cliente.setApelido(c.getApelido());
        cliente.setBairro(c.getBairro());
        cliente.setCelular(c.getCelular());
        cliente.setTelefone(c.getTelefone());
        cliente.setCep(c.getCep());
        cliente.setCpf(c.getCpf());
        cliente.setRg(c.getRg());
        cliente.setEndereco(c.getEndereco());
        cliente.setNumero(c.getNumero());
        cliente.setObservacao(c.getObservacao());
        cliente.setStatus(c.getStatus());
        cliente.setSexo(c.getSexo());

        cliente.setVeiculos(c.getVeiculos());
        
        this.dispose();
   }
      
    public void consultar() throws SQLException, Exception{
        lista = FachadaControle.getFachadaControle().consultaClientes(jChave.getText());
        this.meteNaTabela();
    }

    private void meteNaTabela(){
        DefaultTableModel modelo = (DefaultTableModel) jtabelaclientes.getModel();
        
            //Limpa a tabela antes de adicionar os novos dados
            int tam = modelo.getRowCount();
            for(int i=0;i<tam;i++){
                modelo.removeRow(0);
            }
            
           
            for (Cliente cliente : lista) {
                String[] linha = new String[3];
                linha[0] = cliente.getCodigo()+"";
                linha[1] = cliente.getNome();
                linha[2] = cliente.getApelido();
                modelo.addRow(linha);
    
            }
                       
    }
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /*
//         * Set the Nimbus look and feel
//         */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /*
//         * If Nimbus (introduced in Java SE 6) is not available, stay with the
//         * default look and feel. For details see
//         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JanelaConsultaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JanelaConsultaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JanelaConsultaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JanelaConsultaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /*
//         * Create and display the dialog
//         */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                JanelaConsultaProdutos dialog = new JanelaConsultaProdutos(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JTextField jChave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbutaoAdicionar;
    private javax.swing.JTable jtabelaclientes;
    // End of variables declaration//GEN-END:variables
}