import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class torrent {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Макс\\AppData\\Roaming\\uTorrent\\uTorrent.exe";
        String srch = "magnet:?xt=urn:";
        String cmd = " /LAUNCHBUNDLEDURL ";
        ArrayList list = new ArrayList();
        System.out.print("Укажите адрес для считывание из файла (как пример C:\\\\1.htm): ");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String nameFile = bufferedReader.readLine();
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(nameFile)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                int index = line.indexOf("magnet:?xt=urn:");
                if (index != -1) {
                    //  System.out.println(index);
                    line = line.substring(index, index + 60);
                    list.add(line);
                }
            }
         } catch (IOException e) {
            e.printStackTrace();
       } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
               e.printStackTrace();
           }
        }
        System.out.println("Массив получен: ");
        System.out.println(list);

        ArrayList outlist = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            outlist.add(path + cmd + list.get(i));
        }
        System.out.println("Массив на запись в bat файл:");
        System.out.println(outlist);

        System.out.print("Выберете путь по которому создастся bat файл (как пример C:\\\\1.bat): ");
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(System.in));
        String pathwrite = bufferedReader1.readLine();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathwrite));
        for (int i = 0; i < outlist.size(); i++) {
            bufferedWriter.write(String.valueOf(outlist.get(i)));
            bufferedWriter.newLine();
        }
        bufferedReader1.close();
        bufferedWriter.close();
    }

}

