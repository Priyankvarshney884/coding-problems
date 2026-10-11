package TestScenerios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimeNumberServiceTest {

    private final PrimeNumberService service = new PrimeNumberService();

    @Test
    void shouldReturnTrueForPrimeNumbers() {
        assertTrue(service.isPrime(7));
        assertTrue(service.isPrime(2));
    }

    @Test
    void shouldReturnFalseForCompositeNumbers() {
        assertFalse(service.isPrime(9));
        assertFalse(service.isPrime(100));
    }

    @Test
    void shouldReturnFalseForNumbersLessThanTwo() {
        assertAll(
                () -> assertFalse(service.isPrime(-1)),
                () -> assertFalse(service.isPrime(0)),
                () -> assertFalse(service.isPrime(1))
        );
    }
}
