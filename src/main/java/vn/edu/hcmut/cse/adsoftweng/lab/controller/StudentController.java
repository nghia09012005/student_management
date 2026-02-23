package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService service;

//    @GetMapping
//    public List<Student> getAllStudents(){
//        return service.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public Student getStudentById(@PathVariable Integer id){
//        return service.getById(id);
//
//    }

    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model){
        List<Student> students ;

        if(keyword != null && !keyword.isEmpty()){
            students = service.searchByName(keyword);
        }
        else {
            students = service.getAll();
        }


        // key dsSinhVien sẽ chứa data students chuyển sang bên html xử lý
        model.addAttribute("dsSinhVien", students);

        return "students"; // tên của file html trong templates
    }


    @GetMapping({"/add", "/add/{id}"})
    public String showAddForm(@PathVariable(required = false) Integer id, Model model) {
        if (id != null) { // edit
            Student student = service.getById(id);
            model.addAttribute("student", student);
        } else { // add mới
            model.addAttribute("student", new Student());
        }
        return "student-form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String viewDetail(@PathVariable Integer id, Model model) {
        Student student = service.getById(id);

        model.addAttribute("student", student);
        return "student-detail";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/students"; // xong quay về danh sách
    }



}
