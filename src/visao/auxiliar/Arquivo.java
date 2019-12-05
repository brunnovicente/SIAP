/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visao.auxiliar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe que permite trabalhar com caminho de arquivos
 * @author Bruno Vicente
 */
public class Arquivo {

    /**
     * Abre uma caixa de diálogo que permite pegar o caminho de um arquivo
     * @return
     */
    public static File pegaArquivo(){
    File file;
    String caminho;
    JFileChooser  j = new JFileChooser();

    //seta o titulo do jfilechooser
    j.setDialogTitle("Seleção de arquivos");

    /*seta um filtro para exibir os arquivos , o construtor do
    objeto FileNameExtensionFilter  recebe 2 paremetros um nome e uma extensao
    */
    j.setFileFilter(new FileNameExtensionFilter("Arquivo de texto", "pdf"));

    //seta a opção de selecionar apenas arquivos
    j.setFileSelectionMode(JFileChooser.FILES_ONLY);

    //exibe o jFilechosser
   j.showOpenDialog(j);

    //a variavel file recebe o caminho absoluto do arquivo
    file = j.getSelectedFile().getAbsoluteFile();
        //JOptionPane.showMessageDialog(rootPane, file);
        //String caminh =  String.valueOf(file);
        // mudando as barras pra que a funçao ache o arquivo
         //caminho = caminh.replace("\\" , "/" );
     return file;
    }

    /**
     * Abre uma janela que permite gravar dados em arquivo especificando o caminho
     * @return
     */
    public static String salvaArquivo(){
        File file;
         String caminho;
        JFileChooser  j = new JFileChooser();

    //seta o titulo do jfilechooser
    j.setDialogTitle("SALVAR RELATÓRIO");

    /*seta um filtro para exibir os arquivos , o construtor do
    objeto FileNameExtensionFilter  recebe 2 paremetros um nome e uma extensao
    */
    j.setFileFilter(new FileNameExtensionFilter("Relatório em PDF", "pdf"));
    
    //seta a opção de selecionar apenas arquivos
    j.setFileSelectionMode(JFileChooser.FILES_ONLY);

    //exibe o jFilechosser
    j.showSaveDialog(j);

    //a variavel file recebe o caminho absoluto do arquivo
    file = j.getSelectedFile().getAbsoluteFile();
        //JOptionPane.showMessageDialog(rootPane, file);
        String c =  String.valueOf(file);
        // mudando as barras pra que a funçao ache o arquivo
         caminho = c.replace("\\" , "/" );
    return caminho;
    }
    
    public static File escreverArquivo(String conteudo, String nomeArquivo) throws FileNotFoundException, IOException {
        File arquivo;
        arquivo = new File(nomeArquivo);
        FileOutputStream fos = new FileOutputStream(arquivo);
        fos.write(conteudo.getBytes());
        fos.close();
        return arquivo;
        }

        /**
         * Ler dados de um arquivo
         */
	public static String lerArquivo(File arq) throws FileNotFoundException, IOException {
        int i;
        String aux = new String();
        FileInputStream fis = new FileInputStream(arq);
        while ((i = fis.read()) != -1) {
            aux = aux + (char) i;
        }
        fis.close();
        return aux;
    }

}
