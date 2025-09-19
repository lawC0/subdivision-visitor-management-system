
package Vehicle;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
/**
 *
 * @author Win10
 */
public class TableActionCellEditorDelete extends DefaultCellEditor{
    
    private TableActionEventDelete event;
    public TableActionCellEditorDelete(TableActionEventDelete event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelActionDeleteButton action = new PanelActionDeleteButton();
       action.initEvent(event, row);
       action.setBackground(Color.white);
               return action;
               
    }
}
