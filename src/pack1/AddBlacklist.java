
package pack1;


import java.sql.*;
import java.util.logging.*;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AddBlacklist extends javax.swing.JFrame {
    int count2 =1;
    public boolean checker;
    private int ID;
    private final String password;
    private MenuFrame menuframe;
    /**
     * Creates new form AddBlacklist
     */
    public AddBlacklist(int ID, String password, MenuFrame menuframe) {
        this.menuframe = menuframe;
        initComponents();
        setLocationRelativeTo(null);
        this.ID = ID;
        this.password = password;
        AddTableActionEventBL event = new  AddTableActionEventBL() {
            @Override
            public void onAdd(int row) {
                
                //kapag nagsearch dito
                if(!Search.getText().isEmpty()&&!"Search for people to add".equals(Search.getText())){
                     //perform modification
                    try{
                        String hostname = "localhost";
                        String sqlInstanceName = "NETBEANSCONNECT"; //computer name 
                        String sqlDatabase = "VisitorMS";  //sql server database name
                        String sqlUser = "sa";
                        String sqlPassword = "BSIT2AG1"; //passwrod sa account

                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                        String connectURL = "jdbc:sqlserver://" + hostname + ":1433" 
                                    + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
                            +";encrypt=true;trustServerCertificate=true";

                        Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
                        System.out.println("Connect to database successful!!");    

                        int row2=addTable.getSelectedRow();
                        System.out.println(row2);
                        if (addTable.getRowSorter()!=null) {
                            row2 = addTable.getRowSorter().convertRowIndexToModel(row2);
                        }
                    
                        String id = (addTable.getModel().getValueAt(row2, 0).toString());
                        int visitorId = Integer.parseInt(id);

                        Statement stmt = conn.createStatement();
                        String query ="UPDATE visitor SET visitorstatus = 'Banned' WHERE visitorid ="+visitorId;
                        stmt.executeUpdate(query);

                        DefaultTableModel tb2ModelAdd= (DefaultTableModel)addTable.getModel();
                        tb2ModelAdd.removeRow(row2);
                        
                        conn.close();
                        
                    }catch(SQLException e){
                        e.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }        
                    
                }
                else{//pag di nagsearch
                    try{
                        String hostname = "localhost";
                        String sqlInstanceName = "NETBEANSCONNECT"; //computer name 
                        String sqlDatabase = "VisitorMS";  //sql server database name
                        String sqlUser = "sa";
                        String sqlPassword = "BSIT2AG1"; //passwrod sa account

                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                        String connectURL = "jdbc:sqlserver://" + hostname + ":1433" 
                                    + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
                            +";encrypt=true;trustServerCertificate=true";

                        Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
                        System.out.println("Connect to database successful!!");    


                        String id = (addTable.getModel().getValueAt(row, 0).toString());
                        int visitorId = Integer.parseInt(id);

                        Statement stmt = conn.createStatement();
                        String query ="UPDATE visitor SET visitorstatus = 'Banned' WHERE visitorid ="+visitorId;
                        stmt.executeUpdate(query);

                        DefaultTableModel tb2ModelAdd= (DefaultTableModel)addTable.getModel();
                        tb2ModelAdd.removeRow(row);
                        
                        conn.close();
                        menuframe.viewBL();
                    }catch(SQLException e){
                        e.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
          
                
                
                
            }
        };
                
        addTable.getTableHeader().setUI(null);
        addTable.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRenderBL());
        addTable.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditorBL(event));
        displayPeople();
    }
    //display ang mga allowed na visitor
    public void displayPeople(){
        if(count2==1){
            try{
                String hostname = "localhost";
                String sqlInstanceName = "NETBEANSCONNECT"; //computer name 
                String sqlDatabase = "VisitorMS";  //sql server database name
                String sqlUser = "sa";
                String sqlPassword = "BSIT2AG1"; //passwrod sa account

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                String connectURL = "jdbc:sqlserver://" + hostname + ":1433" 
                            + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
                    +";encrypt=true;trustServerCertificate=true";

                Connection conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
                System.out.println("Connect to database successful!!");    

                Statement st = conn.createStatement();
                //ms sql query
                String sql = "select visitorid,firstname +' '+lastname as 'Name' from visitor where visitorstatus ='Allowed' order by visitorid";
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    String id = String.valueOf(rs.getInt("visitorid"));
                    String name = rs.getString("name");

                    //array

                    String addBl [] = {id,name};                
                    DefaultTableModel tb2Add = (DefaultTableModel)addTable.getModel();
//                    add string array data into jtable
                    tb2Add.addRow(addBl);
                    
                }
                rs.close();
                conn.close();

            }catch(SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            count2=0;
         }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        block = new javax.swing.JLabel();
        Search = new java.awt.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        addTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(354, 467));

        block.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        block.setText("Block Visitor");

        Search.setBackground(new java.awt.Color(211, 227, 255));
        Search.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        Search.setText("Search for people to add");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
        });

        addTable.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        addTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        addTable.setAutoscrolls(false);
        addTable.setRowHeight(45);
        addTable.setRowSelectionAllowed(false);
        addTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
        addTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(addTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(block))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(block)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        DefaultTableModel tb2ModelSearch = (DefaultTableModel)addTable.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(tb2ModelSearch);
        addTable.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(Search.getText()));
    }//GEN-LAST:event_SearchActionPerformed

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
        DefaultTableModel tb2ModelSearch = (DefaultTableModel)addTable.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(tb2ModelSearch);
        addTable.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(Search.getText()));
    }//GEN-LAST:event_SearchKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//       MenuFrame bl = new MenuFrame(this.ID,this.password);
//       bl.setVisible(true);
        dispose();
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddBlacklist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBlacklist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBlacklist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBlacklist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddBlacklist(1001).setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextField Search;
    private javax.swing.JTable addTable;
    private javax.swing.JLabel block;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
