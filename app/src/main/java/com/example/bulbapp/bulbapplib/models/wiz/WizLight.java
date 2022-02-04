package com.example.bulbapp.bulbapplib.models.wiz;

public class WizLight {
    private String operationName;
    private Variables variables;
    private String query;

    public WizLight(String operationName, Variables variables, String query) {
        this.operationName = operationName;
        this.variables = variables;
        this.query = query;
    }

    public String getOperationName() {
        return operationName;
    }

    public Variables getVariables() {
        return variables;
    }

    public String getQuery() {
        return query;
    }
}
