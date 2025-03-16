package gui.tech.compass.controller;

import gui.tech.compass.model.Usuario;
import gui.tech.compass.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos os usuários
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable int id) {
        return usuarioService.buscarPorId(id)
                .map(usuario -> ResponseEntity.ok(usuario)) // Se encontrar o usuário
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrar, retorna 404
    }

    // Criar novo usuário
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCriado = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(201).body(usuarioCriado); // Retorna o usuário criado com status 201
    }

    // Deletar usuário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable int id) {
        if (usuarioService.buscarPorId(id).isPresent()) {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.noContent().build(); // Retorna status 204 (Sem conteúdo) para sucesso
        }
        return ResponseEntity.notFound().build(); // Retorna status 404 se não encontrar o usuário
    }
}
