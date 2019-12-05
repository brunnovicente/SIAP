/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.sql.SQLException;
import java.util.List;
import persistencia.FachadaPersistencia;
import persistencia.entidades.Cliente;
import persistencia.entidades.Usuario;
import persistencia.entidades.Veiculo;

/**
 *
 * @author Bruno
 */
public class GerenciadorClientes {
    
    private FachadaPersistencia banco;
    
    public GerenciadorClientes(){
        this.banco = new FachadaPersistencia();
    }
    
    public Cliente salvarCliente(Cliente cliente) throws SQLException{
        return this.banco.cadastrarCliente(cliente);
    }
    
    public List<Cliente> consultaClientes(String chave) throws Exception{
        return this.banco.consultaClientes(chave);
    }
    
    public List<Cliente> consultaClientes(Veiculo veiculo) throws Exception{
        return this.banco.consultaClientes(veiculo);
    }
    
    public List<Cliente> consultaClientesPendentes() throws Exception{
        return this.banco.consultaClientesPendentes();
    }
    
    public Cliente getCliente(int codigo) throws SQLException{
        return this.banco.getCliente(codigo);
    }
    
    public Cliente editarCliente(Cliente cliente){
        return this.banco.editarCliente(cliente);
    }
    
    public Cliente excluirCliente(int codigo) throws Exception{
        if(GerenciadorUsuarios.getUsuarioLogado().getTipo().equals(Usuario.TIPO_VENDEDOR)){
            throw new Exception("Usuário não têm permissão para realizar esta operação!");
        }

        return this.banco.excluirCliente(codigo);
        
    }
    
    public void removerVeiculo(Veiculo v){
       this.banco.removerVeiculo(v);
    }
    
     public void salvarVeiculo(Veiculo v){
         this.banco.salvarVeiculo(v);
     }
     
     public Veiculo getVeiculo(String placa) throws SQLException{
         return this.banco.getVeiculo(placa);
     }
     
}
