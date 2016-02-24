This program is a very simple batch file renamer made for a friend who had very specific renaming requirements. Input would be something like the following:
"Sunday 10-24-15.mp3" or "Lifeline 5-3-14.jpg" or "Sunday 03-5-13 1045.jpg"
The required format is the date is like so: "YYYY-MM-DD other_text.ext"

This short program takes a single command line argument: the path of the 
root of the directory of files to rename. i.e. - /Users/jsmith/Documents/folderOfFiles

To use the tool, you must have java installed on your machine. Open up a Terminal window and enter: java -jar "/path/to/Renamer.jar" "/path/to/folder/to/rename"

The program will rename all of the files within the folder supplied. It is recommended to test this program on a small batch of files first so you can check if the files are renamed correctly.