package com.okan.imageproccesing.other;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Created by leventyildiz on 22/02/16.
 */


public class BarGraph extends ApplicationFrame {
    public BarGraph(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Category",
                "Score",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        final String zemin = "ZEMIN";
        final String nesne1 = "NESNE1";
        final String nesne2 = "NESNE2";
        final String nesne3 = "NESNE3";

        final String color = "COLOR";

        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        dataset.addValue(10.0, "", zemin);

        dataset.addValue(10.0, "", nesne1);

        dataset.addValue(10.0, "", nesne2);

        dataset.addValue(10.0, "", nesne3);

        return dataset;
    }

    public static void main(String[] args) {
        BarGraph chart = new BarGraph("Color Usage Statistics", "Histogram of 3 object and floor");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}