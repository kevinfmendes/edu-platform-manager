package edu.platform.service;

/*import edu.platform.entity.CurriculoEntity;
import edu.platform.entity.CursoEntity;
import edu.platform.entity.DisciplinaEntity;
import edu.platform.entity.SemestreEntity;
import edu.platform.exception.UserNotFoundException;
import edu.platform.repository.CurriculoRepository;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

public class CurriculoService {

    @Inject
    CurriculoRepository curriculoRepository;

    @Inject
    CursoRepository cursoRepository;

    @Inject
    SemestreRepository semestreRepository;

    @Inject
    DisciplinaRepository disciplinaRepository;

    public CurriculoEntity criar(CurriculoRequest request) {
        CurriculoEntity curriculo = new CurriculoEntity();

        CursoEntity curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

        SemestreEntity semestre = semestreRepository.findById(request.getSemestreId())
                .orElseThrow(() -> new ResourceNotFoundException("Semestre não encontrado"));

        DisciplinaEntity disciplina = disciplinaRepository.findById(request.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada"));

        curriculo.setCurso(curso);
        curriculo.setSemestre(semestre);
        curriculo.setDisciplina(disciplina);
        curriculo.setPosicao(request.getPosicao());

        return curriculoRepository.save(curriculo);
    }

    public CurriculoEntity atualizar(UUID id, CurriculoRequest request) {
        CurriculoEntity curriculo = curriculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Currículo não encontrado"));

        CursoEntity curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

        SemestreEntity semestre = semestreRepository.findById(request.getSemestreId())
                .orElseThrow(() -> new ResourceNotFoundException("Semestre não encontrado"));

        DisciplinaEntity disciplina = disciplinaRepository.findById(request.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada"));

        curriculo.setCurso(curso);
        curriculo.setSemestre(semestre);
        curriculo.setDisciplina(disciplina);
        curriculo.setPosicao(request.getPosicao());

        return curriculoRepository.save(curriculo);
    }

    public void excluir(UUID id) {
        CurriculoEntity curriculo = curriculoRepository.findByIdOptional(id)
                .orElseThrow((UserNotFoundException::new);

        curriculoRepository.delete(curriculo);
    }

    public CurriculoEntity buscarPorId(UUID id) {
        return (CurriculoEntity)curriculoRepository.findByIdOptional(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public List<CurriculoEntity> listarPorCurso(UUID cursoId) {
        return curriculoRepository.findByCursoId(cursoId);
    }

    public List<CurriculoEntity> listarPorSemestre(UUID semestreId) {
        return curriculoRepository.findSemestreById(semestreId);
    }

    public List<CurriculoEntity> listarPorCursoESemestre(UUID cursoId, UUID semestreId) {
        return curriculoRepository.findByCursoIdAndSemestreId(cursoId, semestreId);
    }

    public List<CurriculoEntity> listarTodos(Integer page, Integer pageSize) {
        return curriculoRepository.findAll().page(page, pageSize).list();
    }

}*/
