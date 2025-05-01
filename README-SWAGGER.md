# Como Instalar e Configurar o Swagger no Projeto AlgaFood

Este guia explica como instalar e configurar o Swagger (OpenAPI) no projeto AlgaFood para documentar sua API REST.

## 1. Adicionar Dependências

Adicione as seguintes dependências ao arquivo `pom.xml`:

```xml
<!-- SpringDoc OpenAPI UI -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

Para projetos Spring Boot 2.x, use:

```xml

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.15</version>
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
                                    ));
    }
}
```

## 3. Documentar DTOs e Controllers

Use a anotação `@Schema` para documentar seus DTOs:

```java
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa uma cozinha no sistema")
public record CozinhaDto(
        @Schema(description = "ID da cozinha", example = "1")
        Long id,

        @Schema(description = "Nome da cozinha", example = "Brasileira")
        String nome
) { }
```

Para documentar controllers, use as anotações `@Operation`, `@ApiResponse`, etc:

```java
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cozinhas")
@Tag(name = "Cozinhas", description = "Gerenciamento de cozinhas")
public class CozinhaController {

    @GetMapping
    @Operation(summary = "Lista todas as cozinhas",
            description = "Retorna uma lista com todas as cozinhas cadastradas")
    @ApiResponse(responseCode = "200", description = "Cozinhas listadas com sucesso")
    public List<CozinhaDto> listar() {
        // ...
    }
}
```

## 4. Acessar a Documentação da API

Após iniciar a aplicação, acesse a documentação da API em:

- Swagger UI: http://localhost:8080/swagger-ui.html
- Documentação OpenAPI: http://localhost:8080/v3/api-docs

## 5. Configurações Adicionais (Opcional)

Para personalizar ainda mais a documentação, você pode adicionar as seguintes propriedades ao arquivo `application.yml`:

```yaml
springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operationsSorter: method
    tagsSorter: alpha
    disable-swagger-default-url: true
```

## Solução de Problemas

Se você encontrar problemas com as dependências do Maven, verifique:

1. Se o repositório Maven está configurado corretamente
2. Se você está usando a versão correta das dependências para sua versão do Spring Boot
3. Se há problemas de conectividade com os repositórios Maven

Para Spring Boot 3.x, use `springdoc-openapi-starter-webmvc-ui`
Para Spring Boot 2.x, use `springdoc-openapi-ui`