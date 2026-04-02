package tacos.email;

import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Обработчик сообщений, который завершает поток интеграции,
 * отправляя полученный заказ в REST API Taco Cloud.
 */
@Component
public class OrderSubmitMessageHandler
        implements GenericHandler<EmailOrder> {
    // Обрабатывает полезную нагрузку типа EmailOrder

    private RestTemplate rest;
    private ApiProperties apiProps;

    // Внедрение зависимостей через конструктор
    public OrderSubmitMessageHandler(ApiProperties apiProps, RestTemplate rest) {
        this.apiProps = apiProps;
        this.rest = rest;
    }

/**
* Метод handle вызывается при поступлении сообщения в данный обработчик.
* @param order - полезная нагрузка сообщения (объект заказа из почты)
* @param headers - заголовки сообщения (метаданные)
* @return null, чтобы указать Spring Integration,
 * что это конечный узел потока [5, 6].
*/
    @Override
    public Object handle(EmailOrder order, MessageHeaders headers) {
        // Отправляем заказ POST-запросом на URL, указанный в конфигурации (apiProps)
        rest.postForObject(apiProps.getUrl(), order, String.class);

        // Возврат null важен: если вернуть объект, Spring попытается
        // отправить его в следующий канал, которого здесь нет [6].
        return null;
    }
}