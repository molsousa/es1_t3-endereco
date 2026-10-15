/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.manager;

import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

/**
 *
 * @author molsousa
 */
public class Main {

    public static void main(String[] args) throws Exception {

        UCEnderecoGeralServicos enderecoManager = new UCEnderecoGeralServicos();

        Endereco endereco = enderecoManager.obterEnderecoPorID(1);
        Cidade cidade = enderecoManager.obterCidade(1);
        
        System.out.print(endereco.getLogradouro().getTipoLogradouro().getSigla() + ". ");
        System.out.print(endereco.getLogradouro().getNome() + " ");
        System.out.print(endereco.getBairro().getNome() + ", ");
        System.out.print(endereco.getCidade().getNome() + " - ");
        System.out.print(endereco.getCidade().getUnidadeFederativa().getNome() + ". CEP: ");
        System.out.println(endereco.getCep());
        
        System.out.print(cidade.getNome() + " ");
        System.out.print(cidade.getUnidadeFederativa().getNome() + " - ");
        System.out.println(cidade.getUnidadeFederativa().getSigla());
    }
}
