import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main{

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        char option = ' ';

        System.out.println("**************************");
        System.out.println("ÜDV AZ AKASZTÓFA JÁTÉKBAN!");
        System.out.println("**************************");

        do {

            option = ' ';

            int szint = szintKerdezo();

            Random random = new Random();
            int randomwordindex = random.nextInt(1, 101);

            String path = "";

            path = switch (szint) {
                case 1 -> "szavak_konnyu.txt";
                case 2 -> "szavak_kozepes.txt";
                case 3 -> "szavak_nehez.txt";
                default -> "Nincs ilyen szint";
            };

            String word = wordMaker(path, randomwordindex);

            String myth = "";
            for (int i = 0; i < word.length(); i++) {
                myth += "_ ";
            }

            int attempts = 0;

            //udvozles, hany betus a szo, es ures vonalak

            System.out.println("****************************");
            System.out.println("A SZÓ AMIT KI KELL TALÁLNOD:");
            System.out.println(myth);
            System.out.println("****************************");


            boolean joTipp = false;
            boolean win = false;

            while (attempts < 6) {

                System.out.print("TIPP: ");
                char tipp = scanner.next().toLowerCase().charAt(0);

                if (!Character.isLetter(tipp)) {
                    System.out.println("CSAK BETŰ LEHET!");
                    continue;
                }


                for (int i = 0; i < word.length(); i++) {
                    if (tipp == word.charAt(i)) {
                        myth = myth.substring(0, i * 2) + word.charAt(i) + myth.substring(i * 2 + 1);
                        joTipp = true;
                    }
                }

                if (joTipp) {
                    System.out.println("BENNE VAN!");
                    System.out.println(myth);
                    joTipp = false;
                } else {
                    attempts++;
                    System.out.println("NINCS BENNE!");
                    System.out.print(rajzolo(attempts));
                    System.out.println(myth);
                }

                if (!myth.contains("_")) {
                    win = true;
                    break;
                }
            }

            //win vagy sem

            winOrNot(win, word);

            while (option != 'I' && option !='N'){
                System.out.println("SZERETNÉL MÉG EGYET JÁTSZANI?");
                System.out.println("I - IGEN  |  N - NEM");
                option = scanner.next().toUpperCase().charAt(0);
            }

        }while(option != 'N');

        scanner.close();

    }

    private static String rajzolo(int attempts){

        switch (attempts){
            case 1 -> {return """
                              o  
                              
                              
                              
                            """;}
            case 2 -> {return """
                               o
                               |
                               
                             """;}
            case 3 -> {return """
                               o
                              /|
                              
                             """;}
            case 4 -> {return """
                               o
                              /|\\
                              
                             """;}
            case 5 -> {return """
                               o
                              /|\\
                              /
                             """;}
            case 6 -> {return """
                               o
                              /|\\
                              / \\
                             """;}
            default -> {return "";}

        }




    }

    private static int szintKerdezo(){

        int szint= 0;

        System.out.println("*********************************");
        System.out.println("VÁLASZD KI A NEHÉZSÉGI SZINTET:");
        System.out.print("1 - KÖNNYŰ\n2 - KÖZEPES\n3 - NEHÉZ\n");
        System.out.println("*********************************");

        do {
            try {
                szint = scanner.nextInt();
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Szám kell tesó.");
            } catch (Exception e) {
                System.out.println("Valami nem jött össze.");
            }
            if (szint > 3 || szint < 1) {
                System.out.println("1 és 3 között válassz öreg!");
            }

        } while (szint < 1 || szint > 3);

        return szint;
    }

    private static String wordMaker(String path, int randomwordindex){

        String word = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            int current = 1;
            String line;
            while ((line = reader.readLine()) != null) {
                if (current == randomwordindex) {
                    word = line;
                    break;
                }
                current++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nem található a file.");
        } catch (IOException e) {
            System.out.println("Valami félrement.");
        }
        return word;
    }

    private static void winOrNot(boolean win, String word){

        if (win) {
            System.out.println("********");
            System.out.println("NYERTÉL!");
            System.out.println("********");
        } else {
            System.out.println("***********");
            System.out.println("VESZTETTÉL!");
            System.out.printf("A szó %s volt\n", word);
            System.out.println("***********");
        }
    }

}