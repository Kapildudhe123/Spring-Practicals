package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dto.UserDTO;

@Repository
public class UserDaoImple implements UserDaoInt {

	@PersistenceContext
	public EntityManager entitymanager;

	public long add(UserDTO dto) {
		entitymanager.persist(dto);
		return dto.getId();
	}

	public void update(UserDTO dto) {
		entitymanager.merge(dto);
	}

	public void delete(UserDTO dto) {
		entitymanager.remove(dto);

	}

	@Override
	public UserDTO findbyPk(long pk) {
		UserDTO dto = entitymanager.find(UserDTO.class, pk);
		return dto;
	}

	public List search(UserDTO dto, int pageNo, int pageSize) {

		CriteriaBuilder builder = entitymanager.getCriteriaBuilder();

		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);

		Root<UserDTO> qRoot = cq.from(UserDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (dto != null) {

			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));

			}
			if (dto.getId() != null && dto.getId() > 0) {
				predicateList.add(builder.equal(qRoot.get("id"), dto.getId()));
			}

		}

		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<UserDTO> tq = entitymanager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

		List<UserDTO> list = tq.getResultList(); 

		return list;
	}

	@Override
	public UserDTO findByUniqueKey(String attribute, Object val) {
		CriteriaBuilder builder = entitymanager.getCriteriaBuilder();

		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);

		Root<UserDTO> qRoot = cq.from(UserDTO.class);

		cq.where(builder.equal(qRoot.get(attribute), val));

		TypedQuery<UserDTO> tquery = entitymanager.createQuery(cq);

		List<UserDTO> list = tquery.getResultList();
		UserDTO dto = null;

		if (list.size() > 0) {
			dto = list.get(0);
		}

		return dto;
	}

}
