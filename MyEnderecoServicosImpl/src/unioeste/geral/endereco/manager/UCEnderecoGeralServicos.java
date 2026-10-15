/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.manager;

import java.sql.Connection;
import java.sql.SQLException;
import unioeste.geral.endereco.col.ColEndereco;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.apoio.bd.ConnectionFactory;

/**
 *
 * @author molsousa
 */
public class UCEnderecoGeralServicos {

    public UCEnderecoGeralServicos() {
    }

    public Endereco obterEnderecoPorCEP(String cep) throws Exception {
        Endereco endereco;
        ColEndereco colEndereco = new ColEndereco();
        
        try{
            Connection conn = ConnectionFactory.getConnection();
            endereco = colEndereco.recuperarEnderecoPorCEP(conn, cep);
            
        } catch (SQLException e) {
            throw new Exception ("Erro ao obter endereço: " + e);
        }

        return endereco;
    }
    
    public Endereco obterEnderecoPorID(int idEndereco) throws Exception {
        Endereco endereco;
        ColEndereco colEndereco = new ColEndereco();
        
        try{
            Connection conn = ConnectionFactory.getConnection();
            endereco = colEndereco.recuperarEnderecoPorID(conn, idEndereco);
        } catch (SQLException e) {
            throw new Exception("Erro ao obter endereço: " + e);
        }
        
        return endereco;
    }

}
