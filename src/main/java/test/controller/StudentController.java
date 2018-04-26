package test.controller;

import test.DatabaseQuerries;
import test.Session;
import test.database.Grade;
import test.database.Member;
import test.database.Performance;
import test.output.ResultSet;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Named
@ViewScoped
public class StudentController implements Serializable {
    FacesContext fc = FacesContext.getCurrentInstance();
    ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();

    @Inject
    private Session session;

    @Inject
    private DatabaseQuerries mu;

    private final double positiveGrade=1.5;
    private Member student;
    private String firstname;
    private String lastname;
    private String results;
    private String yearResults;
    private String gradeResults;
    private ArrayList <ResultSet>grads;
    private ArrayList <ResultSet> ongoing;
    private HttpSession session1;
    private List<ResultSet> yearResultSet;

    @PostConstruct
    public void postConstruct(){
        student=session.getUser();
        emptyRedirect();
        checkRole();
        firstname=student.getFirstName();
        lastname=student.getLastName();
        setPerformances();
        setYearResults();
        gradeResults();
    }

    public void toEdit(){
        nav.performNavigation("edit.xhtml");
    }

    private void setPerformances(){
        Map<String,String> result;
        List<Performance> perf=mu.getPerformancesFinished(student);
        result=new HashMap<String, String>();
        String courseName="";
        List<Grade> grads;
        double grade=0;
        for(Performance p:perf){
            courseName=mu.getCourse(p).getName();
            grads=mu.getGrades(p);
            grade=calculateAvg(grads);
            if(grade >= positiveGrade){
                result.put(courseName,grade+"");
            }
        }
        resultSetup(result);
    }

    private void setYearResults(){
        Map<String,Double> result=new HashMap<String, Double>();
        List<Object[]> perf=mu.getPerformancesYears(student);
        String yr;
        Double dbl;
        yearResultSet=new ArrayList<ResultSet>();
        ResultSet rs;
        for(Object[] i:perf){
            yr=i[0].toString();
            dbl=new Double(i[1].toString());
            rs=new ResultSet(yr+"",dbl+"");
            yearResultSet.add(rs);
        }
        System.out.println();
    }



    private void resultSetup(Map<String,String> result){
        grads=new ArrayList<ResultSet>();
        String sentence="";
        ResultSet rs;
        for(String s:result.keySet()){
            sentence=sentence+s+" : "+result.get(s)+"\n";
            rs=new ResultSet(s,result.get(s)+"");
            grads.add(rs);
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

    public String getYearResults() {
        return yearResults;
    }

    public void setYearResults(String yearResults){
        this.yearResults=yearResults;
    }

    public void setGradeResults(String gradeResults) {
        this.gradeResults = gradeResults;
    }

    public String getGradeResults() {
        return gradeResults;
    }

    private void gradeResults(){
        List<Performance> perf=null;
        ongoing=new ArrayList<ResultSet>();
        ResultSet rs;
        String desc="";
        String data="";
        Map <String,List<Grade>> grads=new HashMap<String, List<Grade>>();
        List<Grade> instance;
        String name;
        perf=mu.getPerformancesOngoing(student);
        for(Performance p:perf){
            name=p.getGroupcourseid().getTeachesid().getCourse().getName();
            instance=mu.getGrades(p);
            grads.put(name,instance);
            String datas="";
            if(instance.size()!=0){
                datas="";
                for(Grade i:instance){
                    datas+=i.getGrade()+" ";
                }
                rs=new ResultSet(name,datas);
                ongoing.add(rs);
            }
            else{
                datas="";
                rs=new ResultSet(name,datas);
                ongoing.add(rs);
            }
        }
        System.out.println();
    }

    public ArrayList getGrads() {
        return grads;
    }

    public void setGrads(ArrayList grads) {
        this.grads = grads;
    }

    public ArrayList<ResultSet> getOngoing() {
        return ongoing;
    }

    public void setOngoing(ArrayList<ResultSet> ongoing) {
        this.ongoing = ongoing;
    }
    public void logout(){
        this.session.setUser(null);
        nav.performNavigation("login.xhtml");
        //session1.invalidate();

    }

    private void emptyRedirect(){
        if(student==null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void checkRole(){
        if(!student.getRoleId().getName().equals("Student")){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("warning.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<ResultSet> getYearResultSet() {
        return yearResultSet;
    }

    public void setYearResultSet(List<ResultSet> yearResultSet) {
        this.yearResultSet = yearResultSet;
    }
}
