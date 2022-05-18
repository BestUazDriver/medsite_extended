package com.sabitov.repositories;

import com.sabitov.models.Account;
import com.sabitov.models.Cure;
import com.sabitov.models.Ill;
import lombok.RequiredArgsConstructor;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class IllCriteriaRepositoryImpl implements IllCriteriaRepository {

    private final EntityManager entityManager;

    @Override
    public List<Ill> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ill> illQuery = criteriaBuilder.createQuery(Ill.class);
        Root<Ill> ill = illQuery.from(Ill.class);

        TypedQuery<Ill> illTypedQuery = entityManager.createQuery(illQuery);
        return illTypedQuery.getResultList();
    }

    @Override
    public Ill findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ill> illQuery = criteriaBuilder.createQuery(Ill.class);
        Root<Ill> ill = illQuery.from(Ill.class);

        Predicate illPredicate = criteriaBuilder.like(ill.get("name"), name);
        illQuery.where(illPredicate);
        TypedQuery<Ill> illTypedQuery = entityManager.createQuery(illQuery);
        return illTypedQuery.getSingleResult();
    }

    @Override
    public List<Ill> findAllByNameExists(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ill> illQuery = criteriaBuilder.createQuery(Ill.class);
        Root<Ill> ill = illQuery.from(Ill.class);

        Predicate illPredicate = criteriaBuilder.like(ill.get("name"), '%' + name + '%');
        illQuery.where(illPredicate);
        TypedQuery<Ill> illTypedQuery = entityManager.createQuery(illQuery);
        return illTypedQuery.getResultList();
    }

    @Override
    public void save(Ill ill) {
        entityManager.persist(ill);
    }

    public void get() {
        List<Account> posts = entityManager.createQuery(
                "    select distinct p from Ill pc join pc.accounts p where pc.cures.size > :minScore order by p.id", Account.class)
                .setParameter("minScore", 2)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
    }
}
