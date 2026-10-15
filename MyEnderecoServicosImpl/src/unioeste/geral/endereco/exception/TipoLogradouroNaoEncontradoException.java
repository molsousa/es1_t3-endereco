/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.exception;

/**
 *
 * @author molsousa
 */
public class TipoLogradouroNaoEncontradoException extends EnderecoException {
    
    public TipoLogradouroNaoEncontradoException(String siglaTipoLogradouro) {
        super("Tipo de logradouro não encontrado, sigla: " + siglaTipoLogradouro);
    }
}
