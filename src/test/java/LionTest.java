import com.example.Feline;
import com.example.Lion;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LionTest {

    @Mock
    Feline feline;

    @Test
    public void getKittensTest() throws Exception {
        Lion lion = new Lion("Самка", feline);
        Mockito.when(feline.getKittens()).thenReturn(3);
        int actualKittensCount = lion.getKittens();
        int expectedKittensCount = 3;
        assertEquals(expectedKittensCount, actualKittensCount, "Не правильное число львят");
    }

    @Test
    public void getFoodTest() throws Exception {
        Lion lion = new Lion("Самец", feline);
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> actual = lion.getFood();
        assertEquals(expected, actual, "Лев питается не правильно");
    }


    @Test
    public void doesHaveManeTest() throws Exception {
        Lion lion = new Lion("Самец", feline);
        boolean expectedHaveMane = true;
        boolean actualHaveMane = lion.doesHaveMane();
        assertEquals(expectedHaveMane, actualHaveMane, "У самца пропала грива");
    }

    @Test
    public void doesHaveManeThrowsExceptionTest() {
        Exception exception = assertThrows(
                Exception.class,
                () -> new Lion("Котик", feline)
        );
        String textException = "Используйте допустимые значения пола животного - самец или самка";
        assertEquals(textException, exception.getMessage());
    }
}

