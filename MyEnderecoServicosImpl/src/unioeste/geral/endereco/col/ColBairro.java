/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import java.sql.SQLException;
import unioeste.geral.endereco.bo.Bairro;
import unioeste.geral.endereco.dao.BairroDAO;
import unioeste.geral.endereco.exception.BairroNaoEncontradoException;
import unioeste.geral.endereco.exception.EnderecoException;

/**
 *
 * @author molsousa
 */
public class ColBairro {

    public int cadastrarBairro(Connection conn, Bairro bairro) throws Exception {
        BairroDAO bairroDAO = new BairroDAO();
        int idBairro;

        if (bairro == null || bairro.getNome().trim().isEmpty()) {
            throw new EnderecoException("Objeto bairro não foi devidamente carregado");
        }

        bairro.setNome(bairro.getNome().trim());

        try {
            idBairro = bairroDAO.cadastrarBairro(conn, bairro);
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar bairro: " + e.getMessage());
        } catch (EnderecoException e) {
            throw new EnderecoException("Ocorreu um erro ao cadastrar o bairro");
        }

        return idBairro;
    }

    public Bairro recuperarBairro(Connection conn, int idBairro) throws Exception {
        BairroDAO bairroDAO = new BairroDAO();
        Bairro bairro;

        if (idBairro <= 0) {
            throw new BairroNaoEncontradoException(BairroNaoEncontradoException.CodigoErro.ID_INVALIDO, idBairro);
        }

        try {
            bairro = bairroDAO.buscarBairro(conn, idBairro);
        } catch (SQLException e) {
            throw new SQLException("Ocorreu um erro ao tentar recuperar bairro no banco: " + e.getMessage());
        } catch (EnderecoException e) {
            throw new BairroNaoEncontradoException("Bairro não existe no banco de dados");
        }

        return bairro;
    }

    public int recuperarBairro(Connection conn, Bairro bairro) throws Exception {
        BairroDAO bairroDAO = new BairroDAO();
        int idBairro;

        if (bairro == null || bairro.getNome().trim().isEmpty()) {
            throw new EnderecoException("Objeto bairro não foi devidamente carregado");
        }

        bairro.setNome(bairro.getNome().trim());

        try {
            idBairro = bairroDAO.buscarBairro(conn, bairro);
        } catch (SQLException e) {
            throw new SQLException("Ocorreu um erro ao tentar recuperar bairro no banco: " + e.getMessage());
        } catch (EnderecoException e) {
            throw new BairroNaoEncontradoException(BairroNaoEncontradoException.CodigoErro.NAO_ENCONTRADO, bairro.getNome());
        }

        return idBairro;
    }
}
