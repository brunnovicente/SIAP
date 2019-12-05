/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.FachadaControle;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import persistencia.FachadaPersistencia;
import persistencia.entidades.Venda;

/**
 *
 * @author Bruno
 */
public class JanelaFecharCaixa extends javax.swing.JDialog {
    private java.awt.Frame pai;
    private FachadaPersistencia banco;
    
    /**
     * Creates new form JanelaAbrirCaixa
     */
    public JanelaFecharCaixa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.banco = new FachadaPersistencia();
        this.setLocationRelativeTo(null);
        this.pai = pai;
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        String data = df.format(FachadaControle.getFachadaControle().getCaixa().getDataInicial());
        
        jdata.setText(data+"");
        jvalorfinal.setText(("R$ "+FachadaControle.getFachadaControle().getCaixa().getCaixaFinal()+"").replace(".", ","));
        jvalor.setText(("R$ "+FachadaControle.getFachadaControle().getCaixa().getCaixaInicial()+"").replace(".", ","));
        jdinheiro.setText(("R$ "+FachadaControle.getFachadaControle().getCaixa().getTotalDinheiro()+"").replace(".", ","));
        jcartao.setText(("R$ "+FachadaControle.getFachadaControle().getCaixa().getTotalCartao()+"").replace(".", ","));
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
        jLabel1 = new javax.swing.JLabel();
        jvalor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jvalorfinal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jdata = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jdinheiro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jcartao = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FEHCAR CAIXA DO DIA");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("DADOS DO CAIXA"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("VALOR INCIAL:");

        jvalor.setEditable(false);
        jvalor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jvalor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jvalorKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("VALOR FINAL:");

        jvalorfinal.setEditable(false);
        jvalorfinal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("DATA:");

        jdata.setEditable(false);
        jdata.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jdata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jdataKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("TOTAL DINHEIRO:");

        jdinheiro.setEditable(false);
        jdinheiro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("TOTAL CARTÃO:");

        jcartao.setEditable(false);
        jcartao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jvalorfinal)
                    .addComponent(jvalor)
                    .addComponent(jdata, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdinheiro)
                    .addComponent(jcartao))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jdata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jvalorfinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jdinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok.png"))); // NOI18N
        jButton1.setText("FECHAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir.png"))); // NOI18N
        jButton2.setText("SAIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 124, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.fecharCaixa();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jvalorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jvalorKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.fecharCaixa();
        }else{
            if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
                this.dispose();
            }
        }
    }//GEN-LAST:event_jvalorKeyPressed

    private void jdataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdataKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.fecharCaixa();
        }else{
            if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
                this.dispose();
            }
        }
    }//GEN-LAST:event_jdataKeyPressed

    public void fecharCaixa(){
        try {
            int sim = JOptionPane.showConfirmDialog(null, "Deseja realmente fechar o caixa?", "Fechar Caixa", JOptionPane.YES_NO_OPTION);
            if(sim == JOptionPane.YES_OPTION){
                
                FachadaControle.getFachadaControle().getCaixa().setDataFinal(new Date());
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                ArrayList<Venda> vendas = (ArrayList<Venda>) FachadaControle.getFachadaControle().consultaVendas(formato.format(FachadaControle.getFachadaControle().getCaixa().getDataInicial()),formato.format(FachadaControle.getFachadaControle().getCaixa().getDataFinal()));
                JanelaGerandoRelatorio janelaEspera = new JanelaGerandoRelatorio(pai, vendas,formato.format(new Date()),formato.format(new Date()), true);
                janelaEspera.setVisible(true);
                
                FachadaControle.getFachadaControle().encerrarCaixa();
                JOptionPane.showMessageDialog(null, "Caixa fechado com sucesso!!");

//                for (Venda venda : vendas) {
//                    this.banco.excluirVenda(venda);
//                }

                this.dispose();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            if(ex.getMessage().contains("for")){
                JOptionPane.showMessageDialog(null, "Digite um valor válido!");
            }else{
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JanelaAbrirCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JanelaAbrirCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JanelaAbrirCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JanelaAbrirCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                JanelaAbrirCaixa dialog = new JanelaAbrirCaixa(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jcartao;
    private javax.swing.JTextField jdata;
    private javax.swing.JTextField jdinheiro;
    private javax.swing.JTextField jvalor;
    private javax.swing.JTextField jvalorfinal;
    // End of variables declaration//GEN-END:variables
}
