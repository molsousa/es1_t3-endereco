/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.Bairro;
import java.sql.*;
import unioeste.geral.endereco.exception.EnderecoException;

/**
 *
 * @author molsousa
 */
public class BairroDAO {

    public BairroDAO() {
    }

    public int cadastrarBairro(Connection conn, Bairro bairro) throws SQLException {
        String SQL = "INSERT INTO \"Bairro\" (nome_bairro) VALUES (?) RETURNING id_bairro";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, bairro.getNome());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_bairro");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar bairro: " + e.getMessage(), e);
        }

        throw new SQLException("Erro inesperado: o banco não retornou o id do bairro cadastrado.");
    }

    public int buscarBairro(Connection conn, Bairro bairro) throws Exception {
        String SQL = "SELECT id_bairro FROM \"Bairro\" WHERE nome_bairro = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, bairro.getNome());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idBairro = rs.getInt("id_bairro");
                    
                    return idBairro;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar bairro: " + e);
        }

        throw new EnderecoException("Erro ao retornar id do bairro");
    }

    public Bairro buscarBairro(Connection conn, int idBairro) throws Exception {
        String SQL = "SELECT b.nome_bairro FROM \"Bairro\" b "
                + "WHERE b.id_bairro = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, idBairro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Bairro bairro = new Bairro();
                    bairro.setNome(rs.getString("nome_bairro"));
                    
                    return bairro;
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar bairro: " + e);
        }

        throw new EnderecoException("Erro ao retornar bairro");
    }
}
