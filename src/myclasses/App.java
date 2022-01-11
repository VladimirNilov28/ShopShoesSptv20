/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Customer;
import entity.Income;
import entity.Purchase;
import entity.Shoes;
import interfaces.Keepeing;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import tools.SaverToBase;

public class App {
    private Scanner scanner = new Scanner(System.in);
    private List<Shoes> shoeses = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Purchase> purchases = new ArrayList<>();
//    private Keepeing keeper = new SaverToBase();
    private SaverToBase saverToBase = new SaverToBase();
    private List<Income> incomes = new ArrayList<>();
    private Keepeing keeper;
    Income income = new Income();

    
    public App(){
        keeper = new SaverToBase();
        shoeses = keeper.loadShoeses();
        customers = keeper.loadCustomers();
        purchases = keeper.loadPurchases();
        incomes = keeper.loadIncome();

    }
    
    public void run(){
       String repeat = "yes";
       do{
           System.out.println("Выберите номер задачи: ");
           System.out.println("1: Закрыть программу");
           System.out.println("2: Добавить Обувь");
           System.out.println("3: Список Обуви");
           System.out.println("4: Добавить покупателя");
           System.out.println("5: Список Покупателей");
           System.out.println("6: Купить обувь");
           System.out.println("7: Доход магазина за месяц");
           System.out.println("8: Изменить обувь");
           System.out.println("9: Изменить покупателя");
           System.out.println("10: Дохот ");
           int task = getNumber();
            switch (task) {
                case 1:
                    repeat="exit";
                    System.out.println("Пока! :)");
                    break;
                case 2:
                    addShoes();
                    break;
                case 3:
                    printListShoeses();
                    break;
                case 4:
                    addCustomer();
                    break;
                case 5:
                    printListCustomers();
                    break;
                case 6:
                    givenShoes();
                    break;
                case 7:
                    IncomePerMonth();
                    break;
                case 8:
                    changeShoe();
                    break;
                case 9:
                    changeCustomer();
                    break;
                case 10:
                    Income();
                    break;
                default:
                    System.out.println("Введите номер из списка!");;
            }
            
        }while("yes".equals(repeat));
    }
    private void addCustomer() {
        Customer customer = new Customer();
        System.out.print("Введите имя покупателя: ");
        customer.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию покупателя: ");
        customer.setLastname(scanner.nextLine());
        System.out.print("Введите телефон покупателя: ");
        customer.setPhone(scanner.nextLine());
        System.out.println("Введите деньги покупателя");
        customer.setMoney(scanner.nextInt());scanner.nextLine();
        System.out.println("Покупатель инициирован как: "+customer.toString());
        customers.add(customer);
        keeper.saveCustomers(customers);
    }
    /*
    ------------------------------------------------------------------------------------------------
*/
    private void addShoes() {
       Shoes shoes = new Shoes();
       System.out.print("Введите название обуви: ");
       shoes.setShoesName(scanner.nextLine());
       System.out.print("Введите количество экземпляров обуви: ");
       shoes.setQuantity(scanner.nextInt());scanner.nextLine();
       shoes.setCount(shoes.getQuantity());
       System.out.println("Введите цену обуви");
       shoes.setPrice(scanner.nextInt());scanner.nextLine();
       System.out.println("Обувь инициирована как: "+shoes.toString());    
       shoeses.add(shoes);
       keeper.saveShoeses(shoeses);
    }
   /*
    ------------------------------------------------------------------------------------------------
*/
    private Set<Integer> printListShoeses() {
        Set<Integer> setNumbersShoeses = new HashSet<>();
        System.out.println("Список обуви: ");
        for (int i = 0; i < shoeses.size(); i++) {
            if(shoeses.get(i) != null 
                    && shoeses.get(i).getCount() > 0
                    && shoeses.get(i).getCount() < shoeses.get(i).getQuantity() + 1){
                System.out.printf("%s. %s. %s.  В наличии: %s%n"
                       ,i+1
                       ,shoeses.get(i).getShoesName()
                       ,shoeses.get(i).getPrice()
                       ,shoeses.get(i).getCount()
                );
                setNumbersShoeses.add(i+1);
           }

       }
        return setNumbersShoeses;
    }
   /*
    ------------------------------------------------------------------------------------------------
*/
    private void Income() {
        System.out.println("Доход магазина: ");
        System.out.printf("Выручка магазина составляет: %.2f%n",income.getGeneralMoney());
        keeper.saveIncome(incomes);
        
    }

    
    private Set<Integer> printListCustomers(){
        System.out.println("----- Список покупателей -----");
        Set<Integer> setNumbersCustomers = new HashSet<>();
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i) != null){
                System.out.printf("%d. %s %s. тел.: %s деньги: %s %n"
                       ,i+1
                       ,customers.get(i).getFirstname()
                       ,customers.get(i).getLastname()
                       ,customers.get(i).getPhone()
                       ,customers.get(i).getMoney()
                );
                setNumbersCustomers.add(i+1);
            }
        }
        if(setNumbersCustomers.isEmpty()){
            System.out.println("Список покупателей пуст.");
        }
        return setNumbersCustomers;
    }

       /*
    ------------------------------------------------------------------------------------------------
*/
private void givenShoes() {
    System.out.println("*ПОКУПКА ОБУВИ*"); 
    System.out.println("-----------------------------");
    printListShoeses();
    System.out.print("Выберите нужную модель обуви:");
    int shoesNum= scanner.nextInt(); scanner.nextLine();
    System.out.println("-----------------------------");
    printListCustomers();
    System.out.print("Выберите нужного покупателя: ");
    int customerNum= scanner.nextInt(); scanner.nextLine();
    Purchase purchase= new Purchase();
    purchase.setShoes(shoeses.get(shoesNum-1));
    purchase.setCustomer(customers.get(customerNum-1));
    Calendar c = new GregorianCalendar();
    purchase.setGivenShoes(c.getTime());
    if(purchase.getCustomer().getMoney()>=purchase.getShoes().getPrice()){
        System.out.println("-----------------------------");
        System.out.printf("Кроссовки %s купил %s %s за %s евро %s%n",
                    purchase.getShoes().getShoesName(),
                    purchase.getCustomer().getFirstname(),
                    purchase.getCustomer().getLastname(),
                    purchase.getShoes().getPrice(),
                    purchase.getGivenShoes()
        );
        purchase.getCustomer().setMoney(purchase.getCustomer().getMoney()-purchase.getShoes().getPrice());
        income.setGeneralMoney(income.getGeneralMoney()+purchase.getShoes().getPrice());
        purchase.getShoes().setCount(purchase.getShoes().getCount() - 1);
        purchases.add(purchase);
        keeper.saveShoeses(shoeses);
        keeper.saveCustomers(customers);
        keeper.savePurchases(purchases);
    }else{
        System.out.println("Этот пользователь не может совершить покупку, так как у него не хватает денег!");
    }
}
   /*
    ------------------------------------------------------------------------------------------------
*/


    private void changeCustomer() {
        System.out.println("Выберите пользователя, которого хотитие изменить: ");
        int n=0;
        Set<Integer> setNumbersCustomers = new HashSet<>();
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i) != null){
                System.out.printf("%d. %s %s. тел.: %s деньги: %s %n"
                       ,i+1
                       ,customers.get(i).getFirstname()
                       ,customers.get(i).getLastname()
                       ,customers.get(i).getPhone()
                       ,customers.get(i).getMoney()
                );
                setNumbersCustomers.add(i+1);
            }
        }
        if (n<1) {
            System.out.println("Нет зарегистрированных пользователей");
            
        }
        System.out.print("Выберите номер пользователя: ");
        int numberUser= getNumber();
        String repeat="yes";
        do{
            System.out.println("0: Выход");
            System.out.println("1: Изменить имя пользователя");
            System.out.println("2: Изменить фамилию пользователя");
            System.out.println("3: Изменить номер пользователя");
            System.out.println("4: Добваление денег");
            System.out.println("Выберите номер параметра, который хотите изменить: ");
            int num=getNumber();
            switch(num){
                case 0:
                    repeat="no";
                    break;
                case 1:
                    System.out.print("Введите новое имя пользователя: ");
                    String newFirstname=scanner.nextLine();
                    customers.get(numberUser-1).setFirstname(newFirstname);
                    keeper.saveCustomers(customers);
                    break;
                case 2:
                    System.out.print("Введите новую фамилию пользователя: ");
                    String newLastname=scanner.nextLine();
                    customers.get(numberUser-1).setLastname(newLastname);
                    keeper.saveCustomers(customers);
                    break;
                case 3:
                    System.out.print("Введите новый номер пользователя: ");
                    String newPhone=scanner.nextLine();
                    customers.get(numberUser-1).setPhone(newPhone);
                    keeper.saveCustomers(customers);
                    break;
                case 4:
                    System.out.println("Введите сумму для добавленмя денег: ");
                    int addMoney = scanner.nextInt(); scanner.nextLine();
                    customers.get(numberUser-1).setMoney(customers.get(numberUser-1).getMoney()+addMoney);
                    keeper.saveCustomers(customers);
                    break;
            }
         }while("yes".equals(repeat));
    
    }

    private void changeShoe() {
        System.out.println("Список обуви: ");
        for (int i = 0; i < shoeses.size(); i++) {
            if(shoeses.get(i) != null 
                    && shoeses.get(i).getCount() > 0
                    && shoeses.get(i).getCount() < shoeses.get(i).getQuantity() + 1){
                System.out.printf("%s. %s. %s.  В наличии: %s%n"
                       ,i+1
                       ,shoeses.get(i).getShoesName()
                       ,shoeses.get(i).getPrice()
                       ,shoeses.get(i).getCount()
                );
           }

       }
        System.out.println("Выберите обувь которую хотите изменить: ");
        System.out.print("Выберите номер обуви: ");
        int numberModel= getNumber();
        String repeat="yes";
        do{
            System.out.println("0: Выход");
            System.out.println("1: Изменить название обуви");
            System.out.println("2: Изменить цену обуви");
            System.out.println("3: Изменить количество экземпляров");
            System.out.println("Выберите номер параметра, который хотите изменить: ");
            int num = getNumber();
            switch(num){
                case 0:
                    repeat="no";
                    break;
                case 1:
                    System.out.print("Введите новое название обуви: ");
                    String newShoesName = scanner.nextLine();
                    shoeses.get(numberModel-1).setShoesName(newShoesName);
                    keeper.saveShoeses(shoeses);
                    break;
                case 2:
                    System.out.print("Введите новую цену модели: ");
                    int newPrice = getNumber();
                    shoeses.get(numberModel-1).setPrice(newPrice);
                    keeper.saveShoeses(shoeses);
                    break;
                case 3:
                    System.out.print("Введите новое количество экземпляров: ");
                    int newQuantity = getNumber();
                    shoeses.get(numberModel-1).setQuantity(newQuantity);
                    keeper.saveShoeses(shoeses);
                    break;
            }
         }while("yes".equals(repeat));
    }
    private int getNumber() {
        int number;
        do{
           String strNumber = scanner.nextLine();
           try {
               number = Integer.parseInt(strNumber);
               return number;
           } catch (NumberFormatException e) {
               System.out.println("Введено \""+strNumber+"\". Выбирайте номер ");
           }
       }while(true);
    }

    private void IncomePerMonth(){
        System.out.println("Доход за месяц");
        double income = 0;
        System.out.println("Введите год, за который надо вывести доход: ");
        int years = getNumber();
        System.out.println("Введите месяц (1-12), за который надо вывести доход: ");
        int chosenMonths = getNumber()-1;
        for (int i = 0; i < purchases.size(); i++) {
            Date date = purchases.get(i).getGivenDate();
            boolean toSum = summator(date, chosenMonths, years);
            if (purchases.get(i)!=null & toSum) {
                income+=purchases.get(i).getShoes().getPrice();
            }
        }
        System.out.println("Доход магазина: ");
        System.out.printf("Выручка магазина составляет: %.2f%n",income);
        }
    private boolean summator(Date date, int choicemonth, int years) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            if(month==choicemonth && year==years){
                return true;
        }
            return false;
    }
}
        
  
   /*
    ------------------------------------------------------------------------------------------------
*/

