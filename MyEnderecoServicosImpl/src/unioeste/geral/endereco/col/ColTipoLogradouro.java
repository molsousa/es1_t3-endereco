/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import unioeste.geral.endereco.bo.TipoLogradouro;
import unioeste.geral.endereco.dao.TipoLogradouroDAO;
import unioeste.geral.endereco.exception.EnderecoException;
import unioeste.geral.endereco.exception.TipoLogradouroInvalidoException;
import unioeste.geral.endereco.exception.TipoLogradouroNaoEncontradoException;

/**
 *
 * @author molsousa
 */
public class ColTipoLogradouro {

    public String cadastrarTipoLogradouro(Connection conn, TipoLogradouro tipoLogradouro) throws Exception {
        TipoLogradouroDAO tlDAO = new TipoLogradouroDAO();

        if (tipoLogradouro == null || tipoLogradouro.getSigla() == null || tipoLogradouro.getSigla().trim().isEmpty()
                || tipoLogradouro.getNome() == null || tipoLogradouro.getNome().trim().isEmpty()) {
            System.out.println("erro10");

            throw new EnderecoException("Objeto tipo logradouro não foi devidamente carregado");
        }

        tipoLogradouro.setSigla(tipoLogradouro.getSigla().trim());

        if (tipoLogradouro.getSigla().length() >= 4) {
            throw new TipoLogradouroInvalidoException(tipoLogradouro.getSigla());
        }

        tipoLogradouro.setNome(tipoLogradouro.getNome().trim());

        try {
            return tlDAO.buscarTipoLogradouro(conn, tipoLogradouro);

        } catch (TipoLogradouroNaoEncontradoException e) {
            return tlDAO.cadastrarTipoLogradouro(conn, tipoLogradouro);
        }
    }

    public TipoLogradouro recuperarTipoLogradouro(Connection conn, String siglaTipoLogradouro) throws Exception {
        TipoLogradouroDAO tipoLogradouroDAO = new TipoLogradouroDAO();
        TipoLogradouro tipoLogradouro;

        if (siglaTipoLogradouro == null || siglaTipoLogradouro.trim().isEmpty()) {
            throw new EnderecoException("Objeto tipo logradouro não foi devidamente carregado");
        }

        siglaTipoLogradouro = siglaTipoLogradouro.trim();

        tipoLogradouro = tipoLogradouroDAO.buscarTipoLogradouro(conn, siglaTipoLogradouro);

        if (tipoLogradouro == null) {
            throw new TipoLogradouroNaoEncontradoException(siglaTipoLogradouro);
        }

        return tipoLogradouro;
    }
}
