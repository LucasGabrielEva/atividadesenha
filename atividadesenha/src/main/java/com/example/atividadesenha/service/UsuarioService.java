package com.example.atividadesenha.service;

import com.example.atividadesenha.model.Usuario;
import com.example.atividadesenha.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // RF01, RF02, RF03: Cadastro
    public Usuario cadastrar(Usuario usuario) {
        if (!validarSenha(usuario.getSenha())) {
            // Usando exceção nativa do Java, sem precisar de import
            throw new IllegalArgumentException("A senha deve ter entre 10 e 12 caracteres, letras, números e caracteres especiais.");
        }

        return repository.save(usuario);
    }

    // RF04 e RF06: Login e Bloqueio
    public Usuario login(String login, String senha) {
        if (login == null || login.isBlank() || senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Usuário e senha não podem ser vazios.");
        }

        // Busca o usuário. Se não achar, lança erro.
        Usuario usuario = repository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuário inexistente."));

        if (usuario.isBloqueado()) {
            throw new RuntimeException("Conta bloqueada por excesso de tentativas inválidas.");
        }

        if (!usuario.getSenha().equals(senha)) {
            usuario.setTentativasFalhas(usuario.getTentativasFalhas() + 1);
            if (usuario.getTentativasFalhas() >= 3) {
                usuario.setBloqueado(true);
            }
            repository.save(usuario);
            throw new RuntimeException("Senha inválida.");
        }

        // Se logou com sucesso, zera as falhas
        usuario.setTentativasFalhas(0);
        repository.save(usuario);
        return usuario;
    }

    // RF02: Regra da Senha
    public boolean validarSenha(String senha) {
        if (senha == null || senha.isBlank()) return false;
        if (senha.length() < 10 || senha.length() > 12) return false;

        boolean possuiNumero = senha.matches(".*\\d.*");
        boolean possuiLetra = senha.matches(".*[a-zA-Z].*");
        boolean possuiEspecial = senha.matches(".*[!@#$%&*()].*");

        return possuiNumero && possuiLetra && possuiEspecial;
    }




}
