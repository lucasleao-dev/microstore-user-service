# 🛒 Microstore - Arquitetura de Microsserviços

## 📌 Descrição
O **Microstore** é um sistema de e-commerce construído com **arquitetura de microsserviços**, onde cada serviço é independente e responsável por uma parte específica da aplicação.  
A proposta é ter uma base sólida, escalável e extensível para lidar com usuários, produtos, pedidos, pagamentos e notificações.

Atualmente já implementados:
- **user-service** → Gerenciamento de usuários e autenticação (Spring Security + JWT).  
- **product-service** → Cadastro e consulta de produtos.  

Planejados:
- **order-service** → Gerenciamento de pedidos.  
- **payment-service** → Processamento de pagamentos.  
- **notification-service** → Envio de e-mails, SMS e notificações push.  

---

## 🏗️ Estrutura dos Microsserviços
microstore/
├── gateway-service/ → API Gateway (Spring Cloud Gateway)
├── microstore-user-service/ → Gerenciamento de usuários
├── product-service/ → Cadastro e consulta de produtos
├── order-service/ → Gerenciamento de pedidos
├── payment-service/ → Processamento de pagamentos
└── notification-service/ → Envio de notificações

markdown
Copiar código

---

## 🚀 Tecnologias Utilizadas

### 🔹 Backend & Frameworks
- Java 21  
- Spring Boot 3  
- Spring Web (REST API)  
- Spring Data JPA (CRUD com repositórios)  
- Spring Security + JWT (autenticação e roles no **user-service**)  

### 🔹 Banco de Dados
- **H2 Database** → usado em memória nos testes.  
- **PostgreSQL** → planejado para produção (cada serviço terá seu banco próprio).  

### 🔹 Testes
- JUnit 5  
- Mockito (mock de dependências)  
- Testcontainers (subindo PostgreSQL real em container para testes de integração)  

### 🔹 Boas práticas
- SOLID  
- Clean Code  
- Arquitetura em camadas (controller, service, repository, dto, entity)  
- Separação clara de responsabilidades  
- DTOs para comunicação entre camadas  

### 🔹 DevOps & Observabilidade
- Spring DevTools (reload automático no desenvolvimento)  
- Logs configurados (Hibernate SQL + parâmetros)  
- Docker (planejado para empacotar cada microserviço em imagens)  
- CI/CD com GitHub Actions (pipeline em construção)  

### 🔹 Arquitetura de Microsserviços
- API Gateway com **Spring Cloud Gateway**  
- Serviços independentes com banco próprio  
- Comunicação via REST APIs  
- Configuração preparada para expansão com novos serviços  

---

## 📌 Funcionalidades Futuras (já previstas no design)
- Mensageria (Kafka ou RabbitMQ) → eventos assíncronos.  
- Cache distribuído (Redis).  
- Observabilidade avançada (Prometheus, Grafana, Jaeger).  
- Orquestração de deploys com Kubernetes.  

---

## ⚡ Como rodar
1. Clone o repositório:
   ```bash
   git clone git@github.com:lucasleao-dev/microstore-user-service.git
   cd microstore-user-service
Entre no microserviço desejado:

cd user-service
Execute:

mvn spring-boot:run
Para rodar os testes:


mvn test
📖 Resumindo para currículo
Você já aplicou ou está trabalhando com:

Java 21, Spring Boot 3, Spring Data JPA, Spring Security, JWT

PostgreSQL, H2

JUnit 5, Mockito, Testcontainers

Docker, GitHub Actions (CI/CD)

Arquitetura de microsserviços

Boas práticas: SOLID, Clean Code, DTOs, separação de responsabilidades

👨‍💻 Autor
