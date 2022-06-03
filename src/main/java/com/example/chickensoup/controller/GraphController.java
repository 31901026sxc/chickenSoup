package com.example.chickensoup.controller;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.chickensoup.entity.AnswerSheetEntity;
import com.example.chickensoup.repository.AnswerSheetRepository;
import com.example.chickensoup.repository.UserRepository;
import com.example.chickensoup.service.AnswerService;
import com.example.chickensoup.service.Impl.AnswerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/graph")
public class GraphController {
    @Autowired
    private AnswerServiceImpl answerService;

    @GetMapping("/makeLineAndShapeChart")
    public String makeLineAndShapeChart(@RequestParam Integer studentId, HttpServletRequest request, Model model) throws IOException {
        // 定义图表对象数据，数据
        DefaultCategoryDataset dataset = answerService.createDataset(studentId);
        JFreeChart chart = ChartFactory.createLineChart(
                "学生分数折线图", // chart title
                "考试", // domain axis label
                "分数", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
        );

        CategoryPlot plot = chart.getCategoryPlot();
        // 设置图示字体
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 22));
        //设置横轴的字体
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));//x轴标题字体
        categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 16));//x轴刻度字体

        //以下两行 设置图例的字体
        LegendTitle legend = chart.getLegend(0);
        legend.setItemFont(new Font("宋体", Font.BOLD, 14));
        //设置竖轴的字体
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("宋体", Font.BOLD, 19)); //设置数轴的字体
        rangeAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 16));
        LineAndShapeRenderer lasp = (LineAndShapeRenderer) plot.getRenderer();
        lasp.setBaseShapesVisible(true);
        lasp.setDrawOutlines(true);
        // 设置线条是否被显示填充颜色
        lasp.setUseFillPaint(false);
        // 设置拐点颜色
        lasp.setBaseFillPaint(Color.red);//蓝色
        rangeAxis.setAxisLinePaint(Color.black);
        // 设置背景颜色
        plot.setBackgroundPaint(Color.white);
        // 设置网格竖线颜色
        plot.setDomainGridlinePaint(Color.pink);
        // 设置网格横线颜色
        plot.setRangeGridlinePaint(Color.pink);

        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//去掉竖轴字体显示不全
        rangeAxis.setAutoRangeIncludesZero(true);
        rangeAxis.setUpperMargin(0.20);
        rangeAxis.setLabelAngle(Math.PI / 2.0);

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
                .getRenderer();
        renderer.setSeriesPaint(0, Color.BLACK);//折线颜色，下标0始
        // 6. 将图形转换为图片，传到前台
        String fileName = ServletUtilities.saveChartAsJPEG(chart, 1000, 400, null, request.getSession());
        String chartURL = request.getContextPath() + "/chart?filename=" + fileName;
        model.addAttribute("makeLineAndShapeChart", chartURL);
        System.out.println(chartURL);

        return chartURL;
    }

}