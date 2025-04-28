package com.banco.pagos.dto;

import com.banco.pagos.model.enums.EstatusPago;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request para cambiar el estatus de un pago")
public class CambioEstatusRequest {

    @NotNull
    @Schema(
            description = "Nuevo estatus permitido: PENDIENTE, APROBADO o RECHAZADO",
            example = "APROBADO",
            allowableValues = {"PENDIENTE", "APROBADO", "RECHAZADO"})
    private EstatusPago nuevoEstatus;
}
