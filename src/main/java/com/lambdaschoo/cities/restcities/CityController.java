package com.lambdaschoo.cities.restcities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;


@Slf4j
@RestController
public class CityController {
    private final CityRepository cityrepos;
    private final RabbitTemplate rt;

    public CityController(CityRepository cityrepos, RabbitTemplate rt){
        this.cityrepos = cityrepos;
        this.rt = rt;
    }

    @GetMapping("/cities/afford")
    public void findSome() {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());


        /* ---------------- S E C R E T   Q U E U E ----------------*/
        for (City c : cities) {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);
            if (randBool) {
                log.info("QUEUE_SECRET");
                rt.convertAndSend(RestcitiesApplication.QUEUE_SECRET, message);
            }
        }
        /* -------------  put all NON secret messages with affordability index < 6 in the cities1 queue ----------------------*/
        for (City c : cities) {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);
            if (!randBool && c.getAffordabilityIndex() < 6) {
                log.info("QUEUE1");
                rt.convertAndSend(RestcitiesApplication.QUEUE_CITY_1, message);
            }
        }
        for (City c : cities) {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);
            if (!randBool && c.getAffordabilityIndex() >= 6) {
                log.info("QUEUE2");
                rt.convertAndSend(RestcitiesApplication.QUEUE_CITY_2, message);
            }
        }
    }
}
