/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import unioeste.geral.endereco.bo.TipoLogradouro;
import unioeste.geral.endereco.dao.TipoLogradouroDAO;
import unioeste.geral.endereco.exception.TipoLogradouroInvalidoException;
import unioeste.geral.endereco.exception.TipoLogradouroNaoEncontradoException;

/**
 *
 * @author molsousa
 */
public class ColTipoLogradouro {
    
    public String cadastrarTipoLogradouro(Connection conn, TipoLogradouro tipoLogradouro) throws Exception {
        TipoLogradouroDAO tlDAO = new TipoLogradouroDAO();
        tipoLogradouro.setSigla(tipoLogradouro.getSigla().trim());
        
        if(tipoLogradouro.getSigla().length() >= 4){
            throw new TipoLogradouroInvalidoException(tipoLogradouro.getSigla());
        }
        
        tipoLogradouro.setNome(tipoLogradouro.getNome().trim());
        String siglaTipoLogradouro = tlDAO.cadastrarTipoLogradouro(conn, tipoLogradouro);
        
        return siglaTipoLogradouro;
    }

    public TipoLogradouro recuperarTipoLogradouro(Connection conn, String siglaTipoLogradouro) throws Exception {
        TipoLogradouroDAO tipoLogradouroDAO = new TipoLogradouroDAO();
        TipoLogradouro tipoLogradouro;

        tipoLogradouro = tipoLogradouroDAO.buscarTipoLogradouro(conn, siglaTipoLogradouro);

        if (tipoLogradouro == null) {
            throw new TipoLogradouroNaoEncontradoException(siglaTipoLogradouro);
        }

        return tipoLogradouro;
    }
}
