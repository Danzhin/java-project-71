package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0", description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private static String filePath1;

    @Parameters(index = "1", description = "path to second file")
    private static String filePath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: ${DEFAULT-VALUE}]", defaultValue = "stylish")
    private static String format;

    @Override
    public Integer call() throws Exception {
        String differ = Differ.generate(filePath1, filePath2);
        System.out.println(differ);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}