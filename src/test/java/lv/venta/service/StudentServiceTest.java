package lv.venta.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import lv.venta.model.Student;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.impl.CRUDStudentServiceImpl;

class StudentServiceTest {

	@Mock
	private IStudentRepo studRepo;
	
	@InjectMocks
	private CRUDStudentServiceImpl studService;
	
	@BeforeEach
	public void initialize() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testRetrieveAll() {
		Student st1 = new Student("Rodrigo", "Zandbergs");
		Student st2 = new Student("Aivars", "Ķiris");
		Student st3 = new Student("Nils", "Kalniņš");
		
		ArrayList<Student> testStudents = new ArrayList<>();
		testStudents.addAll(Arrays.asList(st1, st2, st3));
		
		when(studRepo.count()).thenReturn(2l);
		when(studRepo.findAll()).thenReturn(testStudents);
		
		ArrayList<Student> result;
		try {
			result = studService.retrieveAll();
			assertEquals(3, result.size()); 
			assertEquals("Rodrigo", result.get(0).getName()); 
			assertEquals("Aivars", result.get(1).getName());
			assertEquals("Nils", result.get(2).getName());
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(testStudents, studService.retrieveAll());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		assertDoesNotThrow(() -> studService.retrieveAll());
	}
	
	@Test
	public void testRetrieveAllThrowException() {
		
		when(studRepo.count()).thenReturn(0l);
		
		assertThrows(Exception.class, () -> studService.retrieveAll());
	}
	
}
