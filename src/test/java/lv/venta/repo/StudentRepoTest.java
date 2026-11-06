package lv.venta.repo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import lv.venta.model.Student;

@DataJpaTest
class StudentRepoTest {

	@Autowired
	private IStudentRepo studentRepo;
	
	@Test
	public void saveNewStudent() {
		Student student = new Student("Rodrigo", "Zandbergs");
		Student studFromDB = studentRepo.save(student);
		assertEquals(student.getName(), studFromDB.getName());
		assertEquals(student.getSurname(), studFromDB.getSurname());
		assertTrue(studentRepo.existsByNameAndSurname("Rodrigo", "Zandbergs"));
		assertFalse(studentRepo.existsByNameAndSurname("adad", "dadad"));
	}

}
