import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Patient implements Person {

    private final String name;
    private final String surname;
    private final int age;
    private final int polis;
    private final String diagnosis;
    private final String doctorName;

    private final List<Appointment> appointments =
            new ArrayList<>();

    private final List<Medication> medications =
            new ArrayList<>();

    public Patient(String name, String surname, int age,
                   int polis, String diagnosis,
                   String doctorName) {

        if (name == null || name.isBlank()
                || surname == null || surname.isBlank()
                || age < 0 || age > 130
                || polis <= 0
                || diagnosis == null || diagnosis.isBlank()
                || doctorName == null || doctorName.isBlank()) {

            throw new IllegalArgumentException(
                    "Некорректные данные пациента");
        }

        this.name = name;
        this.surname = surname;
        this.age = age;
        this.polis = polis;
        this.diagnosis = diagnosis;
        this.doctorName = doctorName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getFullName() {
        return name + " " + surname;
    }

    public int getPolis() {
        return polis;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getDoctorName() {
        return doctorName;
    }
}