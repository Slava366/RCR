package entity;

import javax.persistence.*;

/**
 * Сущность сотрудника
 */
@Entity
@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;         // Идентификатор

    @Column(name = "FIO", unique = true, nullable = false)
    private String fio;     // ФИО


    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
