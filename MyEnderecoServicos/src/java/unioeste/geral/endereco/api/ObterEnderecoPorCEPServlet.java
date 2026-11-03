/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.endereco.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

/**
 *
 * @author molsousa
 */
@WebServlet("/endereco/cep")
public class ObterEnderecoPorCEPServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UCEnderecoGeralServicos enderecoManager = new UCEnderecoGeralServicos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String cep = request.getParameter("cep");

            if (cep == null || cep.trim().isEmpty()) {
                throw new ServletException("O parâmetro 'cep' é obrigatório.");
            }

            List<Endereco> enderecos = enderecoManager.obterEnderecoPorCEP(cep);

            String enderecoResponse = objectMapper.writeValueAsString(enderecos);
            response.getWriter().write(enderecoResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            Map<String, String> erro = new HashMap<>();
            erro.put("message", e.getMessage());

            String errorJson = objectMapper.writeValueAsString(erro);
            response.getWriter().println(errorJson);
        }
    }
}
