import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Medication {

    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalTime firstDoseTime;
    private final Duration periodicity;
    private final Duration doseDuration;

    public Medication(String name,
                      LocalDate startDate,
                      LocalDate endDate,
                      LocalTime firstDoseTime,
                      Duration periodicity,
                      Duration doseDuration) {

        if (name == null || name.isBlank()
                || startDate == null
                || endDate == null
                || endDate.isBefore(startDate)
                || firstDoseTime == null
                || periodicity == null
                || doseDuration == null
                || periodicity.isZero()
                || periodicity.isNegative()
                || doseDuration.isZero()
                || doseDuration.isNegative()) {

            throw new IllegalArgumentException(
                    "Некорректное назначение лекарства");
        }

        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.firstDoseTime = firstDoseTime;
        this.periodicity = periodicity;
        this.doseDuration = doseDuration;
    }

    public String getName() {
        return name;
    }

    public Duration getPeriodicity() {
        return periodicity;
    }


}