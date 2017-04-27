package com.jooq.proto.jwtangspr;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    public UserController() {
        getUserDb().put("tom", Arrays.asList("user"));
        getUserDb().put("sally", Arrays.asList("user", "admin"));
        getUserDb().put("marchin", Arrays.asList("user", "foo"));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserLogin login)
        throws ServletException {

        System.out.println(String.format("UserController login uri: %s", login));
        if (login.name == null || !getUserDb().containsKey(login.name)) {
            throw new ServletException("Invalid login");
        }
        return new LoginResponse(JWTTokenGenerator.INSTANCE.generateToken(login.name));
    }

    private Map<String, List<String>> getUserDb(){
        return JWTTokenGenerator.INSTANCE.getUserDb();

    }


    @SuppressWarnings("unused")
    private static class UserLogin {
        public String name;
        public String password;

        @Override
        public String toString() {
            return "UserLogin{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            System.out.println(String.format("LoginResponse token: %s", token));
            this.token = token;
        }

        @Override
        public String toString() {
            return "LoginResponse{" +
                    "token='" + token + '\'' +
                    '}';
        }
    }
}
