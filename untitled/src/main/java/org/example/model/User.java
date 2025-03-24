package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private String age;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("marital_status")
    private String marital_status;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("reason_for_counseling")
    private String reason_for_counseling;

    @JsonProperty("cbt_technique")
    private String cbt_technique;

    public User(String name, String age, String gender, String marital_status, String occupation, String reason_for_counseling, String cbt_technique) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.marital_status = marital_status;
        this.occupation = occupation;
        this.reason_for_counseling = reason_for_counseling;
        this.cbt_technique = cbt_technique;
    }
}
