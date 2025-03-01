package com.manpower.controller;

import com.manpower.model.Timesheet;
import com.manpower.service.TimesheetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timesheets")
public class TimesheetController {
    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @GetMapping
    public List<Timesheet> getAllTimesheets() {
        return timesheetService.getAllTimesheets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> getTimesheetById(@PathVariable Integer id) {
        return timesheetService.getTimesheetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * get time sheet by month for a resource
     * @param empId
     * @param year
     * @param month
     * @return
     */
    @GetMapping("sheet/{empId}/{year}/{month}")
    public ResponseEntity<Timesheet> getTimesheetByEmployeeYearMonth(@PathVariable Integer empId, @PathVariable String year, @PathVariable String month) {
        return timesheetService.getTimesheetByAssetYearMonth(empId, year, month)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update-timesheet")
    public  ResponseEntity<Void> updateTimesheetForMonth(@RequestBody List<Timesheet> timesheet) {
        timesheetService.updateTimesheetForMonth(timesheet);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public Timesheet createTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetService.createTimesheet(timesheet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Timesheet> updateTimesheet(@PathVariable Integer id, @RequestBody Timesheet timesheet) {
        return ResponseEntity.ok(timesheetService.updateTimesheet(id, timesheet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable Integer id) {
        timesheetService.deleteTimesheet(id);
        return ResponseEntity.noContent().build();
    }
}
