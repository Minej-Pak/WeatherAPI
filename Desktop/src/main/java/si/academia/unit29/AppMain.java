package si.academia.unit29;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class GraphPanel extends JPanel {

    private  Float[] temps;

    public GraphPanel() { temps=null; }

    private void doDrawing(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        setBackground(Color.WHITE);
        int xres=getWidth();
        int yres=getHeight();
        g2.setColor(Color.BLACK);
        int xstart=30; int ystart=yres-30;
        int xend =xres-20; int yend =20;
        int xsize = xend-xstart;
        int ysize = ystart-yend;
        int dx100=(100*xsize)/24;
        int dy100=(100*ysize)/60;

        g2.drawLine(xstart, ystart, xstart, yend);
        g2.drawLine(xstart, ystart, xend, ystart);

        for (int i=0; i<24; i++) {
            int x=xstart+(dx100*i)/100+dx100/200;
            g2.drawLine(x, ystart, x, ystart+4);
            String hrStr = Integer.toString(i+1);
            Rectangle2D rect = g2.getFontMetrics().getStringBounds(hrStr, g2);
            g2.drawString(hrStr, (float)(x-rect.getCenterX()), (float)(ystart+4+rect.getHeight()));
        }
        for (int i =4; i<=60; i+=4) {
            int y = ystart-(dy100*i)/100;
            g2.drawLine(xstart-4, y, xstart, y);
            String tmpStr = Integer.toString(-20+i);
            Rectangle2D rect = g2.getFontMetrics().getStringBounds(tmpStr, g2);
            g2.drawString(tmpStr, (float)(24-rect.getWidth()), (float)(y-rect.getCenterY()));
        }
        if (temps !=null) {
            int yzero = ystart-(dy100*20)/100;
            g2.setColor(Color.BLACK);
            g2.drawLine(xstart+1, yzero, xend, yzero);
            for(int i = 0; i<temps.length&&i<24; i++) {
                float temp = temps[i];
                if (temp>40.0f) {temp= 40.0f; }
                if (temp<-20.0f) {temp= -20.0f; }
                int x = xstart+(dx100*i)/100;
                if(temp>0) {
                    int height = (int)((dy100*temp)/100-1);
                    g2.setColor(Color.RED);
                    g2.fillRect(x+5, yzero-height-1, dx100/100-10, height);
                }
                else if (temp<0) {
                    int height = (int)((dy100*(-temp))/100);
                    g2.setColor(Color.PINK);
                    g2.fillRect(x+5, yzero+1, dx100/100-10, height);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void setTemps(Float[] temps) {
        this.temps = temps;
        repaint();
    }
}

public class AppMain {

    private File selFile;
    private JTextField txtFile;
    private JButton butDraw;
    private GraphPanel panGraph;

    public AppMain() { selFile=null; }

    private void run () {
        JFrame mainWin = new JFrame("Unit29 - Vaja 1");
        mainWin.setSize(800, 600);
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel bottomPanel = new JPanel();
        JLabel lblFile = new JLabel("Datoteke");
        txtFile = new JTextField(44);
        txtFile.setEditable(false);
        final JButton butOpen = new JButton("Izberi Datoteko");
        butDraw = new JButton("Nariši Graf");
        butDraw.setEnabled(false);
        panGraph = new GraphPanel();
        bottomPanel.add(lblFile); bottomPanel.add(txtFile);
        bottomPanel.add(butOpen); bottomPanel.add(butDraw);
        mainWin.getContentPane().add(BorderLayout.CENTER, panGraph);
        mainWin.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        butOpen.addActionListener(e -> {
            JFileChooser fch= new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int retVal = fch.showOpenDialog(null);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                selFile = fch.getSelectedFile();
                txtFile.setText(selFile.getName());
                butDraw.setEnabled(true);
            }
        });

        butDraw.addActionListener(e -> {
            java.util.List<Float> lstTemps = new ArrayList<>();
            try {
                Scanner reader = new Scanner(selFile);
                while (reader.hasNextLine()) {
                    lstTemps.add(Float.parseFloat(reader.nextLine()));
                }
                reader.close();
                panGraph.setTemps(lstTemps.toArray(new Float[0]));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        mainWin.setVisible(true);
    }

    public static void main(String[] args) {
        AppMain app = new AppMain();
        app.run();
    }
}