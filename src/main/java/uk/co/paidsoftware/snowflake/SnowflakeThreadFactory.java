package uk.co.paidsoftware.snowflake;

import java.util.concurrent.ThreadFactory;

 final class SnowflakeThreadFactory implements ThreadFactory {
    public static volatile int threadID = 0;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread;

        synchronized (SnowflakeThreadFactory.class) {
            thread = new Thread(r, "Thread-" + getBinaryString());
        }

        return thread;
    }

    private String getBinaryString() {
        String placeholder = "00000";
        String binary;

        synchronized (SnowflakeThreadFactory.class) {
            binary = Long.toBinaryString(++threadID);
        }

        return placeholder.substring(binary.length(), placeholder.length()) + binary;
    }
}
