package TestScenerios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimeNumberServiceTest {

    private final PrimeNumberService primeNumberService = new PrimeNumberService();

    @Test
    void shouldTrueForPrimeNumber(){
        assertTrue(primeNumberService.isPrime(7));
    }

}
