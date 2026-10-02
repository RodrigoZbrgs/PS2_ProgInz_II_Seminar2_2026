package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.model.enums.Degree;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IProfessorRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.IFilterService;

@Service
public class FilterServiceImpl implements IFilterService {

	@Autowired
	private IStudentRepo studRepo;
	
	@Autowired
	private IGradeRepo gradeRepo;
	
	@Autowired
	private ICourseRepo courseRepo;
	
	@Autowired
	private IProfessorRepo profRepo;
	
	@Override
	public ArrayList<Grade> filterGradesByStudentId(long id) throws Exception {
		if(id < 1) {
			throw new Exception("Id nevar būt negatīvs");
		}
		if(!studRepo.existsById(id)) {
			throw new Exception("Students ar id " + id + " neeksistē");
		}
		if(gradeRepo.count() == 0) {
			throw new Exception("Atzīmju tabula ir tukša un nevaram filtrēt");
		}
		ArrayList<Grade> resultFromDB = gradeRepo.findByStudentIds(id);
		
		if(resultFromDB.isEmpty()) {
			throw new Exception("Studentam ar id " + id + " nav piesaistīta neviena atzīme");
		}
		
		return resultFromDB;
	}

	@Override
	public ArrayList<Grade> filterGradesByCourseTitle(String title) throws Exception {
		if(gradeRepo.count() == 0) {
			throw new Exception("Atzīmju tabula ir tukša un nav iespējams filtrēt atzīmes");
		}
		
		if(title == null || title.isEmpty() || !title.matches("[A-Ž]{1}[A-Ža-ž0-9 ]{3,40}")) {
			throw new Exception("Kursa nosaukums nav ievadīts korekti");
		}
		
		if(!courseRepo.existsByTitle(title)) {
			throw new Exception("Kurss ar nosaukumu " + title + " neeksistē");
		}
		ArrayList<Grade> results = gradeRepo.findByCourseTitle(title);
		if(results.isEmpty()) {
			throw new Exception("Nav neviena atzīme, kura ir piesaistīta kursam " + title);
		}

		return results;
	}

	@Override
	public ArrayList<Course> filterCoursesByProfessorDegree(Degree degree) throws Exception {
		if(courseRepo.count() == 0) {
			throw new Exception("Kursa tabula ir tukša un nevar filtrēt kursus");
		}
		
		if(degree == null) {
			throw new Exception("Profesora grāds nav ievadīts korekti");
		}
		
		if(!profRepo.existsByDegree(degree)) {
			throw new Exception("Profesori ar " + degree + " grādu neeksistē");
		}
		
		ArrayList<Course> results = courseRepo.findByProfessorDegree(degree);
		if(results.isEmpty()) {
			throw new Exception("Nav neviens kurss, "
					+ "kurš butu piesaistīt profesoram ar grādu " + degree);
		}
		
		return results;
	}

	@Override
	public ArrayList<Student> filterStudentsFailed() throws Exception {
		// TODO 
		//ja atzimju tabula ir tukšā
		if(gradeRepo.count() == 0) {
			throw new Exception("Atzīmju tabula ir tukša un nevar filtrēt");
		}
		//ja studentu tabula ir tukša
		if(studRepo.count() == 0) {
			throw new Exception("Studentu tabula ir tukša un nevar atrast studentus, kuri ir nesekmīgi");
		}
		//izveidot results un atlasīt visus studentus,
		//kam ir atzīmes mazakas par 4
		
		ArrayList<Student> results = studRepo.findByGradesGrvalueLessThan(4);
		
		//ja nav stuentu, kuri ir nesekmīgi
		if(results.isEmpty()) {
			throw new Exception("Nav neviens students, kuram butu nesekmīga atzīme");
		}
		//atgriežam results
		return results;
	}

	@Override
	public ArrayList<Grade> filterExcellentGrades() throws Exception {
		if(gradeRepo.count()==0) {
			throw new Exception("DB nav neviena atzīme");
		}
		
		ArrayList<Grade> results = gradeRepo.findByGrvalue(10);
		
		if(results.isEmpty()) {
			throw new Exception("Nav neviena atzīme 10");
		}
		return results;
	}

	@Override
	public ArrayList<Course> filterCourseByCrediPointsLessThan
	(int level) throws Exception {
		if(courseRepo.count() == 0) {
			throw new Exception("DB nav neviens kurss");
		}
		
		if(level <= 0 || level >=51) {
			throw new Exception("Kredītpunktu skaits var būt robežās no 1 līdz 50");
		}
		
		ArrayList<Course> results = 
				courseRepo.findByCreditpointsLessThan(level);
		if(results.isEmpty()) {
			throw new Exception(
					"Nav neviens kurss, kura KP ir mazāki par " + level);
		}
		return results;
	}

}
