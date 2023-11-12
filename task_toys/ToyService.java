import java.util.ArrayList;
import java.util.List;

public class ToyService {
    
    private final List<Toy> toys;

    public ToyService(){
        this.toys = new ArrayList<>(); 
    }
// Создание новой игрушки
    public void createToy(String title, Integer number, Integer frequency) {
        Integer id = toys.size() + 1;
        this.toys.add(new Toy(id, title, number, frequency));
    }
// Получить все игрушки списком
    public List<Toy> getAllToys() {
        return toys;
    }
// Вывести все игрушки в наличии в терминал
    public void seeAllToys() {
        for (Toy toy : this.toys){
           System.out.println(toy.toString());
        }
    }
// Уменьшить количество игрушек по id
    public void decreaseToy(Integer id) {
        Integer newNumber = 0;
        for (Toy toy : this.toys){
            if (id == toy.getID()){
                newNumber = toy.getNumber() - 1;
                toy.setNumber(newNumber);;
            } 
        }
    }
// Удалить игрушку по id
    public void deteteToy(Integer id) {
        for (Toy toy : this.toys){
            if (id == toy.getID()){
                this.toys.remove(toy);
            } else if (id < ((toy).getID())) {
                Integer newId = (toy).getID() - 1;
                toy.setID(newId);
            }
        }
    }

}
