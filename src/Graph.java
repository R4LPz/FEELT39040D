import sun.java2d.loops.DrawLine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graph extends JPanel {
    public Tree tree;


    public Graph(Tree tree){
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(!tree.isEmpty()){
            DrawTree(g, 0, getWidth(), 0, 0, tree.getRoot());
        }
    }

    public void DrawTree(Graphics g, int StartWidth, int EndWidth, int StartHeight, int level, Node node) {

        String data = String.valueOf(node.getValue());


        int fontHeight = g.getFontMetrics().getHeight();
        int fontWidth = g.getFontMetrics().stringWidth(data);
        int coordX = (StartWidth+EndWidth)/2 ;
        int coordY = StartHeight+level+fontHeight;
        int newLevel = (getHeight()/ tree.getHeight() ) - (fontHeight * tree.getHeight());

        g.drawString(data,coordX, coordY);
        g.drawRect(coordX-5,coordY-fontHeight-5,fontWidth+10,fontHeight+10);


        if (node.getLeftNode() != null) {
            g.drawLine(
                    (coordX+fontWidth/2),
                    (coordY+fontHeight/2),
                    ((StartWidth + coordX/2)+fontWidth) ,
                    (coordY+newLevel)
            );
            DrawTree(g, StartWidth, coordX, coordY, newLevel, node.getLeftNode());
        }
        if (node.getRightNode() != null){
            g.drawLine(
                    (coordX+fontWidth/2),
                    (coordY+fontHeight/2),
                    ((coordX + (EndWidth - coordX)/2)-fontWidth/2) ,
                    (coordY+newLevel)
            );
            DrawTree(g, coordX, EndWidth, coordY,newLevel, node.getRightNode());
        }
    }
}
