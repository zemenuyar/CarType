package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;
    @RequestMapping("/")
    public String getHomepage(){
        return "index";
    }

    @GetMapping("/addDepartment")
    public String getDepartmentForm(Model model){
        model.addAttribute("department",new Department());
        return "departmentform";
    }
    @PostMapping("/addDepartment")
    public String addDepartment(@ModelAttribute("department") Department department){
        departmentRepository.save(department);
        return "redirect:/departmentlist";
    }
    @RequestMapping("/departmentlist")
    public String getDepartmentList(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "departmentlist";
    }

    @GetMapping("/addEmployee")
    public String getEmployeeForm(Model model){
        model.addAttribute("employee",new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employeeform";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("employee") Employee employee){
        employeeRepository.save(employee);
        return "redirect:/employeelist";
    }
    @RequestMapping("/employeelist")
    public String getEmployeesList(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        return "employeelist";
    }
    @RequestMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "employeeform";
    }
    @RequestMapping("/detail/{id}")
    public String getEmployeeDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "employeedetail";
    }
    @RequestMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id){
        employeeRepository.deleteById(id);
        return "redirect:/employeelist";
    }


}
