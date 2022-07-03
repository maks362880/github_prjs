import java.lang.Math;

public class ETH {
    public static void main(String[] args) {
        //цель программы сформировать сетку для тейдинга на покупку и продажу ETHы исходя из ее исторических максимумов и минимумов
        //вводные данные, можно редактировать
        double money = 0.33773695*2; // количество ETH для разбивки
        int max = 4778;// максимальное значение цены ETHы
        int min = 1750; //минимальное значение цены ETHы
        double aggresion = 1.3 ; //степень агрессивной торговли умножение экстремумов х^1.3 на коэффициент
        double[] itr = new double[10]; // количество элементов сетки стоимости актива только четные числа!

        //расчеты не редактируются

        double[] mon = new double[itr.length];// количество элементов сетки обёма средств
        double[] agrs = new double[itr.length];//массив весовых коэффициентов агрессивной торговли
        double mat = max - min; //диапазон относительно нуля
        System.out.println("Общий объем средств равен " + money + " ETH");

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
        System.out.println("Средний шаг по объему средств " + stepmon + " ETH");
        int j = 0; // цикл
        for (double i = min; i <= max; i = i + step) {//финальный цикл сборки всех параметов
            int k = j + 1;
            itr[j] = i;
            mon[j] = stepmon * agrs[j] / summ;
            System.out.println("Объем " + mon[j] + " ETH линия сетки № " + k + " значение " + itr[j] + " долл.");
            j++;
        }


    }
}