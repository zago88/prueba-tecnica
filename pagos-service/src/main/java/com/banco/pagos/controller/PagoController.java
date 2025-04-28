package com.banco.pagos.controller;

import com.banco.pagos.dto.CambioEstatusRequest;
import com.banco.pagos.model.Pago;
import com.banco.pagos.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
@Tag(name = "Pagos", description = "API para gestión de pagos y cambio de estatus")
public class PagoController {

    private final PagoService pagoService;

    @Operation(
            summary = "Registrar un nuevo pago",
            description = "Crea un nuevo registro de pago con estatus pendiente")
    @ApiResponse(responseCode = "201", description = "Pago creado exitosamente")
    @PostMapping
    public ResponseEntity<Pago> crearPago(@Valid @RequestBody Pago pago) {
        Pago pagoGuardado = pagoService.crearPago(pago);
        return ResponseEntity.created(URI.create("/pagos/" + pagoGuardado.getId()))
                .body(pagoGuardado);
    }

    @Operation(
            summary = "Cambiar estatus de un pago",
            description = "Actualiza el estatus de un pago existente")
    @ApiResponse(responseCode = "200", description = "Estatus actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    @PatchMapping("/{id}/estatus")
    public ResponseEntity<Pago> cambiarEstatus(
            @PathVariable String id, @Valid @RequestBody CambioEstatusRequest request) {

        Optional<Pago> pagoActualizado =
                pagoService.cambiarEstatusPago(id, request.getNuevoEstatus());

        return pagoActualizado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Obtener un pago por ID",
            description = "Consulta la información de un pago específico")
    @ApiResponse(responseCode = "200", description = "Pago encontrado")
    @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPago(@PathVariable String id) {
        Optional<Pago> pago = pagoService.obtenerPagoPorId(id);

        return pago.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
