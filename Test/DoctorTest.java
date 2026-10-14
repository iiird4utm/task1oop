
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    private final Doctor doctor = new Doctor(
            "Иван", "Иванов", 45,
            "Терапевт", 101,
            LocalTime.of(9, 0),
            LocalTime.of(18, 0),
            Duration.ofMinutes(30)
    );

    @Test
    void appointmentInsideWorkingHoursShouldBeAllowed() {
        assertTrue(doctor.isWorkingTime(
                LocalTime.of(10, 0),
                LocalTime.of(10, 30)
        ));
    }

    @Test
    void appointmentOutsideWorkingHoursShouldBeRejected() {
        assertFalse(doctor.isWorkingTime(
                LocalTime.of(8, 30),
                LocalTime.of(9, 0)
        ));
    }

    @Test
    void appointmentDurationShouldBeThirtyMinutes() {
        assertEquals(
                Duration.ofMinutes(30),
                doctor.getAppointmentDuration()
        );
    }
}
