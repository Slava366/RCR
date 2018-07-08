package repository;

import entity.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


/**
 * Репозиторий табеля
 */
public interface ReportRepository extends CrudRepository<Report, Integer> {

    /**
     * @return - Список сотрудников
     */
    @Query("select distinct r.fio from Report r")
    Iterable<String> getEmployees();


    /**
     * @return - Список должностей
     */
    @Query("select distinct r.position from Report r")
    Iterable<String> getPositions();


    /**
     * @return - Список дат
     */
    @Query("select distinct substring(r.toDate,0,10) from Report r")
    Iterable<String> getDates();


    /**
     * @param fio - ФИО сотрудника
     * @param position - должность сотрудника
     * @param date - дата отсутствия
     * @return - записи, удовлетворяющие запросу
     */
    @Query("select r from Report r " +
            "where (r.fio = :fio or :fio = '') " +
            "and (r.position = :pos or :pos = '') " +
            "and (substring(r.toDate,0,10) = :date or :date = '0000-00-00')"
    )
    Iterable<Report> getReports(@Param("fio") String fio,
                                @Param("pos") String position,
                                @Param("date") String date);
}
