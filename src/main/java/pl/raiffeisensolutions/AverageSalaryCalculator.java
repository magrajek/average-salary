package pl.raiffeisensolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

class AverageSalaryCalculator {

    private NumberFormat spanishParser;

    AverageSalaryCalculator(Locale locale) {
        // TODO create currency parser here and store it for the needs of salary parsing

        this.spanishParser = DecimalFormat.getInstance(locale);
        ((DecimalFormat) this.spanishParser).setParseBigDecimal(true);
    }

    BigDecimal calculate(String inputFile) {
        // TODO use the other methods to calculate average salary. There is no need to use variables here.
        List<String> readlines_tmp = new ArrayList<>();
        List<BigDecimal> listBigDecimal_tmp = new ArrayList<>();

        return calculateAverage(
                                parseSalaries(
                                                getSalaryColumn(
                                                                    removeHeader(
                                                                                    readLines(inputFile)))));
    }

    private BigDecimal calculateAverage(List<BigDecimal> salaries) {
        // TODO calculate average of the passed salaries
        BigDecimal avarageSalaries;
        BigDecimal sumSalaries = summingSalaries(salaries);

        avarageSalaries = sumSalaries.divide(new BigDecimal(salaries.size())).setScale(2, RoundingMode.HALF_UP);

        return avarageSalaries;
    }

    private BigDecimal summingSalaries(List<BigDecimal> salaries) {
        BigDecimal sumSalaries = BigDecimal.ZERO;
        for (BigDecimal item : salaries) {
            sumSalaries = sumSalaries.add(item);
        }
        return sumSalaries;

    }

    private List<BigDecimal> parseSalaries(List<String> salaryStrings) {
        // TODO return BigDecimal representation of the salary

        List<BigDecimal> listBigDecimal = new ArrayList<>();

        for (String item : salaryStrings){
            try{
                parsingOneRaw(listBigDecimal, item);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            }
        return listBigDecimal;
    }

    private void parsingOneRaw(List<BigDecimal> listBigDecimal, String toParse) throws ParseException {
        Number parsed = spanishParser.parse(toParse);
        BigDecimal parsedBigDecimal = (BigDecimal) parsed;
        listBigDecimal.add(parsedBigDecimal);
    }


    private List<String> getSalaryColumn(List<String> csvLinesWithoutHeader) {
        // TODO trim all the lines ton contain only salary column i.e. from every line in format "John Smith;999999" create "999999"
        List<String> listString = new ArrayList<>();
        String[] string_tmp = new String[2];
        for (String item : csvLinesWithoutHeader) {

            getSalaryValue(listString, item);

      }
      return listString;
    }

    private void getSalaryValue(List<String> listString, String item) {
        String[] string_tmp;
        string_tmp = item.split(";");

        listString.add(string_tmp[1]);
    }


    private List<String> removeHeader(List<String> lines) {
        // TODO remove the first line of the file containing the header
        if(lines.size() < 2) {
            System.out.println("File does not contain any data.");
            System.exit(2);
        }
        return new ArrayList(lines.subList(1,lines.size()));

    }

    private List<String> readLines(String inputFile) {
        // TODO read all the lines of the file and store them as a list
        List<String> listString = new ArrayList<>();
        try {
            File fis = new File(inputFile);
            Scanner scanner = new Scanner(fis);



        while(scanner.hasNextLine()){
            listString.add(scanner.nextLine());
        }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    return listString;

    }
}

