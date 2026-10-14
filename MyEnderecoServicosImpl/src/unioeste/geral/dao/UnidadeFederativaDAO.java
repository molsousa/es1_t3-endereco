/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unioeste.geral.endereco.bo.UnidadeFederativa;

/**
 *
 * @author molsousa
 */
public class UnidadeFederativaDAO {
    
    public UnidadeFederativaDAO() {
    }
    
    public UnidadeFederativa buscarUF(Connection conn, String siglaUF) {
        String SQL = "SELECT uf.sigla_uf, uf.nome_uf FROM \"UnidadeFederativa\" uf "
                + "JOIN \"Cidade\" cid ON cid.sigla_uf = uf.sigla_uf WHERE "
                + "uf.sigla_uf = ?";
        
        UnidadeFederativa uf = null;
        
        try(PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, siglaUF);
            
            try(ResultSet rs = stmt.executeQuery()) {
                
                if(rs.next()){
                    uf = new UnidadeFederativa();

                    uf.setSigla(rs.getString("sigla_uf"));
                    uf.setNome(rs.getString("nome_uf"));
                }
                
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Unidade Federativa " + e);
        }
        
        return uf;
    }
}
