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
import java.sql.Date;
import java.util.List;


public class ManagedUser {

    @Inject
    private EntityManager entityManager;

    @Inject
    private UserTransaction utx;

    public Member getForUsername(String username) {
        try {
            Member user;
            //String jedi="jedi";
            try {
                utx.begin();
                Query query = entityManager.createQuery("select u from Member u where u.username = :username");
                query.setParameter("username", username);
                //query.setParameter("jedi", jedi);
                //query.getSingleResult();
                System.out.println("test");
                user = (Member) query.getSingleResult();
                //user=null;
            } catch (NoResultException e) {
                //throw new RuntimeException(e);
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
    public Member getForId(Long id) {
        try {
            Member user;
            //String jedi="jedi";
            try {
                utx.begin();
                Query query = entityManager.createQuery("select u from Member u where u.id = :id");
                query.setParameter("id", id);http://localhost:8080/appl-1.0-SNAPSHOT/edit.xhtml
                //query.setParameter("jedi", jedi);
                //query.getSingleResult();
                System.out.println("test");
                user = (Member) query.getSingleResult();
                //user=null;
            } catch (NoResultException e) {
                //throw new RuntimeException(e);
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
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
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
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
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
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        return performances;
    }

    public List getGradesPerformance(Member student){
        List performances=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select c.name, g.grade from Grade g join g.performanceId p join p.cdates_id cd join cd.courseId c join p.studentId sid where  sid=:student and p.finished=false");
            query.setParameter("student",student);
            performances=query.getResultList();
            utx.commit();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        return performances;
    }

    public List getPerformancesYears(Member student){
        List performances=null;
        try {
            utx.begin();
            Query query=entityManager.createQuery("select y.year ,avg(g.grade) from Grade g join g.performanceId p join p.cdates_id c join c.yearId y join p.studentId s where s=:student group by y.year ");
            query.setParameter("student",student);
            performances=query.getResultList();
            utx.commit();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        return performances;
    }



    public Course getCourse(Performance per){
        Course course=new Course();
        try {
            utx.begin();
            Query query = entityManager.createQuery("select cd from Performance p  join  p.cdates_id c join c.courseId cd where p.id = :id");
            query.setParameter("id",per.getId());
            course=(Course)query.getSingleResult();
            utx.commit();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }catch (NoResultException e){
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
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }catch (NoResultException e){
            throw new RuntimeException(e);
        }
        return role;
    }

    public void updateUserName(Member user, String userName){
        try {
            utx.begin();
            System.out.println("UPDATE");
            System.out.println(user.getId());
            System.out.println(userName);
            Query query=entityManager.createQuery("Update Member u set u.username=:username where u.id=:id");
            query.setParameter("username", userName);
            query.setParameter("id",user.getId());
            query.executeUpdate();
            utx.commit();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
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
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
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
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
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
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
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
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }
    }




}
