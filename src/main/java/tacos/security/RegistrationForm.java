package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data; // Необходимо добавить импорт
import tacos.User;

@Data // Необходимо добавить аннотацию для работы сеттеров и геттеров [1]
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname; // В книге используется fullname [1]
    private String street;
    private String city;
    private String state;
    private String zip;      // Исправлено: в User у вас поле zip
    private String phone;    // В книге используется phone [1]

    public User toUser(PasswordEncoder passwordEncoder) {
        // Порядок аргументов должен совпадать с объявлением полей в классе User [1]
        return new User(
                username,
                passwordEncoder.encode(password),
                fullname,
                street,
                city,
                state,
                zip,
                phone);
    }
}