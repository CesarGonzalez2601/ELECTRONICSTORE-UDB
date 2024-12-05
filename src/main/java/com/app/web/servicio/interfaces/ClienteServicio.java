package com.app.web.servicio.interfaces;

import java.util.List;

import com.app.web.entidad.Clients;

public interface ClienteServicio {

	public List<Clients> listarTodosLosClientes();
	
	public Clients guardarCliente(Clients clients);
	
	public Clients obtenerClientePorId(Integer id);
	
	public Clients actualizarCliente(Clients clients);
	
	public void eliminarCliente(Integer id);
}
