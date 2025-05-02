# Como Instalar e Configurar o Swagger no Projeto AlgaFood

Este guia explica como instalar e configurar o Swagger (OpenAPI) no projeto AlgaFood para documentar sua API REST.

## 1. Adicionar Dependências

Adicione as seguintes dependências ao arquivo `pom.xml`:

```xml
<!-- SpringDoc OpenAPI UI -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

## 2. Criar Classe de Configuração do Swagger

Crie uma classe de configuração para personalizar a documentação da API:

```java
package org.estudos.algafoods.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                              .title("AlgaFood API")
                              .description("API para gerenciamento de restaurantes e pedidos de comida")
                              .version("1.0.0")
                              .contact(new Contact()
                                               .name("Suporte AlgaFood")
                                               .email("suporte@algafood.com")
                                               .url("https://algafood.com"))
                              .license(new License()
                                               .name("Apache 2.0")
                                               .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .components(new Components()
                                    .addSecuritySchemes("bearer-key",
                                                        new SecurityScheme()
                                                                .type(SecurityScheme.Type.HTTP)
                                                                .scheme("bearer")
                                                                .bearerFormat("JWT")
                                    ))
                .addServersItem(new Server().url("http://localhost:8080").description("Servidor Local"));
        ;
    }
}
```

## 3. Acessar a Documentação da API

Após iniciar a aplicação, acesse a documentação da API em:

- Swagger UI: http://localhost:8080/swagger-ui.html
- Documentação OpenAPI: http://localhost:8080/v3/api-docs

## 4. Configurações Adicionais (Opcional)

Para personalizar ainda mais a documentação, você pode adicionar as seguintes propriedades ao arquivo `application.yml`:

```yaml
springdoc:
  swagger-ui:
    path: /swagger-ui.html
    operations-sorter: method
    tags-sorter: alpha
    disable-swagger-default-url: true
  api-docs:
    path: /v3/api-docs
  packages-to-scan: org.estudos.algafoods
  paths-to-match:
    - /api/**
```

## Solução de Problemas

Se você encontrar problemas com as dependências do Maven, verifique:

1. Se o repositório Maven está configurado corretamente
2. Se você está usando a versão correta das dependências para sua versão do Spring Boot
3. Se há problemas de conectividade com os repositórios Maven

Para Spring Boot 3.x, use `springdoc-openapi-starter-webmvc-ui`