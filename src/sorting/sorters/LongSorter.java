package sorting.sorters;

import sorting.SortingException;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class LongSorter extends Sorter {
    private final List<Long> numbers = new ArrayList<>();

    public LongSorter(String filepath, String sortingType) throws FileNotFoundException {
        super(filepath, sortingType);
    }

    @Override
    public void execute() throws SortingException {
        sort();
    }

    @Override
    protected void sortNatural() {
        int totalSize = numbers.size();
        StringBuilder sb = new StringBuilder();
        numbers.sort(Long::compareTo);
        sb.append("Total numbers: ").append(totalSize).append("\n");
        sb.append("Sorted data: ");
        numbers.forEach(l -> sb.append(l).append(" "));
        text = sb.toString();
    }

    @Override
    protected void sortByCount() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total numbers: ").append(numbers.size()).append(".\n");
        Map<Long, Integer> map = new LinkedHashMap<>();
        Set<Long> longSet = new HashSet<>(numbers);
        longSet.stream().sorted(Long::compareTo).forEach(l -> map.put(l, Collections.frequency(numbers, l)));
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(e -> {
            String text = String.format("%d: %d time(s), %d%%",
                    e.getKey(), e.getValue(), occurrencePercentage(numbers.size(), e.getValue()));
            sb.append(text).append("\n");
        });
        text = sb.toString();
    }

    @Override
    public void inputData() {
        while (scanner.hasNextLong()) {
            String input = scanner.next();
            long number;
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\"" + " isn't a long. It's skipped.");
                continue;
            }
            numbers.add(number);
        }
        scanner.close();
    }
}
