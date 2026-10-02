package lv.venta.service;

import java.util.ArrayList;

public interface ICRUDBaseService<Ttype> {
	//create, retrieve all, retrieve by id un delete by id tiks definēts šeit, jo tiem var izmantot Ttype

	public abstract void create(Ttype newObject) throws Exception;  
	
	public abstract ArrayList<Ttype> retrieveAll() throws Exception;
	
	public abstract Ttype retrieveById(long id) throws Exception;
	
	public abstract void deleteById(long id) throws Exception;
}
