# 📊 Sales Report API — Spring Boot & JPA

Este projeto é uma **API REST desenvolvida com Java e Spring Boot**, focada em consultas avançadas utilizando **Spring Data JPA**, paginação e aplicação de regras de negócio no service.

O sistema trabalha com **vendas (Sale)** e **vendedores (Seller)**, permitindo gerar relatórios detalhados e um sumário de vendas por vendedor em um determinado período.

---

## 🛠️ Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- Banco de dados relacional (H2 / compatível)
- Postman (testes dos endpoints)

---

## 🧩 Modelo de domínio

### Sale
- id
- date
- amount
- seller

### Seller
- id
- name
- sales (relação 1:N)

---

## 🚀 Funcionalidades

### 🔹 Relatório de vendas — `/sales/report`

Retorna uma listagem **paginada** contendo:
- id da venda
- data da venda
- valor vendido
- nome do vendedor

#### Parâmetros opcionais:
- `minDate` → data inicial
- `maxDate` → data final
- `name` → trecho do nome do vendedor

#### Regras de negócio:
- Se `maxDate` não for informada, será considerada a data atual do sistema
- Se `minDate` não for informada, será considerada a data de **1 ano antes da data final**
- Se `name` não for informado, será considerado texto vazio, retornando todos os vendedores  
