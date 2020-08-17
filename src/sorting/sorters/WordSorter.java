package sorting.sorters;

import sorting.SortingException;

import java.io.FileNotFoundException;
import java.util.*;

public class WordSorter extends Sorter {
    private final List<String> words = new ArrayList<>();

    public WordSorter(String filepath, String sortingType) throws FileNotFoundException {
        super(filepath, sortingType);
    }

    @Override
    public void execute() throws SortingException {
        sort();
    }

    @Override
    protected void sortNatural() {
        words.sort(String::compareTo);
        StringBuilder sb = new StringBuilder();
        sb.append("Total words : ").append(words.size()).append("\n");
        sb.append("Sorted data: ");
        words.forEach(s -> sb.append(s).append(" "));
        text = sb.toString();
    }

    @Override
    protected void sortByCount() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total words: ").append(words.size()).append(".\n");
        Map<String, Integer> map = new LinkedHashMap<>();
        Set<String> longSet = new HashSet<>(words);
        longSet.stream().sorted(String::compareTo).forEach(s -> map.put(s, Collections.frequency(words, s)));
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> {
                    String text = String.format("%s: %d time(s), %d%%",
                            e.getKey(), e.getValue(), occurrencePercentage(words.size(), e.getValue()));
                    sb.append(text).append("\n");
                });
        text = sb.toString();
    }

    @Override
    public void inputData() {
        while (scanner.hasNext()) {
            String word = scanner.next();
            words.add(word);
        }
        scanner.close();
    }
}
