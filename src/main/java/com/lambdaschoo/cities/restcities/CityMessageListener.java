package com.lambdaschoo.cities.restcities;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class CityMessageListener {
    @RabbitListener(queues = RestcitiesApplication.QUEUE_CITY_1)
    public void receiveMessage(CityMessage cm){
        log.info("Recieved QUEUE_CITY_1: {}", cm.toString());
    }
    @RabbitListener(queues = RestcitiesApplication.QUEUE_SECRET)
    public void receiveSecretMsg(CityMessage cm){
        log.info("Recieved QUEUE_SECRET: {}", cm.toString());
    }
    @RabbitListener(queues = RestcitiesApplication.QUEUE_CITY_2)
    public void receiveCity2Msg(CityMessage cm){
        log.info("Recieved QUEUE_CITY_2: {}", cm.toString());
    }

}
