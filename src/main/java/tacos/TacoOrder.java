package tacos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Data
@Document // 1. Используется вместо @Entity и @Table [1, 2]
public class TacoOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // 2. Используется аннотация из org.springframework.data.annotation [1]
    private String id; // 3. Тип изменен на String для автогенерации ID в MongoDB [3]

    // 4. Убраны аннотации @ManyToOne и @JoinColumn.
    // В MongoDB объект User будет храниться как вложенный документ [2]
    private User user;

    // 5. Убраны аннотации @ManyToMany и @JoinTable.
    // Список объектов Taco будет вложен в документ заказа [2]
    private List<Taco> tacos = new ArrayList<>();

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[0-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message = "Invalid CVV")
    private String ccCVV;

    // 6. Инициализация даты сразу, так как @PrePersist не работает в MongoDB [2, 3]
    private Date placedAt = new Date();

    public void addTaco(Taco saved) {
        this.tacos.add(saved);
    }
}