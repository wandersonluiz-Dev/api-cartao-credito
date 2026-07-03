# 🚀 Funcionalidades

A API simula o funcionamento do back-end de um sistema de cartão de crédito, implementando regras de negócio encontradas em aplicações financeiras.

---

## 👤 Gerenciamento de Clientes

Permite o gerenciamento completo dos clientes do sistema.

### Funcionalidades

- Cadastro de novos clientes.
- Consulta de clientes por ID.
- Atualização dos dados cadastrais.
- Desativação de clientes.
- Listagem de todos os clientes (Administrador).

Além das operações CRUD, o sistema realiza validações para garantir a integridade dos dados, como CPF, e-mail e telefone.

---

## 💳 Gerenciamento de Cartões

Cada cliente pode possuir um cartão de crédito vinculado ao seu cadastro.

Durante a emissão do cartão são realizadas automaticamente as seguintes operações:

- Associação do cartão ao cliente.
- Geração automática do número do cartão.
- Geração automática do código de segurança (CVV).
- Definição da data de validade.
- Inicialização do limite total e do limite disponível.

Também é possível:

- Consultar cartões.
- Bloquear cartões.
- Cancelar cartões.
- Listar todos os cartões (Administrador).

---

## 🛒 Gerenciamento de Compras

O sistema permite registrar compras realizadas utilizando o cartão de crédito.

Antes da aprovação da compra, são realizadas diversas validações de negócio, como:

- Existência do cartão.
- Cartão ativo.
- Cartão não bloqueado.
- Cartão não cancelado.
- Limite disponível suficiente.
- Valor da compra maior que zero.
- Quantidade de parcelas válida.

Após a aprovação da compra:

- O limite disponível do cartão é reduzido automaticamente pelo valor total da compra.
- O histórico da compra é armazenado.
- O valor da parcela passa a compor a fatura mensal do cartão.

---

## 🧾 Gerenciamento de Faturas

As faturas representam todas as parcelas das compras realizadas durante um determinado período.

Cada fatura possui:

- Valor total.
- Data de vencimento.
- Status da fatura.
- Cartão associado.

O sistema permite:

- Gerar novas faturas.
- Consultar uma fatura por ID.
- Consultar todas as faturas de um cartão.
- Fechar uma fatura.

Ao calcular o valor da fatura, o sistema considera **o valor das parcelas das compras**, simulando o comportamento de cartões de crédito reais, onde apenas as parcelas correspondentes ao mês compõem a cobrança.

---

## 💰 Gerenciamento de Pagamentos

O pagamento da fatura também segue regras de negócio para manter a consistência financeira do sistema.

Ao registrar um pagamento:

- O valor informado é abatido da fatura.
- O limite disponível do cartão é restaurado automaticamente conforme o pagamento realizado.
- O histórico de pagamentos é armazenado para futuras consultas.

---

# 🔒 Segurança

A API utiliza **Spring Security** juntamente com autenticação baseada em **JWT (JSON Web Token)**.

Foram implementados:

- Cadastro de usuários.
- Login com autenticação.
- Criptografia de senhas utilizando BCrypt.
- Geração e validação de Tokens JWT.
- Controle de acesso baseado em Roles.

### Roles disponíveis

- ROLE_CLIENTE
- ROLE_ADMIN

### Permissões

#### ROLE_CLIENTE

- Consultar seus dados.
- Atualizar seus dados.
- Consultar cartões.
- Realizar compras.
- Consultar compras.
- Consultar faturas.
- Realizar pagamentos.

#### ROLE_ADMIN

Além das permissões do cliente, também pode:

- Listar clientes.
- Emitir cartões.
- Bloquear cartões.
- Cancelar cartões.
- Listar cartões.
- Gerar faturas.
- Fechar faturas.
- Listar compras.
- Listar pagamentos.

# 🔐 Configuração

Antes de executar a aplicação, copie o arquivo:

```text
application.properties.example
```

para:

```text
application.properties
```

Depois configure as variáveis de ambiente abaixo:

| Variável | Descrição |
|----------|-----------|
| DB_URL | URL do PostgreSQL |
| DB_USERNAME | Usuário do banco |
| DB_PASSWORD | Senha do banco |
| JWT_KEY | Chave utilizada para geração do JWT |
| JWT_EXPIRATION | Tempo de expiração do token em milissegundos |

Exemplo:

```text
DB_URL=jdbc:postgresql://localhost:5432/api_cartao_security

DB_USERNAME=postgres

DB_PASSWORD=senha

JWT_KEY=sua_chave_super_secreta

JWT_EXPIRATION=900000
```

---

# ⭐ Principais Regras de Negócio

Durante o desenvolvimento da aplicação foram implementadas diversas regras para simular o comportamento de um sistema financeiro, entre elas:

- Cadastro e gerenciamento de clientes.
- Emissão automática de cartões de crédito.
- Geração automática do número do cartão e CVV.
- Controle do limite total e limite disponível.
- Validação do status do cartão antes de realizar compras.
- Redução automática do limite disponível após uma compra aprovada.
- Associação entre clientes, cartões, compras, faturas e pagamentos.
- Cálculo das faturas considerando o valor das parcelas das compras.
- Restauração do limite disponível conforme os pagamentos realizados.
- Controle de autenticação utilizando JWT.
- Controle de autorização baseado em perfis de usuário.
- Tratamento global de exceções.
- Validação de dados utilizando Bean Validation.
