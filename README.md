# API de Bandas Musicais

API REST para gerenciamento de bandas musicais desenvolvida com Spring Boot.

## Rotas Disponíveis

Todas as rotas estão disponíveis no endpoint base: `/atv/bandas`

### 1. Listar todas as bandas

**GET** `/atv/bandas`

Retorna uma lista com todas as bandas cadastradas.

**Exemplo de requisição:**
```bash
curl http://localhost:8080/atv/bandas
```

**Exemplo de resposta:**
```json
[
  {
    "id": "1",
    "nome": "The Beatles",
    "genero": "Rock",
    "anoFormacao": "1960"
  },
  {
    "id": "2",
    "nome": "Pink Floyd",
    "genero": "Progressive Rock",
    "anoFormacao": "1965"
  },
  {
    "id": "3",
    "nome": "Queen",
    "genero": "Rock",
    "anoFormacao": "1970"
  }
]
```

### 2. Buscar banda por ID

**GET** `/atv/bandas/{id}`

Retorna os dados de uma banda específica pelo seu ID.

**Exemplo de requisição:**
```bash
curl http://localhost:8080/atv/bandas/1
```

**Exemplo de resposta:**
```json
{
  "id": "1",
  "nome": "The Beatles",
  "genero": "Rock",
  "anoFormacao": "1960"
}
```

### 3. Criar nova banda

**POST** `/atv/bandas`

Cria uma nova banda no sistema.

**Exemplo de requisição:**
```bash
curl -X POST http://localhost:8080/atv/bandas \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Led Zeppelin",
    "genero": "Rock",
    "anoFormacao": "1968"
  }'
```

**Exemplo de resposta:**
```json
{
  "id": "4",
  "nome": "Led Zeppelin",
  "genero": "Rock",
  "anoFormacao": "1968"
}
```

### 4. Atualizar banda

**PUT** `/atv/bandas/{id}`

Atualiza todos os dados de uma banda existente.

**Exemplo de requisição:**
```bash
curl -X PUT http://localhost:8080/atv/bandas/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "The Beatles",
    "genero": "Pop Rock",
    "anoFormacao": "1960"
  }'
```

**Exemplo de resposta:**
```json
{
  "id": "1",
  "nome": "The Beatles",
  "genero": "Pop Rock",
  "anoFormacao": "1960"
}
```

### 5. Deletar banda

**DELETE** `/atv/bandas/{id}`

Remove uma banda do sistema.

**Exemplo de requisição:**
```bash
curl -X DELETE http://localhost:8080/atv/bandas/1
```

**Exemplo de resposta:**
```
Banda 1 removida
```

## Estrutura de Dados

Cada banda possui os seguintes campos:

- `id`: Identificador único (gerado automaticamente)
- `nome`: Nome da banda
- `genero`: Gênero musical
- `anoFormacao`: Ano de formação da banda

## Dados Iniciais

A API já vem com 3 bandas pré-cadastradas:

1. **The Beatles** - Rock (1960)
2. **Pink Floyd** - Progressive Rock (1965)
3. **Queen** - Rock (1970)

## Como Executar

1. Certifique-se de ter Java 21 e Maven instalados
2. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```
3. A API estará disponível em `http://localhost:8080`

## Documentação da API

Após iniciar a aplicação, a documentação Swagger estará disponível em:
- `http://localhost:8080/swagger-ui.html`

