/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.api;

import com.apress.azm.UserRegistrationSystem.DTO.RoleDTO;
import com.apress.azm.UserRegistrationSystem.DTO.UserDTO;
import com.apress.azm.UserRegistrationSystem.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author azm
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController
{

    @Autowired
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers ()
    {
        return ResponseEntity.ok ().body (userService.getUsers ());
    }

    @PostMapping("/users/save")
    public ResponseEntity<UserDTO> saveUser (@RequestBody UserDTO userDTO)
    {
        URI uri = URI.create (ServletUriComponentsBuilder.fromCurrentContextPath ().path ("/api/user/save").toUriString ());

        return ResponseEntity.created (uri).body (userService.saveUser (userDTO));
    }

    @PostMapping("/role/save")
    public ResponseEntity<RoleDTO> saveRole (@RequestBody RoleDTO roleDTO)
    {
        URI uri = URI.create (ServletUriComponentsBuilder.fromCurrentContextPath ().path ("/api/role/save").toUriString ());

        return ResponseEntity.created (uri).body (userService.saveRole (roleDTO));
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<?> addToUser (@RequestBody RoleTOUserForm form)
    {
        userService.addRoleToUser (form.getUserName (), form.getRoleName ());
        return ResponseEntity.ok ().build ();
    }

    @GetMapping("/token/refresh")
    public void refreshToken (HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String authorizationHeader = request.getHeader (HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith ("Bearer "))
        {

            try
            {
                String refreshToken = authorizationHeader.substring ("Bearer ".length ());
                Algorithm algorithm = Algorithm.HMAC256 ("secret".getBytes ());

                JWTVerifier jWTVerifier = JWT.require (algorithm).build ();
                DecodedJWT decodedJWT = jWTVerifier.verify (refreshToken);
                String username = decodedJWT.getSubject ();
                UserDTO userDTO = userService.getUser (username);

                String acessToken = JWT.create ().
                        withSubject (userDTO.getUserName ()).
                        withExpiresAt (new Date (System.currentTimeMillis () + 10 * 60 * 1000)).withIssuer (request.getRequestURL ().toString ())
                        .withClaim ("roles", userDTO.getRoles ().stream ().map (RoleDTO::getName).collect (Collectors.toList ()))
                        .sign (algorithm);

                Map<String, String> tokens = new HashMap<> ();

                tokens.put ("accessToken", acessToken);
                tokens.put ("refreshToken", refreshToken);

                response.setContentType (MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper ().writeValue (response.getOutputStream (), tokens);
            }
            catch (Exception e)
            {
//                log.error ("Error logging in: {}", e.getMessage ());
                response.setHeader ("Error", e.getMessage ());
                response.setStatus (HttpStatus.FORBIDDEN.value ());
                Map<String, String> error = new HashMap<> ();

                error.put ("Error_messge", e.getMessage ());

                response.setContentType (MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper ().writeValue (response.getOutputStream (), error);
            }
        }
        else
        {
            throw new RuntimeException ("Refresh token is missing");
        }

    }

}

@Data
class RoleTOUserForm
{

    private String userName;
    private String roleName;
}
