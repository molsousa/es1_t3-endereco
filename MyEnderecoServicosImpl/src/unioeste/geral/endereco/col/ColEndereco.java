/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import java.sql.Connection;
import java.sql.SQLException;
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
        
        if(endereco.getCep() == null || !CEP_PATTERN.matcher(endereco.getCep()).matches()) {
            throw new CepInvalidoException(CepInvalidoException.CodigoErro.CEP_INVALIDO, endereco.getCep());
        }
        
        idEndereco = enderecoDAO.cadastrarEndereco(conn, endereco);
        
        return idEndereco;
    }

    public Endereco recuperarEnderecoPorCEP(Connection conn, String cep)
            throws EnderecoNaoEncontradoException, CepInvalidoException, Exception {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        Endereco endereco;
        
        cep = cep.trim();

        if (cep == null || !CEP_PATTERN.matcher(cep).matches()) {
            throw new CepInvalidoException(cep);
        }

        try{
            endereco = enderecoDAO.buscarEnderecoPorCEP(conn, cep);
        } catch (EnderecoException e) {            
            throw new EnderecoNaoEncontradoException(EnderecoNaoEncontradoException.CodigoErro.CEP_NAO_ENCONTRADO, cep);
        } catch (SQLException e) {
            throw new SQLException("Erro no banco ao buscar endereço: " + e.getMessage());
        }


        return endereco;
    }

    public Endereco recuperarEnderecoPorID(Connection conn, int idEndereco) throws Exception {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        Endereco endereco;
        
        if(idEndereco <= 0) {
            throw new EnderecoException("Id inválido: " + idEndereco);
        }

        endereco = enderecoDAO.buscarEnderecoPorID(conn, idEndereco);

        if (endereco == null) {
            throw new EnderecoNaoEncontradoException(EnderecoNaoEncontradoException.CodigoErro.ID_NAO_ENCONTRADO, idEndereco);
        }

        return endereco;
    }

}
