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
}
