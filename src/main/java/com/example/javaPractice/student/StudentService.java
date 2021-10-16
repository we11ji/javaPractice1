package com.example.javaPractice.student;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
        boolean studentExists = studentRepository.findStudentByEmail(student.getEmail()).isPresent();
        if (studentExists){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (studentRepository.existsById(studentId)){
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId, @Nullable String name, @Nullable String email) {
        Optional<Student> student = studentRepository.findById(studentId);
        student.ifPresent(s -> {
            if (email != null
                    && !email.equals(s.getEmail())
                    && studentRepository.findStudentByEmail(email).isEmpty()){
                s.setEmail(email);
            }
            if (name != null && !name.equals(s.getName())){
                s.setName(name);
            }
            if (name != null || email != null) {
                studentRepository.save(s);
            }
        });

        }
}
