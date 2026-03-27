package tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tacos.Taco;
import tacos.TacoOrder;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository; // Наужен в data TacoRepository

import java.util.Optional;

@RestController
@RequestMapping(path="/api/tacos", produces="application/json")
// Обрабатвает запросы с путем /api/tacos
@CrossOrigin(origins="http://tacocloud:8080")
// Разрешает обработку межсайтовых запросов
public class TacoController {

    private final TacoRepository tacoRepo;
    private final OrderRepository orderRepo;

    // 2. ОДИН конструктор для всех зависимостей
    public TacoController(TacoRepository tacoRepo, OrderRepository orderRepo) {
        this.tacoRepo = tacoRepo;
        this.orderRepo = orderRepo;
    }

    @GetMapping
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createAt").descending());
        return tacoRepo.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") String id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return ResponseEntity.ok(optTaco.get()); // Статус 200 OK
        }
        return ResponseEntity.notFound().build(); // Статус 404 Not Found
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }

    @PutMapping(path="/{orderId}", consumes="application/json")
    public TacoOrder putOrder(
            @PathVariable("orderId") String orderId, // Изменено с Long на String
            @RequestBody TacoOrder order) {

        order.setId(orderId); // Теперь типы совпадают
        return orderRepo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") String orderId) {
        try {
            orderRepo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e){

        }
    }
}
