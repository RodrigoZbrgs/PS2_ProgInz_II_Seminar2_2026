package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

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
		
		
		
		
	}

	@Override
	public void addNewGrades(ArrayList<GradeDTO> gradesDTO) throws Exception {
		
		
	}

}
