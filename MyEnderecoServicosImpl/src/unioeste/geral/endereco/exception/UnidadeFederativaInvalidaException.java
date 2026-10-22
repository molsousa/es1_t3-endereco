/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.exception;

/**
 *
 * @author molsousa
 */
public class UnidadeFederativaInvalidaException extends EnderecoException {

    public UnidadeFederativaInvalidaException(String sigla) {
        super("Sigla inválida para unidade federativa: "+ sigla);
    }
    
}
