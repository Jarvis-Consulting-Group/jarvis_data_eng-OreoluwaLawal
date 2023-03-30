# Introduction
The Java Grep application is a program developed to search a file with a pattern provided and outputs the matched lines to a text file.
A root directory is passed and the program filters recursively through the subfolders if any and files and delivers an output. Utilized BufferedReader, FileReader, BufferedWriter and FileWriter to read and write into files.
The project is written in Java 8 and implemented features such as Lambdas and Streams for faster processing of a sequence of data. The application is packaged using Docker and uploaded to Docker Hub, and Maven was utilized to manage dependencies and build the app. 

# Quick Start
There are two ways to use the app, firstly 3 inputs  are required;

- ```` regex ```` : The pattern to be searched for in the file.
- ```` rootpath ```` : The path directory
- ```` outfile ```` : File that contains the matched lines.

Run using jar file:
````bash 
# build package with maven
mvn clean compile package

java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp ${regex} ${rootpath} ${outfile}
````

Run using Docker Image:
````bash 
# pull docker image
docker pull oreoluwalawal/grep

docker run --rm -v `pwd`/data:/data -v `pwd`/log:/log oreoluwalawal/grep ${regex} ${rootpath} ${outfile}
````

# Implementation
## Pseudocode
````bash 
matchedLines = []

for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
        
writeToFile(matchedLines)
````
## Performance Issue
Due to Java having a limited heap memory space, the program will throw an OutOfMemoryException if it is processing a large file.
This happens because List instances returned are stored in the memory taking up space.
Implementing Stream API will resolve this issue by performing intermediate operations until it reaches the terminal operation and the intermediate operations are not stored in the heap memory.

# Test
The application was tested manually. Errors were logged using Log4j, and the program was run with different test cases by implementing different patterns and directories.
The output file was checked to view if the matched lines were documented correctly.

# Deployment

The grep app was dockerized to enable it to be easily consumed. First login to docker and build the docker image
````bash 
# package with maven
mvn clean package

# build the docker image locally
docker build -t ${docker_user}/grep .

````
Then push the image to docker hub
````bash 
docker push ${docker_user}/grep
````

# Improvement
- Implement Stream API to make the memory more efficient.
- The functionality to skip some files can be added if the user doesn't want all the files under the directory to be searched.
- Provide more details of matched lines in the files, such as stating which files contain the patterns and what line number it is.