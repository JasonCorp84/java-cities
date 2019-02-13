package com.lambdaschoo.cities.restcities;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class CityMessageListener {
    @RabbitListener(queues = RestcitiesApplication.QUEUE_CITY_1)
    public void reciveMessage(CityMessage cm){
        log.info("Recieved Nessage: {}", cm.toString());
    }
}
