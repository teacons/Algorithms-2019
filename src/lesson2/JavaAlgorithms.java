package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.File;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     *
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) {
        int startInd = 0, endInd = 0;
        int fLen = first.length(), sLen = second.length();
        for (int i = 0; i < fLen; i++) {
            for (int j = 0; j < sLen; j++) {
                int x = 0;
                while (first.charAt(i + x) == second.charAt(j + x)) {
                    x++;
                    if (((i + x) >= fLen) || ((j + x) >= sLen)) break;
                }
                if (x > endInd) {
                    endInd = x;
                    startInd = i;
                }
            }
        }
        return first.substring(startInd, (startInd + endInd));
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) throws IOException {
        Set<String> answer = new HashSet<>();
        Scanner input = new Scanner(new File(inputName));
        int numRows = 0, numColumns = 0;
        while (input.hasNextLine()) {
            numRows++;
            numColumns = input.nextLine().replaceAll(" ", "").length();
        }
        input.close();
        input = new Scanner(new File(inputName));
        char[][] arrayChar = new char[numRows][numColumns];
        for(int row = 0; row < numRows; row++) {
            String strings = input.nextLine().replaceAll(" ", "");
            for(int column = 0; column < numColumns; column++) arrayChar[row][column] = strings.charAt(column);
        }
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                for (String word : words) {
                    if (arrayChar[row][col] != word.charAt(0)) continue;
                    javafx.util.Pair<Integer, Integer> pairCoord = new javafx.util.Pair<>(row, col);
                    if (find(arrayChar, numColumns, numRows, word, 1, pairCoord, new HashSet<>())) answer.add(word);
                }
            }
        }
        return answer;
    }
    private static boolean find(char[][] arrayChar, int numColumns, int numRows, String word, int k, javafx.util.Pair<Integer, Integer> coord, Set<javafx.util.Pair> oldCord) {
        int[] x = { -1, 0, 0, 1 };
        int[] y = { 0, -1, 1, 0 };
        List<javafx.util.Pair> list = new ArrayList<>();
        int rd;
        int cd;
        for (int dir = 0; dir < 4; dir++) {
            rd = coord.getKey() + x[dir];
            cd = coord.getValue() + y[dir];
            if (rd >= numRows || rd < 0 || cd >= numColumns || cd < 0) continue;
            if (arrayChar[rd][cd] == word.charAt(k)) {
                javafx.util.Pair<Integer, Integer> pairCoord = new javafx.util.Pair<>(rd, cd);
                if (!oldCord.contains(pairCoord)) list.add(pairCoord);
            }
        }
        oldCord.add(coord);
        for (javafx.util.Pair cord : list) {
            if (k + 1 == word.length()) return true;
            if (find(arrayChar, numColumns, numRows, word, k + 1, cord, oldCord)) return true;
        }
        return false;
    }
}
