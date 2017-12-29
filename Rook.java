import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import javafx.util.Pair;

public class Rook extends Piece
{
    public Rook(char c,Board b)
    {
        super(c,"Rook",b);
        value = 500;
        hasMoved = false;
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
        else if (startRow == endRow)
        {
            for (int i = min(startCol,endCol)+1; i < max(startCol,endCol); i++)
            {
                if (board.hasPiece(startRow,i))
                    return false;
            }
            return true;
        }
        else if (startCol == endCol)
        {
            for (int i = min(startRow,endRow)+1; i < max(startRow,endRow); i++)
            {
                if (board.hasPiece(i,startCol))
                    return false;
            }
            return true;
        }
        else
            return false;
    }
}    
