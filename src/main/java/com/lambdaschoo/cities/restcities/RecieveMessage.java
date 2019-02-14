package com.lambdaschoo.cities.restcities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecieveMessage {
    @RabbitListener(queues = RestcitiesApplication.QUEUE_SECRET)
    public void recieveMessage(CityMessage cm){
        log.info("Secret Message: {}", cm.toString());
    }
}
