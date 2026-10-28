/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.externo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import unioeste.geral.endereco.bo.Bairro;
import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.bo.Logradouro;
import unioeste.geral.endereco.bo.UnidadeFederativa;
import unioeste.geral.endereco.exception.EnderecoException;

/**
 *
 * @author molsousa
 */
public class ViaCEP {

    private static final String VIA_CEP_URL = "http://viacep.com.br/ws/";

    public String obterEnderecoViaCEP(String requisicaoURL) throws Exception {
        URL url = new URL(requisicaoURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Erro na conexão com a API ViaCEP");
        }

        try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder stringBuilder = new StringBuilder();
            String str;

            while ((str = bufferReader.readLine()) != null) {
                stringBuilder.append(str);
            }

            return stringBuilder.toString();
        }
    }

    private Map<String, String> converterJSON(String json) {
        Map<String, String> map = new HashMap<>();

        json = json.replaceAll("[{}\"]", "");

        String[] pairs = json.split(",");

        for (String pair : pairs) {
            String[] entry = pair.split(":");
            map.put(entry[0].trim(), entry[1].trim());
        }

        return map;
    }

    public Endereco obterEnderecoPorCep(String cep) throws Exception {
        String enderecoJson = obterEnderecoViaCEP(VIA_CEP_URL + cep + "/json");
        Map<String, String> eMap = converterJSON(enderecoJson);
        Endereco endereco;

        if (eMap.containsKey("erro")) {
            throw new EnderecoException("Endereço não existe para o cep fornecido: " + cep);
        }

        endereco = obterEndereco(eMap);

        return endereco;
    }

    private Endereco obterEndereco(Map<String, String> map) {
        String siglaUF = map.getOrDefault("uf", "");
        String nomeUF = map.getOrDefault("estado", "");
        String nomeCidade = map.getOrDefault("localidade", "");
        String nomeBairro = map.getOrDefault("bairro", "");
        String nomeLogradouro = map.getOrDefault("logradouro", "");
        String cep = map.getOrDefault("cep", "");

        UnidadeFederativa uf = new UnidadeFederativa();
        uf.setNome(nomeUF);
        uf.setSigla(siglaUF);

        Cidade cidade = new Cidade();
        cidade.setNome(nomeCidade);
        cidade.setUnidadeFederativa(uf);

        Bairro bairro = new Bairro();
        bairro.setNome(nomeBairro);

        Logradouro logradouro = new Logradouro();
        logradouro.setNome(nomeLogradouro);

        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setLogradouro(logradouro);

        return endereco;
    }
}
