package br.edu.univas.si.services;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.edu.univas.si.model.dao.UserDAO;
import br.edu.univas.si.model.entities.Course;
import br.edu.univas.si.model.entities.UserInfo;

//Acessar via http://localhost:8080/Voting/rest/saveVotes/30/roberto@gmail.com/sistemas/adm/gpi/engProd

@Path("/vote")
public class VotingServiceREST {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/saveVotes")
	public String saveVotes(@QueryParam("email") String email,
			@QueryParam("age") Integer age,
			@QueryParam("list") final List<String> cources) {
		
		try {
			UserDAO dao = new UserDAO();
			
			if(dao.retrieveUser(email) != null) {
				return "A pesquisa já foi respondida com seu email.";
			}
			
			UserInfo user = new UserInfo();
			user.setEmail(email);
			user.setAge(age);
			user.setRegisterDate(new Date());
			
			dao.saveUser(user);

			for (String courseName : cources) {
				Course course = new Course();
				course.setCourseName(courseName);
				course.setUser(user);
				user.addCourse(course);
				dao.saveCourse(course);
			}
			
			return "Dados salvos com sucesso";
		} catch(Exception e) {
			e.printStackTrace();
			return "Erro ao salvar os dados. Procure a tenda do Sistema.";
		}
	}

}