package lv.twino;

import lv.twino.model.Student;
import lv.twino.repository.StudentRepository;
import lv.twino.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerMockTests {

    @Autowired
    private StudentService studentService;
    
    @MockBean
    private StudentRepository studentRepository;
 
    @Test
    public void testRetrieveStudentWithMockRepository() throws Exception {
  
        Optional<Student> optStudent = Optional.of( new Student("Rajesh","Bhojwani"));
        when(studentRepository.findById(1)).thenReturn(optStudent);

        assertTrue(studentService.retrieveStudent(1).getName().contains("Rajesh"));
    }
 
}

