/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.manager;

import java.sql.Connection;
import java.sql.SQLException;
import unioeste.apoio.bd.ConnectionFactory;
import unioeste.geral.endereco.col.ColEndereco;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.col.ColCidade;
import unioeste.geral.endereco.exception.EnderecoException;
import unioeste.geral.endereco.externo.ViaCEP;

/**
 *
 * @author molsousa
 */
public class UCEnderecoGeralServicos {

    public UCEnderecoGeralServicos() {
    }

    public Endereco cadastrarEndereco(Endereco endereco) throws Exception {
        ColEndereco colEndereco = new ColEndereco();
        int idEndereco;

        if (endereco == null || endereco.getCep() == null || endereco.getCep().trim().isEmpty()) {
            throw new EnderecoException("Objeto endereço não foi devidamente carregado");
        }

        Connection conn = ConnectionFactory.getConnection();

        try {
            idEndereco = colEndereco.cadastrarEndereco(conn, endereco);
            conn.commit();

            return colEndereco.recuperarEnderecoPorID(conn, idEndereco);

        } catch (SQLException e) {
            throw new EnderecoException("Não foi possível cadastrar o endereço: " + e);
        }
    }

    public Endereco obterEnderecoPorCEP(String cep) throws Exception {
        Endereco endereco;
        ColEndereco colEndereco = new ColEndereco();

        try {
            Connection conn = ConnectionFactory.getConnection();
            endereco = colEndereco.recuperarEnderecoPorCEP(conn, cep);

        } catch (SQLException e) {
            throw new EnderecoException("Erro ao obter endereço: " + e);
        }

        return endereco;
    }

    public Endereco obterEnderecoPorID(int idEndereco) throws Exception {
        Endereco endereco;
        ColEndereco colEndereco = new ColEndereco();

        try {
            Connection conn = ConnectionFactory.getConnection();
            endereco = colEndereco.recuperarEnderecoPorID(conn, idEndereco);
        } catch (SQLException e) {
            throw new EnderecoException("Erro ao obter endereço: " + e);
        }

        return endereco;
    }

    public Cidade obterCidade(int idCidade) throws Exception {
        Cidade cidade;
        ColCidade colCidade = new ColCidade();

        try {
            Connection conn = ConnectionFactory.getConnection();
            cidade = colCidade.recuperarCidade(conn, idCidade);
        } catch (SQLException e) {
            throw new EnderecoException("Erro ao obter cidade: " + e);
        }

        return cidade;
    }

    public Endereco obterEnderecoViaCEP(String cep) throws Exception {
        ViaCEP viaCEP = new ViaCEP();

        return viaCEP.obterEnderecoPorCep(cep);
    }

}
