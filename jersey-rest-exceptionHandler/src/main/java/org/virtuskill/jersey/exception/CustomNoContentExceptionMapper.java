package org.virtuskill.jersey.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.virtuskill.jersey.domain.CommonErrorMessage;


public class CustomNoContentExceptionMapper implements ExceptionMapper<CustomNoContentException> {

	@Override
	public Response toResponse(CustomNoContentException exception) {
		CommonErrorMessage obj = new CommonErrorMessage(404, exception.getMessage(),
				"The requested resource is not available.");
		return Response.status(Status.NOT_FOUND).entity(obj).build();
	}

}
