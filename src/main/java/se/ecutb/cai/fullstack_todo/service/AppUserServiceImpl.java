package se.ecutb.cai.fullstack_todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ecutb.cai.fullstack_todo.data.AppUserRepository;
import se.ecutb.cai.fullstack_todo.data.AppUserRoleRepository;
import se.ecutb.cai.fullstack_todo.dto.CreateAppUserForm;
import se.ecutb.cai.fullstack_todo.entity.AppUser;
import se.ecutb.cai.fullstack_todo.entity.AppUserRole;
import se.ecutb.cai.fullstack_todo.entity.Roles;
import se.ecutb.cai.fullstack_todo.security.AppUserPrincipal;

import java.time.LocalDate;
import java.util.*;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    private AppUserRepository appUserRepository;
    private AppUserRoleRepository appUserRoleRepository;
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
   public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository; //används generellt
        this.appUserRoleRepository = appUserRoleRepository; // hämmta roller
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userOptional = appUserRepository.findByUsernameIgnoreCase(username);
        AppUser appUser = userOptional.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " doesnt exist"));

        Collection<GrantedAuthority> authorities = new HashSet<>();
        for(AppUserRole role : appUser.getRoleSet()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole().toString());
            System.err.println(authority.getAuthority());
        }
        return new AppUserPrincipal(appUser, authorities);
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public AppUser registerAppUser(CreateAppUserForm userForm) {
        AppUser newUser = new AppUser(
            userForm.getUsername(),
            userForm.getFirstName(),
            userForm.getLastName(),
                LocalDate.now(),
                passwordEncoder.encode(userForm.getPassword())
        );


        Set<AppUserRole> roleSet = new HashSet<>();

        if(userForm.isAdmin()) {
            AppUserRole admin = appUserRoleRepository.findByRole(Roles.ADMIN).orElseThrow(IllegalArgumentException::new);
            roleSet.add(admin);
        }

        AppUserRole userRole = appUserRoleRepository.findByRole(Roles.USER).orElseThrow(IllegalArgumentException::new);
        roleSet.add(userRole);

        newUser.setRoleSet(roleSet);

        return newUser;
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public Optional<AppUser> findById(int userId) {
        return appUserRepository.findById(userId);
    }

    @Override
    public Optional<AppUser> findByUserName(String username) {
        return appUserRepository.findByUsernameIgnoreCase(username);
    }

    @Override
    public AppUser delete(AppUser appUser) {
        appUserRepository.delete(appUser);
        return appUser;
    }

}
