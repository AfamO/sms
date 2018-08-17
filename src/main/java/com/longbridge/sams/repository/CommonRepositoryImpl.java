package com.longbridge.sams.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.longbridge.sams.model.Code;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.longbridge.sams.model.SearchableEntity;




public  class CommonRepositoryImpl<T> extends SimpleJpaRepository<T, Serializable>
		implements CommonRepository<T, Serializable> {

	private final EntityManager entityManager;
	private JpaEntityInformation<T, ?> info;

	// private Class<T> type;

	public CommonRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		info = entityInformation;
//		Filter filter = entityManager.unwrap(Session.class).enableFilter("sid");
		//filter.setParameter(name, value);
	}

	@Override
	public Page<T> findUsingPattern(String pattern, Pageable details) {
		String lpattern = pattern.toLowerCase();
		CriteriaBuilder critBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> primaryQuery = (CriteriaQuery<T>) critBuilder.createQuery();
		Root<T> rootType = primaryQuery.from(info.getJavaType());
		Predicate[] predicates = null;
		try {
			predicates = new Predicate[getFields().size()];
			int cnt = 0;
			for (String field : getFields()) {
				Predicate predicate = critBuilder.like(critBuilder.lower(rootType.get(field)), "%" + lpattern + "%");
				predicates[cnt] = predicate;
				cnt++;
			}
		} catch (InstantiationException | IllegalAccessException e) {
			return new PageImpl<>(new ArrayList<>());
		}

		CriteriaQuery<T> baseQuery = null;
		CriteriaQuery<Long> countQueryBuilder = critBuilder.createQuery(Long.class);
		CriteriaQuery<Long> countQuery = null;
		if(predicates.length > 0){
			Predicate or = critBuilder.or(predicates);
			 baseQuery = primaryQuery.select(rootType).where(or);
			 countQuery = countQueryBuilder.select(critBuilder.count(countQueryBuilder.from(info.getJavaType()))).where(or);
		}else{
			baseQuery = primaryQuery.select(rootType);
			countQuery = countQueryBuilder.select(critBuilder.count(countQueryBuilder.from(info.getJavaType())));
		}
		
		TypedQuery<T> query = entityManager.createQuery(baseQuery);

		
		Long count = entityManager.createQuery(countQuery).getSingleResult();

		query.setFirstResult(new Long(details.getOffset()).intValue());
		query.setMaxResults(details.getPageSize());
		List<T> list = query.getResultList();

		return new PageImpl<T>(list, details, count);
	}

	private List<String> getFields() throws InstantiationException, IllegalAccessException {
		Class<T> type = info.getJavaType();
		SearchableEntity en = (SearchableEntity) type.newInstance();
		return en.getDefaultSearchFields();

	}


}
