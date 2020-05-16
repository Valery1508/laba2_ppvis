package controller;

import javafx.collections.FXCollections;
import model.Teacher;
import model.TeacherFIO;

import java.io.File;
import java.util.List;

public class Controller {

    private List<Teacher> teachers;
    private PageOfTable page;

    public Controller(List<Teacher> list) {
        teachers = list;
        page = new PageOfTable(teachers);
    }

//----------
    public List<Teacher> load(File file){
        LoadController load = new LoadController();
        this.teachers = load.parse(file);
        //System.out.println(teachers);  //выводит null
        page = new PageOfTable(teachers);
        return this.teachers;
    }

    public  int getSize(){
        return  this.teachers.size();
    }

    public void save(File file){
        SaveController save = new SaveController();
        save.parser(file, teachers);
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    public void deleteTeacher(List<Teacher> teacher){
        for(Teacher teach : teacher){
            teachers.remove(teach);
        }
    }

    public List<String> getListOfFaculty(){
        List<String> list= FXCollections.observableArrayList();
        for(Teacher teach: teachers) { list.add(teach.getFaculty()); }
        return list;
    }

    public List<String> getListOfRank(){
        List<String> list= FXCollections.observableArrayList();
        for(Teacher teach: teachers) { list.add(teach.getAcademicRank()); }
        return list;
    }

    public List<String> getListOfDepartment(){
        List<String> list= FXCollections.observableArrayList();
        for(Teacher teach: teachers) { list.add(teach.getDepartment()); }
        return list;
    }

    public List<Teacher> findByDepartment(String department){
        List<Teacher> list= FXCollections.observableArrayList();
        for(Teacher teach: teachers) {
            if (teach.getDepartment().equals(department))
                list.add(teach);
        }
        return list;
    }

    public List<Teacher> findByFacultyAndRank(String faculty, String rank){
        List<Teacher> list= FXCollections.observableArrayList();
        for(Teacher teach: teachers) {
            if (teach.getFaculty().equals(faculty) && teach.getAcademicRank().equals(rank))
                list.add(teach);
        }
        return list;
    }


    public List<Teacher> findByFIO(TeacherFIO teacherFIO){
        List<Teacher> list= FXCollections.observableArrayList();
        for(Teacher teach: teachers) {
            if (teach.getTeacherFIO().getFirstNameTeacher().equals(teacherFIO.getFirstNameTeacher())) {
                list.add(teach);
            } else if (teach.getTeacherFIO().getSecondNameTeacher().equals(teacherFIO.getSecondNameTeacher())) {
                list.add(teach);
            } else if (teach.getTeacherFIO().getOtchestvoTeacher().equals(teacherFIO.getOtchestvoTeacher())) {
                list.add(teach);
            }
        }
        return list;
    }


    public List<Teacher> findBySeniority(int low,int high){
        List<Teacher> list= FXCollections.observableArrayList();
        for(Teacher teach: teachers) {
            if (teach.getSeniority() <= high && teach.getSeniority() >= low )
                list.add(teach);
        }
        return list;
    }

    public List<Teacher> getPage(int numberOfPage, int rowsInPage){
        return page.getPageOfTable(numberOfPage, rowsInPage);
    }

    public int getLastPage(int rowsPerPage){
        return page.calculateLastPage(rowsPerPage);
    }




}
