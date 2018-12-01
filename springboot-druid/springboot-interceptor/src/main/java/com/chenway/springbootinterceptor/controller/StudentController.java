package com.chenway.springbootinterceptor.controller;



import com.chenway.springbootinterceptor.dao.StudentJPA;
import com.chenway.springbootinterceptor.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentJPA studentJPA;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Student> list(){
        return studentJPA.findAll();
    }

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public Student save(Student student){
        return studentJPA.save(student);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public void delete(Student student){
         studentJPA.delete(student);
    }
}
