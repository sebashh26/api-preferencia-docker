package taller.ninja.demodocker.service.impl;

import java.lang.reflect.Method;
import java.util.List;

import taller.ninja.demodocker.repo.IGenericRepo;

public abstract class CRUDImpl <T, ID>{
	
	protected abstract IGenericRepo<T, ID> getRepo();

    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    public T update(T t, ID id) throws Exception {
        //falta trabajar la asociacion con id
    	Class<?> clazz =  t.getClass();
    	String className =clazz.getSimpleName();
    	String methodName= "setId"+className;
    	Method setIdMethod = clazz.getMethod(methodName, id.getClass());
    	setIdMethod.invoke(t, id);
    	
    	getRepo().findById(id).orElseThrow(() -> new Exception("ID NOT FOUND: " + id));
    	
    	
    	
        return getRepo().save(t);
    }

    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    public T readById(ID id) throws Exception {
        //orElse temporal
        return getRepo().findById(id).orElseThrow(null);// ()-> obj no recibe nada y retorna lo que sea es un supplier, consumer recieb lo q sea y retorna un void
    }

    public void delete(ID id) throws Exception {
    	getRepo().findById(id).orElseThrow(null);
        getRepo().deleteById(id);
    }
}
