
# Microstore - Plataforma de E-commerce Microsserviços Completa

**Descrição:**  
Microstore é uma plataforma de e-commerce baseada em microsserviços, projetada para estudo e prática avançada de Spring Boot, Spring Cloud, mensageria, cache, observabilidade, testes e DevOps.

---

## **Estrutura geral do projeto**

Cada microsserviço é um projeto Spring Boot independente, com seu próprio banco de dados:

```

microstore/
├── user-service/
├── product-service/
├── order-service/
├── payment-service/
├── notification-service/
├── gateway-service/
├── config-service/
└── observability-service/

```

---

## **Microsserviços e responsabilidades**

### 1️⃣ user-service
- **Responsabilidade:** CRUD de usuários, autenticação JWT, roles
- **Tecnologias:** Spring Boot, Spring Security, JWT, PostgreSQL, Testcontainers, JUnit/Mockito

**Pacotes sugeridos:**
```

com.microstore.users
├── controller
├── dto
├── entity
├── repository
├── service
├── security
└── UserServiceApplication.java

```

**Endpoints principais:**
- `POST /api/users` → criar usuário
- `GET /api/users` → listar usuários
- `GET /api/users/{id}` → buscar usuário
- `PUT /api/users/{id}` → atualizar usuário
- `DELETE /api/users/{id}` → deletar usuário
- `POST /api/auth/login` → autenticação e geração de JWT

**Eventos publicados:** `UserCreatedEvent`

---

### 2️⃣ product-service
- **Responsabilidade:** CRUD de produtos e categorias, estoque
- **Tecnologias:** Spring Boot, PostgreSQL/MongoDB, JPA, Testcontainers

**Pacotes:**
```

com.microstore.products
├── controller
├── dto
├── entity
├── repository
├── service
└── ProductServiceApplication.java

```

**Endpoints principais:**  
- `POST /api/products` → criar produto  
- `GET /api/products` → listar produtos  
- `GET /api/products/{id}` → detalhes do produto  
- `PUT /api/products/{id}` → atualizar produto  
- `DELETE /api/products/{id}` → remover produto  

---

### 3️⃣ order-service
- **Responsabilidade:** criação/atualização de pedidos, cálculo de total
- **Tecnologias:** Spring Boot, PostgreSQL, JPA, Kafka/RabbitMQ, testes

**Pacotes:**
```

com.microstore.orders
├── controller
├── dto
├── entity
├── repository
├── service
└── OrderServiceApplication.java

```

**Endpoints principais:**  
- `POST /api/orders` → criar pedido  
- `GET /api/orders` → listar pedidos  
- `GET /api/orders/{id}` → buscar pedido  
- `PUT /api/orders/{id}` → atualizar pedido  
- `DELETE /api/orders/{id}` → deletar pedido  

**Eventos publicados:** `OrderCreatedEvent`, `OrderUpdatedEvent`

---

### 4️⃣ payment-service
- **Responsabilidade:** processar pagamentos e status
- **Tecnologias:** Spring Boot, Kafka/RabbitMQ, PostgreSQL, Redis (cache de status)

**Pacotes:**
```

com.microstore.payments
├── controller
├── dto
├── entity
├── repository
├── service
└── PaymentServiceApplication.java

```

**Endpoints principais:**  
- `POST /api/payments` → criar pagamento  
- `GET /api/payments/{id}` → status do pagamento  

**Eventos publicados:** `PaymentCompletedEvent`

---

### 5️⃣ notification-service
- **Responsabilidade:** consumir eventos e enviar notificações/logs
- **Tecnologias:** Spring Boot, Kafka/RabbitMQ, Redis, logs centralizados

**Pacotes:**
```

com.microstore.notifications
├── consumer
├── service
└── NotificationServiceApplication.java

```

**Funcionalidade principal:**  
- Consumir eventos de `user-service`, `order-service` e `payment-service`  
- Enviar e-mails ou registrar logs

---

### 6️⃣ gateway-service
- **Responsabilidade:** API Gateway, roteamento e JWT
- **Tecnologias:** Spring Cloud Gateway, JWT, Spring Security

**Funcionalidade:**  
- Centraliza endpoints dos microsserviços  
- Autentica via JWT  
- Aplica limitação de taxa e logging

---

### 7️⃣ config-service
- **Responsabilidade:** configuração centralizada para todos os serviços
- **Tecnologias:** Spring Cloud Config

---

### 8️⃣ observability-service
- **Responsabilidade:** monitoramento de métricas, logs e tracing distribuído
- **Tecnologias:** Prometheus, Grafana, ELK, Jaeger

---

## **Funcionalidades chave do projeto completo**
1. CRUD completo em todos os serviços principais  
2. Autenticação JWT com roles (USER e ADMIN)  
3. Event-driven architecture (Kafka/RabbitMQ)  
4. Cache Redis para dados frequentes  
5. Transações distribuídas (ex.: pedido + pagamento)  
6. Testes unitários e integração (Testcontainers)  
7. CI/CD completo → build → teste → sonar → Docker → deploy em Kubernetes  
8. Observabilidade → métricas, logs centralizados e tracing  

---

## **Como começar**
1. Clonar o repositório de cada microsserviço  
2. Configurar banco de dados local (PostgreSQL/MongoDB/Redis)  
3. Rodar cada serviço individualmente  
4. Testar endpoints usando Postman ou curl  
5. Subir Kafka/RabbitMQ para testar mensageria  
6. Configurar gateway e observabilidade conforme necessidade  

---

> Este README serve como **documentação inicial** para qualquer desenvolvedor compreender o projeto e começar a contribuir.
```

---
