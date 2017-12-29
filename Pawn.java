import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import javafx.util.Pair;

public class Pawn extends Piece
{    
    public Pawn(char c,Board b)
    {
        super(c,"Pawn",b);
        value = 100; 
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
    
    private int pawnDirection(int change)
    {
        if (colour == 'W')
        {
            return -1*change;
        }
        else
        {
            return change;
        }        
    }
    
    private boolean capture(int startRow, int startCol, int endRow, int endCol)
    {
        return endRow-startRow == pawnDirection(1) && absDiff(startCol,endCol) == 1;
    }
    
    public boolean legalMove(int startRow, int startCol, int endRow, int endCol)
    {        
        
        if (!super.legalMove(startRow,startCol,endRow,endCol))
            return false;
        else if (startCol == endCol)
        {
            if (board.hasPiece(endRow,endCol))
                return false;
            else if (startRow % 5 == 1 && endRow-startRow == pawnDirection(2))
            {
                if (endRow-startRow == 2 && !board.hasPiece(startRow+1,startCol))
                    return true;
                else if (endRow-startRow == -2 && !board.hasPiece(startRow-1,startCol))
                    return true;
                else
                    return false;
            }
            else if (endRow-startRow == pawnDirection(1))
                return true;
            else
                return false;
        }
        else if (board.enemyRoar(startRow,startCol,endRow,endCol) && capture(startRow,startCol,endRow,endCol))
        {
            return true;            
        }
        else
        {   
            return false;
        }
        
    }
    
    public Image pic (int width, int height, String file)//Method to get create pictures
    {
        Image img = null;
        
        try
        {
            img = ImageIO.read (new File (file));
        }
        catch (IOException e)
        {
        }

        return img.getScaledInstance (width, height, Image.SCALE_DEFAULT);//Allows you to set size of image accordingly
    } 
}
