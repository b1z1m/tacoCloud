package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.data.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    // Конструктор для внедрения зависимостей
    public RegistrationController(UserRepository userRepo,
                                  PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration"; // Возвращает имя шаблона страницы регистрации [3, 4]
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        // Метод toUser преобразует форму в объект User, шифруя пароль [5]
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login"; // Перенаправление на страницу входа после успеха [3]
    }
}