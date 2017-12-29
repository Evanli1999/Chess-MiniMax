import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;
import javax.imageio.*;
import java.util.ArrayList;
import javafx.util.Pair;

public class Knight extends Piece
{    
    public Knight(char c,Board b)
    {
        super(c,"Knight",b);
        value = 320;
    }
    
    public double getValue()
    {
        return value;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public ArrayList<Pair<Integer,Integer>> generateMoves(int startRow, int startCol)
    {
        moves = new ArrayList<Pair<Integer,Integer>>(0);
        
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (legalMove(startRow,startCol,i,j))
                {
                    moves.add(new Pair<Integer,Integer>(i,j));
                }
            }
        }
        
        return moves;
    }
    
    public boolean legalMove(int startRow, int startCol, int endRow, int endCol)
    {        
        if (!super.legalMove(startRow,startCol,endRow,endCol))
            return false;
        else if (absDiff(startRow,endRow) == 1 && absDiff(startCol,endCol) == 2)
            return true;
        else if (absDiff(startCol,endCol) == 1 && absDiff(startRow,endRow) == 2)
            return true;
        else
            return false;
    }
}