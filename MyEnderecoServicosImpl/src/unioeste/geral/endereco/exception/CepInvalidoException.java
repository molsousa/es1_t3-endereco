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
public class CepInvalidoException extends EnderecoException {

    private static final long serialVersionUID = 1;
    
    private final CodigoErro erro;
    
    public CepInvalidoException(CodigoErro erro, String cep) {
        super(montarMensagem(erro, cep));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    public CepInvalidoException(String cep) {
        this(CodigoErro.CEP_INVALIDO, cep);
    }
    
    private static String montarMensagem(CodigoErro erro, String cep) {
        return erro.getMessage() + ": " + cep;
    }
    
    public CodigoErro getErro() {
        return erro;
    }
    
    public enum CodigoErro {
        CEP_INVALIDO("Cep inválido");
        
        private final String msg;
        
        CodigoErro(String msg) {
            this.msg = msg;
        }
        
        public String getMessage() {
            return this.msg;
        }
    }
}
