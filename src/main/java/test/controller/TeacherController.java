package test.controller;

import test.ManagedUser;
import test.Session;
import test.database.*;
import test.output.ResultSet;


import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class TeacherController implements Serializable{

    FacesContext fc = FacesContext.getCurrentInstance();
    ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();

    @Inject
    private Session session;

    @Inject
    private ManagedUser mu;

    private Member teacher;

    private long selectedYearId;

    private long selectedCourseId;

    private long selectedGroupId;

    private List<Year> years;

    private List<Course> courses;

    private List<Group> groups;

    private Groupcourse selectedGroupcourse;

    private Teaches selectedTeaches;

    private List<Performance> performanceData;

    private List<ResultSet> studentResults;

    private String editedStudentUsername;

    private List<Grade> grades;

    private Performance editedPerformance;

    private int addedGrade;

    private int deletedGrade;

    private boolean editGradeBool;

    private int setGradeValue;

    private long setGradeId;




    @PostConstruct
    public void postConstruct(){
        years=mu.getYears();
        teacher=session.getUser();
        emptyRedirect();
        editGradeBool=false;
        //teacher=session.getUser();

    }

    public long getSelectedYearId() {
        return selectedYearId;
    }

    public void setSelectedYearId(long selectedYearId) {
        this.selectedYearId = selectedYearId;
    }

    public long getSelectedCourseId() {
        return selectedCourseId;
    }

    public void setSelectedCourseId(long selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }

    public long getSelectedGroupId() {
        return selectedGroupId;
    }

    public void setSelectedGroupId(long selectedGroupId) {
        this.selectedGroupId = selectedGroupId;
    }

    public List<Year> getYears() {
        return years;
    }

    public void setYears(List<Year> years) {
        this.years = years;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Performance> getPerformanceData() {
        return performanceData;
    }

    public void setPerformanceData(List<Performance> performanceData) {
        this.performanceData = performanceData;
    }

    public List<ResultSet> getStudentResults() {
        return studentResults;
    }

    public void setStudentResults(List<ResultSet> studentResults) {
        this.studentResults = studentResults;
    }

    public String getEditedStudentUsername() {
        return editedStudentUsername;
    }

    public void setEditedStudentUsername(String editedStudentUsername) {
        this.editedStudentUsername = editedStudentUsername;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public int getAddedGrade() {
        return addedGrade;
    }

    public void setAddedGrade(int addedGrade) {
        this.addedGrade = addedGrade;
    }

    public int getDeletedGrade() {
        return deletedGrade;
    }

    public void setDeletedGrade(int deletedGrade) {
        this.deletedGrade = deletedGrade;
    }

    public boolean isEditGradeBool() {
        return editGradeBool;
    }

    public void setEditGradeBool(boolean editGradeBool) {
        this.editGradeBool = editGradeBool;
    }

    public void setupCourses(){
        courses=mu.getCoursesByTeacherYear(teacher.getId(),selectedYearId);
    }
    public void setupGroups(){
        groups=mu.getGroupsByTeacherYearCourse(teacher.getId(),selectedYearId,selectedCourseId);
        selectedTeaches=mu.getTeachesByCourseYearTeacher(selectedCourseId,selectedYearId,teacher.getId());
    }
    public void setupGrades(){
        selectedGroupcourse=mu.getGroupcourse(selectedTeaches.getId(),selectedGroupId);
        Map<String,List<Grade>> gradeMap=new HashMap<String,List<Grade>>();
        studentResults=new ArrayList<ResultSet>();
        performanceData=mu.getPerformancesByGroupcourse(selectedGroupcourse.getId());
        String sname="";
        List<Grade> grads=null;
        for(Performance p:performanceData){
            sname=p.getStudentId().getUsername();
            grads=mu.getGrades(p);
            gradeMap.put(sname,grads);
        }
        ResultSet rs;
        String glue;
        for(String i:gradeMap.keySet()){
            glue="";
            for(Grade j: gradeMap.get(i)){
                glue+=j.getGrade()+" ";
            }
            rs=new ResultSet(i,glue);
            studentResults.add(rs);
        }
        System.out.println();
    }

    public void editGrades(){
        System.out.println(this.editedStudentUsername);
        Member student=mu.getForUsername(editedStudentUsername);
        this.editedPerformance=mu.getPerformanceByStudentGroupcourse(student.getId(),selectedGroupcourse.getId());
        this.grades=mu.getGrades(editedPerformance);
    }

    public void addGrade(){
        Grade g=mu.addGrade(editedPerformance,addedGrade);
        grades.add(g);
        setupGrades();
    }

    public void deleteGrade(){
        Grade g=mu.removeGrade(deletedGrade);
        for(Grade i:grades){
            if(i.getId()==g.getId()){
                grades.remove(i);
                break;
            }
        }
        setupGrades();
    }

    public int getSetGradeValue() {
        return setGradeValue;
    }

    public void setSetGradeValue(int setGradeValue) {
        this.setGradeValue = setGradeValue;
    }

    public long getSetGradeId() {
        return setGradeId;
    }

    public void setSetGradeId(long setGradeId) {
        this.setGradeId = setGradeId;
    }

    public void updateGrade(){
        mu.updateGrade(setGradeValue,setGradeId);
        setupGrades();
        for(Grade i:grades){
            if(i.getId()==setGradeId){
                i.setGrade(setGradeValue);
                break;
            }
        }
        editGradeBool=!editGradeBool;
    }

    public void renderEdit(){
        editGradeBool=!editGradeBool;
    }

    public void logout(){
        session=null;
        nav.performNavigation("login.xhtml");
    }
    private void emptyRedirect(){
        if(teacher==null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
