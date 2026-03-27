package tacos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id; // Изменено
import org.springframework.data.mongodb.core.mapping.Document; // Добавлено
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true) // Рекомендуется PRIVATE для MongoDB
@RequiredArgsConstructor
@Document // Вместо @Entity и @Table [3]
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id // Из пакета org.springframework.data.annotation [4]
    private String id; // Изменено с Long на String для автогенерации MongoDB [5]

    private final String username;
    private final String password;
    private final String fullname; // В источниках обычно используется fullname [6]
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}