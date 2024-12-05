package com.app.web.servicio.interfaces;

import com.app.web.entidad.Users;

import java.util.List;

public interface UsuarioServicio {
    public List<Users> listarTodosLosUsuarios();

    public Users guardarUsuario(Users users);

    public Users obtenerUsuarioPorId(Integer id);

    public Users actualizarUsuario(Users users);

    public void eliminarUsuario(Integer id);
}
