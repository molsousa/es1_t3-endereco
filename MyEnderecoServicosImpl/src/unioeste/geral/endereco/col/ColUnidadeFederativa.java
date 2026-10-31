/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import unioeste.geral.endereco.bo.UnidadeFederativa;
import unioeste.geral.endereco.dao.UnidadeFederativaDAO;
import unioeste.geral.endereco.exception.EnderecoException;
import unioeste.geral.endereco.exception.UnidadeFederativaInvalidaException;
import unioeste.geral.endereco.exception.UnidadeFederativaNaoEncontradaException;

/**
 *
 * @author molsousa
 */
public class ColUnidadeFederativa {

    public String cadastrarUF(Connection conn, UnidadeFederativa uf) throws Exception {
        UnidadeFederativaDAO ufDAO = new UnidadeFederativaDAO();

        if (uf == null || uf.getSigla() == null || uf.getSigla().trim().isEmpty()
                || uf.getNome() == null || uf.getNome().trim().isEmpty()) {
            throw new EnderecoException("Objeto unidade federativa não foi devidamente carregado");
        }

        uf.setSigla(uf.getSigla().trim());

        if (uf.getSigla().length() >= 3) {
            throw new UnidadeFederativaInvalidaException(uf.getSigla());
        }

        uf.setNome(uf.getNome().trim());
        String siglaUF = ufDAO.cadastrarUF(conn, uf);

        return siglaUF;
    }

    public UnidadeFederativa recuperarUnidadeFederativa(Connection conn, String siglaUF) throws Exception {
        UnidadeFederativaDAO ufDAO = new UnidadeFederativaDAO();
        UnidadeFederativa uf;

        if (siglaUF == null || siglaUF.trim().isEmpty()) {
            throw new EnderecoException("Objeto unidade federativa não foi devidamente carregado");
        }

        siglaUF = siglaUF.trim();

        uf = ufDAO.buscarUF(conn, siglaUF);

        if (uf == null) {
            throw new UnidadeFederativaNaoEncontradaException(siglaUF);
        }

        return uf;
    }
}
