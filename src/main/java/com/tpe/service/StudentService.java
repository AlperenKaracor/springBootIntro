package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
   private StudentRepository studentRepository;

    // Not :  Get ALL STUDENTS *************************
    public List<Student> getAll() {

        return studentRepository.findAll();

    }

    // Not : Create new Student ************************

    public void createStudent(Student student) {

        //!!! email unique mi kontrolu
        if(studentRepository.existsByEmail(student.getEmail())){; // existsById() bunu bilinc li mi yaptin demek istiyor

            throw new ConflictException("Email is already exists");


        }
        studentRepository.save(student);


    }


    //Not : getStudentById ReguestParam ******************

    public Student findStudent(Long id){

       return studentRepository.findById(id).orElseThrow(()->
               new ResourceNotFoundException("Student not found with id : " + id));


    }


    //Not : DeleteStudentById **********************
    public void deleteStudent(Long id) {

        // !!! Acaba yukarda verilen id li student nesnem DB de var mi kontrolu !!!
       Student student = findStudent(id);
       studentRepository.delete(student);
    }
}
