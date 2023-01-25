package pl.ryzykowski.radiohistory.service.impl.parser.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DatesUtilOdsluchane {

    public String odsluchaneDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy" , Locale.UK ));
    }

    public boolean validateDates(String dateFrom, String dateTo) {
        LocalDate dateFromLocalDate;
        LocalDate dateToLocalDate;
        try {
            dateFromLocalDate = tryToParseDate(dateFrom);
            dateToLocalDate = tryToParseDate(dateTo);
            if (dateFromLocalDate.isAfter(dateToLocalDate)) {
                return false;
            }
        }
        catch (DateTimeParseException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public LocalDate tryToParseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date);
    }


    public List<LocalDate> getAllDatesBetweenTwoDates(String startDate, String endDate) {
        final LocalDate startLocalDate = LocalDate.parse(startDate).isBefore(LocalDate.now()) ? LocalDate.parse(startDate) : LocalDate.now();
        final LocalDate endLocalDate = LocalDate.parse(endDate).isBefore(LocalDate.now()) ? LocalDate.parse(endDate) : LocalDate.now();
        long numOfDaysBetween = ChronoUnit.DAYS.between(startLocalDate, endLocalDate)+1;
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startLocalDate.plusDays(i))
                .collect(Collectors.toList());
    }

}
