/*
 * DatabaseIterator.java
 *
 * Given a path to the directory containing articles,
 * an instance of this class can iterate through the files,
 * giving a client a reference to a new Article object
 * corresponding to the file.
 *
 * Author: Alexander Breen (abreen@bu.edu)
 * Completed by: 
 * Date: March 21, 2014
 */

import java.util.*;
import java.io.*;

public class DatabaseIterator implements Iterator<Article> {

    private String directoryPath;
    private File[] children;
    private int next;

    public DatabaseIterator(String path) {
        this.directoryPath = path;
        this.next = 0;
        this.children = findChildren(path);
    }


    public boolean hasNext() {
        return next < children.length - 1;
    }


    public Article next() {
        File thisFile = children[next];
        Scanner s = null;

        try {
            s = new Scanner(thisFile, "UTF-8");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("child does not exist -- " +
                                       "was it deleted?");
        }

        String title = s.nextLine();
        String body = "";

        while (s.hasNextLine())
            body += s.nextLine() + "\n";

        Article a = new Article(title, body);

        next++;
        return a;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }


    public void reset() {
        next = 0;
    }


    public int getNumArticles() {
        return children.length;
    }

    private File[] findChildren(String path) throws IllegalArgumentException {
        File dir = new File(path);

        if (!dir.exists())
            throw new IllegalArgumentException("directory does not exist");

        if (!dir.isDirectory())
            throw new IllegalArgumentException("path does not refer to " +
                                               "a directory");

        File[] cs = dir.listFiles();

        if (cs == null)
            throw new RuntimeException("an error occured getting files " +
                                       "under directory");

        return cs;
    }
}
