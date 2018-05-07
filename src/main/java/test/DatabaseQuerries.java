/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test;

import test.database.*;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;


public class DatabaseQuerries implements Serializable {

    @Inject
    private EntityManager entityManager;

    @Inject
    private UserTransaction utx;

    //Get queries

    public Member getForUsername(String username) {
        try {
            Member user;
            try {
                utx.begin();
                Query query = entityManager.createQuery("select u from Member u where u.username = :username");
                query.setParameter("username", username);
                System.out.println("test");
                user = (Member) query.getSingleResult();
            } catch (NoResultException e) {
                user = null;
            }
            utx.commit();
            return user;
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }

    public List<Member> getForRole(Role role) {
        try {
            List <Member> users;
            try {
                utx.begin();
                Query query = entityManager.createQuery("select u from Member u join u.roleId r where r=:role");
                query.setParameter("role", role);
                System.out.println("test");
                users = query.getResultList();
            } catch (NoResultException e) {
                users = null;
            }
            utx.commit();
            return users;
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }
    public Member getForId(Long id) {
        try {
            Member user;
            try {
                utx.begin();
                Query query = entityManager.createQuery("select u from Member u where u.id = :id");
                query.setParameter("id", id);
                System.out.println("test");
                user = (Member) query.getSingleResult();
                //user=null;
            } catch (NoResultException e) {
                user = null;
            }
            utx.commit();
            return user;
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }
    public void createUser(Member user) {
        try {
            try {
                utx.begin();
                entityManager.persist(user);
            } finally {
                utx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }

    public List getGrades(Performance per){
        List grades=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select g from Grade g, in (g.performanceId) p where p=:performance");
            query.setParameter("performance",per);
            grades=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return grades;
    }



    public List getPerformances(Member student){
        List performances=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select p from Performance p, in (p.studentId) m where m=:student");
            query.setParameter("student",student);
            performances=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return performances;
    }



    public List getPerformancesFinished(Member student){
        List performances=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select p from Performance p, in (p.studentId) m where m=:student and p.finished = true");
            query.setParameter("student",student);
            performances=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return performances;
    }

    public List getStudents(){
        List students=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select m from Member m join m.roleId r where r.id=2");
            students=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return students;
    }

    public int getCount(Member student){
        int count=0;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select count(p.id) from Performance p join p.studentId m where m.id=:studentId group by m.id");
            query.setParameter("studentId",student.getId());
            count=(Integer)query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return count;
    }

    public List getAllPerformancesByMember(Member student){
        List performances=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select p from Performance p, in (p.studentId) m where m.id=:student");
            query.setParameter("student",student.getId());
            performances=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return performances;
    }

    public List getPerformancesOngoing(Member student){
        List performances=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select p from Performance p, in (p.studentId) m where m=:student and p.finished = false");
            query.setParameter("student",student);
            performances=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return performances;
    }

    public List getPerformancesByGroupcourse(long gcid){
        List performances=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select p from Performance p join p.groupcourseid gc where gc.id=:gcid");
            query.setParameter("gcid",gcid);
            performances=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return performances;
    }
    public List getPerformancesByGroupcourseActive(long gcid){
        List performances=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select p from Performance p join p.groupcourseid gc where gc.id=:gcid and p.finished=false ");
            query.setParameter("gcid",gcid);
            performances=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return performances;
    }

    public List getPerformancesByGroupcourseFinalized(long gcid){
        List performances=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select p from Performance p join p.groupcourseid gc where gc.id=:gcid and p.finished=true ");
            query.setParameter("gcid",gcid);
            performances=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return performances;
    }
    public Performance getPerformanceByStudentGroupcourse(long studentId,long gcid){
        Performance performance=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select p from Performance p join  p.groupcourseid gc join p.studentId s where gc.id=:gcid and s.id=:studentId ");
            query.setParameter("gcid",gcid);
            query.setParameter("studentId",studentId);
            performance=(Performance)query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return performance;
    }


    public List getGradesByGroupcourse(long gcid){
        List performances=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select sid.username, g.grade from Grade g join g.performanceId p join p.groupcourseid gc join gc.teachesid ti join ti.course c join p.studentId sid where  gc.id=:gcid");
            query.setParameter("gcid",gcid);
            performances=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return performances;
    }

    public List getGradesByStudentGroupcourse(long studentId,long gcid){
        List grades=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select g from Grade g join g.performanceId p join p.groupcourseid gc join p.studentId s where gc.id=:gcid and s.id=:studentId ");
            query.setParameter("gcid",gcid);
            query.setParameter("studentId",studentId);
            grades=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return grades;
    }

    public List getPerformancesYears(Member student){
        List performances=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select y.year ,avg(g.grade) from Grade g join g.performanceId p join p.groupcourseid gi join gi.teachesid ti join ti.yearid y join p.studentId s where s=:student group by y.year ");
            query.setParameter("student",student);
            performances=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return performances;
    }



    public Course getCourse(Performance per){
        Course course=new Course();
        try {
            utx.begin();
            Query query = entityManager.createQuery("select c from Performance p  join  p.groupcourseid gi join gi.teachesid ti join ti.course c where p.id = :id");
            query.setParameter("id",per.getId());
            course=(Course)query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return course;
    }

    public Role getRole(Member user){
        Role role=new Role();
        try {
            utx.begin();
            Query query = entityManager.createQuery("select r from Member m join m.roleId r where m.username = :username");
            query.setParameter("username",user.getUsername());
            role=(Role)query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return role;
    }
    public Role getRoleByname(String role1){
        Role role;
        try {
            utx.begin();
            Query query = entityManager.createQuery("select r from Role r where r.name=:role");
            query.setParameter("role",role1);
            role=(Role)query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return role;
    }

    public List getCoursesByTeacher(long teacherId){
        List courses=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select c  from Teaches tc join tc.course c join tc.memberid t where t.id=:teacherID");
            query.setParameter("teacherID",teacherId);
            courses=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return courses;
    }

    public List getCoursesByTeacherNot(){
        List courses=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select distinct c  from Course c ");
            courses=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return courses;
    }

    public Year yearById(long id){
        Year year=new Year();
        try {
            utx.begin();
            Query query = entityManager.createQuery("select y from Year y where y.id=:yid");
            query.setParameter("yid",id);
            year=(Year) query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return year;
    }

    public List getCoursesByTeacherYear(long teacherId, long yearid){
        List courses=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select c  from Teaches tc join tc.course c join tc.memberid t  join tc.yearid y where t.id=:teacherID and y.id=:yid");
            query.setParameter("teacherID",teacherId);
            query.setParameter("yid",yearid);
            courses=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return courses;
    }



    public Course getCoursesByName(String courseName){
        Course course;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select distinct c  from Course c where c.name=:name");
            query.setParameter("name",courseName);
            course=(Course)query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return course;
    }

    public List getYears(){
        List years=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select y  from Year y ");
            years=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return years;
    }

    public List getGroups(){
        List groups=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select g  from Group g ");
            groups=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return groups;
    }

    public List getGroupsByTeacherYearCourse(long teacherId,long yearId,long courseId){
        List groups=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select g  from Groupcourse gc join gc.groupid g join gc.teachesid tc " +
                    "join tc.memberid m join tc.yearid y join tc.course c where m.id=:teacherId and y.id=:yearId and c.id=:courseId");
            query.setParameter("teacherId",teacherId);
            query.setParameter("yearId",yearId);
            query.setParameter("courseId",courseId);
            groups=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return groups;
    }

    public List getStudentsByGroup(long groupId){
        List students=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select distinct s from Performance p join p.groupcourseid gc join gc.groupid g join p.studentId s where g.id=:gid");
            query.setParameter("gid",groupId);
            students=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return students;
    }

    public List getCoursesByGroup(long groupId){
        List courses=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select c from Groupcourse gc join gc.groupid g join gc.teachesid t join t.course c where g.id=:gid");
            query.setParameter("gid",groupId);
            courses=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return courses;
    }

    public List getTeachersByGroup(Group group){
        List teach=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select t from Groupcourse gc join gc.groupid g join gc.teachesid t where g.id=:gid");
            query.setParameter("gid",group.getId());
            teach=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return teach;
    }


    public Teaches getTeacherByCourseGroupYear(long courseId, long groupId,long yearId){
        Teaches teacher;
        try {
            utx.begin();
            Query query = entityManager.createQuery("select ti from Groupcourse gc join gc.groupid g join gc.teachesid ti join ti.course co  join ti.yearid y where co.id=:cid and g.id=:gid and y.id=:yearId");
            query.setParameter("gid",groupId);
            query.setParameter("cid",courseId);
            query.setParameter("yearId",yearId);
            teacher=(Teaches) query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return teacher;
    }

    public Teaches getTeachesByCourseYearTeacher(long courseId,long yearId,long teacherId){
        Teaches teacher;
        try {
            utx.begin();
            Query query = entityManager.createQuery("select t from Teaches t join t.course c join t.yearid y join t.memberid m where c.id=:cid and y.id=:yid and m.id=:tid");
            query.setParameter("cid",courseId);
            query.setParameter("yid",yearId);
            query.setParameter("tid",teacherId);
            teacher=(Teaches) query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return teacher;
    }

    public Group getGroupById(long id){
        Group group=new Group();
        try {
            utx.begin();
            Query query = entityManager.createQuery("select g from Group g where g.id=:gid");
            query.setParameter("gid",id);
            group=(Group) query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return group;
    }

    public Group getActiveGroup(long studentId){
        Group group=new Group();
        try {
            utx.begin();
            Query query = entityManager.createQuery("select distinct g from Performance p join p.groupcourseid gc join gc.groupid g join p.studentId sid where sid.id=:sid and p.finished=false");
            query.setParameter("sid",studentId);
            group=(Group) query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return group;
    }

    public Group getChangeGroup(long changeId){
        Group group=new Group();
        try {
            utx.begin();
            Query query = entityManager.createQuery("select distinct g from Group g where g.id=:changeId");
            query.setParameter("changeId",changeId);
            group=(Group) query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return group;
    }

    public Teaches getTeaches(Course course, Member teacher){
        Teaches teaches=new Teaches();
        try {
            utx.begin();
            Query query = entityManager.createQuery("select t from Teaches t join t.memberid m join t.course c where m.id=:memberid and c.id=:course");
            query.setParameter("memberid",teacher.getId());
            query.setParameter("course",course.getId());
            teaches=(Teaches)query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return teaches;
    }

    public List getTeachersDataByGroup(Group group){
        List teach=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select c,t,y from Groupcourse gc join gc.groupid g join gc.teachesid tc join tc.memberid t join tc.yearid y join tc.course c where g.id=:gid");
            query.setParameter("gid",group.getId());
            teach=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return teach;
    }

    public List getTeachesByCourseYear(long courseId, long yearId){
        List teach=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select t from Teaches t where t.yearid.id=:yearId and t.course.id=:courseId");
            query.setParameter("yearId",yearId);
            query.setParameter("courseId",courseId);
            teach=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return teach;
    }

    public List getTeachersDataByCourse(long courseId){
        List teach=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select c,t,y,tc from Teaches tc join tc.course c join tc.yearid y join tc.memberid t where c.id=:cid");
            query.setParameter("cid",courseId);
            teach=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return teach;
    }
    public List getTeachersByCourse(long courseId){
        List teachers=null;
        try {
            utx.begin();
            Query query = entityManager.createQuery("select m from Teaches t join t.memberid m join t.course c where c.id=:course");
            query.setParameter("course",courseId);
            teachers=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return teachers;
    }

    public List getTeaches(){
        List teach=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select t from Teaches t");
            teach=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return teach;
    }

    public Groupcourse getGroupcourse(long teachesId,long groupId){
        Groupcourse gc=new Groupcourse();
        try {
            utx.begin();
            Query query = entityManager.createQuery("select gc from Groupcourse gc where gc.groupid.id=:gid and gc.teachesid.id=:tid");
            query.setParameter("gid",groupId);
            query.setParameter("tid",teachesId);
            gc=(Groupcourse)query.getSingleResult();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return gc;
    }

    public List getGroupcoursesByGroup(long groupId){
        List gc=null;
        try {
            utx.begin();
            Query query = entityManager.createQuery("select gc from Groupcourse gc where gc.groupid.id=:gid");
            query.setParameter("gid",groupId);
            gc=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return gc;
    }

    public List getNumberOfPerformancesByStudents(){
        List students=null;
        try {
            utx.begin();
            Query query = entityManager.createQuery("select m,count(p) from Performance p join p.studentId m group by m.id");
            students=query.getResultList();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return students;
    }

    //Update queries

    public void updateUserName(Member user, String userName){
        try {
            utx.begin();
            Query query=entityManager.createQuery("Update Member u set u.username=:username where u.id=:id");
            query.setParameter("username", userName);
            query.setParameter("id",user.getId());
            query.executeUpdate();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }

    }

    public void updateFirstName(Member user,String firstName){
        try {
            utx.begin();
            Query query=entityManager.createQuery("Update Member u set u.firstname=:firstname where u.id=:id");
            query.setParameter("firstname", firstName);
            query.setParameter("id",user.getId());
            query.executeUpdate();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }

    public void updateLasttName(Member user,String lastName){
        try {
            utx.begin();
            Query query=entityManager.createQuery("Update Member u set u.lastname=:lastname where u.id=:id");
            query.setParameter("lastname", lastName);
            query.setParameter("id",user.getId());
            query.executeUpdate();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }
    public void updatepassword(Member user,String password){
        try {
            utx.begin();
            Query query=entityManager.createQuery("Update Member u set u.password=:password where u.id=:id");
            query.setParameter("password", password);
            query.setParameter("id",user.getId());
            query.executeUpdate();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }

    public void updateDateOfBirth(Member user,Date dateOfBirth){
        try {
            utx.begin();
            Query query=entityManager.createQuery("Update Member u set u.dateOfBirth=:dateOfBirth where u.id=:id");
            query.setParameter("dateOfBirth", dateOfBirth);
            query.setParameter("id",user.getId());
            query.executeUpdate();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }

    public void updateGroupcourse(long groupId,long teachesId,long removeId){
        try {
            utx.begin();
            Query query=entityManager.createQuery("Update Groupcourse gc set gc.teachesid.id=:teachesId where gc.groupid.id=:gid and gc.teachesid.id=:removeId");
            query.setParameter("teachesId", teachesId);
            query.setParameter("gid",groupId);
            query.setParameter("removeId",removeId);
            query.executeUpdate();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }

    public void updateGrade(int newGrade,long gradeId){
        try {
            utx.begin();
            Query query=entityManager.createQuery("Update Grade g set g.grade=:newGrade where g.id=:gradeId");
            query.setParameter("newGrade",newGrade);
            query.setParameter("gradeId",gradeId);
            query.executeUpdate();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }

    public void updatePerformancesFinalized(long studentId){
        try {
            utx.begin();
            Query query=entityManager.createQuery("Update Performance p set p.finished=true where p.studentId.id=:studentId");
            query.setParameter("studentId", studentId);
            query.executeUpdate();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }
    public void updatePerformanceFinalized(long performanceId){
        try {
            utx.begin();
            Query query=entityManager.createQuery("Update Performance p set p.finished=true where p.id=:pid");
            query.setParameter("pid", performanceId);
            query.executeUpdate();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }

    public void addCourseToTeacher(Course course, Member teacher,Year year){
        try {
            utx.begin();
            Teaches tch=new Teaches();
            tch.setCourse(course);
            tch.setMemberid(teacher);
            tch.setYearid(year);
            entityManager.persist(tch);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }



    public Groupcourse addGroupcourse(long teachesId, long groupId){
        Groupcourse gc;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select t from Teaches t where t.id=:tid");
            query.setParameter("tid",teachesId);
            Teaches teaches=(Teaches)query.getSingleResult();
            gc=new Groupcourse();
            gc.setTeachesid(teaches);
            Query query2=entityManager.createQuery("select g from Group g where g.id=:gid");
            query2.setParameter("gid",groupId);
            Group group=(Group)query2.getSingleResult();
            gc.setGroupid(group);
            entityManager.persist(gc);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return gc;
    }

    public Groupcourse removeGroupcourse(long teachesId,long groupId){
        Groupcourse gc;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select gc from Groupcourse gc where gc.teachesid.id=:tid and gc.groupid.id=:gid");
            query.setParameter("tid",teachesId);
            query.setParameter("gid",groupId);
            gc=(Groupcourse)query.getSingleResult();
            entityManager.remove(gc);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return gc;
    }

    public void removeTeacherFromCourse(Course course, Member teacher,Year year){
        try {
            utx.begin();
            Teaches teaches;
            Query query = entityManager.createQuery("select t from Teaches t join t.memberid m join t.course c join t.yearid y where m.id=:memberid and c.id=:course and y.id=:yid");
            query.setParameter("memberid",teacher.getId());
            query.setParameter("course",course.getId());
            query.setParameter("yid",year.getId());
            teaches=(Teaches)query.getSingleResult();
            entityManager.remove(teaches);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }

    public Performance addPerformance(long studentId,long groupcourseId){
        Performance perf;
        try {
            utx.begin();
            perf=new Performance();
            Query query=entityManager.createQuery("select s from Member s where s.id=:sid");
            query.setParameter("sid",studentId);
            Member student=(Member)query.getSingleResult();
            perf.setStudentId(student);
            Query query2=entityManager.createQuery("select g from Groupcourse g where g.id=:gid");
            query2.setParameter("gid",groupcourseId);
            Groupcourse groupc=(Groupcourse)query2.getSingleResult();
            perf.setGroupcourseid(groupc);
            entityManager.persist(perf);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return perf;
    }
    public Performance removePerformance(long studentId,long groupcourseId){
        Performance perf;
        try {
            utx.begin();
            perf=new Performance();
            Query query=entityManager.createQuery("select p from Performance p where p.studentId.id=:sid and p.groupcourseid.id=:gid");
            query.setParameter("gid",groupcourseId);
            query.setParameter("sid",studentId);
            perf=(Performance)query.getSingleResult();
            entityManager.remove(perf);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return perf;
    }

    public Grade addGrade(Performance perfId,int value){
        Grade grade=null;
        try {
            utx.begin();
            grade=new Grade();
            grade.setGrade(value);
            Query query=entityManager.createQuery("select p from Performance p where p.id=:pid");
            query.setParameter("pid",perfId.getId());
            Performance perf=(Performance)query.getSingleResult();
            grade.setPerformanceId(perf);
            entityManager.persist(grade);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return grade;
    }

    public Grade removeGrade(long gradeId){
        Grade grade;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select g from Grade g where g.id=:gid");
            query.setParameter("gid",gradeId);
            grade=(Grade)query.getSingleResult();
            entityManager.remove(grade);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return grade;
    }

    public void unfinalize(Performance p){
        try {
            utx.begin();
            Query query=entityManager.createQuery("Update Performance p set p.finished=false where p.id=:pid");
            query.setParameter("pid", p.getId());
            query.executeUpdate();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }

    public Group addGroup(String name,int year){
        Group group;
        try {
            utx.begin();
            group=new Group();
            group.setName(name);
            group.setYear(year);
            entityManager.persist(group);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return group;
    }

    public Course addCourse(String name,String description){
        Course course;
        try {
            utx.begin();
            course=new Course();
            course.setName(name);
            course.setDescription(description);
            entityManager.persist(course);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return course;
    }

    public Year addYear(String yearname,Date startDate,Date endDate){
        Year year;
        try {
            utx.begin();
            year=new Year();
            year.setYear(yearname);
            year.setStartDate(startDate);
            year.setEndDate(endDate);
            entityManager.persist(year);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
        return year;
    }


}
