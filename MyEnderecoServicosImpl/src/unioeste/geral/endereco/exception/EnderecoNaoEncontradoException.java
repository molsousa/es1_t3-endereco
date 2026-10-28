/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.exception;

import java.util.Objects;

/**
 *
 * @author molsousa
 */
public class EnderecoNaoEncontradoException extends EnderecoException {

    private static final long serialVersionUID = 1;
    
    private final CodigoErro erro;
    
    public EnderecoNaoEncontradoException(CodigoErro erro, String cep) {
        super(montarMensagem(erro, cep));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    public EnderecoNaoEncontradoException(String cep) {
        this(CodigoErro.CEP_NAO_ENCONTRADO, cep);
    }
    
    public EnderecoNaoEncontradoException(CodigoErro erro, int id) {
        super(montarMensagem(erro, id));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    public EnderecoNaoEncontradoException(int id) {
        this(CodigoErro.ID_NAO_ENCONTRADO, id);
    }
    
    private static String montarMensagem(CodigoErro erro, String cep) {
        return erro.getMessage() + ": " + cep;
    }
    
    private static String montarMensagem(CodigoErro erro, int id) {
        return erro.getMessage() + ": " + id;
    }
    
    public CodigoErro getErro() {
        return erro;
    }
    
    public enum CodigoErro {
        ID_NAO_ENCONTRADO("Endereço não encontrado para o id"),
        CEP_NAO_ENCONTRADO("Endereço não encontrado para o cep");
        
        private final String msg;
        
        CodigoErro(String msg) {
            this.msg = msg;
        }
        
        public String getMessage() {
            return this.msg;
        }
    }
}
