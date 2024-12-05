package com.app.web.servicio;

import java.util.List;

import com.app.web.entidad.Clients;
import com.app.web.servicio.interfaces.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.repositorio.ClientsRepository;

@Service
public class ClienteServicioImpl implements ClienteServicio {

	@Autowired
	private ClientsRepository clientsRepository;

	@Override
	public List<Clients> listarTodosLosClientes() {
		return clientsRepository.findAll();
	}

	@Override
	public Clients guardarCliente(Clients clients) {

		clients.setDateOfBirth(clients.getDateOfBirth());
		return clientsRepository.save(clients);
	}

	@Override
	public Clients obtenerClientePorId(Integer id) {
		return clientsRepository.findById(id).get();
	}

	@Override
	public Clients actualizarCliente(Clients clients) {
		return clientsRepository.save(clients);
	}

	@Override
		public void eliminarCliente(Integer id) {
		clientsRepository.deleteById(id);

	}

}
