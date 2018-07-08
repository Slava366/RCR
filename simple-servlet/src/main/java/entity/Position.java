package entity;

import javax.persistence.*;

/**
 * Сущность должности
 */
@Entity
@Table(name = "POSITIONS")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;         // Идентификатор

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;    // Название


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
