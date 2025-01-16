package com.natvalentine;

import com.natvalentine.gateway.IBusEvent;
import com.natvalentine.generics.domain.DomainEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class BusAdapter implements IBusEvent {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    public BusAdapter(RabbitTemplate rabbitTemplate, RabbitProperties rabbitProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitProperties = rabbitProperties;
    }

    @Override
    public void sendEventUserCreated(DomainEvent event) {
        rabbitTemplate.convertAndSend(rabbitProperties.getUserExchange(), rabbitProperties.getUserRoutingKey(), event);
    }

    @Override
    public void sendEventBoardCreated(DomainEvent event) {
        rabbitTemplate.convertAndSend(rabbitProperties.getBoardCreatedExchange(), rabbitProperties.getBoardCreatedRoutingKey(), event);
    }

    @Override
    public void sendEventPlayerCreated(DomainEvent event) {
        rabbitTemplate.convertAndSend(rabbitProperties.getBoardCreatedExchange(), rabbitProperties.getBoardCreatedRoutingKey(), event);
    }


}
