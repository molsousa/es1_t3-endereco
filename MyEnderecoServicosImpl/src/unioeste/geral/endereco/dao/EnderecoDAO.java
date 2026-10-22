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
import unioeste.geral.endereco.exception.EnderecoException;

/**
 *
 * @author molsousa
 */
public class EnderecoDAO {

    public EnderecoDAO() {
    }

    public int cadastrarEndereco(Endereco endereco) {
        String SQL = "INSERT INTO \"Endereco\" (cep, id_bairro, id_logradouro, id_cidade) "
                + "VALUES (?,?,?,?) RETURNING id_endereco";

        // TODO finalizar Bairro, Cidade, Logradouro
        return 0;
    }

    public Endereco buscarEnderecoPorCEP(Connection conn, String cep)
            throws Exception {
        String SQL = "SELECT ende.cep, ende.id_bairro, ende.id_logradouro, ende.id_cidade FROM "
                + "\"Endereco\" ende WHERE ende.cep = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, cep);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Endereco endereco = new Endereco();

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

                    return endereco;
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar endereço: " + e);
        }

        throw new EnderecoException("Erro ao retornar endereço");
    }

    public Endereco buscarEnderecoPorID(Connection conn, int idEndereco) throws Exception {
        String SQL = "SELECT ende.cep, ende.id_bairro, ende.id_logradouro, ende.id_cidade FROM "
                + "\"Endereco\" ende WHERE ende.id_endereco = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, idEndereco);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Endereco endereco = new Endereco();

                    ColCidade colCidade = new ColCidade();
                    ColLogradouro colLogradouro = new ColLogradouro();
                    ColBairro colBairro = new ColBairro();

                    int idCidade = rs.getInt("id_cidade");
                    int idLogradouro = rs.getInt("id_logradouro");
                    int idBairro = rs.getInt("id_bairro");

                    Cidade cidade = colCidade.recuperarCidade(conn, idCidade);
                    Logradouro logradouro = colLogradouro.recuperarLogradouro(conn, idLogradouro);
                    Bairro bairro = colBairro.recuperarBairro(conn, idBairro);

                    endereco.setCep(rs.getString("cep"));
                    endereco.setCidade(cidade);
                    endereco.setLogradouro(logradouro);
                    endereco.setBairro(bairro);

                    return endereco;
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar endereço: "+ e);
        }

        throw new EnderecoException("Erro ao retornar endereço");
    }
}
