package com.gdemecki.crudtask.rest.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.gdemecki.crudtask.rest.ResponseEntity;

@Provider
@Component
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {

    @Override
    public Response toResponse(JsonParseException exception) {
        final ResponseEntity resp = ResponseEntity.builder()
                .addError(exception.getOriginalMessage()).build();

        return Response.status(Status.BAD_REQUEST).entity(resp).build();
    }
}
