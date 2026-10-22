/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.exception;

/**
 *
 * @author molsousa
 */
public class TipoLogradouroInvalidoException extends EnderecoException {

    public TipoLogradouroInvalidoException(String sigla) {
        super("Sigla de tipo de logradouro inválida: " + sigla);
    }
}
