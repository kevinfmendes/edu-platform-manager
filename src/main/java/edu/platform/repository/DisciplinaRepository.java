package edu.platform.repository;

import edu.platform.entity.DisciplinaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


@ApplicationScoped
public class DisciplinaRepository implements PanacheRepository<DisciplinaEntity> {
    public List<DisciplinaEntity> findByCursoId(Long cursoId) {
        return list("curso.id", cursoId);
    }

    public DisciplinaEntity findByCodigo(String codigo) {
        return find("codigo", codigo).firstResult();
    }
}
