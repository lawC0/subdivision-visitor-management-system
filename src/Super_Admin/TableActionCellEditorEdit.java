
package Super_Admin;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;

public class TableActionCellEditorEdit extends DefaultCellEditor{
    
    private TableActionEventEdit event;
    public TableActionCellEditorEdit(TableActionEventEdit event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelActionEditAndDeleteButton action = new PanelActionEditAndDeleteButton();
       action.initEvent(event, row);
       action.setBackground(Color.white);
               return action;
               
    }
}
