package com.dailymenurating.util;

import com.dailymenurating.util.exception.BadRequestException;
import com.dailymenurating.util.exception.NotFoundException;

public class ValidationUtil {
    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static int checkId(String id) {
        if (id == null || id.equals("0") || id.equals("")) {
            throw new BadRequestException("ID is incorrect");
        }
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("ID is not a number", e);
        }
    }
}
