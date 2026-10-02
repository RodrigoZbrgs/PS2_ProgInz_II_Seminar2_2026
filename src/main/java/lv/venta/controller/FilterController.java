package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.enums.Degree;
import lv.venta.service.IFilterService;

@Controller
@RequestMapping("/filter")
public class FilterController {
	
	@Autowired
	private IFilterService filterService;
	
	@GetMapping("/grade/student/{id}")//localhost:8080/filter/grade/student/1
	public String getControllerGradesByStudentId(@PathVariable(name = "id") long id,
			Model model) {
		
		try
		{
			model.addAttribute("package", filterService.filterGradesByStudentId(id));
			return "show-multiple-grades";
		}
		catch (Exception e) {
			//e.printStackTrace();
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/grade/course/{title}")//localhost:8080/filter/grade/course/Programmēšana JAVA
	public String getControllerGradesByCourseTitle(@PathVariable(name = "title")
	String title, Model model) {
		try
		{
		model.addAttribute("package", filterService.filterGradesByCourseTitle(title));
		return "show-multiple-grades";
		}
		catch (Exception e) {
			//e.printStackTrace();
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/course/professor/{degree}")//localhost:8080/filter/course/professor/master
	public String getControllerCourseByProfessorDegree(@PathVariable(name = "degree")
			Degree degree, Model model) {
		try
		{
		model.addAttribute("package", filterService.filterCoursesByProfessorDegree(degree));
			return "show-multiple-courses";
		}
		catch (Exception e) {
			//e.printStackTrace();
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/student/failed")//localhost:8080/filter/student/failed
	public String getControllerFailedStudents(Model model) {
		try
		{
			model.addAttribute("package", filterService.filterStudentsFailed());
			return "show-multiple-students";
		}
		catch (Exception e) {
			//e.printStackTrace();
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	
	@GetMapping("/grades/10") //localhost:8080/filter/grades/10
	public String getControllerGrades10(Model model) {
		try
		{
			model.addAttribute("package", 
				filterService.filterExcellentGrades());
			return "show-multiple-grades";
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/courses/creditpoints/{level}")//localhost:8080/filter/courses/creditpoints/5
	public String getControllerCoursesCpLessThan
	(@PathVariable("level")int level, Model model) {
		try
		{
		model.addAttribute("package", filterService.filterCourseByCrediPointsLessThan(level));
			return "show-multiple-courses";
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	

}
