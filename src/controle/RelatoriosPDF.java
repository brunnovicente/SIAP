/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.entidades.Caixa;
import persistencia.entidades.Cliente;
import persistencia.entidades.Item;
import persistencia.entidades.ItensPedido;
import persistencia.entidades.Movimento;
import persistencia.entidades.Pedido;
import persistencia.entidades.Produto;
import persistencia.entidades.Venda;

/**
 *
 * @author Bruno
 */
public class RelatoriosPDF {
    
    
    
    public File gerarRelatorioNota(Venda venda, String caminho) throws DocumentException, FileNotFoundException, BadElementException, MalformedURLException, IOException{
        caminho = caminho+".pdf";
        
        Document documento = null;
        OutputStream saida = null;
               
        documento = new Document(PageSize.A5.rotate(), 20, 20, 20, 20);
        saida = new FileOutputStream(caminho);
        PdfWriter.getInstance(documento, saida);
        documento.open();
        String tipo = "";
        if(venda.getCodigo() == 0){
            tipo = "ORÇAMENTO";
        }else{
            tipo = "NOTA DA VENDA";
        }
        //CRIANDO O TITULO DA FICHA E DO LOGOTIPO
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
        
        String empresa;
        
        if(FachadaControle.getFachadaControle().getEmpresa() == null){
            empresa = "MINHA EMPRESA";
        }else{
            empresa = FachadaControle.getFachadaControle().getEmpresa().getNome();
        }
        
        Paragraph titulo = new Paragraph(empresa+ "\n"+tipo, fonte);
        titulo.setAlignment(Element.ALIGN_CENTER);
        
        //Pegando o logotipo do jevania
        Image logo = Image.getInstance(this.pegaLogo());
        logo.setAlignment(Element.ALIGN_CENTER);

        //tabela_titulos.setWidthPercentage(100.0f);
        PdfPTable tabela_titulos = new PdfPTable(new float[]{0.10f, 0.90f});
        //tabela_titulos.setWidthPercentage(100.0f);
        PdfPCell celula1 = new PdfPCell();
        celula1.setBorderColor(BaseColor.WHITE);
        celula1.setImage(logo);
        celula1.setFixedHeight(20);
        
        PdfPCell celula2 = new PdfPCell(titulo);
        celula2.setBorderColor(BaseColor.WHITE);
        tabela_titulos.addCell(celula1);
        tabela_titulos.addCell(celula2);
        tabela_titulos.setHorizontalAlignment(Element.ALIGN_LEFT);
        documento.add(tabela_titulos);

        //CriaÃ§Ã£o da tabela de Clientes
        fonte = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        PdfPTable tabela_dados = new PdfPTable(new float[]{
                    0.13f, 0.11f, 0.09f, 0.27f, 0.13f, 0.27f});
        tabela_dados.setWidthPercentage(100.0f);
        tabela_dados.setSpacingBefore(5); 
        
        Paragraph lcodigoVenda = new Paragraph("Cód. Venda:", fonte);
        Paragraph ldata = new Paragraph("Data:", fonte);
        Paragraph lvendedor = new Paragraph("Vendedor:", fonte);
        
       
        Paragraph codigoVenda = new Paragraph(venda.getCodigo()+"", fonte);
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        String dat = df.format(venda.getData());
        Paragraph data = new Paragraph(dat, fonte);
        Paragraph vendedor = new Paragraph(venda.getVendedor().getLogin(), fonte);
                
        PdfPCell cel_codigovenda = new PdfPCell(codigoVenda);
        cel_codigovenda.setBorderColor(BaseColor.BLACK);
        cel_codigovenda.setBorderWidthBottom(0);
        cel_codigovenda.setBorderWidthLeft(0);
        cel_codigovenda.setBorderWidthRight(0);
        
        PdfPCell cel_data = new PdfPCell(data);
        cel_data.setBorderColor(BaseColor.BLACK);
        cel_data.setBorderWidthBottom(0);
        cel_data.setBorderWidthLeft(0);
        cel_data.setBorderWidthRight(0);
        
        PdfPCell cel_vendedor = new PdfPCell(vendedor);
        cel_vendedor.setBorderColor(BaseColor.BLACK);
        cel_vendedor.setBorderWidthBottom(0);
        cel_vendedor.setBorderWidthLeft(0);
        cel_vendedor.setBorderWidthRight(0);
       
       
        PdfPCell cel_lcodigovenda = new PdfPCell(lcodigoVenda);
        cel_lcodigovenda.setBorderColor(BaseColor.BLACK);
        cel_lcodigovenda.setBorderWidthBottom(0);
        cel_lcodigovenda.setBorderWidthLeft(0);
        cel_lcodigovenda.setBorderWidthRight(0);
        //cel_lcodigovenda.setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        PdfPCell cel_ldata = new PdfPCell(ldata);
        cel_ldata.setBorderColor(BaseColor.BLACK);
        cel_ldata.setBorderWidthBottom(0);
        cel_ldata.setBorderWidthLeft(0);
        cel_ldata.setBorderWidthRight(0);
        
        PdfPCell cel_lvendedor = new PdfPCell(lvendedor);
        cel_lvendedor.setBorderColor(BaseColor.BLACK);
        cel_lvendedor.setBorderWidthBottom(0);
        cel_lvendedor.setBorderWidthLeft(0);
        cel_lvendedor.setBorderWidthRight(0);
        
        
        tabela_dados.addCell(cel_lcodigovenda);
        tabela_dados.addCell(cel_codigovenda);
        
        tabela_dados.addCell(cel_ldata);
        tabela_dados.addCell(cel_data);
        
        tabela_dados.addCell(cel_lvendedor);
        tabela_dados.addCell(cel_vendedor);
        
       
        documento.add(tabela_dados);
        
        //CriaÃ§Ã£o da Tabela de Itens
        fonte = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        PdfPTable tabela_itens = new PdfPTable(new float[]{
                    0.10f, 0.60f, 0.10f, 0.10f, 0.10f});
        tabela_itens.setWidthPercentage(100.0f);
        tabela_itens.setSpacingBefore(8); 
       
        Paragraph lcodigo = new Paragraph("Cod.", fonte);
        Paragraph ldescricao = new Paragraph("Descrição", fonte);
        Paragraph lquantidade = new Paragraph("Qtd", fonte);
        Paragraph lpreco = new Paragraph("Preço", fonte);
        Paragraph ltotal = new Paragraph("Total", fonte);
        
        PdfPCell cel_codigo = new PdfPCell(lcodigo);
        cel_codigo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_codigo.setBorderColor(BaseColor.BLACK);
        //cel_codigo.setBorderWidthTop(0);
        cel_codigo.setBorderWidthLeft(0);
        cel_codigo.setBorderWidthRight(0);
        
      
        PdfPCell cel_descricao = new PdfPCell(ldescricao);
        cel_descricao.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_descricao.setBorderColor(BaseColor.BLACK);
        //cel_descricao.setBorderWidthTop(0);
        cel_descricao.setBorderWidthLeft(0);
        cel_descricao.setBorderWidthRight(0);
        
        PdfPCell cel_quantidade = new PdfPCell(lquantidade);
        cel_quantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_quantidade.setBorderColor(BaseColor.BLACK);
        //cel_quantidade.setBorderWidthTop(0);
        cel_quantidade.setBorderWidthLeft(0);
        cel_quantidade.setBorderWidthRight(0);
        
        PdfPCell cel_preco = new PdfPCell(lpreco);
        cel_preco.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_preco.setBorderColor(BaseColor.BLACK);
        //cel_preco.setBorderWidthTop(0);
        cel_preco.setBorderWidthLeft(0);
        cel_preco.setBorderWidthRight(0);
        
        PdfPCell cel_total = new PdfPCell(ltotal);
        cel_total.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_total.setBorderColor(BaseColor.BLACK);
        //cel_total.setBorderWidthTop(0);
        cel_total.setBorderWidthLeft(0);
        cel_total.setBorderWidthRight(0);
        
               
        tabela_itens.addCell(cel_codigo);
        tabela_itens.addCell(cel_descricao);
        tabela_itens.addCell(cel_quantidade);
        tabela_itens.addCell(cel_preco);
        tabela_itens.addCell(cel_total);
                
        documento.add(tabela_itens);
        
        //CriaÃ§Ã£o da Tabela da DescriÃ§Ã£o dos itens
        PdfPTable tabela_lista_itens = new PdfPTable(new float[]{
                    0.10f, 0.60f, 0.10f, 0.10f, 0.10f});
        tabela_lista_itens.setWidthPercentage(100.0f);
        tabela_lista_itens.setSpacingBefore(5); 
        
        fonte = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
        //Adicionando os itens na tabela
        for (Item item : venda.getItens()) {
            
            Paragraph codigo = new Paragraph(item.getProduto().getCodigo()+"",fonte);
            Paragraph descricao = new Paragraph(item.getProduto().getDescricao()+"",fonte);
            Paragraph quantidade = new Paragraph(item.getQuantidade()+"",fonte);
            Paragraph preco = new Paragraph(("R$ "+item.getPreco()+"").replace(".", ","),fonte);
            Paragraph total = new Paragraph(("R$ "+item.getTotal()+"").replace(".", ","),fonte);
            
            PdfPCell cel_cod = new PdfPCell(codigo);
            cel_cod.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel_cod.setBorderColor(BaseColor.WHITE);
            
           
            PdfPCell cel_desc = new PdfPCell(descricao);
            cel_desc.setHorizontalAlignment(Element.ALIGN_LEFT);
            cel_desc.setBorderColor(BaseColor.WHITE);
            
            PdfPCell cel_qtd = new PdfPCell(quantidade);
            cel_qtd.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel_qtd.setBorderColor(BaseColor.WHITE);
            
            PdfPCell cel_pre = new PdfPCell(preco);
            cel_pre.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel_pre.setBorderColor(BaseColor.WHITE);
            
            PdfPCell cel_tot = new PdfPCell(total);
            cel_tot.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel_tot.setBorderColor(BaseColor.WHITE);
            
            tabela_lista_itens.addCell(cel_cod);
            tabela_lista_itens.addCell(cel_desc);
            tabela_lista_itens.addCell(cel_qtd);
            tabela_lista_itens.addCell(cel_pre);
            tabela_lista_itens.addCell(cel_tot);
            
        }//Fim do for dos Itens
               
        documento.add(tabela_lista_itens);
        
        //CriaÃ§Ã£o da Tabela do Final
        PdfPTable tabela_final = new PdfPTable(new float[]{0.25f,0.25f,0.25f,0.25f});
        tabela_final.setWidthPercentage(100.0f);
        tabela_final.setSpacingBefore(240-(venda.getItens().size()*20)); 
        
        Paragraph ltotalitens = new Paragraph("Total Itens: ",fonte);
        //Paragraph lpagamento = new Paragraph("Pagamento: ",fonte);
        Paragraph ltotalfinal = new Paragraph("Total Pedido: ",fonte);
        
        PdfPCell cel_ltotalitens = new PdfPCell(ltotalitens);
        cel_ltotalitens.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cel_ltotalitens.setBorderColor(BaseColor.BLACK);
        cel_ltotalitens.setBorderWidthBottom(0);
        cel_ltotalitens.setBorderWidthLeft(0);
        cel_ltotalitens.setBorderWidthRight(0);
            
//        PdfPCell cel_lpagamento = new PdfPCell(lpagamento);
//        cel_lpagamento.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        cel_lpagamento.setBorderColor(BaseColor.WHITE);
        
        PdfPCell cel_ltotalfinal = new PdfPCell(ltotalfinal);
        cel_ltotalfinal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cel_ltotalfinal.setBorderColor(BaseColor.BLACK);
        cel_ltotalfinal.setBorderWidthBottom(0);
        cel_ltotalfinal.setBorderWidthLeft(0);
        cel_ltotalfinal.setBorderWidthRight(0);
        
        Paragraph totalitens = new Paragraph(venda.getItens().size()+"",fonte);
        //Paragraph pagamento = new Paragraph("Ã€ Vista",fonte);
        Paragraph totalfinal = new Paragraph(("R$ "+venda.getTotal()+"").replace(".", ","),fonte);
        
        PdfPCell cel_totalitens = new PdfPCell(totalitens);
        cel_totalitens.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_totalitens.setBorderColor(BaseColor.BLACK);
        cel_totalitens.setBorderWidthBottom(0);
        cel_totalitens.setBorderWidthLeft(0);
        cel_totalitens.setBorderWidthRight(0);
            
//        PdfPCell cel_pagamento = new PdfPCell(pagamento);
//        cel_pagamento.setHorizontalAlignment(Element.ALIGN_LEFT);
//        cel_pagamento.setBorderColor(BaseColor.WHITE);
//        
        PdfPCell cel_totalfinal = new PdfPCell(totalfinal);
        cel_totalfinal.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_totalfinal.setBorderColor(BaseColor.BLACK);
        cel_totalfinal.setBorderWidthBottom(0);
        cel_totalfinal.setBorderWidthLeft(0);
        cel_totalfinal.setBorderWidthRight(0);
        
        //Paragraph p_vazio = new Paragraph("");
        //PdfPCell vazia = new PdfPCell(p_vazio);
        
        tabela_final.addCell(cel_ltotalitens);
        tabela_final.addCell(cel_totalitens);
        tabela_final.addCell(cel_ltotalfinal);
        tabela_final.addCell(cel_totalfinal);
//        tabela_final.addCell(cel_lpagamento);
//        tabela_final.addCell(cel_pagamento);
        //tabela_final.addCell(vazia);
        //tabela_final.addCell(vazia);
        
        documento.add(tabela_final);
        
        //CriaÃ§Ã£o da Ãšltima Linha
        PdfPTable tabela_ultima = new PdfPTable(new float[]{0.100f});
        tabela_ultima.setWidthPercentage(100.0f);
        tabela_ultima.setSpacingBefore(5); 
        
        Paragraph pcelulafinal = new Paragraph("");
        PdfPCell celulafinal = new PdfPCell(pcelulafinal);
        celulafinal.setBorderColor(BaseColor.BLACK);
        celulafinal.setBorderWidthBottom(0);
        celulafinal.setBorderWidthLeft(0);
        celulafinal.setBorderWidthRight(0);
        tabela_ultima.addCell(celulafinal);
        
        documento.add(tabela_ultima);
        
        File file = new File(caminho);
        documento.close();
        saida.close();
        
        return file;

    }//Fim da Gera Relatorio
    
    public File gerarRelatorioCaixa(List<Caixa> lista) throws FileNotFoundException, DocumentException, BadElementException, IOException{
        String caminho = "relatoriocaixa.pdf";
        Document documento = null;
        OutputStream saida = null;
               
        documento = new Document(PageSize.A4, 20, 20, 20, 20);
        saida = new FileOutputStream(caminho);
        PdfWriter.getInstance(documento, saida);
        documento.open();
        
        //CRIANDO O TITULO DA FICHA E DO LOGOTIPO
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
        
        String empresa;
        
        if(FachadaControle.getFachadaControle().getEmpresa() == null){
            empresa = "MINHA EMPRESA";
        }else{
            empresa = FachadaControle.getFachadaControle().getEmpresa().getNome();
        }
        
        Paragraph titulo = new Paragraph(empresa
                + "\nRelatório de Vendas\n", fonte);
        titulo.setAlignment(Element.ALIGN_CENTER);

        //Pegando o logotipo do jevania
        Image logo = Image.getInstance(this.pegaLogo());
        logo.setAlignment(Element.ALIGN_CENTER);
        
        PdfPTable tabela_titulos = new PdfPTable(new float[]{0.10f, 0.90f});
        //tabela_titulos.setWidthPercentage(100.0f);
        PdfPCell celula1 = new PdfPCell();
        celula1.setBorderColor(BaseColor.WHITE);
        celula1.setImage(logo);
        celula1.setFixedHeight(20);
        
        PdfPCell celula2 = new PdfPCell(titulo);
        celula2.setBorderColor(BaseColor.WHITE);
        tabela_titulos.addCell(celula1);
        tabela_titulos.addCell(celula2);
        tabela_titulos.setHorizontalAlignment(Element.ALIGN_LEFT);
        documento.add(tabela_titulos);
        
        //Criação da Tabela de dados de títulos
        PdfPTable tabela_dados_titulos = new PdfPTable(new float[]{0.10f, 0.10f, 0.10f, 0.10f, 0.10f, 0.10f, 0.10f});
        tabela_dados_titulos.setWidthPercentage(100.0f);
        tabela_dados_titulos.setSpacingBefore(5);
        
        Paragraph p1 = new Paragraph("Cod.");
        Paragraph p2 = new Paragraph("Data");
        Paragraph p3 = new Paragraph("Inicial");
        Paragraph p4 = new Paragraph("Final");
        Paragraph p5 = new Paragraph("Cartão");
        Paragraph p6 = new Paragraph("Dinheiro");
        Paragraph p7 = new Paragraph("Fatur.");
        
        
        PdfPCell cel1 = new PdfPCell(p1);
        cel1.setBorderColor(BaseColor.BLACK);
        cel1.setBorderWidthTop(1);
        cel1.setBorderWidthLeft(0);
        cel1.setBorderWidthRight(0);
        
        PdfPCell cel2 = new PdfPCell(p2);
        cel2.setBorderColor(BaseColor.BLACK);
        cel2.setBorderWidthTop(1);
        cel2.setBorderWidthLeft(0);
        cel2.setBorderWidthRight(0);
        
        PdfPCell cel3 = new PdfPCell(p3);
        cel3.setBorderColor(BaseColor.BLACK);
        cel3.setBorderWidthTop(1);
        cel3.setBorderWidthLeft(0);
        cel3.setBorderWidthRight(0);
        
        PdfPCell cel4 = new PdfPCell(p4);
        cel4.setBorderColor(BaseColor.BLACK);
        cel4.setBorderWidthTop(1);
        cel4.setBorderWidthLeft(0);
        cel4.setBorderWidthRight(0);
        
        PdfPCell cel5 = new PdfPCell(p5);
        cel5.setBorderColor(BaseColor.BLACK);
        cel5.setBorderWidthTop(1);
        cel5.setBorderWidthLeft(0);
        cel5.setBorderWidthRight(0);
        
        PdfPCell cel6 = new PdfPCell(p6);
        cel6.setBorderColor(BaseColor.BLACK);
        cel6.setBorderWidthTop(1);
        cel6.setBorderWidthLeft(0);
        cel6.setBorderWidthRight(0);
        
        PdfPCell cel7 = new PdfPCell(p7);
        cel7.setBorderColor(BaseColor.BLACK);
        cel7.setBorderWidthTop(1);
        cel7.setBorderWidthLeft(0);
        cel7.setBorderWidthRight(0);
        
        tabela_dados_titulos.addCell(cel1);
        tabela_dados_titulos.addCell(cel2);
        tabela_dados_titulos.addCell(cel3);
        tabela_dados_titulos.addCell(cel4);
        tabela_dados_titulos.addCell(cel5);
        tabela_dados_titulos.addCell(cel6);
        tabela_dados_titulos.addCell(cel7);
        
        documento.add(tabela_dados_titulos);
        
        PdfPTable tabela_dados = new PdfPTable(new float[]{0.10f, 0.10f, 0.10f, 0.10f, 0.10f, 0.10f, 0.10f});
           tabela_dados.setWidthPercentage(100.0f);
           tabela_dados.setSpacingBefore(5);
        SimpleDateFormat formatodata = new SimpleDateFormat("dd/MM/yyyy");   
        for (Caixa caixa : lista) {
                //Criação da Tabela de dados de títulos
           

           Paragraph p1cod = new Paragraph(caixa.getCodigo()+"");
           Paragraph p2dat = new Paragraph(formatodata.format(caixa.getDataInicial()));
           Paragraph p3ini = new Paragraph(("R$ "+caixa.getCaixaInicial()).replace(".", ","));
           Paragraph p4fin = new Paragraph(("R$ "+caixa.getCaixaFinal()).replace(".", ","));
           Paragraph p5car = new Paragraph(("R$ "+caixa.getTotalCartao()).replace(".", ","));
           Paragraph p6din = new Paragraph(("R$ "+caixa.getTotalDinheiro()).replace(".", ","));
           Paragraph p7fat = new Paragraph(("R$ "+(caixa.getCaixaFinal() - caixa.getCaixaInicial())).replace(".", ","));
           
           PdfPCell cel_cod = new PdfPCell(p1cod);
            cel_cod.setBorderColor(BaseColor.BLACK);
            cel_cod.setBorderWidthTop(0);
            cel_cod.setBorderWidthLeft(0);
            cel_cod.setBorderWidthRight(0);
           
            PdfPCell cel_dat = new PdfPCell(p2dat);
            cel_dat.setBorderColor(BaseColor.BLACK);
            cel_dat.setBorderWidthTop(0);
            cel_dat.setBorderWidthLeft(0);
            cel_dat.setBorderWidthRight(0);
            
            PdfPCell cel_ini = new PdfPCell(p3ini);
            cel_ini.setBorderColor(BaseColor.BLACK);
            cel_ini.setBorderWidthTop(0);
            cel_ini.setBorderWidthLeft(0);
            cel_ini.setBorderWidthRight(0);
            
            PdfPCell cel_fin = new PdfPCell(p4fin);
            cel_fin.setBorderColor(BaseColor.BLACK);
            cel_fin.setBorderWidthTop(0);
            cel_fin.setBorderWidthLeft(0);
            cel_fin.setBorderWidthRight(0);
            
            PdfPCell cel_car = new PdfPCell(p5car);
            cel_car.setBorderColor(BaseColor.BLACK);
            cel_car.setBorderWidthTop(0);
            cel_car.setBorderWidthLeft(0);
            cel_car.setBorderWidthRight(0);
            
            PdfPCell cel_din = new PdfPCell(p6din);
            cel_din.setBorderColor(BaseColor.BLACK);
            cel_din.setBorderWidthTop(0);
            cel_din.setBorderWidthLeft(0);
            cel_din.setBorderWidthRight(0);
            
            PdfPCell cel_fat = new PdfPCell(p7fat);
            cel_fat.setBorderColor(BaseColor.BLACK);
            cel_fat.setBorderWidthTop(0);
            cel_fat.setBorderWidthLeft(0);
            cel_fat.setBorderWidthRight(0);
            
            tabela_dados.addCell(cel_cod);
            tabela_dados.addCell(cel_dat);
            tabela_dados.addCell(cel_ini);
            tabela_dados.addCell(cel_fin);
            tabela_dados.addCell(cel_car);
            tabela_dados.addCell(cel_din);
            tabela_dados.addCell(cel_fat);
           
        }
        
        
        
        documento.add(tabela_dados);
        
        File file = new File(caminho);
        documento.close();
        saida.close();
        
        return file;
    }
    
    public File gerarRelatorioMovimentos(List<Movimento> lista) throws FileNotFoundException, DocumentException, BadElementException, IOException{
        String caminho = "relatoriomovimento.pdf";
        Document documento = null;
        OutputStream saida = null;
               
        documento = new Document(PageSize.A4, 20, 20, 20, 20);
        saida = new FileOutputStream(caminho);
        PdfWriter.getInstance(documento, saida);
        documento.open();
        
        //CRIANDO O TITULO DA FICHA E DO LOGOTIPO
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
        
        String empresa;
        
        if(FachadaControle.getFachadaControle().getEmpresa() == null){
            empresa = "MINHA EMPRESA";
        }else{
            empresa = FachadaControle.getFachadaControle().getEmpresa().getNome();
        }
        
        Paragraph titulo = new Paragraph(empresa
                + "\nRelatório de Entradas e Saídas\n", fonte);
        titulo.setAlignment(Element.ALIGN_CENTER);

        //Pegando o logotipo do jevania
        Image logo = Image.getInstance(this.pegaLogo());
        logo.setAlignment(Element.ALIGN_CENTER);
        
        PdfPTable tabela_titulos = new PdfPTable(new float[]{0.10f, 0.90f});
        //tabela_titulos.setWidthPercentage(100.0f);
        PdfPCell celula1 = new PdfPCell();
        celula1.setBorderColor(BaseColor.WHITE);
        celula1.setImage(logo);
        celula1.setFixedHeight(20);
        
        PdfPCell celula2 = new PdfPCell(titulo);
        celula2.setBorderColor(BaseColor.WHITE);
        tabela_titulos.addCell(celula1);
        tabela_titulos.addCell(celula2);
        tabela_titulos.setHorizontalAlignment(Element.ALIGN_LEFT);
        documento.add(tabela_titulos);
        
        //Criação da Tabela de dados de títulos
        PdfPTable tabela_dados_titulos = new PdfPTable(new float[]{0.40f, 0.05f, 0.15f, 0.15f, 0.30f});
        tabela_dados_titulos.setWidthPercentage(100.0f);
        tabela_dados_titulos.setSpacingBefore(5);
        
        Paragraph p1 = new Paragraph("Produto");
        Paragraph p2 = new Paragraph("Qtd");
        Paragraph p3 = new Paragraph("Tipo");
        Paragraph p4 = new Paragraph("Data");
        Paragraph p5 = new Paragraph("Usuário");
        
        
        PdfPCell cel1 = new PdfPCell(p1);
        cel1.setBorderColor(BaseColor.BLACK);
        cel1.setBorderWidthTop(1);
        cel1.setBorderWidthLeft(0);
        cel1.setBorderWidthRight(0);
        
        PdfPCell cel2 = new PdfPCell(p2);
        cel2.setBorderColor(BaseColor.BLACK);
        cel2.setBorderWidthTop(1);
        cel2.setBorderWidthLeft(0);
        cel2.setBorderWidthRight(0);
        
        PdfPCell cel3 = new PdfPCell(p3);
        cel3.setBorderColor(BaseColor.BLACK);
        cel3.setBorderWidthTop(1);
        cel3.setBorderWidthLeft(0);
        cel3.setBorderWidthRight(0);
        
        PdfPCell cel4 = new PdfPCell(p4);
        cel4.setBorderColor(BaseColor.BLACK);
        cel4.setBorderWidthTop(1);
        cel4.setBorderWidthLeft(0);
        cel4.setBorderWidthRight(0);
        
        PdfPCell cel5 = new PdfPCell(p5);
        cel5.setBorderColor(BaseColor.BLACK);
        cel5.setBorderWidthTop(1);
        cel5.setBorderWidthLeft(0);
        cel5.setBorderWidthRight(0);
        
        
        tabela_dados_titulos.addCell(cel1);
        tabela_dados_titulos.addCell(cel2);
        tabela_dados_titulos.addCell(cel3);
        tabela_dados_titulos.addCell(cel4);
        tabela_dados_titulos.addCell(cel5);
        
        documento.add(tabela_dados_titulos);
        
        PdfPTable tabela_dados = new PdfPTable(new float[]{0.40f, 0.05f, 0.15f, 0.15f, 0.30f});
           tabela_dados.setWidthPercentage(100.0f);
           tabela_dados.setSpacingBefore(5);
        SimpleDateFormat formatodata = new SimpleDateFormat("dd/MM/yyyy");   
        for (Movimento movimento : lista) {
                //Criação da Tabela de dados de títulos
           

           Paragraph p1produto = new Paragraph(movimento.getProduto().getDescricao());
           Paragraph p2quantidade = new Paragraph(movimento.getQuantidade()+"");
           Paragraph p3tipo = new Paragraph(movimento.getTipo());
           Paragraph p4data = new Paragraph(formatodata.format(movimento.getData()));
           Paragraph p5usuario = new Paragraph(movimento.getCriadopor().getNome());
           
           
           PdfPCell cel_produto = new PdfPCell(p1produto);
            cel_produto.setBorderColor(BaseColor.BLACK);
            cel_produto.setBorderWidthTop(0);
            cel_produto.setBorderWidthLeft(0);
            cel_produto.setBorderWidthRight(0);
           
            PdfPCell cel_quantidade = new PdfPCell(p2quantidade);
            cel_quantidade.setBorderColor(BaseColor.BLACK);
            cel_quantidade.setBorderWidthTop(0);
            cel_quantidade.setBorderWidthLeft(0);
            cel_quantidade.setBorderWidthRight(0);
            
            PdfPCell cel_tipo = new PdfPCell(p3tipo);
            cel_tipo.setBorderColor(BaseColor.BLACK);
            cel_tipo.setBorderWidthTop(0);
            cel_tipo.setBorderWidthLeft(0);
            cel_tipo.setBorderWidthRight(0);
            
            PdfPCell cel_data = new PdfPCell(p4data);
            cel_data.setBorderColor(BaseColor.BLACK);
            cel_data.setBorderWidthTop(0);
            cel_data.setBorderWidthLeft(0);
            cel_data.setBorderWidthRight(0);
            
            PdfPCell cel_usuario = new PdfPCell(p5usuario);
            cel_usuario.setBorderColor(BaseColor.BLACK);
            cel_usuario.setBorderWidthTop(0);
            cel_usuario.setBorderWidthLeft(0);
            cel_usuario.setBorderWidthRight(0);
            
           
            
            tabela_dados.addCell(cel_produto);
            tabela_dados.addCell(cel_quantidade);
            tabela_dados.addCell(cel_tipo);
            tabela_dados.addCell(cel_data);
            tabela_dados.addCell(cel_usuario);
           
        }
        
        
        
        documento.add(tabela_dados);
        
        File file = new File(caminho);
        documento.close();
        saida.close();
        
        return file;
    }
    
    /**
     *
     * @param lista
     * @return
     * @throws DocumentException
     * @throws FileNotFoundException
     * @throws BadElementException, String inicio, String fim
     * @throws MalformedURLException
     * @throws IOException
     */
     public File gerarRelatorioVendas(ArrayList<Venda> lista, String inicio, String fim, String caminho) throws DocumentException, FileNotFoundException, BadElementException, MalformedURLException, IOException{
        //String caminho = caminho;
        SimpleDateFormat formatotodata = new SimpleDateFormat("dd/MM/yyyy");    
        
        caminho = caminho +".pdf";
        double total_vendas = 0;
        //Calculando o Total da vendas
        for(Venda v : lista){
            total_vendas += v.getTotal();
        }
        
        
        Document documento = null;
        OutputStream saida = null;
               
        documento = new Document(PageSize.A4, 20, 20, 20, 20);
        saida = new FileOutputStream(caminho);
        PdfWriter.getInstance(documento, saida);
        documento.open();

        //CRIANDO O TITULO DA FICHA E DO LOGOTIPO
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
        
        String empresa;
        
        if(FachadaControle.getFachadaControle().getEmpresa() == null){
            empresa = "MINHA EMPRESA";
        }else{
            empresa = FachadaControle.getFachadaControle().getEmpresa().getNome();
        }
        
        Paragraph titulo = new Paragraph(empresa
                + "\nRelatório de Vendas\n", fonte);
        titulo.setAlignment(Element.ALIGN_CENTER);

        //Pegando o logotipo do jevania
        Image logo = Image.getInstance(this.pegaLogo());
        logo.setAlignment(Element.ALIGN_CENTER);
        
        PdfPTable tabela_titulos = new PdfPTable(new float[]{0.10f, 0.90f});
        //tabela_titulos.setWidthPercentage(100.0f);
        PdfPCell celula1 = new PdfPCell();
        celula1.setBorderColor(BaseColor.WHITE);
        celula1.setImage(logo);
        celula1.setFixedHeight(20);
        
        PdfPCell celula2 = new PdfPCell(titulo);
        celula2.setBorderColor(BaseColor.WHITE);
        tabela_titulos.addCell(celula1);
        tabela_titulos.addCell(celula2);
        tabela_titulos.setHorizontalAlignment(Element.ALIGN_LEFT);
        documento.add(tabela_titulos);
        
        //Criação da Tabela de dados iniciais
        PdfPTable tabela_dados = new PdfPTable(new float[]{0.12f, 0.43f, 0.27f, 0.18f});
        tabela_dados.setWidthPercentage(100.0f);
        tabela_dados.setSpacingBefore(5);
        
        Paragraph p1 = new Paragraph("PERÍODO: ");
        Paragraph p2 = new Paragraph(inicio + " à "+ fim);
        Paragraph p3 = new Paragraph("TOTAL DO RELATÓRIO: ");
        Paragraph p4 = new Paragraph(("R$ "+total_vendas).replace(".", ","));
        
        PdfPCell cel1 = new PdfPCell(p1);
        cel1.setBorderColor(BaseColor.BLACK);
        cel1.setBorderWidthBottom(0);
        cel1.setBorderWidthLeft(0);
        cel1.setBorderWidthRight(0);
        
        PdfPCell cel2 = new PdfPCell(p2);
        cel2.setBorderColor(BaseColor.BLACK);
        cel2.setBorderWidthBottom(0);
        cel2.setBorderWidthLeft(0);
        cel2.setBorderWidthRight(0);
        
        PdfPCell cel3 = new PdfPCell(p3);
        cel3.setBorderColor(BaseColor.BLACK);
        cel3.setBorderWidthBottom(0);
        cel3.setBorderWidthLeft(0);
        cel3.setBorderWidthRight(0);
        
        PdfPCell cel4 = new PdfPCell(p4);
        cel4.setBorderColor(BaseColor.BLACK);
        cel4.setBorderWidthBottom(0);
        cel4.setBorderWidthLeft(0);
        cel4.setBorderWidthRight(0);
        
        tabela_dados.addCell(cel1);
        tabela_dados.addCell(cel2);
        tabela_dados.addCell(cel3);
        tabela_dados.addCell(cel4);
        
        documento.add(tabela_dados);
        
        for(Venda venda : lista){
            
        //Criação dos dados do cliente
        //CriaÃ§Ã£o da Tabela de Itens
        fonte = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        PdfPTable tabela_dados_cliente = new PdfPTable(new float[]{
                    0.05f,0.05f, 0.21f, 0.28f, 0.21f, 0.20f});
        tabela_dados_cliente.setWidthPercentage(100.0f);
        tabela_dados_cliente.setSpacingBefore(8); 
            
       
        Paragraph lcodigocliente = new Paragraph("Cod.", fonte);
        Paragraph lcodigoclientedado = new Paragraph(venda.getCliente().getCodigo()+"", fonte);
        Paragraph lnomecliente = new Paragraph("CLIENTE:", fonte);
        Paragraph lnomeclientedado = new Paragraph(venda.getCliente().getNome(), fonte);
        Paragraph lapelidocliente = new Paragraph("APELIDO:", fonte);    
        Paragraph lapelidoclientedado = new Paragraph(venda.getCliente().getApelido(), fonte);
        
        PdfPCell cel_codigocliente = new PdfPCell(lcodigocliente);
        cel_codigocliente.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cel_codigocliente.setBorderColor(BaseColor.BLACK);
        //cel_codigocliente.setBorderWidthBottom(0);
        cel_codigocliente.setBorderWidthTop(1);
        cel_codigocliente.setBorderWidthLeft(0);
        cel_codigocliente.setBorderWidthRight(0);
        
        
        PdfPCell cel_codigoclientedado = new PdfPCell(lcodigoclientedado);
        cel_codigoclientedado.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_codigoclientedado.setBorderColor(BaseColor.BLACK);
        cel_codigoclientedado.setBorderWidthTop(1);
        //cel_codigoclientedado.setBorderWidthBottom(0);
        cel_codigoclientedado.setBorderWidthLeft(0);
        cel_codigoclientedado.setBorderWidthRight(0);
        
        
        PdfPCell cel_nomecliente = new PdfPCell(lnomecliente);
        cel_nomecliente.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cel_nomecliente.setBorderColor(BaseColor.BLACK);
        cel_nomecliente.setBorderWidthTop(1);
        //cel_nomecliente.setBorderWidthBottom(0);
        cel_nomecliente.setBorderWidthLeft(0);
        cel_nomecliente.setBorderWidthRight(0);
        
        PdfPCell cel_nomeclientedado = new PdfPCell(lnomeclientedado);
        cel_nomeclientedado.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_nomeclientedado.setBorderColor(BaseColor.BLACK);
        cel_nomeclientedado.setBorderWidthTop(1);
        //cel_nomeclientedado.setBorderWidthBottom(0);
        cel_nomeclientedado.setBorderWidthLeft(0);
        cel_nomeclientedado.setBorderWidthRight(0);
        
        PdfPCell cel_apelidocliente = new PdfPCell(lapelidocliente);
        cel_apelidocliente.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cel_apelidocliente.setBorderColor(BaseColor.BLACK);
        cel_apelidocliente.setBorderWidthTop(1);
        //cel_apelidocliente.setBorderWidthBottom(0);
        cel_apelidocliente.setBorderWidthLeft(0);
        cel_apelidocliente.setBorderWidthRight(0);
        
        PdfPCell cel_apelidoclientedado = new PdfPCell(lapelidoclientedado);
        cel_apelidoclientedado.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_apelidoclientedado.setBorderColor(BaseColor.BLACK);
        cel_apelidoclientedado.setBorderWidthTop(1);
        //cel_apelidoclientedado.setBorderWidthBottom(0);
        cel_apelidoclientedado.setBorderWidthLeft(0);
        cel_apelidoclientedado.setBorderWidthRight(0);
        
       
        tabela_dados_cliente.addCell(cel_codigocliente);
        tabela_dados_cliente.addCell(cel_codigoclientedado);
        tabela_dados_cliente.addCell(cel_nomecliente);
        tabela_dados_cliente.addCell(cel_nomeclientedado);
        tabela_dados_cliente.addCell(cel_apelidocliente);
        tabela_dados_cliente.addCell(cel_apelidoclientedado);
        
        
        documento.add(tabela_dados_cliente);
        
        //CriaÃ§Ã£o da Tabela de Itens
        fonte = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        PdfPTable tabela_itens = new PdfPTable(new float[]{
                    0.10f,0.40f, 0.20f, 0.10f, 0.20f});
        tabela_itens.setWidthPercentage(100.0f);
        //tabela_itens.setSpacingBefore(8); 

        Paragraph lcodigo = new Paragraph("Cod.", fonte);
        Paragraph ldata = new Paragraph("Produto", fonte);
        Paragraph ltotal = new Paragraph("Preço", fonte);
        Paragraph ldesconto = new Paragraph("QTD", fonte);
        Paragraph lpagamento = new Paragraph("Total", fonte);
        
        PdfPCell cel_codigo = new PdfPCell(lcodigo);
        cel_codigo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_codigo.setBorderColor(BaseColor.BLACK);
        cel_codigo.setBorderWidthBottom(0);
        cel_codigo.setBorderWidthTop(0);
        cel_codigo.setBorderWidthLeft(0);
        cel_codigo.setBorderWidthRight(0);


        PdfPCell cel_descricao = new PdfPCell(ldata);
        cel_descricao.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_descricao.setBorderColor(BaseColor.BLACK);
        cel_descricao.setBorderWidthTop(0);
        cel_descricao.setBorderWidthBottom(0);
        cel_descricao.setBorderWidthLeft(0);
        cel_descricao.setBorderWidthRight(0);

        PdfPCell cel_quantidade = new PdfPCell(ltotal);
        cel_quantidade.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_quantidade.setBorderColor(BaseColor.BLACK);
        cel_quantidade.setBorderWidthTop(0);
        cel_quantidade.setBorderWidthBottom(0);
        cel_quantidade.setBorderWidthLeft(0);
        cel_quantidade.setBorderWidthRight(0);

        PdfPCell cel_preco = new PdfPCell(ldesconto);
        cel_preco.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_preco.setBorderColor(BaseColor.BLACK);
        cel_preco.setBorderWidthTop(0);
        cel_preco.setBorderWidthBottom(0);
        cel_preco.setBorderWidthLeft(0);
        cel_preco.setBorderWidthRight(0);

        PdfPCell cel_total = new PdfPCell(lpagamento);
        cel_total.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_total.setBorderColor(BaseColor.BLACK);
        cel_total.setBorderWidthTop(0);
        cel_total.setBorderWidthBottom(0);
        cel_total.setBorderWidthLeft(0);
        cel_total.setBorderWidthRight(0);

        
        tabela_itens.addCell(cel_codigo);
        tabela_itens.addCell(cel_descricao);
        tabela_itens.addCell(cel_quantidade);
        tabela_itens.addCell(cel_preco);
        tabela_itens.addCell(cel_total);
        
        documento.add(tabela_itens);
        
        //PERCORRENDO AS VENDAS
        
            
                       
            //}//Fim do for dos Itens
                
                for (Item item : venda.getItens()) {
                    PdfPTable tabela_lista_itens_venda = new PdfPTable(new float[]{
                         0.10f,0.40f, 0.20f, 0.10f, 0.20f});
                    tabela_lista_itens_venda.setWidthPercentage(100.0f);
                    tabela_lista_itens_venda.setSpacingBefore(5); 

                    fonte = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
                    //Adicionando os itens na tabela
                    //for (Item item : venda.getItens()) {
                    
                    Paragraph cod_produto = new Paragraph(item.getProduto().getCodigo()+"",fonte);
                    Paragraph nome_produto = new Paragraph(item.getProduto().getDescricao()+"",fonte);
                    Paragraph preco_produto = new Paragraph(("R$ "+item.getPreco()+"").replace(".", ","),fonte);
                    Paragraph qtd_produto = new Paragraph(item.getQuantidade()+"",fonte);
                    Paragraph total_produto = new Paragraph(("R$ "+item.getTotal()+"").replace(".", ","),fonte);
                    
                    PdfPCell cel_codigo_produto = new PdfPCell(cod_produto);
                    cel_codigo_produto.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cel_codigo_produto.setBorderColor(BaseColor.WHITE);
                    cel_codigo_produto.setBorderWidthTop(1);
                    cel_codigo_produto.setBorderWidthBottom(1);
                    
                    
                    PdfPCell cel_nome_produto = new PdfPCell(nome_produto);
                    cel_nome_produto.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cel_nome_produto.setBorderColor(BaseColor.WHITE);
                    cel_nome_produto.setBorderWidthTop(1);
                    cel_nome_produto.setBorderWidthBottom(1);


                    PdfPCell cel_preco_produto = new PdfPCell(preco_produto);
                    cel_preco_produto.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cel_preco_produto.setBorderColor(BaseColor.WHITE);
                    cel_preco_produto.setBorderWidthTop(1);
                    cel_preco_produto.setBorderWidthBottom(1);

                    PdfPCell cel_qtd_produto = new PdfPCell(qtd_produto);
                    cel_qtd_produto.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cel_qtd_produto.setBorderColor(BaseColor.WHITE);
                    cel_qtd_produto.setBorderWidthTop(1);
                    cel_qtd_produto.setBorderWidthBottom(1);


                    PdfPCell cel_total_produto = new PdfPCell(total_produto);
                    cel_total_produto.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cel_total_produto.setBorderColor(BaseColor.WHITE);
                    cel_total_produto.setBorderWidthTop(1);
                    cel_total_produto.setBorderWidthBottom(1);
                   
                    tabela_lista_itens_venda.addCell(cel_codigo_produto);
                    tabela_lista_itens_venda.addCell(cel_nome_produto);
                    tabela_lista_itens_venda.addCell(cel_preco_produto);
                    tabela_lista_itens_venda.addCell(cel_qtd_produto);
                    tabela_lista_itens_venda.addCell(cel_total_produto);

                    
                    documento.add(tabela_lista_itens_venda);
                }
                           
            PdfPTable tabela_lista_vendas=  new PdfPTable(new float[]{
                         0.20f, 0.20f, 0.20f, 0.20f,0.20f});
            tabela_lista_vendas.setWidthPercentage(100.0f);
            tabela_lista_vendas.setSpacingBefore(5); 

            fonte = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
            //Adicionando os itens na tabela
            //for (Item item : venda.getItens()) {
                String dia = formatotodata.format(venda.getData());
                
                Paragraph data = new Paragraph("Data:"+dia,fonte);
                Paragraph total = new Paragraph("Total: R$ "+venda.getTotal()+"",fonte);
                Paragraph desconto = new Paragraph("Desconto:"+venda.getDesconto()+"%",fonte);
                Paragraph pagamento = new Paragraph("Pag.:" +venda.getFormaPagamento()+"",fonte);
                Paragraph vendedor = new Paragraph("Vend.: "+venda.getVendedor().getNome(),fonte);
                
                
                cel_codigo.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_codigo.setBorderColor(BaseColor.WHITE);
                //cel_codigo.setBorderWidthTop(0);
                //cel_codigo.setBorderWidthLeft(0);
                //cel_codigo.setBorderWidthRight(0);;


                PdfPCell cel_dat = new PdfPCell(data);
                cel_dat.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_dat.setBorderColor(BaseColor.BLACK);
                //cel_codigo.setBorderWidthTop(0);
                cel_dat.setBorderWidthBottom(0);
                cel_dat.setBorderWidthLeft(0);
                cel_dat.setBorderWidthRight(0);

                PdfPCell cel_tot = new PdfPCell(total);
                cel_tot.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_tot.setBorderColor(BaseColor.BLACK);
                //cel_codigo.setBorderWidthTop(0);
                cel_tot.setBorderWidthBottom(0);
                cel_tot.setBorderWidthLeft(0);
                cel_tot.setBorderWidthRight(0);

                
                PdfPCell cel_pag = new PdfPCell(pagamento);
                cel_pag.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_pag.setBorderColor(BaseColor.BLACK);
                //cel_codigo.setBorderWidthTop(0);
                cel_pag.setBorderWidthBottom(0);
                cel_pag.setBorderWidthLeft(0);
                cel_pag.setBorderWidthRight(0);

                PdfPCell cel_des = new PdfPCell(desconto);
                cel_des.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_des.setBorderColor(BaseColor.BLACK);
                //cel_codigo.setBorderWidthTop(0);
                cel_des.setBorderWidthBottom(0);
                cel_des.setBorderWidthLeft(0);
                cel_des.setBorderWidthRight(0);

                PdfPCell cel_ven = new PdfPCell(vendedor);
                cel_ven.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_ven.setBorderColor(BaseColor.BLACK);
                //cel_codigo.setBorderWidthTop(0);
                cel_ven.setBorderWidthBottom(0);
                cel_ven.setBorderWidthLeft(0);
                cel_ven.setBorderWidthRight(0);
                
                
                tabela_lista_vendas.addCell(cel_dat);
                tabela_lista_vendas.addCell(cel_tot);
                tabela_lista_vendas.addCell(cel_des);
                tabela_lista_vendas.addCell(cel_pag);
                tabela_lista_vendas.addCell(cel_ven);

                documento.add(tabela_lista_vendas);
            
        }
        File file = new File(caminho);
        documento.close();
        saida.close();
        
        return file;

    }//Fim da geração de relatorio de vendas
          
     
     public File gerarRelatorioClientes(ArrayList<Cliente> lista, String tipo) throws FileNotFoundException, DocumentException, BadElementException, IOException{
         String caminho = "relatoriocliente.pdf";
         
        Document documento = null;
        OutputStream saida = null;
               
        documento = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
        saida = new FileOutputStream(caminho);
        PdfWriter.getInstance(documento, saida);
        
        documento.open();
        
        //CRIANDO O TITULO DA FICHA E DO LOGOTIPO
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
        
        String empresa;
        
        if(FachadaControle.getFachadaControle().getEmpresa() == null){
            empresa = "MINHA EMPRESA";
        }else{
            empresa = FachadaControle.getFachadaControle().getEmpresa().getNome();
        }
        
        Paragraph titulo = new Paragraph(empresa
                + "\nRelatório: "+tipo+"\n", fonte);
        titulo.setAlignment(Element.ALIGN_CENTER);

        //Pegando o logotipo do jevania
        Image logo = Image.getInstance(this.pegaLogo());
        logo.setAlignment(Element.ALIGN_CENTER);
        
        PdfPTable tabela_titulos = new PdfPTable(new float[]{0.10f, 0.90f});
        //tabela_titulos.setWidthPercentage(100.0f);
        PdfPCell celula1 = new PdfPCell();
        celula1.setBorderColor(BaseColor.WHITE);
        celula1.setImage(logo);
        celula1.setFixedHeight(20);
        
        PdfPCell celula2 = new PdfPCell(titulo);
        celula2.setBorderColor(BaseColor.WHITE);
        tabela_titulos.addCell(celula1);
        tabela_titulos.addCell(celula2);
        tabela_titulos.setHorizontalAlignment(Element.ALIGN_LEFT);
        documento.add(tabela_titulos);
        
        //CRIAÇÃO DA TABELA DE ITENS
        //Criação da Tabela de Itens
        fonte = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        PdfPTable tabela_itens = new PdfPTable(new float[]{
                    0.05f, 0.20f, 0.10f, 0.05f, 0.15f, 0.10f, 0.10f,0.10f,0.10f,0.05f});
        tabela_itens.setWidthPercentage(100.0f);
        tabela_itens.setSpacingBefore(8); 

        Paragraph lcodigo = new Paragraph("Cód.", fonte);
        Paragraph lnome = new Paragraph("Nome", fonte);
        Paragraph lapelido = new Paragraph("Apelido", fonte);
        Paragraph lsexo = new Paragraph("Sexo", fonte);
        Paragraph lendereco = new Paragraph("Endereço", fonte);
        Paragraph lbairro = new Paragraph("Bairro", fonte);
        Paragraph ltelefone = new Paragraph("Telefone", fonte);
        Paragraph lcelular = new Paragraph("Celular", fonte);
        Paragraph lemail = new Paragraph("E-mail", fonte);
        Paragraph lpendente = new Paragraph("pendente", fonte);
        

        PdfPCell cel_codigo = new PdfPCell(lcodigo);
        cel_codigo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_codigo.setBorderColor(BaseColor.BLACK);
        cel_codigo.setBorderWidthTop(1);
        cel_codigo.setBorderWidthBottom(1);
        cel_codigo.setBorderWidthLeft(0);
        cel_codigo.setBorderWidthRight(0);


        PdfPCell cel_nome = new PdfPCell(lnome);
        cel_nome.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_nome.setBorderColor(BaseColor.BLACK);
        cel_nome.setBorderWidthTop(1);
        cel_nome.setBorderWidthBottom(1);
        cel_nome.setBorderWidthLeft(0);
        cel_nome.setBorderWidthRight(0);

        PdfPCell cel_apelido = new PdfPCell(lapelido);
        cel_apelido.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_apelido.setBorderColor(BaseColor.BLACK);
        cel_apelido.setBorderWidthTop(1);
        cel_apelido.setBorderWidthBottom(1);
        cel_apelido.setBorderWidthLeft(0);
        cel_apelido.setBorderWidthRight(0);
        
        PdfPCell cel_sexo = new PdfPCell(lsexo);
        cel_sexo.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_sexo.setBorderColor(BaseColor.BLACK);
        cel_sexo.setBorderWidthTop(1);
        cel_sexo.setBorderWidthBottom(1);
        cel_sexo.setBorderWidthLeft(0);
        cel_sexo.setBorderWidthRight(0);


        PdfPCell cel_endereco = new PdfPCell(lendereco);
        cel_endereco.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_endereco.setBorderColor(BaseColor.BLACK);
        cel_endereco.setBorderWidthTop(1);
        cel_endereco.setBorderWidthBottom(1);
        cel_endereco.setBorderWidthLeft(0);
        cel_endereco.setBorderWidthRight(0);
        
        PdfPCell cel_bairro = new PdfPCell(lbairro);
        cel_bairro.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_bairro.setBorderColor(BaseColor.BLACK);
        cel_bairro.setBorderWidthTop(1);
        cel_bairro.setBorderWidthBottom(1);
        cel_bairro.setBorderWidthLeft(0);
        cel_bairro.setBorderWidthRight(0);
        
        PdfPCell cel_telefone = new PdfPCell(ltelefone);
        cel_telefone.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_telefone.setBorderColor(BaseColor.BLACK);
        cel_telefone.setBorderWidthTop(1);
        cel_telefone.setBorderWidthBottom(1);
        cel_telefone.setBorderWidthLeft(0);
        cel_telefone.setBorderWidthRight(0);

       
        PdfPCell cel_celular = new PdfPCell(lcelular);
        cel_celular.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_celular.setBorderColor(BaseColor.BLACK);
        cel_celular.setBorderWidthTop(1);
        cel_celular.setBorderWidthBottom(1);
        cel_celular.setBorderWidthLeft(0);
        cel_celular.setBorderWidthRight(0);
        
        PdfPCell cel_email = new PdfPCell(lemail);
        cel_email.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_email.setBorderColor(BaseColor.BLACK);
        cel_email.setBorderWidthTop(1);
        cel_email.setBorderWidthBottom(1);
        cel_email.setBorderWidthLeft(0);
        cel_email.setBorderWidthRight(0);
        
        PdfPCell cel_pendente = new PdfPCell(lpendente);
        cel_pendente.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_pendente.setBorderColor(BaseColor.BLACK);
        cel_pendente.setBorderWidthTop(1);
        cel_pendente.setBorderWidthBottom(1);
        cel_pendente.setBorderWidthLeft(0);
        cel_pendente.setBorderWidthRight(0);

        tabela_itens.addCell(cel_codigo);
        tabela_itens.addCell(cel_nome);
        tabela_itens.addCell(cel_apelido);
        tabela_itens.addCell(cel_sexo);
        tabela_itens.addCell(cel_endereco);
        tabela_itens.addCell(cel_bairro);
        tabela_itens.addCell(cel_telefone);
        tabela_itens.addCell(cel_celular);
        tabela_itens.addCell(cel_email);
        tabela_itens.addCell(cel_pendente);
        
        documento.add(tabela_itens);
        
        //PERCORRENDO AS VENDAS
        for(Cliente cliente : lista){
            
            //System.out.println("GERANDO... "+p.getDescricao());
            
            PdfPTable tabela_lista_itens = new PdfPTable(new float[]{
                        0.05f, 0.20f, 0.10f, 0.05f, 0.15f, 0.10f, 0.10f,0.10f,0.10f,0.05f});
            tabela_lista_itens.setWidthPercentage(100.0f);
            tabela_lista_itens.setSpacingBefore(5); 
            
            
            
            fonte = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
            //Adicionando os itens na tabela

                Paragraph codigo = new Paragraph(cliente.getCodigo()+"",fonte);
                Paragraph nome = new Paragraph(cliente.getNome()+"",fonte);
                Paragraph apelido = new Paragraph(cliente.getApelido()+"",fonte);
                Paragraph sexo = new Paragraph(cliente.getSexo()+"",fonte);
                Paragraph endereco = new Paragraph(cliente.getEndereco()+"",fonte);
                Paragraph bairro = new Paragraph(cliente.getBairro()+"",fonte);
                Paragraph telefone = new Paragraph(cliente.getTelefone()+"",fonte);
                Paragraph celular = new Paragraph(cliente.getCelular()+"",fonte);
                Paragraph email = new Paragraph(cliente.getEmail()+"",fonte);
                
                String status;
                if(cliente.isTemPendencia()){
                    status = "Sim";
                }else{
                    status = "Não";
                }
                
                Paragraph pendente = new Paragraph(status,fonte);

                               

                PdfPCell cel_cod = new PdfPCell(codigo);
                cel_cod.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_cod.setBorderColor(BaseColor.BLACK);
                cel_cod.setBorderWidthTop(0);
                cel_cod.setBorderWidthLeft(0);
                cel_cod.setBorderWidthRight(0);

                PdfPCell cel_nom = new PdfPCell(nome);
                cel_nom.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel_nom.setBorderColor(BaseColor.BLACK);
                cel_nom.setBorderWidthTop(0);
                cel_nom.setBorderWidthLeft(0);
                cel_nom.setBorderWidthRight(0);

                PdfPCell cel_apel = new PdfPCell(apelido);
                cel_apel.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel_apel.setBorderColor(BaseColor.BLACK);
                cel_apel.setBorderWidthTop(0);
                cel_apel.setBorderWidthLeft(0);
                cel_apel.setBorderWidthRight(0);

                PdfPCell cel_sex = new PdfPCell(sexo);
                cel_sex.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel_sex.setBorderColor(BaseColor.BLACK);
                cel_sex.setBorderWidthTop(0);
                cel_sex.setBorderWidthLeft(0);
                cel_sex.setBorderWidthRight(0);
                
                PdfPCell cel_end = new PdfPCell(endereco);
                cel_end.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel_end.setBorderColor(BaseColor.BLACK);
                cel_end.setBorderWidthTop(0);
                cel_end.setBorderWidthLeft(0);
                cel_end.setBorderWidthRight(0);
                
                PdfPCell cel_bair = new PdfPCell(bairro);
                cel_bair.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel_bair.setBorderColor(BaseColor.BLACK);
                cel_bair.setBorderWidthTop(0);
                cel_bair.setBorderWidthLeft(0);
                cel_bair.setBorderWidthRight(0);

                PdfPCell cel_telef = new PdfPCell(telefone);
                cel_telef.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel_telef.setBorderColor(BaseColor.BLACK);
                cel_telef.setBorderWidthTop(0);
                cel_telef.setBorderWidthLeft(0);
                cel_telef.setBorderWidthRight(0);
                
                PdfPCell cel_celu = new PdfPCell(celular);
                cel_celu.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel_celu.setBorderColor(BaseColor.BLACK);
                cel_celu.setBorderWidthTop(0);
                cel_celu.setBorderWidthLeft(0);
                cel_celu.setBorderWidthRight(0);
                
                PdfPCell cel_ema = new PdfPCell(email);
                cel_ema.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel_ema.setBorderColor(BaseColor.BLACK);
                cel_ema.setBorderWidthTop(0);
                cel_ema.setBorderWidthLeft(0);
                cel_ema.setBorderWidthRight(0);
                
                PdfPCell cel_pend = new PdfPCell(pendente);
                cel_pend.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel_pend.setBorderColor(BaseColor.BLACK);
                cel_pend.setBorderWidthTop(0);
                cel_pend.setBorderWidthLeft(0);
                cel_pend.setBorderWidthRight(0);
                
                tabela_lista_itens.addCell(cel_cod);
                tabela_lista_itens.addCell(cel_nom);
                tabela_lista_itens.addCell(cel_apel);
                tabela_lista_itens.addCell(cel_sex);
                tabela_lista_itens.addCell(cel_end);
                tabela_lista_itens.addCell(cel_bair);
                tabela_lista_itens.addCell(cel_celu);
                tabela_lista_itens.addCell(cel_telef);
                tabela_lista_itens.addCell(cel_ema);
                tabela_lista_itens.addCell(cel_pend);
                

            documento.add(tabela_lista_itens);
            
        }
        
        File file = new File(caminho);
        documento.close();
        saida.close();
        
        return file;
     }
     
     
     public File gerarRelatorioProdutos(ArrayList<Produto> lista) throws DocumentException, FileNotFoundException, BadElementException, MalformedURLException, IOException, SQLException{
        String caminho = "relatorioproduto.pdf";
        //double total_vendas = 0;
        Document documento = null;
        OutputStream saida = null;
               
        documento = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
        saida = new FileOutputStream(caminho);
        PdfWriter.getInstance(documento, saida);
        
        documento.open();

        //CRIANDO O TITULO DA FICHA E DO LOGOTIPO
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
        
        String empresa;
        
        if(FachadaControle.getFachadaControle().getEmpresa() == null){
            empresa = "MINHA EMPRESA";
        }else{
            empresa = FachadaControle.getFachadaControle().getEmpresa().getNome();
        }
        
        Paragraph titulo = new Paragraph(empresa
                + "\nRelatório de Produtos\n", fonte);
        titulo.setAlignment(Element.ALIGN_CENTER);

        //Pegando o logotipo do jevania
        Image logo = Image.getInstance(this.pegaLogo());
        logo.setAlignment(Element.ALIGN_CENTER);
        
        PdfPTable tabela_titulos = new PdfPTable(new float[]{0.10f, 0.90f});
        //tabela_titulos.setWidthPercentage(100.0f);
        PdfPCell celula1 = new PdfPCell();
        celula1.setBorderColor(BaseColor.WHITE);
        celula1.setImage(logo);
        celula1.setFixedHeight(20);
        
        PdfPCell celula2 = new PdfPCell(titulo);
        celula2.setBorderColor(BaseColor.WHITE);
        tabela_titulos.addCell(celula1);
        tabela_titulos.addCell(celula2);
        tabela_titulos.setHorizontalAlignment(Element.ALIGN_LEFT);
        documento.add(tabela_titulos);
        
        
        
        //Criação da Tabela de Itens
        fonte = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        PdfPTable tabela_itens = new PdfPTable(new float[]{
                    0.20f, 0.25f, 0.05f, 0.05f, 0.10f, 0.10f, 0.25f});
        tabela_itens.setWidthPercentage(100.0f);
        tabela_itens.setSpacingBefore(8); 

        Paragraph lcodigo = new Paragraph("Código", fonte);
        Paragraph ldescricao = new Paragraph("Descrição", fonte);
        Paragraph lquantidade = new Paragraph("Qtd", fonte);
        Paragraph lminimo = new Paragraph("Es.Min.", fonte);
        Paragraph lcusto = new Paragraph("Custo", fonte);
        Paragraph lpreco = new Paragraph("Preço", fonte);
        Paragraph lfabricante = new Paragraph("Fabricante", fonte);

        PdfPCell cel_codigo = new PdfPCell(lcodigo);
        cel_codigo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_codigo.setBorderColor(BaseColor.BLACK);
        //cel_codigo.setBorderWidthTop(0);
        cel_codigo.setBorderWidthLeft(0);
        cel_codigo.setBorderWidthRight(0);


        PdfPCell cel_descricao = new PdfPCell(ldescricao);
        cel_descricao.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_descricao.setBorderColor(BaseColor.BLACK);
        //cel_descricao.setBorderWidthTop(0);
        cel_descricao.setBorderWidthLeft(0);
        cel_descricao.setBorderWidthRight(0);

        PdfPCell cel_quantidade = new PdfPCell(lquantidade);
        cel_quantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_quantidade.setBorderColor(BaseColor.BLACK);
        //cel_quantidade.setBorderWidthTop(0);
        cel_quantidade.setBorderWidthLeft(0);
        cel_quantidade.setBorderWidthRight(0);

        PdfPCell cel_minimo = new PdfPCell(lminimo);
        cel_minimo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_minimo.setBorderColor(BaseColor.BLACK);
        //cel_quantidade.setBorderWidthTop(0);
        cel_minimo.setBorderWidthLeft(0);
        cel_minimo.setBorderWidthRight(0);
        
        PdfPCell cel_custo = new PdfPCell(lcusto);
        cel_custo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_custo.setBorderColor(BaseColor.BLACK);
        //cel_quantidade.setBorderWidthTop(0);
        cel_custo.setBorderWidthLeft(0);
        cel_custo.setBorderWidthRight(0);
        
        PdfPCell cel_preco = new PdfPCell(lpreco);
        cel_preco.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_preco.setBorderColor(BaseColor.BLACK);
        //cel_preco.setBorderWidthTop(0);
        cel_preco.setBorderWidthLeft(0);
        cel_preco.setBorderWidthRight(0);

        PdfPCell cel_fabricante = new PdfPCell(lfabricante);
        cel_fabricante.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_fabricante.setBorderColor(BaseColor.BLACK);
        //cel_total.setBorderWidthTop(0);
        cel_fabricante.setBorderWidthLeft(0);
        cel_fabricante.setBorderWidthRight(0);

        tabela_itens.addCell(cel_codigo);
        tabela_itens.addCell(cel_descricao);
        tabela_itens.addCell(cel_quantidade);
        tabela_itens.addCell(cel_minimo);
        tabela_itens.addCell(cel_custo);
        tabela_itens.addCell(cel_preco);
        tabela_itens.addCell(cel_fabricante);

        documento.add(tabela_itens);
        
        //PERCORRENDO AS VENDAS
        for(Produto p : lista){
            
            
            
            //System.out.println("GERANDO... "+p.getDescricao());
            
            PdfPTable tabela_lista_itens = new PdfPTable(new float[]{
                        0.20f, 0.25f, 0.05f, 0.05f, 0.10f, 0.10f, 0.25f});
            tabela_lista_itens.setWidthPercentage(100.0f);
            tabela_lista_itens.setSpacingBefore(5); 
            
            Produto produto = FachadaControle.getFachadaControle().getProduto(p.getCodigo());
            
            fonte = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
            //Adicionando os itens na tabela

                Paragraph codigo = new Paragraph(produto.getCodigoBarra()+"",fonte);
                Paragraph descricao = new Paragraph(produto.getDescricao()+"",fonte);
                Paragraph quantidade = new Paragraph(produto.getEstoqueAtual()+"",fonte);
                Paragraph minimo = new Paragraph(produto.getEstoqueMinimo()+"",fonte);
                Paragraph custo = new Paragraph(("R$ "+produto.getPrecoCusto()+"").replace(".", ","),fonte);
                Paragraph preco = new Paragraph(("R$ "+produto.getPrecoVenda()+"").replace(".", ","),fonte);

                Paragraph fabricante;
                if(produto.getFabricante() == null){
                    fabricante = new Paragraph("",fonte);
                }else{
                    fabricante = new Paragraph(produto.getFabricante().getNome()+"",fonte);
                }

                

                PdfPCell cel_cod = new PdfPCell(codigo);
                cel_cod.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_cod.setBorderColor(BaseColor.WHITE);

                PdfPCell cel_desc = new PdfPCell(descricao);
                cel_desc.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel_desc.setBorderColor(BaseColor.WHITE);

                PdfPCell cel_qtd = new PdfPCell(quantidade);
                cel_qtd.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_qtd.setBorderColor(BaseColor.WHITE);

                PdfPCell cel_min = new PdfPCell(minimo);
                cel_min.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_min.setBorderColor(BaseColor.WHITE);
                
                PdfPCell cel_cus = new PdfPCell(custo);
                cel_cus.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_cus.setBorderColor(BaseColor.WHITE);
                
                PdfPCell cel_pre = new PdfPCell(preco);
                cel_pre.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_pre.setBorderColor(BaseColor.WHITE);

                PdfPCell cel_fab = new PdfPCell(fabricante);
                cel_fab.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel_fab.setBorderColor(BaseColor.WHITE);

                tabela_lista_itens.addCell(cel_cod);
                tabela_lista_itens.addCell(cel_desc);
                tabela_lista_itens.addCell(cel_qtd);
                tabela_lista_itens.addCell(cel_min);
                tabela_lista_itens.addCell(cel_cus);
                tabela_lista_itens.addCell(cel_pre);
                tabela_lista_itens.addCell(cel_fab);
                

            documento.add(tabela_lista_itens);
            
        }
        File file = new File(caminho);
        documento.close();
        saida.close();
        
        return file;

    }//Fim da geração de relatorio de vendas
     
     public File gerarRelatorioPedidos2(ArrayList<Pedido> lista) throws DocumentException, FileNotFoundException, BadElementException, MalformedURLException, IOException{
        String caminho = "relatoriopedidos.pdf";
        //double total_vendas = 0;

        //Calculando o total no relatorio
        double total_pedidos = 0;
        for (Pedido pedido : lista) {
            total_pedidos += pedido.getTotal();
        }
        
        Document documento = null;
        OutputStream saida = null;
               
        documento = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
        saida = new FileOutputStream(caminho);
        PdfWriter.getInstance(documento, saida);
        documento.open();

        //CRIANDO O TITULO DA FICHA E DO LOGOTIPO
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
        
        String empresa;
        
        if(FachadaControle.getFachadaControle().getEmpresa() == null){
            empresa = "MINHA EMPRESA";
        }else{
            empresa = FachadaControle.getFachadaControle().getEmpresa().getNome();
        }
        
        Paragraph titulo = new Paragraph(empresa
                + "\nRelatório de Pedidos\n", fonte);
        titulo.setAlignment(Element.ALIGN_CENTER);

        //Pegando o logotipo do jevania
        Image logo = Image.getInstance(this.pegaLogo());
        logo.setAlignment(Element.ALIGN_CENTER);
        
        PdfPTable tabela_titulos = new PdfPTable(new float[]{0.10f, 0.90f});
        //tabela_titulos.setWidthPercentage(100.0f);
        PdfPCell celula1 = new PdfPCell();
        celula1.setBorderColor(BaseColor.WHITE);
        celula1.setImage(logo);
        celula1.setFixedHeight(20);
        
        PdfPCell celula2 = new PdfPCell(titulo);
        celula2.setBorderColor(BaseColor.WHITE);
        tabela_titulos.addCell(celula1);
        tabela_titulos.addCell(celula2);
        tabela_titulos.setHorizontalAlignment(Element.ALIGN_LEFT);
        
        documento.add(tabela_titulos);
        
        PdfPTable tabela_inicial = new PdfPTable(new float[]{0.20f, 0.80f});
        
        Paragraph p1 = new Paragraph("TOTAL DO RELATÓRIO",fonte);
        Paragraph p2 = new Paragraph(("R$ "+total_pedidos).replace(".", ","),fonte);
        
        //tabela_titulos.setWidthPercentage(100.0f);
        celula1 = new PdfPCell();
        celula1.setBorderColor(BaseColor.WHITE);
        celula1.addElement(p1);
        
        celula2 = new PdfPCell(titulo);
        celula2.setBorderColor(BaseColor.WHITE);
        celula2.addElement(p2);
        
        tabela_inicial.addCell(celula1);
        tabela_inicial.addCell(celula2);
        tabela_inicial.setHorizontalAlignment(Element.ALIGN_LEFT);
        
        documento.add(tabela_inicial);
               
        
//        //Criação da Tabela de Itens
        fonte = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        PdfPTable tabela_itens = new PdfPTable(new float[]{
                    0.10f, 0.20f, 0.20f, 0.10f, 0.20f, 0.20f});
        tabela_itens.setWidthPercentage(100.0f);
        tabela_itens.setSpacingBefore(8); 

        Paragraph lcodigo = new Paragraph("Cod.", fonte);
        Paragraph ldataPedido = new Paragraph("Data", fonte);
        Paragraph ldataPrevista = new Paragraph("Previsão", fonte);
        Paragraph ltotal = new Paragraph("Total", fonte);
        Paragraph lfornecedor = new Paragraph("Fornecedor", fonte);
        Paragraph lstatus = new Paragraph("Status", fonte);
//
        PdfPCell cel_codigo = new PdfPCell(lcodigo);
        cel_codigo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_codigo.setBorderColor(BaseColor.BLACK);
        //cel_codigo.setBorderWidthTop(0);
        cel_codigo.setBorderWidthLeft(0);
        cel_codigo.setBorderWidthRight(0);
        
        PdfPCell cel_data = new PdfPCell(ldataPedido);
        cel_data.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_data.setBorderColor(BaseColor.BLACK);
        //cel_quantidade.setBorderWidthTop(0);
        cel_data.setBorderWidthLeft(0);
        cel_data.setBorderWidthRight(0);

        PdfPCell cel_dataprevista = new PdfPCell(ldataPrevista);
        cel_dataprevista.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_dataprevista.setBorderColor(BaseColor.BLACK);
        //cel_total.setBorderWidthTop(0);
        cel_dataprevista.setBorderWidthLeft(0);
        cel_dataprevista.setBorderWidthRight(0);
        
        PdfPCell cel_total = new PdfPCell(ltotal);
        cel_total.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_total.setBorderColor(BaseColor.BLACK);
        //cel_quantidade.setBorderWidthTop(0);
        cel_total.setBorderWidthLeft(0);
        cel_total.setBorderWidthRight(0);

        PdfPCell cel_fornecedor = new PdfPCell(lfornecedor);
        cel_fornecedor.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_fornecedor.setBorderColor(BaseColor.BLACK);
        //cel_total.setBorderWidthTop(0);
        cel_fornecedor.setBorderWidthLeft(0);
        cel_fornecedor.setBorderWidthRight(0);
        
        PdfPCell cel_status = new PdfPCell(lstatus);
        cel_status.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_status.setBorderColor(BaseColor.BLACK);
        //cel_total.setBorderWidthTop(0);
        cel_status.setBorderWidthLeft(0);
        cel_status.setBorderWidthRight(0);
//                
        tabela_itens.addCell(cel_codigo);
        tabela_itens.addCell(cel_data);
        tabela_itens.addCell(cel_dataprevista);
        tabela_itens.addCell(cel_total);
        tabela_itens.addCell(cel_fornecedor);
        tabela_itens.addCell(cel_status);
//
        documento.add(tabela_itens);
       //PERCORRENDO AS VENDAS
        for(Pedido pedido : lista){
            
            
            
            
            for (ItensPedido item : pedido.getItens()) {
                
            
            PdfPTable tabela_lista_itens = new PdfPTable(new float[]{
                        0.05f, 0.20f, 0.10f, 0.05f, 0.10f, 0.10f, 0.10f, 0.20f,0.10f});
            tabela_lista_itens.setWidthPercentage(100.0f);
            tabela_lista_itens.setSpacingBefore(5); 

            fonte = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
            //Adicionando os itens na tabela
                
                SimpleDateFormat formatodata = new SimpleDateFormat("dd/MM/yyyy");    
            
                Paragraph codigo = new Paragraph(pedido.getCodigo()+"",fonte);
                Paragraph descricao = new Paragraph(item.getProduto().getDescricao(),fonte);
                Paragraph preco = new Paragraph(("R$ "+item.getPreco()+"").replace(".", ","),fonte);
                Paragraph quantidade = new Paragraph(item.getQuantidade()+"",fonte);
                Paragraph total = new Paragraph(("R$ "+item.getTotal()+"").replace(".", ","),fonte);
                Paragraph data = new Paragraph(formatodata.format(pedido.getDataPedido())+"",fonte);
                Paragraph dataprevista = new Paragraph(formatodata.format(pedido.getDataPrevista())+"",fonte);
                Paragraph fornecedor = new Paragraph(pedido.getFornecedor().getNome()+"",fonte);
                Paragraph status = new Paragraph(pedido.getStatus()+"",fonte);
                

                PdfPCell cel_cod = new PdfPCell(codigo);
                cel_cod.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_cod.setBorderColor(BaseColor.WHITE);

                PdfPCell cel_desc = new PdfPCell(descricao);
                cel_desc.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel_desc.setBorderColor(BaseColor.WHITE);

                PdfPCell cel_qtd = new PdfPCell(quantidade);
                cel_qtd.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_qtd.setBorderColor(BaseColor.WHITE);

                PdfPCell cel_tot = new PdfPCell(total);
                cel_tot.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_tot.setBorderColor(BaseColor.WHITE);
                
                PdfPCell cel_dat = new PdfPCell(data);
                cel_dat.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_dat.setBorderColor(BaseColor.WHITE);
                
                PdfPCell cel_pre = new PdfPCell(preco);
                cel_pre.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_pre.setBorderColor(BaseColor.WHITE);

                PdfPCell cel_datv = new PdfPCell(dataprevista);
                cel_datv.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_datv.setBorderColor(BaseColor.WHITE);
                
                PdfPCell cel_for = new PdfPCell(fornecedor);
                cel_for.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_for.setBorderColor(BaseColor.WHITE);

                PdfPCell cel_sts = new PdfPCell(status);
                cel_sts.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_sts.setBorderColor(BaseColor.WHITE);
                
                tabela_lista_itens.addCell(cel_cod);
                tabela_lista_itens.addCell(cel_desc);
                tabela_lista_itens.addCell(cel_pre);
                tabela_lista_itens.addCell(cel_qtd);
                tabela_lista_itens.addCell(cel_tot);
                tabela_lista_itens.addCell(cel_dat);
                tabela_lista_itens.addCell(cel_datv);
                tabela_lista_itens.addCell(cel_for);
                tabela_lista_itens.addCell(cel_sts);
            
            documento.add(tabela_lista_itens);
            }
//            
        }
        File file = new File(caminho);
        documento.close();
        saida.close();
        
        return file;

    }//Fim da geração de relatorio de vendas 
     
     public File gerarRelatorioPedidos(ArrayList<Pedido> lista) throws DocumentException, FileNotFoundException, BadElementException, MalformedURLException, IOException{
        String caminho = "relatoriovendas.pdf";
        SimpleDateFormat formatotodata = new SimpleDateFormat("dd/MM/yyyy");    
        
        
        double total_pedidos=  0;
        //Calculando o Total da vendas
        for(Pedido p : lista){
            total_pedidos += p.getTotal();
        }
        
        
        Document documento = null;
        OutputStream saida = null;
               
        documento = new Document(PageSize.A4, 20, 20, 20, 20);
        saida = new FileOutputStream(caminho);
        PdfWriter.getInstance(documento, saida);
        documento.open();

        //CRIANDO O TITULO DA FICHA E DO LOGOTIPO
        Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
        
        String empresa;
        
        if(FachadaControle.getFachadaControle().getEmpresa() == null){
            empresa = "MINHA EMPRESA";
        }else{
            empresa = FachadaControle.getFachadaControle().getEmpresa().getNome();
        }
        
        Paragraph titulo = new Paragraph(empresa
                + "\nRelatório de Pedidos\n", fonte);
        titulo.setAlignment(Element.ALIGN_CENTER);

        //Pegando o logotipo do jevania
        Image logo = Image.getInstance(this.pegaLogo());
        logo.setAlignment(Element.ALIGN_CENTER);
        
        PdfPTable tabela_titulos = new PdfPTable(new float[]{0.10f, 0.90f});
        //tabela_titulos.setWidthPercentage(100.0f);
        PdfPCell celula1 = new PdfPCell();
        celula1.setBorderColor(BaseColor.WHITE);
        celula1.setImage(logo);
        celula1.setFixedHeight(20);
        
        PdfPCell celula2 = new PdfPCell(titulo);
        celula2.setBorderColor(BaseColor.WHITE);
        tabela_titulos.addCell(celula1);
        tabela_titulos.addCell(celula2);
        tabela_titulos.setHorizontalAlignment(Element.ALIGN_LEFT);
        documento.add(tabela_titulos);
        
        //Criação da Tabela de dados iniciais
        PdfPTable tabela_dados = new PdfPTable(new float[]{0.30f, 0.70f});
        tabela_dados.setWidthPercentage(100.0f);
        tabela_dados.setSpacingBefore(5);
        
        Paragraph p3 = new Paragraph("TOTAL DO RELATÓRIO: ");
        Paragraph p4 = new Paragraph(("R$ "+total_pedidos).replace(".", ","));
        
        
       
        PdfPCell cel3 = new PdfPCell(p3);
        cel3.setBorderColor(BaseColor.BLACK);
        cel3.setBorderWidthBottom(0);
        cel3.setBorderWidthLeft(0);
        cel3.setBorderWidthRight(0);
        
        PdfPCell cel4 = new PdfPCell(p4);
        cel4.setBorderColor(BaseColor.BLACK);
        cel4.setBorderWidthBottom(0);
        cel4.setBorderWidthLeft(0);
        cel4.setBorderWidthRight(0);
        
        tabela_dados.addCell(cel3);
        tabela_dados.addCell(cel4);
        
        documento.add(tabela_dados);
        
        for(Pedido pedido : lista){
            
        //Criação dos dados do cliente
        //CriaÃ§Ã£o da Tabela de Itens
        fonte = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        PdfPTable tabela_dados_cliente = new PdfPTable(new float[]{
                    0.05f,0.10f, 0.15f, 0.15f, 0.15f, 0.15f, 0.10f, 0.15f});
        tabela_dados_cliente.setWidthPercentage(100.0f);
        tabela_dados_cliente.setSpacingBefore(8); 
            
       
        Paragraph lcodigopedido = new Paragraph("Cod.", fonte);
        Paragraph lcodigopedidodado = new Paragraph(pedido.getCodigo()+"", fonte);
        Paragraph ldatapedido = new Paragraph("DATA:", fonte);
        Paragraph ldatapedidodado = new Paragraph(formatotodata.format(pedido.getDataPedido()), fonte);
        Paragraph lprevisaopedido = new Paragraph("PREVISÃO:", fonte);    
        Paragraph lprevisaopedidodado = new Paragraph(formatotodata.format(pedido.getDataPrevista()), fonte);
        Paragraph ltotalpedido = new Paragraph("TOTAL:", fonte);    
        Paragraph ltotalpedidodado = new Paragraph(("R$ "+pedido.getTotal()).replace(".", ","), fonte);
        
        PdfPCell cel_codigocliente = new PdfPCell(lcodigopedido);
        cel_codigocliente.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cel_codigocliente.setBorderColor(BaseColor.BLACK);
        //cel_codigocliente.setBorderWidthBottom(0);
        cel_codigocliente.setBorderWidthTop(1);
        cel_codigocliente.setBorderWidthLeft(0);
        cel_codigocliente.setBorderWidthRight(0);
        
        
        PdfPCell cel_codigoclientedado = new PdfPCell(lcodigopedidodado);
        cel_codigoclientedado.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_codigoclientedado.setBorderColor(BaseColor.BLACK);
        cel_codigoclientedado.setBorderWidthTop(1);
        //cel_codigoclientedado.setBorderWidthBottom(0);
        cel_codigoclientedado.setBorderWidthLeft(0);
        cel_codigoclientedado.setBorderWidthRight(0);
        
        
        PdfPCell cel_nomecliente = new PdfPCell(ldatapedido);
        cel_nomecliente.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cel_nomecliente.setBorderColor(BaseColor.BLACK);
        cel_nomecliente.setBorderWidthTop(1);
        //cel_nomecliente.setBorderWidthBottom(0);
        cel_nomecliente.setBorderWidthLeft(0);
        cel_nomecliente.setBorderWidthRight(0);
        
        PdfPCell cel_nomeclientedado = new PdfPCell(ldatapedidodado);
        cel_nomeclientedado.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_nomeclientedado.setBorderColor(BaseColor.BLACK);
        cel_nomeclientedado.setBorderWidthTop(1);
        //cel_nomeclientedado.setBorderWidthBottom(0);
        cel_nomeclientedado.setBorderWidthLeft(0);
        cel_nomeclientedado.setBorderWidthRight(0);
        
        PdfPCell cel_apelidocliente = new PdfPCell(lprevisaopedido);
        cel_apelidocliente.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cel_apelidocliente.setBorderColor(BaseColor.BLACK);
        cel_apelidocliente.setBorderWidthTop(1);
        //cel_apelidocliente.setBorderWidthBottom(0);
        cel_apelidocliente.setBorderWidthLeft(0);
        cel_apelidocliente.setBorderWidthRight(0);
        
        PdfPCell cel_apelidoclientedado = new PdfPCell(lprevisaopedidodado);
        cel_apelidoclientedado.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_apelidoclientedado.setBorderColor(BaseColor.BLACK);
        cel_apelidoclientedado.setBorderWidthTop(1);
        //cel_apelidoclientedado.setBorderWidthBottom(0);
        cel_apelidoclientedado.setBorderWidthLeft(0);
        cel_apelidoclientedado.setBorderWidthRight(0);
        
        PdfPCell cel_totalpedido = new PdfPCell(ltotalpedido);
        cel_totalpedido.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cel_totalpedido.setBorderColor(BaseColor.BLACK);
        cel_totalpedido.setBorderWidthTop(1);
        //cel_apelidocliente.setBorderWidthBottom(0);
        cel_totalpedido.setBorderWidthLeft(0);
        cel_totalpedido.setBorderWidthRight(0);
        
        PdfPCell cel_totalpedidodado = new PdfPCell(ltotalpedidodado);
        cel_totalpedidodado.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_totalpedidodado.setBorderColor(BaseColor.BLACK);
        cel_totalpedidodado.setBorderWidthTop(1);
        //cel_apelidoclientedado.setBorderWidthBottom(0);
        cel_totalpedidodado.setBorderWidthLeft(0);
        cel_totalpedidodado.setBorderWidthRight(0);
       
        tabela_dados_cliente.addCell(cel_codigocliente);
        tabela_dados_cliente.addCell(cel_codigoclientedado);
        tabela_dados_cliente.addCell(cel_nomecliente);
        tabela_dados_cliente.addCell(cel_nomeclientedado);
        tabela_dados_cliente.addCell(cel_apelidocliente);
        tabela_dados_cliente.addCell(cel_apelidoclientedado);
        tabela_dados_cliente.addCell(cel_totalpedido);
        tabela_dados_cliente.addCell(cel_totalpedidodado);
        
        
        documento.add(tabela_dados_cliente);
        
        //CriaÃ§Ã£o da Tabela de Itens
        fonte = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        PdfPTable tabela_itens = new PdfPTable(new float[]{
                    0.10f,0.40f, 0.20f, 0.10f, 0.20f});
        tabela_itens.setWidthPercentage(100.0f);
        //tabela_itens.setSpacingBefore(8); 

        Paragraph lcodigo = new Paragraph("Cod.", fonte);
        Paragraph ldata = new Paragraph("Produto", fonte);
        Paragraph ltotal = new Paragraph("Preço", fonte);
        Paragraph ldesconto = new Paragraph("QTD", fonte);
        Paragraph lpagamento = new Paragraph("Total", fonte);
        
        PdfPCell cel_codigo = new PdfPCell(lcodigo);
        cel_codigo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_codigo.setBorderColor(BaseColor.BLACK);
        cel_codigo.setBorderWidthBottom(0);
        cel_codigo.setBorderWidthTop(0);
        cel_codigo.setBorderWidthLeft(0);
        cel_codigo.setBorderWidthRight(0);


        PdfPCell cel_descricao = new PdfPCell(ldata);
        cel_descricao.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_descricao.setBorderColor(BaseColor.BLACK);
        cel_descricao.setBorderWidthTop(0);
        cel_descricao.setBorderWidthBottom(0);
        cel_descricao.setBorderWidthLeft(0);
        cel_descricao.setBorderWidthRight(0);

        PdfPCell cel_quantidade = new PdfPCell(ltotal);
        cel_quantidade.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel_quantidade.setBorderColor(BaseColor.BLACK);
        cel_quantidade.setBorderWidthTop(0);
        cel_quantidade.setBorderWidthBottom(0);
        cel_quantidade.setBorderWidthLeft(0);
        cel_quantidade.setBorderWidthRight(0);

        PdfPCell cel_preco = new PdfPCell(ldesconto);
        cel_preco.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_preco.setBorderColor(BaseColor.BLACK);
        cel_preco.setBorderWidthTop(0);
        cel_preco.setBorderWidthBottom(0);
        cel_preco.setBorderWidthLeft(0);
        cel_preco.setBorderWidthRight(0);

        PdfPCell cel_total = new PdfPCell(lpagamento);
        cel_total.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel_total.setBorderColor(BaseColor.BLACK);
        cel_total.setBorderWidthTop(0);
        cel_total.setBorderWidthBottom(0);
        cel_total.setBorderWidthLeft(0);
        cel_total.setBorderWidthRight(0);

        
        tabela_itens.addCell(cel_codigo);
        tabela_itens.addCell(cel_descricao);
        tabela_itens.addCell(cel_quantidade);
        tabela_itens.addCell(cel_preco);
        tabela_itens.addCell(cel_total);
        
        documento.add(tabela_itens);
        
        //PERCORRENDO AS VENDAS
        
            
                       
            //}//Fim do for dos Itens
                
                for (ItensPedido item : pedido.getItens()) {
                    PdfPTable tabela_lista_itens_venda = new PdfPTable(new float[]{
                         0.10f,0.40f, 0.20f, 0.10f, 0.20f});
                    tabela_lista_itens_venda.setWidthPercentage(100.0f);
                    tabela_lista_itens_venda.setSpacingBefore(5); 

                    fonte = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
                    //Adicionando os itens na tabela
                    //for (Item item : venda.getItens()) {
                    
                    Paragraph cod_produto = new Paragraph(item.getProduto().getCodigo()+"",fonte);
                    Paragraph nome_produto = new Paragraph(item.getProduto().getDescricao()+"",fonte);
                    Paragraph preco_produto = new Paragraph(("R$ "+item.getPreco()+"").replace(".", ","),fonte);
                    Paragraph qtd_produto = new Paragraph(item.getQuantidade()+"",fonte);
                    Paragraph total_produto = new Paragraph(("R$ "+item.getTotal()+"").replace(".", ","),fonte);
                    
                    PdfPCell cel_codigo_produto = new PdfPCell(cod_produto);
                    cel_codigo_produto.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cel_codigo_produto.setBorderColor(BaseColor.WHITE);
                    cel_codigo_produto.setBorderWidthTop(1);
                    cel_codigo_produto.setBorderWidthBottom(1);
                    
                    
                    PdfPCell cel_nome_produto = new PdfPCell(nome_produto);
                    cel_nome_produto.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cel_nome_produto.setBorderColor(BaseColor.WHITE);
                    cel_nome_produto.setBorderWidthTop(1);
                    cel_nome_produto.setBorderWidthBottom(1);


                    PdfPCell cel_preco_produto = new PdfPCell(preco_produto);
                    cel_preco_produto.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cel_preco_produto.setBorderColor(BaseColor.WHITE);
                    cel_preco_produto.setBorderWidthTop(1);
                    cel_preco_produto.setBorderWidthBottom(1);

                    PdfPCell cel_qtd_produto = new PdfPCell(qtd_produto);
                    cel_qtd_produto.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cel_qtd_produto.setBorderColor(BaseColor.WHITE);
                    cel_qtd_produto.setBorderWidthTop(1);
                    cel_qtd_produto.setBorderWidthBottom(1);


                    PdfPCell cel_total_produto = new PdfPCell(total_produto);
                    cel_total_produto.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cel_total_produto.setBorderColor(BaseColor.WHITE);
                    cel_total_produto.setBorderWidthTop(1);
                    cel_total_produto.setBorderWidthBottom(1);
                   
                    tabela_lista_itens_venda.addCell(cel_codigo_produto);
                    tabela_lista_itens_venda.addCell(cel_nome_produto);
                    tabela_lista_itens_venda.addCell(cel_preco_produto);
                    tabela_lista_itens_venda.addCell(cel_qtd_produto);
                    tabela_lista_itens_venda.addCell(cel_total_produto);

                    
                    documento.add(tabela_lista_itens_venda);
                }
                           
            PdfPTable tabela_lista_vendas=  new PdfPTable(new float[]{
                         0.30f, 0.70f});
            tabela_lista_vendas.setWidthPercentage(100.0f);
            tabela_lista_vendas.setSpacingBefore(5); 

            fonte = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
            //Adicionando os itens na tabela
            //for (Item item : venda.getItens()) {
                //String dia = formatotodata.format(pedido.getData());
                
                Paragraph codigofor = new Paragraph("Cod:"+pedido.getFornecedor().getCodigo(),fonte);
                Paragraph total = new Paragraph("FORNECEDOR: "+pedido.getFornecedor().getNome()+"",fonte);
               
                
                cel_codigo.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_codigo.setBorderColor(BaseColor.WHITE);
                //cel_codigo.setBorderWidthTop(0);
                //cel_codigo.setBorderWidthLeft(0);
                //cel_codigo.setBorderWidthRight(0);;


                PdfPCell cel_dat = new PdfPCell(codigofor);
                cel_dat.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_dat.setBorderColor(BaseColor.BLACK);
                //cel_codigo.setBorderWidthTop(0);
                cel_dat.setBorderWidthBottom(0);
                cel_dat.setBorderWidthLeft(0);
                cel_dat.setBorderWidthRight(0);

                PdfPCell cel_tot = new PdfPCell(total);
                cel_tot.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel_tot.setBorderColor(BaseColor.BLACK);
                //cel_codigo.setBorderWidthTop(0);
                cel_tot.setBorderWidthBottom(0);
                cel_tot.setBorderWidthLeft(0);
                cel_tot.setBorderWidthRight(0);

                
                
                
                
                tabela_lista_vendas.addCell(cel_dat);
                tabela_lista_vendas.addCell(cel_tot);


                documento.add(tabela_lista_vendas);
            
        }
        File file = new File(caminho);
        documento.close();
        saida.close();
        
        return file;

    }//Fim da geração de relatorio de vendas
     
    
     public String pegaLogo(){
        FileOutputStream fos; 
        try {
            
            if(FachadaControle.getFachadaControle().getEmpresa() == null){
                fos = new FileOutputStream("sem.jpg");
            }else{
                fos = new FileOutputStream(FachadaControle.getFachadaControle().getEmpresa().getNomelogo());
                fos.write(FachadaControle.getFachadaControle().getEmpresa().getLogo());
            }
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RelatoriosPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RelatoriosPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(FachadaControle.getFachadaControle().getEmpresa() == null){
            return "sem.jpg";
        }
        
        return FachadaControle.getFachadaControle().getEmpresa().getNomelogo();
    }
    
}//Fim da CLasse

