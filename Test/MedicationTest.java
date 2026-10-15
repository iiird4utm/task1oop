import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MedicationTest {

    private final LocalDate date =
            LocalDate.of(2026, 10, 20);

    private final Doctor doctor = new Doctor(
            "Иван", "Иванов", 45,
            "Терапевт", 101,
            LocalTime.of(9, 0),
            LocalTime.of(18, 0),
            Duration.ofMinutes(30)
    );

    private final Patient patient = new Patient(
            "Пётр", "Петров", 25,
            123456, "Грипп", "Иван Иванов"
    );

    private final Medication medication = new Medication(
            "Лекарство",
            date,
            date,
            LocalTime.of(10, 0),
            Duration.ofHours(8),
            Duration.ofMinutes(10)
    );

    @Test
    void shouldStoreMedicationName() {
        assertEquals("Лекарство", medication.getName());
    }

    @Test
    void shouldStoreMedicationPeriodicity() {
        assertEquals(
                Duration.ofHours(8),
                medication.getPeriodicity()
        );
    }

    @Test
    void shouldDetectAppointmentDuringMedicationDose() {
        Appointment appointment = new Appointment(
                patient, doctor, date,
                LocalTime.of(10, 5),
                LocalTime.of(10, 20)
        );

        assertTrue(medication.conflictsWith(appointment));
    }

    @Test
    void shouldNotDetectAppointmentOutsideMedicationDose() {
        Appointment appointment = new Appointment(
                patient, doctor, date,
                LocalTime.of(11, 0),
                LocalTime.of(11, 30)
        );

        assertFalse(medication.conflictsWith(appointment));
    }

    @Test
    void shouldRejectZeroPeriodicity() {
        assertThrows(AppointmentException.class, () ->
                new Medication(
                        "Лекарство",
                        date,
                        date,
                        LocalTime.of(10, 0),
                        Duration.ZERO,
                        Duration.ofMinutes(10)
                )
        );
    }

    @Test
    void shouldRejectZeroDoseDuration() {
        assertThrows(AppointmentException.class, () ->
                new Medication(
                        "Лекарство",
                        date,
                        date,
                        LocalTime.of(10, 0),
                        Duration.ofHours(8),
                        Duration.ZERO
                )
        );
    }
}
