package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.co.paidsoftware.snowflake.SnowflakeGenerator;

import java.util.concurrent.ExecutionException;

public class GenerationTest {

    @Test
    public void generate() throws ExecutionException, InterruptedException {
        SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator("10110");
        long id = snowflakeGenerator.getSnowflake();

        Assertions.assertEquals(63, Long.toBinaryString(id).length());

        System.out.println(id);
        System.out.println(SnowflakeGenerator.getTimestamp(id));
        System.out.println(SnowflakeGenerator.getProcessID(id));
        System.out.println(SnowflakeGenerator.getThreadID(id));
        System.out.println(SnowflakeGenerator.getSequenceID(id));
    }
}