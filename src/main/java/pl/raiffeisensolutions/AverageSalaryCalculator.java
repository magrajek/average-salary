package pl.raiffeisensolutions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

class AverageSalaryCalculator {

    private NumberFormat polishParser;

    AverageSalaryCalculator(Locale locale) {
        // TODO create currency parser here and store it for the needs of salary parsing

        this.polishParser = DecimalFormat.getInstance(locale);
        ((DecimalFormat) this.polishParser).setParseBigDecimal(true);
    }

    BigDecimal calculate(String inputFile) {
        // TODO use the other methods to calculate average salary. There is no need to use variables here.
        List<String> readlines_tmp = new ArrayList<String>();
        List<BigDecimal> listBigDecimal_tmp = new ArrayList<BigDecimal>();

        readlines_tmp = readLines(inputFile);
     //   System.out.println("Reading input file:");
     //   System.out.println(readlines_tmp);

        readlines_tmp = removeHeader(readlines_tmp);
     //   System.out.println("Removing header line:");
     //   System.out.println(readlines_tmp);

        readlines_tmp = getSalaryColumn(readlines_tmp);
     //   System.out.println("Reading salary column:");
     //   System.out.println(readlines_tmp);

        listBigDecimal_tmp = parseSalaries(readlines_tmp);
     //   System.out.println("BigDecimals list:");
     //   System.out.println(listBigDecimal_tmp);


     //   System.out.println("Avarage salaries:");
     //   System.out.println(calculateAverage(listBigDecimal_tmp));

        return calculateAverage(listBigDecimal_tmp);
    }

    private BigDecimal calculateAverage(List<BigDecimal> salaries) {
        // TODO calculate average of the passed salaries
        BigDecimal avarageSalaries = BigDecimal.ZERO;
        for (int i=0; i< salaries.size();i++)
        {
            avarageSalaries = avarageSalaries.add(salaries.get(i));
        }

        avarageSalaries = avarageSalaries.divide(new BigDecimal(salaries.size()));

        return avarageSalaries;
    }

    private List<BigDecimal> parseSalaries(List<String> salaryStrings) {
        // TODO return BigDecimal representation of the salary

        List<BigDecimal> listBigDecimal = new ArrayList<BigDecimal>();

        for(int i=0; i<salaryStrings.size();i++) {
            String toParse = salaryStrings.get(i);
            try{
               // NumberFormat polishParser = DecimalFormat.getInstance(new Locale("es", "ES"));
              //  ((DecimalFormat) polishParser).setParseBigDecimal(true);
                Number parsed = polishParser.parse(toParse);
                //NumberFormat spanishParser = DecimalFormat.getInstance(new Locale("es", "ES"));
                //((DecimalFormat) spanishParser).setParseBigDecimal(true);
                //Number parsed = spanishParser.parse(toParse);
                BigDecimal parsedBigDecimal = (BigDecimal) parsed;
                listBigDecimal.add(parsedBigDecimal);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return listBigDecimal;
    }


    private List<String> getSalaryColumn(List<String> csvLinesWithoutHeader) {
        // TODO trim all the lines ton contain only salary column i.e. from every line in format "John Smith;999999" create "999999"
        List<String> listString = new ArrayList<>();
        String[] string_tmp = new String[2];

      for(int i=0; i<csvLinesWithoutHeader.size();i++)
      {

          string_tmp = csvLinesWithoutHeader.get(i).split(";");

          listString.add(string_tmp[1]);

      }
      return listString;
    }


    private List<String> removeHeader(List<String> lines) {
        // TODO remove the first line of the file containing the header
        lines.remove(0);

        return lines;

    }

    private List<String> readLines(String inputFile) {
        // TODO read all the lines of the file and store them as a list
        List<String> listString = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream(inputFile);
            Scanner scanner = new Scanner(fis);



        while(scanner.hasNextLine()){
            listString.add(scanner.nextLine());
        }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(listString.size() < 2)
            throw new EmptyStackException();

    return listString;

    }
}

