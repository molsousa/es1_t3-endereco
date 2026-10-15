/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.Bairro;
import java.sql.*;

/**
 *
 * @author molsousa
 */
public class BairroDAO {

    public BairroDAO() {
    }

    public Bairro buscarBairro(Connection conn, int idBairro) {
        String SQL = "SELECT b.nome_bairro FROM \"Bairro\" b "
                + "WHERE b.id_bairro = ?";

        Bairro bairro = null;

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, idBairro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    bairro = new Bairro();

                    bairro.setNome(rs.getString("nome_bairro"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar bairro: " + e);
        }

        return bairro;
    }
}
