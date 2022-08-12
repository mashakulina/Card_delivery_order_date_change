package ru.netology;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.LocaleUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    public static String getName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getPhoneNumber() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }

    public static String getNewDate() {
        LocalDate today = LocalDate.now();
        LocalDate dateToBeSet = today.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dateToBeSet.format(formatter);
    }

    public static String getCity() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.address().city();
    }
}
