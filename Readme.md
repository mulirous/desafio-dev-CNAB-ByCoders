# Desafio em Spring Boot para vaga de desenvolvedor da By Coders

O desafio proposto [neste](https://github.com/ByCodersTec/desafio-ruby-on-rails/blob/master/README.md) diretório foi implementado utilizando Java 21 junto à Spring Framework.

## Requisitos para rodar o projeto

1. **Java 21**: Certifique-se de que o Java Development Kit (JDK) 21 está instalado.
2. **Maven**: O projeto utiliza o Maven para gerenciamento de dependências. Certifique-se de que o Maven está instalado e configurado.
3. **Banco de Dados**: O projeto utiliza um banco de dados relacional (ex.: PostgreSQL ou MySQL). Configure as credenciais no arquivo `application.properties` ou `application.yml`.
4. **Git**: Para clonar o repositório.
5. **IDE**: Recomenda-se o uso de uma IDE como IntelliJ IDEA ou VS Code para facilitar o desenvolvimento.

### Passos para rodar o projeto

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/desafio-spring-bycoders.git
    cd desafio-spring-bycoders
    ```
2. Configure o banco de dados no arquivo `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```
3. Compile e rode o projeto:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
4. Acesse a aplicação no navegador:
    ```bash
    http://localhost:8080
    ```

## Endpoints da API

### 1. Autenticação

-   **POST** `/auth/login`

#### Descrição

O endpoint de autenticação permite que os usuários façam login na aplicação. Ele aceita as seguintes informações no corpo da requisição:

**Exemplo de requisição:**

```json
POST /auth/login
Content-Type: application/json

{
     "username": "seu_usuario",
     "password": "sua_senha"
}
```

**Exemplo de resposta:**

```json
{
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

-   **Código de resposta 200**: Login bem-sucedido. Retorna um token JWT.
-   **Código de resposta 401**: Credenciais inválidas.
-   **Código de resposta 500**: Erro interno no servidor.

### 2. Listagem de Transações

-   **GET** `/transacoes`

#### Descrição

Este endpoint retorna uma lista de transações registradas no sistema. Ele suporta filtros opcionais para refinar os resultados.

**Exemplo de requisição:**

```http
GET /transacoes
Content-Type: application/json
Authorization: Bearer {seu_token}
```

**Exemplo de resposta:**

```json
[
	{
		"id": 1,
		"data": "2023-10-01",
		"valor": 150.0,
		"tipo": "entrada",
		"descricao": "Venda de produto"
	},
	{
		"id": 2,
		"data": "2023-10-02",
		"valor": -50.0,
		"tipo": "saída",
		"descricao": "Compra de material"
	}
]
```

-   **Código de resposta 200**: Lista de transações retornada com sucesso.
-   **Código de resposta 401**: Token de autenticação inválido ou ausente.
-   **Código de resposta 500**: Erro interno no servidor.

### Estrutura do Projeto

-   _src/main/java_: Contém o código fonte principal da palicação.
-   _src/main/resources_: Contém os Arquivos de configuração, como `application.properties`.

### O que ainda há de ser implementado

-   _Testes Unitários_
-   _Testes de Carga_
-   _Testes de Aceite_
-   _Endpoint para dumarização dos dados_

> Fique a vontade para contribuir! 🙈🥸👍
