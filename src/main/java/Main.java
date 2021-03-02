import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    private static final String NEOZAL = "손너잘";
    private static final String NABOM = "나봄";

    public static void main(String[] args) {
        Cabinet cabinet = new Cabinet(new Random());

        cabinet.allocate(NEOZAL, 14);
        cabinet.allocate(NABOM, 15);

        Path path = Paths.get("src/main/resources/crews_nickname.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            allocate(reader, cabinet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(cabinet);
    }

    private static void allocate(BufferedReader reader, Cabinet cabinet) throws IOException {
        String name;

        while ((name = reader.readLine()) != null) {
            allocateIfNotAlreadyAllocated(cabinet, name);
        }
    }

    private static void allocateIfNotAlreadyAllocated(Cabinet cabinet, String name) {
        if (!validateAllocatedNickname(name)) {
            cabinet.allocate(name);
        }
    }

    private static boolean validateAllocatedNickname(String name) {
        return name.equals(NEOZAL) || name.equals(NABOM);
    }
}
