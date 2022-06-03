package uk.co.paidsoftware.snowflake;

import java.util.concurrent.*;

public final class Snowflake {
    private final String processID;
    private final ExecutorService executorService;

    public Snowflake(String processID) {
        this(processID, 5);
    }
    public Snowflake(String processID, int processThreads) {
        this.processID = processID;

        if (processID.length() != 5){
            throw new IllegalArgumentException("processID must be 5 bits exclusively");
        }

        for (char letter : processID.toCharArray()){
            if (letter != '0' && letter != '1'){
                throw new IllegalArgumentException("processID must be binary");
            }
        }

        executorService = Executors.newFixedThreadPool(processThreads, new SnowflakeThreadFactory());
    }
    public long getSnowflake() throws ExecutionException, InterruptedException {
        Future<Long> future = executorService.submit(new SnowflakeCallable(processID));

        return future.get();
    }

    public static long getTimestamp(long snowflake){
        return Long.parseLong(Long.toBinaryString(snowflake).substring(0, 41), 2);
    }

    public static long getProcessID(long snowflake){
        return Long.parseLong(Long.toBinaryString(snowflake).substring(41, 46), 2);
    }

    public static long getThreadID(long snowflake){
        return Long.parseLong(Long.toBinaryString(snowflake).substring(46, 51), 2);
    }

    public static long getSequenceID(long snowflake){
        return Long.parseLong(Long.toBinaryString(snowflake).substring(51, 63), 2);
    }
}
