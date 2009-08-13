package org.apache.struts2.jquery.components;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.util.ContainUtil;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@StrutsTag(name="datepicker", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.DatepickerTag", 
		description="Renders a date picker",
		allowDynamicAttributes=true)
public class Datepicker extends TextField {
	
	protected static final Logger LOG = LoggerFactory.getLogger(Datepicker.class);

    public static final String TEMPLATE = "datepicker";
    public static final String TEMPLATE_CLOSE = "datepicker-close";
    
    protected static final String RFC3339_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    protected static final String RFC3339_PATTERN = "{0,date," + RFC3339_FORMAT + "}";

    protected String displayFormat;	//The format for parsed and displayed dates. See http://docs.jquery.com/UI/Datepicker/formatDate
    protected String imageUrl;		//The URL for the popup button image. If set, button text becomes the alt value and is not directly displayed.
    protected String imageTooltip;	//The text to display for the alt tip on the popup button image.
    protected String options;			//Additional widget options
    protected String changeYear;		//Allows you to change the year by selecting from a drop-down list. You can enable this feature by setting the attribute to true.
    protected String changeMonth;		//Allows you to change the month by selecting from a drop-down list. You can enable this feature by setting the attribute to true.
    protected String showButton;		//Set to 'true' in order to show a button next to the field to display the date picker (defaults to false).
    
    public Datepicker(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    public boolean contains(Object obj1, Object obj2) {
        return ContainUtil.contains(obj1, obj2);
    }
    
	@Override
    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();

        if (displayFormat != null) {
        	displayFormat = convertFormatPattern(findString(displayFormat));
            addParameter("displayFormat", displayFormat);
        }
         else
            addParameter("displayFormat", "yy-mm-dd");

        if (imageUrl != null)
            addParameter("imageUrl", findString(imageUrl));

        if (imageTooltip != null)
            addParameter("imageTooltip", findString(imageTooltip));
        else
            addParameter("imageTooltip", "Pick a date");

        if (this.changeMonth != null)
            addParameter("changeMonth", findString(this.changeMonth));
        else
            addParameter("changeMonth", "true");

        if (this.changeYear != null)
            addParameter("changeYear", findString(this.changeYear));
        else
            addParameter("changeYear", "true");

        if (this.showButton != null)
            addParameter("showButton", findValue(this.showButton, Boolean.class));
        else
            addParameter("showButton", false);

        if (this.options != null) {
            addParameter("options", findString(this.options));
        	/*  Not necessary- we need to proper json object string and we do a check in js
            String ops = findString(this.options);
            if (StringUtils.isNotEmpty(ops))
                addParameter("options", StringEscapeUtils.escapeJavaScript(ops));
             */
        }

        Object currentValue = null;
        if (parameters.containsKey("nameValue")) {
            currentValue = parameters.get("nameValue");
        } else if (parameters.containsKey("name")) {
            currentValue = findValue((String) parameters.get("name"));
        }

        if (currentValue != null) {
            Date date = getDate(currentValue);
            if (date != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String formattedDate = displayFormat == null ? MessageFormat.format(RFC3339_PATTERN, date) :  MessageFormat.format("{0,date," + convertFormatPattern(findString(displayFormat)) + "}", date);
                addParameter("nameValue", formattedDate);
                addParameter("year", calendar.get(Calendar.YEAR));
                addParameter("day", calendar.get(Calendar.DAY_OF_MONTH));
                addParameter("month", calendar.get(Calendar.MONTH));
            } else {
                //the value could not be parsed into a Date, display as is
                addParameter("displayValue", currentValue.toString());
            }
        }
    }
	
	private String convertFormatPattern(String javaDateFormat) {
		
		String jQueryFormat = new String(javaDateFormat);
		jQueryFormat = jQueryFormat.replaceAll("(y{2})", "y");
		if(jQueryFormat.contains("MMM")){
			jQueryFormat = jQueryFormat.replaceFirst("(M{2})", "");
		} else if (jQueryFormat.contains("M")){
			jQueryFormat = jQueryFormat.replaceAll("(M)", "m");
		} 		
		
		return jQueryFormat;
	}


    private Date getDate(Object obj) {
        if (obj == null)
            return null;

        if (obj instanceof Date) {
            return (Date) obj;
        } else if (obj instanceof Calendar) {
            return ((Calendar) obj).getTime();
        } else {
            // try to parse a date
            String dateStr = obj.toString();
            if (dateStr.equalsIgnoreCase("today")) {
                return new Date();
            }

            //formats used to parse the date
            List<DateFormat> formats = new ArrayList<DateFormat>();
            formats.add(new SimpleDateFormat(RFC3339_FORMAT));
            formats.add(SimpleDateFormat.getTimeInstance(DateFormat.SHORT));
            formats.add(SimpleDateFormat.getDateInstance(DateFormat.SHORT));
            formats.add(SimpleDateFormat.getDateInstance(DateFormat.MEDIUM));
            formats.add(SimpleDateFormat.getDateInstance(DateFormat.FULL));
            formats.add(SimpleDateFormat.getDateInstance(DateFormat.LONG));
            if (this.displayFormat != null) {
                try {
                    SimpleDateFormat displayFormat = new SimpleDateFormat(
                            (String) getParameters().get("displayFormat"));
                    formats.add(displayFormat);
                } catch (Exception e) {
                }
            }

            for (DateFormat format : formats) {
                try {
                    Date date = format.parse(dateStr);
                    if (date != null)
                        return date;
                } catch (Exception e) {
                    //keep going
                }
            }

            // last resource, assume already in correct/default format
            if (LOG.isDebugEnabled())
                LOG.debug("Unable to parse date " + dateStr);
            return null;
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

    @StrutsTagAttribute(description = "Format use to display the selected date (See java.text.SimpleDateFormat for patterns)", defaultValue = "mm/dd/yy")
    public void setDisplayFormat(String displayFormat) {
        this.displayFormat = displayFormat;
    }

    @StrutsTagAttribute(description = "Tooltip for the calendar image", defaultValue = "Pick a date")
    public void setImageTooltip(String imageTooltip) {
        this.imageTooltip = imageTooltip;
    }

    @StrutsTagAttribute(description = "Image used for the calendar button")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @StrutsTagAttribute(description = "The name of a variable(or a javascript map) that contains additional widget options to be passed to the Datepicker")
    public void setOptions(String options) {
        this.options = options;
    }

    @StrutsTagAttribute(description = "Allows you to change the month by selecting from a drop-down list", type = "Boolean", defaultValue = "true")
    public void setChangeMonth(String changeMonth) {
        this.changeMonth = changeMonth;
    }

    @StrutsTagAttribute(description = "Allows you to change the year by selecting from a drop-down list", type = "Boolean", defaultValue = "true")
    public void setChangeYear(String changeYear) {
        this.changeYear = changeYear;
    }

    @StrutsTagAttribute(description = "Set to 'true' in order to show a button next to the field to display the date picker.", type = "Boolean", defaultValue = "false")
    public void setShowButton(String showButton) {
        this.showButton = showButton;
    }

   }

