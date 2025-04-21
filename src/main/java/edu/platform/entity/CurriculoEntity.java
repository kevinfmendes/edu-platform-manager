package edu.platform.entity;

import jakarta.persistence.*;

import java.util.UUID;

public class CurriculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CursoEntity curso;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private SemestreEntity semestre;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private DisciplinaEntity disciplina;

    // Posição na matriz curricular
    private Integer posicao;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CursoEntity getCurso() {
        return curso;
    }

    public void setCurso(CursoEntity curso) {
        this.curso = curso;
    }

    public SemestreEntity getSemestre() {
        return semestre;
    }

    public void setSemestre(SemestreEntity semestre) {
        this.semestre = semestre;
    }

    public DisciplinaEntity getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaEntity disciplina) {
        this.disciplina = disciplina;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

}
