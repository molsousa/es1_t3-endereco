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
    
    public int cadastrarLogradouro(Connection conn, Logradouro logradouro) throws Exception {
        LogradouroDAO logradouroDAO = new LogradouroDAO();
        logradouro.setNome(logradouro.getNome().trim());
        
        int idLogradouro = logradouroDAO.cadastrarLogradouro(conn, logradouro);
        
        return idLogradouro;
    }

    public Logradouro recuperarLogradouro(Connection conn, int idLogradouro) throws Exception {
        LogradouroDAO logradouroDAO = new LogradouroDAO();
        Logradouro logradouro;
        
        logradouro = logradouroDAO.buscarLogradouro(conn, idLogradouro);
        
        if(logradouro == null) {
            throw new LogradouroNaoEncontradoException(idLogradouro);
        }
        
        return logradouro;
    }
    
    public int recuperarLogradouro(Connection conn, Logradouro logradouro) throws Exception {
        LogradouroDAO logradouroDAO = new LogradouroDAO();
        logradouro.setNome(logradouro.getNome().trim());
        
        try{
            return logradouroDAO.buscarLogradouro(conn, logradouro);
            
        } catch (LogradouroNaoEncontradoException e) {
            throw new LogradouroNaoEncontradoException(logradouro.getNome());
        }
    }
}
