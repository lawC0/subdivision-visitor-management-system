
package Super_Admin;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableActionCellRenderEdit extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
         PanelActionEditAndDeleteButton action = new PanelActionEditAndDeleteButton();
        if(isSelected==false&& row % 2 ==0){
            action.setBackground(Color.white);
        }
        action.setBackground(Color.white);
        return action;
    }  
}
