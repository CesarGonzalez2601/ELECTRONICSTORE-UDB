package com.app.web.controlador;

import com.app.web.entidad.Tables;
import com.app.web.servicio.interfaces.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TablesController {

    @Autowired
    private TablesService tablesService;

    @GetMapping("/tables")
    public String listarMesas(Model modelo) {
        modelo.addAttribute("tables", tablesService.listarTodasLasMesas());
        return "tables/tables";
    }

    @GetMapping("/tables/add_table")
    public String mostrarFormularioDeRegistrarMesa(Model modelo) {
        Tables table = new Tables();
        modelo.addAttribute("table", table);
        return "tables/add_table";
    }

    @PostMapping("/tables")
    public String guardarMesa(@ModelAttribute("table") Tables table) {
        tablesService.guardarMesa(table);
        return "redirect:/tables";
    }

    @GetMapping("/tables/edit/{idTable}")
    public String mostrarFormularioDeEditar(@PathVariable Integer idTable, Model modelo) {
        modelo.addAttribute("table", tablesService.obtenerMesaPorId(idTable));
        return "tables/update_table";
    }

    @PostMapping("/tables/{idTable}")
    public String actualizarMesa(@PathVariable Integer idTable, @ModelAttribute("table") Tables table) {
        Tables mesaExistente = tablesService.obtenerMesaPorId(idTable);
        mesaExistente.setIdTable(idTable);
        mesaExistente.setNumber(table.getNumber());
        mesaExistente.setDescription(table.getDescription());
        mesaExistente.setSeats(table.getSeats());
        mesaExistente.setIsAbailable(table.getIsAbailable());

        tablesService.actualizarMesa(mesaExistente);
        return "redirect:/tables";
    }

    @GetMapping("/tables/{id}")
    public String eliminarMesa(@PathVariable Integer id) {
        tablesService.eliminarMesa(id);
        return "redirect:/tables";
    }
}