package entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Сущность записи табеля
 */
@Entity
@Table(name = "REPORTS")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;             // Идентификатор

    @Column(name = "E_FIO", nullable = false)
    private String fio;         // ФИО сотрудника

    @Column(name = "P_NAME", nullable = false)
    private String position;    // Должность сотрудника

    @Column(name = "F_DATE", nullable = false)
    private Date fromDate;      // Начало пропуска

    @Column(name = "T_DATE", nullable = false)
    private Date toDate;        // Конец пропуска

    @Column(name = "REASON", nullable = false)
    private String reason;      // Причина


    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    /**
     * Возвращает дату без времени в виде строки
     * @return - String dd.MM.yyyy
     */
    public String getFormatDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(getFromDate());
    }

    /**
     * Возвращает диапазон отсутствия сотрудника в виде строки
     * @return - String %02d ч. %02d мин. (с %s по %s)
     */
    public String getFormatTime() {
        long diff = getToDate().getTime() - getFromDate().getTime();
        long h = diff / 3_600_000;
        long m = (diff - h * 3_600_000) / 60_000;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return String.format("%02d ч. %02d мин. (с %s по %s)", h, m, simpleDateFormat.format(getFromDate()), simpleDateFormat.format(getToDate()));
    }
}
