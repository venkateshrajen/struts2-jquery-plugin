package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Chart;

import com.opensymphony.xwork2.util.ValueStack;

public class ChartTag extends AbstractContainerTag {
	
	private static final long serialVersionUID = -4839207037830558353L;

    protected Object data;				//An object with the chart data
    protected String pie;				//If it should be shown as a pie chart
	protected String stacked;			//If the data values should be stacked
	protected String showLegend;		//If the legend should be displayed
	protected String legendPosition;	//Placement of the legend: must be "ne" or "nw" or "se" or "sw"
	protected Object labels;			//A list of labels for the series. Must be same length as # of series in data
	protected Object showLines;			//A list of booleans, for each series, whether to show lines or not
	protected Object showBars;			//A list of booleans, for each series, whether to show bars or not
	protected Object showPoints;		//A list of booleans, for each series, whether to show points or not
	protected String line;				//If all series should show lines by default;
	protected String bar;				//If all series should show bars by default;
	protected String point;				//If all series should show points by default;
	protected String colors;			//A list or comma-delimited string of colors to use for the chart series; colors can be specified like: #d18b2c, #dba255, #919733
	protected String grid;				//If the grid should be displayed or not;
	protected String gridColor;			//The color of the grid lines (if shown)
	protected String gridBackground;	//The background chart color or gradient (if shown). Colors can be specified like: #d18b2c, Gradients can be specified as: { colors: ["#000", "#999"] } or  { colors: [{ opacity: 0.8 }, { brightness: 0.6, opacity: 0.8 } ] }
	protected String onPlotClickTopic;	//A topic that is published whenever the user clicks on the plot;
	protected String onPlotHoverTopic;	//A topic that is published whenever the user moves over the plot;
	protected String borderWidth;		//the width of the border around the plot. 0 = no border
	protected String tickColor;			//The color of the tick marks;
	protected String seriesClickTopics;	//A list or comma delimited string of topics, one per series, that will be published when the user clicks on a data item in that series;
	protected String seriesHoverTopics;	//A list or comma delimited string of topics, one per series, that will be published when the user hovers over a data item in that series;
	protected String legendColor;		//A color for the background of the legend
	protected String legendOpacity;		//The opacity of the background of the legend
	protected String xAxis1Min;			//The min value of the x-axis 1;
	protected String xAxis1Max;			//The max value of the x-axis 1;
	protected String xAxis2Min;			//The min value of the x-axis 2;
	protected String xAxis2Max;			//The max value of the x-axis 2;
	protected String yAxis1Min;			//The min value of the y-axis 1;
	protected String yAxis1Max;			//The max value of the y-axis 1;
	protected String yAxis2Min;			//The min value of the y-axis 2;
	protected String yAxis2Max;			//The max value of the y-axis 2;
	protected String xAxis1Time;		//A boolean indicating whether the x-axis 1 uses time-series data
	protected String xAxis2Time;		//A boolean indicating whether the x-axis 2 uses time-series data
	protected String yAxis1Time;		//A boolean indicating whether the y-axis 1 uses time-series data
	protected String yAxis2Time;		//A boolean indicating whether the y-axis 2 uses time-series data
	protected String xAxis1Label;		//A boolean indicating whether a Label should be shown next to the x-axis 1
	protected String xAxis2Label;		//A boolean indicating whether a Label should be shown next to the x-axis 2
	protected String yAxis1Label;		//A boolean indicating whether a Label should be shown next to the y-axis 1
	protected String yAxis2Label;		//A boolean indicating whether a Label should be shown next to the y-axis 2
	protected String options;			//A catch-all for the user to specify custom flot options above the ones specific provided for as attributes. options provided here will override those set as attributes.
	
	public ChartTag() {
		super();
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Chart(stack, req, res);
		return bean;
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();

        Chart chart = (Chart) component;
        chart.setData(data);
        chart.setShowBars(showBars);
        chart.setShowLines(showLines);
        chart.setShowPoints(showPoints);
        chart.setShowLegend(showLegend);
        chart.setLabels(labels);
        chart.setLegendPosition(legendPosition);
        chart.setPie(pie);
        chart.setStacked(stacked);
        chart.setLine(line);
        chart.setBar(bar);
        chart.setPoint(point);
        chart.setColors(colors);
        chart.setGrid(grid);
        chart.setGridColor(gridColor);
        chart.setGridBackground(gridBackground);
        chart.setOnPlotClickTopic(onPlotClickTopic);
        chart.setOnPlotHoverTopic(onPlotHoverTopic);
        chart.setBorderWidth(borderWidth);
        chart.setTickColor(tickColor);
        chart.setSeriesClickTopics(seriesClickTopics);
        chart.setSeriesHoverTopics(seriesHoverTopics);
        chart.setLegendColor(legendColor);
        chart.setLegendOpacity(legendOpacity);
        chart.setxAxis1Max(xAxis1Max);
        chart.setxAxis1Min(xAxis1Min);
        chart.setxAxis1Time(xAxis1Time);
        chart.setxAxis1Label(xAxis1Label);
        chart.setxAxis2Max(xAxis2Max);
        chart.setxAxis2Min(xAxis2Min);
        chart.setxAxis2Time(xAxis2Time);
        chart.setxAxis2Label(xAxis2Label);
        chart.setyAxis1Max(yAxis1Max);
        chart.setyAxis1Min(yAxis1Min);
        chart.setyAxis1Time(yAxis1Time);
        chart.setyAxis1Label(yAxis1Label);
        chart.setyAxis2Max(yAxis2Max);
        chart.setyAxis2Min(yAxis2Min);
        chart.setyAxis2Time(yAxis2Time);
        chart.setyAxis2Label(yAxis2Label);
        chart.setOptions(options);
    }

	public void setShowPoints(String showPoints) {
		this.showPoints = showPoints;
	}
	
	public void setShowBars(String showBars) {
		this.showBars = showBars;
	}
	
	public void setShowLines(String showLines) {
		this.showLines = showLines;
	}
	
	public void setShowLegend(String showLegend) {
		this.showLegend = showLegend;
	}
	
	public void setLegendPosition(String legendPosition) {
		this.legendPosition = legendPosition;
	}
	
	public void setLabels(Object labels) {
		this.labels = labels;
	}
	
	public void setStacked(String stacked) {
		this.stacked = stacked;
	}
	
	public void setPie(String pie) {
		this.pie = pie;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public void setPoint(String point) {
		this.point = point;
	}
	
	public void setLine(String line) {
		this.line = line;
	}
	
	public void setBar(String bar) {
		this.bar = bar;
	}
    
    public void setColors(String colors) {
		this.colors = colors;
	}

    public void setGrid(String grid) {
		this.grid = grid;
	}

    public void setGridColor(String gridColor) {
		this.gridColor = gridColor;
	}

    public void setGridBackground(String gridBackground) {
		this.gridBackground = gridBackground;
	}

    public void setOnPlotClickTopic(String onPlotClickTopic) {
		this.onPlotClickTopic = onPlotClickTopic;
	}

     public void setOnPlotHoverTopic(String onPlotHoverTopic) {
		this.onPlotHoverTopic = onPlotHoverTopic;
	}

    public void setBorderWidth(String borderWidth) {
		this.borderWidth = borderWidth;
	}
    
    public void setTickColor(String tickColor) {
		this.tickColor = tickColor;
	}

    public void setSeriesClickTopics(String seriesClickTopics) {
		this.seriesClickTopics = seriesClickTopics;
	}

    public void setSeriesHoverTopics(String seriesHoverTopics) {
		this.seriesHoverTopics = seriesHoverTopics;
	}

	public void setLegendColor(String legendColor) {
		this.legendColor = legendColor;
	}

	public void setLegendOpacity(String legendOpacity) {
		this.legendOpacity = legendOpacity;
	}

	public void setxAxis1Min(String xAxis1Min) {
		this.xAxis1Min = xAxis1Min;
	}

	public void setxAxis1Max(String xAxis1Max) {
		this.xAxis1Max = xAxis1Max;
	}

	public void setxAxis2Min(String xAxis2Min) {
		this.xAxis2Min = xAxis2Min;
	}

	public void setxAxis2Max(String xAxis2Max) {
		this.xAxis2Max = xAxis2Max;
	}

	public void setyAxis1Min(String yAxis1Min) {
		this.yAxis1Min = yAxis1Min;
	}

	public void setyAxis1Max(String yAxis1Max) {
		this.yAxis1Max = yAxis1Max;
	}

	public void setyAxis2Min(String yAxis2Min) {
		this.yAxis2Min = yAxis2Min;
	}

	public void setyAxis2Max(String yAxis2Max) {
		this.yAxis2Max = yAxis2Max;
	}
	
	public void setxAxis1Time(String xAxis1Time) {
		this.xAxis1Time = xAxis1Time;
	}

	public void setxAxis2Time(String xAxis2Time) {
		this.xAxis2Time = xAxis2Time;
	}

	public void setyAxis1Time(String yAxis1Time) {
		this.yAxis1Time = yAxis1Time;
	}

	public void setyAxis2Time(String yAxis2Time) {
		this.yAxis2Time = yAxis2Time;
	}
	
	public void setxAxis1Label(String xAxis1Label) {
		this.xAxis1Label = xAxis1Label;
	}
	
	public void setxAxis2Label(String xAxis2Label) {
		this.xAxis2Label = xAxis2Label;
	}
	
	public void setyAxis1Label(String yAxis1Label) {
		this.yAxis1Label = yAxis1Label;
	}
	
	public void setyAxis2Label(String yAxis2Label) {
		this.yAxis2Label = yAxis2Label;
	}
	
	public void setOptions(String options) {
		this.options = options;
	}
}

