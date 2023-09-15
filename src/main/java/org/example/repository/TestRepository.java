package org.example.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.example.models.Account;
import org.example.models.Ill;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TestRepository {

    @Autowired
    private EntityManager entityManager;

    public Ill findIllByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ill> illQuery = criteriaBuilder.createQuery(Ill.class);
        Root<Ill> ill = illQuery.from(Ill.class);

        Predicate illPredicate = criteriaBuilder.like(ill.get("name"), name);
        illQuery.where(illPredicate);
        TypedQuery<Ill> illTypedQuery = entityManager.createQuery(illQuery);
        return illTypedQuery.getSingleResult();
    }

    public List<Account> findAccountByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> accountQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> account = accountQuery.from(Account.class);

        Predicate illPredicate = criteriaBuilder.like(account.get("name"), name);
        accountQuery.where(illPredicate);
        TypedQuery<Account> illTypedQuery = entityManager.createQuery(accountQuery);
        return illTypedQuery.getResultList();
    }

    public List<Account> findByUserName(String userId) {
        return (List<Account>) DetachedCriteria.forClass(Account.class)
                .add(Restrictions.eq("name", userId)).getExecutableCriteria(entityManager.unwrap(Session.class)).list();
    }
    public Account findByUserId(Long userId) {
        return (Account) DetachedCriteria.forClass(Account.class)
                .add(Restrictions.eq("id", userId)).getExecutableCriteria(entityManager.unwrap(Session.class)).uniqueResult();
    }
    public Account getAccountById(Long id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> accountQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> account = accountQuery.from(Account.class);

        Predicate accountPredicate = criteriaBuilder.equal(account.get("id"), id);
        accountQuery.where(accountPredicate);
        TypedQuery<Account> accountTypedQuery = entityManager.createQuery(accountQuery);
        return accountTypedQuery.getSingleResult();
    }

    public int existsByStrcode(String strcode) {
//        Class<Account> entityClass = getEntityClass();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Class> accountQuery = criteriaBuilder.createQuery(Class.class);
        Root<Class> account = accountQuery.from(Class.class);
        List<O> list = new ArrayList<O>();
        Predicate accountPredicate = criteriaBuilder.equal(account.get("name"), strcode);
        accountQuery.where(accountPredicate);
        TypedQuery<Class> accountTypedQuery = entityManager.createQuery(accountQuery);
        return accountTypedQuery.getResultList().size();
    }

    public <O> O getIdByStrcode(String strcode) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
        criteria.add(Restrictions.eq("name", strcode));
        criteria.setProjection(Projections.id()/* property("id") */);

        System.out.println(criteria.getExecutableCriteria(entityManager.unwrap(Session.class)).list());
        return (O) criteria.getExecutableCriteria(entityManager.unwrap(Session.class)).list();
    }
    public <O> O getIdByStrcode(String strcode) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<O> accountQuery = (CriteriaQuery<O>) criteriaBuilder.createQuery();
        Root<O> account = accountQuery.from;
        List<O> list = new ArrayList<O>();
        Predicate accountPredicate = criteriaBuilder.equal(account.get("name"), strcode);
        accountQuery.where(accountPredicate);
        TypedQuery<O> accountTypedQuery = entityManager.createQuery(accountQuery);
        return accountTypedQuery.getResultList().size();
        return (O) criteria.getExecutableCriteria(entityManager.unwrap(Session.class)).list();
    }

}
