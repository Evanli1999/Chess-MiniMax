import java.util.ArrayList;
import javafx.util.Pair;
import java.util.Random;
import java.util.Hashtable;
import java.util.Enumeration; // apparently I might need this for hashTable?

public class AI
{
    private Board board;
    private char col;
    private final int winValue = Integer.MAX_VALUE;
    private final int loseValue = Integer.MIN_VALUE;
    private final int drawValue = 0;
    private final int maxDepth = 4;
    private int nodesVisited;

    public AI(Board b, char c)
    {
        nodesVisited = 0;
        board = b;
        col = c; 
    }

    public void doMove(Board chess, Move m)
    {
        chess.move(m.getStartRow(),m.getStartCol(),m.getEndRow(),m.getEndCol());
    }

    public ArrayList<Move> getAllMoves(Board chess,char colour)
    {
        ArrayList<Move> moves = new ArrayList<Move>(0);
        Piece piece;

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                for (int a = 0; a < 8 && chess.hasPiece(i,j); a++)
                {
                    for (int b = 0; b < 8; b++)
                    {
                        piece = chess.getPiece(i,j);

                        if (piece.getColour() == colour && piece.legalMove(i,j,a,b))
                        {
                            moves.add(new Move(i,j,a,b,piece));
                        }
                    }
                }
            }
        }
        
        return moves;
    }

    public Move makeMove (Board chess)
    {
        Move best = getBestMove(chess);
        doMove(chess,best);
        return best;
    }

    private char oppColour(char c)
    {
        if (c == 'W')
            return 'B';
        else
            return 'W';
    }
    
    private int material (Piece piece)
    {
        if (piece instanceof Pawn)
            return 100;
        else if (piece instanceof Knight)
            return 320;
        else if (piece instanceof Bishop)
            return 333;
        else if (piece instanceof Rook)
            return 510;
        else if (piece instanceof Queen)
            return 880;
        else if (piece instanceof King) 
            return 20000;
        else // if this null
            return 0;
    }
    
    private int material (Board chess)
    {
        int total = 0;
        Piece piece;
        
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            { 
                if (chess.hasPiece(i,j))
                { 
                    piece = chess.getPiece(i,j);
                
                    if (piece.getColour() == col)
                    {
                        total += material(piece);
                    }
                    else
                    {
                        total -= material(piece);
                    }
                }
            }
        }   
        
        return total;
    }
    
    private Move getBestMove(Board chess)
    {
        int score;
        int bestScore = loseValue;
        Move bestMove = new Move();
        Position oldPos = chess.getPosition();
        
        for (Move move: getAllMoves(chess,col))
        {
            doMove(chess,move);
            score = evaluate(chess,0);
            if (score > bestScore)
            {
                bestScore = score;
                bestMove = move;
            }
            chess.setPosition(oldPos);
        }
        return bestMove;
    }
    
    // Evaluate board using minimax
    private int evaluate (Board chess, int depth) 
    {
        int score,bestScore;
        
        if (depth == maxDepth || chess.endGame())
        {
            return material(chess);            
        }
        
        char colour = depth % 2 == 0 ? oppColour(col) : col;
        ArrayList<Move> moves = getAllMoves(chess,colour);
        
        if (moves.size() == 0) // no possible moves so stalement since game hasn't ended
        {
            return drawValue;
        }
        
        Position oldPos = chess.getPosition();
         
        if (depth % 2 == 0) // minimzing
        {
            bestScore = Integer.MAX_VALUE;
           
            for (int i = 0; i < moves.size(); i++)
            {
                doMove(chess,moves.get(i));
                score = evaluate(chess,depth+1);
                if (score < bestScore)
                {
                    bestScore = score;
                }
                chess.setPosition(oldPos);
            }
            
            return bestScore;
        }
        else // maximizing
        {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < moves.size(); i++)
            {
                doMove(chess,moves.get(i));
                score = evaluate(chess,depth+1);
                if (score > bestScore)
                {
                    bestScore = score;
                }
                chess.setPosition(oldPos);
            }
            
            return bestScore;
        }
    }

}

