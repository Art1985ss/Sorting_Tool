package sorting.sorters;

import sorting.SortingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Sorter {
    private final String filepath;
    protected final Scanner scanner;
    protected String text;
    protected String sortingType;

    public Sorter(String filepath, String sortingType) throws FileNotFoundException {
        this.filepath = filepath;
        this.sortingType = sortingType;
        if (filepath == null) {
            scanner = new Scanner(System.in);
        } else {
            scanner = new Scanner(new File(filepath));
        }
    }

    public abstract void execute() throws SortingException;

    protected abstract void sortNatural();

    protected abstract void sortByCount();

    public abstract void inputData();

    protected void sort() {
        if ("natural".equals(sortingType)) {
            sortNatural();
        } else {
            sortByCount();
        }
    }

    protected int occurrencePercentage(int totalSize, int occurrences) {
        return (int) ((double) occurrences / totalSize * 100);
    }

    public static Sorter create(String dataType, String sortingType, String filepath) throws FileNotFoundException {
        switch (dataType) {
            case "long":
                return new LongSorter(filepath, sortingType);
            case "line":
                return new LineSorter(filepath, sortingType);
            case "word":
            default:
                return new WordSorter(filepath, sortingType);
        }
    }

    @Override
    public String toString() {
        return text;
    }
}
