package com.app.web.servicio.interfaces;

import com.app.web.entidad.Employees;

import java.util.List;

public interface EmployeeServicio {

    public List<Employees> listarTodosLosEmpleados();

    public Employees guardarEmpleado(Employees employee);

    public Employees obtenerEmpleadoPorId(Integer id);

    public Employees actualizarEmpleado(Employees employee);

    public void eliminarEmpleado(Integer id);
}
