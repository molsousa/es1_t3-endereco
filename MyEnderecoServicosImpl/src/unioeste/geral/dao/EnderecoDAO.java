/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unioeste.apoio.bd.ConnectionFactory;
import unioeste.geral.endereco.bo.*;

/**
 *
 * @author molsousa
 */
public class EnderecoDAO {
    
    public EnderecoDAO(){
    }
    
    public Endereco buscarEndereco(String cep) {
        String SQL = "SELECT ende.cep, ende.id_bairro, ende.id_logradouro, ende.id_cidade FROM "
                + "\"Endereco\" ende WHERE ende.cep = ?";
        
        Endereco endereco = null;
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL)) {
            
            stmt.setString(1, cep);
            
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    endereco = new Endereco();
                    
                    endereco.setCep(cep);
                    CidadeDAO cidadeDAO = new CidadeDAO();
                    LogradouroDAO logradouroDAO = new LogradouroDAO();
                    BairroDAO bairroDAO = new BairroDAO();
                    
                    int idCidade = rs.getInt("id_cidade");
                    int idLogradouro = rs.getInt("id_logradouro");
                    int idBairro = rs.getInt("id_bairro");
                    
                    Cidade cidade = cidadeDAO.buscarCidade(conn, idCidade);
                    Logradouro logradouro = logradouroDAO.buscarLogradouro(conn, idLogradouro);
                    Bairro bairro = bairroDAO.buscarBairro(conn, idBairro);
                    
                    endereco.setCidade(cidade);
                    endereco.setLogradouro(logradouro);
                    endereco.setBairro(bairro);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar endereco: " + e);
            return null;
        }
        
        return endereco;
    }
}
