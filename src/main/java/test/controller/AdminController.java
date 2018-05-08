package test.controller;

import test.Helper;
import test.DatabaseQuerries;
import test.Session;
import test.database.*;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
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
public class AdminController  implements Serializable {
    @Inject
    private DatabaseQuerries mu;

    @Inject
    private Session session;

    private Member user;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private Date dateOfBirth;

    private String hello;

    private String roleChoices;

    private Member teacher;

    private String teacherValue;

    private Course course;
    private List <Member>teachers;

    private Member editedTeacher;

    private List<Course> courseData;

    private String selectedCourse;

    private List<Course> addCourseData;

    private String addCourse;
    private List<Year> years;

    private long selectedYear;
    //FacesContext fc = FacesContext.getCurrentInstance();
    //ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();

    @PostConstruct
    public void postConstruct(){
        roleChoices="";
        user=session.getUser();
        emptyRedirect();
        checkRole();
        this.hello=user.getFirstName()+" "+user.getLastName();
        getTeachers1();
    }

    public Member getTeacher() {
        return teacher;
    }

    public void setTeacher(Member teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getRoleChoices() {
        return roleChoices;

    }

    public void setRoleChoices(String roleChoices) {
        this.roleChoices = roleChoices;
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
        this.userName = userName;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password) {
        if(password.equals("")){
           this.password = Helper.getSHA(password, userName);
        }
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

    public String getTeacherValue() {
        return teacherValue;
    }

    public void setTeacherValue(String teacherValue) {
        this.teacherValue = teacherValue;
    }

    private void getTeachers1(){
        teachers=mu.getForRole(mu.getRoleByname("Teacher"));
        years=mu.getYears();

    }

    public List getTeachers() {
        return teachers;
    }

    public void setTeachers(List teachers) {
        this.teachers = teachers;
    }

    public Member getEditedTeacher() {
        return editedTeacher;
    }

    public void setEditedTeacher(Member editedTeacher) {
        this.editedTeacher = editedTeacher;
    }

    public List<Course> getCourseData() {
        return courseData;
    }

    public void setCourseData(List<Course> courseData) {
        this.courseData = courseData;
    }

    public String getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public List<Course> getAddCourseData() {
        return addCourseData;
    }

    public void setAddCourseData(List<Course> addCourseData) {
        this.addCourseData = addCourseData;
    }

    public String getAddCourse() {
        return addCourse;
    }

    public void setAddCourse(String addCourse) {
        this.addCourse = addCourse;
    }

    public List<Year> getYears() {
        return years;
    }

    public void setYears(List<Year> years) {
        this.years = years;
    }

    public long getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(long selectedYear) {
        this.selectedYear = selectedYear;
    }

    public void setupEditedTeacher(){
        editedTeacher=mu.getForUsername(teacherValue);
        firstName=editedTeacher.getFirstName();
        lastName=editedTeacher.getLastName();
        userName=editedTeacher.getUsername();
        password=editedTeacher.getPassword();
        dateOfBirth=editedTeacher.getDateOfBirth();

        courseData=mu.getCoursesByTeacherYear(editedTeacher.getId(),selectedYear);

        ArrayList<Course> indices=new ArrayList<Course>();
        addCourseData=mu.getCoursesByTeacherNot();
        for(Course c: courseData){
            for(Course cd:addCourseData){
                if(c.getName().equals(cd.getName())){
                    indices.add(cd);
                }
            }
        }

        for(Course i:indices){
            addCourseData.remove(i); //Optimize this part
        }
    }

    public void addUser(){
        Member user=new Member();
        user.setPassword(password);
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        user.setRoleId(mu.getRoleByname(roleChoices));
        mu.createUser(user);
    }

    public void courseAdd(){
        Member teacher=mu.getForId(editedTeacher.getId());
        Course course=mu.getCoursesByName(addCourse);
        Year year=mu.yearById(selectedYear);
        mu.addCourseToTeacher(course,teacher,year);
        Course toAdd=null;
        for(Course c:addCourseData){
            if(c.getName().equals(addCourse)){
                toAdd=c;
                addCourseData.remove(toAdd);
                break;
            }
        }
        courseData.add(toAdd);
    }

    public void courseRemove(){
        Member teacher=mu.getForId(editedTeacher.getId());
        Course course=mu.getCoursesByName(selectedCourse);
        Year year=mu.yearById(selectedYear);
        mu.removeTeacherFromCourse(course,teacher,year);
        Course toRemove=null;
        for(Course c:courseData){
            if(c.getName().equals(selectedCourse)){
                toRemove=c;
                courseData.remove(toRemove);
                break;
            }
        }
        addCourseData.add(toRemove);
    }

    public void updateTeacher(){
        mu.updateUserName(editedTeacher,userName);
        mu.updatepassword(editedTeacher,password);
        mu.updateFirstName(editedTeacher,firstName);
        mu.updateLasttName(editedTeacher,lastName);
        mu.updateDateOfBirth(editedTeacher,dateOfBirth);
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

    private void checkRole(){
        if(!user.getRoleId().getName().equals("Admin")){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("warning.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void logout(){
        this.session.setUser(null);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //session1.invalidate();

    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public String getHello(){
        return this.hello;
    }

    public void goToTeacher(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("editteacher.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToStudent(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("editstudent.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToAdduser(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("adduser.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToEdit(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("edit.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void back(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("admin1.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
