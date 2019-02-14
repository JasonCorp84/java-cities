package com.lambdaschoo.cities.restcities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


import java.util.ArrayList;
import java.util.Random;

@Slf4j
@Service

public class CityMessageSender {
    private final RabbitTemplate ct;
    private final CityRepository cityRepos;

    public CityMessageSender(RabbitTemplate ct, CityRepository cityRepos) {
        this.ct = ct;
        this.cityRepos = cityRepos;
    }

    @Scheduled(fixedDelay = 30000L)
    public void sendMessage(){
        ArrayList<City> cities = new ArrayList<City>();

        cities.addAll(cityRepos.findAll());

        for(City c: cities) {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);
            if(randBool) {
                log.info("Sending to secret queue");
                ct.convertAndSend(RestcitiesApplication.QUEUE_SECRET);
            }
            if(rand <= 5) {
                log.info("Sending message city1: ");
                ct.convertAndSend(RestcitiesApplication.QUEUE_CITY_1, message);
            } else {
                log.info("Sending message city2");
                ct.convertAndSend(RestcitiesApplication.QUEUE_CITY_2, message);
            }
        }
    }
}
