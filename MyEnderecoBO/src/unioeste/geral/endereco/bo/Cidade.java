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
public class Cidade implements Serializable {

    public final static long serialVersionUID = 1;

    private String nome;
    private UnidadeFederativa uf;

    public Cidade() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UnidadeFederativa getUnidadeFederativa() {
        return uf;
    }

    public void setUnidadeFederativa(UnidadeFederativa uf) {
        this.uf = uf;
    }

}
