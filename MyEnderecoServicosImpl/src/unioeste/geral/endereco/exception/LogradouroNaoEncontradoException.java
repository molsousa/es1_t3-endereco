/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.exception;

/**
 *
 * @author molsousa
 */
public class LogradouroNaoEncontradoException extends EnderecoException {

    public LogradouroNaoEncontradoException(int idLogradouro) {
        super("Logradouro não encontrado, ID: " + idLogradouro);
    }
    
    public LogradouroNaoEncontradoException(String nome) {
        super("Logradouro não encontrado, nome: " + nome);
    }
    
}
