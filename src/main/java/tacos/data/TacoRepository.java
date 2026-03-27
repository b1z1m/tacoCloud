package tacos.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import tacos.Taco;

// Наследуем PagingAndSortingRepository для поддержки findAll(Pageable)
public interface TacoRepository
        extends PagingAndSortingRepository<Taco, String>,
        CrudRepository<Taco, String> {
}