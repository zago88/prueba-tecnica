package com.banco.pagos.controller;

import com.banco.pagos.dto.CambioEstatusRequest;
import com.banco.pagos.model.Pago;
import com.banco.pagos.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<Pago> crearPago(@Valid @RequestBody Pago pago) {
        Pago pagoGuardado = pagoService.crearPago(pago);
        return ResponseEntity.created(URI.create("/pagos/" + pagoGuardado.getId()))
                             .body(pagoGuardado);
    }

    @PatchMapping("/{id}/estatus")
    public ResponseEntity<Pago> cambiarEstatus(
            @PathVariable String id,
            @Valid @RequestBody CambioEstatusRequest request) {

        Optional<Pago> pagoActualizado = pagoService.cambiarEstatusPago(id, request.getNuevoEstatus());

        return pagoActualizado
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPago(@PathVariable String id) {
        Optional<Pago> pago = pagoService.obtenerPagoPorId(id);

        return pago
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

