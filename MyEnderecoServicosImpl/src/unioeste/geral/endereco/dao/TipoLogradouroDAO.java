/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unioeste.geral.endereco.bo.TipoLogradouro;
import unioeste.geral.endereco.exception.EnderecoException;

/**
 *
 * @author molsousa
 */
public class TipoLogradouroDAO {

    public TipoLogradouroDAO() {
    }

    public String cadastrarTipoLogradouro(Connection conn, TipoLogradouro tipoLogradouro) throws SQLException {
        String SQL = "INSERT INTO \"TipoLogradouro\" (sigla_tipo_logradouro,nome_tipo_logradouro) "
                + "VALUES (?,?) RETURNING sigla_tipo_logradouro";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, tipoLogradouro.getSigla());
            stmt.setString(2, tipoLogradouro.getNome());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("sigla_tipo_logradouro");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar tipo logradouro: " + e.getMessage(), e);
        }

        throw new SQLException("Erro inesperado: o banco não retornou a sigla do tipo de logradouro.");
    }

    public String buscarTipoLogradouro(Connection conn, TipoLogradouro tipoLogradouro) throws Exception {
        String SQL = "SELECT sigla_tipo_logradouro FROM \"TipoLogradouro\" WHERE nome_tipo_logradouro = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, tipoLogradouro.getNome());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String siglaTipoLogradouro = rs.getString("sigla_tipo_logradouro");

                    return siglaTipoLogradouro;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar tipo de logradouro: " + e);
        }

        throw new EnderecoException("Erro ao retornar sigla do tipo de logradouro");
    }

    public TipoLogradouro buscarTipoLogradouro(Connection conn, String siglaTipoLogradouro) throws Exception {
        String SQL = "SELECT tiplog.nome_tipo_logradouro FROM \"TipoLogradouro\" tiplog "
                + "WHERE tiplog.sigla_tipo_logradouro = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, siglaTipoLogradouro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TipoLogradouro tipoLogradouro = new TipoLogradouro();

                    tipoLogradouro.setSigla(siglaTipoLogradouro);
                    tipoLogradouro.setNome(rs.getString("nome_tipo_logradouro"));

                    return tipoLogradouro;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar tipo de logradouro: " + e);
        }

        throw new EnderecoException("Erro ao retornar tipo de logradouro");
    }

}
