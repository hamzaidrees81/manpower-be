package com.manpower.service;

import com.manpower.model.Timesheet;
import com.manpower.repository.TimesheetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TimesheetService {
    private final TimesheetRepository timesheetRepository;

    public TimesheetService(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    public List<Timesheet> getAllTimesheets() {
        return timesheetRepository.findAll();
    }

    public Optional<Timesheet> getTimesheetById(Integer id) {
        return timesheetRepository.findById(id);
    }

    public Timesheet createTimesheet(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    public void deleteTimesheet(Integer id) {
        timesheetRepository.deleteById(id);
    }

    public Timesheet updateTimesheet(Integer id, Timesheet timesheet) {
        throw new RuntimeException("Not implemented yet");

    }

    public Optional<Timesheet> getTimesheetByAssetYearMonth(Integer assetId, String year, String month) {

        //calculate date for timesheet
        // Convert String to Integer
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);

        YearMonth yearMonth = YearMonth.of(yearInt, monthInt);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        return timesheetRepository.getTimesheetByAssetIdAndTimesheetDateBetween(assetId, startDate, endDate);
    }

    /**
     * iterate over timesheet dates and add hours
     * @param timesheet
     * @return
     */
    @Transactional
    public void updateTimesheetForMonth(List<Timesheet> timesheets) {

        if(timesheets.isEmpty()) {
            return;
        }

        String assetIdStr = timesheets.getFirst().getAsset().getIdNumber();
        if(assetIdStr == null || assetIdStr.isEmpty()) {throw new RuntimeException("Invalid asset id");}

        Integer assetId = Integer.parseInt(assetIdStr);

        // Get all timesheets for this asset in the given month in ONE query
        List<LocalDate> timesheetDates = timesheets.stream()
          .map(Timesheet::getTimesheetDate)
          .collect(Collectors.toList());

        List<Timesheet> existingTimesheets = timesheetRepository
          .findByAssetIdAndTimesheetDateIn(assetId, timesheetDates);

        // Convert to a map for fast lookup
        Map<LocalDate, Timesheet> existingTimesheetMap = existingTimesheets.stream()
          .collect(Collectors.toMap(Timesheet::getTimesheetDate, Function.identity()));

        List<Timesheet> timesheetsToUpdate = new ArrayList<>();
        List<Timesheet> timesheetsToInsert = new ArrayList<>();

        for (Timesheet timesheetForDay : timesheets) {
            Timesheet existing = existingTimesheetMap.get(timesheetForDay.getTimesheetDate());
            if (existing != null) {
                // Update existing timesheet
                existing.setHours(timesheetForDay.getHours());
                existing.setRateType(timesheetForDay.getRateType());
                existing.setInvoiceNumber(timesheetForDay.getInvoiceNumber());
                timesheetsToUpdate.add(existing);
            } else {
                // Prepare new timesheet entry
                timesheetsToInsert.add(timesheetForDay);
            }
        }

        // Batch update and insert to minimize queries
        if (!timesheetsToUpdate.isEmpty()) {
            timesheetRepository.saveAll(timesheetsToUpdate); // Single batch update
        }
        if (!timesheetsToInsert.isEmpty()) {
            timesheetRepository.saveAll(timesheetsToInsert); // Single batch insert
        }
    }
}
