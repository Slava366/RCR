package controllers;

import entity.Employee;
import entity.Position;
import entity.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repository.EmployeeRepository;
import repository.PositionRepository;
import repository.ReportRepository;
import simple.Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class UiController {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ReportRepository reportRepository;


    /**
     * Конструктор
     * @param employeeRepository - репозиторий сотрудника
     * @param positionRepository - репозиторий должности
     * @param reportRepository - репозиторий записи табеля
     */
    public UiController(EmployeeRepository employeeRepository, PositionRepository positionRepository, ReportRepository reportRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.reportRepository = reportRepository;
    }


    /**
     * Добавление сотрудника
     * @param fio - ФИО
     * @param position - должность
     * @param date - дата отсутствия
     * @param fromTime - с какого времени
     * @param toTime - по какое время
     * @param reason - причина
     * @param sent - подтверждение отправки
     * @param model - ответ форме
     * @return - add.html
     */
    @RequestMapping("/add")
    public String addUser(@RequestParam(value = "fio", defaultValue = "") String fio,
                          @RequestParam(value = "position", defaultValue = "") String position,
                          @RequestParam(value = "date", defaultValue = "0000-00-00") String date,
                          @RequestParam(value = "fromTime", defaultValue = "00:00") String fromTime,
                          @RequestParam(value = "toTime", defaultValue = "00:00") String toTime,
                          @RequestParam(value = "reason", defaultValue = "") String reason,
                          @RequestParam(value = "sent", defaultValue = "") String sent,
                          Model model) {
        // Формируем ответ
        boolean fioAdded, positionAdded, dateAdded, timeAdded, reasonAdded;
        fioAdded = positionAdded = dateAdded = timeAdded = reasonAdded = true;
        boolean employeeAdded = false;
        Date fromDate = null;
        Date toDate = null;
        if(!sent.isEmpty()) {
            // Проверяем и редактируем ФИО
            fio = fio.trim();
            fioAdded = !fio.isEmpty() & fio.matches("([A-Za-zА-Яа-я]+-?[A-Za-zА-Яа-я]+ ){1,2}[A-Za-zА-Яа-я]+-?[A-Za-zА-Яа-я]+");
            if(fioAdded) {
                String[] splitFio = fio.split("\\s+");
                for (int i = 0; i < 3; i++) {
                    splitFio[i] = splitFio[i].substring(0, 1).toUpperCase() + splitFio[i].substring(1);
                }
                fio = String.join(" ", splitFio);
            }
            // Проверяем и редактируем должность
            position = position.trim();
            positionAdded = !position.isEmpty() & position.matches("[A-Za-zА-Яа-я]+");
            if(positionAdded) position = position.substring(0, 1).toUpperCase() + position.substring(1);
            // Проверяет дату
            dateAdded = !date.equals("0000-00-00");
            // Проверяем время
            timeAdded = !(fromTime.equals("00:00") || toTime.equals("00:00"));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                fromDate = simpleDateFormat.parse(date + " " + fromTime);
                toDate = simpleDateFormat.parse(date + " " + toTime);
                if(toDate.getTime() - fromDate.getTime() <= 0) timeAdded = false;
            } catch (ParseException e) {
                timeAdded = false;
            }
            // Проверяем причину
            reasonAdded = !reason.isEmpty();
            // Оцуниваем резульат проверок
            if(fioAdded && positionAdded && dateAdded && timeAdded && reasonAdded) {
                // Добавляем запись в табель
                Report report = new Report();
                report.setFio(fio);
                report.setPosition(position);
                report.setFromDate(fromDate);
                report.setToDate(toDate);
                report.setReason(reason);
                reportRepository.save(report);
                employeeAdded = true;
                LOG.info(String.format("Сотрудник '%s' добавлен в табель!", report.getFio()));
            } else {
                // Отправляем уже введенные поля в форму
                if(fioAdded) model.addAttribute("fio", fio);
                if(positionAdded) model.addAttribute("posName", position);
                if(dateAdded) model.addAttribute("date", date);
                if(timeAdded) {
                    model.addAttribute("fromTime", fromTime);
                    model.addAttribute("toTime", toTime);
                }
                if(reasonAdded) model.addAttribute("reason", reason);
            }
        }
        // Отправляем ответ
        model.addAttribute("fioAdded", fioAdded);
        model.addAttribute("positionAdded", positionAdded);
        model.addAttribute("dateAdded", dateAdded);
        model.addAttribute("timeAdded", timeAdded);
        model.addAttribute("reasonAdded", reasonAdded);
        model.addAttribute("employeeAdded", employeeAdded);
        // Передаем список сотрудников
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        // Передаем список должностей
        Iterable<Position> positions = positionRepository.findAll();
        model.addAttribute("positions", positions);
        return "add";
    }


    /**
     * Управляемый табель
     * @param reportId - запись в табеле на удаление
     * @param fio - ФИО сотрудника для поиска
     * @param position - должность для поиска
     * @param date - дата для поиска
     * @param model - ответ форме
     * @return - report.html
     */
    @RequestMapping("/report")
    public String reportTable(@RequestParam(value = "reportId", defaultValue = "") String reportId,
                              @RequestParam(value = "fio", defaultValue = "") String fio,
                              @RequestParam(value = "position", defaultValue = "") String position,
                              @RequestParam(value = "date", defaultValue = "0000-00-00") String date,
                              Model model) {
        // Удаляем выбранную запись
        boolean reportDeleted = false;
        if(!reportId.isEmpty()) {
            reportRepository.delete(Integer.parseInt(reportId));
            reportDeleted = true;
            LOG.info(String.format("Сотрудник с идентификатором '%s' удален из табеля!", reportId));
        }
        model.addAttribute("reportDeleted", reportDeleted);
        // Передаем список сотрудников для сортировки
        Iterable<String> employees = reportRepository.getEmployees();
        model.addAttribute("employees", employees);
        // Передаем список должностей
        Iterable<String> positions = reportRepository.getPositions();
        model.addAttribute("positions", positions);
        // Передаем список дат
        Iterable<String> dates = reportRepository.getDates();
        model.addAttribute("dates", dates);
        // Передаем необходимые записи табеля
        Iterable<Report> reports = reportRepository.getReports(fio, position, date);
        model.addAttribute("reports", reports);
        // Возвращаем введенные поля
        model.addAttribute("fio", fio);
        model.addAttribute("position", position);
        model.addAttribute("date", date);
        return "report";
    }
}
