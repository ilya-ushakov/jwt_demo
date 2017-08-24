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
        getUserDb().put("test", Arrays.asList("user"));
        getUserDb().put("sally", Arrays.asList("user", "admin"));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserLogin login)
        throws ServletException {

        System.out.println(String.format("UserController login uri: %s", login));
        if (login.username == null || !getUserDb().containsKey(login.username)) {
            throw new ServletException("Invalid login");
        }
        return new LoginResponse(JWTTokenGenerator.INSTANCE.generateToken(login.username));
    }

    private Map<String, List<String>> getUserDb(){
        return JWTTokenGenerator.INSTANCE.getUserDb();

    }


    @SuppressWarnings("unused")
    private static class UserLogin {
        public String username;
        public String password;

        @Override
        public String toString() {
            return "UserLogin{" +
                    "name='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String accesstoken;
        public String refreshtoken;
        public String token_type;
        public Integer expires_in;
		public String scope;

        public LoginResponse(final String token) {
            System.out.println(String.format("LoginResponse token: %s", token));
            this.accesstoken = token;
            this.refreshtoken= token;
            this.token_type="bearer";
            this.expires_in=2000;
            this.scope="administration compliance search";
        }

        @Override
        public String toString() {
            return "LoginResponse{" +
                    "token='" + accesstoken + '\'' +
                    '}';
        }
    }
}
