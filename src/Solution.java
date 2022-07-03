import java.lang.Math;

public class Solution {
    public static void main(String[] args) {
        //цель программы сформировать сетку для тейдинга на покупку и продажу монеты исходя из ее исторических максимумов и минимумов
        //вводные данные, можно редактировать
        double money = 3747.94*2; // количество монет для разбивки
        double max = 2;// максимальное значение цены монеты
        double min = 0.511; //минимальное значение цены монеты
        double aggresion = 1.3 ; //степень агрессивной торговли умножение экстремумов х^1.3 на коэффициент
        double[] itr = new double[10]; // количество элементов сетки стоимости актива только четные числа!

        //расчеты не редактируются

        double[] mon = new double[itr.length];// количество элементов сетки обёма средств
        double[] agrs = new double[itr.length];//массив весовых коэффициентов агрессивной торговли
        double mat = max - min; //диапазон относительно нуля
        System.out.println("Общий объем средств равен " + money + " монет");

        int a = agrs.length / 2;
        double summ = 0; //нормированная сумма всех коэффициентов
        for (int i = 1; i <= agrs.length / 2; i++) {//генерируем массив коэффициентов
            agrs[a] = Math.pow(i, aggresion);//степень коэффициента умножения
            System.out.println("коэффициент № " + i + " умножение на " + agrs[a]);
            agrs[agrs.length / 2 - i] = agrs[a];
            summ = summ + (2 * agrs[a]);
            a++;
        }
        summ = summ / agrs.length; //нормирование суммы коэфф.


        System.out.println("Разброс относительно нуля равен " + mat + " долл.");
        double step = mat / (itr.length - 1); // шаг сетки в единицах
        double stepmon = money / (mon.length); // шаг сетки в единицах
        System.out.println("Шаг сетки равен " + step + " долл.");
        System.out.println("Средний шаг по объему средств " + stepmon + " монет");
        int j = 0; // цикл
        for (double i = min; i <= max; i = i + step) {//финальный цикл сборки всех параметов
            int k = j + 1;
            itr[j] = i;
            mon[j] = stepmon * agrs[j] / summ;
            System.out.println("Объем " + mon[j] + " монет линия сетки № " + k + " значение " + itr[j] + " долл.");
            j++;
        }


    }
}