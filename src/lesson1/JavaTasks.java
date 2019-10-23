package lesson1;

import kotlin.NotImplementedError;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    // O(N^2)-трудоёмкость
    // O(N)-ресурсоемкость
    static public void sortAddresses(String inputName, String outputName) throws IOException {
        TreeMap<String, TreeMap<Integer, List<String>>> addrBook = new TreeMap<>();
        Scanner in = new Scanner(new File(inputName));
        while (in.hasNextLine()) {
            List<String> lines = new ArrayList<>();
            List<String> names = new ArrayList<>();
            TreeMap<Integer, List<String>> temp = new TreeMap<>();
            List<String> temp3 = new ArrayList<>();
            List<String> temp2 = new ArrayList<>();
            Collections.addAll(lines, in.nextLine().split(" - "));
            if (lines.size() != 2) throw new IllegalArgumentException();
            if (lines.get(0).matches(".+[0-9]+")) throw new IllegalArgumentException();
            Collections.addAll(names, lines.get(0).split(" "));
            if (names.size() != 2) throw new IllegalArgumentException();
            Collections.addAll(temp2, lines.get(1).split(" "));
            if (addrBook.get(temp2.get(0)) != null) {
                temp = addrBook.get(temp2.get(0));
                if (temp.get(Integer.parseInt(temp2.get(1))) != null) temp3 = temp.get(Integer.parseInt(temp2.get(1)));
            }
            temp3.add(lines.get(0));
            temp.put(Integer.parseInt(temp2.get(1)), temp3);
            Collections.sort(temp3);
            if (addrBook.get(temp2.get(0)) != null) addrBook.replace(lines.get(1), temp);
            else addrBook.put(temp2.get(0), temp);
        }
        try (FileWriter writer = new FileWriter(outputName)) {
            for (Map.Entry<String, TreeMap<Integer, List<String>>> item : addrBook.entrySet()){
                String addr = item.getKey();
                for (Map.Entry<Integer, List<String>> item2 : item.getValue().entrySet())
                    writer.write(String.format("%s %s - %s\n", addr, item2.getKey().toString(), item2.getValue().toString().replaceAll("[\\[\\]]", "")));
            }
        }
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    // O(N)-трудоёмкость
    // O(N)-ресурсоемкость
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        List<Double> list = new ArrayList<>();
        Scanner in = new Scanner(new File(inputName));
        while (in.hasNextLine()) {
            Double temp = Double.parseDouble(in.nextLine());
            if (temp > 500.0 || temp < -273.0) throw new IllegalArgumentException();
            list.add(temp);
        }
        Collections.sort(list);
        try (FileWriter writer = new FileWriter(outputName)) {
            for (Double element : list) writer.write(element.toString() + "\n");
        }
    }


    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
