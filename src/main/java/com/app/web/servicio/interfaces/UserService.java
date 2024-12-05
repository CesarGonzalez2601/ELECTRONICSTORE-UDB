package com.app.web.servicio.interfaces;

import com.app.web.entidad.Users;

import java.util.List;

public interface UserService {
    List<Users> findAll();
    Users findById(Integer id);
    void save(Users user);
    void delete(Integer id);
}
