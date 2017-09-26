package br.edu.univas.si.services;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.univas.si.model.dao.UserDAO;
import br.edu.univas.si.model.entities.Course;
import br.edu.univas.si.model.entities.UserInfo;

//Acessar via http://localhost:8080/Voting/rest/saveVotes/30/roberto@gmail.com/sistemas/adm/gpi/engProd

@Path("/vote")
public class VotingServiceREST {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/saveVotes")
	public Response saveVotes(@QueryParam("email") String email,
			@QueryParam("age") Integer age,
			@QueryParam("permission") Boolean permission,
			@QueryParam("list") final List<String> cources) {
		
		System.out.println("Email: " + email + " Permission: " + permission);
		
		br.edu.univas.si.services.Response resp = new br.edu.univas.si.services.Response();
		UserDAO dao = new UserDAO();
		try {
			dao.init();
			if(dao.retrieveUser(email) != null) {
				resp.setMessage("A pesquisa já foi respondida com seu email.");
				return Response.ok(resp, MediaType.APPLICATION_JSON).build();
			}
			
			UserInfo user = new UserInfo();
			user.setEmail(email);
			user.setAge(age);
			user.setPermission(permission);
			user.setRegisterDate(new Date());
			
			dao.saveUser(user);

			for (String courseName : cources) {
				Course course = new Course();
				course.setCourseName(courseName);
				course.setUser(user);
				user.addCourse(course);
				dao.saveCourse(course);
			}
			
			resp.setMessage("Dados salvos com sucesso!");
			return Response.ok(resp, MediaType.APPLICATION_JSON).build();
		} catch(Exception e) {
			e.printStackTrace();
			resp.setMessage("Erro ao salvar os dados. Procure o stand do Sistema.");
			return Response.status(Response.Status.NOT_FOUND).entity(resp).build();
		} finally {
			dao.close();
		}
	}

}