/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.client;

import com.apress.azm.UserRegistrationSystem.dto.UserDTO;
import javax.xml.ws.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author azm
 */
public class UserRegistrationClient
{

    private static RestTemplate restTemplate = new RestTemplate ();
    private static final String USER_REGISTRATION_BASE_URL = "http://localhost:8080/api/user/";

    public UserDTO getUserById (final String userId)
    {
        return restTemplate.getForObject (USER_REGISTRATION_BASE_URL + "/{id}", UserDTO.class, userId);
    }

    public UserDTO[] getAllUsers ()
    {
        return restTemplate.getForObject (USER_REGISTRATION_BASE_URL, UserDTO[].class);
    }

    public UserDTO createUser (final UserDTO user)
    {
        return restTemplate.postForObject (USER_REGISTRATION_BASE_URL, user, UserDTO.class);
    }

    public void updateUser (final String userId, final UserDTO userDTO)
    {
        restTemplate.put (USER_REGISTRATION_BASE_URL + "/{id}", userDTO, userId);
    }

    public void deleteUser (final String userId)
    {
        restTemplate.delete (USER_REGISTRATION_BASE_URL + "/[id}", userId);
    }

    public ResponseEntity<UserDTO> getUserByIdUsingExchangeAPI (final String userId)
    {
        HttpEntity<UserDTO> httpEntity = new HttpEntity<UserDTO> (new UserDTO ());

        return restTemplate.exchange (USER_REGISTRATION_BASE_URL + "/{id}", HttpMethod.GET, httpEntity, UserDTO.class, userId);
    }

    public static void main (String[] args)
    {
        UserRegistrationClient userRegistrationClient = new UserRegistrationClient ();

        UserDTO user = userRegistrationClient.getUserById ("61186d387f1bb16a396217f1");

        System.out.println ("User-ID -> " + user.getId () + "User-Name -> " + user.getName ());

        UserDTO userDTO = new UserDTO ();
        userDTO.setName ("Catyana Carneiro");
        userDTO.setAddress ("Luanda-Cassenda");
        userDTO.setEmail ("catyanaC@appress.com");

        UserDTO newUSer = userRegistrationClient.createUser (userDTO);
        System.out.println (newUSer.getId ());

        UserDTO userDTO1 = userRegistrationClient.getUserById ("61185f487f1bb16a396217f0");
        System.out.println ("Old User Email -> " + userDTO1.getEmail ());
        userDTO1.setEmail ("leandroFonsecaS@appress.com");
        System.out.println ("Updated user Email -> " + userDTO1.getEmail ());

        System.out.println ("Old Users lists  -> " + userRegistrationClient.getAllUsers ().length);
        userRegistrationClient.deleteUser ("");

        System.out.println ("New Users lists  -> " + userRegistrationClient.getAllUsers ().length);

        ResponseEntity<UserDTO> responseEntity = userRegistrationClient.getUserByIdUsingExchangeAPI ("");
        UserDTO userDTO2 = responseEntity.getBody ();
        System.out.println (userDTO2.getName ());

    }
}
