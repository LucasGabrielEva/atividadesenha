package com.example.atividadesenha.controller;

import com.example.atividadesenha.dto.UsuarioRequestDTO;
import com.example.atividadesenha.dto.UsuarioResponseDTO;
import com.example.atividadesenha.model.Usuario;
import com.example.atividadesenha.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    // RF01 - Endpoint de Cadastro
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioRequestDTO request) {
        try {
            // Converte o DTO para a Entidade Usuario
            Usuario usuario = new Usuario();
            usuario.setNome(request.getNome());
            usuario.setLogin(request.getLogin());
            usuario.setEmail(request.getEmail());
            usuario.setSenha(request.getSenha());
            usuario.setUsuario(request.getTipo());

            // Chama o serviço para salvar
            Usuario salvo = service.cadastrar(usuario);

            // Devolve o ResponseDTO (protegendo a senha)
            UsuarioResponseDTO response = new UsuarioResponseDTO(salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            // Retorna o erro de validação (ex: senha fora do padrão)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // RF04 - Endpoint de Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String login, @RequestParam String senha) {
        try {
            // Tenta realizar a autenticação
            Usuario logado = service.login(login, senha);

            // Se der certo, retorna os dados do usuário (ResponseDTO)
            UsuarioResponseDTO response = new UsuarioResponseDTO(logado);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Retorna erro 401 caso o login falhe ou esteja bloqueado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
