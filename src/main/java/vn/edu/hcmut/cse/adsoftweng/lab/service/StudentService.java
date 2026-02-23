package vn.edu.hcmut.cse.adsoftweng.lab.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public List<Student> getAll(){
        return repo.findAll();
    }

    public Student getById(Integer id){
        return repo.findById(id).orElse(null);
    }

    public List<Student> searchByName(String name){
        return repo.findByName(name);
    }

    public Student save(Student student) {
        return repo.save(student);
    }

    public void deleteById(Integer id){
        repo.deleteById(id);
    }

}
