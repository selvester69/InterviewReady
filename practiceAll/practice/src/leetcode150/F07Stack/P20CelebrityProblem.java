package leetcode150.F07Stack;

// https://www.geeksforgeeks.org/dsa/the-celebrity-problem/#expected-approach-using-two-pointers-on-time-and-o1-space
/**
 * Given a square matrix mat[][] of size n x n, where mat[i][j] == 1 means
 * person i knows person j, and mat[i][j] == 0 means person i does not know
 * person j, find the celebrity person where,
 * 
 * A celebrity is defined as someone who:
 * 
 * Is known by everyone else
 * Does not know anyone (except themselves)
 * Return the index of the celebrity if one exists, otherwise return -1.
 * 
 * Note: It is guaranteed that mat[i][i] == 1 for all i
 * 
 * Examples:
 * 
 * Input: mat[][] = [[1, 1, 0],
 * [0, 1, 0],
 * [0, 1, 1]]
 * Output: 1
 * Explanation: 0th and 2nd person both know 1. Therefore, 1 is the celebrity.
 * 
 * Input: mat[][] = [[1, 1],
 * [1, 1]]
 * Output: -1
 * Explanation: The two people at the party both know each other. None of them
 * is a celebrity.
 * 
 * Input: mat[][] = [[1]]
 * Output: 0
 */
public class P20CelebrityProblem {

}
