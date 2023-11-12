import java.util.Scanner;

public class View {
    private final LotteryService lotteryService;

    public View(){
        this.lotteryService = new LotteryService();
    }

    public void run(){
        Commands com = Commands.NONE;
        System.out.println("Доступные команды:");
        System.out.println("ADDTOY - добавить игрушку в ассортимент магазина");
        System.out.println("TOYLIST - посмотреть список всех игрушек в магазине");
        System.out.println("LOTTERY - провести лотерею");
        System.out.println("PRIZE - получить приз из лотереи");
        System.out.println("EXIT - завершить программу");

        while (true) {
            String command = prompt("Введите команду: ");
            try {
                com = Commands.valueOf(command);
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод команды.");
            }

            if (com == Commands.EXIT) return;
            try{
                switch (com) {
                    case ADDTOY:
                        System.out.println("Для добавления игрушки введите:");
                        String title = prompt("Название: ");
                        Integer number = promptInt("Количество: ");
                        Integer frequency = promptInt("Частота выпадения: ");
                        lotteryService.addToy(title, number, frequency);
                        break;
                    case TOYLIST:
                        System.out.println("Ассортимент магазина:");
                        lotteryService.toyList();
                        break;
                    case LOTTERY:
                        System.out.println("Розыгрыш игрушки выполнен.");
                        lotteryService.doLottery();
                        break;
                    case PRIZE:
                        System.out.println("Вы можете увидеть полученный приз в файле prizes.txt");
                        lotteryService.takePrize();
                        break;
                }
            } catch (Exception e){
                System.out.println("Неверный ввод или последовательность комманд.");
            }
        }
    }
// Ввод строки
    private String prompt(String message) {
        System.out.print(message);
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    
    }
// Ввод числа
    private Integer promptInt(String message) {
        System.out.print(message);
        Scanner in = new Scanner(System.in);
        return Integer.valueOf(in.nextLine());
    }

}
