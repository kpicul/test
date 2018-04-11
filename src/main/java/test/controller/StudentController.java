package test.controller;

import test.ManagedUser;
import test.Session;
import test.database.Grade;
import test.database.Member;
import test.database.Performance;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class StudentController {
    FacesContext fc = FacesContext.getCurrentInstance();
    ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();

    @Inject
    private Session session;

    @Inject
    private ManagedUser mu;

    private Member student;
    private String firstname;
    private String lastname;
    private String results;
    private Map<String,String> result;

    @PostConstruct
    public void postConstruct(){
        student=session.getUser();
        firstname=student.getFirstName();
        lastname=student.getLastName();
        setPerformances();
    }

    public void toEdit(){
        System.out.println("dead end");
        nav.performNavigation("edit.xhtml");
    }

    private void setPerformances(){
        List<Performance> perf=mu.getPerformances(student);
        result=new HashMap<String, String>();
        String courseName="";
        double grade=0;
        for(Performance p:perf){
            courseName=mu.getCourse(p).getName();
            grade=calculateAvg(mu.getGrades(p));
            result.put(courseName,grade+"");
        }
        resultSetup();
    }

    private void resultSetup(){
        String sentence="";
        for(String s:result.keySet()){
            sentence=sentence+s+" : "+result.get(s)+"\n";
        }
        results=sentence;
    }

    private double calculateAvg(List <Grade> grades){
        double avg=0;
        for(Grade g: grades){
            avg+=g.getGrade();
        }
        avg=avg/grades.size();
        return avg;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getResults(){
        return this.results;
    }

    public void setResults(String results){
        this.results=results;
    }
}
