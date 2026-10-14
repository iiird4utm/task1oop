import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor implements Person {

    private final String name;
    private final String surname;
    private final int age;
    private final String specialization;
    private final int numberOfOffice;

    private final LocalTime workStart;
    private final LocalTime workEnd;
    private final Duration appointmentDuration;

    private final List<Appointment> appointments =
            new ArrayList<>();

    public Doctor(String name, String surname, int age,
                  String specialization, int numberOfOffice,
                  LocalTime workStart, LocalTime workEnd,
                  Duration appointmentDuration) {

        if (name == null || name.isBlank()
                || surname == null || surname.isBlank()
                || age < 18 || age > 130
                || specialization == null
                || specialization.isBlank()
                || numberOfOffice <= 0
                || workStart == null
                || workEnd == null
                || !workStart.isBefore(workEnd)
                || appointmentDuration == null
                || appointmentDuration.isZero()
                || appointmentDuration.isNegative()) {

            throw new IllegalArgumentException(
                    "Некорректные данные врача");
        }

        this.name = name;
        this.surname = surname;
        this.age = age;
        this.specialization = specialization;
        this.numberOfOffice = numberOfOffice;
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.appointmentDuration = appointmentDuration;
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

    public String getSpecialization() {
        return specialization;
    }

    public int getNumberOfOffice() {
        return numberOfOffice;
    }

    public LocalTime getWorkStart() {
        return workStart;
    }

    public LocalTime getWorkEnd() {
        return workEnd;
    }

    public Duration getAppointmentDuration() {
        return appointmentDuration;
    }

    public List<Appointment> getAppointments() {
        return List.copyOf(appointments);
    }

    public boolean isWorkingTime(LocalTime start,
                                 LocalTime end) {

        return start != null
                && end != null
                && start.isBefore(end)
                && !start.isBefore(workStart)
                && !end.isAfter(workEnd);
    }

    public boolean checkOfFreedom(LocalDate date,
                                  LocalTime start,
                                  LocalTime end) {

        if (date == null || !isWorkingTime(start, end)) {
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

    public boolean recordTime(Patient patient,
                              LocalDate date,
                              LocalTime start) {

        if (patient == null || date == null || start == null) {
            return false;
        }

        LocalTime end;

        try {
            end = start.plus(appointmentDuration);
        } catch (RuntimeException exception) {
            return false;
        }

        if (!isWorkingTime(start, end)) {
            return false;
        }

        if (!checkOfFreedom(date, start, end)) {
            return false;
        }

        if (!patient.isAvailable(date, start, end)) {
            return false;
        }

        Appointment appointment = new Appointment(
                patient,
                this,
                date,
                start,
                end
        );

        if (patient.hasMedicationConflict(appointment)) {
            return false;
        }

        appointments.add(appointment);
        patient.addAppointment(appointment);

        return true;
    }
}