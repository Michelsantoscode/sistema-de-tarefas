# 📌 Sistema de Gerenciamento de Tarefas (Spring Boot API)

API REST desenvolvida com **Spring Boot** para gerenciamento de usuários e tarefas, aplicando boas práticas de arquitetura em camadas, validação, tratamento de exceções e mapeamento de DTOs.

---

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Web (REST API)
- Spring Data JPA
- Hibernate
- Bean Validation (Jakarta Validation)
- ModelMapper
- Lombok
- Maven
- Banco de dados relacional (MySQL)

---

## 🧠 Arquitetura do projeto

O projeto segue uma arquitetura em camadas:

controller → service → repository → domain
                     ↓
                assembler (DTO mapping)

### 📦 Camadas

- **Controller**: expõe endpoints REST
- **Service**: contém regras de negócio
- **Repository**: acesso ao banco de dados
- **Domain**: entidades do sistema
- **Assembler**: conversão entre DTOs e entidades

---

## 📌 Funcionalidades

### 👤 Usuários

- Criar usuário  
- Listar usuários  
- Buscar usuário por ID  
- Atualizar usuário  
- Remover usuário  

### 📋 Tarefas

- Criar tarefa vinculada a um usuário  
- Listar tarefas  
- Buscar tarefa por ID  
- Atualizar tarefa  
- Alterar status (PENDENTE / CONCLUÍDA)  
- Remover tarefa  

-

## 🔄 Regras de negócio

- Não é permitido cadastrar usuários com e-mail duplicado  
- Toda tarefa deve estar associada a um usuário existente  
- Status padrão de uma tarefa: **PENDENTE**  
- Ao concluir uma tarefa, a data de conclusão é registrada automaticamente  

---

## 📦 DTOs

O projeto utiliza DTOs para separar a camada de API da camada de domínio:

### Entrada (Input)

- UsuarioInput  
- TarefaInput  
- StatusInput  

### Saída (Model)

- UsuarioModel  
- TarefaModel  

---

## 🔁 Mapeamento de objetos

O projeto utiliza **ModelMapper** para conversão entre:

- DTO → Entidade  
- Entidade → DTO  

Pacote responsável:

com.michel.tarefas.api.assembler

---

## ⚠️ Tratamento de exceções

Implementado com `@ControllerAdvice` e `ProblemDetail`.

### Tipos de erro

- NegocioException → regras de negócio (400)  
- EntidadeNaoEncontrada → recurso inexistente (404)  

### Exemplo de resposta

{
  "type": "/erros/negocio",
  "title": "erro de negocio",
  "status": 400,
  "detail": "Email já cadastrado"
}

---

## 🗄️ Modelo de dados

### Usuario

- id  
- nome  
- email  
- tarefas (1:N)  

### Tarefa

- id  
- titulo  
- descricao  
- status (PENDENTE / CONCLUÍDA)  
- dataCadastro  
- dataConclusao  
- usuario (N:1)  

---

## 🔗 Endpoints principais

### Usuários

POST /usuarios  
GET /usuarios  
GET /usuarios/{id}  
PUT /usuarios/{id}  
DELETE /usuarios/{id}  


### Tarefas

POST /tarefas  
GET /tarefas  
GET /tarefas/{id}  
PUT /tarefas/{id}  
DELETE /tarefas/{id}  
PUT /tarefas/{id}/status 

## 🎨 Frontend

O frontend foi desenvolvido em **HTML, CSS e JavaScript puro** com auxílio do **Claude**, e está integrado diretamente ao projeto Spring Boot servido como arquivo estático em `src/main/resources/static/`.

### ✅ Funcionalidades da interface

- 📊 Dashboard com cards de resumo (total, pendentes, concluídas)
- 📋 Listagem de tarefas e usuários em tabela interativa
- 🔍 Filtro por status e busca em tempo real
- 🖊️ Modais para criar e editar tarefas e usuários
- 🔄 Alternância de status direto na tabela (pendente ↔ concluída)
- 🔢 Contador de caracteres respeitando os limites das validações do backend
- ⚠️ Exibição das mensagens de erro retornadas pela API via `ProblemDetail`

> 💡 A IA foi utilizada como ferramenta de apoio no desenvolvimento da interface, acelerando a criação do frontend enquanto o foco principal era mantido na arquitetura e qualidade do backend. Isso demonstra como ferramentas de IA podem ser integradas de forma produtiva no fluxo de trabalho de um desenvolvedor.


## 👨‍💻 Autor

Projeto desenvolvido por **Michel**.
