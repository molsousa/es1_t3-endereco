/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import unioeste.geral.endereco.bo.Logradouro;
import unioeste.geral.endereco.dao.LogradouroDAO;
import unioeste.geral.endereco.exception.LogradouroNaoEncontradoException;

/**
 *
 * @author molsousa
 */
public class ColLogradouro {

    public Logradouro recuperarLogradouro(Connection conn, int idLogradouro) throws Exception {
        LogradouroDAO logradouroDAO = new LogradouroDAO();
        Logradouro logradouro;
        
        logradouro = logradouroDAO.buscarLogradouro(conn, idLogradouro);
        
        if(logradouro == null) {
            throw new LogradouroNaoEncontradoException(idLogradouro);
        }
        
        return logradouro;
    }
}
