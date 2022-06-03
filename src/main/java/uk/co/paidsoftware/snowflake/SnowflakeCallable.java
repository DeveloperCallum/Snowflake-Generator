package uk.co.paidsoftware.snowflake;

import java.util.concurrent.Callable;
 final class SnowflakeCallable implements Callable<Long> {
    private final String processID;
    public volatile long sequence = 0;

    public SnowflakeCallable(String processID) {
        this.processID = processID;
    }

    @Override
    public Long call() throws Exception {
        return generateSnowflake();
    }

    private long generateSnowflake() {
        StringBuilder uid = new StringBuilder("0");
        uid.append(getTime());
        uid.append(processID);
        uid.append(Thread.currentThread().getName().split("-")[1]);
        uid.append(getSequence());
        long id = Long.parseLong(uid.toString(), 2);
        stepSequence();

        return id;
    }

    private synchronized void stepSequence() {
        long sequenceBits = 12L;
        long mask = -1L ^ (-1L << sequenceBits);

        sequence = (sequence + 1) & mask;
    }

    public synchronized String getSequence() {
        String placeholder = "000000000000";
        String binary = Long.toBinaryString(sequence);

        return placeholder.substring(binary.length(), placeholder.length()) + binary;
    }

    public String getTime() {
        return Long.toBinaryString(System.currentTimeMillis());
    }
}
