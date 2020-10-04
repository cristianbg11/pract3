package com.pucmm.pract1.servicios;

import java.util.List;

import com.pucmm.pract1.entidades.Estudiante;
import com.pucmm.pract1.repositorio.EstudianteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class EstudianteService {
 
    @Autowired
    private EstudianteRepository studentRepository;
 
    public List<Estudiante> getAllStudents() {
        return studentRepository.findAll();
    }
 
    public void createStudent(Estudiante student) {
        studentRepository.save(student);
    }
 
    public void updateStudent(Estudiante student, Long id) {
        Estudiante std = studentRepository.getOne(id+1);
        std.setNombre(student.getNombre());
        std.setApellido(student.apellido);
        std.setMatricula(student.matricula);
        std.setTelefono(student.telefono);
        studentRepository.save(std);
    }
 
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
 
    public Estudiante getStudent(Long id) {
        return studentRepository.getOne(id);
    }
}