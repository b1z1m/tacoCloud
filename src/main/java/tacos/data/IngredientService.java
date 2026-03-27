package tacos.data;

import tacos.Ingredient;

public interface IngredientService {

    // Метод для получения списка всех ингредиентов
    Iterable<Ingredient> findAll();

    // Метод для добавления нового ингредиента
    Ingredient addIngredient(Ingredient ingredient);

}