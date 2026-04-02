package tacos.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

/**
 * Класс-хранитель конфигурационных свойств для взаимодействия с API Taco Cloud.
 * Используется, чтобы избежать жесткого кодирования (hardcoding) URL-адресов в коде [2].
 */
@Data // Генерирует геттеры, сеттеры, toString и другие методы через Lombok [3, 4]
@ConfigurationProperties(prefix = "tacocloud.api") // Связывает поля класса с настройками в application.yml (префикс tacocloud.api) [2, 5]
@Component // Регистрирует класс как bean-компонент в контексте Spring для внедрения в другие сервисы [6, 7]
public class ApiProperties {

    /**
     * URL-адрес конечной точки REST API (например, http://localhost:8080/orders/fromEmail),
     * куда будут отправляться заказы, извлеченные из электронных писем [2].
     */
    private String url;

}