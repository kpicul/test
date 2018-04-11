
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
    private String username;
    private String converted;

    @PostConstruct
    public void postContruct() {
        plain = "one";
        username="test";
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
        return this.converted;
    }
    public void setConverted(String converted){
        this.converted=Helper.getSHA(this.plain,this.username);
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public void convert(){
        this.converted=Helper.getSHA(plain,username);
    }
}
