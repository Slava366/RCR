package repository;

import entity.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий сотрудника
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
}