/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import persistencia.entidades.Caixa;
import persistencia.entidades.Produto;

/**
 *
 * @author Bruno
 */
public class JanelaPainelCaixa extends javax.swing.JInternalFrame {

    
    private Caixa caixa;
    
    /**
     * Creates new form JanelaPainel
     */
    public JanelaPainelCaixa(Caixa caixa) {
        initComponents();
        this.caixa = caixa;

        this.atualizaTabela();
    }

    public void atualizaTabela(){
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        String dataFormatada = df.format(caixa.getDataInicial());
        jdata.setText(dataFormatada);
        jinicial.setText("R$ "+caixa.getCaixaFinal());
        System.out.println("R$ "+caixa.getCaixaFinal());
        jdinheiro.setText("R$ "+caixa.getTotalDinheiro());
        System.out.println("R$ "+caixa.getTotalDinheiro());
        jcartao.setText("R$ "+caixa.getTotalCartao());
        System.out.println("R$ "+caixa.getTotalCartao());
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
        jdata = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jinicial = new javax.swing.JTextField();
        jdinheiro = new javax.swing.JTextField();
        jcartao = new javax.swing.JTextField();

        setTitle("DADOS DO CAIXA ABERTO");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("CAIXA ABERTO:");

        jdata.setEditable(false);
        jdata.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jdata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdataActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("CAIXA INICIAL:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("TOTAL EM DINHEIRO");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("TOTAL EM CARTÃO");

        jinicial.setEditable(false);
        jinicial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jdinheiro.setEditable(false);
        jdinheiro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jcartao.setEditable(false);
        jcartao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdata, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jcartao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                        .addComponent(jdinheiro, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jinicial, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jdata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jinicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jdinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jcartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jdataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jdataActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jcartao;
    private javax.swing.JTextField jdata;
    private javax.swing.JTextField jdinheiro;
    private javax.swing.JTextField jinicial;
    // End of variables declaration//GEN-END:variables
}
