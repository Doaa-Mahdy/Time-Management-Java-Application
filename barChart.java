package test;

import javax.swing.*;
import javax.xml.datatype.Duration;

import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.*;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class barChart extends JPanel
{
    tasksStorage reader= new tasksStorage();//object that will retrieve number of tasks
    final String[] months={"Jan","Feb","Mar","April","May","June","July","Aug","Sep","Oct","Nov","Dec"};

    //overdue tasks
    barChart() throws InterruptedException {

        setBounds(230,480,760,300);

        setLayout(new GridLayout(1,1));

        //creating costume font
        File fontFile = new File("pixel_font.ttf");
        Font costumeFont= null;
        Font barAxesFont= null;
        try {
            costumeFont= Font.createFont(Font.TRUETYPE_FONT,fontFile);//adding a costume font
            costumeFont= costumeFont.deriveFont(Font.BOLD,30f);

        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //creating data set
        DefaultCategoryDataset barDataSet = new DefaultCategoryDataset();

        barDataSet.addValue(0,"Overdue",months[0]);
        barDataSet.addValue(0,"Overdue",months[1]);
        barDataSet.addValue(0,"Overdue",months[2]);
        barDataSet.addValue(0,"Overdue",months[3]);
        barDataSet.addValue(0,"Overdue",months[4]);
        barDataSet.addValue(0,"Overdue",months[5]);
        barDataSet.addValue(0,"Overdue",months[6]);
        barDataSet.addValue(0,"Overdue",months[7]);
        barDataSet.addValue(0,"Overdue",months[8]);
        barDataSet.addValue(0,"Overdue",months[9]);
        barDataSet.addValue(0,"Overdue",months[10]);
        barDataSet.addValue(0,"Overdue",months[11]);
//unfinished tasks
        barDataSet.addValue(0,"TasksInProcess",months[0]);
        barDataSet.addValue(0,"TasksInProcess",months[1]);
        barDataSet.addValue(0,"TasksInProcess",months[2]);
        barDataSet.addValue(0,"TasksInProcess",months[3]);
        barDataSet.addValue(0,"TasksInProcess",months[4]);
        barDataSet.addValue(0,"TasksInProcess",months[5]);
        barDataSet.addValue(0,"TasksInProcess",months[6]);
        barDataSet.addValue(0,"TasksInProcess",months[7]);
        barDataSet.addValue(0,"TasksInProcess",months[8]);
        barDataSet.addValue(0,"TasksInProcess",months[9]);
        barDataSet.addValue(0,"TasksInProcess",months[10]);
        barDataSet.addValue(0,"TasksInProcess",months[11]);

//finished tasks
        barDataSet.addValue(0,"Finished",months[0]);
        barDataSet.addValue(0,"Finished",months[1]);
        barDataSet.addValue(0,"Finished",months[2]);
        barDataSet.addValue(0,"Finished",months[3]);
        barDataSet.addValue(0,"Finished",months[4]);
        barDataSet.addValue(0,"Finished",months[5]);
        barDataSet.addValue(0,"Finished",months[6]);
        barDataSet.addValue(0,"Finished",months[7]);
        barDataSet.addValue(0,"Finished",months[8]);
        barDataSet.addValue(0,"Finished",months[9]);
        barDataSet.addValue(0,"Finished",months[10]);
        barDataSet.addValue(0,"Finished",months[11]);

        JFreeChart bar = ChartFactory.createBarChart("Progress","Number of tasks","Time per month",  barDataSet);
        bar.setBackgroundPaint(new Color(0xC1B7C5));


        ChartPanel barPanel= new ChartPanel(bar);
        barPanel.setSize(600,600);
        CategoryPlot barPlot= (CategoryPlot) bar.getPlot();
        //changing font of title
        TextTitle barTitle= new TextTitle("Progress");
        barTitle.setFont(costumeFont);
        bar.setTitle(barTitle);
        bar.setBackgroundPaint(null);
        barPanel.setOpaque(false);


        setOpaque(false);
        add(barPanel);
        setVisible(true);
        updateBarChart(barDataSet,barPanel,reader.readTaskNumber()[0], reader.readTaskNumber()[1],reader.readTaskNumber()[2]);
    }

    void updateBarChart( DefaultCategoryDataset barDataSet, ChartPanel barPanel,int[] finished,int[] unfinished,int[] overdue) throws InterruptedException {
        final int[] ctrFinished={0,0,0,0,0,0,0,0,0,0,0,0};
        final int[] ctrUnfinished={0,0,0,0,0,0,0,0,0,0,0,0};
        final int[] ctrOverdue={0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i =0;i<12;i++) {
            final int iterator=i;
            Timer t = new Timer(5, (e) -> {
                if(ctrFinished[iterator] !=finished[iterator] )
                {
                    barDataSet.setValue(++ctrFinished[iterator],"Finished",months[iterator]);

                }
                if(ctrUnfinished[iterator] !=unfinished[iterator] )
                {
                    barDataSet.setValue(++ctrUnfinished[iterator],"TasksInProcess",months[iterator]);

                }
                if(ctrOverdue[iterator] !=overdue[iterator] )
                {
                    barDataSet.setValue(++ctrOverdue[iterator],"Overdue",months[iterator]);

                }
                barPanel.repaint();
                if(ctrOverdue[iterator] ==overdue[iterator] &&ctrUnfinished[iterator] ==unfinished[iterator]&&ctrFinished[iterator] ==finished[iterator])
                    ((Timer)e.getSource()).stop();
            });
            t.start();

        }

    }

}
