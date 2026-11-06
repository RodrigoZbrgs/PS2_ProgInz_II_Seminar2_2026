package lv.venta.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentTest {

	@Test
	void  testStudentConstructor() { 
		Student student = new Student("Rodrigo", "Zandbergs"); 
		assertEquals("Rodrigo", student.getName()); 
		assertEquals("Zandbergs", student.getSurname()); 
		} 
	
	@Test
	public void testToString() {
		Student student = new Student("Rodrigo", "Zandbergs");
		assertNotNull(student.toString());
	}
	
	@Test
	public void testDefaultConstructor() {
		Student student = new Student();
		student.setName("Janis");
		student.setSurname("Zīle");
		assertEquals("Jānis", student.getName());
		assertEquals("Zīle", student.getSurname());
	}

}
