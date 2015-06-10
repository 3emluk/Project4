package com.epam.labs.utils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Class for handling custom jstl concat tag
 */
public class ConcatTagHandler extends TagSupport {
    /**
     * Line till the concat will perform
     */
    private String originalLine;
    /**
     * Line which will be concated
     */
    private String concatLine;

    /**
     * Method for describing tag  concat action
     *
     * @return Concat action
     * @throws JspException in case wrong attributes
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.println(originalLine = originalLine.concat(concatLine));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    /**
     * Method for getting original line
     *
     * @return Original line
     */
    public String getOriginalLine() {
        return originalLine;
    }

    /**
     * Method for setting up original line
     *
     * @param originalLine Original line
     */
    public void setOriginalLine(String originalLine) {
        this.originalLine = originalLine;
    }

    /**
     * Method for getting concat line
     *
     * @return Concat line
     */
    public String getConcatLine() {
        return concatLine;
    }

    /**
     * Method for setting concat line
     *
     * @param concatLine Concat line
     */
    public void setConcatLine(String concatLine) {
        this.concatLine = concatLine;
    }

}
