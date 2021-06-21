import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static int generateRandomNumber() {
        Random rand = new Random();
        int upperbound = 55;
        int int_random = rand.nextInt(upperbound);
        return int_random;
    }
    public static void main(String[] args) {
        String line = "";
        String splitBy = ",";
        ArrayList<String> africanCountries = new ArrayList<String>();
        ArrayList<String> africanCapitals = new ArrayList<String>();
        ArrayList<Integer> multipleCapitals = new ArrayList<Integer>();
        ArrayList<Integer> randomNumberList = new ArrayList<Integer>();
        ArrayList<String> capitalCitiesAnswers = new ArrayList<String>();
        int random_number;
        int correct_answers = 0;

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("african_countries.csv"));

            while ((line = csvReader.readLine()) != null) {
                String[] countries = line.split(splitBy);
                africanCountries.add(countries[0].toLowerCase());
                africanCapitals.add(countries[1].toLowerCase());
            } 
        } catch (IOException e) {
                e.printStackTrace();
        }

        for (String i : africanCapitals) {
            if (i.contains("*")) {
                multipleCapitals.add(africanCapitals.indexOf(i));
            } 
        }

        for (Integer i : multipleCapitals) {
            System.out.println(i);
        }



        int i = 0;
        while (i < 10) {
            random_number = generateRandomNumber();

            while (randomNumberList.contains(random_number)) {
                random_number = generateRandomNumber();
            }
            randomNumberList.add(random_number);
            
            Scanner in = new Scanner(System.in);

            try {
                System.out.println("What is the capital of " + africanCountries.get(random_number) + ":");
                String enteredInput = in.nextLine();
                capitalCitiesAnswers.add(enteredInput.toLowerCase());
            } catch (Exception e) {
                e.printStackTrace();
            }

            i++;
        }

        for (String capitalCities : capitalCitiesAnswers) {
            for (int random_no : randomNumberList) {
                if (multipleCapitals.contains(random_no)) {
                    if (africanCapitals.get(random_no).contains("/b" + capitalCities + "/b")) {
                        correct_answers += 1;
                        break;
                    }
                } else {
                    if (capitalCities == africanCapitals.get(random_no)) {
                        correct_answers += 1;
                    }
                }
            }
        }

        System.out.println("Total score = " + correct_answers * 5 + "/" + 50);

    } 
    
}