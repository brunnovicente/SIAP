/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import javax.mail.MessagingException;
import persistencia.FachadaPersistencia;
import persistencia.entidades.Cliente;
import persistencia.entidades.Empresa;
import persistencia.entidades.Usuario;

/**
 *
 * @author User
 */
class GerenciadorUsuarios {

    private static Usuario usuarioLogado;
    private Hashing hash = Hashing.getHashing();
    private FachadaPersistencia banco;
    
    public GerenciadorUsuarios(){
        this.banco = new FachadaPersistencia();
    }
    /**
     * método para fazer login no sistema
     *
     * @param login login do usuário
     * @param senha senha do usuário
     * @return retorna o Usuário caso o login tenha sucesso, retorna null caso o
     * login falhe
     */
    public Usuario fazerLogin(String login, String senha) throws SQLException, Exception {
        senha = hash.HashSHA512(senha);
        Usuario usuario = this.banco.buscarUsuario(login);
        if (usuario == null) {
            throw new Exception("Usuário '"+login+"' não existe");
        }
        if (usuario.getSenha().equals(senha)) {
            usuarioLogado = usuario;
            return usuario;
        }else{
            throw new Exception("Senha Inválida!");
        }
    }
    
    /**
     * metodo que retorna o usuário logado no sistema
     * @return o usuário logado no sistema ou null caso não haja usuário logado no sistema
     */
    public static Usuario getUsuarioLogado(){
        return usuarioLogado;
    }
    
    public List<Usuario> consultaUusarios() throws SQLException{
        return this.banco.consultaUsuario();
    }
    
    public Usuario cadastrarUsuario(Usuario usuario) throws Exception{
        usuario.setSenha(hash.HashSHA512(usuario.getSenha()));
        
        if(usuario.getNome().isEmpty() || usuario.getEmail().isEmpty() || usuario.getLogin().isEmpty() || usuario.getSenha().isEmpty()) {
            throw new Exception("Todos os campos devem ser preenchidos");
        }
        if(usuario.getTipo().isEmpty()) {
            throw new Exception("Cargo do Usuário Não Informado!");
        }
        if(usuarioLogado == null) {
            throw new Exception("Não existe nenhum usuário logado");
        }
        if(usuarioLogado.getTipo().equals(Usuario.TIPO_VENDEDOR)) {
            throw new Exception("O usuário logado não tem autoridade para realizar esta operação");
        }
        if(!(usuario.getTipo().equals(Usuario.TIPO_ADMNISTRADOR)||usuario.getTipo().equals(Usuario.TIPO_GERENTE)||usuario.getTipo().equals(Usuario.TIPO_VENDEDOR))) {
            throw new Exception("Tipo de Usuário Desconhecido");
        }
        if(this.banco.buscarUsuario(usuario.getLogin())!=null) {
            throw new Exception("Login já existente no sistema");
        }
        //if(usuario.getNome().length() > 15) throw new Exception("Nome do usuário deve ter no máximo 15 letras!");
        if(usuario.getLogin().length() > 15) {
            throw new Exception("Login do usuário deve ter no máximo 15 letras!");
        }
        
        Usuario cadastrarUsuario = this.banco.cadastrarUsuario(usuario);
               
        return cadastrarUsuario;
    }
    
    public Usuario excluirUsuario(String login) throws Exception{
        if(login.isEmpty()) {
            throw new Exception("Selecione um Usuário");
        }
        if(usuarioLogado.getLogin().equals(login)) {
            throw new Exception("Não é permitido um usuário excluir ele mesmo!");
        }
        if(usuarioLogado.getTipo().equals(Usuario.TIPO_VENDEDOR)) {
            throw new Exception("O usuário logado não tem autoridade para realizar esta operação");
        }
        Usuario usuario = this.banco.buscarUsuario(login);
        this.banco.excluirUSuario(login);
        return usuario;
    }
    
    public Usuario editarUsuario(Usuario usuario, String senhaAntiga) throws SQLException, Exception{
        //String senhaVelha = this.hash.HashSHA512(senhaAntiga);
        if((usuarioLogado.getLogin().equals(usuario.getLogin()))||(usuarioLogado.getTipo().equals(Usuario.TIPO_ADMNISTRADOR)) || (usuarioLogado.getTipo().equals(Usuario.TIPO_GERENTE))){
            //usuarioLogado = this.banco.editarUsuario(usuario);
            Usuario temp = this.banco.buscarUsuario(usuario.getLogin());
            //JOptionPane.showMessageDialog(null, temp.getSenha()+" \n"+senhaAntiga);
            if(temp.getSenha().equals(senhaAntiga)){
                String novaSenha = this.hash.HashSHA512(usuario.getSenha());  
                usuario.setSenha(novaSenha);
                usuarioLogado = this.banco.editarUsuario(usuario);
                return usuarioLogado;
            }else{
                throw new Exception("Senha Incorreta!");
            }
        }else{
            throw new Exception("O usuário logado não tem autoridade para realizar esta operação!");
        }
    }//Fim de Editar Usuário
    
    public void recuperarSenha(String login) throws SQLException, MessagingException, Exception{
        String senha = this.gerarSenhaNova();
        Usuario usuario = this.banco.buscarUsuario(login);
        if(usuario == null){
            throw new Exception("O usuário '"+login+"' não existe!");
        }
        String conteudo = "Olá "+usuario.getNome()+", veja abaixo suas informações de usuários, contendo "
                + "sua senha temporária "
                + "gerada automaticamente pelo Sistema.\n\n"
                + "LOGIN: "+usuario.getLogin()+"\n"
                + "SENHA: "+senha+".\n\n"
                + ""
                + "Use sua senha temporária para criar uma nova senha em 'Alterar Dados do Usuário', no "
                + "menu 'Sistema'.\n\n"
                + "Obrigado!";
        
        Email.Enviar("no-reply@bcsolutions.com.br", "SWaj6PhasAcr7r8X", usuario.getEmail(), "Recuperar Senha", conteudo);
        usuario.setSenha(this.hash.HashSHA512(senha));
        this.banco.editarUsuario(usuario);
    }
    
    private String gerarSenhaNova(){
        Random r = new Random();
        String senha = "";
        for(int i=0;i<8;i++){
            int v = r.nextInt(10);
            senha += v;
        }
        return senha;
    }
    
    public Usuario getUsuario(String login) throws Exception{
        
        return this.banco.buscarUsuario(login);
    }
    
    public void criarUsuarioPadrao() throws SQLException, Exception{
        Usuario usuario = this.banco.buscarUsuario("admin");
        Cliente cliente = this.banco.getCliente(1);
        if(usuario == null){
            String senha = this.hash.HashSHA512("admin");
            this.banco.criarUsuarioPadrao(senha);
            this.banco.criarClientePadrao();
            this.banco.criarLogoPadrao();
        }if(cliente == null){
        
        }
    }
    
    public Empresa salvarEmpresa(Empresa empresa){
        return this.banco.salvarEmpresa(empresa);
    }
    
    public Empresa editarEmpresa(Empresa empresa){
        return this.banco.editarEmpresa(empresa);
    }
    
    public Empresa getEmpresa(){
        return this.banco.getEmpresa();
    }
}//Fim da Classe
