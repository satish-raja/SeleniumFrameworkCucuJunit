package com.orangehrm.selenium.cucumber.utils;

import java.time.LocalDateTime;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * TestLogger class provides logging functionalities for test results and context.
 * It allows setting the log level and logs messages based on the current log level.
 */
public class TestLogger {

    private LogUtil.LogLevel currentLogLevel = LogUtil.LogLevel.INFO; // Default log level

    /**
     * Sets the log level for the logger.
     *
     * @param logLevel The log level to set.
     */
    public void setLogLevel(String logLevel) {
        try {
            this.currentLogLevel = LogUtil.LogLevel.valueOf(logLevel.toUpperCase());
        } catch (IllegalArgumentException e) {
            logWarning("Invalid log level provided: " + logLevel + ". Falling back to default: INFO.");
        }
    }

    /**
     * Logs the start of a test.
     *
     * @param testName The name of the test.
     */
    public void logTestStart(String testName) {
        log("Test Started: " + testName + " at " + LocalDateTime.now(), LogUtil.LogLevel.INFO);
    }

    /**
     * Logs the success of a test.
     *
     * @param testName The name of the test.
     * @param duration The duration of the test in milliseconds.
     */
    public void logTestSuccess(String testName, long duration) {
        log("Test Passed: " + testName + " Duration: " + duration + " ms", LogUtil.LogLevel.INFO);
    }

    /**
     * Logs the failure of a test.
     *
     * @param testName The name of the test.
     * @param duration The duration of the test in milliseconds.
     */
    public void logTestFailure(String testName, long duration) {
        log("Test Failed: " + testName + " Duration: " + duration + " ms", LogUtil.LogLevel.ERROR);
    }

    /**
     * Logs the skipping of a test.
     *
     * @param testName The name of the test.
     */
    public void logTestSkipped(String testName) {
        log("Test Skipped: " + testName, LogUtil.LogLevel.WARN);
    }

    /**
     * Logs the start of a test context.
     *
     * @param context The ExtensionContext for the test context.
     */
    public void logTestContextStart(ExtensionContext context) {
        log("Test Context Started: " + context.getDisplayName(), LogUtil.LogLevel.INFO);
    }

    /**
     * Logs the finish of a test context.
     *
     * @param context The ExtensionContext for the test context.
     */
    public void logTestContextFinish(ExtensionContext context) {
        log("Test Context Finished: " + context.getDisplayName(), LogUtil.LogLevel.INFO);
    }

    /**
     * Logs an error message.
     *
     * @param message The error message to log.
     */
    public void logError(String message) {
        log(message, LogUtil.LogLevel.ERROR);
    }

    /**
     * Logs an informational message.
     *
     * @param message The informational message to log.
     */
    public void logInfo(String message) {
        log(message, LogUtil.LogLevel.INFO);
    }

    /**
     * Logs a warning message.
     *
     * @param message The warning message to log.
     */
    public void logWarning(String message) {
        log(message, LogUtil.LogLevel.WARN);
    }

    /**
     * Logs a warning message with parameters.
     *
     * @param message The warning message to log.
     * @param params The parameters to format the message.
     */
    public void logWarning(String message, Object... params) {
        log(String.format(message, params), LogUtil.LogLevel.WARN);
    }

    /**
     * Logs a message if the current log level is appropriate.
     *
     * @param message The message to log.
     * @param level The level of the message.
     */
    private void log(String message, LogUtil.LogLevel level) {
        if (level.ordinal() >= currentLogLevel.ordinal()) {
            LogUtil.log(message, level);
        }
    }
}