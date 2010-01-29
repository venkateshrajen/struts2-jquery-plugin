package org.apache.struts2.jquery.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.util.MakeIterator;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="chart", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.ChartTag", 
		description="Render a dynamic chart providing content from list or remote call via AJAX",
		allowDynamicAttributes=true)
public class Chart extends AbstractContainer {

    public static final String TEMPLATE = "chart";
    public static final String TEMPLATE_CLOSE = "chart-close";
    
    protected Object data;				//If the dialog should be draggable or not (true/false)
    protected String pie;				//If it should be shown as a pie chart
	protected String stacked;			//If the data values should be stacked
	protected String showLegend;		//If the legend should be displayed
	protected String legendPosition;	//Placement of the legend: must be "ne" or "nw" or "se" or "sw"
	protected Object labels;			//A list of labels for the series. Must be same length as # of series in data
	protected Object showLines;			//A list or comma-delimited string, of booleans, for each series, whether to show lines or not
	protected Object showBars;			//A list or comma-delimited string, of booleans, for each series, whether to show bars or not
	protected Object showPoints;		//A list or comma-delimited string, of booleans, for each series, whether to show points or not
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
	protected String xAxis1Label;		//A boolean indicating whether a label should be shown next to the x-axis 1
	protected String xAxis2Label;		//A boolean indicating whether a label should be shown next to the x-axis 2
	protected String yAxis1Label;		//A boolean indicating whether a label should be shown next to the y-axis 1
	protected String yAxis2Label;		//A boolean indicating whether a label should be shown next to the y-axis 2
	protected String options;			//A catch-all for the user to specify custom flot options above the ones specific provided for as attributes. options provided here will override those set as attributes.
	
	
    public Chart(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

	@Override
    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();
                        
        addListParameter(showLines,"lines");

        addListParameter(showBars,"bars");

        addListParameter(showPoints,"points");

        addListParameter(labels,"labels");
        
        addListParameter(colors,"colors");

        addListParameter(seriesClickTopics,"seriesClickTopics");

        addListParameter(seriesHoverTopics,"seriesHoverTopics");
            	
        if (showLegend != null){
        	addParameter("showLegend", findValue(showLegend, Boolean.class));
        }       
        if (legendPosition != null){
        	addParameter("legendPosition", findValue(legendPosition, Boolean.class));
        }       
        if (line != null){
        	addParameter("line", findValue(line, Boolean.class));
        }       
        if (bar != null){
        	addParameter("bar", findValue(bar, Boolean.class));
        }       
        if (point != null){
        	addParameter("point", findValue(point, Boolean.class));
        }       
        if (pie != null){
        	addParameter("pie", findValue(pie, Boolean.class));
        }
        if (stacked != null){
        	addParameter("stacked", findValue(stacked, Boolean.class));
        } 
        if (grid != null){
        	addParameter("grid", findValue(grid, Boolean.class));
        }   
        if (gridColor != null){
        	addParameter("gridColor", findString(gridColor));
        }   
        if (gridBackground != null){
        	addParameter("gridBackground", findString(gridBackground));
        }    
        if (onPlotClickTopic != null){
        	addParameter("onPlotClickTopic", findString(onPlotClickTopic));
        }    
        if (onPlotHoverTopic != null){
        	addParameter("onPlotHoverTopic", findString(onPlotHoverTopic));
        }    
        if (borderWidth != null){
        	addParameter("borderWidth", findValue(borderWidth, Integer.class));
        }    
        if (tickColor != null){
        	addParameter("tickColor", findString(tickColor));
        }   
        if (legendColor != null){
        	addParameter("legendColor", findString(legendColor));
        }   
        if (legendOpacity != null){
        	addParameter("legendOpacity", findValue(legendOpacity, Integer.class));
        }     
        if (xAxis1Min != null){
        	addParameter("xAxis1Min", findValue(xAxis1Min, Double.class));
        }      
        if (xAxis1Max != null){
        	addParameter("xAxis1Max", findValue(xAxis1Max, Double.class));
        }          
        if (xAxis2Min != null){
        	addParameter("xAxis2Min", findValue(xAxis2Min, Double.class));
        }          
        if (xAxis2Max != null){
        	addParameter("xAxis2Max", findValue(xAxis2Max, Double.class));
        }          
        if (yAxis1Min != null){
        	addParameter("yAxis1Min", findValue(yAxis1Min, Double.class));
        }          
        if (yAxis1Max != null){
        	addParameter("yAxis1Max", findValue(yAxis1Max, Double.class));
        }          
        if (yAxis2Min != null){
        	addParameter("yAxis2Min", findValue(yAxis2Min, Double.class));
        }          
        if (yAxis2Max != null){
        	addParameter("yAxis2Max", findValue(yAxis2Max, Double.class));
        }   	
        if (xAxis1Time != null){
        	addParameter("xAxis1Time", findValue(xAxis1Time, Boolean.class));
        }    	
        if (xAxis2Time != null){
        	addParameter("xAxis2Time", findValue(xAxis2Time, Boolean.class));
        }    	
        if (yAxis1Time != null){
        	addParameter("yAxis1Time", findValue(yAxis1Time, Boolean.class));
        }    	
        if (yAxis2Time != null){
        	addParameter("yAxis2Time", findValue(yAxis2Time, Boolean.class));
        }        	
        if (xAxis1Label != null){
        	addParameter("xAxis1Label", findString(xAxis1Label));
        }    	
        if (xAxis2Label != null){
        	addParameter("xAxis2Label", findString(xAxis2Label));
        }    	
        if (yAxis1Label != null){
        	addParameter("yAxis1Label", findString(yAxis1Label));
        }    	
        if (yAxis2Label != null){
        	addParameter("yAxis2Label", findString(yAxis2Label));
        }     
        if (options != null){
        	addParameter("options", findString(options));
        }                  
        if (data == null) {
            data = parameters.get("data");
        }
        
        Object value = null;
        
        if (data instanceof String) {
            value = findValue((String) data);
        } else if (data instanceof Collection<?>) {
            value = data;
        } else if (MakeIterator.isIterable(data)) {
            value = MakeIterator.convert(data);
        }
        
        if (value instanceof Collection<?>) {
        	List<?> list = new ArrayList<Object>((Collection<?>)value);
        	if(!list.isEmpty()) {
        		if(list.get(0) instanceof Map<?,?>) {        			
        			if(list.size() == 1) {
        				value = list.get(0);
        	            addParameter("series", 1);
        			} else {
        				value = list;
        	            addParameter("series", list.size());
        			}
        		} else {
        			HashMap<Object, Object> map = new HashMap<Object, Object>(list.size());
        			for (Object item : list) {
						map.put(item, item);
					}
        			value = map;
    	            addParameter("series", 1);
        		}
        	} else {
        		value = null;
        	}
        } else if (value instanceof Map<?,?>) {
            addParameter("series", 1);
            addParameter("seriesSize", ((Map<?, ?>)value).size());
        } else if (value != null && value.getClass().isArray()) {
        	Object[] array  = (Object[])value;
        	HashMap<Object, Object> map = new HashMap<Object, Object>(array.length);
			for (Object item : array) {
				map.put(item, item);
			}
			value = map;
            addParameter("series", 1);
        }
                
        addParameter("data", MakeIterator.convert(value));     
    }
	
	private void addListParameter(Object parameter, String name) {
		
		Object value = null;
		
        if (parameter != null){
        	if(parameter instanceof String && !((String)parameter).contains(",")) {
        		value = findValue(parameter.toString());
        		if(value != null) {
        			parameter = value;
        		}
        	} 
        	
        	if (parameter instanceof Collection<?>) {
        		value = StringUtils.join((Collection<?>)parameter, ",");
        	} else {
        		value = parameter.toString();
        	}
        	
    		addParameter(name, value);
        }
	}

    
    @Override
    public String getDefaultOpenTemplate() {
        return TEMPLATE;
    }

    @Override
    protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
    }

    @StrutsTagAttribute(description="Iterable source to populate chart from. Typically the data will be a collection of series where each series is " +
    		"a Map and the key/value pairs in the map represent the X,Y series data points. If a Map is provided, there will only be a single series." +
    		"If a collection is provided, the data points in the collection willserve as both the X&Y coordiates in a series except for pie charts" +
    		"in which the provide the segment values for the pie. This should not be used together with the 'src' attribute", required=false)
	public void setData(Object data) {

    	this.data = data;
	}

    @StrutsTagAttribute(description="A list of booleans, for each series, if the bars should be shown on the chart", required=false)
    public void setShowBars(Object showBars) {
		this.showBars = showBars;
	}

    @StrutsTagAttribute(description="A list of booleans, for each series, if the lines should be shown on the chart", required=false)
    public void setShowLines(Object showLines) {
		this.showLines = showLines;
	}

    @StrutsTagAttribute(description="A list of booleans, for each series, if the points should be shown on the chart", required=false)
    public void setShowPoints(Object showPoints) {
		this.showPoints = showPoints;
	}

    @StrutsTagAttribute(description="A list of labels for the series. Must be same length as # of series in data", required=false)
    public void setLabels(Object labels) {
		this.labels = labels;
	}

    @StrutsTagAttribute(description="If the legend should be displayed", type="Boolean", defaultValue="false", required=false)
    public void setShowLegend(String showLegend) {
		this.showLegend = showLegend;
	}

    @StrutsTagAttribute(description="Placement of the legend: must be 'ne' or 'nw' or 'se' or 'sw'", required=false)
    public void setLegendPosition(String legendPosition) {
		this.legendPosition = legendPosition;
	}
	
    @StrutsTagAttribute(description="If the chart should be a pie chart",  type="Boolean", defaultValue="false", required=false)
    public void setPie(String pie) {
		this.pie = pie;
	}

    @StrutsTagAttribute(description="If the data values should be stacked",  type="Boolean", defaultValue="false", required=false)
    public void setStacked(String stacked) {
		this.stacked = stacked;
	}

    @StrutsTagAttribute(description="If all series should show points by default",  type="Boolean", defaultValue="false", required=false)
    public void setPoint(String point) {
		this.point = point;
	}

    @StrutsTagAttribute(description="If all series should show lines by default",  type="Boolean", defaultValue="false", required=false)
    public void setLine(String line) {
		this.line = line;
	}

    @StrutsTagAttribute(description="If all series should show bars by default",  type="Boolean", defaultValue="false", required=false)
    public void setBar(String bar) {
		this.bar = bar;
    }
    
    @StrutsTagAttribute(description="A list or comma-delimited string of colors to use for the chart series; colors can be specified like: #d18b2c, #dba255, #919733", required=false)
    public void setColors(String colors) {
		this.colors = colors;
	}

    @StrutsTagAttribute(description="If the grid should be displayed or not",  type="Boolean", defaultValue="true", required=false)
    public void setGrid(String grid) {
		this.grid = grid;
	}

    @StrutsTagAttribute(description="//The color of the grid lines (if shown)", required=false)
    public void setGridColor(String gridColor) {
		this.gridColor = gridColor;
	}

    @StrutsTagAttribute(description="The background chart color or gradient (if shown). Colors can be specified like: #d18b2c, Gradients can be specified as: { colors: ['#000', '#999'] } or  { colors: [{ opacity: 0.8 }, { brightness: 0.6, opacity: 0.8 } ] }", required=false)
    public void setGridBackground(String gridBackground) {
		this.gridBackground = gridBackground;
	}

    @StrutsTagAttribute(description="A topic that is published whenever the user clicks on the plot", required=false)
    public void setOnPlotClickTopic(String onPlotClickTopic) {
		this.onPlotClickTopic = onPlotClickTopic;
	}

    @StrutsTagAttribute(description="A topic that is published whenever the user hovers over the plot", required=false)
    public void setOnPlotHoverTopic(String onPlotHoverTopic) {
		this.onPlotHoverTopic = onPlotHoverTopic;
	}

    @StrutsTagAttribute(description="The width of the border around the plot. 0 = no border", required=false)
    public void setBorderWidth(String borderWidth) {
		this.borderWidth = borderWidth;
	}
    
    @StrutsTagAttribute(description="The color of the plot tick marks", required=false)
    public void setTickColor(String tickColor) {
		this.tickColor = tickColor;
	}

    @StrutsTagAttribute(description="A list or comma delimited string of topics, one per series, that will be published when the user clicks on a data item in that series;", required=false)
    public void setSeriesClickTopics(String seriesClickTopics) {
		this.seriesClickTopics = seriesClickTopics;
	}

    @StrutsTagAttribute(description="A list or comma delimited string of topics, one per series, that will be published when the user hovers over a data item in that series;", required=false)
    public void setSeriesHoverTopics(String seriesHoverTopics) {
		this.seriesHoverTopics = seriesHoverTopics;
	}

	@StrutsTagAttribute(description="A color for the background of the legend", required=false)
    public void setLegendColor(String legendColor) {
		this.legendColor = legendColor;
	}

	@StrutsTagAttribute(description="The opacity of the background of the legend", required=false)
	public void setLegendOpacity(String legendOpacity) {
		this.legendOpacity = legendOpacity;
	}

	@StrutsTagAttribute(description="The min value of the x-axis 1", required=false)
	public void setxAxis1Min(String xAxis1Min) {
		this.xAxis1Min = xAxis1Min;
	}

	@StrutsTagAttribute(description="The max value of the x-axis 1", required=false)
	public void setxAxis1Max(String xAxis1Max) {
		this.xAxis1Max = xAxis1Max;
	}

	@StrutsTagAttribute(description="The min value of the x-axis 2", required=false)
	public void setxAxis2Min(String xAxis2Min) {
		this.xAxis2Min = xAxis2Min;
	}

	@StrutsTagAttribute(description="The max value of the x-axis 2", required=false)
	public void setxAxis2Max(String xAxis2Max) {
		this.xAxis2Max = xAxis2Max;
	}

	@StrutsTagAttribute(description="The min value of the y-axis 1", required=false)
	public void setyAxis1Min(String yAxis1Min) {
		this.yAxis1Min = yAxis1Min;
	}

	@StrutsTagAttribute(description="The max value of the y-axis 1", required=false)
	public void setyAxis1Max(String yAxis1Max) {
		this.yAxis1Max = yAxis1Max;
	}

	@StrutsTagAttribute(description="The min value of the y-axis 2", required=false)
	public void setyAxis2Min(String yAxis2Min) {
		this.yAxis2Min = yAxis2Min;
	}

	@StrutsTagAttribute(description="The max value of the y-axis 2", required=false)
	public void setyAxis2Max(String yAxis2Max) {
		this.yAxis2Max = yAxis2Max;
	}
	
	@StrutsTagAttribute(description="A boolean indicating whether the x-axis 1 uses time-series data",  type="Boolean", defaultValue="false", required=false)
	public void setxAxis1Time(String xAxis1Time) {
		this.xAxis1Time = xAxis1Time;
	}

	@StrutsTagAttribute(description="A boolean indicating whether the x-axis 2 uses time-series data",  type="Boolean", defaultValue="false", required=false)
	public void setxAxis2Time(String xAxis2Time) {
		this.xAxis2Time = xAxis2Time;
	}

	@StrutsTagAttribute(description="A boolean indicating whether the y-axis 1 uses time-series data",  type="Boolean", defaultValue="false", required=false)
	public void setyAxis1Time(String yAxis1Time) {
		this.yAxis1Time = yAxis1Time;
	}

	@StrutsTagAttribute(description="A boolean indicating whether the y-axis 2 uses time-series data",  type="Boolean", defaultValue="false", required=false)
	public void setyAxis2Time(String yAxis2Time) {
		this.yAxis2Time = yAxis2Time;
	}

	@StrutsTagAttribute(description="A label to be shown next to the x-axis 1",  type="Boolean", defaultValue="false", required=false)
	public void setxAxis1Label(String xAxis1Label) {
		this.xAxis1Label = xAxis1Label;
	}
	
	@StrutsTagAttribute(description="A label to be  shown next to the x-axis 2",  type="Boolean", defaultValue="false", required=false)
	public void setxAxis2Label(String xAxis2Label) {
		this.xAxis2Label = xAxis2Label;
	}
	
	@StrutsTagAttribute(description="A label to be shown next to the y-axis 1",  type="Boolean", defaultValue="false", required=false)
	public void setyAxis1Label(String yAxis1Label) {
		this.yAxis1Label = yAxis1Label;
	}
	
	@StrutsTagAttribute(description="A label to be shown next to the y-axis 2",  type="Boolean", defaultValue="false", required=false)
	public void setyAxis2Label(String yAxis2Label) {
		this.yAxis2Label = yAxis2Label;
	}
		
	@StrutsTagAttribute(description="A catch-all for the user to specify custom flot options above the ones specific provided for as attributes. options provided here will override those set as attributes.", required=false)
	public void setOptions(String options) {
		this.options = options;
	}
}
