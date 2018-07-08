package entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "REPORTS")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "E_FIO", nullable = false)
    private String fio;

    @Column(name = "P_NAME", nullable = false)
    private String position;

    @Column(name = "F_DATE", nullable = false)
    private Date fromDate;

    @Column(name = "T_DATE", nullable = false)
    private Date toDate;

    @Column(name = "REASON", nullable = false)
    private String reason;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFormatDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(getFromDate());
    }

    public String getFormatTime() {
        long diff = getToDate().getTime() - getFromDate().getTime();
        long h = diff / 1000 / 60 / 60;
        long m = (diff - h * 60 * 60 * 1000) / 1000 / 60;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return String.format("%02d ч. %02d мин.\n(с %s по %s)", h, m, simpleDateFormat.format(getFromDate()), simpleDateFormat.format(getToDate()));
    }
}
