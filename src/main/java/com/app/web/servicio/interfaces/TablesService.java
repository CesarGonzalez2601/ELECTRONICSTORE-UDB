package com.app.web.servicio.interfaces;
import com.app.web.entidad.Tables;

import java.util.List;

public interface TablesService {

    List<Tables> listarTodasLasMesas();

    Tables obtenerMesaPorId(Integer id);

    void guardarMesa(Tables table);

    void actualizarMesa(Tables table);

    void eliminarMesa(Integer id);
}
