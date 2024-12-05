package com.app.web.servicio;

import com.app.web.entidad.Employees;
import com.app.web.repositorio.EmployeeRepository;
import com.app.web.servicio.interfaces.EmployeeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServicioImpl implements EmployeeServicio {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employees> listarTodosLosEmpleados() {
        return employeeRepository.findAll();
    }

    @Override
    public Employees guardarEmpleado(Employees employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employees obtenerEmpleadoPorId(Integer id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employees actualizarEmpleado(Employees employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void eliminarEmpleado(Integer id) {
        employeeRepository.deleteById(id);
    }
}
