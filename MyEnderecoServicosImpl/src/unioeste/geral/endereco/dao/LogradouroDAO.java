/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unioeste.geral.endereco.bo.Logradouro;
import unioeste.geral.endereco.bo.TipoLogradouro;
import unioeste.geral.endereco.col.ColTipoLogradouro;
import unioeste.geral.endereco.exception.EnderecoException;
import unioeste.geral.endereco.exception.LogradouroNaoEncontradoException;
import unioeste.geral.endereco.exception.TipoLogradouroInvalidoException;

/**
 *
 * @author molsousa
 */
public class LogradouroDAO {

    public LogradouroDAO() {
    }

    public int cadastrarLogradouro(Connection conn, Logradouro logradouro)
            throws SQLException, EnderecoException, Exception {
        String SQL = "INSERT INTO \"Logradouro\" (nome_logradouro,sigla_tipo_logradouro) "
                + "VALUES (?,?) RETURNING id_logradouro";

        ColTipoLogradouro colTipoLogradouro = new ColTipoLogradouro();

        logradouro.getTipoLogradouro().setNome(logradouro.getTipoLogradouro().getNome().trim());

        try {
            colTipoLogradouro.recuperarTipoLogradouro(conn, logradouro.getTipoLogradouro().getSigla());
        } catch (TipoLogradouroInvalidoException e) {
            colTipoLogradouro.cadastrarTipoLogradouro(conn, logradouro.getTipoLogradouro());
        }

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, logradouro.getNome());
            stmt.setString(2, logradouro.getTipoLogradouro().getSigla());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_logradouro");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar logradouro: " + e.getMessage(), e);
        }

        throw new EnderecoException("Erro ao retornar o id do logradouro");
    }

    public int buscarLogradouro(Connection conn, Logradouro logradouro) throws Exception {
        String SQL = "SELECT id_logradouro FROM \"Logradouro\" WHERE nome_logradouro = ? AND sigla_tipo_logradouro = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, logradouro.getNome());
            stmt.setString(2, logradouro.getTipoLogradouro().getSigla());

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    int idLogradouro = rs.getInt("id_logradouro");

                    return idLogradouro;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar logradouro: " + e);
        }

        throw new EnderecoException("Erro ao retornar id do logradouro");
    }

    public Logradouro buscarLogradouro(Connection conn, int idLogradouro) throws Exception {
        String SQL = "SELECT log.nome_logradouro, log.sigla_tipo_logradouro "
                + "FROM \"Logradouro\" log WHERE log.id_logradouro = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, idLogradouro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ColTipoLogradouro colTipoLogradouro = new ColTipoLogradouro();
                    TipoLogradouro tipoLogradouro;

                    Logradouro logradouro = new Logradouro();

                    String siglaTipoLogradouro = rs.getString("sigla_tipo_logradouro");
                    logradouro.setNome(rs.getString("nome_logradouro"));
                    tipoLogradouro = colTipoLogradouro.recuperarTipoLogradouro(conn, siglaTipoLogradouro);

                    logradouro.setTipoLogradouro(tipoLogradouro);

                    return logradouro;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar logradouro: " + e);
        }

        throw new EnderecoException("Erro ao retornar logradouro");
    }

}
