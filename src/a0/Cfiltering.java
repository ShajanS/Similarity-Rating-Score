// **********************************************************
// Assignment0: group_0052
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
package a0;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Cfiltering {

  // this is a 2d matrix i.e. user*movie
  private int userMovieMatrix[][];
  // this is a 2d matrix i.e. user*movie
  private float userUserMatrix[][];

  /**
   * Default Constructor.
   */
  public Cfiltering() {
    // this is 2d matrix of size 1*1
    userMovieMatrix = new int[1][1];
    // this is 2d matrix of size 1*1
    userUserMatrix = new float[1][1];
  }

  /**
   * Constructs an object which contains two 2d matrices, one of size
   * users*movies which will store integer movie ratings and one of size
   * users*users which will store float similarity scores between pairs of
   * users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
    // create a user matrix with the size of user*user (square matrix)
    userUserMatrix = new float[numberOfUsers][numberOfUsers];
    // create a movie matrix with the size of user*movies
    userMovieMatrix = new int[numberOfUsers][numberOfMovies];
  }

  /**
   * The purpose of this method is to populate the UserMovieMatrix. As input
   * parameters it takes in a rowNumber, columnNumber and a rating value. The
   * rating value is then inserted in the UserMovieMatrix at the specified
   * rowNumber and the columnNumber.
   * 
   * @param rowNumber The row number of the userMovieMatrix.
   * @param columnNumber The column number of the userMovieMatrix.
   * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
   */
  public void populateUserMovieMatrix(int rowNumber, int columnNumber,
      int ratingValue) {
    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   * 
   * @param users Is the number of users
   * @param movie Is the number of movies
   */
  public void calculateSimilarityScore(int users, int movies) {
    // go through every user by row
    for (int i = 0; i < users; i++) {
      // loop through every column
      for (int j = 0; j < users; j++) {
        // Created a variable to hold the distance value between two users
        float distance = 0;
        // loop through every movie
        for (int k = 0; k < movies; k++) {
          // calculate the distance by difference of squares between two users
          // for each movie
          int rating = (userMovieMatrix[i][k] - userMovieMatrix[j][k]);
          distance += (rating * rating);
        }
        // call the helper function to calculate similarity score
        float similarityscore = this.similarityscore(distance);
        // set userUserMatrix values to the appropriate similarity scores
        // symmetric matrix [i][j] == [j][i]
        userUserMatrix[i][j] = similarityscore;
        userUserMatrix[j][i] = similarityscore;
      }
    }
  }

  /**
   * This is a helper function which calculates the similarity score after
   * inputed the distance
   * 
   * @param distance Takes a float of the distance between users
   * @return A float value of the similarity score
   */
  public float similarityscore(float distance) {
    // plug and play the input value and return the similarity score
    return (float) (1 / (1 + Math.sqrt(distance)));
  }

  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   * 
   * @param users Is the number of users
   */
  public void printUserUserMatrix(int users) {
    // formating of intended output result
    System.out.println();
    System.out.println();
    System.out.println("userUserMatrix is:");
    // Create an array to hold similarity scores between users
    String[][] similarityarray = new String[users][users];
    // set decimal format to match output requirements
    DecimalFormat outputformat = new DecimalFormat("0.0000");
    for (int i = 0; i < users; i++) {
      for (int j = 0; j < users; j++) {
        // format the array
        similarityarray[i][j] = outputformat.format(userUserMatrix[i][j]);
      }
      // print rows
      System.out.println(Arrays.toString(similarityarray[i]));
    }
  }

  /**
   * This helper function finds and returns the largest or smallest similarity
   * score between all users
   * 
   * @param condition A string telling the function max value or min value to
   *        return
   * @param base Is the first base value to compare if other value is larger or
   *        smaller
   * @param users Is the number of users
   * @return base The value which is the most similar or dissimilar
   */
  public float findmaxormin(String condition, float base, int users) {
    // iterate through rows and columns
    for (int i = 0; i < users; i++) {
      for (int j = 0; j < users; j++) {
        // i must not equal j
        if (i != j) {
          // If function must find most similar score
          if (condition == "max") {
            // is less than...empty condition
            if (userUserMatrix[i][j] < base) {
            }
            // is greater than, then set base to the higher value
            if (userUserMatrix[i][j] > base) {
              base = userUserMatrix[i][j];
            }
          }
          // If function must find most dissimilar score
          if (condition == "min") {
            // is greater than...empty condition
            if (userUserMatrix[i][j] > base) {
            }
            // is less than, then set base to the lower value
            if (userUserMatrix[i][j] < base) {
              base = userUserMatrix[i][j];
            }
          }
        }
      }
    }
    // return value
    return base;
  }

  /**
   * This function finds and prints the most similar pair of users in the
   * userUserMatrix.
   * 
   * @param users Is the number of users
   */
  public void findAndprintMostSimilarPairOfUsers(int users) {
    // formating of intended output result
    System.out.println();
    System.out.println();
    System.out.println(
        "The most similar pairs of users " + "from above userUserMatrix are:");
    // call helper function to find most similar pair
    float checker = this.findmaxormin("max", userUserMatrix[0][1], users);
    // iterate through rows and columns
    for (int i = 0; i < users; i++) {
      for (int j = i; j < users; j++) {
        // check which user has the most similar score
        if (userUserMatrix[i][j] == checker) {
          if (i != j) {
            // print the results formated to the output
            // +1 used to not print duplicates
            // (user 1 and user 2 == user 2 and user 1)
            System.out.println("User" + (i + 1) + " and User" + (j + 1));
          }
        }
      }
    }
    // format the similarity score and print
    DecimalFormat scoreform = new DecimalFormat("0.0000");
    float score = checker;
    System.out.println("with similarity score of " + scoreform.format(score));
  }

  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   * 
   * @param users Is the number of users
   */
  public void findAndprintMostDissimilarPairOfUsers(int users) {
    // formating of intended output result
    System.out.println();
    System.out.println();
    System.out.println("The most dissimilar pairs of users "
        + "from above userUserMatrix are:");
    // call helper function to find most dissimilar pair
    float checker = this.findmaxormin("min", userUserMatrix[0][1], users);
    // iterate through rows and columns
    for (int i = 0; i < users; i++) {
      for (int j = i; j < users; j++) {
        // check which user has the most dissimilar score
        if (userUserMatrix[i][j] == checker) {
          if (i != j) {
            // print the results formated to the output
            // +1 used to not print duplicates
            // (user 1 and user 2 == user 2 and user 1)
            System.out.println("User" + (i + 1) + " and User" + (j + 1));
          }
        }
      }
    }
    // format the similarity score and print
    DecimalFormat scoreform = new DecimalFormat("0.0000");
    float score = checker;
    System.out.println("with similarity score of " + scoreform.format(score));
  }
}
