public class Game {
    public final int size = 4;
    public int[][] field = new int[size][size];

    public Game(){
        for (int  i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                field[i][j] = 0;
            }
        }
    }

    int getRandomNum(){
        if (Math.random() > 0.9)
            return 2;
        return 4;
    }

    int getRandomEmptyCell(){
        int[] cells = new int[size * size];
        int realSize = 0;
        for (int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(field[i][j] == 0){
                    cells[realSize] = i * 10 + j;
                    realSize++;
                }
            }
        }
        // Тут мы имеем массив всех пустых ячеек
        int peek = (int) (Math.random() * realSize);
    }

    void start(){
        getRandomEmptyCell();
    }
}
