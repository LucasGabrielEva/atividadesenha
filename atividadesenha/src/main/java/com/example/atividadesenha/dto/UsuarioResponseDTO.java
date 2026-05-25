package com.example.atividadesenha.dto;

import com.example.atividadesenha.model.TipoUsuario;
import com.example.atividadesenha.model.Usuario;

public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String login;
    private String email;
    private TipoUsuario tipo;

    // 1. Construtor vazio padrão
    public UsuarioResponseDTO() {
    }

    // 2. ESTE É O CONSTRUTOR QUE FALTAVA E VAI RESOLVER O SEU ERRO!
    // Ele pega os dados do Usuario salvo e joga para o DTO automaticamente.
    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.email = usuario.getEmail();
        this.tipo = usuario.getUsuario();
    }

    // --- GETTERS E SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}
