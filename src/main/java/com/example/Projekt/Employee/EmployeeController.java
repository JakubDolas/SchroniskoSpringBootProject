package com.example.Projekt.Employee;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
                               @RequestParam(defaultValue = "8") int size,
                               @RequestParam(defaultValue = "firstName") String sortBy,
                               @RequestParam(defaultValue = "asc") String sortDir) {
        Pageable pageable = PageRequest.of(page, size,
                sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Page<Employee> employeesPage = employeeService.getPaginatedEmployees(pageable);

        model.addAttribute("employeesPage", employeesPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("size", size);
        return "employees";
    }

    @GetMapping("/addEmployee")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee,
                              BindingResult result,
                              @RequestParam("image") MultipartFile imageFile,
                              Model model) {
        if (result.hasErrors()) {
            return "addEmployee";
        }

        try {
            byte[] imageBytes = imageFile.getBytes();
            EmployeePhoto employeePhoto = new EmployeePhoto(imageBytes);
            employeePhotoService.savePhoto(employeePhoto);

            employee.setEmployeePhoto(employeePhoto);
            employeeService.saveEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/employees";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(Model model,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "6") int size,
                                 @RequestParam(defaultValue = "firstName") String sortBy,
                                 @RequestParam(defaultValue = "asc") String sortDir) {
        if (page < 0) {
            page = 0;
        }

        Pageable pageable = PageRequest.of(page, size,
                sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Page<Employee> employeesPage = employeeService.getPaginatedEmployees(pageable);

        model.addAttribute("employeesPage", employeesPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("size", size);
        return "deleteEmployee";
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/deleteEmployee";
    }
}
