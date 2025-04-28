package com.banco.pagos.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "pagos.estatus.cambiado";
    public static final String ROUTING_KEY = "estatus.cambiado.general";

    public static final String QUEUE_NOTIFICACIONES = "pagos.notificaciones";
    public static final String QUEUE_VERIFICACION = "pagos.verificacion";

    @Bean
    public TopicExchange pagosExchange() {
        return new TopicExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue notificacionesQueue() {
        return new Queue(QUEUE_NOTIFICACIONES, true);
    }

    @Bean
    public Queue verificacionQueue() {
        return new Queue(QUEUE_VERIFICACION, true);
    }

    @Bean
    public Binding bindingNotificaciones(Queue notificacionesQueue, TopicExchange pagosExchange) {
        return BindingBuilder.bind(notificacionesQueue)
                             .to(pagosExchange)
                             .with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingVerificacion(Queue verificacionQueue, TopicExchange pagosExchange) {
        return BindingBuilder.bind(verificacionQueue)
                             .to(pagosExchange)
                             .with(ROUTING_KEY);
    }
}

