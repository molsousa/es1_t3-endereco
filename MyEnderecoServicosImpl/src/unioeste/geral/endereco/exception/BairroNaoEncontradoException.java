/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.exception;

/**
 *
 * @author molsousa
 */
public class BairroNaoEncontradoException extends EnderecoException {

    public BairroNaoEncontradoException(int idBairro) {
        super("Bairro não encontrado, ID: " + idBairro);
    }
    
}
