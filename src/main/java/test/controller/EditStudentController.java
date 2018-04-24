package test.controller;

import test.Helper;
import test.ManagedUser;
import test.Session;
import test.database.*;
import test.output.ResultTeaches;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class EditStudentController implements Serializable {
    @Inject
    private ManagedUser mu;

    @Inject
    private Session session;

    private Member user;

    private List<Group> groups;

    private long selectedGroupId;

    private List<Member> students;

    private long selectedStudent;

    private Member student;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private Date dateOfBirth;

    private List<String> teachesDescription;

    private String selectTeaches;

    private List<Teaches> teachers;

    private Teaches selectedTeacher;

    private List<Course> courses;

    private long selectedCourse;

    private long changedTeacherId;

    private long changeTeaches;

    private List<Year> years;

    private long selectedYearId;

    private List<Teaches> unassignedCourses;

    long selectedUnassigned;

    private long changedGroup;

    private Group activeGroup;



    @PostConstruct
    public void postConstruct(){
        groupSetup();
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public long getSelectedGroupId() {
        return selectedGroupId;
    }

    public void setSelectedGroupId(long selectedGroupId) {
        this.selectedGroupId = selectedGroupId;
    }

    public List<Member> getStudents() {
        return students;
    }

    public void setStudents(List<Member> students) {
        this.students = students;
    }

    public long getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(long selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public long getChangeTeaches() {
        return changeTeaches;
    }

    public void setChangeTeaches(long changeTeaches) {
        this.changeTeaches = changeTeaches;
    }


    private void groupSetup(){
        user=session.getUser();
        emptyRedirect();
        groups=mu.getGroups();
        years=mu.getYears();
        //activeGroup=null;
    }

    public void setupStudents(){
        this.student=null;
        this.userName="";
        this.firstName="";
        this.lastName="";
        this.password="";
        this.dateOfBirth=null;
        students=mu.getStudentsByGroup(selectedGroupId);
        //teachesDescription=new ArrayList<String>();
        courses=mu.getCoursesByGroup(selectedGroupId);
        setupUnassigned();

    }

    public void setupUnassigned(){
        unassignedCourses=mu.getTeaches();
        ArrayList <Teaches> toRemove=new ArrayList<Teaches>();
        for(Teaches i:unassignedCourses){
            for(Course c:courses){
                if(i.getCourse().getId()==c.getId()){
                    toRemove.add(i);
                }
            }
        }
        for(Teaches i:toRemove){
            unassignedCourses.remove(i);
        }
    }

    public Member getStudent() {
        return student;
    }

    public void setStudent(Member student) {
        this.student = student;
    }

    public void selectStudent(){
        this.student=null;
        this.userName="";
        this.firstName="";
        this.lastName="";
        this.password="";
        this.dateOfBirth=null;

        this.student=mu.getForId(selectedStudent);
        this.userName=student.getUsername();
        this.firstName=student.getFirstName();
        this.lastName=student.getLastName();
        this.password=student.getPassword();
        this.dateOfBirth=student.getDateOfBirth();

        activeGroup=mu.getActiveGroup(student.getId());
        this.changedGroup=activeGroup.getId();
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        System.out.println("SET lastname: "+lastName);
        this.lastName=lastName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        //System.out.println("SET username: "+userName);
        this.userName = userName;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password) {
        //System.out.println("SET password: "+password);
        if(password!=""){
            this.password = Helper.getSHA(password, userName);
        }
        //this.password=password;
    }


    public String getDateOfBirth() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String date=null;
        if(dateOfBirth != null){
            date=df.format(dateOfBirth);
        }
        return date;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.dateOfBirth = new java.sql.Date(df.parse(dateOfBirth).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getSelectTeaches() {
        return selectTeaches;
    }

    public void setSelectTeaches(String selectTeaches) {
        this.selectTeaches = selectTeaches;
    }


    public List<String> getTeachesDescription() {
        return teachesDescription;
    }

    public void setTeachesDescription(List<String> teachesDescription) {
        this.teachesDescription = teachesDescription;
    }

    public List<Teaches> getTeachers() {
        return teachers;
    }


    public Teaches getSelectedTeacher() {
        return selectedTeacher;
    }

    public void setSelectedTeacher(Teaches selectedTeacher) {
        this.selectedTeacher = selectedTeacher;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public long getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(long selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public long getChangedTeacherId() {
        return changedTeacherId;
    }

    public void setChangedTeacherId(long changedTeacher) {
        this.changedTeacherId = changedTeacher;
    }

    public List<Year> getYears() {
        return years;
    }

    public void setYears(List<Year> years) {
        this.years = years;
    }

    public long getSelectedYearId() {
        return selectedYearId;
    }

    public void setSelectedYearId(long selectedYearId) {
        this.selectedYearId = selectedYearId;
    }

    public List<Teaches> getUnassignedCourses() {
        return unassignedCourses;
    }

    public void setUnassignedCourses(List<Teaches> unassignedCourses) {
        this.unassignedCourses = unassignedCourses;
    }

    public long getSelectedUnassigned() {
        return selectedUnassigned;
    }

    public void setSelectedUnassigned(long selectedUnassigned) {
        this.selectedUnassigned = selectedUnassigned;
    }

    public long getChangedGroup() {
        return changedGroup;
    }

    public void setChangedGroup(long changedGroup) {
        this.changedGroup = changedGroup;
    }

    public Group getActiveGroup() {
        return activeGroup;
    }

    public void setActiveGroup(Group activeGroup) {
        this.activeGroup = activeGroup;
    }

    public void updateStudent(){
        mu.updateUserName(student,userName);
        mu.updatepassword(student,password);
        mu.updateFirstName(student,firstName);
        mu.updateLasttName(student,lastName);
        mu.updateDateOfBirth(student,dateOfBirth);
        changeGroup();
    }

    public void setupTeacherSet(){
        selectedTeacher=mu.getTeacherByCourseGroupYear(selectedCourse,selectedGroupId,selectedYearId);
        teachers=mu.getTeachesByCourseYear(selectedCourse,selectedYearId);
    }



    public void updateCourseGroup(){
        long toRemoveId=selectedTeacher.getId();
        mu.updateGroupcourse(selectedGroupId,changedTeacherId,toRemoveId);
    }

    public void addCourseToGroup(){
        Groupcourse gc=mu.addGroupcourse(selectedUnassigned,selectedGroupId);
        for(Teaches i:unassignedCourses){
            if(i.getId()==selectedUnassigned){
                unassignedCourses.remove(i);
                courses.add(i.getCourse());
                break;
            }
        }
        for(Member student:students){
            mu.addPerformance(student.getId(),gc.getId());
        }
    }

    public void removeCourseFromGroup(){
        Groupcourse gc=mu.getGroupcourse(selectedTeacher.getId(),selectedGroupId);
        List<Performance> performances=mu.getPerformancesByGroupcourse(gc.getId());
        List<Grade> g;
        for(Performance p:performances){
            g=mu.getGrades(p);
            for(Grade j:g){
                mu.removeGrade(j.getId());
            }
            mu.removePerformance(p.getStudentId().getId(),p.getGroupcourseid().getId());
        }
        long teachesRemove=gc.getTeachesid().getId();
        long groupRemove=gc.getGroupid().getId();
        mu.removeGroupcourse(teachesRemove,groupRemove);
        Course toRemoveC=gc.getTeachesid().getCourse();
        for(Course i:courses){
            if(i.getName().equals(toRemoveC.getName())){
                courses.remove(i);
                break;
            }
        }
        setupUnassigned();
    }

    public void changeGroup(){
        if(changedGroup!=selectedGroupId){
            Group toChange=mu.getChangeGroup(changedGroup);
            List<Groupcourse> gc=mu.getGroupcoursesByGroup(changedGroup);
            mu.updatePerformancesFinalized(selectedStudent);
            for(Groupcourse i:gc){
                mu.addPerformance(selectedStudent,i.getId());
            }
        }
    }
    private void emptyRedirect(){
        if(user==null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
