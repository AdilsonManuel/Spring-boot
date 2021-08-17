/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.Service;

import com.apress.azm.UserRegistrationSystem.dto.UserInfo;
import com.apress.azm.UserRegistrationSystem.repository.UserInfoRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author azm
 */
@Service
public class UserInfoDetailsService implements UserDetailsService
{

    @Autowired
    private UserInfoRepository userInfoRepository;

//    public static final Logger logger
//            = LoggerFactory.getLogger (UserInfoDetailsService.class);

    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException
    {
        UserInfo userInfo = userInfoRepository.findByName (userName);
//        logger.info("User data -> " + userInfo.toString());

        if (userInfo == null)
        {
            throw new UsernameNotFoundException ("Opps! user not found with user-name: " + userName);
        }

        return new org.springframework.security.core.userdetails.User (userInfo.getUserName (), userInfo.getPassword (), getAuthorities (userInfo));
    }

    private Collection<GrantedAuthority> getAuthorities (UserInfo userInfo)
    {
        List<GrantedAuthority> authorities = new ArrayList<> ();
        authorities = AuthorityUtils.createAuthorityList (userInfo.getRole ());

        return authorities;
    }

}
