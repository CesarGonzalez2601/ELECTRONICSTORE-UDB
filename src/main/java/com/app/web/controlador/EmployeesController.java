package com.app.web.controlador;

import com.app.web.entidad.Employees;
import com.app.web.servicio.interfaces.EmployeeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeesController {

    @Autowired
    private EmployeeServicio employeeServicio;

    @GetMapping({"/employees", "/"})
    public String listarEmpleados(Model modelo) {
        modelo.addAttribute("employees", employeeServicio.listarTodosLosEmpleados());
        return "employees/employees";
    }

    @GetMapping("/employees/add_employee")
    public String mostrarFormularioDeRegistrarEmpleado(Model modelo) {
        Employees employee = new Employees();
        modelo.addAttribute("employee", employee);
        return "employees/add_employee";
    }


    @PostMapping("/employees")
    public String guardarEmpleado(@ModelAttribute("employees") Employees employee) {
        employeeServicio.guardarEmpleado(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{employeeId}")
    public String mostrarFormularioDeEditar(@PathVariable Integer employeeId, Model modelo) {
        modelo.addAttribute("employees", employeeServicio.obtenerEmpleadoPorId(employeeId));
        return "employees/update_employee";
    }

    @PostMapping("/employees/{employeeId}")
    public String actualizarEmpleado(@PathVariable Integer employeeId, @ModelAttribute("employees") Employees employee) {
        Employees empleadoExistente = employeeServicio.obtenerEmpleadoPorId(employeeId);
        empleadoExistente.setEmployeeId(employeeId);
        empleadoExistente.setFirstName(employee.getFirstName());
        empleadoExistente.setLastName(employee.getLastName());
        empleadoExistente.setDateOfBirth(employee.getDateOfBirth());
        empleadoExistente.setDateOfJoining(employee.getDateOfJoining());
        empleadoExistente.setDateOfLeaving(employee.getDateOfLeaving());
        empleadoExistente.setIsActive(employee.getIsActive());

        employeeServicio.actualizarEmpleado(empleadoExistente);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}")
    public String eliminarEmpleado(@PathVariable Integer id) {
        employeeServicio.eliminarEmpleado(id);
        return "redirect:/employees";
    }
}