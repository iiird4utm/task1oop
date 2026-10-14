import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2026, 10, 12);

        Doctor doctor = new Doctor(
                "Aleksei",
                "Ivanov",
                40,
                "Therapist",
                101,
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                Duration.ofMinutes(30)
        );

        Patient patient = new Patient(
                "Vladimir",
                "Petrov",
                25,
                234512,
                "Headache",
                doctor.getFullName()
        );

        Medication medication = new Medication(
                "Paracetamol",
                date,
                date.plusDays(3),
                LocalTime.of(10, 0),
                Duration.ofHours(8),
                Duration.ofMinutes(10)
        );

        patient.addMedication(medication);

        System.out.println("Пациент: "
                + patient.getFullName());

        System.out.println("Возраст: "
                + patient.getAge());

        System.out.println("Полис: "
                + patient.getPolis());

        System.out.println("Запись: "
                + doctor.recordTime(
                patient, date, LocalTime.of(10, 15)));

        System.out.println("Запись вне рабочего дня: "
                + doctor.recordTime(
                patient, date, LocalTime.of(18, 0)));

        System.out.println("Записи пациента:");

        for (Appointment appointment
                : patient.historyOfAppointments()) {
            System.out.println(appointment);
        }
    }
}