
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    private final Patient patient = new Patient(
            "Пётр", "Петров", 25,
            123456, "Грипп", "Иван Иванов"
    );

    private final Doctor doctor = new Doctor(
            "Иван", "Иванов", 45,
            "Терапевт", 101,
            LocalTime.of(9, 0),
            LocalTime.of(18, 0),
            Duration.ofMinutes(30)
    );

    private final LocalDate date =
            LocalDate.of(2026, 10, 20);

    @Test
    void shouldStorePersonalData() {
        assertEquals("Пётр", patient.getName());
        assertEquals("Петров", patient.getSurname());
        assertEquals(25, patient.getAge());
        assertEquals(123456, patient.getPolis());
    }

    @Test
    void shouldStoreDiagnosis() {
        assertEquals("Грипп", patient.getDiagnosis());
    }

    @Test
    void shouldStoreDoctorName() {
        assertEquals("Иван Иванов", patient.getDoctorName());
    }

    @Test
    void shouldInitiallyHaveNoMedications() {
        assertNotNull(patient.getMedications());
        assertTrue(patient.getMedications().isEmpty());
    }

    @Test
    void shouldAddMedication() {
        Medication medication = new Medication(
                "Лекарство",
                date,
                date,
                LocalTime.of(10, 0),
                Duration.ofHours(8),
                Duration.ofMinutes(10)
        );

        patient.addMedication(medication);

        assertTrue(patient.getMedications().contains(medication));
        assertEquals(1, patient.getMedications().size());
    }

    @Test
    void shouldInitiallyHaveEmptyAppointmentHistory() {
        assertNotNull(patient.historyOfAppointments());
        assertTrue(patient.historyOfAppointments().isEmpty());
    }

    @Test
    void shouldAddAppointmentToHistory() {
        Appointment appointment = new Appointment(
                patient, doctor, date,
                LocalTime.of(10, 0),
                LocalTime.of(10, 30)
        );

        patient.addAppointment(appointment);

        assertTrue(
                patient.historyOfAppointments().contains(appointment)
        );
    }
}
