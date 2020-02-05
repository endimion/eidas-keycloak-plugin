/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.uaegean.pojo;

/**
 *
 * @author nikos
 */
public class Country   {

    private String name;
    private String code;
    private boolean enabled;
 
 
    public Country(String name, String code, boolean enabled){
        this.name = name;
        this.code = code;
        this.enabled = enabled;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
