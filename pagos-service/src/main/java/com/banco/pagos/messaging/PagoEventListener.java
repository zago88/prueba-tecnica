package com.banco.pagos.messaging;

import com.banco.pagos.model.Pago;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PagoEventListener {

    @RabbitListener(queues = "${app.rabbitmq.routing-key}")
    public void recibirCambioEstatus(Pago pago) {
        log.info("📬 [Listener] Evento recibido - Pago ID [{}] nuevo estatus [{}]", pago.getId(), pago.getEstatus());
        
        // Aquí podrías agregar lógica para otros procesos si quieres simular más
    }
}

