package org.apache.struts2.jquery.components;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.util.MakeIterator;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="grid", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.GridTag", 
		description="Render a dynamic grid providing content from list or remote call via AJAX",
		allowDynamicAttributes=true)
public class Grid extends AbstractContainer {

    public static final String TEMPLATE = "grid";
    public static final String TEMPLATE_CLOSE = "grid-close";
    
    protected Object data;					//An object with the grid data
    protected String options;				//A catch-all for the user to specify custom jQGrid options above the ones specific provided for as attributes. options provided here will override those set as attributes.
	protected Object columnNames;			//A list of column names. The list should be the same length as the number of columns in the data
	protected Object columnWidths;			//A List or map of column widths. If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected Object columnSortables;		//A list or map of booleans indicating whether a column is sortable or not. If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected Object columnEditables;		//A list or map of booleans indicating whether a column is editable or not. If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected Object columnResizables;		//A list or map of booleans indicating whether a column is resizable or not. If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected Object columnAlignments;		//A list or map of Strings indicating the alignment of a column. Valid string value are 'right','left','center', or null. If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected Object columnSortTypes;		//A list or map of sort types for each column. The sort types are int/integer/float/number/currency/date/text (defaults to 'text'). If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected Object columnLabels;			//A list or map of labels for the columns. If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected Object columnDateFormats;		//A list or map of the date formats for each column. The sort types are int/integer/float/number/currency/date/text (defaults to 'text'). If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected String sortColumn;			//The name or index of the column to be sorted first
	protected String sortAscending;			//A boolean indicating whether to sort the initial column in ascending order (true=ascending, false=descending). Only used if sortColumn is set.
	protected String pager;					//A boolean indicating whether the 'pager' control which provides paging navigation should be shown
	protected String rowsPerPage;			//An integer with the number of rows that should be displayed on a page			
	protected Object rowsPerPageOptions;	//A list or comma delimited string of integers of the choices for rows-per-page'
	protected String showTotal;				//A boolean indicating whether or not to display the number of total records from the query in the pager bar.
	protected String caption;				//The caption for the grid  
	protected String height;				//The height of the grid. Can be an integer value (pixels) or a percentage string (like '40%');
	protected String width;					//The width of the grid. Can be an integer value (pixels) or a percentage string (like '40%');
		  
	protected List<Column> columns;
	
    public Grid(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

	@Override
    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();
               
        if (options != null){
        	addParameter("options", findString(options));
        }      
        if (pager != null){
        	addParameter("pager", findValue(pager, Boolean.class));
        }    
        if (sortAscending != null){
        	addParameter("sortAscending", findValue(sortAscending, Boolean.class));
        }      
        if (sortColumn != null){
        	addParameter("sortColumn", findString(sortColumn));
        }    
        if (rowsPerPage != null){
        	addParameter("rowsPerPage",  findValue(rowsPerPage, Integer.class));
        }    
        if (height != null){
        	addParameter("height",  findString(height));
        }   
        if (width != null){
        	addParameter("width",  findString(width));
        } 
        
        addListParameter(rowsPerPageOptions,"rowsPerPageOptions");
              
        if (showTotal != null){
        	addParameter("showTotal", findValue(showTotal, Boolean.class));
        }      
        if (caption != null){
        	addParameter("caption", findString(caption));
        }      
        
        if (data == null) {
            data = parameters.get("data");
        }
        
        Object value = null;
                
        if (data instanceof String) {
            value = findValue((String) data);
        } else if (data instanceof Collection<?>) {
            value = data;
        }
        
        Integer columnCount = null;
        Integer dataLength = null;
        
        if (value instanceof Collection<?>) {
        	List<?> grid = new ArrayList<Object>((Collection<?>)value);    
        	dataLength = grid.size();
        	if(!grid.isEmpty()) {
        		Object row = grid.get(0);
        		if(row instanceof Collection<?>) {   
        			columnCount = ((Collection<?>)row).size(); 			
        			value = grid;
        		} else if(row.getClass().isArray()) {
        			columnCount = ((Object[])row).length; 	
        			//TODO: convert row arrays to collections
        		} else {
            		value = null;
        		}
        	} else {
        		value = null;
    		}
        } else if (value != null && value.getClass().isArray()) {
        	Object[] array  = (Object[])value;
        	dataLength = array.length;
        	if(array.length > 0 && array[0].getClass().isArray()) {
        		
        		ArrayList<ArrayList<Object>> grid = new ArrayList<ArrayList<Object>>(Array.getLength(value));
        		ArrayList<Object> row;
            	Object rowObject = array[0];
            	
            	if(rowObject.getClass().isArray()) {  
            		columnCount = ((Object[])rowObject).length; 	
	                for (int i = 0; i < array.length; i++) {
	                	Object[] rowArray = (Object[])array[i];
	                	row = new ArrayList<Object>();
	                	for(int j = 0; j < rowArray.length; ++j) {
	                		row.add(rowArray[j]);
	                	}
	                	grid.add(row);
	                }	
	                
        		} else if(rowObject instanceof Collection<?>){
        			columnCount = ((Collection<?>)rowObject).size(); 
        			for (int i = 0; i < array.length; i++) {
 	                	grid.add(new ArrayList<Object>((Collection<?>)array[i]));
 	                }
        		}            	
                value = grid;
        	} else {
        		value = null;
        	}
        } else {
    		value = null;
        	dataLength = 0;
    	}
                
        addParameter("data", MakeIterator.convert(value));  
        addParameter("dataLength", dataLength);  
        
        columns = new ArrayList<Column>();
     
        List<?> columnNameList = makeList(columnNames);
    	Map<Integer,?> columnWidthsMap = makeMap(columnWidths, columnNameList);
    	Map<Integer,?> columnSortablesMap = makeMap(columnSortables, columnNameList);
    	Map<Integer,?> columnSortTypesMap = makeMap(columnSortTypes, columnNameList);
    	Map<Integer,?> columnEditablesMap = makeMap(columnEditables, columnNameList);
    	Map<Integer,?> columnResizablesMap = makeMap(columnResizables, columnNameList);
    	Map<Integer,?> columnAlignmentsMap = makeMap(columnAlignments, columnNameList);
    	Map<Integer,?> columnDateFormatsMap = makeMap(columnDateFormats, columnNameList);
    	Map<Integer,?> columnLabelsMap = makeMap(columnLabels, columnNameList);
    	
    	columnCount = columnCount != null ? columnCount : (columnNameList != null ? columnNameList.size() : null);
    		
        if(columnCount != null) {
			
    		Column column;
        	
        	for (int i = 0; i < columnCount; i++) {
				
        		column = new Column();
        		column.name = columnNameList.size() > i ? columnNameList.get(i).toString() : null;
        		column.width = columnWidthsMap.size() > i ? Integer.valueOf(columnWidthsMap.get(i).toString()) : null;
        		column.sortable = columnSortablesMap.size() > i ? Boolean.valueOf(columnSortablesMap.get(i).toString()) : null;
        		column.editable = columnEditablesMap.size() > i ? Boolean.valueOf(columnEditablesMap.get(i).toString()) : null;
        		column.resizable = columnResizablesMap.size() > i ? Boolean.valueOf(columnResizablesMap.get(i).toString()) : null;
        		column.alignment = columnAlignmentsMap.size() > i ? columnAlignmentsMap.get(i).toString() : null;
        		column.alignment = columnAlignmentsMap.size() > i ? columnAlignmentsMap.get(i).toString() : null;
        		column.label = columnLabelsMap.size() > i ? columnLabelsMap.get(i).toString() : null;
        		column.sortType = columnSortTypesMap.size() > i ? columnSortTypesMap.get(i).toString() : null;
        		column.dateFormat = columnDateFormatsMap.size() > i ? columnDateFormatsMap.get(i).toString() : null;

        		columns.add(column);
			}
        }
        
        addParameter("columns", columns);  
    }
	
	@SuppressWarnings("unchecked")
	private List<?> makeList(Object parameter) {
		
		Object value = null;
		
        if (parameter != null){
        	if(parameter instanceof String) {
        		if(((String)parameter).contains(",")) {
        			value = ((String)parameter).split(",");
        		} else {
            		value = findValue(parameter.toString());
        		}
        		if(value != null) {
        			parameter = value;
        		}else {
        			parameter = new String[]{(String)parameter};
        		}
        	} 
        	
        	if (parameter instanceof Collection<?>) {
        		value = new ArrayList<Object>((Collection<?>)parameter);
        	} else if(parameter.getClass().isArray()) {
        		Object[] array = (Object[])parameter;
        		value = new ArrayList<Object>(array.length);
        		for (int i = 0; i < array.length; i++) {
        			((ArrayList<Object>)value).add(array[i]);
				}        		
        	} else if(parameter instanceof Iterable<?>) {
        		Iterable<?> iterable = (Iterable<?>)parameter;
        		value = new ArrayList<Object>();
        		for (Object object : iterable) {
        			((ArrayList<Object>)value).add(object);					
				}
        		value = parameter.toString();
        	}  else if(parameter instanceof Iterator<?>) {
        		value = new ArrayList<Object>();
        		for (Iterator<?> iterator = (Iterator<?>)parameter; iterator.hasNext();) {
        			((ArrayList<Object>)value).add(iterator.next());					
				}
        	} 
        }
    	
		return value == null ? new ArrayList<Object>() : (List<?>)value;
	}
	
	@SuppressWarnings("unchecked")
	private Map<Integer,?> makeMap(Object parameter, List<?> keyNames) {
		
		Object value = null;
		
        if (parameter != null){
        	if(parameter instanceof String) {
        		if(((String)parameter).contains(",")) {
        			value = ((String)parameter).split(",");
        		} else {
            		value = findValue(parameter.toString());
        		}
        		if(value != null) {
        			parameter = value;
        		} else {
        			parameter = new String[]{(String)parameter};
        		}
        	}  
        	
        	if (parameter instanceof Collection<?>) {
        		value = new HashMap<Integer,Object>();
        		int i = 0;
        		for (Object object : (Collection<?>)parameter) {
					((HashMap<Integer,Object>)value).put(i, object);
					i++;
				}
        	} else if(parameter.getClass().isArray()) {
        		Object[] array = (Object[])parameter;
        		value = new HashMap<Integer,Object>();
        		for (int i = 0; i < array.length; i++) {
					((HashMap<Integer,Object>)value).put(i, array[i]);
				}        		
        	} else if(parameter instanceof Iterable<?>) {
        		Iterable<?> iterable = (Iterable<?>)parameter;
        		value = new HashMap<Integer,Object>();
        		int i = 0;
        		for (Object object : iterable) {
					((HashMap<Integer,Object>)value).put(i, object);
					i++;				
				}
        		value = parameter.toString();
        	}  else if(parameter instanceof Iterator<?>) {
        		value = new HashMap<Integer,Object>();
        		int i = 0;
        		for (Iterator<?> iterator = (Iterator<?>)parameter; iterator.hasNext();) {
					((HashMap<Integer,Object>)value).put(i, iterator.next());				
				}
        	} else if(parameter instanceof Map<?,?>) {
        		
        		Map<?,?> map = (Map<?,?>)parameter;
        		if(!map.isEmpty()) {        			
        			Object key = map.keySet().iterator().next();
        			if(key instanceof String) {
        				if(keyNames != null) {
        					for (Entry<?, ?> entry : map.entrySet()) {
    							Integer index = keyNames.indexOf(key);
    							if(index >= 0) {
        							((HashMap<Integer,Object>)value).put(index, entry.getValue());
    							}
        					}
        				}
        			} else if(key instanceof Integer) {
        				value = map;
					}        			       			
        		}	
        	}
        }
    	
		return value == null ? new HashMap<Integer, Object>() : (Map<Integer,?>)value;		
	}
	
	private void addListParameter(Object parameter, String name) {
		
		Object value = null;
		
        if (parameter != null){
        	if(parameter instanceof String && !((String)parameter).contains(",")) {
        		value = findValue(parameter.toString());
        		if(value != null) {
        			parameter = value;
        		} else {
        			parameter = new String[]{(String)parameter};
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
	
	public class Column {
		private String name;
		private String index;
		private String alignment;
		private Integer width;
		private String label;
		private Boolean resizable = true;
		private Boolean editable = false;
		private Boolean sortable = false;
		private String sortType;
		private String dateFormat;
		
		public String getName() {
			return name;
		}
		public String getIndex() {
			return index;
		}
		public String getAlignment() {
			return alignment;
		}
		public Integer getWidth() {
			return width;
		}
		public String getLabel() {
			return label;
		}
		public Boolean getResizable() {
			return resizable;
		}
		public Boolean getEditable() {
			return editable;
		}
		public Boolean getSortable() {
			return sortable;
		}		
		public String getSortType() {
			return sortType;
		}
		public String getDateFormat() {
			return dateFormat;
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

    @StrutsTagAttribute(description="Iterable source to populate table from. Typically the data will be a collection of collections where each contained collection is " +
    		"a row in the table. The natural sorting order of the iterator obtained from the collections will be used to sort the rows and" +
    		"the columns.  This should not be used together with the 'src' attribute", required=false)
	public void setData(Object data) {

    	this.data = data;
	}
		
	@StrutsTagAttribute(description="A catch-all for the user to specify custom jqGrid options above the ones specific provided for as attributes. options provided here will override those set as attributes.", required=false)
	public void setOptions(String options) {
		this.options = options;
	}

	@StrutsTagAttribute(description="A boolean indicating whether the 'pager' control which provides paging navigation should be shown",  type="Boolean", defaultValue="false", required=false)
    public void setPager(String pager) {
		this.pager = pager;
	}
	
	@StrutsTagAttribute(description="A list or map or comma delimited string of Strings indicating the alignment of a column. Valid string value are 'right','left','center', or null. If a collection is provided, the order of the collection is assumed to be the order of the columns. " +
			"If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)", required=false)
	public void setColumnAlignments(Object columnAlignments) {
		this.columnAlignments = columnAlignments;
	}

	@StrutsTagAttribute(description="A list or map or comma delimited string of booleans indicating whether a column is editable or not. If a collection is provided, the order of the collection is assumed to be the order of the columns. " +
			"If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)", required=false)
	public void setColumnEditables(Object columnEditables) {
		this.columnEditables = columnEditables;
	}

	@StrutsTagAttribute(description="A list or comma delimited string of column names. The list should be the same length as the number of columns in the data", required=false)
	public void setColumnNames(Object columnNames) {
		this.columnNames = columnNames;
	}

    @StrutsTagAttribute(description="A list or map or comma delimited string of labels for the columns. If a collection is provided, the order of the collection is assumed to be the order of the columns. " +
    		"If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)", required=false)
	public void setColumnLabels(Object columnLabels) {
		this.columnLabels = columnLabels;
	}

	@StrutsTagAttribute(description="A list or map or comma delimited string of booleans indicating whether a column is resizable or not. If a collection is provided, the order of the collection is assumed to be the order of the columns." +
			"If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)", required=false)
	public void setColumnResizables(Object columnResizables) {
		this.columnResizables = columnResizables;
	}

	@StrutsTagAttribute(description="A list or map or comma delimited string of booleans indicating whether a column is sortable or not. If a collection is provided, the order of the collection is assumed to be the order of the columns. " +
			"If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)", required=false)
	public void setColumnSortables(Object columnSortables) {
		this.columnSortables = columnSortables;
	}

	@StrutsTagAttribute(description="A list or map of sort types for each column. The sort types are int/integer/float/number/currency/date/text " +
			"(defaults to 'text'). If a collection is provided, the order of the collection is assumed to be the order of the columns. " +
			"If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)", required=false)
	public void setColumnSortTypes(Object columnSortTypes) {
		this.columnSortTypes = columnSortTypes;
	}
	
	@StrutsTagAttribute(description="A list or map of the date formats for each column. The sort types are int/integer/float/number/currency/date/text " +
			"(defaults to 'text'). If a collection is provided, the order of the collection is assumed to be the order of the columns. " +
			"If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)", required=false)
	public void setColumnDateFormats(Object columnDateFormats) {
		this.columnDateFormats = columnDateFormats;
	}
	
	@StrutsTagAttribute(description="A list or map or comma delimited string of column widths. If a collection is provided, the order of the collection is assumed to be the order of the columns. " +
			"If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)", required=false)
	public void setColumnWidths(Object columnWidths) {
		this.columnWidths = columnWidths;
	}
	
	@StrutsTagAttribute(description="The name or index of the column to be sorted first when the grid is poulated remotely (using 'src')", required=false)
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	
	@StrutsTagAttribute(description="A boolean indicating whether to sort the initial column in ascending order (true=ascending, false=descending). Only used if sortColumn is set and the grid is poulated remotely (using 'src')",  type="Boolean", defaultValue="false", required=false)
    public void setSortAscending(String sortAscending) {
		this.sortAscending = sortAscending;
	}
	
	@StrutsTagAttribute(description="An integer with the number of rows that should be displayed on a page",  type="Integer", required=false)
    public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	
	@StrutsTagAttribute(description="A list or comma delimited string of integers of the choices for rows-per-page", required=false)
    public void setRowsPerPageOptions(Object rowsPerPageOptions) {
		this.rowsPerPageOptions = rowsPerPageOptions;
	}
	
	@StrutsTagAttribute(description="A boolean indicating whether or not to display the number of total records from the query in the pager bar.",  type="Boolean", defaultValue="false", required=false)
    public void setShowTotal(String showTotal) {
		this.showTotal = showTotal;
	}
	
	@StrutsTagAttribute(name="caption", description="The caption for the grid",required=false)
    public void setCaption(String caption) {
		this.caption = caption;
	}
	
	@StrutsTagAttribute(name="height", description="The height of the grid. Can be an integer value (pixels) or a percentage string (like '40%')",required=false)
    public void setHeight(String height) {
		this.height = height;
	}
	
	@StrutsTagAttribute(name="width", description="The width of the grid. Can be an integer value (pixels) or a percentage string (like '40%');",required=false)
    public void setWidth(String width) {
		this.width = width;
	}
}
