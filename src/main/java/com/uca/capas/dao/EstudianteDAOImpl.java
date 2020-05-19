package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Estudiante;

@Repository
public class EstudianteDAOImpl implements EstudianteDAO{

	@PersistenceContext(unitName = "capas2")
	private EntityManager entityManager;
	
	@Override
	public List<Estudiante> findeAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.estudiante");
		Query query = entityManager.createNativeQuery(sb.toString(), Estudiante.class);
		List<Estudiante> resulset = query.getResultList();
		return resulset;
	}

	@Override
	@Transactional
	public void insert(Estudiante estudiante) throws DataAccessException {
		entityManager.persist(estudiante);		
	}

	@Override
	@Transactional
	public void delete(Integer codigo) throws DataAccessException {
		Estudiante estudiante = entityManager.find(Estudiante.class, codigo);
		entityManager.remove(estudiante);
	}

}
