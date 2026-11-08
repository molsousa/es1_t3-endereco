/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.col;

import unioeste.geral.endereco.bo.EnderecoEspecifico;

/**
 *
 * @author molsousa
 */
public class ColEnderecoEspecifico {

    public boolean validarNroEndereco(EnderecoEspecifico enderecoEspecifico) {
        return enderecoEspecifico.getNroEndereco() > 0;
    }

    public boolean validarEndereco(EnderecoEspecifico enderecoEspecifico) {
        return enderecoEspecifico.getEndereco() != null;
    }
}
