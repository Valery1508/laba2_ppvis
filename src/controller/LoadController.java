package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Teacher;
import model.TeacherFIO;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sun.rmi.server.LoaderHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.time.LocalDate;

public class LoadController {

    public ObservableList<Teacher> parse(File file) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();

            LoaderHandler handler = new LoaderHandler();
            saxParser.parse(file, handler);
            return handler.getTeachers();
        }
        catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    private static class LoaderHandler extends DefaultHandler {

        private boolean boolfaculty = false;
        private boolean booldepartment = false;
        private boolean boolfirstNameTeacher = false;
        private boolean boolsecondNameTeacher = false;
        private boolean boolotchestvoTeacher = false;
        private boolean boolacademicRank = false;
        private boolean boolacademicDegree = false;
        private boolean boolseniority = false;

        private ObservableList<Teacher> teachers = null;

        private Teacher teacher = null;
        private TeacherFIO teacherFIO = null;
        private String data;

        public ObservableList<Teacher> getTeachers() {
            return teachers;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    // логика реакции на начало элемента
            if(qName.equalsIgnoreCase("teacher")) {
                teacher = new Teacher();
                teacherFIO = new TeacherFIO();
                if(teachers == null) {
                    teachers = FXCollections.observableArrayList();
                }
            }
            else if (qName.equalsIgnoreCase("faculty")) {
                boolfaculty = true;
            }
            else if (qName.equalsIgnoreCase("department")) {
                booldepartment = true;
            }
            else if (qName.equalsIgnoreCase("firstNameTeacher")) {
                boolfirstNameTeacher = true;
            }
            else if (qName.equalsIgnoreCase("secondNameTeacher")) {
                boolsecondNameTeacher = true;
            }
            else if (qName.equalsIgnoreCase("otchestvoTeacher")) {
                boolotchestvoTeacher = true;
            }
            else if (qName.equalsIgnoreCase("academicRank")) {
                boolacademicRank = true;
            }
            else if (qName.equalsIgnoreCase("academicDegree")) {
                boolacademicDegree = true;
            }
            else if (qName.equalsIgnoreCase("seniority")) {
                boolseniority = true;
            }

            data = "";
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
    // логика реакции на конец элемента
            if(boolfaculty) {
                teacher.setFaculty(data);
                boolfaculty = false;
            }
            else if (booldepartment) {
                teacher.setDepartment(data);
                booldepartment = false;
            }
            else if (boolfirstNameTeacher) {
                teacherFIO.setFirstNameTeacher(data);
                boolfirstNameTeacher = false;
            }
            else if (boolsecondNameTeacher) {
                teacherFIO.setSecondNameTeacher(data);
                boolsecondNameTeacher = false;
            }
            else if (boolotchestvoTeacher) {
                teacherFIO.setOtchestvoTeacher(data);
                boolotchestvoTeacher = false;
            }
            else if (boolacademicRank) {
                teacher.setAcademicRank(data);
                boolacademicRank = false;
            }
            else if (boolacademicDegree) {
                teacher.setAcademicDegree(data);
                boolacademicDegree = false;
            }
            else if (boolseniority) {
                teacher.setSeniority(Integer.parseInt(data));
                boolseniority = false;
            }

            if (qName.equalsIgnoreCase("teacher")) {
                teacher.setTeacherFIO(teacherFIO);
                teachers.add(teacher);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
    //  логика реакции на текст между элементами
            data = new String(ch, start, length);

        }
    }

}
