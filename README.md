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

Nesta seção serão incluídas evidências práticas de uso do `user-service`, como:

### 1️⃣ Exemplo de Controller
Mostra a implementação de endpoints, regras de negócio e comunicação com o service/repository. 

<img width="475" height="398" alt="Esttrutura_Projeto" src="https://github.com/user-attachments/assets/d63c7ac1-0c44-4430-a47c-45e98028342b" />

<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/6f0a7fa6-8f30-4ae7-9af7-ac256eae9153" />

### 2️⃣ Requisições via Postman
Exemplos de chamadas HTTP para cadastro, login, consulta de usuários etc.
<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/e0714402-2c13-4ec8-a493-18fe525766cb" />
<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/5490e5c1-f1b1-4fab-9d82-4537204771b3" />

![Postman Example](caminho/para/imagem-postman.png)

### 3️⃣ Saída JSON esperada
Formato de resposta das APIs, incluindo dados de usuários, roles e timestamps.  
<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/4affb629-755f-4083-8c18-5725792be747" />

<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/02e0887e-3c59-4a5a-8c1e-8caf885187ef" />


![JSON Output](caminho/para/imagem-json.png)

### 4️⃣ Fluxo de autenticação JWT
- Registro e login de usuários  
- Geração de token JWT  
- Autorização de endpoints protegidos  

![JWT Flow](caminho/para/imagem-jwt.png)

### 5️⃣ Eventos assíncronos (Kafka/RabbitMQ)
- Produção e consumo de eventos  
- Integração com outros microserviços  

![Kafka Example](caminho/para/imagem-kafka.png)

### 6️⃣ Testes unitários e de integração
- JUnit 5, Mockito, Testcontainers
- <img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/bcb62b80-6bb6-432d-9ae3-7e7e76822e4b" />

- Testes de endpoints e integração com banco real em container  

![Tests Example](caminho/para/imagem-testes.png)
