import java.util.*;
import java.io.*;

public class ps6_partI {
    public static void main (String[] args) {
        
        System.out.println(schoolNumber("CFA"));
    }
    
    public static final String[] SCHOOL_ABBREVS = {"CAS", "CFA", "CGS",
        "COM", "ENG", "GMS", "GRS", "GSM", "LAW", "MED", "MET", "SAR", "SED",
        "SHA", "SMG", "SPH", "SSW", "STH"};
    
    public static int schoolNumber(String school) {
        int schoolNr = 0;
        for(int i=0; i<SCHOOL_ABBREVS.length; i++) {
            if(SCHOOL_ABBREVS[i].equals(school)) {
                schoolNr = i;
                break;
            } else {
                schoolNr = -1;
            }
        }
        return schoolNr;
    }
}