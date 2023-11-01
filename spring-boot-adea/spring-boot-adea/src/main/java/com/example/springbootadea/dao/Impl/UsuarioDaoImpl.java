package com.example.springbootadea.dao.Impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.util.MultiValueMap;

import com.example.springbootadea.dao.UsuarioDao;
import com.example.springbootadea.entities.Usuario;
import com.example.springbootadea.utils.FechaUtilsHelper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class UsuarioDaoImpl implements UsuarioDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Usuario> findByFilters(MultiValueMap<String, ?> params) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date dateInicial = null;
		Date dateFinal = null;
		LocalDate localDate = null;
		StringBuilder query = new StringBuilder("Select U from Usuario U where 1=1 ");
		TypedQuery<Usuario> hqlQuery = null;

		if(params.containsKey("fechaInicial")) {
			localDate =LocalDate.parse(params.getFirst("fechaInicial").toString());
			dateInicial= Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		}
		if(params.containsKey("fechaInicial")) {
			localDate =LocalDate.parse(params.getFirst("fechaFinal").toString());
			dateFinal = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		}
		
		if(params.containsKey("nombreSearch")) {
			query.append(" AND U.nombre like '"+params.getFirst("nombreSearch").toString()+"%'");
		}
		if(params.containsKey("estatus")) {
			query.append(" AND U.estatus like '"+params.getFirst("estatus").toString()+"'");
		}
		if(dateInicial != null && dateFinal != null) {
			query.append(" AND U.fechaAlta between '"+FechaUtilsHelper.df.format(dateInicial)
			+"' and '"+FechaUtilsHelper.df.format(dateFinal)+"'");
		}
	
		hqlQuery = entityManager.createQuery(query.toString(),Usuario.class);
				
		return hqlQuery.getResultList();
	}

}
