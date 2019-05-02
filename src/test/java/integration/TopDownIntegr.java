package integration;

import org.junit.Before;
import org.junit.Test;
import repository.NotaRepository;
import repository.StudentRepository;
import repository.TemaRepository;
import service.ServiceModified;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.util.Iterator;

public class TopDownIntegr {
    ServiceModified service;

    @Before
    public void setup() {
        StudentRepository studentRepository = new StudentRepository(new StudentValidator());
        TemaRepository assignmentRepository = new TemaRepository(new TemaValidator());
        NotaRepository gradeRepository = new NotaRepository(new NotaValidator());
        service = new ServiceModified(studentRepository, assignmentRepository, gradeRepository);
    }

    @Test
    public void addStudentTestTopDownApproach() {
        service.saveStudent("1","Prisacariu Alex", 936);
        Iterable values = service.findAllStudents();
        assert getIterableSize(values)==1;
    }

    @Test
    public void addAssignmentTestTopDownApproach() {
        service.saveStudent("1","Prisacariu Alex", 936);
        service.saveTema("1","description", 13, 12);
        Iterable students = service.findAllStudents();
        Iterable assignments = service.findAllTeme();
        assert getIterableSize(students) == 1 && getIterableSize(assignments) == 1;
    }

    @Test
    public void addGradeTestTopDownApproach() {
        service.saveStudent("1","Alexandra Muscat", 936);
        service.saveTema("1","description", 13, 12);
        service.saveNota("1", "1", 9.5, 13, "well done");
        Iterable students = service.findAllStudents();
        Iterable assignments = service.findAllTeme();
        Iterable values = service.findAllNote();
        assert getIterableSize(students) == 1 && getIterableSize(assignments) == 1 && getIterableSize(values) == 1;
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
