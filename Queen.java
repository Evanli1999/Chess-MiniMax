import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import javafx.util.Pair;

public class Queen extends Piece
{        
    public Queen(char c,Board b)
    {
        super(c,"Queen",b);
        value = 900;
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
        else if (absDiff(startCol,endCol) != absDiff(startRow,endRow) && startRow != endRow && startCol != endCol)
            return false;
        else if (startCol == endCol)
        {
            for (int i = min(startRow,endRow)+1; i < max(startRow,endRow); i++)
            {
                if (board.hasPiece(i,startCol))
                    return false;
            }
            return true;
        }
        else if (startRow == endRow)
        {
            for (int i = min(startCol,endCol)+1; i < max(startCol,endCol); i++)
            {
                if (board.hasPiece(startRow,i))
                    return false;
            }
            return true;
        }
        else
        {
            for (int i = min(startRow,endRow); i < max(startRow,endRow); i++)
            {
                for (int j = min(startCol,endCol)+1; j  < max(startCol,endCol); j++)
                {
                    if (absDiff(i,startRow) == absDiff(j,startCol))
                    {
                        if (board.hasPiece(i,j))
                            return false;
                    }               
                }
            }
            return true;
        }
    }
}