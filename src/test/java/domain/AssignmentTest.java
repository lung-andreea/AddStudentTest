package domain;

import org.junit.Before;
import org.junit.Test;
import repository.TemaRepository;
import validation.TemaValidator;

import java.util.Iterator;

public class AssignmentTest {
    TemaRepository assignmentRepo;

    @Before
    public void setup() {
        assignmentRepo = new TemaRepository(new TemaValidator());
    }

    @Test
    public void shouldAddValidAssignment() {
        Tema assignment = new Tema("1","Lab03/SSVV",13,1);
        assignmentRepo.save(assignment);
        assert assignmentRepo.findOne("1").equals(assignment);
    }

    @Test
    public void shouldNotAddAssigmentWithDeadlineBeforeStartline() {
        Tema assignment = new Tema("1","Lab03/SSVV",5,8);
        assignmentRepo.save(assignment);
        Iterable values = assignmentRepo.findAll();
        Iterator it = values.iterator();
        int nr=0;
        while (it.hasNext()) {
            it.next();
            nr++;
        }
        assert nr==0;
    }

    @Test
    public void shouldNotAddAssignmentWithNullID() {
        Tema assignmentWithNullID = new Tema(null,"Lab03/SSVV",8,5);
        Tema st1 = assignmentRepo.save(assignmentWithNullID);

        assert st1 == null;
    }

    @Test
    public void shouldNotAddAssignmentWithEmptyStringID() {
        Tema assignmentWithEmptyID = new Tema("","Lab03/SSVV",8,5);
        Tema st1 = assignmentRepo.save(assignmentWithEmptyID);

        assert st1 == null;
    }

    @Test
    public void shouldNotAddAssignmentWithNullDescription() {
        Tema assignmentWithNullDescription = new Tema("1",null,8,5);
        Tema st1 = assignmentRepo.save(assignmentWithNullDescription);

        assert st1 == null;
    }

    @Test
    public void shouldNotAddAssignmentWithEmptyStringDescription() {
        Tema assignmentWithEmptyID = new Tema("1","",8,5);
        Tema st1 = assignmentRepo.save(assignmentWithEmptyID);

        assert st1 == null;
    }

    @Test
    public void shouldNotAddAssigmentWithDateOutOfRange() {
        // Date range is considered the usual duration of a school semester, that is 14 weeks,
        // so assignment has to have startline and deadline within 1-14.

        Tema assignmentWithInvalidStartline = new Tema("1","Lab03/SSVV",5,0);
        Tema st1 = assignmentRepo.save(assignmentWithInvalidStartline);

        assert st1 == null;

        Tema assignmentWithInvalidDeadline = new Tema("2","Lab03/SSVV",15,4);
        Tema st2 = assignmentRepo.save(assignmentWithInvalidDeadline);

        assert st2 == null;
    }

    @Test
    public void shouldNotAddTemaIfAlreadyPresent() {
        Tema assignment1 = new Tema("1","Lab03/SSVV",5,3);
        assignmentRepo.save(assignment1);
        Tema assignment2 = new Tema("1","Lab03-02/SSVV",5,3);
        assignmentRepo.save(assignment2);
        Iterable values = assignmentRepo.findAll();
        Iterator it = values.iterator();
        int nr=0;
        while (it.hasNext()) {
            it.next();
            nr++;
        }
        assert nr==1;
    }
}
