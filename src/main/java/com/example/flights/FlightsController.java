package com.example.flights;

import com.example.flights.security.RoleRepository;
import com.example.flights.security.User;
import com.example.flights.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    ReservationRepository reservationRepository;

    @Autowired
    ReservedFlightRepository reservedFlightRepository;

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

        FlightSearch flightSearch = new FlightSearch();
        flightSearch.setDirection("OneWay");

        model.addAttribute("flightSearch", flightSearch);
        model.addAttribute("airports", airportRepository.findAll());

        return "search";
    }

    @PostMapping("/processSearch")
    public String processSearch(@Valid FlightSearch flightSearch, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            User currentUser = principal != null ? userRepository.findByUsername(principal.getName()) : null;
            model.addAttribute("user", currentUser);

            flightSearch.setDirection(flightSearch.getDirection() == null ? "OneWay" : flightSearch.getDirection());
            model.addAttribute("airports", airportRepository.findAll());

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

        if ("RoundTrip".equals(flightSearch.getDirection())) {
            ArrayList<Flight> flightsBack = new ArrayList<>();
            for (Flight flight : flightRepository.findAll()) {
                if (flight.getFrom().getCode().equals(flightSearch.getTo().getCode()) &&
                        flight.getTo().getCode().equals(flightSearch.getFrom().getCode())) {
                    flightsBack.add(flight);
                }
            }
            model.addAttribute("flightsBack", flightsBack);
        }

        model.addAttribute("search", flightSearch);
        model.addAttribute("airports", airportRepository.findAll());

        return "search";
    }

    @GetMapping("/reserveFlight/{flightId}")
    public String reserveFlight(@PathVariable("flightId") Long flightId, Model model, Principal principal) {
        Flight flight = flightRepository.findById(flightId).get();

        ReservedFlight reservedFlight = new ReservedFlight(flight, 1, "Economy", "OneWay", null);
        model.addAttribute("reservedFlight", reservedFlight);

        return "reserveFlight";
    }

    @PostMapping("/processReservation")
    public String processReservation(@Valid ReservedFlight reservedFlight, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "reserveFlight";
        }

        User currentUser = principal != null ? userRepository.findByUsername(principal.getName()) : null;

        String curDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
        Reservation reservation = new Reservation(currentUser, curDate);
        reservedFlight.setReservation(reservation);

        reservationRepository.save(reservation);
        reservedFlightRepository.save(reservedFlight);

        return "redirect:/reservations";
    }

    @GetMapping("/reservations")
    public String reservations(Model model, Principal principal) {
        User currentUser = principal != null ? userRepository.findByUsername(principal.getName()) : null;

        List<Reservation> reservations = reservationRepository.findByOwner(currentUser);
        model.addAttribute("reservations", reservations);
        return "reservations";
    }

    @GetMapping("/addFlight")
    public String addFlight(Model model, Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", currentUser);

        Flight flight = new Flight();
        model.addAttribute("flight", flight);
        return "addFlight";
    }

    @PostMapping("/processFlight")
    public String processFlight(@Valid Flight flight, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            // -- This is to prevent "Welcome null" message in the header
            User currentUser = userRepository.findByUsername(principal.getName());
            model.addAttribute("user", currentUser);

            return "addFlight";
        }

        flightRepository.save(flight);
        return "redirect:/flights";
    }

    /*@GetMapping("/checkout")
    public String checkout(@Valid ReservedFlight reservedFlight, BindingResult result, Principal principal){
        User currentUser = principal != null ? userRepository.findByUsername(principal.getName()) : null;
        String total =
        return "checkout";
    }*/


}
