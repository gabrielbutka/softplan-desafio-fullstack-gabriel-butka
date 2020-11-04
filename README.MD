## MVP Gerenciador de Processos


### Iniciando
- Backend: `mvn spring-boot:run`
- URL: http://localhost:8080
- Frontend: `yarn start`
- URL: http://localhost:3000

Por padrão, utiliza o banco de dados H2 em memória. Poderá ser configurado no arquivo `application.properties`.

### Usuários pré-cadastrados

- Administrador 1
    - E-mail: administrador1@softplan.com.br
    - Tipo: administrador
    - Senha: administrador1
    
- Triador 1
    - E-mail: triador1@softplan.com.br
    - Tipo: triador
    - Senha: triador1
    
- Finalizador 1
    - E-mail: finalizador1@softplan.com.br
    - Tipo: finalizador
    - Senha: finalizador1

- Finalizador 2
    - E-mail: finalizador2@softplan.com.br
    - Tipo: finalizador
    - Senha: finalizador2
    
- Finalizador 3
    - E-mail: finalizador3@softplan.com.br
    - Tipo: finalizador
    - Senha: finalizador3

### O que foi utilizado

- spring-boot
- react (componentes funcionais)
- ant.design (primeira vez que usei)
- flyway (scripts db)
- lombok
- hibernate
- h2 database
- basic auth
- i18n (somente pt-br)