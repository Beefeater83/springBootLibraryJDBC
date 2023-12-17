package diakonov.library.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private volatile int id;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min=2, max = 30, message = "Имя должно содержать от 2 до 30 символов")
    private String personName;
    @Min(value = 1900, message = "Введите корректный год")
    private int yearOfBirth;
    public Person() {
    }

    public Person(String personName, int yearOfBirth) {

        this.personName = personName;
        this.yearOfBirth = yearOfBirth;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
