import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import javafx.util.Pair;

public class King extends Piece
{  
    
    public King(char c,Board b)
    {
        super(c,"King",b);
        value = 20000;
        hasMoved = false;
        castling = false;
    }
    
    public double getValue()
    {
        return value;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public ArrayList<Pair<Integer,Integer>> getAllOpponentMoves()
    {
        ArrayList<Pair<Integer,Integer>> badMoves = new ArrayList<Pair<Integer,Integer>>(0);
        
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (board.hasPiece(i,j))
                {
                    Piece piece = board.getPiece(i,j);
                    if (piece.getColour() != colour)
                    {
                        for (Pair<Integer,Integer> move:piece.generateMoves(i,j))
                        {
                            badMoves.add(move);
                        }
                    }
                }
            }
        }
        
        return badMoves;
    }
    
    public ArrayList<Pair<Integer,Integer>> generateMoves(int startRow, int startCol)
    {
        moves = new ArrayList<Pair<Integer,Integer>>(0);
        ArrayList<Pair<Integer,Integer>> badMoves = getAllOpponentMoves();
        
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                Pair<Integer,Integer> currentPoint = new Pair<Integer,Integer>(i,j);
                if (legalMove(startRow,startCol,i,j) && !badMoves.contains(currentPoint))
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
            return absDiff(startCol,endCol) == 1;
        else if (startCol == endCol)
            return absDiff(startRow,endRow) == 1;
        else
            return absDiff(startRow,endRow) == 1 && absDiff(startCol,endCol) == 1;
    }
    
    public boolean isCheck()
    {
        return getAllOpponentMoves().contains(new Pair<Integer,Integer>(row,col));
    }
}