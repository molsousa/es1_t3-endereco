/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.exception;

/**
 *
 * @author molsousa
 */
public class EnderecoNaoEncontradoException extends EnderecoException {

    public EnderecoNaoEncontradoException(String cep) {
        super("Endereço não encontrado para o CEP: " + cep);
    }

    public EnderecoNaoEncontradoException(int idEndereco) {
        super("Endereco não encontrado para o ID: " + idEndereco);
    }
}
