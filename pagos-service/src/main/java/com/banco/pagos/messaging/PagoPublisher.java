package com.banco.pagos.messaging;

import com.banco.pagos.model.Pago;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PagoPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    public void publicarCambioEstatus(Pago pago) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, pago);
            log.info("Evento publicado a RabbitMQ: Pago ID [{}] nuevo estatus [{}]", pago.getId(), pago.getEstatus());
        } catch (Exception ex) {
            log.error("Error al publicar evento de cambio de estatus", ex);
        }
    }
}

