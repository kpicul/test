package test;

        import javax.inject.Inject;
        import javax.persistence.EntityManager;


public class CreateUser  {

    @Inject
    private EntityManager em;




    public void createUser(Person user){
        em.persist(user);
    }
}
