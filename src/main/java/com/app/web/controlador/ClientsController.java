package com.app.web.controlador;

import com.app.web.entidad.Clients;
import com.app.web.servicio.interfaces.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientsController {

	@Autowired
	private ClienteServicio clienteServicio;

	@GetMapping({ "/clients", "/" })
	public String listarClientes(Model modelo) {
		modelo.addAttribute("clients", clienteServicio.listarTodosLosClientes());
		return "clients/clients";
	}

	@GetMapping("/clients/add_clients")
	public String mostrarFormularioDeRegistrtarCliente(Model modelo) {
		Clients clients = new Clients();
		modelo.addAttribute("clients", clients);
		return "clients/add_client";
	}

	@PostMapping("/clients")
	public String guardarCliente(@ModelAttribute("cliente") Clients clients) {
		clienteServicio.guardarCliente(clients);
		return "redirect:/clients";
	}

	@GetMapping("/clients/edit/{id}")
	public String mostrarFormularioDeEditar(@PathVariable Integer id, Model modelo) {
		modelo.addAttribute("clients", clienteServicio.obtenerClientePorId(id));
		return "clients/update_client";
	}


	@PostMapping("/clients/{idClient}")
	public String actualizarCliente(@PathVariable Integer idClient, @ModelAttribute("clients") Clients clients,
			Model modelo) {
		Clients clientsExistente = clienteServicio.obtenerClientePorId(idClient);
		clientsExistente.setIdClient(idClient);
		clientsExistente.setFirstName(clients.getFirstName());
		clientsExistente.setLastName(clients.getLastName());
		clientsExistente.setDateOfBirth(clients.getDateOfBirth());
		clientsExistente.setIsActive(clients.getIsActive());

		clienteServicio.actualizarCliente(clientsExistente);
		return "redirect:/clients";
	}

	@GetMapping("/clients/{id}")
	public String deleteClient(@PathVariable Integer id) {
		clienteServicio.eliminarCliente(id);
		return "redirect:/clients";
	}
}
