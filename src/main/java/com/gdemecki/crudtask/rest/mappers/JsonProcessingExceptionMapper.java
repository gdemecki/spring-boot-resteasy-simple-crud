package com.gdemecki.crudtask.rest.mappers;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdemecki.crudtask.rest.ResponseEntity;
import org.springframework.stereotype.Component;


/**
 * Prevents leak of all Jackson's {@code JsonProcessingException} and its subclasses.
 *
 * @author Grzegorz Demecki
 * @since Sep 18, 2018
 */
@Provider
@Component
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {

	@Context
	private HttpHeaders headers;

	@Override
	public Response toResponse(JsonProcessingException exception) {
		final ResponseEntity resp = ResponseEntity.builder()
				.addError(exception.getOriginalMessage()).build();

		return Response.status(Status.BAD_REQUEST)
				.entity(resp)
				.type(headers.getMediaType())
				.build();
	}

}