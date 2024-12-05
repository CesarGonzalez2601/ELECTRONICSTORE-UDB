package com.app.web.servicio;

import com.app.web.entidad.Users;
import com.app.web.repositorio.UsersRepository;
import com.app.web.servicio.interfaces.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> listarTodosLosUsuarios() {
        return usersRepository.findAll();
    }

    @Override
    public Users guardarUsuario(Users users) {
        return usersRepository.save(users);
    }


    @Override
    public Users obtenerUsuarioPorId(Integer id) {
        return usersRepository.findById(id).get();
    }

    @Override
    public Users actualizarUsuario(Users Users) {

        return usersRepository.save(Users);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usersRepository.deleteById(id);

    }
}
