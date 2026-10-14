/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.bo;

import java.io.Serializable;

/**
 *
 * @author molsousa
 */
public class TipoLogradouro implements Serializable {

    public final static long serialVersionUID = 1;

    private String sigla;
    private String nome;

    public TipoLogradouro() {
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
