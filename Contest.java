import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Contest {

  private final String[] representations = {"1111110", "0110000", "1101101", "1111001", "0110011", "1011011", "1011111", "1110000", "1111111", "1111011"};
  private ArrayList<Integer> possibls = new ArrayList<Integer>();
  private ArrayList<Integer> erro = new ArrayList<Integer>();


  private ArrayList<Integer> possibilityOf(String toConvert) {
    for (int i = 0; i < 10; i++) {
      int check = 0;
      for (int j = 0; j < 7; j++) {
        if (toConvert.charAt(j) == '1' && representations[i].charAt(j) == '0') {
          check = 0;
          break;
        } else {
          check = 1;
        }
      }
      if (check == 1) {
        possibls.add(i);
      }
    }
    return possibls;
  }

  public ArrayList<Integer> remains(String toConvert, ArrayList<Integer> s) {
    ArrayList<Integer> remainPossible = new ArrayList<Integer>();
    for (int i : s) {
      int check = 0;
      for (int j = 0; j < 7; j++) {
        if (toConvert.charAt(j) == '1' && representations[(i + 99) % 10].charAt(j) == '0') {
          check = 0;
          break;
        } else {
          check = 1;
        }
      }
      if (check == 1) {
        remainPossible.add((i + 99) % 10);
      }
    }
    return remainPossible;
  }

  public void err(String toCheck, int index) {
    for (int j = 0; j < 7; j++) {
      if (toCheck.charAt(j) == '0' && representations[index].charAt(j) == '1') {
        erro.add(j);
      }
    }
  }

  public static void main(String [] args) {
    Scanner input = new Scanner(System.in);
    int numberOfTestCases = input.nextInt();
    int printer = numberOfTestCases;

    while (numberOfTestCases > 0) {
      Contest manual = new Contest();
      ArrayList<Integer> decoy;
      ArrayList<Integer> main2;
      ArrayList<String> checks = new ArrayList<String>();

      int numberOfStates = input.nextInt();

      String deck = input.next();
      checks.add(deck);

      ArrayList<Integer> main = manual.possibilityOf("" + deck);
      decoy = main;

      int index = 0;

      while (numberOfStates - 1 > 0) {
        deck = input.next();
        checks.add(deck);
        main2 = manual.remains("" + deck, decoy);
        decoy = main2;
        numberOfStates--;
      }

      if (decoy.size() != 1) {
        System.out.println("Case" + " #" + (printer - numberOfTestCases + 1) + ": ERROR");
      } else {
        for (int o : decoy) {
          index = o;
        }
        for (int k = checks.size() - 1; k > 0; k--) {
          manual.err(checks.get(k), index);
          index = (index + 101) % 10;
        }
        for (int i : decoy) {
          StringBuilder agends = new StringBuilder(manual.representations[(i + 99) % 10]);
          for (int j : manual.erro) {
            agends.setCharAt(j, '0');
          }
          System.out.println("Case" + " #" + (printer - numberOfTestCases + 1) + ": " + agends);

        }
      }
      numberOfTestCases--;
    }
  }



}