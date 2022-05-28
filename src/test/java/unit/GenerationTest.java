package unit;

import org.junit.jupiter.api.Test;
import uk.co.paidsoftware.snowflake.SnowflakeGenerator;

import java.util.concurrent.ExecutionException;

public class GenerationTest {

    @Test
    public void generate() throws ExecutionException, InterruptedException {
        SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator("10110");
        System.out.println(snowflakeGenerator.getSnowflake());
    }
}
