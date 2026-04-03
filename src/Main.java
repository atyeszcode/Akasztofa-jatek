import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main{

    public static void main(String[] args){

        char option = ' ';

        Scanner scanner = new Scanner(System.in);

        System.out.println("**************************");
        System.out.println("ÜDV AZ AKASZTÓFA JÁTÉKBAN!");
        System.out.println("**************************");

        do {
            System.out.println("*********************************");
            System.out.println("VÁLASZD KI A NEHÉZSÉGI SZINTET:");
            System.out.print("1 - KÖNNYŰ\n2 - KÖZEPES\n3 - NEHÉZ\n");
            System.out.println("*********************************");


            Random random = new Random();
            int randomwordindex = random.nextInt(1, 101);

            int szint = 0;
            String path = "";


            do {
                try {
                    szint = scanner.nextInt();
                } catch (NumberFormatException e) {
                    System.out.println("Szám kell tesó.");
                } catch (Exception e) {
                    System.out.println("Valami nem jött össze.");
                }
                if (szint > 3 || szint < 1) {
                    System.out.println("1 és 3 között válassz öreg!");
                }

            } while (szint < 1 || szint > 3);

            path = switch (szint) {
                case 1 -> "szavak_konnyu.txt";
                case 2 -> "szavak_kozepes.txt";
                case 3 -> "szavak_nehez.txt";
                default -> "Nincs ilyen szint";
            };


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


            int attempts = 0;
            String myth = "";
            for (int i = 0; i < word.length(); i++) {
                myth += "_ ";
            }

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


            while (option != 'I' && option !='N'){
                System.out.println("SZERETNÉL MÉG EGYET JÁTSZANI?");
                System.out.println("I - IGEN  |  N - NEM");
                option = scanner.next().toUpperCase().charAt(0);
            }

        }while(option != 'N');

        scanner.close();

    }

    public static String rajzolo(int attempts){

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

}