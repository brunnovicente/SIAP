/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import visao.processos.BuscaGrupo;
import controle.FachadaControle;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.entidades.Fornecedor;
import persistencia.entidades.Grupo;

/**
 *
 * @author Bruno
 */
public class JanelaConsultaGrupo extends javax.swing.JDialog {
    
    private TelaPrincipal pai;
    private Grupo grupo = null;
    /**
     * Creates new form JanelaConsultaFabricantes
     */
    public JanelaConsultaGrupo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.pai = (TelaPrincipal) parent;
        jbutaoadd.setVisible(false);
        this.atualizaTabela();
    }

    public JanelaConsultaGrupo(java.awt.Frame parent, boolean modal, Grupo grupo) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.pai = (TelaPrincipal) parent;
        this.grupo = grupo;
        jbutaoeditar.setEnabled(false);
        jbutaoexcluir.setEnabled(false);
        this.atualizaTabela();
    }
    
   /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabela = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jbutaoexcluir = new javax.swing.JButton();
        jbutaoeditar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jpesquisa = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jbutaoadd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTA DE GRUPOS");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Consulta de Grupos"));

        jtabela.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "NOME"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabelaMouseClicked(evt);
            }
        });
        jtabela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtabelaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtabela);
        if (jtabela.getColumnModel().getColumnCount() > 0) {
            jtabela.getColumnModel().getColumn(0).setResizable(false);
            jtabela.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtabela.getColumnModel().getColumn(1).setResizable(false);
            jtabela.getColumnModel().getColumn(1).setPreferredWidth(350);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        jButton1.setText("FECHAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Novo.png"))); // NOI18N
        jButton2.setText("NOVO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jbutaoexcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Lixeira2.png"))); // NOI18N
        jbutaoexcluir.setText("EXCLUIR");
        jbutaoexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaoexcluirActionPerformed(evt);
            }
        });

        jbutaoeditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar.png"))); // NOI18N
        jbutaoeditar.setText("EDITAR");
        jbutaoeditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaoeditarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa de Grupos"));

        jpesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jpesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jpesquisaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jpesquisaKeyReleased(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisa2.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpesquisa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jpesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jbutaoadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Add.png"))); // NOI18N
        jbutaoadd.setText("ADD");
        jbutaoadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaoaddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jbutaoadd, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbutaoeditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbutaoexcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jbutaoexcluir)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbutaoeditar))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbutaoadd)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JanelaCadastroGrupo cadastro = new JanelaCadastroGrupo(this.pai, true);
        cadastro.setVisible(true);
        this.atualizaTabela();
        jpesquisa.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jbutaoexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaoexcluirActionPerformed
        this.excluir();
    }//GEN-LAST:event_jbutaoexcluirActionPerformed

    private void jpesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpesquisaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jpesquisaKeyReleased

    private void jpesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpesquisaKeyPressed
        if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)){
             jtabela.requestFocus();
        }if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.atualizaTabela();
        }
    }//GEN-LAST:event_jpesquisaKeyPressed

    private void jtabelaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtabelaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE){
             this.excluir();
        }if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(grupo == null){
                this.editar();
            }else{
                this.enviar();
            }
        }
    }//GEN-LAST:event_jtabelaKeyPressed

    private void jbutaoeditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaoeditarActionPerformed
         this.editar();
    }//GEN-LAST:event_jbutaoeditarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.atualizaTabela();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jbutaoaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaoaddActionPerformed
        this.enviar();
    }//GEN-LAST:event_jbutaoaddActionPerformed

    private void jtabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabelaMouseClicked
        if(evt.getClickCount() == 2){
            if(grupo == null){
                this.editar();
            }else{
                this.enviar();
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jtabelaMouseClicked

    public void excluir(){
        try {
            int codigo = Integer.parseInt((String)jtabela.getValueAt(jtabela.getSelectedRow(), 0));
            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este grupo?", "Excluir Grupo", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(confirma == JOptionPane.YES_OPTION){
                Grupo grupo = FachadaControle.getFachadaControle().excluirGrupo(codigo);
                this.atualizaTabela();
                JOptionPane.showMessageDialog(null, "Grupo '"+grupo.getNome()+"' excluído com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                jpesquisa.requestFocus();
            }            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            if(ex.getMessage().equals("-1")){
                JOptionPane.showMessageDialog(null, "Selecione um Grupo!");
                ex.printStackTrace();
            }else{
                if(ex.getMessage().contains("while")){
                    JOptionPane.showMessageDialog(null, "Não pode excluir um grupo relacionado à um produto!");
                    ex.printStackTrace();
                }else{
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }
    
    public void editar(){
        try{
            Grupo g = new Grupo();
            g.setCodigo(Integer.parseInt((String)jtabela.getValueAt(jtabela.getSelectedRow(), 0)));
            g.setNome((String)jtabela.getValueAt(jtabela.getSelectedRow(), 1));
            g.setStatus("Ativo");
            JanelaCadastroGrupo editar = new JanelaCadastroGrupo(this.pai, true,g);
            editar.setVisible(true);
            this.atualizaTabela();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Selecione um Grupo!");
        }
    }
    
    public void atualizaTabela(){
        try {
            List<Grupo> lista = FachadaControle.getFachadaControle().buscaGrupo(jpesquisa.getText());
            DefaultTableModel modelo = (DefaultTableModel) jtabela.getModel();
            //Limpa a tabela antes de adicionar os novos dados
            int tam = modelo.getRowCount();
            for(int i=0;i<tam;i++){
                modelo.removeRow(0);
            }
            
            BuscaGrupo processoGrupo = new BuscaGrupo(jtabela, lista);
            processoGrupo.start();
            
        } catch (SQLException ex) {
            Logger.getLogger(JanelaConultaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviar(){
        try{
            //Fabricante f = new Fabricante();
            grupo.setCodigo(Integer.parseInt((String)jtabela.getValueAt(jtabela.getSelectedRow(), 0)));
            grupo.setNome((String)jtabela.getValueAt(jtabela.getSelectedRow(), 1));
            this.dispose();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Selecione um Grupo!");
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
//            java.util.logging.Logger.getLogger(JanelaConsultaFabricantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JanelaConsultaFabricantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JanelaConsultaFabricantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JanelaConsultaFabricantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /*
//         * Create and display the dialog
//         */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                JanelaConsultaFabricantes dialog = new JanelaConsultaFabricantes(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbutaoadd;
    private javax.swing.JButton jbutaoeditar;
    private javax.swing.JButton jbutaoexcluir;
    private javax.swing.JTextField jpesquisa;
    private javax.swing.JTable jtabela;
    // End of variables declaration//GEN-END:variables
}
