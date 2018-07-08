package repository;

import entity.Position;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий должности
 */
public interface PositionRepository extends CrudRepository<Position, Integer> {
}
