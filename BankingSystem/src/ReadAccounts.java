import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ReadAccounts {
    private String filePath;

    public ReadAccounts(String filePath) {
        this.filePath = filePath;
    }

    public LinkedList<String> getFirstNames() {
        return getColumnData(0);
    }

    public LinkedList<String> getLastNames() {
        return getColumnData(1);
    }

    public LinkedList<Integer> getAccountNumbers() {
        return getIntColumnData(2);
    }

    public LinkedList<Integer> getBalances() {
        return getIntColumnData(3);
    }

    private LinkedList<String> getColumnData(int columnIndex) {
        LinkedList<String> columnData = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header row
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (columnIndex < parts.length) {
                    columnData.add(parts[columnIndex]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnData;
    }

    private LinkedList<Integer> getIntColumnData(int columnIndex) {
        LinkedList<Integer> columnData = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header row
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (columnIndex < parts.length) {
                    columnData.add(Integer.parseInt(parts[columnIndex].trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnData;
    }
}
