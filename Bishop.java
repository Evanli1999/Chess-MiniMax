import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import javafx.util.Pair;

public class Bishop extends Piece
{
    public Bishop(char c,Board b)
    {
        super(c,"Bishop",b);
        value = 330;
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
        Piece bishop = board.getPiece(startRow,startCol);

        if (!super.legalMove(startRow,startCol,endRow,endCol))
            return false;
        else if (absDiff(startRow,endRow) != absDiff(startCol,endCol))
            return false;
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