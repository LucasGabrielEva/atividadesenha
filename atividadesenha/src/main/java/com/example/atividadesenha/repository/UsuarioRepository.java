package com.example.atividadesenha.repository;

import com.example.atividadesenha.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
