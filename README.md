# 🛒 API Mercadinho - Controle de Estoque

API REST desenvolvida para resolver o **Problema 3** da atividade proposta. O sistema gerencia produtos e movimentações, garantindo que não ocorram vendas de itens sem estoque.

## 🛠️ Tecnologias
* **Java 21** & **Spring Boot 3.5.13**
* **JPA / Hibernate**: Para persistência e relacionamentos
* **H2 Database**: Banco de dados em memória
* **Maven**: Gestão de dependências

## 📌 Regras de Negócio
* **CRUD Completo**: Gestão total de produtos (Criar, Ler, Atualizar e Deletar).
* **Bloqueio de Estoque**: Impede saídas se a quantidade for insuficiente ou o estoque estiver zerado.
* **Histórico Automático**: Cada operação gera um registro com data e hora.
* **Status HTTP**: Uso correto de retornos como `201 Created`, `204 No Content` e `400 Bad Request`.

---

## 🚀 Endpoints Principais

### 1. Produtos (`/produtos`)
* `POST`: Cadastrar produto.
* `GET`: Listar todos os produtos.
* `PUT`: Atualizar dados do produto.
* `DELETE`: Remover produto.

### 2. Movimentações (`/movimentacoes`)
* `POST`: Registrar entrada ou saída.
* `GET`: Consultar histórico de movimentações.

---

## 🧪 Bateria de Testes (Postman)

### A. Cadastrar Produto
**POST** `/produtos`
```json
{
  "nome": "Arroz 5kg",
  "preco": 25.00,
  "quantidadeEstoque": 10
}