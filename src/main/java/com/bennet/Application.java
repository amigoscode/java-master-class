package com.bennet;

import com.bennet.booking.model.BookingRequestDto;
import com.bennet.booking.service.BookingService;
import com.bennet.booking.value_objects.TimeSequence;
import com.bennet.car.model.CarResponseDto;
import com.bennet.car.service.CarService;
import com.bennet.user.model.User;
import com.bennet.user.model.UserRequestDto;
import com.bennet.user.model.UserResponseDto;
import com.bennet.user.service.UserService;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;


@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run( Application.class, args);
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    @Profile("test")
    public CommandLineRunner commandLineRunner(BookingService bookingService,
                                               UserService userService,
                                               CarService carService) {
        return args -> {
            // Initialize database with users if empty
            if (userService.getAllUsers().isEmpty()) {
                log.info("Initializing users in database with faker library");
                List<User> users = IntStream.range(0, 10).mapToObj( i ->
                        new User(
                                faker().name().fullName(),
                                faker().internet().password(),
                                faker().internet().emailAddress()
                                ) )
                        .toList();
                users.forEach( user -> userService.createUser( new UserRequestDto( user.getName(), user.getPassword(), user.getEmail() )) );
                System.out.println("Initialized users in database");
            }

            UserResponseDto user = userService.getAllUsers().getFirst();
            CarResponseDto carResponseDto = carService.getAllCars().getFirst();
            TimeSequence sequence = new TimeSequence( LocalDateTime.now(), LocalDateTime.now().plusDays( 1) );
            BookingRequestDto bookingRequestDto = new BookingRequestDto(user.id(), carResponseDto.id(), sequence);
            bookingService.createBooking( bookingRequestDto, user.email() );

            log.info( "All users: " );
            userService.getAllUsers().forEach( u -> log.info( u.toString() ) );
            carService.getAllCars().forEach( c -> log.info( c.toString() ) );
            bookingService.getAllBookings().forEach( b -> {
                CarResponseDto carResponse = carService.getCarById( b.carId() );
                UserResponseDto userResponseDto = userService.getUserById( b.userId() );
                log.info( "Booking: {} booked {} from {} to {}", userResponseDto.name(), carResponse.brand() + " " + carResponse.model(), b.timeSequence().startDate(), b.timeSequence().endDate() );
            });
        };
    }
}
