
package test;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named
@SessionScoped
public class Converter implements Serializable {

    FacesContext fc = FacesContext.getCurrentInstance();
    ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();

    private String plain;
    private String converted;

    @PostConstruct
    public void postContruct() {
        plain = "one";
        convert();
       // converted=Helper.toHash(plain);
    }

    public String getPlain() {
        return plain;
    }

    public void setPlain(String plain) {
        this.plain = plain;
    }
    public String getConverted(){
        System.out.println(converted);
        return this.converted;
    }
    public void setConverted(String converted){
        this.converted=Helper.convertr(this.plain);
    }

    public void convert(){
        this.converted=Helper.convertr(plain);
    }
}
