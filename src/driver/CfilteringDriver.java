// **********************************************************
// Assignment0:
// UTORID: sivara57
// UT Student #: 1001732002
// Author: Shajan Sivarajah
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences. In this semester
// we will select any three of your assignments from total of 5 and run it
// for plagiarism check.
// *********************************************************
package driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import a0.Cfiltering;

public class CfilteringDriver {

  /**
   * Reads user movie ratings from a text file, calculates similarity scores and
   * prints a score matrix.
   */
  public static void main(String[] args) {
    try {

      // open file to read
      String fileName;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of input file? ");
      fileName = in.nextLine();
      FileInputStream fStream = new FileInputStream(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

      // Read dimensions: number of users and number of movies
      int numberOfUsers = Integer.parseInt(br.readLine());
      int numberOfMovies = Integer.parseInt(br.readLine());

      /*
       * create a new Cfiltering object that contains: a) 2d matrix
       * i.e.userMovieMatrix (#users*#movies) b) 2d matrix i.e. userUserMatrix
       * (#users*#users)
       */
      Cfiltering cfObject = new Cfiltering(numberOfUsers, numberOfMovies);
      // this is a blankline being read
      br.readLine();
      // read each line of movie ratings and populate the
      // userMovieMatrix
      String row;
      int rownum = 0;
      while ((row = br.readLine()) != null) {
        // allRatings is a list of all String numbers on one row
        String allRatings[] = row.split(" ");
        int colnum = 0;
        for (String singleRating : allRatings) {
          int rv = Integer.valueOf(singleRating);
          // make the String number into an integer
          // populate userMovieMatrix
          // TODO: COMPLETE THIS I.E. POPULATE THE USER_MOVIE MATRIX
          cfObject.populateUserMovieMatrix(rownum, colnum, rv);
          colnum++;
        }
        rownum++;
      }
      // close the file
      fStream.close();
      in.close();

      /*
       * COMPLETE THIS ( I.E. CALL THE APPROPRIATE FUNCTIONS THAT DOES THE
       * FOLLOWING)
       */
      // TODO:1.) CALCULATE THE SIMILARITY SCORE BETWEEN USERS.
      cfObject.calculateSimilarityScore(numberOfUsers, numberOfMovies);
      // TODO:2.) PRINT OUT THE userUserMatrix
      cfObject.printUserUserMatrix(numberOfUsers);
      // TODO:3.) PRINT OUT THE MOST SIMILAR PAIRS OF USER AND THE MOST
      // DISSIMILAR
      // PAIR OF USERS.
      cfObject.findAndprintMostSimilarPairOfUsers(numberOfUsers);
      cfObject.findAndprintMostDissimilarPairOfUsers(numberOfUsers);

    } catch (FileNotFoundException e) {
      System.err.println("Do you have the input file in the root folder "
          + "of your project?");
      System.err.print(e.getMessage());
    } catch (IOException e) {
      System.err.print(e.getMessage());
    }
  }

}
