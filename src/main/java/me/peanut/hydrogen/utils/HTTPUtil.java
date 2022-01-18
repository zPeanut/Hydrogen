package me.peanut.hydrogen.utils;

import com.vdurmont.semver4j.Semver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by peanut on 18/01/2022
 */
public class HTTPUtil {

    public static String commitDate;
    public static String commitTime;

    public static String getCurrentCommitHash() {
        try {
            URL url = new URL("https://api.github.com/repos/zpeanut/hydrogen/commits/master");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String readLine = br.readLine();
            String[] splitLine = readLine.split("\"");
            String fullCommitHash = splitLine[3];
            String shortCommitHash = fullCommitHash.substring(0, Math.min(fullCommitHash.length(), 7));
            return shortCommitHash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getCurrentCommitDate() {
        try {

            // read github api line

            URL url = new URL("https://api.github.com/repos/zpeanut/hydrogen/commits/master");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String readLine = br.readLine();

            // get date

            String[] splitLine = readLine.split("\"");
            String fullDate = splitLine[23];

            // split date string into 2 -> date, time

            String[] splitDate = fullDate.split("T");
            String localDate = splitDate[0];
            String localTimeFull = splitDate[1];

            // remove the "Z" at end of time string

            String localTimeShort = localTimeFull.substring(0, Math.min(fullDate.length(), 8));

            // put values into class defined variables

            commitTime = localTimeShort;
            commitDate = localDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getWebsiteLine(String URL) {
        try {
            URL url = new URL(URL);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            if ((line = br.readLine()) != null) {
                return line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
