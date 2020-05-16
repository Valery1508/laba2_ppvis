package model;

public class TeacherFIO {
    private String firstNameTeacher, secondNameTeacher, otchestvoTeacher;

    public TeacherFIO(String firstNameTeacher, String secondNameTeacher, String otchestvoTeacher) {
        this.firstNameTeacher = firstNameTeacher;
        this.secondNameTeacher = secondNameTeacher;
        this.otchestvoTeacher = otchestvoTeacher;
    }

    public TeacherFIO() {
        firstNameTeacher = null;
        secondNameTeacher = null;
        otchestvoTeacher = null;
    }

    public String getFirstNameTeacher() {
        return firstNameTeacher;
    }

    public void setFirstNameTeacher(String firstNameTeacher) {
        this.firstNameTeacher = firstNameTeacher;
    }

    public String getSecondNameTeacher() {
        return secondNameTeacher;
    }

    public void setSecondNameTeacher(String secondNameTeacher) {
        this.secondNameTeacher = secondNameTeacher;
    }

    public String getOtchestvoTeacher() {
        return otchestvoTeacher;
    }

    public void setOtchestvoTeacher(String otchestvoTeacher) {
        this.otchestvoTeacher = otchestvoTeacher;
    }
}
