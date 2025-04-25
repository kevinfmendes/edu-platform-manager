package edu.platform.repository;

import edu.platform.entity.CursoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CursoRepository  implements PanacheRepository<CursoEntity> {
    public CursoEntity findByCodigo(String codigo) {
        return find("codigo", codigo).firstResult();
    }
}
