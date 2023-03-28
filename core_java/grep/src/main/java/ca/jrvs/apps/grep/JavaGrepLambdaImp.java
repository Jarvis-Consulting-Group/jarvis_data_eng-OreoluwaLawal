package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaGrepLambdaImp extends JavaGrepImp {
    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);
    public static void main(String[] args) {
        if(args.length != 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        BasicConfigurator.configure();

        JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
        javaGrepLambdaImp.setRegex(args[0]);
        javaGrepLambdaImp.setRootPath(args[1]);
        javaGrepLambdaImp.setOutFile(args[2]);

        try{
            javaGrepLambdaImp.process();
        }catch (Exception ex){
            javaGrepLambdaImp.logger.error("Error: Unable to process ", ex);
        }

    }
    @Override
    public List<File> listFiles(String rootDir) throws IOException {
        List<File> files = new ArrayList<>();

        files = Files.walk(Paths.get(rootDir)).filter(Files::isRegularFile)
                .map(Path::toFile).collect(Collectors.toList());
        return files;
    }

    @Override
    public List<String> readLines(File inputFile) throws IOException {
        Path file = Paths.get(inputFile.getPath());

        return Files.lines(file).collect(Collectors.toList());
    }
}
