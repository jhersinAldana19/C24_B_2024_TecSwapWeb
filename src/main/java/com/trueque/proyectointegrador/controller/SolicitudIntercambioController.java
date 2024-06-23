package com.trueque.proyectointegrador.controller;

import com.trueque.proyectointegrador.model.SolicitudIntercambio;
import com.trueque.proyectointegrador.model.Producto;
import com.trueque.proyectointegrador.model.Usuario;
import com.trueque.proyectointegrador.repository.SolicitudIntercambioRepository;
import com.trueque.proyectointegrador.repository.ProductoRepository;
import com.trueque.proyectointegrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class SolicitudIntercambioController {

    @Autowired
    private SolicitudIntercambioRepository solicitudIntercambioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/intercambio")
    public SolicitudIntercambio createSolicitudIntercambio(@RequestBody SolicitudIntercambio solicitudIntercambio, Authentication authentication) {
        String username = authentication.getName();
        Usuario solicitante = usuarioRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Producto productoSolicitado = productoRepository.findById(solicitudIntercambio.getProductoSolicitado().getId())
                .orElseThrow(() -> new RuntimeException("Producto solicitado no encontrado"));
        Producto productoOfrecido = productoRepository.findById(solicitudIntercambio.getProductoOfrecido().getId())
                .orElseThrow(() -> new RuntimeException("Producto ofrecido no encontrado"));

        solicitudIntercambio.setSolicitante(solicitante);
        solicitudIntercambio.setProductoSolicitado(productoSolicitado);
        solicitudIntercambio.setProductoOfrecido(productoOfrecido);
        solicitudIntercambio.setPropietario(productoSolicitado.getUsuario());

        solicitudIntercambio.setRespondida(false);
        solicitudIntercambio.setAceptada(false);

        return solicitudIntercambioRepository.save(solicitudIntercambio);
    }

    @GetMapping("/intercambio/solicitudes")
    public List<SolicitudIntercambio> getSolicitudesIntercambio(Authentication authentication) {
        String username = authentication.getName();
        Usuario propietario = usuarioRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return solicitudIntercambioRepository.findByPropietarioId(propietario.getId());
    }

    @PostMapping("/intercambio/responder/{id}")
    public SolicitudIntercambio respondToSolicitudIntercambio(@PathVariable Long id, @RequestParam boolean aceptada) {
        SolicitudIntercambio solicitudIntercambio = solicitudIntercambioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud de intercambio no encontrada"));
        solicitudIntercambio.setAceptada(aceptada);
        solicitudIntercambio.setRespondida(true);
        return solicitudIntercambioRepository.save(solicitudIntercambio);
    }
}