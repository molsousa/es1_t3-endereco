/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.dao.CidadeDAO;
import unioeste.geral.endereco.exception.CidadeNaoEncontradaException;
import unioeste.geral.endereco.exception.EnderecoException;

/**
 *
 * @author molsousa
 */
public class ColCidade {
    
    public Cidade recuperarCidade(Connection conn, int idCidade) throws EnderecoException {
        Cidade cidade;
        CidadeDAO cidadeDAO = new CidadeDAO();
        
        cidade = cidadeDAO.buscarCidadeEndereco(conn, idCidade);
        
        if(cidade == null) {
            throw new CidadeNaoEncontradaException(idCidade);
        }
        
        return cidade;
    }
}
