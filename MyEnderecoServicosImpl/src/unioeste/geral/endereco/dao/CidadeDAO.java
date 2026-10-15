/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unioeste.geral.endereco.bo.Cidade;

/**
 *
 * @author molsousa
 */
public class CidadeDAO {

    public CidadeDAO() {
    }

    public Cidade buscarCidadeEndereco(Connection conn, int idCidade) {
        String SQL = "SELECT cid.nome_cidade, cid.sigla_uf FROM \"Cidade\" cid "
                + "JOIN \"Endereco\" ende ON ende.id_cidade = cid.id_cidade "
                + "WHERE cid.id_cidade = ?";

        Cidade cidade = null;

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, idCidade);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cidade = new Cidade();

                    String siglaUF = rs.getString("sigla_uf");
                    cidade.setNome(rs.getString("nome_cidade"));

                    UnidadeFederativaDAO ufDAO = new UnidadeFederativaDAO();
                    cidade.setUnidadeFederativa(ufDAO.buscarUF(conn, siglaUF));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cidade: " + e);
        }

        return cidade;
    }
    
    public Cidade buscarCidade(Connection conn, int idCidade) {
        String SQL = "SELECT cid.nome_cidade, cid.sigla_uf FROM \"Cidade\" cid "
                + "WHERE cid.id_cidade = ?";

        Cidade cidade = null;

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, idCidade);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cidade = new Cidade();

                    String siglaUF = rs.getString("sigla_uf");
                    cidade.setNome(rs.getString("nome_cidade"));

                    UnidadeFederativaDAO ufDAO = new UnidadeFederativaDAO();
                    cidade.setUnidadeFederativa(ufDAO.buscarUF(conn, siglaUF));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cidade: " + e);
        }

        return cidade;
    }

}
