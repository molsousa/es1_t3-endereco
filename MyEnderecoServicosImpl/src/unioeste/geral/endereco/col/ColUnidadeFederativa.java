/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import unioeste.geral.endereco.bo.UnidadeFederativa;
import unioeste.geral.endereco.dao.UnidadeFederativaDAO;
import unioeste.geral.endereco.exception.UnidadeFederativaInvalidaException;
import unioeste.geral.endereco.exception.UnidadeFederativaNaoEncontradaException;

/**
 *
 * @author molsousa
 */
public class ColUnidadeFederativa {

    public String cadastrarUF(Connection conn, UnidadeFederativa uf) throws Exception {
        UnidadeFederativaDAO ufDAO = new UnidadeFederativaDAO();
        uf.setSigla(uf.getSigla().trim());
        
        if(uf.getSigla().length() >= 3) {
            throw new UnidadeFederativaInvalidaException(uf.getSigla());
        }
        
        uf.setNome(uf.getNome().trim());
        String siglaUF = ufDAO.cadastrarUF(conn, uf);
        
        return siglaUF;
    }
    
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
