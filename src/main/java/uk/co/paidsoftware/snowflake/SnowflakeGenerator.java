package uk.co.paidsoftware.snowflake;

import java.util.concurrent.*;

public final class SnowflakeGenerator {
    private final String processID;
    private final ExecutorService executorService;

    public SnowflakeGenerator(String processID) {
        this(processID, 5);
    }

    public SnowflakeGenerator(String processID, int processThreads) {
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
        Future<Long> future = executorService.submit(new SnowflakeRunnable(processID));

        return future.get();
    }
}
