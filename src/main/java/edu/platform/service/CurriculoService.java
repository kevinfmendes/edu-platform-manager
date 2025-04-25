package edu.platform.service;

import edu.platform.entity.CurriculoEntity;
import edu.platform.repository.CurriculoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public class CurriculoService {

    @Inject
    CurriculoRepository curriculoRepository;

    public List<CurriculoEntity> findByCurso(Long cursoId) {
        return curriculoRepository.findByCursoId(cursoId);
    }

    public List<CurriculoEntity> findByCursoAndSemestre(Long cursoId, Long semestreId) {
        return curriculoRepository.findByCursoAndSemestre(cursoId, semestreId);
    }

    @Transactional
    public CurriculoEntity addDisciplina(CurriculoEntity item) {
        curriculoRepository.persist(item);
        return item;
    }

    @Transactional
    public void removeDisciplina(UUID id) {
        curriculoRepository.deleteById(id);
    }

    @Transactional
    public CurriculoEntity updatePosicao(UUID id, Integer novaPosicao) {
        CurriculoEntity item = curriculoRepository.findById(id);
        if (item != null) {
            item.setPosicao(novaPosicao);
        }
        return item;
    }

}
