/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;
import unioeste.geral.endereco.dao.EnderecoDAO;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.exception.EnderecoNaoEncontradoException;
import unioeste.geral.endereco.exception.CepInvalidoException;
import unioeste.geral.endereco.exception.EnderecoException;

/**
 *
 * @author molsousa
 */
public class ColEndereco {

    private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{5}-\\d{3}$");

    public int cadastrarEndereco(Connection conn, Endereco endereco) throws Exception {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        int idEndereco;

        endereco.setCep(endereco.getCep().trim());

        if (!CEP_PATTERN.matcher(endereco.getCep()).matches()) {
            throw new CepInvalidoException(CepInvalidoException.CodigoErro.CEP_INVALIDO, endereco.getCep());
        }

        try {
            idEndereco = enderecoDAO.buscarIDEnderecoPorCep(conn, endereco.getCep());
        } catch (EnderecoNaoEncontradoException e) {
            idEndereco = enderecoDAO.cadastrarEndereco(conn, endereco);
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar endereço: " + e.getMessage());
        } catch (EnderecoException e) {
            throw new EnderecoException("Ocorreu um erro ao retornar o objeto Endereco: " + e.getMessage());
        }

        return idEndereco;
    }
    
    public int recuperarIDEnderecoPorCEP(Connection conn, String cep) throws Exception {
        
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        
        cep = cep.trim();
        
        if (cep == null || !CEP_PATTERN.matcher(cep).matches()) {
            throw new CepInvalidoException(CepInvalidoException.CodigoErro.CEP_INVALIDO, cep);
        }
        
        try {
            return enderecoDAO.buscarIDEnderecoPorCep(conn, cep);
        } catch (EnderecoException e) {
            throw new EnderecoNaoEncontradoException(EnderecoNaoEncontradoException.CodigoErro.CEP_NAO_ENCONTRADO, cep);
        } catch (SQLException e) {
            throw new SQLException("Erro no banco ao buscar endereço: " + e.getMessage());
        }
    }

    public List<Endereco> recuperarEnderecoPorCEP(Connection conn, String cep)
            throws EnderecoNaoEncontradoException, CepInvalidoException, Exception {
        List<Endereco> enderecos;
        EnderecoDAO enderecoDAO = new EnderecoDAO();

        cep = cep.trim();

        if (cep == null || !CEP_PATTERN.matcher(cep).matches()) {
            throw new CepInvalidoException(CepInvalidoException.CodigoErro.CEP_INVALIDO, cep);
        }

        try {
            enderecos = enderecoDAO.buscarEnderecoPorCEP(conn, cep);
        } catch (EnderecoException e) {
            throw new EnderecoNaoEncontradoException(EnderecoNaoEncontradoException.CodigoErro.CEP_NAO_ENCONTRADO, cep);
        } catch (SQLException e) {
            throw new SQLException("Erro no banco ao buscar endereço: " + e.getMessage());
        }

        return enderecos;
    }

    public Endereco recuperarEnderecoPorID(Connection conn, int idEndereco) throws Exception {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        Endereco endereco;

        if (idEndereco <= 0) {
            throw new EnderecoException("Id inválido: " + idEndereco);
        }

        try {
            endereco = enderecoDAO.buscarEnderecoPorID(conn, idEndereco);
        } catch (EnderecoException e) {
            throw new EnderecoNaoEncontradoException(EnderecoNaoEncontradoException.CodigoErro.ID_NAO_ENCONTRADO, idEndereco);
        } catch (SQLException e) {
            throw new SQLException("Erro no banco ao buscar endereço: " + e.getMessage());
        }

        return endereco;
    }

}
