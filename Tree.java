/*
Необходимо преобразовать собранное на семинаре дерево поиска в полноценное левостороннее красно-чёрное дерево. 
Реализовать метод добавления новых элементов с балансировкой.
Красно-чёрное дерево имеет следующие критерии:
- Каждая нода имеет цвет (красный или черный);
- Корень дерева всегда черный;
- Новая нода всегда красная;
- Красные ноды могут быть только левым дочерним элементом;
- У красной ноды все дочерние элементы черного цвета.

Соответственно, чтобы данные условия выполнялись, после добавления элемента в дерево 
необходимо произвести балансировку, благодаря которой все критерии выше станут валидными.
Для балансировки существует 3 операции:
- левый малый поворот
- правый малый поворот
- смена цвета.

Критерии применения этих операций следующие:
- Если правый дочерний элемент красный, а левый черный, то применяем малый правый поворот
- Если левый дочерний элемент красный и его левый дочерний элемент тоже красный, то применяем малый левый поворот
- Если оба дочерних элемента красные, то делаем смену цвета
- Если корень стал красным, то перекрашиваем его в черный
 */

package prog_control;

public class Tree <V extends Comparable<V>>{
    private Node root;

    private class Node{
        private int value;
        private Color color;
        private Node left;
        private Node right;
    }
 /* 
    public boolean contains(V value){
        Node node = root;
        while (node != null){
            if(node.value.equals((value)))
                return true;
            if(node.value.compareTo(value) > 0)
                node = node.left;
            else
                node = node.right;
        }
        return false;
    }
*/
    private enum Color{
        RED, BLACK
    } 

    
    public boolean add(int value){
        if (root != null){
            boolean result = add(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }

    private boolean add(Node node, int value){
        if (node.value == value){
            return false;
        } else {
            if (node.value > value){
                if (node.left != null) {
                    boolean result  = add(node.left, value);
                    node.left = rebalance(node.left);
                    return result;
                } else {
                    node.left = new Node();
                    node.left.color = Color.RED;
                    node.left.value = value;
                    return true;
                }
            } else {
                if (node.right != null){
                    boolean result = add(node.right, value);
                    node.right = rebalance(node.right);
                    return result;
                } else {
                    node.right = new Node();
                    node.right.color = Color.RED;
                    node.right.value = value;
                    return true;
                }
            }
        }
    }

    private void colorChange(Node node){
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }

    private Node leftRotation(Node node){
        Node left = node.left;
        Node middle = left.right;
        left.left = node;
        node.left = middle;
        left.color = node.color;
        node.color = Color.RED;
        return left;
    }

    private Node rightRotation(Node node){
        Node right = node.right;
        Node middle = right.left;
        right.left = node;
        node.right = middle;
        right.color = node.color;
        node.color = Color.RED;
        return right;
    }

    private Node rebalance(Node node){
        Node result = node;
        boolean needReb;
        do{
            needReb = false;
            if (result.right != null && result.right.color == Color.RED && (result.left == null || result.left.color ==  Color.BLACK)){
                needReb = true;
                result = rightRotation(result);
            }
            if (result.left != null && result.left.color == Color.RED && (result.left.left != null && result.left.left.color == Color.RED)){
                needReb = true;
                result = leftRotation(result);
            } 
            if (result.left != null && result.left.color ==Color.RED && result.right != null  && result.right.color == Color.RED){
                needReb = true;
                colorChange(result);
            }
        } while (needReb);
        return result;
    }

}