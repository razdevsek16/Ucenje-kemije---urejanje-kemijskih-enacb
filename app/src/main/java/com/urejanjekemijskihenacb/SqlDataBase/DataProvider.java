package com.urejanjekemijskihenacb.SqlDataBase;

/**
 * Created by Aljaz on 16. 01. 2017.
 */

public class DataProvider {
    private String name;
    private String equation;
    private String description;

    public DataProvider(String name, String equation, String description){
        this.name=name;
        this.equation=equation;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
