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


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioRequestDTO request) {
        try {

            Usuario usuario = new Usuario();
            usuario.setNome(request.getNome());
            usuario.setLogin(request.getLogin());
            usuario.setEmail(request.getEmail());
            usuario.setSenha(request.getSenha());
            usuario.setUsuario(request.getTipo());


            Usuario salvo = service.cadastrar(usuario);


            UsuarioResponseDTO response = new UsuarioResponseDTO(salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String login, @RequestParam String senha) {
        try {

            Usuario logado = service.login(login, senha);


            UsuarioResponseDTO response = new UsuarioResponseDTO(logado);
            return ResponseEntity.ok(response);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
