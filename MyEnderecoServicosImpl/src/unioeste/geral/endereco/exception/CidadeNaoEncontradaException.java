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
public class CidadeNaoEncontradaException extends EnderecoException {

    private static final long serialVersionUID = 1;

    private final CodigoErro erro;

    public CidadeNaoEncontradaException(CodigoErro erro, int id) {
        super(montarMensagem(erro, id));
        this.erro = Objects.requireNonNull(erro, "erro");
    }

    public CidadeNaoEncontradaException(int id) {
        this(CodigoErro.ID_INVALIDO, id);
    }

    public CidadeNaoEncontradaException(CodigoErro erro, String nome) {
        super(montarMensagem(erro, nome));
        this.erro = Objects.requireNonNull(erro, "erro");
    }

    public CidadeNaoEncontradaException(String nome) {
        this(CodigoErro.NAO_ENCONTRADO, nome);
    }

    private static String montarMensagem(CodigoErro erro, int id) {
        return erro.getMessage() + ": " + id;
    }

    private static String montarMensagem(CodigoErro erro, String nome) {
        return erro.getMessage() + ": " + nome;
    }

    public CodigoErro getErro() {
        return this.erro;
    }

    public enum CodigoErro {
        ID_INVALIDO("Id inválido"),
        NAO_ENCONTRADO("Cidade nao encontrada");

        private final String msg;

        private CodigoErro(String msg) {
            this.msg = msg;
        }

        public String getMessage() {
            return this.msg;
        }
    }
}
