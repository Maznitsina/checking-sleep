package ru.dream.checkingsleep.repository;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.model.User;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DreamRepository extends JpaRepository<Dream, UUID>, JpaSpecificationExecutor<Dream> {

    Optional<Dream> findById(UUID id);

    void deleteById(UUID id);

    default List<Dream> findByDate(LocalDate date) {
        LocalDateTime dayStart = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime dayEnd = LocalDateTime.of(date, LocalTime.MAX);
        LocalDateTime nextDayStart = dayStart.plusDays(1);

        Specification<Dream> hasDayOrNightInterval = (root, query, cb) -> {
            Predicate dayInterval = cb.and(
                    cb.greaterThanOrEqualTo(root.get("dayStart"), dayStart),
                    cb.lessThanOrEqualTo(root.get("dayFinish"), dayEnd)
            );

            Predicate nightIntervalCurrentDay = cb.and(
                    cb.greaterThanOrEqualTo(root.get("nightStart"), dayStart),
                    cb.lessThanOrEqualTo(root.get("nightFinish"), nextDayStart)
            );

            Predicate nightIntervalSameDay = cb.and(
                    cb.greaterThanOrEqualTo(root.get("nightStart"), dayStart),
                    cb.lessThanOrEqualTo(root.get("nightFinish"), dayEnd)
            );

            Predicate nightIntervalNextDay = cb.and(
                    cb.greaterThanOrEqualTo(root.get("nightStart"), nextDayStart),
                    cb.lessThanOrEqualTo(root.get("nightFinish"), dayEnd)
            );

            return cb.or(dayInterval, nightIntervalCurrentDay, nightIntervalSameDay, nightIntervalNextDay);
        };

        return findAll(hasDayOrNightInterval);
    }
}



