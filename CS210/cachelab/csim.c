/* CS210 Programming Assignment 3
 * Part A
 * Aleksander Skjoelsvik in collaboration with Raphael Baysa
 */

#include "cachelab.h"
#include <unistd.h>
#include <stdlib.h>
#include <getopt.h>
#include <stdio.h>
#include <math.h>

typedef struct {
    int validTag;
    long tagBit;
} cacheLine;

typedef struct {
    cacheLine *line;
} cacheSet;

int main(int argc, char *argv[]) {
    FILE *file;         // To read the file
    int i;              // Counter
    int j;              // Counter
    int s;              // Number of sets
    int S;              // Cache size
    int E;              // Number of lines
    int b;              // Number of bits
    cacheSet *cache;    // The cache
    int opt;            // For reading the parameters
    long maskS;         // Address mask (to get set of current address)
    long maskT;         // Address mask (to get tag of current address)
    int set;            // Keep track of the current set
    long tag;           // Keep track of the current tag
    int missFlag;       // Flag to set if miss
    int hit = 0;        // Hit counter
    int miss = 0;       // Miss counter
    int evict = 0;      // Evict counter
    char chOpt;         // To keep track of current type of cache call
    unsigned addr;      // To keep track of current address
    int size;           // To keep track of current size

    while((opt = getopt(argc, argv, "s:E:b:t:")) != -1) {   // Go through each input parameter
        switch(opt) {
            case 's':
                s = atoi(optarg);                           // Set the number of sets
                S = pow(2, s);                              // Calculate the cache size
                break;
            case 'E':
                E = atoi(optarg);                           // Set the number of lines
                break;
            case 'b':
                b = atoi(optarg);                           // Set the number of bits
                break;
            case 't':
                file = fopen(optarg, "r");                  // Open the file
                break;
            default:
                return 1;
        }
    }
    maskT = -1 << (b + s);  // Set the tag mask
    maskS = S - 1;          // Set the set mask
    
    cache = (cacheSet *)malloc(S * sizeof(cacheSet));               // Create the cache
    for (i = 0; i < S; ++i) {                                       // Go through each cache set
        cache[i].line = (cacheLine *)malloc(E * sizeof(cacheLine)); // Create the line
        for(j = 0; j < E; ++j) {                                    // Go through each line
            cache[i].line[j].validTag = 0;                          // Set the validTag to 0
        }
    }
    
    while (fscanf(file, " %c %x,%d", &chOpt, &addr, &size) > 0) {   // Go through each line in the file
        tag = addr & maskT;                                         // Get the tag for the current address
        set = (addr >> b) & maskS;                                  // Get the set for the current address
        switch(chOpt) {                                             // Check what operation it is
            case 'M':                                               // Data modify operation
                missFlag = 1;
                for (i = 0; i < E && missFlag; ++i) {                                               // Go through each line until it's found
                    if (cache[set].line[i].validTag && cache[set].line[i].tagBit == tag) {          // If the current line is valid and the tag matches
                        missFlag = 0;                                                               // No longer a miss
                        hit += 2;                                                                   // Increase hit-count by two
                        for (j = 1; j < i+1; ++j) {                                                 // Go through from start of cache to current spot
                            cache[set].line[i + 1 - j].validTag = cache[set].line[i - j].validTag;  // Move everything
                            cache[set].line[i + 1 - j].tagBit = cache[set].line[i - j].tagBit;      // --""--
                        }
                        cache[set].line[0].validTag = 1;                                            // Set to valid and tag to this tag (store in cache)
                        cache[set].line[0].tagBit = tag;
                    }
                }
                
                if (missFlag) {                                                                     // If it wasn't found
                    ++hit;                                                                          // Increase hit-count by one
                    ++miss;                                                                         // Increase miss-count by one
                    if(cache[set].line[E - 1].validTag) {                                           // If last line is valid, increase evict-count by one too
                        ++evict;
                    }
                    for (i = 1; i < E; ++i) {                                                       // Go through each line
                        cache[set].line[E - i].validTag = cache[set].line[E - 1 - i].validTag;      // Move everything up
                        cache[set].line[E - i].tagBit = cache[set].line[E - 1 - i].tagBit;          // Move everything up
                    }
                    cache[set].line[0].validTag = 1;                                                // Set to valid and tag to this tag (store in cache)
                    cache[set].line[0].tagBit = tag;
                }
                break;
            case 'L':                                                                               // If data load, do as data store
            case 'S':                                                                               // If data store operation
                missFlag = 1;
                for (i = 0; i < E && missFlag; ++i) {                                               // Go through each line
                    if(cache[set].line[i].validTag && cache[set].line[i].tagBit == tag) {           // If valid and tag matches
                        missFlag = 0;                                                               // No longer a miss
                        ++hit;                                                                      // Increase hit-count by one
                        for (j = 1; j < i + 1; ++j) {                                              // Go through from start of cache to current spot
                            cache[set].line[i + 1 - j].validTag = cache[set].line[i - j].validTag;  // Move everything up
                            cache[set].line[i + 1 - j].tagBit = cache[set].line[i - j].tagBit;      // --""--
                        }
                        cache[set].line[0].validTag = 1;                                            // Set to valid and tag to this tag (store in cache)
                        cache[set].line[0].tagBit = tag;
                    }
                }
                if (missFlag) {                                                                     // If it wasn't found
                    ++miss;                                                                         // Increase miss-count by one
                    if (cache[set].line[E - 1].validTag) {                                          // If last line is valid, increase evict-count by one too
                        ++evict;
                    }
                    for (i = 1; i < E; ++i) {                                                      // Go through each line
                        cache[set].line[E - i].validTag = cache[set].line[E - 1 - i].validTag;      // Move everything up
                        cache[set].line[E - i].tagBit = cache[set].line[E - 1 - i].tagBit;          // --""--
                    }
                    cache[set].line[0].validTag = 1;                                                // Set valid tag and tag to this tag (store in cache)
                    cache[set].line[0].tagBit = tag;
                    printf("\n");
                }
                break;
            default:
                ;
        }
    }
    printSummary(hit, miss, evict);     // Print the summary
    
    for (i = 0; i < S; ++i) {          // Free up every line
        free(cache[i].line);
    }
    free(cache);                        // Free up the cache
    fclose(file);                       // Close the file

    return 0;
}