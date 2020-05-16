package model;

public class Teacher {
    private String faculty, department;
    private TeacherFIO teacherFIO;
    private String academicRank, academicDegree;
    private int seniority;

    public Teacher() {
        this.faculty = null;
        this.department = null;
        this.teacherFIO = null;
        this.academicRank = null;
        this.academicDegree = null;
        this.seniority = 0;
    }

    public Teacher(String faculty, String department, TeacherFIO teacherFIO, String academicRank, String academicDegree, int seniority) {
        this.faculty = faculty;
        this.department = department;
        this.teacherFIO = teacherFIO;
        this.academicRank = academicRank;
        this.academicDegree = academicDegree;
        this.seniority = seniority;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public TeacherFIO getTeacherFIO() {
        return teacherFIO;
    }

    public void setTeacherFIO(TeacherFIO teacherFIO) {
        this.teacherFIO = teacherFIO;
    }

    public String getAcademicRank() {
        return academicRank;
    }

    public void setAcademicRank(String academicRank) {
        this.academicRank = academicRank;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }
}
