package pl.raiffeisensolutions;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        AverageSalaryCalculator averageSalaryCalculator = new AverageSalaryCalculator(new Locale("es","ES"));

        BigDecimal averageSalary = averageSalaryCalculator.calculate("C:\\Users\\rb21690\\IdeaProjects\\average-salary\\src\\main\\resources\\report.csv");

        System.out.println("Average salary is " + averageSalary.setScale(2, BigDecimal.ROUND_HALF_UP));



        try {
            averageSalary = averageSalaryCalculator.calculate("C:\\Users\\rb21690\\IdeaProjects\\average-salary\\src\\main\\resources\\report_empty.csv");
        } catch (Exception e) {
            System.out.println("The file report_empty.csv has too few lines");
        }
    }
}
