package com.example.studentdashboard;

import java.io.Serializable;

public class StudentInfoDomain implements Serializable {
    String username,useremail,userdepartment,usersemester,userdivision,userid;

    public StudentInfoDomain() {
    }

    public StudentInfoDomain(String name, String email, String dept, String semester, String divi, String id) {
        this.useremail = email;
        this.usersemester = semester;
        this.username = name;
        this.userdepartment = dept;
        this.userdivision=divi;
        this.userid=id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserdepartment() {
        return userdepartment;
    }

    public void setUserdepartment(String userdepartment) {
        this.userdepartment = userdepartment;
    }

    public String getUsersemester() {
        return usersemester;
    }

    public void setUsersemester(String usersemester) {
        this.usersemester = usersemester;
    }

    public String getUserdivision() {
        return userdivision;
    }

    public void setUserdivision(String userdivision) {
        this.userdivision = userdivision;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


}
