/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visao;

import visao.processos.Barra;

/**
 *
 * @author brunn_000
 */
public class JanelaRestauracao extends javax.swing.JDialog {

    /**
     * Creates new form JanelaRestauracao
     */
    private Process p;
    
    public JanelaRestauracao(java.awt.Frame parent, boolean modal,String caminho, Process p) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.p = p;
        //System.out.println("TAMANHO: " + jbarra.getWidth()+"");
        //jbarra.setIndeterminate(true);
        
//        Barra barra = new Barra(jbarra);
//        barra.start();

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
        jbarra = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtexto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Restaurando Backup");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Restauração dos Dados"));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/backup.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Restaurando os dados... Não feche o sistema!");

        jtexto.setEditable(false);
        jtexto.setBackground(new java.awt.Color(240, 240, 240));
        jtexto.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jbarra, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                .addComponent(jtexto)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Barra barra = new Barra(jbarra,this,jtexto, p);
        barra.start();
        //boolean resultado = this.recuperarBackup(caminho);
        //barra.parar();
        //this.dispose();
    }//GEN-LAST:event_formWindowOpened

//    private boolean recuperarBackup(String filename) {
//        FachadaControle.getFachadaControle().Fechar(); //FECHA A INSTANCIA DO HIBERNATE
//        try {
//            File file = new File(filename);
//
//            String dump = "cmd.exe /c mysql" +
//                    " --user=root" +
//                    " --password=neural" +
//                    " bancopecas" +
//                    " < " + file.getAbsolutePath();
//            System.out.println(dump);
//            //try {
//                Runtime runtime = Runtime.getRuntime();
//                runtime.exec(dump);
//                //Process process = this.start(dump);  
//                //process.waitFor();
//                System.out.println("Fim");
//                
//            //} catch (InterruptedException ex) {
//            //    Logger.getLogger(JanelaRestauracao.class.getName()).log(Level.SEVERE, null, ex);
//            //}
//            
//        } catch (IOException ex) {
//            Logger.getLogger(BackupSupremo.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return true;
//    }
    
//    private Process start(String comando) {      
//        try {      
//             return Runtime.getRuntime().exec(comando); 
//        } catch (IOException ex) {      
//             ex.printStackTrace();      
//        }      
//        return null;
//    }  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jbarra;
    private javax.swing.JTextField jtexto;
    // End of variables declaration//GEN-END:variables
}
