package com.timekeeping.timekeeping.controllers;

import com.timekeeping.timekeeping.enums.ApprovalStatus;
import com.timekeeping.timekeeping.models.Account;
import com.timekeeping.timekeeping.models.Shift;
import com.timekeeping.timekeeping.models.WorkSchedule;
import com.timekeeping.timekeeping.services.AccountService;
import com.timekeeping.timekeeping.services.ShiftService;
import com.timekeeping.timekeeping.services.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/workSchedules")
public class WorkScheduleController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private WorkScheduleService workScheduleService;

    @GetMapping
    public String getWeeklySchedule(
            @RequestParam(value = "week", required = false) String week,
            @RequestParam(value = "name", required = false) String name,
            Model model) {
        LocalDate startOfWeek;
        List<Account> accounts;

        if (name != null && !name.isEmpty()) {
            accounts = accountService.findByNameEmployee(name);
            model.addAttribute("accounts", accountService.findByNameEmployee(name));
        } else {
            accounts = accountService.findAllEmployees();
            model.addAttribute("accounts", accountService.findAllEmployees());
        }

        if (week != null && !week.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_WEEK_DATE;
            startOfWeek = LocalDate.parse(week + "-1", formatter);  // Assumes week format yyyy-Www
        } else {
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            startOfWeek = LocalDate.now().with(weekFields.dayOfWeek(), 2);
        }
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        List<WorkSchedule> weeklySchedules = workScheduleService.getSchedulesForWeek(startOfWeek, endOfWeek);

        List<String> dayNamesInWeek = startOfWeek.datesUntil(endOfWeek.plusDays(1))
                .map(date -> date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("en"))
                        + "   \n   " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .collect(Collectors.toList());

        List<LocalDate> datesInWeek = startOfWeek.datesUntil(endOfWeek.plusDays(1)).collect(Collectors.toList());

        model.addAttribute("dayNamesInWeek", dayNamesInWeek);
        model.addAttribute("datesInWeek", datesInWeek);
        model.addAttribute("weeklySchedules", weeklySchedules);
        model.addAttribute("shifts", shiftService.getAllShifts());
        model.addAttribute("shift", new Shift());
        model.addAttribute("workSchedule", new WorkSchedule());

        Map<Account, Map<LocalDate, WorkSchedule>> scheduleMap = new HashMap<>();

        for (Account account : accounts) {
            Map<LocalDate, WorkSchedule> dateScheduleMap = new HashMap<>();
            for (LocalDate date : datesInWeek) {
                // Lọc schedule tương ứng với account và ngày cụ thể
                WorkSchedule schedule = workScheduleService.findScheduleForAccountAndDate(account, date, weeklySchedules);
                dateScheduleMap.put(date, schedule);
            }
            scheduleMap.put(account, dateScheduleMap);
        }

        model.addAttribute("scheduleMap", scheduleMap);
        return "workSchedules/index";
    }

    @GetMapping("/view")
    public String viewWeeklySchedule(
            @RequestParam(value = "week", required = false) String week,
            @RequestParam(value = "name", required = false) String name,
            Model model) {
        List<Account> accounts;
        LocalDate startOfWeek;
        List<Shift> shifts = shiftService.getAllShifts();
        if (week != null && !week.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_WEEK_DATE;
            startOfWeek = LocalDate.parse(week + "-1", formatter);  // Assumes week format yyyy-Www
        } else {
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            startOfWeek = LocalDate.now().with(weekFields.dayOfWeek(), 2);
        }

        if (name != null && !name.isEmpty()) {
            accounts = accountService.findByNameEmployee(name);
            model.addAttribute("accounts", accountService.findByNameEmployee(name));
        } else {
            accounts = accountService.findAllEmployees();
            model.addAttribute("accounts", accountService.findAllEmployees());
        }

        LocalDate endOfWeek = startOfWeek.plusDays(6);

        List<WorkSchedule> weeklySchedules = workScheduleService.getSchedulesForWeek(startOfWeek, endOfWeek);

        List<String> dayNamesInWeek = startOfWeek.datesUntil(endOfWeek.plusDays(1))
                .map(date -> date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("en"))
                        + "   \n   " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .collect(Collectors.toList());

        List<LocalDate> datesInWeek = startOfWeek.datesUntil(endOfWeek.plusDays(1)).collect(Collectors.toList());

        Map<String, String> dateWithDayName = new LinkedHashMap<>();
        for (LocalDate date : datesInWeek) {
            dateWithDayName.put(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("en")) , date.format(DateTimeFormatter.ofPattern("dd-MM")));
        }

        model.addAttribute("dateWithDayName", dateWithDayName);
        model.addAttribute("datesInWeek", datesInWeek);
        model.addAttribute("weeklySchedules", weeklySchedules);
        model.addAttribute("shifts", shiftService.getAllShifts());
        model.addAttribute("shift", new Shift());
        model.addAttribute("workSchedule", new WorkSchedule());

        Map<Shift, Map<LocalDate, List<WorkSchedule>>> scheduleMap = new HashMap<>();

        for (Shift shift : shifts) {
            Map<LocalDate, List<WorkSchedule>> dateScheduleMap = new HashMap<>();
            for (LocalDate date : datesInWeek) {
                // Lọc schedule tương ứng với shift và ngày cụ thể
                List<WorkSchedule> schedules = workScheduleService.findScheduleForShiftAndDate(shift.getShiftId(), date);
                dateScheduleMap.put(date, schedules);
            }
            scheduleMap.put(shift, dateScheduleMap);
        }

        model.addAttribute("scheduleMap", scheduleMap);
        return "workSchedules/view";
    }

    @PostMapping("/create")
    public String create(@RequestParam("accountId") Integer accountId,
                         @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                         @RequestParam("shiftId") Integer shiftId,
                         @RequestParam(value = "register", required = false) String register,
                         RedirectAttributes redirectAttributes) {
        if (accountId == null || date == null || shiftId == null) {
            throw new IllegalArgumentException("One or more parameters are invalid");
        }
        WorkSchedule newSchedule = new WorkSchedule();
        Account account = accountService.findById(accountId).orElseThrow();
        Shift shift = shiftService.getShiftById(shiftId).orElseThrow();

        newSchedule.setAccount(account);
        newSchedule.setDate(date);
        newSchedule.setShift(shift);
        newSchedule.setStatus(ApprovalStatus.PENDING);

        workScheduleService.saveSchedule(newSchedule);
        redirectAttributes.addFlashAttribute("successMessage", "Work Schedule saved successfully!");

        if (register != null && !register.isEmpty()) {
            return "redirect:/workSchedules/register";
        }
        return "redirect:/workSchedules";
    }

    @GetMapping("/edit/{id}")
    public String editSchedule(@PathVariable int id, Model model) {
        Optional<WorkSchedule> schedule = workScheduleService.getScheduleById(id);
        if (schedule.isPresent()) {
            model.addAttribute("accounts", accountService.findAllEmployees());
            model.addAttribute("shifts", shiftService.getAllShifts());
            model.addAttribute("shift", schedule.get().getShift());
            model.addAttribute("account", schedule.get().getAccount());
            model.addAttribute("workSchedule", schedule.get());
            return "workSchedules/edit";
        }
        return "redirect:/workSchedules";
    }

    @PostMapping
    public String saveSchedule(@ModelAttribute("workSchedule") WorkSchedule schedule, @RequestParam(value = "register", required = false) String register, RedirectAttributes redirectAttributes) {
        workScheduleService.saveSchedule(schedule);
        redirectAttributes.addFlashAttribute("successMessage", "Work Schedule saved successfully!");
        if (register != null && !register.isEmpty()) {
            return "redirect:/workSchedules/register";
        }
        return "redirect:/workSchedules";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable int id) {
        workScheduleService.deleteSchedule(id);
        return "redirect:/workSchedules";
    }

    @GetMapping("/updateShift")
    public String updateShiftSchedule(@RequestParam("scheduleId") int scheduleId, @RequestParam("shiftId") int shiftId, @RequestParam(value = "register", required = false) String register) {
        workScheduleService.updateShiftSchedule(scheduleId, shiftId);
        if (register != null && !register.isEmpty()) {
            return "redirect:/workSchedules/register";
        }
        return "redirect:/workSchedules";
    }

    @GetMapping("/approval")
    public String approvalSchedule(@RequestParam("id") int id, @RequestParam("status") ApprovalStatus status) {
        workScheduleService.approvalSchedule(id, status);
        return "redirect:/workSchedules";
    }

    @PostMapping("/dragDropWS")
    @ResponseBody
    public ResponseEntity<?> dragDropWS(@RequestBody Map<String, Object> payload) {
        int scheduleIdFrom = Integer.parseInt(payload.get("scheduleIdFrom").toString());
        Object scheduleIdToObj = payload.get("scheduleIdTo");
        Object scheduleToDateObj = payload.get("scheduleToDate");
        Object scheduleToAccountIDObj = payload.get("scheduleToAccountID");
        LocalDate scheduleToNewDate = null;
        Account scheduleToNewAccount = null;
        WorkSchedule scheduleTo = null;

        if (scheduleToDateObj != null && scheduleToAccountIDObj != null) {
            int accountID = Integer.parseInt(scheduleToAccountIDObj.toString());
            scheduleToNewAccount = accountService.findById(accountID).orElseThrow();
            scheduleToNewDate = LocalDate.parse(scheduleToDateObj.toString());
        }

        WorkSchedule scheduleFrom = workScheduleService.findById(scheduleIdFrom);
        if (scheduleIdToObj != null) {
            int scheduleIdTo = Integer.parseInt(scheduleIdToObj.toString());
            scheduleTo = workScheduleService.findById(scheduleIdTo);
        }

        if (scheduleFrom == null) {
            return ResponseEntity.badRequest().body("Invalid schedule ID from.");
        }

        if (scheduleTo != null) {
            Account accountToTemp = scheduleTo.getAccount();
            ApprovalStatus statusToTemp = scheduleTo.getStatus();
            LocalDate dateToTemp = scheduleTo.getDate();
            Shift shiftToTemp = scheduleTo.getShift();

            scheduleTo.setAccount(scheduleFrom.getAccount());
            scheduleTo.setStatus(scheduleFrom.getStatus());
            scheduleTo.setShift(scheduleFrom.getShift());

            scheduleFrom.setAccount(accountToTemp);
            scheduleFrom.setStatus(statusToTemp);
            scheduleFrom.setShift(shiftToTemp);

            workScheduleService.saveSchedule(scheduleFrom);
            workScheduleService.saveSchedule(scheduleTo);
        } else {
            scheduleTo = new WorkSchedule();
            scheduleTo.setAccount(scheduleToNewAccount);
            scheduleTo.setStatus(scheduleFrom.getStatus());
            scheduleTo.setDate(scheduleToNewDate);
            scheduleTo.setShift(scheduleFrom.getShift());

            workScheduleService.saveSchedule(scheduleTo);
            workScheduleService.deleteSchedule(scheduleIdFrom);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("message", "Work schedule updated successfully!"));
    }
}