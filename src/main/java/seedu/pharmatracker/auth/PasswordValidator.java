package seedu.pharmatracker.auth;

import seedu.pharmatracker.exceptions.PharmaTrackerException;

/**
 * Validates password complexity requirements.
 */
public class PasswordValidator {
    private static final int MIN_LENGTH = 8;

    /**
     * Validates password strength rules.
     *
     * @param password Raw password.
     * @throws PharmaTrackerException If password is invalid.
     */
    public void validate(String password) throws PharmaTrackerException {
        if (password == null || password.length() < MIN_LENGTH) {
            throw new PharmaTrackerException("Password must be at least 8 characters long.");
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isWhitespace(c)) {
                hasSpecial = true;
            }
        }

        if (!hasUpper || !hasLower || !hasDigit || !hasSpecial) {
            throw new PharmaTrackerException(
                "Password must include uppercase, lowercase, number, and special character.");
        }
    }
}
