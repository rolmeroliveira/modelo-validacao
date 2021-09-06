package br.com.repositorio.repo.config.security.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository repo;

    @PostMapping
    public ResponseEntity<UsuarioResp> insere(@RequestBody @Valid UsuarioReq usuarioReq  , UriComponentsBuilder ucb){
            Usuario usuario  = usuarioReq.toModel();
            Usuario usuarioSalvo = repo.save(usuario);
            UsuarioResp usuarioResp = new UsuarioResp(usuarioSalvo);
            URI uri = ucb.path("usuarios/{id}").buildAndExpand(usuarioResp.getId()).toUri();
            return ResponseEntity.created(uri).body(usuarioResp);
    }


    @GetMapping
    public ResponseEntity<List<UsuarioResp>> lista(UriComponentsBuilder ucb){
        List<UsuarioResp>lista = repo.findAll().stream().map( u -> new UsuarioResp(u)).collect((Collectors.toList()));
        URI uri = ucb.path("/usuarios").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(lista);
    }
}
