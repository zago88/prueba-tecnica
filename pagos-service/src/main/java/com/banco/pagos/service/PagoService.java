package com.banco.pagos.service;

import com.banco.pagos.messaging.PagoPublisher;
import com.banco.pagos.model.Pago;
import com.banco.pagos.repository.PagoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final PagoPublisher pagoPublisher;

    @Transactional
    public Pago crearPago(Pago pago) {
        pago.setId(null); // Forzar a que Mongo genere un nuevo ID
        pago.setFechaRegistro(java.time.LocalDateTime.now());
        pago.setEstatus("pendiente"); // Siempre inicia en pendiente
        return pagoRepository.save(pago);
    }

    @Transactional
    public Optional<Pago> cambiarEstatusPago(String idPago, String nuevoEstatus) {
        Optional<Pago> pagoOptional = pagoRepository.findById(idPago);

        if (pagoOptional.isPresent()) {
            Pago pago = pagoOptional.get();
            pago.setEstatus(nuevoEstatus);
            pagoRepository.save(pago);

            // Publicar Evento
            pagoPublisher.publicarCambioEstatus(pago);
        }

        return pagoOptional;
    }

    public Optional<Pago> obtenerPagoPorId(String idPago) {
        return pagoRepository.findById(idPago);
    }
}
