package controller;

import model.Teacher;

import java.util.List;

public class PageOfTable {
    private List<Teacher> listOfTeachers;

    PageOfTable(List<Teacher> list){
        listOfTeachers=list;
    }

    List<Teacher> getPageOfTable(int pageNumber,int rowsPerPage){
        try {
            return listOfTeachers.subList((pageNumber - 1) * rowsPerPage, pageNumber * rowsPerPage);
        }catch (Exception e){
            return listOfTeachers.subList((pageNumber - 1) * rowsPerPage, listOfTeachers.size());
        }
    }

    public int calculateLastPage(int rowsPerPage){
        int lastPage;
        if(listOfTeachers.size()%rowsPerPage!=0){
            lastPage = listOfTeachers.size()/rowsPerPage+1;
        } else lastPage = listOfTeachers.size()/rowsPerPage;
        return lastPage;
    }
}
