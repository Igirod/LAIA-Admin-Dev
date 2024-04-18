package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.DisabledDate;

@Repository
public interface DisabledDateJpaRepository extends JpaRepository<DisabledDate, Long> {

   @Query(value = "SELECT CAST(e.date AS CHAR) FROM disabled_dates e WHERE calendar_id = ?3 AND e.date BETWEEN ?1 AND ?2", nativeQuery = true)
   List<String> findDisabedDatesByCalendarIdRange(Date startDate, Date endDate, Long calendarId);

   @Query(value = "SELECT CAST(date as CHAR) FROM disabled_dates WHERE id = ?1", nativeQuery = true)
   List<String> findDisabledDatesByCalendarId(Long calendarId);

}
