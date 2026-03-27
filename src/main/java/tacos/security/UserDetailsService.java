package tacos.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetailsService loadUserBySername(String username) throws UsernameNotFoundException;

}
