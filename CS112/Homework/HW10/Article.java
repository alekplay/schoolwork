/*
 * Article.java
 *
 * A simple blueprint class representing an article from the
 * Simple English Wikipedia. An article has a title, a body,
 * and a filename that corresponds to its location in the
 * on-disk database of articles.
 *
 * Author: Alexander Breen (abreen@bu.edu)
 * Completed by: 
 * Date: March 21, 2014
 */

import java.util.*;

public class Article implements Comparable<Article> {

    private String title;
    private String body;
    public String filename;

    public Article(String t, String b) {
        this.title = t;
        this.body = b;

    }

    public String getTitle() {
        return this.title;
    }

    public String getBody() {
        return this.body;
    }



    public String toString() {
        String t = getTitle();
        String s = t +  "\n";

        for (int i = 0; i < t.length(); i++)
            s += "=";

        s += "\n";
        s += wrapString(getBody());

        return s;
    }


    public int compareTo(Article other) {
        return this.getTitle().compareTo(other.getTitle());
    }

    /*
     * Given a string, return a new string with newlines in the
     * appropriate places to keep lines less than 80 characters
     * long. This method will convert single existing newlines
     * to double newlines, to simulate a paragraph break.
     */
    private String wrapString(String s) {
        String out = "";
        String[] lines = s.split("\r\n?|\n");

        int cols = 0;
        for (int i = 0; i < lines.length; i++) {
            String[] words = lines[i].split(" ");

            for (int j = 0; j < words.length; j++) {
                if (cols + words[j].length() >= 80) {
                    cols = words[j].length() + 1;
                    out += "\n" + words[j] + " ";
                } else {
                    cols += words[j].length() + 1;
                    out += words[j] + " ";
                }
            }

            cols = 0;
            out += "\n\n";
        }

        return out;
    }
}
