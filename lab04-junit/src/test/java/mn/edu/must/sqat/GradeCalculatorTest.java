package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {

    @Test
    @DisplayName("95 оноо A дүн байх ёстой")
    void score95ShouldBeA() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        double score = 95.0;

        // Act
        String grade = calc.letterGrade(score);

        // Assert
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("85 оноо B дүн байх ёстой")
    void score85ShouldBeB() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        double score = 85.0;

        // Act
        String grade = calc.letterGrade(score);

        // Assert
        assertEquals("B", grade);
    }

    @Test
    @DisplayName("75 оноо C дүн байх ёстой")
    void score75ShouldBeC() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        double score = 75.0;

        // Act
        String grade = calc.letterGrade(score);

        // Assert
        assertEquals("C", grade);
    }

    @Test
    @DisplayName("65 оноо D дүн байх ёстой")
    void score65ShouldBeD() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        double score = 65.0;

        // Act
        String grade = calc.letterGrade(score);

        // Assert
        assertEquals("D", grade);
    }

    @Test
    @DisplayName("30 оноо F дүн байх ёстой")
    void score30ShouldBeF() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        double score = 30.0;

        // Act
        String grade = calc.letterGrade(score);

        // Assert
        assertEquals("F", grade);
    }

    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)")
    void ninetyIsExactlyA() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(90.0);

        // Assert
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой (хязгаарын тохиолдол)")
    void score89Point99ShouldBeB() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(89.99);

        // Assert
        assertEquals("B", grade);
    }

    @Test
    @DisplayName("60 оноо яг D дүн байх ёстой (хязгаарын тохиолдол)")
    void score60ShouldBeD() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(60.0);

        // Assert
        assertEquals("D", grade);
    }

    @Test
    @DisplayName("59.99 оноо F дүн байх ёстой (хязгаарын тохиолдол)")
    void score59Point99ShouldBeF() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(59.99);

        // Assert
        assertEquals("F", grade);
    }

    @Test
    @DisplayName("0 оноо F дүн байх ёстой (доод хязгаар)")
    void zeroShouldBeF() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(0.0);

        // Assert
        assertEquals("F", grade);
    }

    @Test
    @DisplayName("100 оноо A дүн байх ёстой (дээд хязгаар)")
    void hundredShouldBeA() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(100.0);

        // Assert
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("-1 оноо оруулахад IllegalArgumentException шидэх ёстой")
    void negativeScoreShouldThrowException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(-1.0)
        );
    }

    @Test
    @DisplayName("101 оноо оруулахад IllegalArgumentException шидэх ёстой")
    void scoreOver100ShouldThrowException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(101.0)
        );
    }

    @Test
    @DisplayName("Бүх оноо дээд хязгаарт байхад нийлбэр 100 байх ёстой")
    void totalScoreShouldBe100() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        double total = calc.totalScore(10, 40, 10, 10, 30);

        // Assert
        assertEquals(100.0, total);
    }

    @Test
    @DisplayName("Ирц сөрөг утгатай бол IllegalArgumentException шидэх ёстой")
    void negativeAttendanceShouldThrowException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(-5, 40, 10, 10, 30)
        );
    }

    @Test
    @DisplayName("Лабын оноо 40-өөс их бол IllegalArgumentException шидэх ёстой")
    void labOverMaximumShouldThrowException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(10, 41, 10, 10, 30)
        );
    }
}