package com.dc.csrpg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Util {
  public static List<String> readFile(String filename) {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
      List<String> result = new ArrayList<String>();
      String line;
      while ((line = reader.readLine()) != null) {
        result.add(line);
      }
      reader.close();
      return result;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
