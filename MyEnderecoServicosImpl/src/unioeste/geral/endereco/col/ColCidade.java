/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import java.sql.SQLException;
import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.dao.CidadeDAO;
import unioeste.geral.endereco.exception.CidadeNaoEncontradaException;

/**
 *
 * @author molsousa
 */
public class ColCidade {
    
    public int cadastrarCidade(Connection conn, Cidade cidade) throws Exception {
        int idCidade;
        CidadeDAO cidadeDAO = new CidadeDAO();
        
        cidade.setNome(cidade.getNome().trim());
        
        idCidade = cidadeDAO.cadastrarCidade(conn, cidade);
        
        return idCidade;
    }
    
    public Cidade recuperarCidade(Connection conn, int idCidade) throws Exception {
        Cidade cidade;
        CidadeDAO cidadeDAO = new CidadeDAO();
        
        cidade = cidadeDAO.buscarCidade(conn, idCidade);
        
        if(cidade == null) {
            throw new CidadeNaoEncontradaException(idCidade);
        }
        
        return cidade;
    }
    
    public int recuperarCidade(Connection conn, Cidade cidade) throws Exception {
        CidadeDAO cidadeDAO = new CidadeDAO();
        
        int idCidade = cidadeDAO.buscarCidade(conn, cidade);
        
        return idCidade;
    }
}
