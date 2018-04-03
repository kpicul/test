package test;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class Login implements Serializable {
    FacesContext fc = FacesContext.getCurrentInstance();
    ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();
    private String name;
    private String password;

    public void openStudent(){
        nav.performNavigation("student.xhtml");
    }
    public void openAdmin(){
        nav.performNavigation("admin.xhtml");
    }
    public void openTeacher(){
        nav.performNavigation("teacher.xhtml");
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPassword(String password){
        this.password=Helper.toHash(password);
    }
    public String getName(){
        return this.name;
    }

    public String getPassword() {
        return password;
    }

    public void login(){
        /*Person[] persons=new Person[3];
        persons[0] = new Person(1L,"DarthVader","Anakin","Skywalker","01.01.1970","student","iDontLikeSand");
        persons[1]=new Person(2L,"ObiWan","Ben","Kenobi","07.10.1956","teacher","HighGround");
        persons[2]=new Person(3L,"DarthSidious","Sheev","Palpatine","06.06.1906","admin","Order66");
        for(int i=0;i<persons.length;i++){
            if(persons[i].getUsername().equals(this.name) && persons[i].getPassword().equals(this.password)){
                System.out.println(this.password);
                if(persons[i].getRole().equals("admin")){
                    openAdmin();
                }
                else if(persons[i].getRole().equals("student")){
                    openStudent();
                }
                else if(persons[i].getRole().equals("teacher")){
                    openTeacher();
                }
            }
        }*/
    }



}
