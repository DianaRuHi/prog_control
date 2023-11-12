import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;


public class LotteryService {
    private final ToyService toyService;
    private final Queue<Toy> prizes;
    
    public LotteryService(){
        this.toyService = new ToyService();
        this.prizes = new PriorityQueue<>(idComparator); 
    }
// Добавить игрушку в список (ассортимент магазина)
    public void addToy(String title, Integer number, Integer frequency) {
        this.toyService.createToy(title, number, frequency);
    }
// Вывести список всех игрушек
    public void toyList(){
        this.toyService.seeAllToys();
    }
// Провести лотерею (добавление одной игрушки в очередь)
    public void doLottery() {
        List<Toy> toys = toyService.getAllToys();
        Integer totalTrequency = 0;
        for (Toy toy : toys){
            totalTrequency += toy.getFrequency();
        }
        Random random = new Random(); 
        int randomNumber = random.nextInt(totalTrequency);
        totalTrequency = 0;

        for (Toy toy : toys){
            if ((totalTrequency <= randomNumber) && (randomNumber < (totalTrequency + toy.getFrequency()))){
                // добавляем игрушку в очередь
                this.prizes.add(toy);
                // уменьшаем количество игрушек в списке или удаляем игрушку если их не осталось
                toyService.decreaseToy(toy.getID());
                if ((toy.getNumber()) - 1 == 0){
                    toyService.deteteToy(toy.getID());
                }
                
            }
            totalTrequency += toy.getFrequency();
        }
    }
// Получить разыгранную игрушку (из очереди) (вывод в файл)
    public void takePrize() {
        Toy prizeTake = this.prizes.remove();
        File file = new File("task_toys/prizes.txt");

        FileWriter fr = null;
        try {
            fr = new FileWriter(file,true);
            fr.write(prizeTake.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
//Анонимный класс компаратора для работы с PriorityQueue
    public static Comparator<Toy> idComparator = new Comparator<Toy>(){
        @Override
        public int compare(Toy t1, Toy t2) {
            return (int) (t1.getID() - t2.getID());
        }
    };

}
