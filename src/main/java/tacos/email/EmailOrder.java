package tacos.email;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import tacos.Taco;

/**
 * Упрощенный объект заказа, создаваемый на основе содержимого
 * электронного письма. Содержит только email автора и список тако [2, 4].
 */
@Data
public class EmailOrder {

    // Email отправителя, извлеченный из письма
    private final String email;

    // Список упрощенных объектов Taco (определенных в этом же пакете)
    private List<Taco> tacos = new ArrayList<>();

    /**
     * Метод для удобного добавления тако в список заказа [4].
     */
    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}