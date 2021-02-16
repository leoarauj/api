package br.com.api.exception;

/**
 * Enum com os StatusHttp e possivéis respostas do servidor
 * 
 * @author Leonardo Araújo
 */
public enum RestMessageCode {

	CAMPOS_OBRIGATORIOS_NAO_INFORMADOS(400, "Campos obrigatórios não informados!"),
	NENHUM_RESULTADO_ENCONTRADO(404, "Nenhum resultado encontrado!"),
	DUPLICIDADE_CPF(409, "CPF já cadastrado"),
	NAO_PERMITIDO_ALTERACAO_PESSOA(409, "Não é permitido alteração nas informações de Pessoa"),
	DADOS_PF_NAO_ENCONTRADOS(404, "Dados cadastrais de Pessoa Física não encontrados!"),

	ERRO_RESULTADO_NAO_UNICO(500, "Erro interno no servidor: Mais de um resultado encontrado, favor notificar o administrador."),
	ERRO_COMUNICACAO_COM_CADASTRO(500, "Não foi possível estabelecer comunicação com o sistema de cadastro, favor notificar o administrador."),
	ERRO_INTERNO_SERVIDOR(500, "Erro interno no servidor, favor notificar o administrador.");

	private int status;
	private String message;

	private RestMessageCode(int status, String message) {
		this.status = status;
		this.message = message;
	}

	/**
	 * @return the codeStatus
	 */
	public int getCodeStatus() {
		return status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
