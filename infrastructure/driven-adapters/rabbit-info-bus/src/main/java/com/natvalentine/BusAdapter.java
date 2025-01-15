package com.natvalentine;

import com.natvalentine.gateway.IBusEvent;
import com.natvalentine.generics.domain.DomainEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BusAdapter implements IBusEvent {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    public BusAdapter(RabbitTemplate rabbitTemplate, RabbitProperties rabbitProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitProperties = rabbitProperties;
    }

    @Override
    public void sendEventUserCreated(Mono<DomainEvent> event) {
        event.subscribe(userCreated -> {
                    rabbitTemplate.convertAndSend(rabbitProperties.getUserExchange(), rabbitProperties.getUserRoutingKey(), userCreated);
                }
        );
    }
}
