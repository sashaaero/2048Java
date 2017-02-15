import static java.awt.event.KeyEvent.*;

public class Game
{
    public final int size = 4;
    public int[][] field = new int[size][size];
    public boolean over = false;

    public Game()
    {
        for (int  i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                field[i][j] = 0;
            }
        }
    }

    int getRandomNum()
    {
        if (Math.random() < 0.9)
        {
            return 2;
        }
        return 4;
    }

    int getRandomEmptyCell()
    {
        int[] cells = new int[size * size];
        int realSize = 0;
        for (int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(field[i][j] == 0)
                {
                    cells[realSize] = i * 10 + j;
                    realSize++;
                }
            }
        }
        // Тут мы имеем массив всех пустых ячеек
        int peek = (int) (Math.random() * realSize);

        return cells[peek];
    }

    void create()
    {
        int xy = this.getRandomEmptyCell();
        int i = xy / 10;
        int j = xy % 10;
        int val = this.getRandomNum();
        this.field[i][j] = val;
    }

    void move(int direction)
    {
        if (this.over){return;}
        int[][] old_field = new int[size][size];
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                old_field[i][j] = field[i][j];
            }
        }

        switch (direction)
        {
            case VK_UP:
                for (int row = 1; row < size; row++)
                {
                    for (int col = 0; col < size; col++)
                    {
                        if (field[row][col] == 0)
                        {
                            continue;
                        }
                        int newRow = row - 1;
                        while (newRow >= 0 && field[newRow][col] == 0)
                        {
                            newRow--;
                        }

                        if (newRow == -1)
                        {
                            field[0][col] = field[row][col];
                            field[row][col] = 0;
                        }
                        else if (field[newRow][col] == field[row][col])
                        {
                            field[newRow][col] *= 2;
                            field[row][col] = 0;
                        }
                        else {
                            int val = field[row][col];
                            field[row][col] = 0;
                            field[newRow + 1][col] = val;
                        }
                    }
                }
                break;
            case VK_RIGHT:
                for (int col = size - 2; col >= 0; col--)
                {
                    for (int row = 0; row < size; row++)
                    {
                        if (field[row][col] == 0)
                        {
                            continue;
                        }
                        int newCol = col + 1;
                        while (newCol < size && field[row][newCol] == 0)
                        {
                            newCol++;
                        }

                        if (newCol == size) {
                            field[row][size - 1] = field[row][col];
                            field[row][col] = 0;
                        }
                        else if (field[row][newCol] == field[row][col])
                        {
                            field[row][newCol] *= 2;
                            field[row][col] = 0;
                        }
                        else
                        {
                            int val = field[row][col];
                            field[row][col] = 0;
                            field[row][newCol - 1] = val;
                        }
                    }
                }
                break;
            case VK_DOWN:
                for (int row = size - 2; row >= 0; row--)
                {
                    for (int col = 0; col < size; col++)
                    {
                        if (field[row][col] == 0)
                        {
                            continue;
                        }
                        int newRow = row + 1;
                        while (newRow < size && field[newRow][col] == 0)
                        {
                            newRow++;
                        }

                        if (newRow == size)
                        {
                            field[size - 1][col] = field[row][col];
                            field[row][col] = 0;
                        }
                        else if (field[newRow][col] == field[row][col])
                        {
                            field[newRow][col] *= 2;
                            field[row][col] = 0;
                        }
                        else
                        {
                            int val = field[row][col];
                            field[row][col] = 0;
                            field[newRow - 1][col] = val;
                        }
                    }

                }
                break;
            case VK_LEFT:
                for (int col = 1; col < size; col++)
                {
                    for (int row = 0; row < size; row++)
                    {
                        if (field[row][col] == 0)
                        {
                            continue;
                        }

                        int newCol = col - 1;
                        while (newCol >= 0 && field[row][newCol] == 0)
                        {
                            newCol--;
                        }

                        if (newCol == -1)
                        {
                            field[row][newCol + 1] = field[row][col];
                            field[row][col] = 0;
                        }
                        else if (field[row][newCol] == field[row][col])
                        {
                            field[row][newCol] *= 2;
                            field[row][col] = 0;
                        }
                        else
                        {
                            int val = field[row][col];
                            field[row][col] = 0;
                            field[row][newCol + 1] = val;
                        }
                    }
                }
                break;
        }
        boolean changed = false;
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(field[i][j] != old_field[i][j])
                {
                    changed = true;
                }
                if(field[i][j] == 2048)
                {
                    over = true;
                    return;
                }
            }
        }
        if (changed)
        {
            create();
        }
    }

    void start()
    {
        create();
        create();
    }
}
