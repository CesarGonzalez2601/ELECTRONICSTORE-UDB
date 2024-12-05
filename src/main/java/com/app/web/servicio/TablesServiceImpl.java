package com.app.web.servicio;
import com.app.web.entidad.Tables;
import com.app.web.repositorio.TablesRepository;
import com.app.web.servicio.interfaces.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TablesServiceImpl implements TablesService {

    @Autowired
    private TablesRepository tablesRepository;

    @Override
    public List<Tables> listarTodasLasMesas() {
        return tablesRepository.findAll();
    }

    @Override
    public Tables obtenerMesaPorId(Integer id) {
        return tablesRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarMesa(Tables table) {
        tablesRepository.save(table);
    }

    @Override
    public void actualizarMesa(Tables table) {
        tablesRepository.save(table);
    }

    @Override
    public void eliminarMesa(Integer id) {
        tablesRepository.deleteById(id);
    }
}