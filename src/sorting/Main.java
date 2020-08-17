package sorting;

import sorting.sorters.Sorter;

import java.io.FileNotFoundException;

public class Main {
    private static String sortingType = "natural";
    private static String dataType = "word";
    private static String inputFile;
    private static String outputFile;

    public static void main(final String[] args) {
        try {
            getDataType(args);
            Sorter sorter = Sorter.create(dataType, sortingType, inputFile);
            sorter.inputData();
            sorter.execute();
            if (outputFile == null) {
                System.out.println(sorter);
            } else {
                FileService.save(outputFile, sorter.toString());
            }
        } catch (SortingException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getDataType(String[] args) throws SortingException {
        for (int i = 0; i < args.length; i++) {
            if ("-dataType".equals(args[i])) {
                try {
                    dataType = args[i + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new SortingException("No data type defined!");
                }
                i++;
                continue;
            }
            if ("-sortingType".equals(args[i])) {
                try {
                    sortingType = args[i + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new SortingException("No sorting type defined!");
                }
                i++;
                continue;
            }

            if ("-inputFile".equals(args[i])) {
                try {
                    inputFile = args[i + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new SortingException("No sorting type defined!");
                }
                i++;
                continue;
            }

            if ("-outputFile".equals(args[i])) {
                try {
                    outputFile = args[i + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new SortingException("No sorting type defined!");
                }
                i++;
                continue;
            }
            System.out.println("\"" + args[i] + "\"" + " isn't a valid parameter. It's skipped.");
        }
    }
}
