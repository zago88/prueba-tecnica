package com.banco.pagos.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.banco.pagos.messaging.PagoPublisher;
import com.banco.pagos.model.Pago;
import com.banco.pagos.model.enums.EstatusPago;
import com.banco.pagos.repository.PagoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

class PagoServiceTest {

    private PagoRepository pagoRepository;
    private PagoPublisher pagoPublisher;
    private PagoService pagoService;

    @BeforeEach
    void setUp() {
        pagoRepository = mock(PagoRepository.class);
        pagoPublisher = mock(PagoPublisher.class);
        pagoService = new PagoService(pagoRepository, pagoPublisher);
    }

    @Test
    void crearPago_deberiaGuardarPagoConEstatusPendiente() {
        // Arrange
        Pago pago =
                Pago.builder()
                        .concepto("Compra")
                        .cantidadProductos(2)
                        .quienPaga("Cliente A")
                        .aQuienSePaga("Proveedor B")
                        .montoTotal(BigDecimal.valueOf(1000))
                        .build();

        when(pagoRepository.save(any(Pago.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Pago resultado = pagoService.crearPago(pago);

        // Assert
        assertThat(resultado.getEstatus()).isEqualTo(EstatusPago.PENDIENTE);
        verify(pagoRepository).save(any(Pago.class));
    }

    @Test
    void cambiarEstatusPago_pagoExistente_actualizaEstatusYPublicaEvento() {
        // Arrange
        Pago pagoExistente =
                Pago.builder()
                        .id("123")
                        .concepto("Compra")
                        .cantidadProductos(2)
                        .quienPaga("Cliente A")
                        .aQuienSePaga("Proveedor B")
                        .montoTotal(BigDecimal.valueOf(500))
                        .estatus(EstatusPago.PENDIENTE)
                        .build();

        when(pagoRepository.findById("123")).thenReturn(Optional.of(pagoExistente));
        when(pagoRepository.save(any(Pago.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // ActOptional<Pago> resultado = pagoService.cambiarEstatusPago("123", "aprobado");
        Optional<Pago> resultado = pagoService.cambiarEstatusPago("123", EstatusPago.APROBADO);
        // Assert
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getEstatus()).isEqualTo(EstatusPago.APROBADO);

        verify(pagoPublisher).publicarCambioEstatus(any(Pago.class));
        verify(pagoRepository).save(any(Pago.class));
    }

    @Test
    void cambiarEstatusPago_pagoNoExistente_noHaceNada() {
        // Arrange
        when(pagoRepository.findById("999")).thenReturn(Optional.empty());

        // Act
        Optional<Pago> resultado = pagoService.cambiarEstatusPago("999", EstatusPago.RECHAZADO);

        // Assert
        assertThat(resultado).isEmpty();
        verify(pagoRepository, never()).save(any());
        verify(pagoPublisher, never()).publicarCambioEstatus(any());
    }

    @Test
    void obtenerPagoPorId_pagoExistente_devuelvePago() {
        // Arrange
        Pago pago = Pago.builder().id("456").build();
        when(pagoRepository.findById("456")).thenReturn(Optional.of(pago));

        // Act
        Optional<Pago> resultado = pagoService.obtenerPagoPorId("456");

        // Assert
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getId()).isEqualTo("456");
    }

    @Test
    void obtenerPagoPorId_pagoNoExistente_devuelveVacio() {
        // Arrange
        when(pagoRepository.findById("789")).thenReturn(Optional.empty());

        // Act
        Optional<Pago> resultado = pagoService.obtenerPagoPorId("789");

        // Assert
        assertThat(resultado).isEmpty();
    }
}
