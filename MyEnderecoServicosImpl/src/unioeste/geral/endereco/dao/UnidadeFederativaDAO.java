/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unioeste.geral.endereco.bo.UnidadeFederativa;
import unioeste.geral.endereco.exception.EnderecoException;

/**
 *
 * @author molsousa
 */
public class UnidadeFederativaDAO {

    public UnidadeFederativaDAO() {
    }

    public String cadastrarUF(Connection conn, UnidadeFederativa unidadeFederativa) throws SQLException {
        String SQL = "INSERT INTO \"UnidadeFederativa\" (sigla_uf,nome_uf) VALUES (?,?) RETURNING sigla_uf";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, unidadeFederativa.getSigla());
            stmt.setString(1, unidadeFederativa.getNome());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("sigla_uf");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar unidade federativa: " + e.getMessage(), e);
        }

        throw new SQLException("Erro inesperado: o banco não retornou a sigla da unidade federativa.");
    }

    public String buscarUF(Connection conn, UnidadeFederativa unidadeFederativa) throws Exception {
        String SQL = "SELECT nome_uf FROM \"UnidadeFederativa\" WHERE sigla_uf = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, unidadeFederativa.getSigla());

            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    String siglaUF = rs.getString("sigla_uf");

                    return siglaUF;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar unidade federativa: " + e);
        }

        throw new EnderecoException("Erro ao retornar sigla de unidade federativa");
    }

    public UnidadeFederativa buscarUF(Connection conn, String siglaUF) throws Exception {
        String SQL = "SELECT uf.sigla_uf, uf.nome_uf FROM \"UnidadeFederativa\" uf "
                + "WHERE uf.sigla_uf = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, siglaUF);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    UnidadeFederativa uf = new UnidadeFederativa();

                    uf.setSigla(rs.getString("sigla_uf"));
                    uf.setNome(rs.getString("nome_uf"));
                    
                    return uf;
                }

            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar unidade federativa: " + e);
        }

        throw new EnderecoException("Erro ao retornar unidade federativa");
    }
}
