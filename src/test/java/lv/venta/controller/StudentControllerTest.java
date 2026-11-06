package lv.venta.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import lv.venta.model.Student;
import lv.venta.service.ICRUDStudentService;

class StudentControllerTest {

	@Mock
	private ICRUDStudentService studService;
	
	@InjectMocks
	private CRUDStudentController studController;
	private MockMvc mockMVC; 
	
	@BeforeEach
	public void initialize() {
		MockitoAnnotations.openMocks(this); 
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean(); 
		 validator.afterPropertiesSet(); 
		 mockMVC = MockMvcBuilders.standaloneSetup(studController)
				 .setValidator(validator).build();
	}
	
	@Test
	public void testGetAllController() throws Exception {
		Student st1 = new Student("Rodrigo", "Zandbergs");
		Student st2 = new Student("Aivars", "Ķiris");
		Student st3 = new Student("Nils", "Kalniņš");
		
		ArrayList<Student> testStudents = new ArrayList<>();
		testStudents.addAll(Arrays.asList(st1, st2, st3));
		try {
		when(studService.retrieveAll()).thenReturn(testStudents); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			mockMVC.perform(get("/students/crud/all"))
			.andExpect(status().isOk());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
//		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//		.andExcpect(jsonPath("$[0].name").valueOf("Rodrigo"), null));
	}
	

}
