
package pack1;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;

public class TableActionCellEditorBL extends DefaultCellEditor{
    
    private AddTableActionEventBL event;
    public TableActionCellEditorBL(AddTableActionEventBL event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
       PanelActionAddButtonBL action = new PanelActionAddButtonBL();
       action.initEvent(event, row);
       action.setBackground(Color.white);
               return action;
               
    }
}
