/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.auxiliar;

import persistencia.entidades.Produto;

/**
 *
 * @author Bruno
 */
public interface NotificacaoListener {
    
    public void notificarFaltaProduto(Produto produto);
    
}
