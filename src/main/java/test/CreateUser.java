package test;

        import test.database.Member;

        import javax.inject.Inject;
        import javax.persistence.EntityManager;
        import javax.persistence.NoResultException;
        import javax.persistence.Query;


public class CreateUser  {

    @Inject
    private EntityManager em;
   /* FacesContext fc = FacesContext.getCurrentInstance();
    ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();*/

    public Member getForUsername(String username) {
        //nav.performNavigation("admin.xhtml");
        try {
            Query query = em.createQuery("select u from Member u where u.username = :username");
            query.setParameter("username", username);
            return (Member) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void createUser(Member member){
        em.persist(member);
    }
}
