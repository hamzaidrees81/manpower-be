package com.manpower.repository;

import com.manpower.model.Asset;
import com.manpower.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {
  public Optional<Timesheet> getTimesheetByAssetIdAndTimesheetDateBetween(Integer assetId, LocalDate timesheetDateAfter, LocalDate timesheetDateBefore);
  public List<Timesheet> findByAssetIdAndTimesheetDateIn(Integer assetId, List<LocalDate> timesheetDates);

}
