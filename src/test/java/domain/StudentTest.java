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
        studentRepository = new StudentRepository(new StudentValidator()); }

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

    @Test
    public void shouldAddStudentWithValidGroupNr(){
        Student studentWithValidGroup = new Student("1", "Cosmina", 936);
        studentRepository.save(studentWithValidGroup);

        assert studentRepository.findOne("1").equals(studentWithValidGroup);
    }

    @Test
    public void shouldNotAddStudentWithInvalidGroupNr() {
        Student studentWithGroupLessThanInterval = new Student("1", "Cosmina", 5);
        Student st1 = studentRepository.save(studentWithGroupLessThanInterval);
        Student studentWithGroupLargerThanInterval = new Student("2", "Andreea", 1009);
        Student st2 = studentRepository.save(studentWithGroupLargerThanInterval);

        assert st1 == null;
        assert st2 == null;
    }

    @Test
    public void shouldNotAddStudentWithNullID() {
        Student studentWithNullID = new Student(null, "Cosmina", 936);
        Student st1 = studentRepository.save(studentWithNullID);

        assert st1 == null;
    }

    @Test
    public void shouldNotAddStudentWithEmptyStringID() {
        Student studentWithEmptyID = new Student("", "Cosmina", 936);
        Student st1 = studentRepository.save(studentWithEmptyID);

        assert st1 == null;
    }

    @Test
    public void shouldAddStudentWithValidID() {
        Student studentWithValidID = new Student("1", "Cosmina", 936);
        studentRepository.save(studentWithValidID);

        assert studentRepository.findOne("1").equals(studentWithValidID);
    }

    @Test
    public void shouldNotAddStudentWithNullName() {
        Student studentWithNullName = new Student("1", null, 936);
        Student st1 = studentRepository.save(studentWithNullName);

        assert st1 == null;
    }

    @Test
    public void shouldNotAddStudentWithEmptyStringName() {
        Student studentWithEmptyName = new Student("1", "", 936);
        Student st1 = studentRepository.save(studentWithEmptyName);

        assert st1 == null;
    }

    @Test
    public void shouldAddStudentWithValidName() {
        Student studentWithValidName = new Student("1", "Cosmina", 936);
        studentRepository.save(studentWithValidName);

        assert studentRepository.findOne("1").equals(studentWithValidName);
    }
}
