package com.example.bulbapp.bulbapplib.models.wiz;

public class WizLight {
    private Variables variables;
    private String query;

    public WizLight(Variables variables, String query) {
        this.variables = variables;
        this.query = query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Variables getVariables() {
        return variables;
    }

    public String getQuery() {
        return query;
    }

    public static String getLightOnQuery(){
        return "mutation ($r: Int!, $g: Int!, $b: Int!, $c: Int!, $w: Int!, $lightId: String!, $provider: String!, $macAddress: String) {\n  pilotLight(lightId: $lightId, provider: $provider, mac: $macAddress, state: {state: true, r: $r, g: $g, b: $b, cw: $c, ww: $w})\n}\n";
    }

    public static String getLightOffQuery(){
        return "mutation ($status: Boolean!, $lightId: String!, $provider: String!, $macAddress: String) {\n  pilotLight(lightId: $lightId, provider: $provider, mac: $macAddress, state: {state: $status})\n}\n";
    }
}
