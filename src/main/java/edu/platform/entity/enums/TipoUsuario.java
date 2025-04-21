package edu.platform.entity.enums;

public enum TipoUsuario {

    ADMIN(1, "ADMIN"),
    COORDENADOR(2, "COORDENADOR"),
    PROFESSOR(3, "PROFESSOR"),
    ALUNO(4, "ALUNO");

    private Integer codigo;
    private String descricao;

    TipoUsuario(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
