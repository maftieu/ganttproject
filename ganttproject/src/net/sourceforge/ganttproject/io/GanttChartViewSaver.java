package net.sourceforge.ganttproject.io;

import javax.xml.transform.sax.TransformerHandler;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import net.sourceforge.ganttproject.GanttTreeTable;
import net.sourceforge.ganttproject.GanttTreeTable.DisplayedColumnsList;

class GanttChartViewSaver extends SaverBase {

    void save(GanttTreeTable treeTable, TransformerHandler handler) throws SAXException {
        AttributesImpl attrs = new AttributesImpl();
        startElement("taskdisplaycolumns", handler);
        final DisplayedColumnsList displayedColumns = treeTable.getDisplayColumns();
        if (displayedColumns != null) {
            for (int i=0; i<displayedColumns.size(); i++) {
                GanttTreeTable.DisplayedColumn dc = (GanttTreeTable.DisplayedColumn)displayedColumns.get(i);
                if (dc.isDisplayed()) {
                    addAttribute("property-id", dc.getID(), attrs);
                    addAttribute("order", dc.getOrder(), attrs);
                    addAttribute("width", dc.getWidth(), attrs);
                    emptyElement("displaycolumn", attrs, handler);
                }
            }
        }
        endElement("taskdisplaycolumns", handler);
    }
}
