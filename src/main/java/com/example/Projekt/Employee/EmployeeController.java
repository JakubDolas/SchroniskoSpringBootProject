package com.example.Projekt.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeePhotoService employeePhotoService;

    @GetMapping("/employees/{id}")
    public String getEmployeeDetails(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return "redirect:/employees";
        }
        model.addAttribute("employee", employee);
        return "employeeDetails";
    }

    @GetMapping("/employees")
    public String getEmployees(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeesPage = employeeService.getPaginatedEmployees(pageable);

        model.addAttribute("employeesPage", employeesPage);
        return "employees";
    }

    @GetMapping("/addEmployee")
    public String addEmployeeForm() {
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("image") MultipartFile imageFile) {
        try {
            byte[] imageBytes = imageFile.getBytes();
            EmployeePhoto employeePhoto = new EmployeePhoto(imageBytes);
            employeePhotoService.savePhoto(employeePhoto);

            Employee employee = new Employee(firstName, lastName, employeePhoto);
            employeeService.saveEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/employees";
    }
}
