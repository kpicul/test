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



import test.database.Member;
import test.database.Role;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class GetController {

    @Inject
    private ManagedUser cu;

    private String username;

    private String password;


    FacesContext fc = FacesContext.getCurrentInstance();
    ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();

    public void greet() {
        Member user = cu.getForUsername(username);
        System.out.println(password);
        boolean checkPassword=Helper.checkPass(user,password);
        if (user != null && checkPassword) {
            System.out.println("42");
            Role role=cu.getRole(user);
            if(role.getName().equals("Admin")){
                nav.performNavigation("admin.xhtml");
            }
            else if(role.getName().equals("Teacher")){
                nav.performNavigation("teacher.xhtml");
            }
            else if(role.getName().equals("Student")){
                nav.performNavigation("student.xhtml");
            }

            else{
                System.out.println("Can't find role");
            }
        } else {
            String message = "Error";
            fc.addMessage(null, new FacesMessage(message));
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String pass){
        System.out.println(pass);
        this.password=pass; //set to Hash later
    }

    public String getPassword(){
        return password;
    }




}
