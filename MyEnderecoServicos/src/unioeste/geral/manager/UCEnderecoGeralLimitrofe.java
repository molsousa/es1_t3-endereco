/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.manager;

import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

/**
 *
 * @author molsousa
 */
public class UCEnderecoGeralLimitrofe {

    public Endereco obterEnderecoPorCEP(String cep) throws Exception {
        Endereco endereco;
        UCEnderecoGeralServicos enderecoManager = new UCEnderecoGeralServicos();

        endereco = enderecoManager.obterEnderecoPorCEP(cep);

        return endereco;
    }
}
