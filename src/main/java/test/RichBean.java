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

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

/**
 * <p>
 * {@link RichBean} is the JSF backing bean for the application, holding the input data to be
 * re-displayed.
 * </p>
 */
@Named
@SessionScoped
public class RichBean implements Serializable {

    private static final long serialVersionUID = -6239437588285327644L;

    private String name1;

    FacesContext fc = FacesContext.getCurrentInstance();
    ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();


    @PostConstruct
    public void postContruct() {
        name1 = "John";

    }

    public String getName1() {
        return name1;
    }


    public void setName1() {
        this.name1 = "John";
    }
    public void setName2() {
        this.name1 = "Kris";
    }
    public void setName3() {
        this.name1 = "Tina";
    }

    public void openAdmin(){
        nav.performNavigation("admin.xhtml");
    }
    public void openStudent(){
        nav.performNavigation("student.xhtml");
    }
    public void openTeacher(){
        nav.performNavigation("teacher.xhtml");
    }
}
