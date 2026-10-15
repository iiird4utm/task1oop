import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

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

    private final LocalDate date =
            LocalDate.of(2026, 10, 20);

    @Test
    void shouldStoreAppointmentData() {
        Appointment appointment = new Appointment(
                patient, doctor, date,
                LocalTime.of(10, 0),
                LocalTime.of(10, 30)
        );

        assertEquals(patient, appointment.getPatient());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals(date, appointment.getDate());
        assertEquals(
                LocalTime.of(10, 0),
                appointment.getStart()
        );
        assertEquals(
                LocalTime.of(10, 30),
                appointment.getEnd()
        );
    }

    @Test
    void shouldDetectOverlappingAppointments() {
        Appointment first = new Appointment(
                patient, doctor, date,
                LocalTime.of(10, 0),
                LocalTime.of(10, 30)
        );

        Appointment second = new Appointment(
                patient, doctor, date,
                LocalTime.of(10, 15),
                LocalTime.of(10, 45)
        );

        assertTrue(first.overlaps(second));
    }

    @Test
    void shouldAllowAppointmentsThatTouchAtBoundary() {
        Appointment first = new Appointment(
                patient, doctor, date,
                LocalTime.of(10, 0),
                LocalTime.of(10, 30)
        );

        Appointment second = new Appointment(
                patient, doctor, date,
                LocalTime.of(10, 30),
                LocalTime.of(11, 0)
        );

        assertFalse(first.overlaps(second));
    }

    @Test
    void shouldNotDetectOverlapOnDifferentDates() {
        Appointment first = new Appointment(
                patient, doctor, date,
                LocalTime.of(10, 0),
                LocalTime.of(10, 30)
        );

        Appointment second = new Appointment(
                patient, doctor,
                date.plusDays(1),
                LocalTime.of(10, 0),
                LocalTime.of(10, 30)
        );

        assertFalse(first.overlaps(second));
    }

    @Test
    void shouldThrowAppointmentExceptionForInvalidDoctor() {
        assertThrows(
                AppointmentException.class,
                () -> new Doctor(
                        "Иван", "Иванов", 45,
                        "Терапевт", 101,
                        LocalTime.of(18, 0),
                        LocalTime.of(9, 0),
                        Duration.ofMinutes(30)
                )
        );
    }

    @Test
    void shouldThrowAppointmentExceptionForInvalidPatient() {
        assertThrows(
                AppointmentException.class,
                () -> new Patient(
                        "", "Петров", 25,
                        123456, "Грипп", "Иван Иванов"
                )
        );
    }

    @Test
    void shouldThrowAppointmentExceptionForInvalidMedication() {
        assertThrows(
                AppointmentException.class,
                () -> new Medication(
                        "",
                        date,
                        date.plusDays(3),
                        LocalTime.of(10, 0),
                        Duration.ofHours(8),
                        Duration.ofMinutes(10)
                )
        );
    }
}
