package test;

import test.database.Member;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Controller {

    @Inject
    private FacesContext fc;

    @Inject
    private ManagedUser cu;

    @Named
    @Produces
    @RequestScoped
    private Member newPerson=new Member();

    public void create(){
        try{
            cu.createUser(newPerson);
            String message = "A new user with id " + newPerson.getUsername() + " has been created successfully";
            fc.addMessage(null, new FacesMessage(message));
        }catch (Exception e){
            String message = "An error has occured while creating the user (see log for details)";
            fc.addMessage(null, new FacesMessage(message));
            throw new RuntimeException(e);
        }

    }
}
