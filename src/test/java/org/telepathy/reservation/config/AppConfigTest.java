package org.telepathy.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telepathy.reservation.controller.RoomServiceController;
import org.telepathy.reservation.service.DataService;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfigTest {

    @Bean
    public DataService dataService(){return new DataService();}

    @Bean
    public RoomServiceController roomServiceController(){return new RoomServiceController();}

}