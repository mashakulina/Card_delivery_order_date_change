package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));

    public static String getName() {
        return faker.name().fullName();
    }

    public static String getPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public static String getNewDate() {
        LocalDate today = LocalDate.now();
        LocalDate dateToBeSet = today.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dateToBeSet.format(formatter);
    }

    public static String getCity() {
        return faker.address().city();
    }
}
