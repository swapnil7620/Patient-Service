package com.swapnil.doctor_service.repository;

import com.swapnil.doctor_service.entity.Schedule;
import com.swapnil.doctor_service.enums.DayOfWeek;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByDoctorId(Long doctorId);

    Optional<Schedule> findByIdAndDoctorId(Long scheduleId, Long doctorId);

    boolean existsByDoctorIdAndDayOfWeekAndStartTimeAndEndTime(
            Long doctorId,
            DayOfWeek dayOfWeek,
            LocalTime startTime,
            LocalTime endTime
    );

    @Query("""
       SELECT COUNT(s) > 0
       FROM Schedule s
       WHERE s.doctor.id = :doctorId
       AND s.dayOfWeek = :dayOfWeek
       AND s.startTime < :endTime
       AND s.endTime > :startTime
       """)
    boolean existsOverlappingSchedule(
            @Param("doctorId") Long doctorId,
            @Param("dayOfWeek") DayOfWeek dayOfWeek,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

}
