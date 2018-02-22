import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

/**
 * main
 * Created by <kuzne4eg@gmail.com> on 11.09.2017
 */
class Skyline extends JFrame {
    JButton b;
    Color nowColor = Color.cyan;
    List<Rectangle> rList = new ArrayList<>();
    List<Poly> pList = new ArrayList<>();
    Coordinates tempPoint = new Coordinates();
    ButtonGroup bg = new ButtonGroup();
    JRadioButton cyanB, yellowB, blueB, greenB, magentaB, orangeB, grayB, darkGreyB, lightGrayB, pinkB, whiteB;
    int comWidth = 70, comHight = 20;
    JCheckBox merge;
    boolean clear = false;
    Rectangle lastFigure;

    /*
    the idea is to differentiate between two drawing modes
    mode one: single rectangles can override each other; merge box is unchecked
    mode two: shapes combine several figures if they have common areas; merge box is checked
    for each mode, there are separate lists to hold the objects
    after leaving mode 2, the rectangle list is emptied since all figures were converted to the shapes
     */

    Skyline() {

        // main window
        setTitle("Skyline");
        setSize(1500, 500); //top-left corner is the origin
        setLocation(50, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); //allows coordinate-based positioning

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                System.out.println("mousePressed " + me.getX() + " / " + me.getY());
                tempPoint.x = me.getX();
                tempPoint.y = me.getY();
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                System.out.println("mouseReleased " + me.getX() + " / " + me.getY());
                int minX = Math.min(tempPoint.x, me.getX()); //to catch right-to-left mouse movement
                lastFigure = new Rectangle( minX, tempPoint.y, Math.max(me.getX(), tempPoint.x) - minX, 460 - tempPoint.y, nowColor);

                if(merge.isSelected()) //shape mode
                    update();
                else //rectangle mode
                    rList.add(lastFigure);

                repaint();
            }
        });

        //check box
        add(merge = new JCheckBox("Merge", true));
        merge.setBounds(1400, 20, comWidth, comHight);

        //if box was checked transform all the new figures to the shape
        merge.addActionListener(e -> {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean mergeON = abstractButton.getModel().isSelected();

                if(mergeON) {
                    System.out.println("mergeON");
                    update();
                    repaint();
                }
        });

        //radio buttons for colors
        add(cyanB = new JRadioButton("cyan", true));
        bg.add(cyanB);
        cyanB.setBounds(1400, 40, comWidth, comHight);
        cyanB.addActionListener(e -> nowColor = Color.cyan);

        add(yellowB = new JRadioButton("yellow", false));
        bg.add(yellowB);
        yellowB.setBounds(1400, 60, comWidth, comHight);
        yellowB.addActionListener(e -> nowColor = Color.yellow);

        //button to clear the pane
        add(b = new JButton("Clear"));
        b.setBounds(1400, 80, comWidth, comHight);
        b.addActionListener(e -> {
            pList.clear();
            rList.clear();
            clear = true;
            repaint();
        });

        setVisible(true);
    }

    //this is a switch to eventually leap over the iterator function if there is only one rectangle
    void update(){
        if(rList.isEmpty())
            toShape(new Poly(lastFigure.x, lastFigure.y, lastFigure.x + lastFigure.w, 460));
        else
            rectangleIterator(lastFigure);
    }

    //assuming there are multiple rectangles - transform them into shapes to allow a comparison later
    void rectangleIterator(Rectangle temp){

        //first, transform existing figures from rectangle list
        for(Rectangle foo : rList)
            toShape(new Poly(foo.x, foo.y, foo.x + foo.w, 460));
        rList.clear();

        //then add the last one on top
        toShape(new Poly(temp.x, temp.y, temp.x + temp.w, 460));
    }

    //merge all overlapping shapes
    void toShape(Poly temp) {
        if (!pList.isEmpty()) {
            for (Poly foo : pList)
                temp = temp.merge(foo);

            // temp replaces the old structure
            pList.clear();
        }
        pList.add(temp);
    }

    @Override
    public void paint(Graphics g) {

        if(clear){
            g.clearRect(0, 0, 1400, 460);
            clear = false;
        }

        g.setColor(Color.black);
        g.drawLine(30, 460, 1470, 460); //x axis
        g.clearRect(0, 0, 1400, 459);

        //draw shapes
        for (Poly foo : pList) {
            int x[] = foo.prepareForPaint();
            int y[] = foo.getYarray();
            g.drawPolyline(x, y, foo.arrSize);
        }

        //draw rectangles
        for (Rectangle foo : rList) {
            g.setColor(foo.color);
            g.fillRect(foo.x, foo.y, foo.w, foo.h);
            g.setColor(Color.BLACK);
            g.drawRect(foo.x, foo.y, foo.w, foo.h);
        }
    }

    public static void main(String[] args) {
        new Skyline();
    }
}

