import java.io.*;

public class FileInputOutput {
    public static void main(String[] args){
        String fileLocation = "C:\\Users\\alehk\\IdeaProjects\\JavaCore\\test.txt";
        System.out.println(new File(fileLocation).exists());

        try(BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            String line;

            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found " + fileLocation);
        }
        catch (IOException e){
            System.out.println("Error reading file " + fileLocation);
        }

/*        Path path = Paths.get("test.txt");
        Files.write(path, text.getBytes());

        String someText = Files.readString(path);
        System.out.println(someText);*/

    }
}
