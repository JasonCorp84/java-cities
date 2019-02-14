package com.lambdaschoo.cities.restcities;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.http.PushBuilder;

@SpringBootApplication
@EnableScheduling
public class RestcitiesApplication {

    public static final String EXCHANGE_NAME = "CsabaServer";
    public static final String QUEUE_SECRET = "SecretQueue";
    public static final String QUEUE_CITY_1 = "CityOneQueue";
    public static final String QUEUE_CITY_2 = "CityTwoQueue";

    public static void main(String[] args) {
        SpringApplication.run(RestcitiesApplication.class, args);
    }


    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue appQueueSecret(){
        return new Queue(QUEUE_SECRET);
    }
    @Bean
    public Binding declareBindingSecret(){
        return BindingBuilder.bind(appQueueSecret()).to(appExchange()).with(QUEUE_SECRET);
    }
    @Bean
    public Queue appQueueCityOne() {
        return new Queue(QUEUE_CITY_1);
    }

    @Bean
    public Binding declareBindingCityOne() {
        return BindingBuilder.bind(appQueueCityOne()).to(appExchange()).with(QUEUE_CITY_1);
    }

    @Bean
    public Queue appQueueCityTwo(){
        return new Queue(QUEUE_CITY_2);
    }

    @Bean
    public Binding declareBindingCityTwo() {
        return BindingBuilder.bind(appQueueCityTwo()).to(appExchange()).with(QUEUE_CITY_2);
    }
    @Bean
    Jackson2JsonMessageConverter producerJacksonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}

