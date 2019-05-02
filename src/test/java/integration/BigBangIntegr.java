package integration;

import org.junit.Before;
import org.junit.Test;
import repository.*;
import service.Service;
import service.ServiceModified;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.util.Iterator;

public class BigBangIntegr {
    //    Service service;
    ServiceModified service;

    @Before
    public void setup() {

//        StudentXMLRepository studentRepository = new StudentXMLRepository(new StudentValidator(),"src/studenti.xml");
//        TemaXMLRepository assignmentRepository = new TemaXMLRepository(new TemaValidator(), "src/teme.xml");
//        NotaXMLRepository gradeRepository = new NotaXMLRepository(new NotaValidator(), "src/note.xml");
//        service = new Service(studentRepository, assignmentRepository, gradeRepository);

//        ------------- tests fail with the existing service implementation -------------
//        ------------- so we will be using the modified version of the service ---------
        StudentRepository studentRepository = new StudentRepository(new StudentValidator());
        TemaRepository assignmentRepository = new TemaRepository(new TemaValidator());
        NotaRepository gradeRepository = new NotaRepository(new NotaValidator());
        service = new ServiceModified(studentRepository, assignmentRepository, gradeRepository);
    }

    @Test
    public void addStudentTestBigBangApproach() {
        service.saveStudent("1","Prisacariu Alex", 936);
        Iterable values = service.findAllStudents();
        assert getIterableSize(values)==1;
    }

    @Test
    public void addAssignmentTestBigBangApproach() {
        service.saveTema("1","description", 13, 12);
        Iterable values = service.findAllTeme();
        assert getIterableSize(values)==1;
    }

    @Test
    public void addGradeTestBigBangApproach() {
        service.saveStudent("1","Prisacariu Alex", 936);
        service.saveTema("1","description", 13, 12);
        service.saveNota("1", "1", 9.5, 13, "well done");
        Iterable values = service.findAllNote();
        assert getIterableSize(values)==1;
    }

    @Test
    public void allAddFeaturesIntegrationTest() {
        service.saveStudent("1","Prisacariu Alex", 936);
        service.saveTema("1","description", 13, 12);
        service.saveNota("1", "1", 9.5, 13, "well done");
        Iterable students = service.findAllStudents();
        Iterable assignments = service.findAllTeme();
        Iterable grades = service.findAllNote();
        assert getIterableSize(students)==1 && getIterableSize(assignments)==1 && getIterableSize(grades)==1;
    }

    public int getIterableSize(Iterable values) {
        Iterator it = values.iterator();
        int nr=0;
        while (it.hasNext()) {
            it.next();
            nr++;
        }
        return nr;
    }

}
