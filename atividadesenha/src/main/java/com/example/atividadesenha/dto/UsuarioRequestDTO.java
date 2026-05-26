package com.example.atividadesenha.dto;

import com.example.atividadesenha.model.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O login é obrigatório")
    private String login;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 10, max = 12, message = "A senha deve ter entre 10 e 12 caracteres")
    private String senha;

    @NotNull(message = "O tipo de usuário é obrigatório")
    private TipoUsuario tipo;

    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(String nome, String login, String email, String senha, TipoUsuario tipo) {
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}
