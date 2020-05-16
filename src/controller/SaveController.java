package controller;

import model.Teacher;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class SaveController {

    public void parser(File file, List<Teacher> list){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();
            Element rootElement = doc.createElementNS("","teachers");
            doc.appendChild(rootElement);

            for(Teacher teacher: list)
                rootElement.appendChild(getTeach(doc, teacher));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult file1 = new StreamResult(file);
            transformer.transform(source, file1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static Node getTeach(Document doc, Teacher teacher) {
        Element language = doc.createElement("teacher");

        language.appendChild(getTeacherElements(doc,  "faculty", teacher.getFaculty()));
        language.appendChild(getTeacherElements(doc,  "department", teacher.getDepartment()));
        language.appendChild(getTeacherElements(doc,  "firstNameTeacher", teacher.getTeacherFIO().getFirstNameTeacher()));
        language.appendChild(getTeacherElements(doc,  "secondNameTeacher", teacher.getTeacherFIO().getSecondNameTeacher()));
        language.appendChild(getTeacherElements(doc,  "otchestvoTeacher", teacher.getTeacherFIO().getOtchestvoTeacher()));
        language.appendChild(getTeacherElements(doc,  "academicRank", teacher.getAcademicRank()));
        language.appendChild(getTeacherElements(doc,  "academicDegree", teacher.getAcademicDegree()));
        language.appendChild(getTeacherElements(doc,  "seniority", String.valueOf(teacher.getSeniority())));

        return language;
    }



    private static Node getTeacherElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}
