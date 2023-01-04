package com.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

@Configuration
public class RabbitMQConfig {
    @Bean
    public FanoutExchange fanout () {
        return new FanoutExchange("Auth");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("jwt");}
    @Bean
    public Queue autoDeleteQueue() {
        return new AnonymousQueue();
    }
    @Bean
    public Binding binding (FanoutExchange fanout, Queue autoDeleteQueue){
        return BindingBuilder.bind(autoDeleteQueue).to(fanout);
    }
   /*
    String products1Queue = "authentications1_queue_fanout";

    String products2Queue = "authentications2_queue_fanout";
    String products3Queue = "authentications3_queue_fanout";

    String productsExchange = "authentications_exchange";

    @Bean
    Queue products1Queue() {
        return new Queue(products1Queue, true);
    }

    @Bean
    Queue products2Queue() {
        return new Queue(products2Queue, false);
    }

    @Bean
    Queue products3Queue() {
        return new Queue(products3Queue, false);
    }

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(productsExchange);
    }

    @Bean
    Binding deliveryBinding(Queue products1Queue, FanoutExchange exchange) {
        return BindingBuilder.bind(products1Queue).to(exchange);
    }

    @Bean
    Binding emailBinding(Queue products2Queue, FanoutExchange exchange) {
        return BindingBuilder.bind(products2Queue).to(exchange);
    }

    @Bean
    Binding emailBinding1(Queue products3Queue, FanoutExchange exchange) {
        return BindingBuilder.bind(products3Queue).to(exchange);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        return cachingConnectionFactory;
    }
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(jsonMessageConverter());
        factory.setErrorHandler(errorHandler());
        return factory;
    }
    @Bean
    public ErrorHandler errorHandler() {
        return new ConditionalRejectingErrorHandler(new MyFatalExceptionStrategy());
    }
    public static class MyFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {
        private final Logger logger = LogManager.getLogger(getClass());
        @Override
        public boolean isFatal(Throwable t) {
            if (t instanceof ListenerExecutionFailedException) {
                ListenerExecutionFailedException lefe = (ListenerExecutionFailedException) t;
                logger.error("Failed to process inbound message from queue "
                        + lefe.getFailedMessage().getMessageProperties().getConsumerQueue()
                        + "; failed message: " + lefe.getFailedMessage(), t);
            }
            return super.isFatal(t);
        }
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }*/

}





