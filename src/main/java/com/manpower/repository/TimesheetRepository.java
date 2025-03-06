package com.manpower.repository;

import com.manpower.model.Timesheet;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {
  public Optional<List<Timesheet>> getTimesheetByAssetIdAndTimesheetDateBetween(Integer assetId, LocalDate timesheetDateAfter, LocalDate timesheetDateBefore);
  public List<Timesheet> findByAssetIdAndTimesheetDateIn(Integer assetId, List<LocalDate> timesheetDates);
  public Optional<List<Timesheet>> getTimesheetsByAssetIdAndAssetProjectIdAndTimesheetDateBetween(Integer assetId, Integer assetProjectId, LocalDate timesheetDateAfter, LocalDate timesheetDateBefore);
  }
