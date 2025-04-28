package com.banco.pagos.messaging;

import com.banco.pagos.config.RabbitMQConfig;
import com.banco.pagos.model.Pago;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VerificacionEventListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_VERIFICACION)
    public void verificarCambioDeEstatus(Pago pago) {
        log.info("✅ Verificación recibida: El pago con ID {} ha cambiado a estatus {}", pago.getId(), pago.getEstatus());
        // Aquí podrías hacer otras acciones, como guardar historial o enviar notificación a otro servicio
    }
}

