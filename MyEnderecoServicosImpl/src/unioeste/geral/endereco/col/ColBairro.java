/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import unioeste.geral.endereco.bo.Bairro;
import unioeste.geral.endereco.dao.BairroDAO;
import unioeste.geral.endereco.exception.BairroNaoEncontradoException;

/**
 *
 * @author molsousa
 */
public class ColBairro {

    public int cadastrarBairro(Connection conn, Bairro bairro) throws Exception {
        BairroDAO bairroDAO = new BairroDAO();

        bairro.setNome(bairro.getNome().trim());
        int idBairro = bairroDAO.cadastrarBairro(conn, bairro);

        return idBairro;
    }

    public Bairro recuperarBairro(Connection conn, int idBairro) throws Exception {
        BairroDAO bairroDAO = new BairroDAO();
        Bairro bairro;

        bairro = bairroDAO.buscarBairro(conn, idBairro);

        if (bairro == null) {
            throw new BairroNaoEncontradoException(idBairro);
        }

        return bairro;
    }
    
    public int recuperarBairro(Connection conn, Bairro bairro) throws Exception {
        BairroDAO bairroDAO = new BairroDAO();
        
        int idBairro = bairroDAO.buscarBairro(conn, bairro);
        
        if(bairro == null) {
            throw new BairroNaoEncontradoException(bairro.getNome());
        }
        
        return idBairro;
    }
}
