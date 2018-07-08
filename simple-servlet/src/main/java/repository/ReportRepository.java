package repository;

import entity.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Integer> {

/*
    */
/**
     * @param fio - ФИО сотрудника
     * @return - Список сотрудников
     *//*

    @Query("select distinct r.fio from Report r where r.fio = :fio")
    Iterable<String> getEmployees(@Param("fio") String fio);

    @Query("select distinct r.position from Report r where r.position = :position")
    Iterable<String> getPositions(@Param("position") String position);
*/
    @Query("select distinct r.fio from Report r")
    Iterable<String> getEmployees();

    @Query("select distinct r.position from Report r")
    Iterable<String> getPositions();

    @Query("select distinct substring(r.toDate,0,10) from Report r")
    Iterable<String> getDates();
}
