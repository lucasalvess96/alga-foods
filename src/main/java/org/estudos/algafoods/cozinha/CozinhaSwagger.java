package org.estudos.algafoods.cozinha;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.estudos.algafoods.excecao.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CozinhaSwagger {

    @Operation(summary = "Criar uma nova cozinha", description = "Cria uma nova cozinha caso os campos estiverem " +
            "preenchidos de forma válida",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cozinha criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResourceNotFoundException.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "timestamp": "01/05/2025 20:21:08",
                                                "status": 400,
                                                "error": "Ocorreu erro ao salvar informações, verifique seus campos de preenchimentos",
                                                "path": "/cozinha/create",
                                                "message": "O campo NOME deve conter apenas letras"
                                            }
                                            """

                            )
                    )),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error - Erro interno no " +
                            "servidor ao tentar " +
                            "processar requisicao",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResourceNotFoundException.class),
                                    examples = @ExampleObject(
                                            value = """
                                                        {
                                                          "timestamp": "01/05/2025 20:21:08",
                                                          "status": 500,
                                                          "message": "Erro ao enviar informações"
                                                        }
                                                    """
                                    )
                            )
                    )
            })
    ResponseEntity<CozinhaDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto contendo informações da cozinha",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CozinhaDto.class)
                    )
            )
            CozinhaDto personCreateDto);

    @Operation(summary = "Listar todas as cozinhas", description = "Lista quantidade de cozinhas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de cozinha retornada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CozinhaDto.class))
                            )
                    ),
                    @ApiResponse(responseCode = "204", description = "Não há informações na base dados",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResourceNotFoundException.class),
                                    examples = @ExampleObject(
                                            value = "[]"
                                    )
                            )
                    )
            })
    ResponseEntity<List<CozinhaDto>> list();

    @Operation(summary = "Listar cozinha com paginação",
            description = "Lista quantidade de cozinha por página",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de cozinha retornada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Page.class, allowableValues = "type=PatientDto"),
                                    examples = @ExampleObject(
                                            value = "{\"content\":[{\"id\":1,\"nome\":\"john Doe\",\"age\":\"23\"," +
                                                    "\"cpf\":\"12345678951\"}],\"pageable\":{\"pageNumber\":0," +
                                                    "\"pageSize\":10,\"sort\":{\"empty\":true,\"sorted\":false," +
                                                    "\"unsorted\":true},\"offset\":0,\"paged\":true," +
                                                    "\"unpaged\":false},\"totalPages\":1,\"totalElements\":1," +
                                                    "\"last\":true,\"size\":10,\"number\":0,\"sort\":{\"empty\":true," +
                                                    "\"sorted\":false,\"unsorted\":true},\"first\":true," +
                                                    "\"numberOfElements\":1,\"empty\":false}"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "204", description = "Não há informações na base de dados",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Page.class),
                                    examples = @ExampleObject(
                                            value = "{\"content\": [],\"pageable\": {\"pageNumber\": 0,\"pageSize\": " +
                                                    "10,\"sort\": {\"empty\": true,\"sorted\": false,\"unsorted\": " +
                                                    "true},\"offset\": 0,\"paged\": true,\"unpaged\": false}," +
                                                    "\"last\": true,\"totalPages\": 0,\"totalElements\": 0,\"size\": " +
                                                    "10,\"number\": 0,\"sort\": {\"empty\": true,\"sorted\": false," +
                                                    "\"unsorted\": true},\"first\": true,\"numberOfElements\": 0," +
                                                    "\"empty\": true}"
                                    )
                            )
                    )
            })
    ResponseEntity<Page<CozinhaDto>> pagination(Pageable pageable);

    @Operation(summary = "Obter detalhes de uma cozinha pelo ID", description = "busca e informa detalhes da cozinha",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Detalhes da cozinha encontrada"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResourceNotFoundException.class),
                            examples = @ExampleObject(
                                    value = """
                                              {
                                                  "timestamp": "02/05/2025 08:56:09",
                                                  "status": 404,
                                                  "error": "Recurso não encontrado",
                                                  "path": "/cozinha/detail/13333",
                                                  "message": "Informações não encontradas para: 13333"
                                              }
                                            """
                            )
                    )),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error - Erro interno no " +
                            "servidor ao tentar " +
                            "processar requisicao",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResourceNotFoundException.class),
                                    examples = @ExampleObject(
                                            value = "{\"timestamp\":\"2024-01-15T20:08:06\",\"status\":500," +
                                                    "\"message\":\"Erro ao enviar informações\"}"
                                    )
                            )
                    )
            })
    ResponseEntity<CozinhaDto> detail(
            @Parameter(
                    name = "id",
                    description = "ID da cozinha",
                    in = ParameterIn.PATH,
                    example = "1",
                    required = true,
                    schema = @Schema(type = "integer", format = "int64", example = "1")
            )
            Long id
    );

    @Operation(summary = "Atualiza uma nova cozinha", description = "método de atualizar",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dados alterados com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResourceNotFoundException.class),
                            examples = @ExampleObject(
                                    value = "{\"timestamp\":\"2024-01-15T20:08:06\",\"status\":400," +
                                            "\"error\":\"Erro na informação do campo\",\"message\": \"O campo NOME " +
                                            "não deve está vazio, O campo NOME deve conter apenas letras\"}"
                            )
                    )),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error - Erro interno no " +
                            "servidor ao tentar " +
                            "processar requisicao",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResourceNotFoundException.class),
                                    examples = @ExampleObject(
                                            value = "{\"timestamp\":\"2024-01-15T20:08:06\",\"status\":500," +
                                                    "\"message\":\"Erro ao enviar informações\"}"
                                    )
                            )
                    )
            })
    ResponseEntity<CozinhaDto> update(
            @Parameter(
                    name = "id",
                    description = "ID da cozinha",
                    in = ParameterIn.PATH,
                    example = "1",
                    required = true,
                    schema = @Schema(type = "integer", format = "int64", example = "1")
            )
            Long id, CozinhaDto cozinhaDto);

    @Operation(summary = "Listar todas as cozinhas", description = "Lista quantidade de cozinhas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de cozinha retornada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CozinhaDto.class)),
                                    examples = @ExampleObject(
                                            value = """
                                                        [
                                                            {
                                                                "nome": "bosco alves"
                                                            }
                                                        ]
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "204", description = "Não há informações na base dados",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResourceNotFoundException.class),
                                    examples = @ExampleObject(
                                            value = "[]"
                                    )
                            )
                    )
            })
    ResponseEntity<List<CozinhaDto>> searchList(
            @Parameter(
                    name = "nome",
                    description = "nome da cozinha",
                    in = ParameterIn.QUERY,
                    example = "Doe",
                    required = true,
                    schema = @Schema(type = "string", format = "int64", example = "Doe")
            )
            String nome);

    @Operation(summary = "Excluir cozinha pelo ID", description = "exluir cozinha buscada",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cozinha excluída com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResourceNotFoundException.class),
                            examples = @ExampleObject(
                                    value = "{\"timestamp\":\"2024-01-15T20:08:06\",\"status\":400," +
                                            "\"error\":\"Erro na informação do campo\",\"message\": \"O campo NOME " +
                                            "não deve está vazio, O campo NOME deve conter apenas letras\"}"
                            )
                    )),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error - Erro interno no " +
                            "servidor ao tentar " +
                            "processar requisicao",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResourceNotFoundException.class),
                                    examples = @ExampleObject(
                                            value = "{\"timestamp\":\"2024-01-15T20:08:06\",\"status\":500," +
                                                    "\"message\":\"Erro ao enviar informações\"}"
                                    )
                            )
                    )
            })
    ResponseEntity<Cozinha> delete(
            @Parameter(
                    name = "id",
                    description = "ID da cozinha",
                    in = ParameterIn.PATH,
                    example = "12",
                    required = true,
                    schema = @Schema(type = "integer", format = "int64", example = "12")
            )
            Long id);
}
