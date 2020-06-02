package se.ecutb.cai.fullstack_todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.ecutb.cai.fullstack_todo.data.AppUserRepository;
import se.ecutb.cai.fullstack_todo.data.AppUserRoleRepository;
import se.ecutb.cai.fullstack_todo.entity.AppUser;
import se.ecutb.cai.fullstack_todo.entity.AppUserRole;
import se.ecutb.cai.fullstack_todo.security.AppUserPrincipal;

import java.time.LocalDate;
import java.util.*;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {


    private AppUserRepository appUserRepository;
    private AppUserRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AppUser registerAppUser(String firstName, String lastName, String email, String password, LocalDate regDate, boolean isAdmin) {
        AppUser newUser = new AppUser(email, firstName, lastName, regDate, bCryptPasswordEncoder.encode(password));
        List<AppUserRole> appRoleList = new ArrayList<>();

        if (isAdmin) {
            AppUserRole admin = appRoleRepository.findByRole("ADMIN").orElseThrow(IllegalArgumentException::new);
            appRoleList.add(admin);
        }

        AppUserRole appUserRole = appRoleRepository.findByRole("USER").orElseThrow(IllegalArgumentException::new);
        appRoleList.add(appUserRole);

        newUser.setAppUserRoleList(appRoleList);
        return appUserRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> optional = appUserRepository.findByEmailIgnoreCase(email);
        AppUser user = optional.orElseThrow(() -> new IllegalArgumentException("Email " + email + " could not be found"));

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (AppUserRole role : user.getAppUserRoleList()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(authority);
        }
        return new AppUserPrincipal(user, authorities);
    }

}
