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


    }
}