import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window {
    private JFrame frame;
    private JPanel contentPane;
    private JButton button;
    private JFrame mapFrame;
    private MapPanel mapPanel;
    private AtlasEngine atlas;
    private String buttonActionCommand;


    public Window(AtlasEngine atlas){
        this.atlas = atlas;
        frame = new JFrame("Atlas of Cities");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(450,480));
        contentPane.setLayout(new FlowLayout());
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public void generateCentralPointButtons(ArrayList<String> listOfCentralPoints){
        String name;
        for(int i = 0; i < listOfCentralPoints.size(); i++){
            name = listOfCentralPoints.get(i);
            button = new JButton(name);
            button.addActionListener(new buttonListener());
            button.setActionCommand(name);
            contentPane.add(button);
        }
        frame.pack();

    }

    private class buttonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            button = (JButton) e.getSource();
            buttonActionCommand = button.getActionCommand();
            generateMapWindow();
        }
    }

    public void generateMapWindow(){

        mapFrame = new JFrame("Map of Cities");
        mapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        City centralCity = new City("","","",0,false);
        for(int i = 0; i < atlas.getListOfCities().size(); i++){
            if(buttonActionCommand.equals(atlas.getListOfCities().get(i).getName())){
                centralCity = atlas.getListOfCities().get(i);
            }
        }
        mapPanel = new MapPanel(atlas, centralCity.getRelativeX(), centralCity.getRelativeY());
        mapFrame.getContentPane().add(mapPanel);
        mapFrame.pack();
        mapFrame.setVisible(true);

    }

}
