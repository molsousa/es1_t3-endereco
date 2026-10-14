/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.manager;

import unioeste.geral.dao.EnderecoDAO;
import unioeste.geral.endereco.bo.Endereco;

/**
 *
 * @author molsousa
 */
public class Main {
    public static void main(String[] args) {
        EnderecoDAO endeDAO = new EnderecoDAO();
        
        Endereco endereco = endeDAO.buscarEndereco("01311-100");
        
        if(endereco != null) {
            System.out.print(endereco.getLogradouro().getTipoLogradouro().getSigla() + ". ");
            System.out.print(endereco.getLogradouro().getNome() + " ");
            System.out.print(endereco.getBairro().getNome() + ", ");
            System.out.print(endereco.getCidade().getNome() + " - ");
            System.out.print(endereco.getCidade().getUnidadeFederativa().getNome() + ". CEP: ");
            System.out.println(endereco.getCep());
        } else {
            System.out.println("Endereço não cadastrado");
        }
    }
}
