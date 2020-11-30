package com.example.schedule_manager.adminUI;

public class CustomListAdminEmployees {

    private String id,firstName,lastName,birthD,eMail,phone,job;


    public CustomListAdminEmployees(String id,String firstName, String lastName, String birthD, String eMail, String phone, String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthD = birthD;
        this.eMail = eMail;
        this.phone = phone;
        this.job = job;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthD() {
        return birthD;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPhone() {
        return phone;
    }

    public String getJob() {
        return job;
    }

    public String getId() {
        return id;
    }
}
