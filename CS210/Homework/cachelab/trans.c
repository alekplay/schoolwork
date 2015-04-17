/* CS210 Programming Assignment 3
 * Part B
 * Aleksander Skjoelsvik (U54904431) in collaboration with Raphael Baysa (U99595463)
 */

/*
 * trans.c - Matrix transpose B = A^T
 *
 * Each transpose function must have a prototype of the form:
 * void trans(int M, int N, int A[N][M], int B[M][N]);
 *
 * A transpose function is evaluated by counting the number of misses
 * on a 1KB direct mapped cache with a block size of 32 bytes.
 */

#include <stdio.h>
#include "cachelab.h"

int is_transpose(int M, int N, int A[N][M], int B[M][N]);

/* 
 * transpose_submit - This is the solution transpose function that you
 *     will be graded on for Part B of the assignment. Do not change
 *     the description string "Transpose submission", as the driver
 *     searches for that string to identify the transpose function to
 *     be graded. 
 */
char transpose_submit_desc[] = "Transpose submission";
void transpose_submit(int M, int N, int A[N][M], int B[M][N]) {
    int i,j,k,l;                // Counter variables
	int rowBlock, colBlock;     // Blocking Variables
	int tempVal,diaVal;         // Value that will help in switching elements in the array
    
    switch (M) {
        case 32: {                                                              // 32x32 matrix
            for(colBlock = 0; colBlock < N; colBlock += 8) {			       	// Blocking with size 8 (Maximize Cache Size)
                for(rowBlock = 0; rowBlock < N; rowBlock += 8) {
                    for(i = rowBlock; i < (rowBlock + 8); i++) {                // Traversing the sub-matrix using i and j
                        for(j = colBlock; j < (colBlock + 8); j++) {
                            if(i == j) {                                        // If its a diagonal value that doesn't need to be changed.
                                tempVal = A[i][j];                              // Store the value of the diagonal array
                                diaVal  = i;                                    // Stores the location where to place the value in the B array
                            } else {                                            // If they are not a diagonal location from 0,0
                                B[j][i] = A[i][j];                              // Transpose if ^
                            }
                        }                                                       // Dealing with diagonals (do not move them)
                        if(rowBlock == colBlock){                               // Using diag values and temp values now to add in the diag values
                            B[diaVal][diaVal] = tempVal;
                        }
                    }
                }
            }
            break;
        }
        case 64: {                                                              // 64x64 matrix
            int v0, v1, v2, v3, v4, v5, v6, v7;                                 // Extra variables to hold values
            for (i = 0; i < 8; ++i) {                                           // Traversing column until it reaches block 8
                for (j = 0; j < 8; ++j) {                                       // Traversing row until it reaches block 8
                    if (i == j) {                                               // If it is a diagonal value v
                        for (k = 0; k < 2; ++k) {                               // Only checking up until every 2nd row element
                            for (l = 0; l < 2; ++l) {                           // Same for the columns
                                v0 = A[i * 8 + k * 4 + 0][j * 8 + l * 4 + 2];   // Holding values for transposing using v0 - v7 which holds the max in cache
                                v1 = A[i * 8 + k * 4 + 0][j * 8 + l * 4 + 3];   // ^
                                v2 = A[i * 8 + k * 4 + 1][j * 8 + l * 4 + 2];   // ^
                                v3 = A[i * 8 + k * 4 + 1][j * 8 + l * 4 + 3];   // ^
                                v4 = A[i * 8 + k * 4 + 2][j * 8 + l * 4 + 2];   // ^
                                v5 = A[i * 8 + k * 4 + 2][j * 8 + l * 4 + 3];   // ^
                                v6 = A[i * 8 + k * 4 + 3][j * 8 + l * 4 + 2];   // ^
                                v7 = A[i * 8 + k * 4 + 3][j * 8 + l * 4 + 3];   // ^
                                B[j * 8 + l * 4 + 2][i * 8 + k * 4 + 0] = v0;   // Assign B array values to respective variables
                                B[j * 8 + l * 4 + 2][i * 8 + k * 4 + 1] = v2;   // ^
                                B[j * 8 + l * 4 + 2][i * 8 + k * 4 + 2] = v4;   // ^
                                B[j * 8 + l * 4 + 2][i * 8 + k * 4 + 3] = v6;   // ^
                                B[j * 8 + l * 4 + 3][i * 8 + k * 4 + 0] = v1;   // ^
                                B[j * 8 + l * 4 + 3][i * 8 + k * 4 + 1] = v3;   // ^
                                B[j * 8 + l * 4 + 3][i * 8 + k * 4 + 2] = v5;   // ^
                                B[j * 8 + l * 4 + 3][i * 8 + k * 4 + 3] = v7;   // ^
                                v0 = A[i * 8 + k * 4 + 0][j * 8 + l * 4 + 0];   // Redoing the next portion of the array into the variables
                                v1 = A[i * 8 + k * 4 + 0][j * 8 + l * 4 + 1];   // ^
                                v2 = A[i * 8 + k * 4 + 1][j * 8 + l * 4 + 0];   // ^
                                v3 = A[i * 8 + k * 4 + 1][j * 8 + l * 4 + 1];   // ^
                                v4 = A[i * 8 + k * 4 + 2][j * 8 + l * 4 + 0];   // ^
                                v5 = A[i * 8 + k * 4 + 2][j * 8 + l * 4 + 1];   // ^
                                v6 = A[i * 8 + k * 4 + 3][j * 8 + l * 4 + 0];   // ^
                                v7 = A[i * 8 + k * 4 + 3][j * 8 + l * 4 + 1];   // ^
                                B[j * 8 + l * 4 + 0][i * 8 + k * 4 + 0] = v0;   // Assigning B array values to respective variables
                                B[j * 8 + l * 4 + 0][i * 8 + k * 4 + 1] = v2;   // ^
                                B[j * 8 + l * 4 + 0][i * 8 + k * 4 + 2] = v4;   // ^
                                B[j * 8 + l * 4 + 0][i * 8 + k * 4 + 3] = v6;   // ^
                                B[j * 8 + l * 4 + 1][i * 8 + k * 4 + 0] = v1;   // ^
                                B[j * 8 + l * 4 + 1][i * 8 + k * 4 + 1] = v3;   // ^
                                B[j * 8 + l * 4 + 1][i * 8 + k * 4 + 2] = v5;   // ^
                                B[j * 8 + l * 4 + 1][i * 8 + k * 4 + 3] = v7;   // ^
                            }
                        }
                    } else {                                                    // If not diagonal location or i != k
                        
                        for (k = 0; k < 4; ++k) {
                            for (l = 0; l < 4; ++l) {
                                B[j * 8 + l][i * 8 + k] = A[i * 8 + k][j * 8 + l];  // Add into B array the reversed values of [M,N]->[N,M]
                            }
                        }
                        v0 = A[i * 8 + 0][j * 8 + 4];                           // Overriding variables of array A into temp variables again
                        v1 = A[i * 8 + 0][j * 8 + 5];                           // ^
                        v2 = A[i * 8 + 0][j * 8 + 6];                           // ^
                        v3 = A[i * 8 + 0][j * 8 + 7];                           // ^
                        v4 = A[i * 8 + 1][j * 8 + 4];                           // ^
                        v5 = A[i * 8 + 1][j * 8 + 5];                           // ^
                        v6 = A[i * 8 + 1][j * 8 + 6];                           // ^
                        v7 = A[i * 8 + 1][j * 8 + 7];                           // ^
                        
                        for (k = 4; k < 8; ++k) {
                            for (l = 0; l < 4; ++l) {
                                B[j * 8 + l][i * 8 + k] = A[i * 8 + k][j * 8 + l];  // Switch variables based on location [M,N] -> [N,M]
                            }
                        }
                        
                        for (k = 4; k < 8; ++k) {                               // Switch variables based on location [M,N] -> [N,M]
                            for (l = 4; l < 8; ++l) {
                                B[j * 8 + l][i * 8 + k] = A[i * 8 + k][j * 8 + l];
                            }
                        }
                        
                        B[j * 8 + 4][i * 8 + 0] = v0;                           // Array B gets temp variables
                        B[j * 8 + 5][i * 8 + 0] = v1;                           // ^
                        B[j * 8 + 6][i * 8 + 0] = v2;                           // ^
                        B[j * 8 + 7][i * 8 + 0] = v3;                           // ^
                        B[j * 8 + 4][i * 8 + 1] = v4;                           // ^
                        B[j * 8 + 5][i * 8 + 1] = v5;                           // ^
                        B[j * 8 + 6][i * 8 + 1] = v6;                           // ^
                        B[j * 8 + 7][i * 8 + 1] = v7;                           // ^
                        
                        for (k = 2; k < 4; ++k) {
                            for (l = 4; l < 8; ++l) {
                                B[j * 8 + l][i * 8 + k] = A[i * 8 + k][j * 8 + l];
                            }
                        }
                    }
                }
            }
            break;
        }
        case 61: {                                                              // 61x67 matrix
            int v0, v1, v2, v3, v4, v5, v6, v7;                                 // Extra variables to hold values
            for (i = 0; i < 8; ++i) {                                           // Traversing
                for (j = 0; j < 7; ++j) {
                    for (k = 0; k < 8; ++k) {
                        v0 = A[i * 8 + k][j * 8 + 0];                           // Holding values for transposing using v0 - v7
                        v1 = A[i * 8 + k][j * 8 + 1];
                        v2 = A[i * 8 + k][j * 8 + 2];
                        v3 = A[i * 8 + k][j * 8 + 3];
                        v4 = A[i * 8 + k][j * 8 + 4];
                        v5 = A[i * 8 + k][j * 8 + 5];
                        v6 = A[i * 8 + k][j * 8 + 6];
                        v7 = A[i * 8 + k][j * 8 + 7];
                        B[j * 8 + 0][i * 8 + k] = v0;                           // Assign B array values to respective variables
                        B[j * 8 + 1][i * 8 + k] = v1;
                        B[j * 8 + 2][i * 8 + k] = v2;
                        B[j * 8 + 3][i * 8 + k] = v3;
                        B[j * 8 + 4][i * 8 + k] = v4;
                        B[j * 8 + 5][i * 8 + k] = v5;
                        B[j * 8 + 6][i * 8 + k] = v6;
                        B[j * 8 + 7][i * 8 + k] = v7;
                    }
                }
            }
            for (j = 0; j < 7; ++j) {                                           // Traversing
                for (i = 64; i < 67; ++i) {
                    v0 = A[i][j * 8 + 0];                                       // Holding values for transposing using v0 - v7
                    v1 = A[i][j * 8 + 1];
                    v2 = A[i][j * 8 + 2];
                    v3 = A[i][j * 8 + 3];
                    v4 = A[i][j * 8 + 4];
                    v5 = A[i][j * 8 + 5];
                    v6 = A[i][j * 8 + 6];
                    v7 = A[i][j * 8 + 7];
                    B[j * 8 + 0][i] = v0;                                       // Assign B array values to respective variables
                    B[j * 8 + 1][i] = v1;
                    B[j * 8 + 2][i] = v2;
                    B[j * 8 + 3][i] = v3;
                    B[j * 8 + 4][i] = v4;
                    B[j * 8 + 5][i] = v5;
                    B[j * 8 + 6][i] = v6;
                    B[j * 8 + 7][i] = v7;
                }
            }
            for (i = 0; i < 67; ++i) {                                          // Traversing
                v0 = A[i][56];                                                  // Hilding values for transposing using v0 - v4
                v1 = A[i][57];
                v2 = A[i][58];
                v3 = A[i][59];
                v4 = A[i][60];
                B[56][i] = v0;                                                  // Assign B array values to respective variables
                B[57][i] = v1;
                B[58][i] = v2;
                B[59][i] = v3;
                B[60][i] = v4;
            }
            break;
        }
        default:
            break;
    }
}




/* 
 * You can define additional transpose functions below. We've defined
 * a simple one below to help you get started. 
 */ 

/* 
 * trans - A simple baseline transpose function, not optimized for the cache.
 */
char trans_desc[] = "Simple row-wise scan transpose";
void trans(int M, int N, int A[N][M], int B[M][N])
{
    int i, j, tmp;

    for (i = 0; i < N; i++) {
   	 for (j = 0; j < M; j++) {
		if(i ==j) {}
		 else {
	    		tmp = A[j][i];
            		B[i][j] = tmp;
		}       
 	}
    }    

}

/*
 * registerFunctions - This function registers your transpose
 *     functions with the driver.  At runtime, the driver will
 *     evaluate each of the registered functions and summarize their
 *     performance. This is a handy way to experiment with different
 *     transpose strategies.
 */
void registerFunctions()
{
    /* Register your solution function */
    registerTransFunction(transpose_submit, transpose_submit_desc); 

    /* Register any additional transpose functions */
    registerTransFunction(trans, trans_desc); 

}

/* 
 * is_transpose - This helper function checks if B is the transpose of
 *     A. You can check the correctness of your transpose by calling
 *     it before returning from the transpose function.
 */
int is_transpose(int M, int N, int A[N][M], int B[M][N])
{
    int i, j;

    for (i = 0; i < N; i++) {
        for (j = 0; j < M; ++j) {
            if (A[i][j] != B[j][i]) {
                return 0;
            }
        }
    }
    return 1;
}

