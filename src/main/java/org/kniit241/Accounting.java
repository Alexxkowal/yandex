package org.kniit241;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Accounting {
    HashMap<String, ArrayList<Monthly_report>> monthlyReports;
    HashMap<String, Year_report> yearReportHashMap;
    HashSet<String> years;
    public Accounting(){
        monthlyReports = new HashMap<>();
        yearReportHashMap = new HashMap<>();
        years = new HashSet<>();
    }
    public void execute() throws IOException {
        getYears();
        System.out.println("Добро пожаловать!");
        System.out.println("1. Считать все месячные отчеты за год");
        System.out.println("2. Считать годовой отчет");
        System.out.println("3. Вывести информацию о всех месячных отчетах за год");
        System.out.println("4. Вывести информацию о годовом отчете");
        System.out.println("5. Сравнить отчеты");
        System.out.println("6. Закрыть программу");
        int action;
        Scanner scanner = new Scanner(System.in);
        String year;
        while (true){
            System.out.print("Введите действие: ");
            action = scanner.nextInt();
            scanner.nextLine();
            switch (action){
                case 1:
                    System.out.print("Введите год: ");
                    year = scanner.nextLine();
                    if (!(years.contains(year))){
                        System.out.print("Такого года нет!");
                        break;
                    }
                    else {
                        read_months(year);
                        System.out.println("Отчеты считаны");
                        break;
                    }
                case 2:
                    System.out.print("Введите год: ");
                    year = scanner.nextLine();
                    if (!(years.contains(year))){
                        System.out.println("Такого года нет!");
                        break;
                    }
                    else {
                        read_years(year);
                        System.out.println("Отчет считан");
                        break;
                    }
                case 3:
                    System.out.print("Введите год: ");
                    year = scanner.nextLine();
                    if (!(years.contains(year))){
                        System.out.println("Такого года нет!");
                        break;
                    }
                    else {
                        for (Monthly_report monthlyReport : monthlyReports.get(year)){
                            System.out.println(monthlyReport);
                        }
                        break;
                    }
                case 4:
                    System.out.print("Введите год: ");
                    year = scanner.nextLine();
                    if (!(years.contains(year))){
                        System.out.println("Такого года нет!");
                        break;
                    }
                    else {
                        System.out.println((yearReportHashMap.get(year)));
                        break;
                    }
                case 5:
                    System.out.println("Введите год: ");
                    year = scanner.nextLine();
                    if (!(years.contains(year))){
                        System.out.println("Такого года нет!");
                        break;
                    }
                    if (yearReportHashMap.get(year) == null || monthlyReports.get(year).isEmpty()){
                        System.out.println("Сначала считайте все отчеты!!!");
                        break;
                    }
                    Year_report year_report = yearReportHashMap.get(year);
                    int year_expense = year_report.getExpense();
                    int year_not_expense = year_report.getNot_expense();
                    ArrayList<Monthly_report> monthly_reports_List = monthlyReports.get(year);
                    int month_expense = 0;
                    int month_not_expense = 0;
                    for (Monthly_report monthlyReport : monthly_reports_List){
                        month_expense+=monthlyReport.getMonth_expense();
                        month_not_expense+=monthlyReport.getMonth_not_expense();
                    }
                    int expense_different = year_expense - month_expense;
                    int not_expense_different = year_not_expense - month_not_expense;
                    System.out.println("Годовой отчет траты = "+ year_expense + " " + "годовой отчет доход = "+year_not_expense+"\n" +
                            "Траты по месячным отчетам = "+month_expense +" "+ "Доходы по месячным отчетам = "+ month_not_expense+"\n" +
                            "Разница трат = "+ expense_different + " "+ "Разница доходов = "+ not_expense_different);
                    break;
                case 6:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Введено неверное действие!!");
                    break;



            }
        }
    }
    private void getYears(){
        File file = new File("data");
        String[] file_names = file.list();
        String file_year;
        if (file_names!=null){
            for (String i: file_names){
                file_year = i.split("\\.")[1].substring(0,4);
                years.add(file_year);
            }
        }
    }
    private void read_months(String year) throws IOException {
        ArrayList<Monthly_report> monthly_reports_list = new ArrayList<>();
        File file = new File("data");
        String[] file_names = file.list();
        if (file_names != null)
        {
            for (String report : file_names)
            {
                String[] report_parts = report.split("\\.");
                String report_year = report_parts[1].substring(0, 4);
                String report_month = report_parts[1].substring(4);
                if (report_parts[0].equals("y") || !(report_year.equals(year))){
                    continue;
                }
                else{
                    Monthly_report monthlyReport = new Monthly_report(report_year, report_month);
                    monthly_reports_list.add(monthlyReport);
                }
            }
        }
        else{
            System.out.println("Файл пуст");
            return;
        }
        monthlyReports.put(year, monthly_reports_list);
    }

    private void read_years(String year) throws IOException {
        File file = new File("data");
        String[] file_names = file.list();
        if (file_names != null)
        {
            for (String report : file_names)
            {
                String[] report_parts = report.split("\\.");
                String report_year = report_parts[1].substring(0, 4);
                String report_month = report_parts[1].substring(4);
                if (report_parts[0].equals("m") || !(report_year.equals(year))){
                    continue;
                }
                else{
                    Year_report year_report = new Year_report(year);
                    yearReportHashMap.put(year, year_report);
                }
            }
        }
        else{
            System.out.println("Файл пуст");
            return;
        }
    }

}
