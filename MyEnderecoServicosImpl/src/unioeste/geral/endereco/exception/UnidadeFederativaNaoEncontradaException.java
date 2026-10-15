/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.exception;

/**
 *
 * @author molsousa
 */
public class UnidadeFederativaNaoEncontradaException extends EnderecoException {

    public UnidadeFederativaNaoEncontradaException(String siglaUF) {
        super("Unidade federativa não encontrada, sigla: " + siglaUF);
    }
    
}
