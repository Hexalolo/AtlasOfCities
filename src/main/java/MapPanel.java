import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MapPanel extends JPanel {
    private AtlasEngine atlas;
    private ArrayList<City> listOfDots;
    private int centralPointRelativeX;
    private int centralPointRelativeY;
    private int tempPositionX;
    private int tempPositionY;
    private JButton buttonUp;
    private JButton buttonDown;
    private JButton buttonLeft;
    private JButton buttonRight;
    private JButton zoomIn;
    private JButton zoomOut;

    public MapPanel(AtlasEngine atlas, int centralPointRelativeX, int centralPointRelativeY){
        this.atlas = atlas;
        this.centralPointRelativeX = centralPointRelativeX;
        this.centralPointRelativeY = centralPointRelativeY;

        setPreferredSize(new Dimension(530,590));
        listOfDots = new ArrayList<City>();
        listOfDots = atlas.returnArrayOfDots(centralPointRelativeX, centralPointRelativeY);

        buttonUp = new JButton("Move up");
        buttonUp.addActionListener(new upActionListener());
        add(buttonUp);
        buttonDown = new JButton("Move down");
        buttonDown.addActionListener(new downActionListener());
        add(buttonDown);
        buttonLeft = new JButton("Move left");
        buttonLeft.addActionListener(new leftActionListener());
        add(buttonLeft);
        buttonRight = new JButton("Move right");
        buttonRight.addActionListener(new rightActionListener());
        add(buttonRight);

        zoomIn = new JButton("Zoom in");
        zoomIn.setPreferredSize(new Dimension(180, 30));
        zoomIn.addActionListener(new zoomInActionListener());
        add(zoomIn);
        zoomOut = new JButton("Zoom out");
        zoomOut.setPreferredSize(new Dimension(180, 30));
        zoomOut.addActionListener(new zoomOutActionListener());
        add(zoomOut);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(25, 85, 480, 480);


        for(City actualDot : listOfDots){
            if(actualDot.isCentralPoint()){
                tempPositionX = 265 + Math.round((actualDot.getRelativeX() - centralPointRelativeX)/atlas.getScaleFactor());
                tempPositionY = 325 + Math.round((actualDot.getRelativeY() - centralPointRelativeY)/atlas.getScaleFactor());
                g2d.setColor(Color.RED);
                g2d.fillOval(tempPositionX, tempPositionY, adaptSize(actualDot.getSize()), adaptSize(actualDot.getSize()));
                g2d.setColor(Color.BLACK);
                g2d.drawString(actualDot.getName(), tempPositionX - 10, tempPositionY - 10);
            }
        }

        for(City actualDot : listOfDots){
            if(actualDot.isCentralPoint() == false){
                tempPositionX = 265 + Math.round((actualDot.getRelativeX() - centralPointRelativeX)/atlas.getScaleFactor());
                tempPositionY = 325 + Math.round((actualDot.getRelativeY() - centralPointRelativeY)/atlas.getScaleFactor());
                g2d.setColor(Color.BLUE);
                g2d.fillOval(tempPositionX, tempPositionY, adaptSize(actualDot.getSize()), adaptSize(actualDot.getSize()));
                g2d.setColor(Color.BLACK);
                g2d.drawString(actualDot.getName(), tempPositionX - 10, tempPositionY - 10);
            }
        }
    }

    private int adaptSize(int originalSize){
        if(originalSize == 1) {return 3;}
        if(originalSize == 2) {return 4;}
        if(originalSize >= 3 && originalSize <= 6) {return 5;}
        if(originalSize >= 7 && originalSize <= 11) {return 7;}
        if(originalSize >= 12 && originalSize <= 20) {return 9;}
        if(originalSize >= 21 && originalSize <= 40) {return 12;}
        if(originalSize >= 41 && originalSize <= 80) {return 16;}
        if(originalSize >= 81) {return 22;}
        return 0;
    }

    private class upActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            centralPointRelativeY = centralPointRelativeY - 120*atlas.getScaleFactor();
            listOfDots = atlas.returnArrayOfDots(centralPointRelativeX, centralPointRelativeY);
            repaint();
        }
    }

    private class downActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            centralPointRelativeY = centralPointRelativeY + 120*atlas.getScaleFactor();
            listOfDots = atlas.returnArrayOfDots(centralPointRelativeX, centralPointRelativeY);
            repaint();
        }
    }

    private class leftActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            centralPointRelativeX = centralPointRelativeX - 120*atlas.getScaleFactor();
            listOfDots = atlas.returnArrayOfDots(centralPointRelativeX, centralPointRelativeY);
            repaint();
        }
    }

    private class rightActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            centralPointRelativeX = centralPointRelativeX + 120*atlas.getScaleFactor();
            listOfDots = atlas.returnArrayOfDots(centralPointRelativeX, centralPointRelativeY);
            repaint();
        }
    }

    private class zoomInActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(atlas.getScaleFactor() > 1) {
                atlas.setScaleFactor(atlas.getScaleFactor() - 1);
                listOfDots = atlas.returnArrayOfDots(centralPointRelativeX, centralPointRelativeY);
                repaint();
            }
        }
    }

    private class zoomOutActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            atlas.setScaleFactor(atlas.getScaleFactor() + 1);
            listOfDots = atlas.returnArrayOfDots(centralPointRelativeX, centralPointRelativeY);
            repaint();
        }
    }
}
