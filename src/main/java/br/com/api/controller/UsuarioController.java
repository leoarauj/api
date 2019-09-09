package br.com.api.controller;

import java.net.URI;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.dto.UsuarioDTO;
import br.com.api.enuns.StatusAtivoInativo;
import br.com.api.exception.RestResponseMessageException;
import br.com.api.mapper.UsuarioMapper;
import br.com.api.model.Usuario;
import br.com.api.response.RestResponse;
import br.com.api.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Classe de controle dos Endpoints de {@link Usuario}
 * 
 * @author Leonardo Araújo
 */
@RestController
@RequestMapping(value = "${api.version}/usuarios")
@Api(value = "API REST - Usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	public UsuarioService usuarioService;

	@Autowired
	public UsuarioMapper usuarioMapper;

	/**
	 * Realiza a conversão de DTO para Entity e executa o serviço para persistir uma
	 * nova {@link Usuario}
	 * 
	 * @param usuarioDTO
	 * @return
	 */
	@ApiOperation(value = "Salva Usuario")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Success", response = UsuarioDTO.class),
		@ApiResponse(code = 400, message = "Bad Request", response = RestResponse.class),
		@ApiResponse(code = 404, message = "Nof Found", response = RestResponse.class)
	})
	@PreAuthorize("hasAuthority('ROLE_ADM')")
	@RequestMapping(produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody final UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(null);
		usuarioDTO.setDataInicio(LocalDateTime.now());

		Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
		usuarioService.salvar(usuario);

		URI location = getURI(usuarioDTO);

		return ResponseEntity.created(location).build();
	}
	
	/**
	 * Retorna a {@link UsuarioDTO} segundo o ID informado
	 * 
	 * @param id
	 * @return
	 * @throws RestResponseMessageException 
	 */
	@ApiOperation(value = "Consulta Usuario por ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class),
		@ApiResponse(code = 400, message = "Bad Request", response = RestResponse.class),
		@ApiResponse(code = 404, message = "Nof Found", response = RestResponse.class)
	})
	@RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") final Long id, @AuthenticationPrincipal UserDetails userDetails) {
		Usuario usuario = usuarioService.buscarPorId(id);
		UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario);

		return ResponseEntity.ok(usuarioDTO);
	}

	/**
	 * Realiza a conversão de DTO para Entity e executa o serviço para alterar uma
	 * {@link Usuario}
	 * 
	 * @param usuarioDTO
	 * @return
	 */
	@ApiOperation(value = "Altera usuário segundo dados informados")
	@ApiResponses({ 
		@ApiResponse(code = 202, message = "Success", response = UsuarioDTO.class),
		@ApiResponse(code = 400, message = "Bad Request", response = RestResponse.class),
		@ApiResponse(code = 404, message = "Nof Found", response = RestResponse.class)
	})
	@PreAuthorize("hasAuthority('ROLE_ADM')")
	@RequestMapping(produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<?> alterar(@RequestBody final UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
		usuarioService.salvar(usuario);

		return ResponseEntity.accepted().build();
	}
	
	/**
	 * Altera o {@link StatusAtivoInativo} do {@link Usuario} para <b>INATIVO</b>
	 * 
	 * @param id
	 * @return
	 * @throws RestResponseMessageException 
	 */
	@ApiOperation(value = "Altera os Status do Usuário para Inativo segundo ID do Usuário")
	@ApiResponses({ 
		@ApiResponse(code = 204, message = "No Content", response = UsuarioDTO.class),
		@ApiResponse(code = 400, message = "Bad Request", response = RestResponse.class),
		@ApiResponse(code = 404, message = "Nof Found", response = RestResponse.class)
	})
	@PreAuthorize("hasAuthority('ROLE_ADM')")
	@RequestMapping(value = "/inativar/{id:[\\d]+}", method = RequestMethod.PATCH)
	public ResponseEntity<?> inativar(@PathVariable("id") final Long id) {
		usuarioService.inativarUsuario(id);

		return ResponseEntity.noContent().build();
	}

	/**
	 * Altera o {@link StatusAtivoInativo} do {@link Usuario} para <b>ATIVO</b>
	 * 
	 * @param id
	 * @return
	 * @throws RestResponseMessageException 
	 */
	@ApiOperation(value = "Altera os Status do Usuário para Ativo segundo ID do Usuário")
	@ApiResponses({ 
		@ApiResponse(code = 204, message = "No Content", response = UsuarioDTO.class),
		@ApiResponse(code = 400, message = "Bad Request", response = RestResponse.class),
		@ApiResponse(code = 404, message = "Nof Found", response = RestResponse.class)
	})
	@PreAuthorize("hasAuthority('ROLE_ADM')")
	@RequestMapping(value = "/ativar/{id:[\\d]+}", method = RequestMethod.PATCH)
	public ResponseEntity<?> ativar(@PathVariable("id") final Long id) {
		usuarioService.ativarUsuario(id);

		return ResponseEntity.noContent().build();
	}

	/**
	 * Gera a URI de retorno
	 * 
	 * @param usuarioDTO
	 * @return
	 */
	private URI getURI(final UsuarioDTO usuarioDTO) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId()).toUri();
	}
}
