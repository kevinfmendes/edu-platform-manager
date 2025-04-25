package edu.platform.service;

import edu.platform.entity.CursoEntity;
import edu.platform.repository.CursoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CursoService {

    @Inject
    CursoRepository cursoRepository;

    public List<CursoEntity> findAll() {
        return cursoRepository.listAll();
    }

    public CursoEntity findById(Long id) {
        return cursoRepository.findById(id);
    }

    @Transactional
    public CursoEntity save(CursoEntity curso) {
        cursoRepository.persist(curso);
        return curso;
    }

    @Transactional
    public CursoEntity update(Long id, CursoEntity curso) {
        CursoEntity entity = cursoRepository.findById(id);
        if (entity != null) {
            entity.setNome(curso.getNome());
            entity.setCodigo(curso.getCodigo());
            entity.setDescricao(curso.getDescricao());
        }
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }

}
