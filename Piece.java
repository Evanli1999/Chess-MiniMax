import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import javafx.util.Pair;

public class Piece
{
    protected Board board;
    protected int row, col;
    protected char colour;
    protected double value;
    protected String type; 
    protected BufferedImage image;
    //protected ArrayList<Move> moves;
    protected boolean hasMoved;
    protected boolean castling;
    protected ArrayList<Pair<Integer,Integer>> moves;
    
    public Piece()
    {
        colour = ' ';
        type = " ";
    }
    
    public Piece(char c, String t, Board b)
    {
        colour = c;
        type = t;
        row = 0;
        col = 0;
        board = b;
        hasMoved = false;
        
        try
        {
            if (c == 'W')
                image = ImageIO.read (new File ("Images//White " + type + ".png"));
            else if (c == 'B')
                image = ImageIO.read (new File ("Images//Black " + type + ".png"));
        }
        catch (IOException e)
        {
        }
    }
    
    public boolean equals(Piece p)
    {
        return colour == p.getColour() && type.equals(p.getType());
    }
    
    protected int absDiff(int a, int b)
    {
        return b >= a ? b-a:a-b;
    }
    
    public void setPos(int a,int b)
    {
        row = a;
        col = b;
    }
    
    public boolean hasMoved()
    {
        return hasMoved;
    }
    
    public void setMoved(boolean move)
    {
        hasMoved = move;
    }
    
    public void setCastle(boolean c)
    {
        castling = c;
    }
    
    public String getType()
    {
        return type;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public Pair<Integer,Integer> getPos()
    {
        return new Pair<Integer,Integer>(row,col);
    }
    
    public char getColour()
    {
        return colour;
    }
    
    public int min(int a, int b)
    {
        return a < b ? a:b;
    }
    
    public int max(int a, int b)
    {
        return a > b ? a:b;
    }
    
    public ArrayList<Pair<Integer,Integer>> generateMoves(int startRow, int startCol)
    {
        moves = new ArrayList<Pair<Integer,Integer>>(0);        
        return moves;
    }
    
    public ArrayList<Pair<Integer,Integer>> getMoves()
    {
        return moves;
    }
    
    private boolean isInRange(int check)
    {
        return 0 <= check && check <= 7;
    }
    
    public boolean legalMove(int startRow, int startCol, int endRow, int endCol)
    {        
        if (startRow == endRow && startCol == endCol)
            return false;
        else if (isInRange(startRow) && isInRange(startCol) && isInRange(endRow) && isInRange(endCol))
        {
            if (board.hasPiece(endRow,endCol))
            {
                Piece start = board.getPiece(startRow,startCol);
                Piece end = board.getPiece(endRow,endCol);
                
                return start.getColour() != end.getColour();
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    
    public void move(int endRow,int endCol)
    {
        if (legalMove(row,col,endRow,endCol))
        {
            row = endRow;
            col = endCol;
        }
    }
    
    
    
    
    public boolean isAttacked()
    {
        return true;
    }
    
    public void addMove(Point destiny)
    {
       // moves.add(new Move(pos,destiny,this));
    }
}