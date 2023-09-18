package org.example.repository;

import org.example.models.Account;
import org.example.models.Note;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class TestRepository {

    @Autowired
    private EntityManager entityManager;


//    public Ill findIllByName(String name) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Ill> illQuery = criteriaBuilder.createQuery(Ill.class);
//        Root<Ill> ill = illQuery.from(Ill.class);
//
//        Predicate illPredicate = criteriaBuilder.like(ill.get("name"), name);
//        illQuery.where(illPredicate);
//        TypedQuery<Ill> illTypedQuery = entityManager.createQuery(illQuery);
//        return illTypedQuery.getSingleResult();
//    }
//
//    public List<Account> findAccountByName(String name) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Account> accountQuery = criteriaBuilder.createQuery(Account.class);
//        Root<Account> account = accountQuery.from(Account.class);
//
//        Predicate illPredicate = criteriaBuilder.like(account.get("name"), name);
//        accountQuery.where(illPredicate);
//        TypedQuery<Account> illTypedQuery = entityManager.createQuery(accountQuery);
//        return illTypedQuery.getResultList();
//    }
//
//    public List<Account> findByUserName(String userId) {
//        return (List<Account>) DetachedCriteria.forClass(Account.class)
//                .add(Restrictions.eq("name", userId)).getExecutableCriteria(entityManager.unwrap(Session.class)).list();
//    }
//    public Account findByUserId(Long userId) {
//        return (Account) DetachedCriteria.forClass(Account.class)
//                .add(Restrictions.eq("id", userId)).getExecutableCriteria(entityManager.unwrap(Session.class)).uniqueResult();
//    }
//    public Account getAccountById(Long id, String name){
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Account> accountQuery = criteriaBuilder.createQuery(Account.class);
//        Root<Account> account = accountQuery.from(Account.class);
//
//        Predicate accountPredicate = criteriaBuilder.equal(account.get("id"), id);
//        Predicate accountPredicate2 = criteriaBuilder.equal(account.get("name"), name);
//        accountQuery.where(criteriaBuilder.and(accountPredicate, accountPredicate2));
//        TypedQuery<Account> accountTypedQuery = entityManager.createQuery(accountQuery);
//        return accountTypedQuery.getSingleResult();
//    }

//    public int existsByStrcode(String strcode) {
//        Class<Account> entityClass = getEntityClass();
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Class<? extends Object>> accountQuery = criteriaBuilder.createQuery(Class.class);
//        Root<Class> account = accountQuery.from(Class.class);
//        List<O> list = new ArrayList<O>();
//        Predicate accountPredicate = criteriaBuilder.equal(account.get("name"), strcode);
//        accountQuery.where(accountPredicate);
//        TypedQuery<Class<? extends Object>> accountTypedQuery = entityManager.createQuery(accountQuery);
//        return accountTypedQuery.getResultList().size();
//    }

    //    public <O> O getIdByStrcode(String strcode) {
//        DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
//        criteria.add(Restrictions.eq("name", strcode));
//        criteria.setProjection(Projections.id()/* property("id") */);
//
//        System.out.println(criteria.getExecutableCriteria(entityManager.unwrap(Session.class)).list());
//        return (O) criteria.getExecutableCriteria(entityManager.unwrap(Session.class)).list();
//    }
//    public <O> O newGetIdByStrcode(String strcode) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<O> accountQuery = (CriteriaQuery<O>) criteriaBuilder.createQuery();
//        Root<O> account = accountQuery.from((Class<O>) Account.class);
//        System.out.println(account.getModel());
//        System.out.println(account.toString());
//        Predicate accountPredicate = criteriaBuilder.equal(account.get("name"), strcode);
//        accountQuery.where(accountPredicate);
//
//
//        TypedQuery<O> accountTypedQuery = entityManager.createQuery(accountQuery);
//        return (O) accountTypedQuery.getResultList();
//        return (O) criteria.getExecutableCriteria(entityManager.unwrap(Session.class)).list();
//    }
//
    public List<Account> findPreusedByUserId(Long userId, boolean privatePage) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
        criteria.add(Restrictions.eq("id", userId));
        criteria.add(Restrictions.isNotNull("appEndDate"));
        if (!privatePage) {
            criteria.createCriteria("email", "email");
            criteria.add(Restrictions.or(Restrictions.eq("name", "name1"),
                    Restrictions.eq("password", "password1")));
        }
        criteria.addOrder(Order.asc("id"));
        return criteria.getExecutableCriteria(entityManager.unwrap(Session.class)).list();
    }
//
    public int countUserApplications(String userId) {
        CriteriaBuilder criteriaBuilder = entityManager.unwrap(Session.class).getCriteriaBuilder();
        CriteriaQuery<Account> userCriteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> userApplicationRoot = userCriteriaQuery.from(Account.class);

        userCriteriaQuery.where(criteriaBuilder.equal(userApplicationRoot.get("email"), userId));
        List<Account> result = entityManager.unwrap(Session.class).createQuery(userCriteriaQuery).getResultList();
        return result == null ? 0 : ((Number) result.size()).intValue();
    }

//    public int countUserApplications(String userId) {
//        DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
//        criteria.add(Restrictions.eq("email", userId));
//        criteria.setProjection(Projections.rowCount());
//        Object result = criteria.getExecutableCriteria(entityManager.unwrap(Session.class)).uniqueResult();
//        return result == null ? 0 : ((Number) result).intValue();
//    }

//    public Account findByUserIdAndApplicationId(Long userId, Long applicationId) {
//        DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
//        criteria.add(Restrictions.eq("user.id", userId));
//        criteria.add(Restrictions.eq("application.id", applicationId));
//        return (Account) criteria.getExecutableCriteria(entityManager.unwrap(Session.class)).uniqueResult();
//    }


    public List<Account> findByFeatureId(Long featureId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
        System.out.println(criteria.getAlias());
        DetachedCriteria feature = criteria.createCriteria("notes");
        System.out.println(feature.getAlias());
        System.out.println(feature.getClass());
        feature.add(Restrictions.eq("id", featureId));
        return feature.getExecutableCriteria(entityManager.unwrap(Session.class)).list();
    }
//
//    public List<ApplicationFeatureDeleted> findByFeatureId(Long featureId) {
//        CriteriaBuilder criteriaBuilder = entityManager.unwrap(Session.class).getCriteriaBuilder();
//        CriteriaQuery<ApplicationFeatureDeleted> userCriteriaQuery = criteriaBuilder.createQuery(ApplicationFeatureDeleted.class);
//        Root<ApplicationFeatureDeleted> userApplicationRoot = userCriteriaQuery.from(ApplicationFeatureDeleted.class);
//        userCriteriaQuery.where(criteriaBuilder.equal(userApplicationRoot.get("id"), featureId));
//        return entityManager.unwrap(Session.class).createQuery(userCriteriaQuery).getResultList();
//    }

    public List<Account> findAllWithAccount() {
        CriteriaBuilder criteriaBuilder = entityManager.unwrap(Session.class).getCriteriaBuilder();
        CriteriaQuery<Account> userCriteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> userApplicationRoot = userCriteriaQuery.from(Account.class);
        Join<Account, Note> join = userApplicationRoot.join("notes", JoinType.LEFT);

        userCriteriaQuery.where(criteriaBuilder.and(criteriaBuilder.isNotNull(userApplicationRoot.get("role")),
                criteriaBuilder.equal(join.get("address"), "address2")));
//        userCriteriaQuery.where(cri)

        return entityManager.unwrap(Session.class).createQuery(userCriteriaQuery).getResultList();
    }

    public List<Account> findAllWithAccountTest() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
        criteria.add(Restrictions.isNotNull("role"));
        criteria.add(Restrictions.isNotNull("notes"));

        criteria.createAlias("notes", "notes");
        criteria.add(Restrictions.eq("notes.address",  "address2"));
        return criteria.getExecutableCriteria(entityManager.unwrap(Session.class)).list();
    }

    public List<Note> getNotesByAccountId(Long accountId){
        CriteriaBuilder criteriaBuilder = entityManager.unwrap(Session.class).getCriteriaBuilder();
        CriteriaQuery<Note> query = criteriaBuilder.createQuery(Note.class);
        Root<Note> from = query.from(Note.class);

        query.where(criteriaBuilder.equal(from.get("patient"), accountId));
        return entityManager.unwrap(Session.class).createQuery(query).getResultList();
    }

    public Account getAccountById(Long id){
        CriteriaBuilder criteriaBuilder = entityManager.unwrap(Session.class).getCriteriaBuilder();
        CriteriaQuery<Account> userCriteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> userApplicationRoot = userCriteriaQuery.from(Account.class);

        userCriteriaQuery.where(criteriaBuilder.equal(userApplicationRoot.get("id"), id));
        return entityManager.unwrap(Session.class).createQuery(userCriteriaQuery).getSingleResult();
    }

}
