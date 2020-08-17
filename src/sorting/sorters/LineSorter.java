package sorting.sorters;

import sorting.SortingException;

import java.io.FileNotFoundException;
import java.util.*;

public class LineSorter extends Sorter {
    private final List<String> lines = new ArrayList<>();

    public LineSorter(String filepath, String sortingType) throws FileNotFoundException {
        super(filepath, sortingType);
    }

    @Override
    public void execute() throws SortingException {
        sort();
    }

    @Override
    protected void sortNatural() {
        lines.sort(String::compareTo);
        StringBuilder sb = new StringBuilder();
        sb.append("Total lines : ").append(lines.size()).append("\n");
        sb.append("Sorted data: ").append("\n");
        lines.forEach(s -> sb.append(s).append("\n"));
        text = sb.toString();
    }

    @Override
    protected void sortByCount() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total lines: ").append(lines.size()).append(".\n");
        Map<String, Integer> map = new LinkedHashMap<>();
        Set<String> longSet = new HashSet<>(lines);
        longSet.stream().sorted(String::compareTo).forEach(s -> map.put(s, Collections.frequency(lines, s)));
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(e -> {
            String text = String.format("%s: %d time(s), %d%%",
                    e.getKey(), e.getValue(), occurrencePercentage(lines.size(), e.getValue()));
            sb.append(text).append("\n");
        });
        text = sb.toString();
    }

    @Override
    public void inputData() {
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        scanner.close();
    }
}
