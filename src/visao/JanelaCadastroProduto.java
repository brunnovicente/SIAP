/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.FachadaControle;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import persistencia.FachadaPersistencia;
import persistencia.entidades.Fabricante;
import persistencia.entidades.Fornecedor;
import persistencia.entidades.Grupo;
import persistencia.entidades.Produto;
import persistencia.entidades.Produto_fornecedor;
import persistencia.entidades.Usuario;


/**
 *
 * @author Bruno
 */
public class JanelaCadastroProduto extends javax.swing.JDialog {
    
    private FachadaPersistencia banco;
    private TelaPrincipal pai;
    private boolean cadastro;
    private File arquivo = null;
    private Set<Produto> similares;
    private Produto produto;
    private Set<Produto> excluidos;
    /**
     * Construtor para o modo de cadastro de produto
     */
    public JanelaCadastroProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.banco = new FachadaPersistencia();
        
        this.pai = (TelaPrincipal) parent;
        this.setLocationRelativeTo(null);
        this.cadastro = true;

        this.jcodigo.requestFocus();
//        this.jbutaoNovo.setEnabled(false);
        this.similares = new HashSet<Produto>();
        excluidos = new HashSet<Produto>();
        this.produto = new Produto();
        
        try{
            //Exibindo a imagem no JLabelprod
           jfoto.setIcon(null);
           arquivo = new File("semfoto.jpg");
           //Exibindo a imagem no JLabel
           ImageIcon img = new ImageIcon("semfoto.jpg");
           
           Image foto = img.getImage().getScaledInstance(jfoto.getWidth(), jfoto.getHeight(), Image.SCALE_DEFAULT);
           jfoto.setIcon(new ImageIcon(foto));
         }catch(Exception e){
                
         }
       
    }

    public JanelaCadastroProduto(java.awt.Frame parent, boolean modal, Produto produto) {
        super(parent, modal);
        initComponents();
        this.banco = new FachadaPersistencia();
        
        if(FachadaControle.getFachadaControle().getUsuarioLogado().getTipo().equals(Usuario.TIPO_VENDEDOR)){
            jbutaoSalvar.setEnabled(false);
        }
        
        if(FachadaControle.getFachadaControle().getUsuarioLogado().getTipo().equals(Usuario.TIPO_VENDEDOR)){
            jbutaoSalvar.setEnabled(false);
            jbutaoexcluir1.setEnabled(false);
            jbutaoexcluir2.setEnabled(false);
            jbutaoexcluir3.setEnabled(false);
            jbutaomais1.setEnabled(false);
            jbutaomais2.setEnabled(false);
            jbutaomais3.setEnabled(false);
            jbutaomais4.setEnabled(false);
        }
        this.pai = (TelaPrincipal) parent;
        this.setLocationRelativeTo(null);
        this.cadastro = true;

        this.jcodigo.requestFocus();
//        this.jbutaoNovo.setEnabled(false);
        this.similares = produto.getSimilares();
        excluidos = new HashSet<>();
        this.produto = produto;
        
        //jcodigo.setEnabled(false);
        
        jcodigo.setText(produto.getCodigoBarra()+"");
        jcodigoproduto.setText(produto.getCodigoproduto());
        
        jdescricao.setText(produto.getDescricao());
        if(produto.getFabricante() != null)
            jfabricante.setText(produto.getFabricante().getNome());
        jestoqueAtual.setText(produto.getEstoqueAtual()+"");
        jestoqueMinimo.setText(produto.getEstoqueMinimo()+"");
        jprecoCusto.setText(produto.getPrecoCusto()+"");
        jprecoVenda.setText(produto.getPrecoVenda()+"");
        
        //Adicionando os grupos
        DefaultTableModel modelogrupos = (DefaultTableModel) jtabelagrupos.getModel();
        for (Grupo grupo : this.produto.getGrupo()) {
            Grupo[] g = new Grupo[1];
            g[0] = grupo;
            modelogrupos.addRow(g);
        }
        
        //Adicionando os fornecedores
        DefaultTableModel modeloforn = (DefaultTableModel) jtabelafornecedor.getModel();
        for (Produto_fornecedor forn : this.produto.getFornecedor()) {
            String[] f = new String[2];
            f[0] = forn.getCodigoFornecedor();
            f[1] = forn.toString();
            modeloforn.addRow(f);
        }
        
        //Adicionando similares
        DefaultTableModel modelosim = (DefaultTableModel) jtabelasimilar.getModel();
        for (Produto pro : this.produto.getSimilares()) {
            Produto[] s = new Produto[1];
            s[0] = pro;
            modelosim.addRow(s);
        }
      
        try{
            //Exibindo a imagem no JLabelprod
           jfoto.setIcon(null);
           ImageIcon img = new ImageIcon(produto.getFoto());
           Image foto = img.getImage().getScaledInstance(jfoto.getWidth(), jfoto.getHeight(), Image.SCALE_DEFAULT);
           jfoto.setIcon(new ImageIcon(foto));
         }catch(Exception e){
                
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jdescricao = new javax.swing.JTextField();
        jestoqueMinimo = new javax.swing.JTextField();
        jprecoCusto = new javax.swing.JTextField();
        jprecoVenda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jcodigo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jfoto = new javax.swing.JLabel();
        jbutaomais2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jcodigoproduto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jfabricante = new javax.swing.JTextField();
        jbutaomais1 = new javax.swing.JButton();
        jestoqueAtual = new javax.swing.JTextField();
        jbutaoSalvar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtabelagrupos = new javax.swing.JTable();
        jbutaomais3 = new javax.swing.JButton();
        jbutaoexcluir1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtabelafornecedor = new javax.swing.JTable();
        jbutaomais4 = new javax.swing.JButton();
        jbutaoexcluir2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jbutaomais5 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtabelasimilar = new javax.swing.JTable();
        jbutaoexcluir3 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CADASTRO DE PRODUTO");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Produto"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("DESCRIÇÃO:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("ESTOQUE ATUAL:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("ESTOQUE MÍNIMO:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("PREÇO DE CUSTO:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("PREÇO DE VENDA:");

        jdescricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jdescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdescricaoActionPerformed(evt);
            }
        });
        jdescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jdescricaoKeyPressed(evt);
            }
        });

        jestoqueMinimo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jestoqueMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jestoqueMinimoKeyPressed(evt);
            }
        });

        jprecoCusto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jprecoCusto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jprecoCustoKeyPressed(evt);
            }
        });

        jprecoVenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jprecoVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jprecoVendaKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("CÓDIGO DE BARRAS:");

        jcodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcodigoKeyPressed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Foto"));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jfoto, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jfoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jbutaomais2.setText("...");
        jbutaomais2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaomais2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("CÓDIGO DO PRODUTO:");

        jcodigoproduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcodigoproduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcodigoprodutoKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("FABRICANTE (MARCA):");

        jfabricante.setEditable(false);
        jfabricante.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jbutaomais1.setText("...");
        jbutaomais1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaomais1ActionPerformed(evt);
            }
        });

        jestoqueAtual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jestoqueAtual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jestoqueAtualKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jprecoCusto, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                    .addComponent(jestoqueAtual))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jestoqueMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jprecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jfabricante))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbutaomais1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcodigoproduto)
                            .addComponent(jdescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                            .addComponent(jcodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbutaomais2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jcodigoproduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jestoqueMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jestoqueAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jprecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jprecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbutaomais2)
                    .addComponent(jLabel9)
                    .addComponent(jfabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbutaomais1))
                .addContainerGap())
        );

        jbutaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Salvar.png"))); // NOI18N
        jbutaoSalvar.setText("SALVAR");
        jbutaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaoSalvarActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Parar.png"))); // NOI18N
        jButton4.setText("FECHAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Grupos"));

        jtabelagrupos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtabelagrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GRUPOS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabelagrupos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtabelagrupos);
        if (jtabelagrupos.getColumnModel().getColumnCount() > 0) {
            jtabelagrupos.getColumnModel().getColumn(0).setResizable(false);
        }

        jbutaomais3.setText("...");
        jbutaomais3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaomais3ActionPerformed(evt);
            }
        });

        jbutaoexcluir1.setText("Excluir");
        jbutaoexcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaoexcluir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbutaoexcluir1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbutaomais3)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbutaomais3)
                    .addComponent(jbutaoexcluir1)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Fornecedores"));
        jPanel3.setPreferredSize(new java.awt.Dimension(286, 248));

        jtabelafornecedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtabelafornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "FORNECEDOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabelafornecedor.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jtabelafornecedor);
        if (jtabelafornecedor.getColumnModel().getColumnCount() > 0) {
            jtabelafornecedor.getColumnModel().getColumn(0).setResizable(false);
            jtabelafornecedor.getColumnModel().getColumn(0).setPreferredWidth(5);
            jtabelafornecedor.getColumnModel().getColumn(1).setResizable(false);
            jtabelafornecedor.getColumnModel().getColumn(1).setPreferredWidth(80);
        }

        jbutaomais4.setText("...");
        jbutaomais4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaomais4ActionPerformed(evt);
            }
        });

        jbutaoexcluir2.setText("Excluir");
        jbutaoexcluir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaoexcluir2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbutaoexcluir2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbutaomais4)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbutaomais4)
                    .addComponent(jbutaoexcluir2)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos Similares"));

        jbutaomais5.setText("...");
        jbutaomais5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaomais5ActionPerformed(evt);
            }
        });

        jtabelasimilar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtabelasimilar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SIMILARES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabelasimilar.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jtabelasimilar);
        if (jtabelasimilar.getColumnModel().getColumnCount() > 0) {
            jtabelasimilar.getColumnModel().getColumn(0).setResizable(false);
        }

        jbutaoexcluir3.setText("Excluir");
        jbutaoexcluir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutaoexcluir3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbutaoexcluir3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbutaomais5)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbutaomais5)
                    .addComponent(jbutaoexcluir3)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbutaoSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jbutaoSalvar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jbutaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaoSalvarActionPerformed
        
        if(!jfabricante.getText().isEmpty() && !jestoqueAtual.getText().isEmpty() && !jestoqueMinimo.getText().isEmpty() && !jprecoCusto.getText().isEmpty() && !jprecoVenda.getText().isEmpty()){
            try{
            
            jdescricao.setText(jdescricao.getText());
            produto.setDescricao(jdescricao.getText());
            produto.setCodigoBarra(jcodigo.getText());
            produto.setCodigoproduto(jcodigoproduto.getText());
            produto.setEstoqueAtual(Integer.parseInt(jestoqueAtual.getText()));
            produto.setEstoqueMinimo(Integer.parseInt(jestoqueMinimo.getText()));
            produto.setPrecoCusto(Double.parseDouble(jprecoCusto.getText().replaceAll(",", ".")));
            produto.setPrecoVenda(Double.parseDouble(jprecoVenda.getText().replaceAll(",", ".")));
            produto.setStatus(Produto.ATIVO);
            
            if(arquivo != null){
                
                try {
                    
                    
                    BufferedImage imagem = ImageIO.read(arquivo);  
                    int new_w = 512, new_h = 384;
                    BufferedImage new_img = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = new_img.createGraphics();
                    g.drawImage(imagem, 0, 0, new_w, new_h, null);
                    ImageIO.write(new_img, "JPG", new File("temp.jpg"));
//
                    File arq = new File("temp.jpg");
                   byte[] dadosimagem = new byte[(int) arq.length()];
                    
                   FileInputStream fileInputStream = new FileInputStream(arq);
                   fileInputStream.read(dadosimagem);
                   fileInputStream.close();
                   
                   
                   
                   File file = new File("temp.jpg");  
                   file.delete(); 
                    
                   produto.setFoto(dadosimagem); 
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //produto.setNomeFoto(produto.getCodigoBarra()+".jpg");
                
            }
            //Fim da Pegando a Foto
            
            boolean fechar = false;
            if(produto.getCodigo() != 0){
                fechar = true;
            }
               
                
                //this.similaridade(produto);
                
                Produto p = FachadaControle.getFachadaControle().salvaProduto(produto);
                
                p = FachadaControle.getFachadaControle().getProduto(p.getCodigoBarra());
                
                for(Produto exc : excluidos){
                    FachadaControle.getFachadaControle().salvaProduto(exc);
                }
                for (Produto similar : similares) {
                    processarSimilar(p, similar);
                }
                
                
                JOptionPane.showMessageDialog(null, "Produto cadastro com sucesso!");
                if(fechar){
                    this.dispose();
                }
                this.novoproduto();
                //this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                if(ex.getMessage().contains("could not execute statement")){
                    JOptionPane.showMessageDialog(null, "Já existe um produto cadastrado com o código de barra "+jcodigo.getText());
                    //JOptionPane.showMessageDialog(null, ex.getMessage());
                    this.jcodigo.requestFocus();
                }if(ex.getMessage().contains("For input string")){
                    JOptionPane.showMessageDialog(null, "Dados incorretos adicionados.");
                }else{
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } 
        }else{
            JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
        }
    }//GEN-LAST:event_jbutaoSalvarActionPerformed

    private void ClearTabela(JTable jtabela){
        DefaultTableModel model = (DefaultTableModel) jtabela.getModel();
        int tam = model.getRowCount();
        for(int i=0;i<tam;i++){
            model.removeRow(0);
        }
    }
    
    /**
     * Fazer os processamentos de forma a manter a integridade na adição de prdutos similares
     */
    private void processarSimilar(Produto prod, Produto similar) throws Exception{
        
       HashSet<Produto> grupoSimilar = new HashSet<>();
       
       
       grupoSimilar.add(prod);
       grupoSimilar.add(similar);
       
       for(Produto simi: prod.getSimilares()){
           grupoSimilar.add(simi);
       }
       
       for(Produto simi: similar.getSimilares()){
           grupoSimilar.add(simi);
       }
       
       for(Produto p : grupoSimilar){
           p.setSimilares(new HashSet<Produto>());
       }
       
       //para cada produto do grupo similar atualizar os similares e salvar
       for(Produto p: grupoSimilar){
           for(Produto s: grupoSimilar){
               if(!p.equals(s)){
                    p.getSimilares().add(s);                   
               }
           }
           FachadaControle.getFachadaControle().salvaProduto(p);
       }
               
    }
    
    
    private void jbutaomais3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaomais3ActionPerformed
        Grupo grupo = new Grupo();
        JanelaConsultaGrupo janela = new JanelaConsultaGrupo(pai, true, grupo);
        janela.setVisible(true);
        if(grupo.getCodigo() != 0){
            produto.getGrupo().add(grupo);
            Grupo[] vg = new Grupo[1];
            vg[0] = grupo;
            DefaultTableModel modelo = (DefaultTableModel) jtabelagrupos.getModel();
            modelo.addRow(vg);
        }
    }//GEN-LAST:event_jbutaomais3ActionPerformed

    private void jprecoVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jprecoVendaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            jfabricante.requestFocus();
        }
    }//GEN-LAST:event_jprecoVendaKeyPressed

    private void jprecoCustoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jprecoCustoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            jprecoVenda.requestFocus();
        }
    }//GEN-LAST:event_jprecoCustoKeyPressed

    private void jestoqueMinimoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jestoqueMinimoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            jprecoCusto.requestFocus();
        }
    }//GEN-LAST:event_jestoqueMinimoKeyPressed

    private void jdescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdescricaoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            jestoqueAtual.requestFocus();
        }
    }//GEN-LAST:event_jdescricaoKeyPressed

    private void jdescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdescricaoActionPerformed

    }//GEN-LAST:event_jdescricaoActionPerformed

    private void jbutaomais4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaomais4ActionPerformed
        //Fornecedor fornecedor = new Fornecedor();
        Produto_fornecedor profor = new Produto_fornecedor();
        
        JanelaInserirFornecedor janela = new JanelaInserirFornecedor(pai, true, profor);
        janela.setVisible(true);
        
//        JanelaConsultaFornecedor janela = new JanelaConsultaFornecedor(pai, true, fornecedor);
//        janela.setVisible(true);
        if(profor.getFornecedor() != null){
            profor.setProduto(produto);
            produto.getFornecedor().add(profor);
            String[] vg = new String[2];
            vg[0] = profor.getCodigoFornecedor();
            vg[1] = profor.toString();
            DefaultTableModel modelo = (DefaultTableModel) jtabelafornecedor.getModel();
            modelo.addRow(vg);
        }
    }//GEN-LAST:event_jbutaomais4ActionPerformed

    private void jcodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcodigoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            jcodigoproduto.requestFocus();
        }
    }//GEN-LAST:event_jcodigoKeyPressed

    private void jbutaomais2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaomais2ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        
            //seta o titulo do jfilechooser
        fileChooser.setDialogTitle("ESCOLHER FOTO");

        /*seta um filtro para exibir os arquivos , o construtor do
        objeto FileNameExtensionFilter  recebe 2 paremetros um nome e uma extensao
        */
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg"));

        //seta a opção de selecionar apenas arquivos
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.CANCEL_OPTION){  

        }   
        else{  
           String caminho = fileChooser.getSelectedFile().getAbsolutePath();           
           arquivo = new File(caminho);
           
           //Exibindo a imagem no JLabel
           ImageIcon img = new ImageIcon(caminho);
           Image foto = img.getImage().getScaledInstance(jfoto.getWidth(), jfoto.getHeight(), Image.SCALE_DEFAULT);
           jfoto.setIcon(new ImageIcon(foto));
           //Fim da Exibição da Imagem no JLabel
           
        }//Fim do Else
    }//GEN-LAST:event_jbutaomais2ActionPerformed

    private void jbutaomais5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaomais5ActionPerformed
        this.buscarProduto();
    }//GEN-LAST:event_jbutaomais5ActionPerformed

    private void jcodigoprodutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcodigoprodutoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            jdescricao.requestFocus();
        }
    }//GEN-LAST:event_jcodigoprodutoKeyPressed

    private void jbutaomais1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaomais1ActionPerformed
        Fabricante fabricante = new Fabricante();
        JanelaConsultaFabricantes janela = new JanelaConsultaFabricantes(pai, true,fabricante);
        janela.setVisible(true);
        if(fabricante.getCodigo() != 0){
            this.produto.setFabricante(fabricante);
            jfabricante.setText(fabricante.getNome());
        }
        
    }//GEN-LAST:event_jbutaomais1ActionPerformed

    private void jestoqueAtualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jestoqueAtualKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            jestoqueMinimo.requestFocus();
        }
    }//GEN-LAST:event_jestoqueAtualKeyPressed

    private void jbutaoexcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaoexcluir1ActionPerformed
        int i = jtabelagrupos.getSelectedRow();
        produto.getGrupo().remove(i);
        excluirItemTabela(jtabelagrupos);
    }//GEN-LAST:event_jbutaoexcluir1ActionPerformed

    private void jbutaoexcluir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaoexcluir2ActionPerformed
        int i = jtabelafornecedor.getSelectedRow();
        produto.getFornecedor().remove(i);
        excluirItemTabela(jtabelafornecedor);
        
//        try {
//            FachadaControle.getFachadaControle().editarProduto(produto);
//            
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "ERRO: "+ex.getMessage());
//        }
        
    }//GEN-LAST:event_jbutaoexcluir2ActionPerformed

    private void jbutaoexcluir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutaoexcluir3ActionPerformed
        int i = jtabelasimilar.getSelectedRow();
        String codigo = ((Produto)jtabelasimilar.getModel().getValueAt(i, 0)).getCodigoBarra();
        try {
            Produto similar = this.banco.buscaProdutoBarra(codigo);
            
            System.out.println("Produto similar encontrado: " + similar.getDescricao() + "  codBarra:" + similar.getCodigoBarra());
             
            similares.remove(similar);
            
            for(Produto p :  similares){
                p.getSimilares().remove(similar);
            }
            similar.setSimilares(new HashSet<Produto>());
            excluidos.add(similar);
            
            System.out.println("exclui: " + produto.getSimilares().remove(similar));
            
            excluirItemTabela(jtabelasimilar);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "não foi possível excluir o produto similar");
        }
        
        
       
    }//GEN-LAST:event_jbutaoexcluir3ActionPerformed

    private void excluirItemTabela(JTable tabela){
        try{
            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            model.removeRow(tabela.getSelectedRow());        // TODO add your handling code here:
        }catch(ArrayIndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null, "Selecione um item para excluir!");
        }
    }
    
    private void novoproduto(){
        jcodigo.setText("");
        jcodigoproduto.setText("");
        jdescricao.setText("");
        jestoqueAtual.setText("");
        jestoqueMinimo.setText("");
        jprecoCusto.setText("");
        jprecoVenda.setText("");
        jfabricante.setText("");
        
        DefaultTableModel model = (DefaultTableModel) jtabelafornecedor.getModel();
        int qtd = model.getRowCount();
        for (int i = 0; i < qtd; i++) {
            model.removeRow(0);
        }
        
        model = (DefaultTableModel) jtabelagrupos.getModel();
        qtd = model.getRowCount();
        for (int i = 0; i < qtd; i++) {
            model.removeRow(0);
        }
        
        model = (DefaultTableModel) jtabelasimilar.getModel();
        qtd = model.getRowCount();
        for (int i = 0; i < qtd; i++) {
            model.removeRow(0);
        }
        
        //Exibindo a imagem no JLabel
        //ImageIcon img = new ImageIcon(caminho);
        //Image foto = img.getImage().getScaledInstance(jfoto.getWidth(), jfoto.getHeight(), Image.SCALE_DEFAULT);
        try{
            //Exibindo a imagem no JLabelprod
           jfoto.setIcon(null);
           arquivo = new File("semfoto.jpg");
           //Exibindo a imagem no JLabel
           ImageIcon img = new ImageIcon("semfoto.jpg");
           
           Image foto = img.getImage().getScaledInstance(jfoto.getWidth(), jfoto.getHeight(), Image.SCALE_DEFAULT);
           jfoto.setIcon(new ImageIcon(foto));
         }catch(Exception e){
                
         }
        
        produto = new Produto();     
        this.similares = new HashSet<Produto>();
        jcodigo.requestFocus();
    }
    
    private void buscarProduto() {
        Produto pdto = new Produto();
        JanelaConsultaProdutosVenda janelaconsulta = new JanelaConsultaProdutosVenda(this.pai, true, pdto);
        janelaconsulta.setVisible(true);
        if(pdto.getCodigo() > 0){
            //produto.getSimilares().add(pdto);
            this.similares.add(pdto);
            Produto[] vg = new Produto[1];
            vg[0] = pdto;
            DefaultTableModel modelo = (DefaultTableModel) jtabelasimilar.getModel();
            modelo.addRow(vg);
        }
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbutaoSalvar;
    private javax.swing.JButton jbutaoexcluir1;
    private javax.swing.JButton jbutaoexcluir2;
    private javax.swing.JButton jbutaoexcluir3;
    private javax.swing.JButton jbutaomais1;
    private javax.swing.JButton jbutaomais2;
    private javax.swing.JButton jbutaomais3;
    private javax.swing.JButton jbutaomais4;
    private javax.swing.JButton jbutaomais5;
    private javax.swing.JTextField jcodigo;
    private javax.swing.JTextField jcodigoproduto;
    private javax.swing.JTextField jdescricao;
    private javax.swing.JTextField jestoqueAtual;
    private javax.swing.JTextField jestoqueMinimo;
    private javax.swing.JTextField jfabricante;
    private javax.swing.JLabel jfoto;
    private javax.swing.JTextField jprecoCusto;
    private javax.swing.JTextField jprecoVenda;
    private javax.swing.JTable jtabelafornecedor;
    private javax.swing.JTable jtabelagrupos;
    private javax.swing.JTable jtabelasimilar;
    // End of variables declaration//GEN-END:variables

   
}
