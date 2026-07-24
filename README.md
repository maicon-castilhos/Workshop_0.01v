# Sistema de Gestão de Oficina Mecânica

Aplicação de console em Java desenvolvida para simular um sistema 
de gestão de oficinas mecânicas, substituindo o controle manual 
feito em papel e planilhas por uma solução digital estruturada.

## Sobre o Projeto

Este é um projeto pessoal e contínuo — desenvolvido de forma 
independente para aplicar conceitos de Java conforme são aprendidos, 
crescendo gradualmente rumo a uma aplicação backend completa com 
Spring Boot, integração com banco de dados e testes automatizados.

## Funcionalidades

- Cadastro de cliente com dados de contato
- Cadastro de veículo vinculado a um cliente
- Cadastro de ordem de serviço vinculada a um veículo
- Controle de status da ordem (OPEN, IN_PROGRESS, FINISHED)
- Exibição em cascata dos dados (Cliente → Veículo → Ordens de Serviço)

## Estrutura de Classes

- `Client` — dados pessoais e lista de veículos possuídos
- `Vehicle` — dados do veículo e histórico de ordens de serviço
- `ServiceOrder` — data do serviço, descrição, preço e status
- `OrderStatus` — enum representando o ciclo de vida da ordem

## Relacionamento de Domínio

Client (1) ──possui──> Vehicle (N)
Vehicle (1) ──tem──> ServiceOrder (N)


## Tecnologias

- Java 21
- API moderna de datas (LocalDate, DateTimeFormatter)
- Enum para controle de status de domínio
- StringBuilder para exibição em cascata de objetos

## Como Rodar

1. Clone o repositório
2. Abra no Eclipse ou IntelliJ
3. Execute a classe `Main.java`
4. Siga as instruções no console

## Exemplo de Uso

Dados do cliente:
Nome: João
Celular: 11999999999
E-mail: joao@email.com

Dados do veículo:
Placa: ABC1234
Modelo: Civic
Marca: Honda
Ano: 2020

Dados da ordem de serviço:
Data do serviço (dd/MM/yyyy): 20/07/2026
Descrição: Troca de óleo
Preço: 150.00
Status (OPEN, IN_PROGRESS, FINISHED): OPEN

========== DADOS FINAIS ==========
Nome: João
Celular: 11999999999
E-mail: joao@email.com
Veículos:
Placa: ABC1234, Modelo: Civic, Marca: Honda, Ano: 2020
Ordens de Serviço:

Data: 20/07/2026, Descrição: Troca de óleo, Preço: R$ 150.00, Status: OPEN

## Roadmap

- [ ] Comparable/Comparator para ordenar ordens de serviço por data ou preço
- [ ] Exceções customizadas para validação de regras de negócio
- [ ] Collections (Map) para busca rápida de veículo por placa
- [ ] Suporte a múltiplos clientes com cadastro em loop
- [ ] Migração para Spring Boot com API REST
- [ ] Integração com banco de dados (PostgreSQL + JPA)
- [ ] Testes automatizados (JUnit)

## Conceitos Praticados

- Composição de objetos em três níveis (Client → Vehicle → ServiceOrder)
- Enum para status de domínio
- Encapsulamento e design de classes limpo
- StringBuilder para formatação de objetos complexos
- Debug independente (problema de buffer do Scanner)