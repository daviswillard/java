#   Clearing previous build results and
#   creating target directory for compilation results
rm -rf target; mkdir -p target

#   External libs
#   JColor: https://repo1.maven.org/maven2/com/diogonunes/JColor/5.5.1/JColor-5.5.1.jar
#   JCommander: https://repo1.maven.org/maven2/com/beust/jcommander/1.78/jcommander-1.78.jar

#   extract external libraries
cd target
jar -xf ../lib/JColor-5.5.1.jar
jar -xf ../lib/jcommander-1.78.jar
cd ..

#   Compiling java sources and putting result in target directory
javac -sourcepath ./src/java -cp target -d target src/java/ex02/edu/school21/printer/edu.school21.sockets.app/*.java
cp -R ./src/resources ./target/

rm -rf target/META-INF

#   Running project:
#       Arg1 is white color symbol
#       Arg2 is black color symbol
#       Arg3 is the absolute path to .bmp image
#   Like that:
#   java -classpath ./target ex02.edu.school21.printer.edu.school21.sockets.app.Program "Arg1" "Arg2" "Arg3"


#   Create .jar archive
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target .

#   Running java program
java -jar target/images-to-chars-printer.jar --white=blue --black=red