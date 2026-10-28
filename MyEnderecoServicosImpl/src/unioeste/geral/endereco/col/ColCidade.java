/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import java.sql.SQLException;
import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.dao.CidadeDAO;
import unioeste.geral.endereco.exception.CidadeNaoEncontradaException;
import unioeste.geral.endereco.exception.EnderecoException;

/**
 *
 * @author molsousa
 */
public class ColCidade {

    public int cadastrarCidade(Connection conn, Cidade cidade) throws Exception {
        int idCidade;
        CidadeDAO cidadeDAO = new CidadeDAO();

        cidade.setNome(cidade.getNome().trim());

        try {
            idCidade = cidadeDAO.cadastrarCidade(conn, cidade);
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar cidade: " + e.getMessage());
        } catch (EnderecoException e) {
            throw new CidadeNaoEncontradaException("Ocorreu um erro ao cadastrar a cidade");
        }

        return idCidade;
    }

    public Cidade recuperarCidade(Connection conn, int idCidade) throws Exception {
        Cidade cidade;
        CidadeDAO cidadeDAO = new CidadeDAO();

        if (idCidade <= 0) {
            throw new CidadeNaoEncontradaException(CidadeNaoEncontradaException.CodigoErro.ID_INVALIDO, idCidade);
        }

        try {
            cidade = cidadeDAO.buscarCidade(conn, idCidade);
        } catch (SQLException e) {
            throw new SQLException("Ocorreu um erro ao tentar recuperar a cidade no banco: " + e.getMessage());
        } catch (EnderecoException e) {
            throw new CidadeNaoEncontradaException("Cidade não existe no banco de dados");
        }

        return cidade;
    }

    public int recuperarCidade(Connection conn, Cidade cidade) throws Exception {
        CidadeDAO cidadeDAO = new CidadeDAO();
        int idCidade;

        try {
            idCidade = cidadeDAO.buscarCidade(conn, cidade);
        } catch (SQLException e) {
            throw new SQLException("Ocorreu um erro ao tentar recuperar cidade no banco: " + e.getMessage());
        } catch (EnderecoException e) {
            throw new CidadeNaoEncontradaException(CidadeNaoEncontradaException.CodigoErro.NAO_ENCONTRADO, cidade.getNome());
        }

        return idCidade;
    }
}
