/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import unioeste.geral.endereco.bo.Bairro;
import unioeste.geral.endereco.dao.BairroDAO;
import unioeste.geral.endereco.exception.BairroNaoEncontradoException;
import unioeste.geral.endereco.exception.EnderecoException;

/**
 *
 * @author molsousa
 */
public class ColBairro {

    public Bairro recuperarBairro(Connection conn, int idBairro) throws EnderecoException {
        BairroDAO bairroDAO = new BairroDAO();
        Bairro bairro;
        
        bairro = bairroDAO.buscarBairro(conn, idBairro);
        
        if(bairro == null) {
            throw new BairroNaoEncontradoException(idBairro);
        }
        
        return bairro;
    }
}
