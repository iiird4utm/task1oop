
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

            throw new AppointmentException(
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

    public void addMedication(Medication medication) {

        if (medication == null) {
            throw new AppointmentException(
                    "Лекарство не задано");
        }

        for (Appointment appointment : appointments) {
            if (medication.conflictsWith(appointment)) {
                throw new AppointmentException(
                        "Приём лекарства пересекается "
                                + "с записью к врачу");
            }
        }

        medications.add(medication);
    }

    public List<Medication> getMedications() {
        return List.copyOf(medications);
    }

    public void addAppointment(Appointment appointment) {

        if (appointment == null) {
            throw new AppointmentException(
                    "Запись на приём не задана");
        }

        appointments.add(appointment);
    }

    public boolean isAvailable(LocalDate date,
                               LocalTime start,
                               LocalTime end) {

        if (date == null || start == null || end == null
                || !start.isBefore(end)) {
            return false;
        }

        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date)
                    && start.isBefore(appointment.getEnd())
                    && appointment.getStart().isBefore(end)) {
                return false;
            }
        }

        return true;
    }

    public boolean hasMedicationConflict(
            Appointment appointment) {

        for (Medication medication : medications) {
            if (medication.conflictsWith(appointment)) {
                return true;
            }
        }

        return false;
    }

    public List<Appointment> historyOfAppointments() {
        return List.copyOf(appointments);
    }
}
