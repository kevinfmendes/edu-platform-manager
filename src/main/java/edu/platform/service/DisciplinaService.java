package edu.platform.service;

import edu.platform.entity.DisciplinaEntity;
import edu.platform.repository.DisciplinaRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;


public class DisciplinaService {

    @Inject
    DisciplinaRepository disciplinaRepository;

    public List<DisciplinaEntity> findAll() {
        return disciplinaRepository.listAll();
    }

    public List<DisciplinaEntity> findByCurso(Long cursoId) {
        return disciplinaRepository.findByCursoId(cursoId);
    }

    public DisciplinaEntity findById(Long id) {
        return disciplinaRepository.findById(id);
    }

    @Transactional
    public DisciplinaEntity save(DisciplinaEntity disciplina) {
        disciplinaRepository.persist(disciplina);
        return disciplina;
    }

    @Transactional
    public DisciplinaEntity update(Long id, DisciplinaEntity disciplina) {
        DisciplinaEntity entity = disciplinaRepository.findById(id);
        if (entity != null) {
            entity.setNome(disciplina.getNome());
            entity.setCodigo(disciplina.getCodigo());
            entity.setCargaHoraria(disciplina.getCargaHoraria());
            entity.setEmenta(disciplina.getEmenta());
        }
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        disciplinaRepository.deleteById(id);
    }

}
