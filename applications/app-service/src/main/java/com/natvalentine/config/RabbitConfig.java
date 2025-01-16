package com.natvalentine.config;

import com.natvalentine.RabbitProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    private final RabbitProperties rabbitProperties;

    public RabbitConfig(RabbitProperties rabbitProperties) {
        this.rabbitProperties = rabbitProperties;
    }

    @Bean
    public TopicExchange userCreatedExchange() {
        return new TopicExchange(rabbitProperties.getUserExchange());
    }

    @Bean
    public Queue userCreatedQueue() {
        return new Queue(rabbitProperties.getUserQueue(), true);
    }

    @Bean
    public Binding userCreatedBinding() {
        return BindingBuilder.bind(userCreatedQueue())
                .to(userCreatedExchange())
                .with(rabbitProperties.getUserRoutingKey());
    }

    @Bean
    public TopicExchange boardCreatedExchange() {
        return new TopicExchange(rabbitProperties.getBoardCreatedExchange());
    }

    @Bean
    public Queue boardCreatedQueue() {
        return new Queue(rabbitProperties.getBoardCreatedQueue(), true);
    }

    @Bean
    public Binding boardCreatedBinding() {
        return BindingBuilder.bind(boardCreatedQueue())
                .to(boardCreatedExchange())
                .with(rabbitProperties.getBoardCreatedRoutingKey());
    }

    @Bean
    public TopicExchange boardUpdatedExchange() {
        return new TopicExchange(rabbitProperties.getBoardUpdatedExchange());
    }

    @Bean
    public Queue boardUpdatedQueue() {
        return new Queue(rabbitProperties.getBoardUpdatedQueue(), true);
    }

    @Bean
    public Binding boardUpdatedBinding() {
        return BindingBuilder.bind(boardUpdatedQueue())
                .to(boardUpdatedExchange())
                .with(rabbitProperties.getBoardUpdatedRoutingKey());
    }

    @Bean
    public TopicExchange playerCreatedExchange() {
        return new TopicExchange(rabbitProperties.getPlayerCreatedExchange());
    }

    @Bean
    public Queue playerCreatedQueue() {
        return new Queue(rabbitProperties.getPlayerCreatedQueue(), true);
    }

    @Bean
    public Binding playerCreatedBinding() {
        return BindingBuilder.bind(playerCreatedQueue())
                .to(playerCreatedExchange())
                .with(rabbitProperties.getPlayerCreatedRoutingKey());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplateBean(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeBeans(AmqpAdmin amqpAdmin) {
        return event -> {
            amqpAdmin.declareExchange(userCreatedExchange());
            amqpAdmin.declareQueue(userCreatedQueue());
            amqpAdmin.declareBinding(userCreatedBinding());

            amqpAdmin.declareExchange(boardCreatedExchange());
            amqpAdmin.declareQueue(boardCreatedQueue());
            amqpAdmin.declareBinding(boardCreatedBinding());

            amqpAdmin.declareExchange(boardUpdatedExchange());
            amqpAdmin.declareQueue(boardUpdatedQueue());
            amqpAdmin.declareBinding(boardUpdatedBinding());

            amqpAdmin.declareExchange(playerCreatedExchange());
            amqpAdmin.declareQueue(playerCreatedQueue());
            amqpAdmin.declareBinding(playerCreatedBinding());
        };
    }

}
