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

/**
 *
 * @author molsousa
 */
public class LogradouroDAO {

    public LogradouroDAO() {
    }

    public Logradouro buscarLogradouro(Connection conn, int idLogradouro) throws Exception {
        String SQL = "SELECT log.nome_logradouro, log.sigla_tipo_logradouro "
                + "FROM \"Logradouro\" log WHERE log.id_logradouro = ?";

        Logradouro logradouro = null;

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, idLogradouro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ColTipoLogradouro colTipoLogradouro = new ColTipoLogradouro();
                    TipoLogradouro tipoLogradouro = new TipoLogradouro();
                    
                    logradouro = new Logradouro();

                    String siglaTipoLogradouro = rs.getString("sigla_tipo_logradouro");
                    logradouro.setNome(rs.getString("nome_logradouro"));
                    tipoLogradouro = colTipoLogradouro.recuperarTipoLogradouro(conn, siglaTipoLogradouro);
                    
                    logradouro.setTipoLogradouro(tipoLogradouro);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar logradouro: " + e);
        }

        return logradouro;
    }

}
