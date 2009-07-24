package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Datepicker;

import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class DatepickerTag extends TextFieldTag {
	
    private String displayFormat;
    private String imageUrl;
    private String imageTooltip;
    private String options;
    private String changeYear;
    private String changeMonth;
    private String showButton;

	public DatepickerTag() {
		super();
	}
	
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Datepicker(stack, req, res);
		return bean;
	}
	
	@Override
    protected void populateParams() {
		
		super.populateParams();
		
        Datepicker picker = ((Datepicker) component);
        picker.setDisplayFormat(displayFormat);
        picker.setImageTooltip(imageTooltip);
        picker.setImageUrl(imageUrl);
        picker.setOptions(options);
        picker.setChangeMonth(changeMonth);
        picker.setChangeYear(changeYear);  
        picker.SetShowButton(showButton);  
    }

    public void setDisplayFormat(String displayFormat) {
        this.displayFormat = displayFormat;
    }

    public void setImageTooltip(String imageTooltip) {
        this.imageTooltip = imageTooltip;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public void setChangeYear(String changeYear) {
        this.changeYear = changeYear;
    }

    public void setChangeMonth(String changeMonth) {
        this.changeMonth = changeMonth;
    }

	public void setShowButton(String showButton) {
		this.showButton = showButton;
	}
}
