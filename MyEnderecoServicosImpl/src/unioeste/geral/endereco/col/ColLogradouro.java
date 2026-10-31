/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import unioeste.geral.endereco.bo.Logradouro;
import unioeste.geral.endereco.dao.LogradouroDAO;
import unioeste.geral.endereco.exception.CidadeNaoEncontradaException;
import unioeste.geral.endereco.exception.EnderecoException;
import unioeste.geral.endereco.exception.LogradouroNaoEncontradoException;

/**
 *
 * @author molsousa
 */
public class ColLogradouro {

    public int cadastrarLogradouro(Connection conn, Logradouro logradouro) throws Exception {
        LogradouroDAO logradouroDAO = new LogradouroDAO();
        int idLogradouro;

        if (logradouro == null || logradouro.getNome().trim().isEmpty()) {
            throw new EnderecoException("Objeto logradouro não foi devidamente carregado");
        }

        logradouro.setNome(logradouro.getNome().trim());

        try {
            idLogradouro = logradouroDAO.cadastrarLogradouro(conn, logradouro);
        } catch (SQLException e) {

            throw new SQLException("Erro ao cadastrar logradouro: " + e.getMessage());
        } catch (EnderecoException e) {
            throw new LogradouroNaoEncontradoException("Ocorreu um erro ao cadastrar logradouro: " + e.getMessage());
        }

        return idLogradouro;
    }

    public Logradouro recuperarLogradouro(Connection conn, int idLogradouro) throws Exception {
        LogradouroDAO logradouroDAO = new LogradouroDAO();
        Logradouro logradouro;

        if (idLogradouro <= 0) {
            throw new LogradouroNaoEncontradoException(LogradouroNaoEncontradoException.CodigoErro.ID_INVALIDO, idLogradouro);
        }

        try {
            logradouro = logradouroDAO.buscarLogradouro(conn, idLogradouro);
        } catch (SQLException e) {
            throw new SQLException("Ocorreu um erro ao tentar recuperar o logradouro no banco: ", e.getMessage());
        } catch (EnderecoException e) {
            throw new LogradouroNaoEncontradoException("Logradouro não existe no banco de dados");
        }

        return logradouro;
    }

    public int recuperarLogradouro(Connection conn, Logradouro logradouro) throws Exception {
        LogradouroDAO logradouroDAO = new LogradouroDAO();

        if (logradouro == null || logradouro.getNome().trim().isEmpty()) {
            throw new EnderecoException("Objeto logradouro não foi devidamente carregado");
        }

        logradouro.setNome(logradouro.getNome().trim());

        try {
            return logradouroDAO.buscarLogradouro(conn, logradouro);
        } catch (SQLException e) {
            throw new SQLException("Ocorreu um erro ao tentar recuperar o logradouro no banco: ", e.getMessage());
        } catch (EnderecoException e) {
            throw new LogradouroNaoEncontradoException(LogradouroNaoEncontradoException.CodigoErro.NAO_ENCONTRADO, logradouro.getNome());
        }
    }
}
