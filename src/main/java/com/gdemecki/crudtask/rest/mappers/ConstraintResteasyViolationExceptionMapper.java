package com.gdemecki.crudtask.rest.mappers;

import com.gdemecki.crudtask.rest.ResponseEntity;
import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintDeclarationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle gracefully {@code javax.validation} API exceptions within RESTEasy in a
 * uniform manner.
 *
 * @author Grzegorz Demecki
 */
@Provider
@Component
public class ConstraintResteasyViolationExceptionMapper implements
		ExceptionMapper<ResteasyViolationException> {

	@Override
	public Response toResponse(ResteasyViolationException exception) {
		final List<String> violations = new ArrayList<String>();

		// Check for unexpected exceptions
		if (exception.getException() != null) {
			ResponseEntity entity = ResponseEntity.builder()
					.addError(exception.getException().getMessage())
					.build();
			return Response.status(500).entity(entity).build();
		}

		for (ResteasyConstraintViolation violation : exception.getViolations()) {
			String path = violation.getPath();
			int beginIndex = path.lastIndexOf(".");
			path = (beginIndex == -1) ? path : path.substring(beginIndex + 1);

			violations.add(path + " " + violation.getMessage());
		}

		ResponseEntity resp = ResponseEntity.builder().addErrors(violations).build();
		return Response.status(422).entity(resp).build();
	}
}