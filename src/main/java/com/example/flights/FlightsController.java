package com.example.flights;

import com.example.flights.security.RoleRepository;
import com.example.flights.security.User;
import com.example.flights.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class FlightsController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirportRepository airportRepository;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/flights")
    public String index(Model model, Principal principal) {
        User currentUser = principal != null ? userRepository.findByUsername(principal.getName()) : null;
        model.addAttribute("user", currentUser);

        model.addAttribute("flights", flightRepository.findAll());
        return "index";
    }

    @RequestMapping("/")
    public String search(Model model, Principal principal) {
        User currentUser = principal != null ? userRepository.findByUsername(principal.getName()) : null;
        model.addAttribute("user", currentUser);

        model.addAttribute("search", new FlightSearch());
        model.addAttribute("airports", airportRepository.findAll());

        return "search";
    }

    @PostMapping("/processSearch")
    public String processSearch(@Valid FlightSearch flightSearch, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            User currentUser = userRepository.findByUsername(principal.getName());
            model.addAttribute("user", currentUser);
            return "search";
        }

        ArrayList<Flight> flights = new ArrayList<>();
        for (Flight flight : flightRepository.findAll()) {
            if (flight.getFrom().getCode().equals(flightSearch.getFrom().getCode()) &&
                    flight.getTo().getCode().equals(flightSearch.getTo().getCode())) {
                flights.add(flight);
            }
        }
        model.addAttribute("flightOptions", flights);

        model.addAttribute("search", flightSearch);
        model.addAttribute("airports", airportRepository.findAll());

        return "search";
    }
}
