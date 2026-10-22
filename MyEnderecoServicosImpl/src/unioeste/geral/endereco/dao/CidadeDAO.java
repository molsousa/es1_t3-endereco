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
import unioeste.geral.endereco.bo.UnidadeFederativa;
import unioeste.geral.endereco.col.ColUnidadeFederativa;
import unioeste.geral.endereco.exception.EnderecoException;
import unioeste.geral.endereco.exception.UnidadeFederativaNaoEncontradaException;

/**
 *
 * @author molsousa
 */
public class CidadeDAO {

    public CidadeDAO() {
    }
    
    public int cadastrarCidade(Connection conn, Cidade cidade) throws Exception {
        String SQL = "INSERT INTO \"Cidade\" (nome_cidade, sigla_uf) VALUES (?,?) RETURNING id_cidade";
        
        ColUnidadeFederativa colUnidadeFederativa = new ColUnidadeFederativa();
        
        cidade.getUnidadeFederativa().setNome(cidade.getUnidadeFederativa().getNome().trim());
        
        try{
            colUnidadeFederativa.recuperarUnidadeFederativa(conn, cidade.getUnidadeFederativa().getSigla());
            
        }catch (UnidadeFederativaNaoEncontradaException e) {
            colUnidadeFederativa.cadastrarUF(conn, cidade.getUnidadeFederativa());
            
        }
        
        try(PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setString(1, cidade.getNome());
            stmt.setString(2, cidade.getUnidadeFederativa().getSigla());
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("id_cidade");
                }
            }
        } catch(SQLException e) {
            throw new SQLException("Erro ao cadastrar cidade: " + e);
        }
        
        throw new EnderecoException("Erro ao retornar id da cidade");
    }
    
    public int buscarCidade(Connection conn, Cidade cidade) throws Exception {
        String SQL = "SELECT id_cidade FROM \"Cidade\" WHERE nome_cidade = ? AND sigla_uf = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, cidade.getNome());
            stmt.setString(2, cidade.getUnidadeFederativa().getSigla());
            
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return rs.getInt("id_cidade");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar cidade: " + e);
        }

        throw new EnderecoException("Erro ao retornar id da cidade");
    }

    public Cidade buscarCidade(Connection conn, int idCidade) throws Exception {
        String SQL = "SELECT cid.nome_cidade, cid.sigla_uf FROM \"Cidade\" cid "
                + "WHERE cid.id_cidade = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, idCidade);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ColUnidadeFederativa colUnidadeFederativa = new ColUnidadeFederativa();
                    UnidadeFederativa uf;
                    Cidade cidade = new Cidade();

                    String siglaUF = rs.getString("sigla_uf");
                    cidade.setNome(rs.getString("nome_cidade"));

                    uf = colUnidadeFederativa.recuperarUnidadeFederativa(conn, siglaUF);

                    cidade.setUnidadeFederativa(uf);
                    
                    return cidade;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar cidade: " + e);
        }

        throw new EnderecoException("Erro ao retornar cidade");
    }

}
