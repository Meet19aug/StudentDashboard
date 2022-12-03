package com.example.studentdashboard;
import java.io.Serializable;
public class StudentNoticeDomain  implements Serializable {

    String email,semester,msgTitle,message;
    int id;

    public StudentNoticeDomain() {
    }

    public StudentNoticeDomain(String email, String semester, String msgTitle, String message) {
        this.email = email;
        this.semester = semester;
        this.msgTitle = msgTitle;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
}


}
