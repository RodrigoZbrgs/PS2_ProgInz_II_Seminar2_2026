package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.model.dto.GradeDTO;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.IGradeService;

public class GradeServiceImpl implements IGradeService {

	@Autowired
	private IStudentRepo studRepo;
	
	@Autowired
	private ICourseRepo courseRepo;
	
	@Autowired
	private IGradeRepo gradeRepo;

	
	@Override
	public void addNewGrade(GradeDTO gradeDTO) throws Exception {
		if(gradeDTO == null) {
			throw new Exception("Dati nav pareizi ievadīti");
		}
		
		if(gradeDTO.getGradeValue() < 1 || gradeDTO.getGradeValue() > 10) {
			throw new Exception("Atzīmes vērtībai jābūt no 1 līdz 10");
		}
		
		if(!studRepo.existsByNameAndSurname
				(gradeDTO.getStudName(), gradeDTO.getStudSurname())) {
			throw new Exception("Tāds students neeksistē!");
		}
		
		if(!courseRepo.existsByTitle(gradeDTO.getCourseTitle())) {
			throw new Exception("Tāds kurss neeksistē!");
		}
		

		
		Student stud = studRepo.findByNameandSurname(gradeDTO.getStudName(), gradeDTO.getStudSurname());
		
		Course course = courseRepo.findByTitle(gradeDTO.getCourseTitle());
		
		if(!gradeRepo.existsByGradevalueAndStudentAndCourse(gradeDTO.getGradeValue(), stud, course)) {
			throw new Exception("Šis jau eksistē");
		}
		
		Grade grade = new Grade(gradeDTO.getGradeValue(), stud, course);
		gradeRepo.save(grade);
	}

	@Override
	public void addNewGrades(ArrayList<GradeDTO> gradesDTO) throws Exception {
		
		
	}

}
