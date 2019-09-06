package br.com.api.controller;

import java.net.URI;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.dto.PessoaDTO;
import br.com.api.mapper.PessoaMapper;
import br.com.api.model.Pessoa;
import br.com.api.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Classe de controle dos Endpoints de {@link Pessoa}
 * 
 * @author Leonardo Araújo
 */
@RestController
@RequestMapping(value = "${api.version}/pessoas")
@Api(value = "API REST - Pessoa")
@CrossOrigin(origins = "*")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PessoaMapper pessoaMapper;

	/**
	 * Realiza a conversão de DTO para Entity e executa o serviço para persistir uma
	 * nova {@link Pessoa}
	 * 
	 * @param pessoaDTO
	 * @return
	 */
	@ApiOperation(value = "Salva Pessoa")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Success", response = PessoaDTO.class),
//		@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
//		@ApiResponse(code = 404, message = "Nof Found", response = MessageResponse.class)
	})
	@RequestMapping(produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody final PessoaDTO pessoaDTO) {
		pessoaDTO.setId(null);
		pessoaDTO.setDataInicio(LocalDateTime.now());

		Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);
		pessoaService.salvar(pessoa);

		URI location = getURI(pessoaDTO);

		return ResponseEntity.created(location).build();
	}

	/**
	 * Retorna a {@link PessoaDTO} segundo o ID informado
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Consulta Pessoa por ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Success", response = PessoaDTO.class),
//		@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
//		@ApiResponse(code = 404, message = "Nof Found", response = MessageResponse.class)
	})
	@RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") final Long id) {
		Pessoa pessoa = pessoaService.getById(id);
		PessoaDTO pessoaDTO = pessoaMapper.toDTO(pessoa);
		return ResponseEntity.ok(pessoaDTO);
	}

	/**
	 * Realiza a conversão de DTO para Entity e executa o serviço para alterar uma
	 * {@link Pessoa}
	 * 
	 * @param pessoaDTO
	 * @return
	 */
	@ApiOperation(value = "Altera pessoa segundo dados informados")
	@ApiResponses({ 
		@ApiResponse(code = 202, message = "Success", response = PessoaDTO.class),
//		@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
//		@ApiResponse(code = 404, message = "Nof Found", response = MessageResponse.class)
	})
	@RequestMapping(produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<?> alterar(@RequestBody final PessoaDTO pessoaDTO) {
		Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);
		pessoaService.salvar(pessoa);

		return ResponseEntity.accepted().build();
	}

	/**
	 * Gera a URI de retorno
	 * 
	 * @param pessoaDTO
	 * @return
	 */
	private URI getURI(final PessoaDTO pessoaDTO) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoaDTO.getId()).toUri();
	}
}
