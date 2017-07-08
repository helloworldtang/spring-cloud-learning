package com.learning.client.rest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * Created by tangcheng on 7/9/2017.
 */
@RestController
public class LoginController {

    @RequestMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {

        Date expiration = LocalDateTime.now().plusDays(30).toDate();

        return Jwts.builder().setIssuer("http://www.hymxys.com")
                .setId(username)
                .setSubject("subject")
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
    }

}
