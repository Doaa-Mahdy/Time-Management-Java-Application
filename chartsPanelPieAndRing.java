package test;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CenterTextMode;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public class chartsPanelPieAndRing extends JPanel {
    chartsPanelPieAndRing() throws InterruptedException {
        // setBackground(new Color(0xA907E0));
        setBounds(270,140,740,340);
        setLayout(new GridLayout(1,2, 50,20));
        tasksStorage chartsReader= new tasksStorage();//read data from file and insert it in a file
        //creating costume font
        File fontFile = new File("pixel_font.ttf");
        Font costumeFont=null;
        try {
            costumeFont= Font.createFont(Font.TRUETYPE_FONT,fontFile);//adding a costume font
            costumeFont= costumeFont.deriveFont(Font.BOLD,15f);

        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //TODO:pie chart for finished-unfinished and overdue tasks

        DefaultPieDataset pieChartDataSet= new DefaultPieDataset();
        //setting values for the chart
        pieChartDataSet.setValue("Unfinished Tasks",0);
        pieChartDataSet.setValue("Finished Tasks",0);
        pieChartDataSet.setValue("Overdue Tasks",0);


        //creating the plot for pie chart
        JFreeChart plotPieChart= ChartFactory.createPieChart("Overall Tasks",pieChartDataSet);
        PiePlot pie=(PiePlot) plotPieChart.getPlot();
        plotPieChart.getTitle().setFont(costumeFont);
        TextTitle title= new TextTitle("Overall Tasks");
        title.setFont(costumeFont);
        plotPieChart.setTitle(title);
        //setting colors for each section
        // plotPieChart.setBackgroundPaint(new Color(0xA907E0));

        //set each sections color
        pie.setSectionPaint("Unfinished Tasks",new Color(46, 100, 207));
        pie.setSectionPaint("Finished Tasks",new Color(40, 172, 46));
        pie.setSectionPaint("Overdue Tasks",new Color(236, 57, 57));

        //Creating the panel

        ChartPanel pieChartPanel= new ChartPanel(plotPieChart);
        pieChartPanel.setSize(200,200);
        // pieChartPanel.setBackground(new Color(0xA907E0));
        pie.setBackgroundPaint(new Color(0xC1B7C5));
        pieChartPanel.setOpaque(false);
        pieChartPanel.setBackground(new Color(0, 0, 0, 0));
        pie.setShadowPaint(null);

        plotPieChart.setBackgroundPaint(null);
        plotPieChart.setBorderVisible(false);
        pie.setBackgroundPaint(null);
        pie.setOutlineVisible(false);
        pie.setLabelGenerator(null);



        //TODO:second the ring chart(finished tasks-unfinished tasks)
        //creating dataset and adding its values
        DefaultPieDataset ringDataSet=new DefaultPieDataset();
        ringDataSet.setValue("Finished Tasks",0);
        ringDataSet.setValue("Unfinished Tasks",0);



        //creating the chart
        JFreeChart ringChart = ChartFactory.createRingChart("Finished/Unfinished Tasks",ringDataSet,true,true,false);
        RingPlot rp= (RingPlot) ringChart.getPlot();
        TextTitle title_ring= new TextTitle("Finished/Unfinished Tasks");
        title_ring.setFont(costumeFont);
        ringChart.setTitle(title_ring);
        rp.setCenterTextMode(CenterTextMode.FIXED);//to make text in the middle
        rp.setCenterText(0+"%");//text in the middle

        rp.setLabelGenerator(null);

        rp.setCenterTextFont(costumeFont);//middle text font
        rp.setBackgroundPaint(new Color(0xC1B7C5) );
        //colors of charts
        rp.setSectionPaint("Finished Tasks",new Color(40, 172, 46));
        rp.setSectionPaint("Unfinished Tasks",new Color(236, 57, 57));

        ringChart.setBackgroundPaint(null);
        ringChart.setBorderVisible(false);
        rp.setBackgroundPaint(null);
        rp.setOutlineVisible(false);



        //creating panel
        ChartPanel ringPanel= new ChartPanel(ringChart);
        ringPanel.setSize(200,200);
        ringPanel.setOpaque(false);


        setOpaque(false);
        add(pieChartPanel);
        add(ringPanel);
        setVisible(true);
        updateThePie(pieChartDataSet,plotPieChart,rp, Arrays.stream(chartsReader.readTaskNumber()[1]).sum(),Arrays.stream(chartsReader.readTaskNumber()[0]).sum(),Arrays.stream(chartsReader.readTaskNumber()[2]).sum());
        updateTheRing(ringDataSet,ringChart,Arrays.stream(chartsReader.readTaskNumber()[0]).sum(),Arrays.stream(chartsReader.readTaskNumber()[1]).sum()+Arrays.stream(chartsReader.readTaskNumber()[2]).sum());
    }
    public void updateThePie( DefaultPieDataset pieChartDataSet,JFreeChart plotPieChart,RingPlot rp,int unfinished,int finished,int overdue) throws InterruptedException {


        plotPieChart.fireChartChanged();
        final int ctr[]={unfinished,finished,overdue};
        final int ctrIntial[]={0,0,0};
        Timer timer= new Timer(50,(e)->
        {
            if(ctrIntial[0] !=ctr[0] )
            {

                pieChartDataSet.setValue("Unfinished Tasks",++ctrIntial[0]);
                plotPieChart.fireChartChanged();
            } else if (ctrIntial[1] !=ctr[1]) {

                pieChartDataSet.setValue("Finished Tasks",++ctrIntial[1]);
                plotPieChart.fireChartChanged();
            }else if (ctrIntial[2] !=ctr[2]) {

                pieChartDataSet.setValue("Overdue Tasks", ++ctrIntial[2]);
                plotPieChart.fireChartChanged();
            }else
                ((Timer)e.getSource()).stop();


        });
        timer.start();

    }

    public void updateTheRing( DefaultPieDataset pieChartDataSet,JFreeChart rp,int finished,int unfinished)
    {
        final int[] ctr={finished,unfinished};
        final int[] ctrInit={1,1};
        final RingPlot ringPlot = (RingPlot) rp.getPlot();
        Timer t = new Timer(50,(e)->{
            if(ctrInit[0] !=ctr[0] )
            {

                pieChartDataSet.setValue("Finished Tasks", ++ctrInit[0]);
                double percentage = (ctrInit[0] * 100.0) / (ctrInit[0] + ctrInit[1]);
                ringPlot.setCenterText(String.format("%.2f%%", percentage));
                rp.fireChartChanged();

            } else if(ctrInit[1] !=ctr[1] )
            {
                pieChartDataSet.setValue("Unfinished Tasks", ++ctrInit[1]);
                double percentage = (ctrInit[0] * 100.0) / (ctrInit[0] + ctrInit[1]);
                ringPlot.setCenterText(String.format("%.2f%%", percentage));
                rp.fireChartChanged();


            }else
                ((Timer)e.getSource()).stop();


        });

        t.start();


    }


}
