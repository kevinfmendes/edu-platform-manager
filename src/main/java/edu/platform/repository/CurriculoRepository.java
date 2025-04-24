package edu.platform.repository;

import edu.platform.entity.CurriculoEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.List;
import java.util.UUID;

public class CurriculoRepository implements PanacheRepositoryBase<CurriculoEntity, UUID> {

    public List<CurriculoEntity> findByCursoId(UUID cursoId){
        return find("cursoId", cursoId).stream().toList();
    };

    public List<CurriculoEntity> findSemestreById(UUID semestreId){
        return find("semestreId", semestreId).stream().toList();
    }

    public List<CurriculoEntity> findByCursoIdAndSemestreId(UUID cursoId, UUID semestreId){
        return find("cursoId, semestreId", cursoId, semestreId).stream().toList();
    }

}
