package pl.ryzykowski.radiohistory.service.impl.parser.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatesUtilOdsluchaneTest {

    DatesUtilOdsluchane datesUtilOdsluchane;

    @BeforeEach
    public void init() {
        datesUtilOdsluchane = new DatesUtilOdsluchane();
    }

    @Test
    public void shoudReverseDate() {
        assertEquals( "02-01-2022", datesUtilOdsluchane.odsluchaneDate(LocalDate.parse("2022-01-02")));
        assertEquals( "17-12-2015", datesUtilOdsluchane.odsluchaneDate(LocalDate.parse("2015-12-17")));
    }

    @Test
    public void shouldReturnOneDateBetweenTwoDates(){
        List<LocalDate> expected = Arrays.asList(LocalDate.parse("2022-02-01"));
        assertEquals(expected, datesUtilOdsluchane.getAllDatesBetweenTwoDates("2022-02-01", "2022-02-01"));
    }

    @Test
    public void shouldReturnTwoDatesBetweenTwoDates(){
        List<LocalDate> expected = Arrays.asList(LocalDate.parse("2022-02-01"), LocalDate.parse("2022-02-02"));
        assertEquals(expected, datesUtilOdsluchane.getAllDatesBetweenTwoDates("2022-02-01", "2022-02-02"));
    }

    @Test
    public void shouldReturnThreeDatesBetweenTwoDates(){
        List<LocalDate> expected = Arrays.asList(LocalDate.parse("2022-02-01"), LocalDate.parse("2022-02-02"), LocalDate.parse("2022-02-03"));
        assertEquals(expected, datesUtilOdsluchane.getAllDatesBetweenTwoDates("2022-02-01", "2022-02-03"));
    }

    @Test
    public void shouldReturnTrueValidateDatesCorrect() {
        assertTrue(datesUtilOdsluchane.validateDates("2022-02-01", "2022-02-03"));
    }

    @Test
    public void shouldReturnTrueValidateDatesDateFromAndDatesToAreEqual() {
        assertTrue(datesUtilOdsluchane.validateDates("2022-02-01", "2022-02-01"));
    }

    @Test
    public void shouldReturnFalseValidateDatesDateFromIsLaterThanDateTo() {
        assertFalse(datesUtilOdsluchane.validateDates("2022-02-03", "2022-02-01"));
    }

    @Test
    public void shouldReturnFalseAndExceptionValidateDatesBadFormat() {
    }
}
