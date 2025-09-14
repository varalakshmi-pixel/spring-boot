package com.example.employee_api;



	

	import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	@RestController
	@RequestMapping("/api/employees")
	public class EmployeeController {

	    @Autowired
	    private EmployeeRepository employeeRepository;

	    // Create a new employee
	    @PostMapping
	    public Employee createEmployee(@RequestBody Employee employee) {
	        return employeeRepository.save(employee);
	    }

	    // Get all employees
	    @GetMapping
	    public List<Employee> getAllEmployees() {
	        return employeeRepository.findAll();
	    }

	    // Get an employee by ID
	    @GetMapping("/{id}")
	    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
	        return employeeRepository.findById(id);
	    }

	    // Update an employee
	    @PutMapping("/{id}")
	    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
	        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
	        employee.setName(employeeDetails.getName());
	        employee.setPosition(employeeDetails.getPosition());
	        employee.setDepartment(employeeDetails.getDepartment());
	        return employeeRepository.save(employee);
	    }

	    // Delete an employee
	    @DeleteMapping("/{id}")
	    public String deleteEmployee(@PathVariable Long id) {
	        employeeRepository.deleteById(id);
	        return "Employee with ID: " + id + " has been deleted successfully.";
	    }
	}


