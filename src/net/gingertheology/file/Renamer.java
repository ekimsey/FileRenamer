/*
 * This program is a very simple batch file renamer made for a friend who had very specific 
 * renaming requirements. Input would be something like the following:
 * "Sunday 10-24-15.mp3" or "Lifeline 5-3-14.jpg" or "Sunday 03-5-13 1045.jpg"
 * The required format is the date is like so: "YYYY-MM-DD other_text.ext"
 * 
 * This short program takes a single command line argument: the path of the 
 * root of the directory of files to rename. i.e. - /Users/jsmith/Documents/folderOfFiles
 * 
 * To use the tool, you must have java installed on your machine. Open up a Terminal window
 * and enter: java -jar "/path/to/Renamer.jar" "/path/to/folder/to/rename"
 * 
 * The program will rename all of the files within the folder supplied. It is recommended to 
 * test this program on a small batch of files first so you can check if the files are renamed
 * correctly.
 */

package net.gingertheology.file;

import java.io.File;
import java.util.regex.*;

public class Renamer {

	public static void main(String[] args) {
		
		File folder = new File(args[0]);
		File[] listOfFiles = folder.listFiles();
		
		getAllFiles(listOfFiles);
	}
	
	public static void getAllFiles(File[] listOfFiles) {
		for(int i = 0; i < listOfFiles.length; i++) {
			if(listOfFiles[i].isFile()) {
				String name = listOfFiles[i].getName();
				if(name.equals(".DS_Store")) {
					continue;
				}
				String path = listOfFiles[i].getPath().substring(0, listOfFiles[i].getPath().length() - name.length());
				//System.out.println(path);
				//System.out.println(name);
				//Pattern p = Pattern.compile("^(.*(\\d{1,}-\\d{1,}-\\d{1,}).*)$");
				Pattern p = Pattern.compile("(0?[1-9]|1[0-2])[-](0?[1-9]|[12]\\d|3[01])[-]\\d{2,4}");
				Matcher m = p.matcher(name);
				if(m.find()) {
					//System.out.println(name.substring(m.start(),m.end()));
					String[] splitStr = name.split("\\.");
					name = splitStr[0];
					String extension = splitStr[1];
					String date = name.substring(m.start(),m.end());
					date = processDate(date);
					name = name.replaceAll("(0?[1-9]|1[0-2])[-](0?[1-9]|[12]\\d|3[01])[-]\\d{2,4}", "");
					name = date + " " + name.trim() + "." + extension;
					name = name.replaceAll("\\s{2,}", " ");
					name = path + name;
					File newFile = new File(name);
					listOfFiles[i].renameTo(newFile);
				}
			}
		}
	}
	
	public static String processDate(String date) {
		String[] splitDate = date.split("-");
		int year = Integer.parseInt(splitDate[2]);
		if(year > 50 && year < 100) {
			year += 1900;
		} else if(year >= 0 && year < 50) {
			year += 2000;
		}
		date = year + "-" + splitDate[0] + "-" + splitDate[1];
		return date;
	}
}
