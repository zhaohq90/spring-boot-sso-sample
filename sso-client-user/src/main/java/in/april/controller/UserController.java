package in.april.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class UserController {

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    public String user( ) {
        return "This is a user";
    }

    @GetMapping("/medium")
  //  @PreAuthorize("hasAuthority('ROLE_MEDIUM')")
    public String medium() {
        return "medium permission test success";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin() {
        return "This is admin";
    }

    @GetMapping("/login2")
    public String login(HttpServletRequest request,String code,String state){
        log.info("code="+code);
        log.info("state="+state);

        return "end";
    }

}