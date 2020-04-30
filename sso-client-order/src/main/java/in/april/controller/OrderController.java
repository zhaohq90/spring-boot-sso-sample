package in.april.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/order")
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    public String normal( ) {
        return "This is an order.";
    }

}