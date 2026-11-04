# Implementação da camada de negócios para endereço.

**Disciplina**: Engenharia de Software.

**Professor**: Roberto Gil Brasil.

**Estudantes**: [Julia Gabrielly](https://github.com/yuchenkkao), [Marcos Sousa](https://github.com/molsousa).

## Sumário

- [Implementação](#implementação)
- [MyInfraAPI](#myinfraapi)
- [MyEnderecoBO](#myenderecobo)
- [MyEnderecoServicos](#myenderecoservicos)
- [MyEnderecoServicosImpl](#myenderecoservicosimpl)
- [Endereço externo](#endereço-externo)
- [Dependências](#dependências)
- [Compilação](#compilação)
- [Referências](#referências)

## Descrição

Este trabalho contém quatro projetos que compõem da camada de negócios de endereço para a disciplina de Engenharia de Software I. O programa foi implementado em Java e seus componentes podem ser utilizados em outros trabalhos.

--- 

## Implementação

Para os códigos-fonte de todos os projetos fora utilizado Java 8. A estrutura dos quatro projetos são modulares a fim de melhorar a legibilidade e manutenção do programa. O servidor utilizado é o Glassfish e o banco de dados é o `postgresql`.

## Os quatro projetos

### MyInfraAPI

Esse projeto é o único que diretamente não é ligado aos projetos de endereço, a infra faz apenas a conexão com o servidor, ela pode ser utilizada em diferentes projetos sem que esse projeto precise ter endereço, segue abaixo a estrutura das pastas:

```ASCII
└── unioeste/
    └── apoio/
        └── bd/
            └── ConnectionFactory.java
```

### MyEnderecoBO

Esse é um projeto que contém apenas as classes de negócios, todas elas implementam `Serializable` a fim de facilitar a troca de dados entre uma camada e outra. Segue abaixo a estrutura das pastas:

```ASCII
└── unioeste/
    └── geral/
        └── endereco/
            └── bo/
                ├── Bairro.java
                ├── Cidade.java
                ├── Endereco.java
                ├── EnderecoEspecifico.java
                ├── Logradouro.java
                ├── TipoLogradouro.java
                └── UnidadeFederativa.java
```

Apesar de não utilizado nessa fase de implementação, `EnderecoEspecifico` pode fazer parte do projeto de `Endereco`, por mais que sua implementação real seja com uma classe `Pessoa` implementada.

### MyEnderecoServicosImpl

O projeto que aplica as regras de negócio, faz transações ou buscas no banco, implementa exceções e orquestra o funcionamento dos serviços. Possui cinco pacotes: Col, DAO, Externo, Exception e Manager, onde:

- Manager: abre transação com o banco e chama a classe Col correspondente, se tudo ocorrer bem, é feito o 'commit' no banco, se caso der erro, 'rollback'.

- Col: valida as informações, verifica se há espaços em branco e os retira, joga exceção pro `manager` caso ocorra algum erro durante a validação, as exceções lançadas são consultas no pacote `exception`. Se estiver tudo certo, chama o DAO.

- DAO: aplica a solicitação do `col` de inserção ou busca. Caso ocorra algum erro na conexão com o banco, retorna para o `col`. Caso dê tudo certo, nenhuma mensagem adicional é emitida além da solicitação do `col`.

- Exception: aqui é implementado as classes de exceção, `EnderecoException` herda de `Exception`. As demais classes herdam de `EnderecoException` e são personalizadas a casos específicos, como linhas do banco que não foram encontradas ou erros de formatação.

- Externo: aqui são guardados serviços de API externa, isto é, uma requisição que pode ser feita externa ao código-fonte e utilizada no mesmo.

Segue abaixo a estrutura das pastas: 

```ASCII
└── unioeste/
    └── geral/
        └── endereco/
            ├── col/
            │   ├── ColBairro.java
            │   ├── ColCidade.java
            │   ├── ColEndereco.java
            │   ├── ColEnderecoEspecifico.java
            │   ├── ColLogradouro.java
            │   ├── ColTipoLogradouro.java
            │   └── ColUnidadeFederativa.java
            ├── dao/
            │   ├── BairroDAO.java
            │   ├── CidadeDAO.java
            │   ├── EnderecoDAO.java
            │   ├── EnderecoEspecificoDAO.java
            │   ├── LogradouroDAO.java
            │   ├── TipoLogradouroDAO.java
            │   └── UnidadeFederativaDAO.java
            ├── exception/
            │   ├── BairroNaoEncontradoException.java
            │   ├── CepInvalidoException.java
            │   ├── CidadeNaoEncontradaException.java
            │   ├── EnderecoException.java
            │   ├── EnderecoNaoEncontradoException.java
            │   ├── LogradouroNaoEncontradoException.java
            │   ├── TipoLogradouroInvalidoException.java
            │   ├── TipoLogradouroNaoEncontradoException.java
            │   ├── UnidadeFederativaInvalidaException.java
            │   └── UnidadeFederativaNaoEncontradaException.java
            ├── externo/
            │   └── ViaCEP.java
            └── manager/
                └── UCEnderecoGeralServicos.java
```

### MyEnderecoServicos 

Projeto que faz conexão via WebServlet com o frontend. Aqui ficam as classes limítrofes, todas as requsições de endereço que o frontend fizer sempre virá para as classes desse projeto. Cada uma das classes é um serviço que o frontend requisitou, segue abaixo a estrutura de pastas:

``` ASCII
└── java/
    └── unioeste/
        └── geral/
            └── endereco/
                ├── api/
                │   ├── CadastrarEnderecoServlet.java
                │   ├── ObterCidadePorIDServlet.java
                │   ├── ObterEnderecoExterno.java
                │   ├── ObterEnderecoPorCEPServlet.java
                │   └── ObterEnderecoPorIDServlet.java
                └── config/
                    └── CorsFilter.java
```

#### Endereço externo 

A API utilizada no endereço externo foi o [ViaCEP](https://viacep.com.br/).

### Dependências

Para o funcionamento de um backend com diferentes projetos é necessário gerar um executável '`.jar`' para inclusão de bibliotecas, sendo assim:

- É preciso ter o `postgresql-42.7.4.jar` e `gf-client.jar` (do Glassfish) inclusos no `MyInfraAPI`.

- É preciso ter `MyEnderecoBO.jar` e `MyInfraAPI.jar` no `MyEnderecoServicosImpl`.

- É preciso ter `MyEnderecoBO.jar`, `MyInfraAPI.jar`, `MyEnderecoServicosImpl.jar`, `jackson-databind-2.13.4.jar`, `jackson-core-2.13.4.jar` e `jackson-annotations-2.13.4.jar` no `MyEnderecoServicos`.


## Compilação 

Para a compilação é preciso limpar e executar todos os projetos e colocar o servidor no ar pelo `MyEnderecoServicos`, após isso, pode ser feita qualquer requisição no frontend.

## Referências

POSTGRESQL. PostgreSQL: The World's Most Advanced Open Source Relational Database. [S. l.]: PostgreSQL Global Development Group, [2026?]. Disponível em: https://www.postgresql.org/. Acesso em: 4 nov. 2026.

GITHUB. FasterXML/jackson-annotations: Core annotations (annotations that only depend on jackson-core) for Jackson data processor. [S. l.]: GitHub, [2026?]. Disponível em: https://github.com/FasterXML/jackson-annotations. Acesso em: 4 nov. 2026.

VIACEP. ViaCEP - Webservice CEP e IBGE gratuito. [S. l.], [2026?]. Disponível em: https://viacep.com.br/. Acesso em: 4 nov. 2026.

ECLIPSE FOUNDATION. Eclipse GlassFish. [S. l.]: Eclipse Foundation, [2026?]. Disponível em: https://glassfish.org/. Acesso em: 4 nov. 2026.

---
