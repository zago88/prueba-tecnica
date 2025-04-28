package com.banco.pagos.messaging;

import com.banco.pagos.model.Pago;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VerificacionEventListener {

    @RabbitListener(queues = "pagos.notificaciones")
    public void recibirParaVerificacion(Pago pago) {
        log.info("✅ [Verificación] Procesando verificación para pago ID [{}], estatus [{}]", pago.getId(), pago.getEstatus());

        // Aquí podrías simular reglas de negocio de verificación antifraude, etc.
    }
}

