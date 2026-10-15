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

/**
 *
 * @author molsousa
 */
public class LogradouroDAO {

    public LogradouroDAO() {
    }

    public Logradouro buscarLogradouro(Connection conn, int idLogradouro) {
        String SQL = "SELECT log.nome_logradouro, log.sigla_tipo_logradouro "
                + "FROM \"Logradouro\" log JOIN \"Endereco\" ende ON "
                + "ende.id_logradouro = log.id_logradouro WHERE "
                + "log.id_logradouro = ?";

        Logradouro logradouro = null;

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, idLogradouro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    logradouro = new Logradouro();

                    String siglaTipoLogradouro = rs.getString("sigla_tipo_logradouro");
                    logradouro.setNome(rs.getString("nome_logradouro"));

                    TipoLogradouroDAO tipLogDAO = new TipoLogradouroDAO();
                    
                    logradouro.setTipoLogradouro(tipLogDAO.buscarTipoLogradouro(conn, siglaTipoLogradouro));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar logradouro: " + e);
        }

        return logradouro;
    }

}
