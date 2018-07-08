package entity;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    private static final String TEST_NAME = "Тохман Вячеслав Анатольевич";
    private static final String TEST_POSITION = "Ученик";
    private static final String TEST_REASON = "Болезнь";

    @Test
    void fioTest() {
        Report report = new Report();
        report.setFio(TEST_NAME);
        assertEquals(TEST_NAME, report.getFio());
    }

    @Test
    void positionTest() {
        Report report = new Report();
        report.setPosition(TEST_POSITION);
        assertEquals(TEST_POSITION, report.getPosition());
    }

    @Test
    void fromDateTest() throws ParseException {
        Report report = new Report();
        SimpleDateFormat sdf = new SimpleDateFormat();
        Calendar calendar = sdf.getCalendar();
        calendar.set(2018, Calendar.JULY, 8, 17, 44);
        Date date = calendar.getTime();
        report.setFromDate(date);
        assertEquals(date, report.getFromDate());
    }

    @Test
    void toDateTest() {
        Report report = new Report();
        SimpleDateFormat sdf = new SimpleDateFormat();
        Calendar calendar = sdf.getCalendar();
        calendar.set(2018, Calendar.JULY, 8, 17, 44);
        Date date = calendar.getTime();
        report.setToDate(date);
        assertEquals(date, report.getToDate());
    }

    @Test
    void reasonTest() {
        Report report = new Report();
        report.setReason(TEST_REASON);
        assertEquals(TEST_REASON, report.getReason());
    }

    @Test
    void getFormatDate() {
        Report report = new Report();
        SimpleDateFormat sdf = new SimpleDateFormat();
        Calendar calendar = sdf.getCalendar();
        calendar.set(2018, Calendar.JULY, 8, 17, 44);
        Date date = calendar.getTime();
        report.setFromDate(date);
        assertEquals("08.07.2018", report.getFormatDate());
    }

    @Test
    void getFormatTime() {
        Report report = new Report();
        SimpleDateFormat sdf = new SimpleDateFormat();
        Calendar calendar = sdf.getCalendar();
        calendar.set(2018, Calendar.JULY, 8, 17, 44);
        Date date = calendar.getTime();
        report.setFromDate(date);
        calendar.set(2018, Calendar.JULY, 8, 19, 56);
        date = calendar.getTime();
        report.setToDate(date);
        assertEquals("02 ч. 12 мин. (с 17:44 по 19:56)", report.getFormatTime());
    }
}