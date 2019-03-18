package domain;

import org.junit.Before;
import org.junit.Test;
import repository.StudentRepository;
import validation.StudentValidator;

import java.util.Iterator;

public class StudentTest {
    StudentRepository studentRepository;

    @Before
    public void setup() {
        studentRepository = new StudentRepository(new StudentValidator());
    }

    @Test
    public void shouldSaveNewStudentWithValidId() {
        Student student = new Student("1","Prisacariu", 936);
        studentRepository.save(student);
        assert studentRepository.findOne("1").equals(student);
    }

    @Test
    public void shouldNotAddStudentIfAlreadyPresent() {
        Student student1 = new Student("1","Prisacariu", 936);
        studentRepository.save(student1);
        Student student2 = new Student("1","Alt Prisacariu", 936);
        studentRepository.save(student2);
        Iterable values = studentRepository.findAll();
        Iterator it = values.iterator();
        int nr=0;
        while (it.hasNext()) {
            it.next();
            nr++;
        }
        assert nr==1;
    }

}
