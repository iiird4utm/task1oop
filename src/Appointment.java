import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private final Patient patient;
    private final Doctor doctor;
    private final LocalDate date;
    private final LocalTime start;
    private final LocalTime end;

    public Appointment(Patient patient, Doctor doctor,
                       LocalDate date, LocalTime start,
                       LocalTime end) {

        if (patient == null || doctor == null || date == null
                || start == null || end == null) {
            throw new AppointmentException(
                    "Данные записи не должны быть пустыми");
        }

        if (!end.isAfter(start)) {
            throw new AppointmentException(
                    "Время окончания должно быть позже времени начала");
        }

        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public boolean overlaps(Appointment other) {
        if (other == null) {
            throw new AppointmentException(
                    "Запись для сравнения не должна быть null");
        }

        if (!date.equals(other.date)) {
            return false;
        }

        return start.isBefore(other.end)
                && other.start.isBefore(end);
    }

    public boolean overlaps(LocalTime otherStart,
                            LocalTime otherEnd) {

        if (otherStart == null || otherEnd == null
                || !otherStart.isBefore(otherEnd)) {
            return false;
        }

        return start.isBefore(otherEnd)
                && otherStart.isBefore(end);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "patient=" + patient.getFullName() +
                ", doctor=" + doctor.getFullName() +
                ", date=" + date +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
