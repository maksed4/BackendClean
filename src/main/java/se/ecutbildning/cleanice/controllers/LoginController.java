package se.ecutbildning.cleanice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ecutbildning.cleanice.entities.dto.CustomerDTO;
import se.ecutbildning.cleanice.entities.dto.LoginRequestDTO;
import se.ecutbildning.cleanice.entities.dto.SignUpRequestDTO;
import se.ecutbildning.cleanice.entities.dto.WhoAmIDTO;
import se.ecutbildning.cleanice.security.service.LoginService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:3000"}, methods = {
        RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.GET
})
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return loginService.login(loginRequestDTO.username(), loginRequestDTO.password());
    }

    @GetMapping("/whoami")
    public WhoAmIDTO whoAmI(@RequestParam String token) {
        return loginService.whoAmI(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        return loginService.signUp(
                signUpRequestDTO.username(),
                signUpRequestDTO.password(),
                signUpRequestDTO.email(),
                signUpRequestDTO.roles()
        );
    }

    @PostMapping("/customer-signup")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return loginService.customerSignUp(
                customerDTO.id(),
                customerDTO.firstname(),
                customerDTO.lastname(),
                customerDTO.address(),
                customerDTO.zipcode(),
                customerDTO.city(),
                customerDTO.customerType()
        );
    }

}
