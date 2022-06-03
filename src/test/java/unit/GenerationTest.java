package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.co.paidsoftware.snowflake.Snowflake;

import java.util.concurrent.ExecutionException;

public class GenerationTest {

    @Test
    public void generate() throws ExecutionException, InterruptedException {
        Snowflake snowflakeGenerator = new Snowflake("10110");
        long id = snowflakeGenerator.getSnowflake();

        Assertions.assertEquals(63, Long.toBinaryString(id).length());

        System.out.println(id);
        System.out.println(Snowflake.getTimestamp(id));
        System.out.println(Snowflake.getProcessID(id));
        System.out.println(Snowflake.getThreadID(id));
        System.out.println(Snowflake.getSequenceID(id));
    }
}