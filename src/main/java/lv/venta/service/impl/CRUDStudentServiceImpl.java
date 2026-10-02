package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.ICRUDStudentService;

@Service
public class CRUDStudentServiceImpl implements ICRUDStudentService{

	@Autowired
	private IStudentRepo studRepo;
	
	@Autowired
	private IGradeRepo gradeRepo;
	
	//TODO create un update pabeigt mājās, pēc līdzīga piemera, kā product (Sem1) create un update 
	
	@Override
	public void create(Student newObject) throws Exception {
		if(newObject == null) {
			throw new Exception("Students nevar būt tukšs");
		}
		//TODO pārbaudīt newObject name, surname pēc regex, vai nav tukšs
		if(studRepo.existsByNameAndSurname(
				newObject.getName(), newObject.getSurname() )) {
			throw new Exception("Tāds students jau eksistē!");
		}
		
		studRepo.save(newObject);
		
	}

	@Override
	public ArrayList<Student> retrieveAll() throws Exception {
		if(studRepo.count() == 0) {
			throw new Exception("Studentu tabula ir tukša");
		}
		return (ArrayList<Student>) studRepo.findAll();
	}

	@Override
	public Student retrieveById(long id) throws Exception {
		if(studRepo.count() == 0) {
			throw new Exception("Studentu tabula ir tukša un nevar atgreizts nevienu studentu");
		}
		
		if(id < 1) {
			throw new Exception("Id nevar būt negatīvs vai 0");
		}
		
		if(!studRepo.existsById(id)) {
			throw new Exception("Students ar id " + id + " neeksistē");
		}
		
		
		return studRepo.findById(id).get();
	}

	@Override
	public void deleteById(long id) throws Exception {
		Student studentForDeletion = retrieveById(id);
		
		ArrayList<Grade> allGradesForStudent = gradeRepo.findByStudentIds(id);
		
		for(Grade tempG : allGradesForStudent) {
			tempG.setStudent(null);//noņemt saiti uz studenta id grade tabulā
			gradeRepo.save(tempG);
			
		}
				
		studRepo.delete(studentForDeletion);
		
	}

	@Override
	public void updateById(long id, String name, String surname) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
