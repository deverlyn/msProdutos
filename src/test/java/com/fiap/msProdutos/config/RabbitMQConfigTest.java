package com.fiap.msProdutos.config;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RabbitMQConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void testPedidosQueueBean() {
        Queue pedidosQueue = context.getBean(Queue.class);
        assertNotNull(pedidosQueue);
    }

    @Test
    void testJsonMessageConverterBean() {
        Jackson2JsonMessageConverter converter = context.getBean(Jackson2JsonMessageConverter.class);
        assertNotNull(converter);
    }

    @Test
    void testRabbitListenerContainerFactoryBean() {
        SimpleRabbitListenerContainerFactory factory = context.getBean(SimpleRabbitListenerContainerFactory.class);
        assertNotNull(factory);
    }
}
