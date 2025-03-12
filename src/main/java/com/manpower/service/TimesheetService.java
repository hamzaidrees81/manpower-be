package com.manpower.service;

import com.manpower.common.Contants;
import com.manpower.model.AssetProject;
import com.manpower.model.Timesheet;
import com.manpower.repository.AssetProjectRepository;
import com.manpower.repository.TimesheetRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimesheetService {
    private final TimesheetRepository timesheetRepository;
    private final AssetProjectRepository assetProjectRepository;

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

    public Optional<List<Timesheet>> getTimesheetByAssetYearMonth(Integer assetId, String year, String month) {

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
     * @param
     * @return
     */
    @Transactional
    public void updateTimesheetForMonth(List<Timesheet> timesheets) {

        if(timesheets.isEmpty()) {
            return;
        }

        Integer assetId = timesheets.getFirst().getAsset().getId();
        if(assetId == null || assetId.equals(0)) {throw new RuntimeException("Invalid asset id");}

        // Get all timesheets for this asset in the given month in ONE query
        List<LocalDate> timesheetDates = timesheets.stream()
          .map(Timesheet::getTimesheetDate)
          .collect(Collectors.toList());


        List<Timesheet> existingTimesheets = timesheetRepository
          .findByAssetIdAndTimesheetDateIn(assetId, timesheetDates);

        // Convert to a map for fast lookup
        Map<Map.Entry<LocalDate, Byte>, Timesheet> existingTimesheetMap = existingTimesheets.stream()
          .collect(Collectors.toMap(ts -> Map.entry(ts.getTimesheetDate(), ts.getRateType()), // Composite key
            Function.identity()));

        List<Timesheet> timesheetsToUpdate = new ArrayList<>();
        List<Timesheet> timesheetsToInsert = new ArrayList<>();

        Map<Integer, AssetProject> assetProjectMap = new HashMap<>();

        for (Timesheet timesheetForDay : timesheets) {

            AssetProject assetProject;

            if(assetProjectMap.containsKey(timesheetForDay.getAsset().getId())) {
                assetProject = assetProjectMap.get(timesheetForDay.getAsset().getId());
            }
            else {
                //get rate for this project entry if not exist in map
                Optional<AssetProject> assetProjectTemp = assetProjectRepository.findById(timesheetForDay.getAssetProject().getId());

                if (assetProjectTemp.isEmpty()) {
                    throw new RuntimeException("Invalid asset project");
                }
                assetProjectMap.put(assetProjectTemp.get().getId(), assetProjectTemp.get());
                assetProject = assetProjectTemp.get();
            }

            Timesheet existing = existingTimesheetMap.get(Map.entry(timesheetForDay.getTimesheetDate(), timesheetForDay.getRateType()));
            if (existing != null) {
                // Update existing timesheet
                existing.setHours(timesheetForDay.getHours());
                existing.setRateType(timesheetForDay.getRateType());
                existing.setInvoiceNumber(timesheetForDay.getInvoiceNumber());
                existing.setRate(getRate(assetProject, existing.getRateType()));
                existing.setRatePaid(getPaidRate(assetProject, existing.getRateType()));
                timesheetsToUpdate.add(existing);
            } else {
                // Prepare new timesheet entry
                timesheetForDay.setRate(getRate(assetProject, timesheetForDay.getRateType()));
                timesheetForDay.setRatePaid(getPaidRate(assetProject, timesheetForDay.getRateType()));
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

    private BigDecimal getRate(AssetProject assetProject, @NotNull Byte rateType) {
        if(rateType == Contants.RateType.REGULAR.getValue())
            return assetProject.getRegularRate();
        else
            return assetProject.getOvertimeRate();
    }

    private BigDecimal getPaidRate(AssetProject assetProject, @NotNull Byte rateType) {
        if(rateType == Contants.RateType.REGULAR.getValue())
            return assetProject.getRegularRatePaid();
        else
            return assetProject.getOvertimeRatePaid();
    }

    public Optional<List<Timesheet>> getTimesheetByAssetOnProjectBetweenDate(Integer assetId, Integer assetProjectId, @NotNull LocalDate startDate, @NotNull LocalDate endDate) {
        return timesheetRepository.getTimesheetsByAssetIdAndAssetProjectIdAndTimesheetDateBetween(assetId, assetProjectId, startDate, endDate);
    }

    public List<Timesheet> getTimesheetByAssetAndProjectAndDateRange(Integer assetId, Integer assetProjectId, LocalDate startDate, LocalDate endDate) {
        return timesheetRepository.findByAssetIdAndAssetProjectIdAndTimesheetDateBetween(assetId, assetProjectId, startDate, endDate);
    }
}
