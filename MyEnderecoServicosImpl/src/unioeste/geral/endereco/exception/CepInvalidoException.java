/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.exception;

/**
 *
 * @author molsousa
 */
public class CepInvalidoException extends EnderecoException {

    public CepInvalidoException(String cep) {
        super("CEP inválido: " + cep);
    }
}
