package com.bm;

import java.sql.SQLException;

// 1. Unchecked / Runtime exception representing our high-level application error
class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause); // Exception chaining preserves the root cause
    }
}

public class ExceptionTranslationDemo {

    // Simulating a low-level database operation that throws a CHECKED exception (SQLException)
    public void queryDatabase() throws SQLException {
        throw new SQLException("Database connection timeout on port 3306");
    }

    // Service method that translates the checked exception into an unchecked runtime exception
    public void executeBusinessLogic() {
        try {
            // Calling code that throws a checked exception
            queryDatabase();
            
        } catch (SQLException checkedException) {
            // TRANSLATION HAPPENS HERE: 
            // We catch a checked exception and wrap/throw it as an unchecked RuntimeException.
            // Why? Because callers don't need forced try-catch/throws boilerplate for unrecoverable DB failures.
            throw new DataAccessException("Failed to execute data operation due to internal error.", checkedException);
        }
    }

    public static void main(String[] args) {
        ExceptionTranslationDemo demo = new ExceptionTranslationDemo();

        try {
            demo.executeBusinessLogic();
        } catch (DataAccessException e) {
            System.err.println("--- Caught Clean Runtime Exception ---");
            System.err.println("Message: " + e.getMessage());
            System.err.println("Original Root Cause: " + e.getCause().getMessage());
        }
    }
}