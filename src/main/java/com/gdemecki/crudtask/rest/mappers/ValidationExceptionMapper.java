package com.gdemecki.crudtask.rest.mappers;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.gdemecki.crudtask.rest.ResponseEntity;

@Provider
@Component
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        final ResponseEntity resp = ResponseEntity.builder()
                .addError(exception.getMessage()).build();

        return Response.status(Status.BAD_REQUEST).entity(resp).build();
    }
}
