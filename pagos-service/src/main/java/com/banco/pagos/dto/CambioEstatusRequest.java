package com.banco.pagos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CambioEstatusRequest {

    @NotBlank(message = "El nuevo estatus no puede ser vacío")
    private String nuevoEstatus;
}

