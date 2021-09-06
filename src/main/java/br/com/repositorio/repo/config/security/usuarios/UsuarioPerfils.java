package br.com.repositorio.repo.config.security.usuarios;

import javax.persistence.*;

@Entity
@Table(name = "usuario_perfis")
public class UsuarioPerfils  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Perfil perfil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public UsuarioPerfils(Long id, Usuario usuario, Perfil perfil) {
        this.id = id;
        this.usuario = usuario;
        this.perfil = perfil;
    }
}
