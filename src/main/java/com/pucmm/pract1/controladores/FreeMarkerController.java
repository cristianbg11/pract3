package com.pucmm.pract1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pucmm.pract1.entidades.*;
import com.pucmm.pract1.repositorio.EstudianteRepository;

@Controller()
@RequestMapping("/")
public class FreeMarkerController {
    
   @Autowired
    private EstudianteRepository estudianteRepository;
    
    /**
     * Prueba utilizando los webjars, cargando los BootStrap.
     * 
     * @param model
     * @return
     */

   

    @GetMapping("/")
    public ModelAndView index(Model model){
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        estudiantes = estudianteRepository.findAll();
        Map < String, Object > params = new HashMap < String, Object > ();
    
      params.put("listado", estudiantes);

      return new ModelAndView("index", params);
    }

    @RequestMapping("/crear")
    public String crear(Model model){
       return "/crear";
    }

    @PostMapping("/insertar")
    public String insertar(@Valid Estudiante estudiante, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "in-sertar";
        }

        estudianteRepository.save(estudiante);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(Model model, @PathVariable("id") Long id) {
        Estudiante student = estudianteRepository.getOne(id+1);

        Map < String, Object > params = new HashMap < String, Object > ();
    
        params.put("edicion", student);
        params.put("id", id+1);

        return new ModelAndView("editar", params);
        /*
        model.addAttribute("edicion", student);
        model.addAttribute("id", id+1);
        return "edit23";
        */
    }

    @RequestMapping("/view/{id}")
    public ModelAndView view(Model model, @PathVariable("id") Long id) {
        Estudiante student = estudianteRepository.getOne(id+1);

        Map < String, Object > params = new HashMap < String, Object > ();

        params.put("vista", student);
        params.put("id", id+1);

        return new ModelAndView("view", params);
    }

    /*
    @GetMapping("/edit/{id}")
    public String showUpdateForm(Model model, @PathVariable("id") Integer id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante invalido:" + id));

    Map < String, Object > params = new HashMap < String, Object > ();
    params.put("edicion", estudiante);
      return new ModelAndView("/edit", params);
    }
    */

    @PostMapping("/actualizar")
    public String updateEstudent(@ModelAttribute("estudiante") Estudiante student,@RequestParam(name = "id")  Long id){
        //estudianteService.updateStudent(student, id+1);
        Estudiante std = estudianteRepository.getOne(id);
        std.setNombre(student.getNombre());
        std.setApellido(student.apellido);
        std.setMatricula(student.matricula);
        std.setTelefono(student.telefono);
        estudianteRepository.save(std);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEstudent(@PathVariable("id") long id, Model model) {
        Estudiante estudiante = estudianteRepository.findById(id+1)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante invalido:" + id));
        estudianteRepository.delete(estudiante);
        //model.addAttribute("estudantes", estudianteRepository.findAll());
        return "redirect:/";
    }

}
