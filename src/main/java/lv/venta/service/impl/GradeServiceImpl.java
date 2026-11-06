package lv.venta.service.impl;

import java.util.ArrayList;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.model.dto.GradeDTO;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.IGradeService;

@Service
public class GradeServiceImpl implements IGradeService {

	@Autowired
	private IStudentRepo studRepo;

	@Autowired
	private ICourseRepo courseRepo;

	@Autowired
	private IGradeRepo gradeRepo;

	@Override
	public void addNewGrade(GradeDTO gradeDTO) throws Exception {
		if (gradeDTO == null) {
			throw new Exception("Dati nav pareizi ievadīti");
		}
		System.out.println(gradeDTO);
		if (gradeDTO.getGradeValue() < 1 || gradeDTO.getGradeValue() > 10) {
			throw new Exception("Atzīmes vērtībai jābūt no 1 līdz 10");
		}

		if (!studRepo.existsByNameAndSurname(gradeDTO.getStudName(), gradeDTO.getStudSurname())) {
			throw new Exception("Tāds students neeksistē!");
		}

		if (!courseRepo.existsByTitle(gradeDTO.getCourseTitle())) {
			throw new Exception("Tāds kurss neeksistē!");
		}

		Student stud = studRepo.findByNameAndSurname(gradeDTO.getStudName(), gradeDTO.getStudSurname());

		Course course = courseRepo.findByTitle(gradeDTO.getCourseTitle());

//		if (!gradeRepo.existsByGrvalueAndStudentAndCourse(gradeDTO.getGradeValue(), stud, course)) {
//			throw new Exception("Šis jau eksistē");
//		}

		Grade grade = new Grade(gradeDTO.getGradeValue(), stud, course);
		gradeRepo.save(grade);
	}

	@Override
	public void addNewGrades(ArrayList<GradeDTO> gradesDTO) throws Exception {
		if (gradesDTO == null) {
			throw new Exception("Dati nav pareizi ievadīti");
		}
		if (gradesDTO.isEmpty()) {
			throw new Exception("Nav datu, ko saglabāt!");
		}
		for (GradeDTO tempG : gradesDTO) {
			addNewGrade(tempG);
		}
	}

	@Override
	public ArrayList<GradeDTO> retrieveAll() throws Exception {
		if (gradeRepo.count() == 0) {
			throw new Exception("Atzimju tabula ir tukša");
		}
		ArrayList<GradeDTO> result = new ArrayList<>();

		for (Grade tempG : gradeRepo.findAll()) {
			result.add(new GradeDTO(tempG.getStudent().getName(), 
									tempG.getStudent().getSurname(),
									tempG.getCourse().getTitle(), 
									tempG.getGrvalue()));
			
		}
		return result;
	}

}
