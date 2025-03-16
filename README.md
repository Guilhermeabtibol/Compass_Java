
# Compass Java

O **Compass Java** é uma aplicação desenvolvida em Java com o objetivo de gerenciar usuários. A aplicação permite realizar operações de CRUD (Create, Read, Update, Delete) para adicionar, listar, atualizar e excluir usuários de forma eficiente e simples. A aplicação utiliza o Spring Boot para a construção da API e o framework Swing para a interface gráfica.

## Funcionalidades

- Cadastro de novos usuários
- Listagem de usuários registrados
- Edição de informações de usuários
- Exclusão de usuários

## Tecnologias Utilizadas

- **Java**
- **Spring Boot**: Para construção da API REST.
- **JPA/Hibernate**: Para persistência de dados no banco de dados.
- **Swing**: Para a construção da interface gráfica.
- **MySQL**: Banco de dados relacional para armazenar dados dos usuários.

## Instalação

Para rodar a aplicação localmente, siga as etapas abaixo:

1. **Clonar o repositório:**
   Abra o terminal e digite:

   ```bash
   git clone https://github.com/Guilhermeabtibol/Compass_Java.git
   cd Compass_Java
   ```

2. **Configurar o banco de dados MySQL:**

   - Crie um banco de dados no MySQL, por exemplo `compass_db`.
   - Altere as configurações de conexão no arquivo `application.properties` (localizado em `src/main/resources`) para refletir suas credenciais do MySQL:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/compass_db
     spring.datasource.username=root
     spring.datasource.password=senha
     ```

3. **Compilar e executar o projeto:**

   Execute o seguinte comando para compilar e rodar a aplicação:

   ```bash
   mvn spring-boot:run
   ```

   Isso iniciará o servidor na porta padrão `8080`.

4. **Interface gráfica (Swing):**

   Se você quiser utilizar a interface gráfica, execute a classe `UsuarioApp` para abrir a janela do gerenciador de usuários.

## Uso da Aplicação

A aplicação fornece uma interface gráfica (Swing) onde o usuário pode registrar novos usuários, visualizar a lista de usuários, e excluir usuários.

### Operações disponíveis:

- **Registrar Usuário**: Informe o nome, e-mail e senha e clique em "Registrar".
- **Listar Usuários**: Clique em "Listar Usuários" para visualizar todos os usuários cadastrados.
- **Excluir Usuário**: Selecione um usuário na tabela e clique em "Excluir".

### API REST:

- **GET /usuarios**: Retorna todos os usuários cadastrados.
- **GET /usuarios/{id}**: Retorna um usuário específico pelo ID.
- **POST /usuarios**: Cria um novo usuário.
- **DELETE /usuarios/{id}**: Exclui um usuário pelo ID.

## Estrutura de Diretórios

```
Compass_Java/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── gui/                  # Interface gráfica (Swing)
│   │   │   ├── tech/                 # Pacote principal
│   │   │   │   ├── compass/
│   │   │   │   │   ├── controller/    # Controladores
│   │   │   │   │   ├── model/         # Modelos de dados
│   │   │   │   │   ├── repository/    # Repositórios de dados
│   │   │   │   │   └── service/       # Lógica de negócios
│   │   │   └── resources/
│   │   │       └── application.properties # Configurações do banco de dados
│   └── test/
│       └── java/
│           └── tech/
└── pom.xml                           # Arquivo de configuração do Maven
```

## Como Contribuir

Se você quiser contribuir com melhorias ou correções para o projeto, siga os seguintes passos:

1. Fork o repositório.
2. Crie uma branch para a sua feature ou correção.
3. Faça as modificações necessárias.
4. Crie os testes para garantir que tudo funcione corretamente.
5. Envie um Pull Request explicando suas alterações.

Lembre-se de manter o estilo de codificação do projeto e de seguir as melhores práticas de desenvolvimento!

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
