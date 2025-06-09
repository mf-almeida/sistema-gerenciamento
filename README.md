# 🐾 Sistema de Gerenciamento de Petshop

Este é um sistema de gerenciamento para um petshop, desenvolvido em **Java**. O sistema é executado em um console interativo, permitindo cadastrar e listar animais, além de calcular preços de serviços. Ele demonstra conceitos importantes de **Orientação a Objetos** (Herança, Polimorfismo) e boas práticas como o uso do padrão **DAO** e separação de credenciais sensíveis.

---

## ✨ Funcionalidades

- **Cadastro de Animais:** Cachorros, Gatos e Outros, cada um com atributos específicos.
- **Listagem de Animais:** Exibe todos os animais cadastrados em tabela formatada no console.
- **Cálculo de Serviços:** Banho, Tosa e Consulta Veterinária, com regras específicas para cada tipo de animal.
- **Persistência de Dados:** Todos os dados são salvos em um banco de dados MySQL.

---

## 🛠 Tecnologias Utilizadas

- **Java:** Linguagem principal
- **Maven:** Gerenciamento de dependências e build
- **MySQL:** Banco de dados relacional
- **JDBC:** Conexão Java com bancos de dados

---

## ⚙️ Pré-requisitos

- Java 8 ou superior
- Apache Maven
- Servidor MySQL

---

## 🗄️ Configuração do Banco de Dados

1. **Conecte-se ao MySQL como root:**
   ```sh
   mysql -u root -p
   ```

2. **Crie a base de dados e a tabela:**
   ```sql
   -- 1. Criar a base de dados
   CREATE DATABASE IF NOT EXISTS petshop;

   -- 2. Usar a base de dados
   USE petshop;

   -- 3. Criar a tabela de animais
   CREATE TABLE animais (
       id INT AUTO_INCREMENT PRIMARY KEY,
       nome VARCHAR(100) NOT NULL,
       raca_tipo VARCHAR(100),
       idade INT,
       proprietario VARCHAR(100),
       tipo_animal VARCHAR(50) NOT NULL,
       porte VARCHAR(50),
       cor_olhos VARCHAR(50),
       tipo_especifico VARCHAR(100)
   );

   -- 4. Criar um usuário seguro para a aplicação
   CREATE USER 'petshop_app'@'localhost' IDENTIFIED BY 'sua_senha_forte_aqui';

   -- 5. Conceder permissões necessárias
   GRANT SELECT, INSERT, UPDATE, DELETE ON petshop.* TO 'petshop_app'@'localhost';

   -- 6. Aplicar as alterações
   FLUSH PRIVILEGES;
   ```

---

## ⚙️ Configuração do Projeto

Após configurar o banco, você precisa informar as credenciais de acesso à aplicação.

1. **Crie o arquivo de configuração:**
   - Crie o arquivo `database.properties` dentro da pasta `src/main/resources/`.

2. **Adicione suas credenciais ao arquivo:**
   - Abra o `database.properties` e adicione o conteúdo abaixo, substituindo `sua_senha_forte_aqui` pela senha que você definiu no passo anterior.
     ```properties
     db.url=jdbc:mysql://localhost:3306/petshop
     db.user=petshop_app
     db.password=sua_senha_forte_aqui
     ```

---

## 🚀 Como Executar

Com o projeto e o banco de dados configurados, você pode executar o sistema via linha de comando na raiz do projeto.

1. **Compile o projeto:**
   ```sh
   mvn clean compile
   ```

2. **Execute o sistema:**
   ```sh
   mvn exec:java -Dexec.mainClass="br.com.petshop.GerenciadorPetshop"
   ```

---

## 🔒 Observações de Segurança

- O arquivo `database.properties` é intencionalmente ignorado pelo Git através do arquivo `.gitignore`. Isso é uma prática de segurança fundamental para evitar que credenciais sensíveis sejam expostas em repositórios de código.

- A aplicação utiliza um usuário de banco de dados (`petshop_app`) com permissões limitadas, em vez do usuário `root`, seguindo o Princípio do Menor Privilégio.

## 📄 Licença
- Projeto para fins educacionais.

