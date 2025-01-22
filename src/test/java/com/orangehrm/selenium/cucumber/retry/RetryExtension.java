package com.orangehrm.selenium.cucumber.retry;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class RetryExtension implements TestWatcher, TestExecutionExceptionHandler {

    private int retryCount = 0;
    private static final int maxRetryCount = 2; // Number of retries

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test " + context.getDisplayName() + " (" + retryCount + "/" + maxRetryCount + ")");
            throw throwable; // Re-throw the exception to trigger retry
        } else {
            throw throwable; // No more retries, fail the test
        }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("Test " + context.getDisplayName() + " failed on retry " + retryCount);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("Test " + context.getDisplayName() + " passed on retry " + retryCount);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("Test " + context.getDisplayName() + " was aborted on retry " + retryCount);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println("Test " + context.getDisplayName() + " was disabled");
    }
}