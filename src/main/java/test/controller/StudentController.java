package test.controller;

import sun.misc.Perf;
import test.ManagedUser;
import test.Session;
import test.database.Grade;
import test.database.Member;
import test.database.Performance;
import test.output.ResultSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
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

    private final double positiveGrade=2.0;
    private Member student;
    private String firstname;
    private String lastname;
    private String results;
    private String yearResults;
    private String gradeResults;
    private ArrayList <ResultSet>grads;
    private ArrayList <ResultSet> ongoing;

    @PostConstruct
    public void postConstruct(){
        student=session.getUser();
        firstname=student.getFirstName();
        lastname=student.getLastName();
        setPerformances();
        setYearResults();
        gradeResults();
    }

    public void toEdit(){
        System.out.println("dead end");
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
        System.out.println("test");
        for(Object[] i:perf){
            yr=i[0].toString();
            dbl=new Double(i[1].toString());
            result.put(yr,dbl);
        }
        //System.out.println();
        yearResultsSetup(result);
    }

    private void yearResultsSetup(Map<String,Double> result ){
        String sentence="";
        for(String s:result.keySet()){
            sentence=sentence+s+" : "+result.get(s)+"\n";
        }
        yearResults=sentence;
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
        ongoing=new ArrayList<ResultSet>();
        ResultSet rs;
        String desc="";
        String data="";
        Map<String,List<Integer>> result;
        result=new HashMap<String, List<Integer>>();
        List<Object[]> perf=mu.getGradesPerformance(student);
        String course;
        int grade;
        for(Object[] i:perf){
            course=i[0].toString();
            grade=new Integer(i[1].toString());
            if(result.get(course)==null){
                List <Integer>grades=new ArrayList<Integer>();
                result.put(course,grades);
                result.get(course).add(grade);
            }
            else{
                result.get(course).add(grade);
            }
        }
        gradeResults="";
        for(String i:result.keySet()){
            gradeResults+=i +": ";
            desc=i;
            data="";
            for(int j:result.get(i)){
                gradeResults+=j+",";
                data+=j+" ";
            }
            rs=new ResultSet(desc,data);
            ongoing.add(rs);
        }
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
}
