package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Grid;

import com.opensymphony.xwork2.util.ValueStack;

public class GridTag extends AbstractContainerTag {
	
	private static final long serialVersionUID = -5224020702653433267L;
	
	protected Object data;				//An object with the grid data
	protected String options;			//A catch-all for the user to specify custom flot options above the ones specific provided for as attributes. options provided here will override those set as attributes.
	protected Object columnLabels;		//A list of labels for the columns. Must be same length as # of columns in data
	protected Object columnNames;			//A list of column names. The list should be the same length as the number of columns in the data
	protected Object columnWidths;			//A List or map of column widths. If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected Object columnSortables;		//A list or map of booleans indicating whether a column is sortable or not. If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected Object columnSortTypes;		//A list or map of sort types for each column. The sort types are int/integer/float/number/currency/date/text (defaults to 'text'). If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected Object columnEditables;		//A list or map of booleans indicating whether a column is editable or not. If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected Object columnResizables;		//A list or map of booleans indicating whether a column is resizable or not. If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
	protected Object columnAlignments;		//A list or map of Strings indicating the alignment of a column. Valid string value are 'right','left','center', or null. If a collection is provided, the order of the collection is assumed to be the order of the columns. If a map is provided, the key may be the column index (integer) or the column name (only if columnNames list is provided)
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
		  
	public GridTag() {
		super();
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Grid(stack, req, res);
		return bean;
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();

		Grid grid = (Grid) component;
		grid.setData(data);
		grid.setColumnLabels(columnLabels);
		grid.setOptions(options);
		grid.setColumnAlignments(columnAlignments);
		grid.setColumnEditables(columnEditables);
		grid.setColumnNames(columnNames);
		grid.setColumnWidths(columnWidths);
		grid.setColumnResizables(columnResizables);
		grid.setColumnSortables(columnSortables);
		grid.setColumnSortTypes(columnSortTypes);
		grid.setSortAscending(sortAscending);
		grid.setColumnDateFormats(columnDateFormats);
		grid.setSortColumn(sortColumn);
		grid.setPager(pager);		
		grid.setRowsPerPage(rowsPerPage);
		grid.setRowsPerPageOptions(rowsPerPageOptions);
		grid.setCaption(caption);
		grid.setShowTotal(showTotal);
		grid.setWidth(width);
		grid.setHeight(height);
		
    }

	public void setData(Object data) {
		this.data = data;
	}
	
	public void setOptions(String options) {
		this.options = options;
	}
	
	public void setColumnLabels(Object columnLabels) {
		this.columnLabels = columnLabels;
	}
	
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	
	public void setSortAscending(String sortAscending) {
		this.sortAscending = sortAscending;
	}
	
	public void setPager(String pager) {
		this.pager = pager;
	}
	
	public void setColumnWidths(Object columnWidths) {
		this.columnWidths = columnWidths;
	}
	
	public void setColumnSortables(Object columnSortables) {
		this.columnSortables = columnSortables;
	}
	
	public void setColumnResizables(Object columnResizables) {
		this.columnResizables = columnResizables;
	}
	
	public void setColumnNames(Object columnNames) {
		this.columnNames = columnNames;
	}
	
	public void setColumnEditables(Object columnEditables) {
		this.columnEditables = columnEditables;
	}
	
	public void setColumnAlignments(Object columnAlignments) {
		this.columnAlignments = columnAlignments;
	}
	
	public void setColumnDateFormats(Object columnDateFormats) {
		this.columnDateFormats = columnDateFormats;
	}
	
	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	
	public void setRowsPerPageOptions(Object rowsPerPageOptions) {
		this.rowsPerPageOptions = rowsPerPageOptions;
	}
	
	public void setShowTotal(String showTotal) {
		this.showTotal = showTotal;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public void setHeight(String height) {
		this.height = height;
	}
	
	public void setWidth(String width) {
		this.width = width;
	}
	
	public void setColumnSortTypes(Object columnSortTypes) {
		this.columnSortTypes = columnSortTypes;
	}
}

