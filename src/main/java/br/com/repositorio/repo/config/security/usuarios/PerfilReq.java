package br.com.repositorio.repo.config.security.usuarios;

public class PerfilReq{

	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	Perfil toModel(){
		Perfil perfil = new Perfil(this.nome);
		return perfil;
	}

}
