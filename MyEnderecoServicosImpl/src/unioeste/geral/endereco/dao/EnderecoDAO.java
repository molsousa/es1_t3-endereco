/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.bo.Logradouro;
import unioeste.geral.endereco.bo.Bairro;
import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.col.ColBairro;
import unioeste.geral.endereco.col.ColCidade;
import unioeste.geral.endereco.col.ColLogradouro;
import unioeste.geral.endereco.exception.CidadeNaoEncontradaException;
import unioeste.geral.endereco.exception.LogradouroNaoEncontradoException;

/**
 *
 * @author molsousa
 */
public class EnderecoDAO {

    public EnderecoDAO() {
    }

    public Endereco buscarEnderecoPorCEP(Connection conn, String cep)
            throws Exception {
        String SQL = "SELECT ende.cep, ende.id_bairro, ende.id_logradouro, ende.id_cidade FROM "
                + "\"Endereco\" ende WHERE ende.cep = ?";

        Endereco endereco = null;

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, cep);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    endereco = new Endereco();

                    endereco.setCep(cep);
                    ColCidade colCidade = new ColCidade();
                    ColLogradouro colLogradouro = new ColLogradouro();
                    ColBairro colBairro = new ColBairro();

                    int idCidade = rs.getInt("id_cidade");
                    int idLogradouro = rs.getInt("id_logradouro");
                    int idBairro = rs.getInt("id_bairro");

                    Cidade cidade = colCidade.recuperarCidade(conn, idCidade);
                    Logradouro logradouro = colLogradouro.recuperarLogradouro(conn, idLogradouro);
                    Bairro bairro = colBairro.recuperarBairro(conn, idBairro);

                    endereco.setCidade(cidade);
                    endereco.setLogradouro(logradouro);
                    endereco.setBairro(bairro);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar endereco: " + e);
            return null;
        }

        return endereco;
    }

    public Endereco buscarEnderecoPorID(Connection conn, int idEndereco) {
        String SQL = "SELECT ende.cep, ende.id_bairro, ende.id_logradouro, ende.id_cidade FROM "
                + "\"Endereco\" ende WHERE ende.id_endereco = ?";

        Endereco endereco = null;

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, idEndereco);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    endereco = new Endereco();

                    CidadeDAO cidadeDAO = new CidadeDAO();
                    LogradouroDAO logradouroDAO = new LogradouroDAO();
                    BairroDAO bairroDAO = new BairroDAO();

                    int idCidade = rs.getInt("id_cidade");
                    int idLogradouro = rs.getInt("id_logradouro");
                    int idBairro = rs.getInt("id_bairro");

                    Cidade cidade = cidadeDAO.buscarCidade(conn, idCidade);
                    Logradouro logradouro = logradouroDAO.buscarLogradouro(conn, idLogradouro);
                    Bairro bairro = bairroDAO.buscarBairro(conn, idBairro);

                    endereco.setCep(rs.getString("cep"));
                    endereco.setCidade(cidade);
                    endereco.setLogradouro(logradouro);
                    endereco.setBairro(bairro);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar endereco: " + e);
            return null;
        }

        return endereco;
    }
}
