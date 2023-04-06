package com.makarenko.main.util;

import com.makarenko.main.exception.IntegerUserException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.makarenko.main.util.Constants.*;

public final class ProcessStepFromUser {

    private final static Scanner scanner = new Scanner(System.in);

    public static int returnStep(int start, int end) {
        while (true) {
            String stringNumber = scanner.nextLine();
            try {
                int number = Integer.parseInt(stringNumber);
                if (number < start || number > end) {
                    throw new IntegerUserException(FROM_USER_ERROR);
                } else {
                    return number;
                }
            } catch (IntegerUserException e) {
                System.err.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println(FROM_USER_ERROR);
            } finally {
                System.out.println(SPACING);
            }
        }
    }

    public static String checkStepUserWithRegex(Pattern pattern) {
        while (true) {
            String stepUser = scanner.nextLine();
            Matcher matcher = pattern.matcher(stepUser);
            if (matcher.find()) {
                return stepUser;
            } else {
                System.out.println(FROM_USER_ERROR2);
            }
        }
    }

    public static LocalDateTime checkDateTimeFromUser(String text, DateTimeFormatter formatter) {
        LocalDateTime localDateTime = LocalDateTime.parse(text, formatter);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeMonthLater = now.plusMonths(3L);
        if (localDateTime.isBefore(now)) {
            System.out.println(FROM_USER_DATE1);
            return null;
        } else if (localDateTime.isAfter(threeMonthLater)) {
            System.out.println(FROM_USER_DATE2);
            return null;
        } else {
            System.out.println(FROM_USER_DATE_SUCCESS);
            return localDateTime;
        }
    }

    public static int checkIdFromUser(int zero, List<Integer> listId) {
        while (true) {
            String stringNumber = scanner.nextLine();
            try {
                int number = Integer.parseInt(stringNumber);
                if (number == zero || listId.contains(number)) {
                    return number;
                } else {
                    throw new IntegerUserException(FROM_USER_ERROR);
                }
            } catch (IntegerUserException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(FROM_USER_ERROR);
            } finally {
                System.out.println(SPACING);
            }
        }
    }

    public static int checkChoiseFromUser(List<Integer> list) {
        while (true) {
            String stringNumber = scanner.nextLine();
            try {
                int number = Integer.parseInt(stringNumber);
                if (list.contains(number)) {
                    return number;
                } else {
                    throw new IntegerUserException(FROM_USER_ERROR);
                }
            } catch (IntegerUserException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(FROM_USER_ERROR);
            } finally {
                System.out.println(SPACING);
            }
        }
    }
    private ProcessStepFromUser() {
    }
}
