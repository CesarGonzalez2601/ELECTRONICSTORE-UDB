package com.app.web.servicio;

import com.app.web.entidad.Users;
import com.app.web.repositorio.UsersRepository;
import com.app.web.servicio.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Users user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
