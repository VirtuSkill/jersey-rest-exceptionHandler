package org.virtuskill.jersey.services;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.virtuskill.jersey.domain.Student;
import org.virtuskill.jersey.repository.StudentDAO;

@Path("/student")
public class StudentService {

	StudentDAO studentDAO = new StudentDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudentDetails() {
		GenericEntity<List<Student>> entityBuilder = new GenericEntity<List<Student>>(
				studentDAO.getAllStudentDetails()) {
		};
		return Response.ok(entityBuilder).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam(value = "id") long id) {
		return studentDAO.getStudent(id);
	}

	@GET
	@Path("/{id}/getName/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam(value = "id") long id, @PathParam(value = "name") String name) {
		return studentDAO.getStudent(id, name);
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudent(Student stdReqObj, @Context UriInfo uriInfo) {
		Student studentObj = studentDAO.addStudent(stdReqObj);
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(studentObj.getId())).build();
		return Response.created(uri).entity(studentObj).build();
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStudent(Student stdReqObj) {
		return Response.status(Status.OK).entity(studentDAO.updateStudent(stdReqObj)).build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response deleteStudent(@PathParam(value = "id") long id) {
		return Response.status(204).entity(studentDAO.deleteStudent(id)).build();
	}
}
