import java.util.*;
import java.util.stream.Collectors;

public class Cabinet {
    private static final int MAX_CABINET_SIZE = 52;
    private static final int NOT_ALLOWED_POSITION = 40;

    private final Map<Integer, String> allocated;
    private final Random random;

    public Cabinet(Random random) {
        this.allocated = new HashMap<>();
        allocate("X", NOT_ALLOWED_POSITION);

        this.random = random;
    }

    public void allocate(String crew) {
        allocated.put(generateRandomNumber(), crew);
    }

    public void allocate(String crew, int position) {
        allocated.put(position, crew);
    }

    private int generateRandomNumber() {
        int randomPosition = 0;

        while (isAllocated(randomPosition)) {
            randomPosition = random.nextInt(MAX_CABINET_SIZE);
        }

        return randomPosition;
    }

    private boolean isAllocated(int position) {
        return allocated.containsKey(position);
    }


    public List<String> toList() {
        return allocated.keySet().stream()
                .filter(position -> allocated.get(position) != null)
                .map(position -> position + 1 + ": " + allocated.get(position) + " ")
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        List<String> positions = toList();

        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < positions.size(); i += 13) {
            buffer.append(
                    String.join(",", positions.subList(i, Math.min(13 + i, positions.size())))
            ).append(System.lineSeparator());
        }

        return buffer.toString();
    }
}
