package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.model.enums.Degree;

public interface IFilterService {

	public abstract ArrayList<Grade> filterGradesByStudentId(long id)
		throws Exception;
	
	public abstract ArrayList<Grade> filterGradesByCourseTitle(String title)
		throws Exception;
	
	public abstract ArrayList<Course> filterCoursesByProfessorDegree(Degree degree)
		throws Exception;
	
	public abstract ArrayList<Student> filterStudentsFailed() 
			throws Exception;
	
	public abstract ArrayList<Grade> filterExcellentGrades()
			throws Exception;
	
	public abstract ArrayList<Course> 
	filterCourseByCrediPointsLessThan(int level) throws Exception;
	
}

