package edu.platform.repository;

import edu.platform.entity.CurriculoEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.List;
import java.util.UUID;

public class CurriculoRepository implements PanacheRepositoryBase<CurriculoEntity, UUID> {

    public List<CurriculoEntity> findByCursoId(Long cursoId) {
        return list("curso.id", cursoId);
    }

    public List<CurriculoEntity> findByCursoAndSemestre(Long cursoId, Long semestreId) {
        return list("curso.id = ?1 and semestre.id = ?2", cursoId, semestreId);
    }

}
