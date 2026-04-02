package tacos.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

/**
 * Класс-хранитель свойств для настройки интеграции с электронной почтой.
 * Позволяет типизированно считывать параметры подключения к IMAP-серверу.
 */
@Data // Генерирует геттеры, сеттеры, toString и другие методы через Lombok [3]
@ConfigurationProperties(prefix="tacocloud.email") // Связывает поля класса с переменными из application.yml с префиксом tacocloud.email [2]
@Component // Регистрирует класс как bean-компонент в контексте Spring для внедрения в другие части потока интеграции [1]
public class EmailProperties {

    private String username; // Логин для подключения к почтовому ящику
    private String password; // Пароль для подключения
    private String host;     // Хост IMAP-сервера (например, imap.tacocloud.com)
    private String mailbox;  // Имя почтового ящика для опроса (обычно INBOX)

    /**
     * Частота опроса почтового сервера в миллисекундах.
     * По умолчанию составляет 30 секунд (30000 мс). [2]
     */
    private long pollRate = 30000;

    /**
     * Генерирует полный URL для подключения по протоколу IMAP.
     * Используется адаптером входящего канала для извлечения почты. [2]
     *
     * @return строка формата imaps://username:password@host/mailbox
     */
    public String getImapUrl() {
        return String.format("imaps://%s:%s@%s/%s",
                this.username, this.password, this.host, this.mailbox);
    }
}