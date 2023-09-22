package org.example.repository;

import org.example.models.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TestRepository {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;


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
    public Account findByUserId(Long userId) {
        return (Account) DetachedCriteria.forClass(Account.class)
                .add(Restrictions.eq("id", userId)).getExecutableCriteria(entityManager.unwrap(Session.class)).uniqueResult();
    }
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
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
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
        criteria.add(Restrictions.eq("notes.address", "address2"));
        return criteria.getExecutableCriteria(entityManager.unwrap(Session.class)).list();
    }

//    public List<Note> getNotesByAccountId(Long accountId){
//        CriteriaBuilder criteriaBuilder = entityManager.unwrap(Session.class).getCriteriaBuilder();
//        CriteriaQuery<Note> query = criteriaBuilder.createQuery(Note.class);
//        Root<Note> from = query.from(Note.class);
//
//        query.where(criteriaBuilder.equal(from.get("patient"), accountId));
//        return entityManager.unwrap(Session.class).createQuery(query).getResultList();
//    }

    public Account getAccountById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.unwrap(Session.class).getCriteriaBuilder();
        CriteriaQuery<Account> userCriteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> userApplicationRoot = userCriteriaQuery.from(Account.class);

        userCriteriaQuery.where(criteriaBuilder.equal(userApplicationRoot.get("id"), id));
        return entityManager.unwrap(Session.class).createQuery(userCriteriaQuery).getSingleResult();
    }

    //    public List<Note> findByApplicationIdAndFeatureId(Long applicationId, Long featureId) {
//        DetachedCriteria criteria = DetachedCriteria.forClass(Note.class);
//        criteria.add(Restrictions.eq("patient", applicationId));
//        criteria.add(Restrictions.eq("doctor", featureId));
//        DetachedCriteria feature = criteria.createCriteria("doctor");
//        feature.createAlias("docNotes", "docNotes");
////        feature.addOrder(Order.asc("category.id"));
//        feature.createAlias("notes", "another_notes", org.hibernate.sql.JoinType.LEFT_OUTER_JOIN);
////        feature.addOrder(Order.asc("group.orderNum"));
////        feature.addOrder(Order.asc("group.id"));
////        feature.addOrder(Order.asc("orderNum"));
//        return criteria.getExecutableCriteria(entityManager.unwrap(Session.class)).list();
//    }
    private Session session() {
        return entityManager.unwrap(Session.class);
    }

    public List<Note> getNotesByAccountId(Long id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Note.class);
        criteria.createAlias("patient", "pat");
        criteria.add(Restrictions.eq("pat.id", id));
        criteria.createAlias("pat.illness", "ill");
        criteria.add(Restrictions.eq("ill.name", "chuma"));
        return criteria.getExecutableCriteria(session()).list();
    }
//    public ApplicationFeatureMutation findByApplicationIdAndFeatureId(Long applicationId, Long featureId) {
//        DetachedCriteria criteria = DetachedCriteria.forClass(ApplicationFeatureMutation.class);
//        criteria.add(Restrictions.eq("applicationId", applicationId));
//        criteria.add(Restrictions.eq("feature.id", featureId));
//        DetachedCriteria feature = criteria.createCriteria("feature");
//        feature.createAlias("category", "category");
//        feature.addOrder(Order.asc("category.id"));
//        feature.createAlias("featureGroup", "group");
//        feature.addOrder(Order.asc("group.orderNum"));
//        feature.addOrder(Order.asc("group.id"));
//        feature.addOrder(Order.asc("orderNum"));
//        return (ApplicationFeatureMutation) criteria.getExecutableCriteria(session()).uniqueResult();
//    }

    public ApplicationFeatureMutation findByApplicationIdAndFeatureId(Long applicationId, Long featureId) {
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<ApplicationFeatureMutation> query = criteriaBuilder.createQuery(ApplicationFeatureMutation.class);
        Root<ApplicationFeatureMutation> root = query.from(ApplicationFeatureMutation.class);

        query.where(criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("applicationId"), applicationId)),
                criteriaBuilder.equal(root.get("feature"), featureId));

        Join<ApplicationFeatureMutation, Feature> featureJoin = root.join("feature");
        query.orderBy(criteriaBuilder.asc(featureJoin.get("category")));
        Join<Feature, FeatureGroup> featureGroupJoin = featureJoin.join("featureGroup");
        query.orderBy(
                criteriaBuilder.asc(featureGroupJoin.get("orderNum")),
                criteriaBuilder.asc(featureGroupJoin.get("id")),
                criteriaBuilder.asc(featureJoin.get("orderNum"))
        );
        return session().createQuery(query).getSingleResult();
    }

    public List<ApplicationFeatureMutation> findByApplicationId(Long applicationId) {
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<ApplicationFeatureMutation> query = criteriaBuilder.createQuery(ApplicationFeatureMutation.class);
        Root<ApplicationFeatureMutation> root = query.from(ApplicationFeatureMutation.class);

        query.where(criteriaBuilder.equal(root.get("applicationId"), applicationId));
        Join<ApplicationFeatureMutation, Feature> featureJoin = root.join("feature");
        Join<Feature, FeatureGroup> featureGroupJoin = featureJoin.join("featureGroup");
        query.orderBy(
                criteriaBuilder.asc(featureJoin.get("category")),
                criteriaBuilder.asc(featureGroupJoin.get("orderNum")),
                criteriaBuilder.asc(featureJoin.get("orderNum")));

        return session().createQuery(query).getResultList();
    }

    public List<ApplicationFeatureMutation> findByApplicationIdOld(Long applicationId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ApplicationFeatureMutation.class);
        criteria.add(Restrictions.eq("applicationId", applicationId));
        DetachedCriteria feature = criteria.createCriteria("feature");
        feature.createAlias("category", "category");
        feature.addOrder(Order.asc("category.id"));
        feature.createAlias("featureGroup", "group");
        feature.addOrder(Order.asc("group.orderNum"));
        feature.addOrder(Order.asc("orderNum"));
        return criteria.getExecutableCriteria(session()).list();
    }

    public List<ApplicationFeatureMutation> findByFeatureIdOld(Long featureId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ApplicationFeatureMutation.class);
        DetachedCriteria feature = criteria.createCriteria("feature");
        feature.add(Restrictions.eq("id", featureId));
        return criteria.getExecutableCriteria(session()).list();
    }

    public List<Account> findByFeatureIdNew(List<Long> featureId) {
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Account> query = criteriaBuilder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);

        Predicate[] predicates = new Predicate[featureId.size()];
        for (int i = 0; i < featureId.size(); i++) {
            predicates[i] = criteriaBuilder.equal(root.get("id"), featureId.get(i));
        }
        query.where(
                criteriaBuilder.or(
                        predicates
                )
        );
//        query.where(criteriaBuilder.equal(root.get("feature"), featureId));
        return session().createQuery(query).getResultList();
    }

    public List<ApplicationFeatureMutation> findApplicationFeaturesNew(Long applicationId, Long categoryId) {
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<ApplicationFeatureMutation> query = criteriaBuilder.createQuery(ApplicationFeatureMutation.class);
        Root<ApplicationFeatureMutation> root = query.from(ApplicationFeatureMutation.class);
        Join<ApplicationFeatureMutation, Feature> featureJoin = root.join("feature");

        query.where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("applicationId"), applicationId),
                criteriaBuilder.equal(featureJoin.get("category"), categoryId))).orderBy(criteriaBuilder.asc(criteriaBuilder.function("rand", Double.class)));
        return session().createQuery(query).getResultList();
    }

    public List<ApplicationFeatureMutation> findApplicationFeaturesOld(Long applicationId, Long categoryId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ApplicationFeatureMutation.class);
        criteria.add(Restrictions.eq("applicationId", applicationId));
        DetachedCriteria category = criteria.createCriteria("feature");
        category.createAlias("category", "category");
        category.add(Restrictions.eq("category.id", categoryId));
        return criteria.getExecutableCriteria(session()).list();
    }

    public boolean exists(Long initiatorId, String resourceName, Long resourceId, List<UserActivity> activity) {
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Account> query = criteriaBuilder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);

        query.where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("id"), initiatorId),
                criteriaBuilder.equal(root.get(resourceName), resourceId),
//                criteriaBuilder.equal(root.get("email"), activity.toString())
                root.get("email").in(activity.stream().map(UserActivity::toString).collect(Collectors.toList()))
        ));
        return (session().createQuery(query).getSingleResult()) != null;
    }

//    public boolean exists(Long initiatorId, String resourceName, Long resourceId, UserActivity activity) {
//        DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
//        criteria.add(Restrictions.eq("id", initiatorId));
//        criteria.add(Restrictions.eq(resourceName + ".id", resourceId));
//        criteria.add(Restrictions.eq("email", activity.toString()));
//        criteria.setProjection(Projections.rowCount());
//        Object result = criteria.getExecutableCriteria(session()).uniqueResult();
//        return result == null ? false : ((Number) result).intValue() > 0;
//    }

    private class BulkNotifyFirstThenDateOrder extends Order {
        private static final long serialVersionUID = 1L;

        public BulkNotifyFirstThenDateOrder() {
            super("", false);
        }

        @Override
        public String toSqlString(Criteria criteria, org.hibernate.criterion.CriteriaQuery criteriaQuery) throws HibernateException {
            return "(activity = 'bulk_notification' AND was_viewed = FALSE) DESC";
        }
    }
//    public int countUserBookmarks(Long userId) {
//        DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
//        criteria.add(Restrictions.eq("avatar.id", userId));
//        criteria.setProjection(Projections.rowCount());
//        Object result = criteria.getExecutableCriteria(session()).uniqueResult();
//        return result == null ? 0 : ((Number) result).intValue();
//    }

    public int countUserBookmarks(Long userId) {
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Account> query = criteriaBuilder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);

        query.where(criteriaBuilder.equal(root.get("avatar"), userId));
        return session().createQuery(query).getResultList().size();
    }


//new TypeConfiguration().getBasicTypeRegistry().getRegisteredType(String.class)
}
