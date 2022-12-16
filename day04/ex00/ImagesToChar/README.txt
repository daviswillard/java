#   Clearing previous build results and
#   creating target directory for compilation results
rm -rf target; mkdir -p target

#   Compiling java sources and putting result in target directory
javac -sourcepath ./src/java -d target src/java/edu/school21/printer/app/*.java

#   Running project:
#       Arg1 is white color symbol
#       Arg2 is black color symbol
#       Arg3 is the absolute path to .bmp image
#   Like that:
#   java -classpath ./target app.ex00.edu.school21.printer.Program "Arg1" "Arg2" "Arg3"


#   Default case
java -classpath ./target app.ex00.edu.school21.printer.Program . 0 /Users/dwillard/Desktop/jaaav/day04/ex00/ImagesToChar/image.bmp