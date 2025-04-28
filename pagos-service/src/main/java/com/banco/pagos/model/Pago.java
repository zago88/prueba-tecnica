package com.banco.pagos.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pagos")
public class Pago {

    @Id private String id;

    @NotBlank(message = "El concepto no puede estar vacío")
    private String concepto;

    @NotNull(message = "La cantidad de productos es obligatoria")
    @Min(value = 1, message = "La cantidad de productos debe ser al menos 1")
    private Integer cantidadProductos;

    @NotBlank(message = "El campo quien paga no puede estar vacío")
    private String quienPaga;

    @NotBlank(message = "El campo a quien se paga no puede estar vacío")
    private String aQuienSePaga;

    @NotNull(message = "El monto total es obligatorio")
    @Min(value = 0, message = "El monto debe ser mayor o igual a 0")
    private BigDecimal montoTotal;

    @NotBlank(message = "El estatus es obligatorio")
    private String estatus; // "pendiente", "aprobado", "rechazado"

    private LocalDateTime fechaRegistro;

    /**
     * Inicializamos fechaRegistro automáticamente cuando se construye con Builder si no se asigna.
     */
    public static PagoBuilder builder() {
        return new PagoBuilder().fechaRegistro(LocalDateTime.now());
    }
}
