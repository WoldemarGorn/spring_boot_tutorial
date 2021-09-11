package com.springtutorial.demo.student.service;

import com.springtutorial.demo.student.entity.Student;
import com.springtutorial.demo.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentReq = studentRepository.findStudentByEmail(student.getEmail());
        if (studentReq.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("student with id : " + studentId + " does not exist");
        } else {
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist!"
                ));

        if (null != name && name.length() > 0
        && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if (null != email && email.length() > 0
                && !Objects.equals(student.getEmail(), email)){

            Optional<Student> studentReq = studentRepository.findStudentByEmail(student.getEmail());
            if (studentReq.isPresent()) {
                throw new IllegalStateException("email taken");
            }

            student.setEmail(email);
        }
    }
}
