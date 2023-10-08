# ProjetoProgracaoWeb2
Ecommerce Mana | Login, Authentication & CRUD

##1)	Objetivo do Projeto
Este projeto tem o objetivo de disponibilizar uma API composta por um conjunto de endpoints necessários para a operação de um Ecommerce. A macroestrutura do serviço está organizada da seguinte forma:

•	Login
•	Usuário
•	Tipo de Produto & Produto
•	Tipo de Pagamento & Pagamento
•	Pedido


Esse projeto considera ainda os serviços de autenticação de usuário para que todas as requests sejam devidamente autorizados pelos serviços de SecurityFilter e TokenService.

##2)	Segurança
A segurança de acesso à API foi construída considerando os seguintes serviços:
•	Login
•	SecurityConfig
•	SecurityFilter
•	TokenService

De forma geral, esses serviços ficam responsáveis, respectivamente por: 

•	Disponibilizar endpoint para que um usuário cadastrado na API, possa realizar o cadastro de login e receber como retorno um Token do tipo JWT, que deverá ser utilizado nas demais consultas. Vale ressaltar que o token é do tipo Bearer que deverá ser registrado no campo “Authorization” no Header da requisição;

•	O Serviço de security config conterá as configurações de autorização a serem seguidos pela authorizeHttpRequests, padrão do Java que, em nosso caso, serão aplicados somente após os filtros próprios desenvolvidos no projeto. Essa tem a função de inicialmente já realizar a autenticação do usuário para depois seguir para as próximas etapas;

•	Na sequência, o token é submetido a validações com o objetivo de verificar se sua formatação está no formato padrão esperado pelo serviço (Bearer);

•	Por fim, este último serviço será responsável por critptografar/descriptografar a senha de acesso cadastrada pelo usuário com o objetivo de tornar o processo mais seguro. Neste projeto foi utilizado o algoritmo “HMAC512”.







##3)	Funcionalidades

As funcionalidades desenvolvidas neste projeto previram a construção de um CRUD (acrônimo para Create (criar), Read (ler), Update (atualizar) e Delete (apagar)), com o objetivo de proporcionar a realização das seguintes ações:

###3.1)	Create (Criar) - A operação é usada para criar novos registros ou objetos à um banco de dados. 
Exemplo:

//Criar o novo usuário

User newUser = new User();
newUser.setName("Juliana");
newUser.setEmail("juliana@teste.com");
newUser.setPassword("Amareloazul@23");

//Salvar o usuário no banco de dados
UserRepository.save(newUser);

###3.2)	Read (Ler) - A operação é usada para ler os dados do banco de dados.
Exemplo:

UserController
//Chamada para recuperar o email de um usuário a partir do endpoint na API

    @GetMapping ("/email/{email}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }



UserService
//Chamada para recuperar o email de um usuário 

    public UserResponse getUserByEmail(String email){
        User user = (User) userRepository.findByEmail(email);
        return UserConvert.toResponse(user);
    }


UserRepository
//Chamada para recuperar o email de um usuário cadastrado na base de dados

UserDetails findByEmail(String email);


###3.3)	Update (Atualizar) - A operação é usada para atualizar os registros existentes no banco de dados.
//Chamada para realizar a atualização de todos os dados de um usuário na base de dados

    public UserResponse updateUser(Integer id, UserRequest userRequest){
        User user = UserConvert.toEntity(userRequest);
        user.setId(id);
        return UserConvert.toResponse(userRepository.save(user));
    }
###3.4)	Delete (Apagar) | A operação é usada para excluir os registros existentes no banco de dados
     public void deleteUser(Integer id){
        User user = userRepository.findById(id).orElseThrow();
        user.setActive(false);
        userRepository.save(user);
    }

	Aqui vale ressaltar que no projeto foi utilizado apenas o delete lógico dos registros.
Esses por sua vez, nos permitirão realizar: 
•	Gestão de Clientes
•	Gerenciamento de Produtos
•	Gerenciamento de Pagamento
•	Gerenciamento de Pedidos

Na sequência, serão apresentados alguns exemplos do uso do CRUD utilizados nesta aplicação:


##4)	Tecnologias, Framework, BD & Dependências 

Esse projeto considerou os seguintes parâmetros:

•	Linguagem: Java Version 20.0.1
•	Framework Java: Spring Boot
•	Banco de Dados: H2 database
•	Software Teste: Postman
•	Dependências Adicionadas: 
o	com.auth0 | java-jwt
o	springframework.security | spring-security-test
o	org.springframework.boot | spring-boot-starter-security
o	org.springframework.boot | spring-boot-starter-data-jpa
o	org.springframework.boot | spring-boot-starter-test


##5)	Aprendizados & Desafios

•	Como novo aprendizado destaco a criação do projeto de forma modular e com acesso através de endpoints, acessados via Postman. Além disso, neste projeto, foi possível identificar a aplicabilidade dos conceitos SOLID aprendidos no módulo anterior.

•	Adicionalmente, destaco a grande aprendizagem adquirida através do uso/configuração do banco de dados h2 e da construção da função de login e autenticação de usuário. Essa também foi uma das partes mais desafiadoras do projeto, tendo em vista as diversas configurações e etapas de validação necessárias para autenticar um usuário que deseja utilizar uma das requisições que compõem a API de Ecommerce.


 
