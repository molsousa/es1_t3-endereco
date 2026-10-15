/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import unioeste.geral.endereco.bo.UnidadeFederativa;
import unioeste.geral.endereco.dao.UnidadeFederativaDAO;
import unioeste.geral.endereco.exception.UnidadeFederativaNaoEncontradaException;

/**
 *
 * @author molsousa
 */
public class ColUnidadeFederativa {

    public UnidadeFederativa recuperarUnidadeFederativa(Connection conn, String siglaUF) throws Exception {
        UnidadeFederativaDAO ufDAO = new UnidadeFederativaDAO();
        UnidadeFederativa uf;

        uf = ufDAO.buscarUF(conn, siglaUF);

        if (uf == null) {
            throw new UnidadeFederativaNaoEncontradaException(siglaUF);
        }

        return uf;
    }
}
