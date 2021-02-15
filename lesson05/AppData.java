import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AppData {

    private static String[] header;
    private static int[][] data;
    private static final AppData DATA = new AppData();

    private AppData() {
    }

    public static String[] getHeader() {
        return header;
    }

    public static void setHeader(String[] header) {
        DATA.header = header;
    }

    public static int[][] getData() {
        return data;
    }

    public static void setData(int[][] data) {
        DATA.data = data;
    }

    public static void save(String file) {
        String[] header = DATA.header;
        int[][] data = DATA.data;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < header.length; i++) {
                writer.write(header[i]);
                if (i != header.length - 1){
                    writer.write(";");
                }
            }
            writer.write("\n");
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    writer.write(String.valueOf(data[i][j]));
                    if (j != data[i].length - 1){
                        writer.write(";");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
