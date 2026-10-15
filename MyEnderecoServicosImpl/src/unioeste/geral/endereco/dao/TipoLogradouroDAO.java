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

/**
 *
 * @author molsousa
 */
public class TipoLogradouroDAO {

    public TipoLogradouroDAO() {
    }

    public TipoLogradouro buscarTipoLogradouro(Connection conn, String siglaTipoLogradouro) {
        String SQL = "SELECT tiplog.nome_tipo_logradouro FROM \"TipoLogradouro\" tiplog "
                + "WHERE tiplog.sigla_tipo_logradouro = ?";

        TipoLogradouro tipoLogradouro = null;

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, siglaTipoLogradouro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tipoLogradouro = new TipoLogradouro();

                    tipoLogradouro.setSigla(siglaTipoLogradouro);
                    tipoLogradouro.setNome(rs.getString("nome_tipo_logradouro"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar tipo de logradouro: " + e);
        }

        return tipoLogradouro;
    }

}
