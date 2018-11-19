package com.example.flights;

import com.example.flights.security.Role;
import com.example.flights.security.RoleRepository;
import com.example.flights.security.User;
import com.example.flights.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("admin@admin.com", passwordEncoder.encode("password"), "Admin", "User", true, "admin", "Site Super Admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        user = new User("jim@gmail.com", passwordEncoder.encode("password"), "Jim", "Jimmerson", true, "jim", "VIP user");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        // ---- initial flights data load

        Airport atl = new Airport("ATL", "Hartsfield–Jackson Atlanta International Airport", "Atlanta, GA", 50251962);
        airportRepository.save(atl);
        Airport lax = new Airport("LAX", "Los Angeles International Airport", "Los Angeles, CA", 41232416);
        airportRepository.save(lax);
        Airport ord = new Airport("ORD", "O'Hare International Airport", "Chicago, IL", 38593028);
        airportRepository.save(ord);
        Airport jfk = new Airport("JFK", "John F. Kennedy International Airport", "New York, NY", 29533154);
        airportRepository.save(jfk);
        Airport fll = new Airport("FLL", "Fort Lauderdale–Hollywood International Airport", "Fort Lauderdale, FL", 16216686);
        airportRepository.save(fll);
        Airport iad = new Airport("IAD", "Washington Dulles International Airport", "Washington, D.C.", 11407107);
        airportRepository.save(iad);

        flightRepository.save(new Flight("F2501", jfk, fll, "2018-11-19", "2018-11-19", 180, 120.2, 500.0, "A321"));
        flightRepository.save(new Flight("F2501", jfk, fll, "2018-11-20", "2018-11-20", 181, 119.0, 481.2, "A321"));
        flightRepository.save(new Flight("F9011", jfk, fll, "2018-11-19", "2018-11-19", 195, 110.2, 494.0, "A320"));
        flightRepository.save(new Flight("F9011", jfk, fll, "2018-11-20", "2018-11-20", 200, 110.2, 494.0, "A320"));
        flightRepository.save(new Flight("F3513", atl, ord, "2018-11-20", "2018-11-20", 108, 90.9, 234.0, "E75L"));
        flightRepository.save(new Flight("F4978", ord, jfk, "2018-11-20", "2018-11-20", 180, 134.0, 515.8, "E75L"));
        flightRepository.save(new Flight("F0570", jfk, lax, "2018-11-20", "2018-11-20", 200, 145.8, 634.0, "B763"));

    }
}
