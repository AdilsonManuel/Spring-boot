package com.apress.azm.EnterpriseResourcePlanning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnterpriseResourcePlanningApplication
{

    public static void main (String[] args)
    {
        SpringApplication.run (EnterpriseResourcePlanningApplication.class, args);
    }
    
//    @Bean
//    PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }
    
//    @Bean
//    CommandLineRunner run (UserService userService)
//    {
//        return args ->
//        {
//            userService.saveRole (new RoleDTO ("", "ROLE_USER"));
//            userService.saveRole (new RoleDTO ("", "ROLE_ADMIN"));
//            userService.saveRole (new RoleDTO ("", "ROLE_MANAGER"));
//            userService.saveRole (new RoleDTO ("", "ROLE_SUPER_ADMIN"));
//
//            userService.saveUser (new UserDTO ("", "Adilson Manuel", "AM", "testing123", new ArrayList<> ()));
//            userService.saveUser (new UserDTO ("", "Arnaldo Zumba", "ArZ", "testing123", new ArrayList<> ()));
//            userService.saveUser (new UserDTO ("", "Antónia Zumba", "AZ", "testing123", new ArrayList<> ()));
//            userService.saveUser (new UserDTO ("", "Lidiany Zumba", "LZ", "testing123", new ArrayList<> ()));
//
//            userService.addRoleToUser ("AM", "ROLE_SUPER_ADMIN");
//            userService.addRoleToUser ("ArZ", "ROLE_ADMIN");
//            userService.addRoleToUser ("AZ", "ROLE_MANAGER");
//            userService.addRoleToUser ("LZ", "ROLE_USER");
//        };
//    }

}
