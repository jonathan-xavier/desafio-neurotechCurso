package com.jhon.desafio.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//captura execoes de resposta de entidades. alga works aula 3.9	

//essa classe ja vem com vários metodos uteis.
//controllerAdvice porque ele observa toda a aplicação.
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired // esse messageSource pega os itens de messages.properties no resources.
	private MessageSource messageSource;

	// mensagem não consegue ser lida
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mensagemUsuario = messageSource.getMessage("default.title", null, LocaleContextHolder.getLocale());
		String mensagemDev = ex.getCause().toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDev));

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST,
				request);
	}

	//ctrl + shift + f identar.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	//result são os campos que deram erro que foram validados na entidade.
	private List<Erro> criarListaDeErros(BindingResult result){
		List<Erro> erros = new ArrayList<>();
		
		for(FieldError fieldError : result.getFieldErrors() ) {
			
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDev = fieldError.toString();
			
			erros.add(new Erro(mensagemUsuario, mensagemDev));
			
		}
		return erros;
	}
	
	

	public static class Erro {
		private String mensagemUsuario;
		private String mensagemDev;

		public Erro(String mensagemUsuario, String mensagemDev) {
			super();
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDev = mensagemDev;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}

		public String getMensagemDev() {
			return mensagemDev;
		}

		public void setMensagemDev(String mensagemDev) {
			this.mensagemDev = mensagemDev;
		}

	}

}
