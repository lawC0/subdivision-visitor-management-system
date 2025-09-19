/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Super_Admin;

import Objects.*;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.*;
import pack1.MenuFrame;

/**
 *
 * @author Win10
 */
public class editRes extends javax.swing.JPanel {
String lname, fname, mname,suf,sex,bdate,email,phone,block,lot,phase,sexC,id2;
boolean lnameChecker, fnameChecker, mnameChecker,sufChecker,bdateChecker,emailChecker,phoneChecker,blockChecker,lotChecker,phaseChecker,editSuccess,deleteSuccess;
int rowCount;
    /**
     * Creates new form editRes
     */
    public editRes() {
        initComponents();
        tableDesign();
        tableColumnSize();
        viewRes();
        confirmationPanel2.setVisible(false); 
        TableActionEventEdit event = new TableActionEventEdit() {
            @Override
            public void onEdit(int row) {
                if(editResTable.isEditing()){
                    editResTable.getCellEditor().stopCellEditing();
                }        
                if (!Searchtextfield1.getText().isEmpty() && !"Search...".equals(Searchtextfield1.getText())) {
                    int row3 = editResTable.getSelectedRow();
                        if (editResTable.getRowSorter() != null) {
                            row = editResTable.getRowSorter().convertRowIndexToModel(row3);
                        }
                }
                System.out.println(row);
                System.out.println("edit");
                editResTabPanel.setSelectedIndex(1);
                id2 = (editResTable.getModel().getValueAt(row, 0).toString());
//                System.out.println(id2);
                String lname2 = (editResTable.getModel().getValueAt(row, 1).toString());
                String fname2 = (editResTable.getModel().getValueAt(row, 2).toString());
                
                if(editResTable.getModel().getValueAt(row, 3)!=null){
                    String mname2 = (editResTable.getModel().getValueAt(row, 3).toString());
                    MNameField2.setText(mname2);
                }
                else{
                    MNameField2.setText("");
                }
                if(editResTable.getModel().getValueAt(row, 4)!=null){
                    String suf2 = (editResTable.getModel().getValueAt(row, 4).toString());
                    SuffixField2.setText(suf2);
                }
                else{
                    SuffixField2.setText("");
                }
                String sex2 = (editResTable.getModel().getValueAt(row, 5).toString());
                String bdate2 = (editResTable.getModel().getValueAt(row, 6).toString());
                String email2 = (editResTable.getModel().getValueAt(row, 7).toString());
                String phone2 = (editResTable.getModel().getValueAt(row, 8).toString());
                String block2 = (editResTable.getModel().getValueAt(row, 9).toString());
                String lot2 = (editResTable.getModel().getValueAt(row, 10).toString());
                String phase2 = (editResTable.getModel().getValueAt(row,11).toString());
                
                //display the selected Resident Info
                FNameField2.setText(fname2);          
                LNameField2.setText(lname2);
                System.out.println(sex2);
                
                if("F".equals(sex2)){
                    SexCombo2.setSelectedIndex(1);
                }
                else{
                    SexCombo2.setSelectedIndex(0);
                }
                
                BdateField.setText(bdate2);
                emailField.setText(email2);
                phoneNumberField.setText(phone2);
                blockNumField.setText(block2);
                lotNumField.setText(lot2);
                phaseNumField.setText(phase2);
                
                
            }

            @Override
            public void onDelete(int row) {
                if(editResTable.isEditing()){
                    editResTable.getCellEditor().stopCellEditing();
                }
                if (!Searchtextfield1.getText().isEmpty() && !"Search...".equals(Searchtextfield1.getText())) {
                    int row3 = editResTable.getSelectedRow();
                        if (editResTable.getRowSorter() != null) {
                            row = editResTable.getRowSorter().convertRowIndexToModel(row3);
                        }
                }
                System.out.println("Delete");
                id2 = (editResTable.getModel().getValueAt(row, 0).toString());
                System.out.println(id2);
                try {                            
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");
            
                    System.out.println("Connect to database successful!!");
                    
                        int residentId = Integer.parseInt(id2);
                        String query = "DELETE from resident WHERE residentid ="+residentId;
                        deleteSuccess=true;
                        
                        JOptionPane.showMessageDialog(null, "Successfully Deleted!", "Message", JOptionPane.INFORMATION_MESSAGE);
    
                        PreparedStatement pst= conn.prepareStatement(query);

                         pst.executeUpdate();

                    conn.close();

                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, e);
                }
                
                if(deleteSuccess){
                    viewRes();
                    deleteSuccess=false;
                }
              
            }
        };
                
        editResTable.getColumnModel().getColumn(12).setCellRenderer(new TableActionCellRenderEdit());
        editResTable.getColumnModel().getColumn(12).setCellEditor(new TableActionCellEditorEdit(event));
    }
     public void tableDesign() {
        DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
        head_render.setBackground(new Color(30, 30, 30));
        head_render.setForeground(Color.white);
        head_render.setFont(new Font("Calibri", Font.BOLD, 14));
        editResTable.getTableHeader().setDefaultRenderer(head_render);
//        editResTable.setBackground(Color.white);
    }
     public void tableColumnSize(){
        TableColumnModel columnModel = editResTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(1).setPreferredWidth(90);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(40);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(100);
        columnModel.getColumn(8).setPreferredWidth(100);
        columnModel.getColumn(9).setPreferredWidth(50);
        columnModel.getColumn(10).setPreferredWidth(50);
        columnModel.getColumn(11).setPreferredWidth(50);
    }
 
    public void viewRes() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");
            System.out.println("Connect to database successful!!");

            Statement st = conn.createStatement();
            //ms sql query
            String sql = "select * from resident order by residentid";
            ResultSet rs = st.executeQuery(sql);
            
            DefaultTableModel tb3Model = (DefaultTableModel) editResTable.getModel();
            tb3Model.setRowCount(0);
            
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("residentid"));
                String lname = rs.getString("LastName");
                String fname = rs.getString("FirstName");
                String mname = rs.getString("MiddleName");
                String suf = rs.getString("Suffix");
                String sex = rs.getString("sex");
                String bdate = rs.getString("Birthdate");
                String email = rs.getString("email");
                String phone = rs.getString("PhoneNumber");
                String block = String.valueOf(rs.getInt("BlockNum"));
                String lot = String.valueOf(rs.getInt("LotNum"));
                String phase = String.valueOf(rs.getInt("PhaseNum"));


                //array
                String resData[] = {id, lname,fname,mname,suf, sex,bdate, email,phone,block,lot,phase};         
                
                //add string array data into jtable
                tb3Model.addRow(resData);

            }
            rs.close();
            
            rowCount = editResTable.getRowCount();
            conn.close();

        } catch (SQLException e) {
                e.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(SuperAdmin.class.getName()).log(Level.SEVERE, null, ex);
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

        editResTabPanel = new javax.swing.JTabbedPane();
        editTable = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        editResTable = new javax.swing.JTable();
        Searchtextfield1 = new java.awt.TextField();
        Clear = new java.awt.Button();
        editResRefreshButton = new javax.swing.JButton();
        editRes = new javax.swing.JPanel();
        addPeoplePanel = new javax.swing.JPanel();
        FullNameLabel = new javax.swing.JLabel();
        LNameLabel1 = new javax.swing.JLabel();
        FNameLabel1 = new javax.swing.JLabel();
        MNameLabel1 = new javax.swing.JLabel();
        SuffixLabel1 = new javax.swing.JLabel();
        SexLabel1 = new javax.swing.JLabel();
        AddressLabel = new javax.swing.JLabel();
        blockNum = new javax.swing.JLabel();
        lotNum = new javax.swing.JLabel();
        phaseNum = new javax.swing.JLabel();
        BDateLabel = new javax.swing.JLabel();
        LNameField2 = new javax.swing.JTextField();
        FNameField2 = new javax.swing.JTextField();
        MNameField2 = new javax.swing.JTextField();
        SuffixField2 = new javax.swing.JTextField();
        blockNumField = new javax.swing.JTextField();
        lotNumField = new javax.swing.JTextField();
        phaseNumField = new javax.swing.JTextField();
        phoneNumberLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        phoneNumberField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();
        SexCombo2 = new javax.swing.JComboBox<>();
        BdateField = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        confirmationPanel2 = new javax.swing.JPanel();
        residentLabel2 = new javax.swing.JLabel();
        fnameLabel2 = new javax.swing.JLabel();
        sexLabel2 = new javax.swing.JLabel();
        bdateLabel2 = new javax.swing.JLabel();
        pnumberLabel2 = new javax.swing.JLabel();
        EmailLabel2 = new javax.swing.JLabel();
        addressLabel2 = new javax.swing.JLabel();
        confirm2Button = new javax.swing.JButton();
        resNameLabel = new javax.swing.JLabel();
        resBdateLabel = new javax.swing.JLabel();
        resSexLabel1 = new javax.swing.JLabel();
        resEmailLabel = new javax.swing.JLabel();
        resAddressLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        resPhoneNumberLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editResTabPanel.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        editTable.setBackground(new java.awt.Color(255, 255, 255));

        editResTable.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        editResTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Resident ID", "Last Name", "First Name", "Middle Name", "Suffix", "Sex", "Birthdate", "Email", "Phone No.", "Block", "Lot", "Phase", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        editResTable.setRowHeight(40);
        editResTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
        editResTable.setSelectionForeground(new java.awt.Color(30, 30, 30));
        editResTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(editResTable);
        if (editResTable.getColumnModel().getColumnCount() > 0) {
            editResTable.getColumnModel().getColumn(0).setResizable(false);
            editResTable.getColumnModel().getColumn(4).setResizable(false);
            editResTable.getColumnModel().getColumn(5).setResizable(false);
            editResTable.getColumnModel().getColumn(9).setResizable(false);
            editResTable.getColumnModel().getColumn(10).setResizable(false);
            editResTable.getColumnModel().getColumn(11).setResizable(false);
            editResTable.getColumnModel().getColumn(12).setResizable(false);
        }

        Searchtextfield1.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        Searchtextfield1.setForeground(new java.awt.Color(30, 30, 30));
        Searchtextfield1.setText("Search...");
        Searchtextfield1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Searchtextfield1MouseClicked(evt);
            }
        });
        Searchtextfield1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Searchtextfield1ActionPerformed(evt);
            }
        });
        Searchtextfield1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Searchtextfield1KeyReleased(evt);
            }
        });

        Clear.setBackground(new java.awt.Color(217, 217, 217));
        Clear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Clear.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        Clear.setForeground(new java.awt.Color(133, 133, 133));
        Clear.setLabel("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        editResRefreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/refresh.png"))); // NOI18N
        editResRefreshButton.setText("Refresh");
        editResRefreshButton.setContentAreaFilled(false);
        editResRefreshButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editResRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editResRefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editTableLayout = new javax.swing.GroupLayout(editTable);
        editTable.setLayout(editTableLayout);
        editTableLayout.setHorizontalGroup(
            editTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTableLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(editTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(editTableLayout.createSequentialGroup()
                        .addGroup(editTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Searchtextfield1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(editTableLayout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(724, 724, 724)
                        .addComponent(editResRefreshButton)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        editTableLayout.setVerticalGroup(
            editTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Searchtextfield1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editResRefreshButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
        );

        editResTabPanel.addTab("tab1", editTable);

        editRes.setBackground(new java.awt.Color(255, 255, 255));

        addPeoplePanel.setVisible(true);
        addPeoplePanel.setBackground(new java.awt.Color(255, 255, 255));
        addPeoplePanel.setPreferredSize(new java.awt.Dimension(753, 700));

        FullNameLabel.setFont(new java.awt.Font("Calibri", 0, 26)); // NOI18N
        FullNameLabel.setText("Full Name");

        LNameLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        LNameLabel1.setText("Last Name*");

        FNameLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        FNameLabel1.setText("First Name*");

        MNameLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        MNameLabel1.setText("Middle Name");

        SuffixLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        SuffixLabel1.setText("Suffix");

        SexLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        SexLabel1.setText("Sex*");

        AddressLabel.setFont(new java.awt.Font("Calibri", 0, 26)); // NOI18N
        AddressLabel.setText("Address");

        blockNum.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        blockNum.setText("Block*");

        lotNum.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lotNum.setText("Lot Number*");

        phaseNum.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        phaseNum.setText("Phase*");

        BDateLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        BDateLabel.setText("Birthdate (yyyy-mm-dd)*");

        LNameField2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        LNameField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LNameField2ActionPerformed(evt);
            }
        });

        FNameField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FNameField2ActionPerformed(evt);
            }
        });

        MNameField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MNameField2ActionPerformed(evt);
            }
        });

        SuffixField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuffixField2ActionPerformed(evt);
            }
        });

        blockNumField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockNumFieldActionPerformed(evt);
            }
        });

        lotNumField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lotNumFieldActionPerformed(evt);
            }
        });

        phaseNumField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phaseNumFieldActionPerformed(evt);
            }
        });

        phoneNumberLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        phoneNumberLabel.setText("Phone Number*");

        emailLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        emailLabel.setText("Email*");

        phoneNumberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumberFieldActionPerformed(evt);
            }
        });

        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        submitButton.setBackground(new java.awt.Color(46, 110, 76));
        submitButton.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        SexCombo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        SexCombo2.setSelectedIndex(-1);
        SexCombo2.setPreferredSize(new java.awt.Dimension(330, 30));
        SexCombo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SexCombo2ActionPerformed(evt);
            }
        });

        BdateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BdateFieldActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(46, 110, 76));
        backButton.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("< Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addPeoplePanelLayout = new javax.swing.GroupLayout(addPeoplePanel);
        addPeoplePanel.setLayout(addPeoplePanelLayout);
        addPeoplePanelLayout.setHorizontalGroup(
            addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPeoplePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPeoplePanelLayout.createSequentialGroup()
                        .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPeoplePanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(SuffixField2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(SuffixLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SexLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(addPeoplePanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(SexCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPeoplePanelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(BDateLabel))
                            .addGroup(addPeoplePanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(addPeoplePanelLayout.createSequentialGroup()
                        .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FNameLabel1)
                            .addGroup(addPeoplePanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(FNameField2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MNameLabel1)
                            .addComponent(MNameField2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LNameLabel1)
                            .addComponent(LNameField2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(FullNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addPeoplePanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddressLabel)
                            .addComponent(phaseNum)
                            .addComponent(blockNum)
                            .addGroup(addPeoplePanelLayout.createSequentialGroup()
                                .addGap(384, 384, 384)
                                .addComponent(lotNum))
                            .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(addPeoplePanelLayout.createSequentialGroup()
                                    .addComponent(phaseNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(266, 266, 266)
                                    .addComponent(submitButton)
                                    .addGap(10, 10, 10))
                                .addGroup(addPeoplePanelLayout.createSequentialGroup()
                                    .addComponent(blockNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(365, 365, 365)))
                            .addGroup(addPeoplePanelLayout.createSequentialGroup()
                                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(235, 235, 235)
                                .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(phoneNumberLabel)
                                    .addComponent(phoneNumberField)
                                    .addComponent(lotNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(backButton))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        addPeoplePanelLayout.setVerticalGroup(
            addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPeoplePanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(backButton)
                .addGap(18, 18, 18)
                .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPeoplePanelLayout.createSequentialGroup()
                        .addComponent(FullNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FNameField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPeoplePanelLayout.createSequentialGroup()
                        .addComponent(MNameLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MNameField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPeoplePanelLayout.createSequentialGroup()
                        .addComponent(LNameLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LNameField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SuffixLabel1)
                    .addComponent(SexLabel1)
                    .addComponent(BDateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SuffixField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SexCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNumberLabel)
                    .addComponent(emailLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(AddressLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blockNum)
                    .addComponent(lotNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blockNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lotNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phaseNum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phaseNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        residentLabel2.setFont(new java.awt.Font("Calibri", 1, 26)); // NOI18N
        residentLabel2.setText("RESIDENT INFORMATION");

        fnameLabel2.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        fnameLabel2.setText("NAME:");

        sexLabel2.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        sexLabel2.setText("SEX:");

        bdateLabel2.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        bdateLabel2.setText("BIRTHDATE:");

        pnumberLabel2.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        pnumberLabel2.setText("PHONE NUMBER:");

        EmailLabel2.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        EmailLabel2.setText("EMAIL:");

        addressLabel2.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        addressLabel2.setText("ADDRESS:");

        confirm2Button.setBackground(new java.awt.Color(46, 110, 76));
        confirm2Button.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        confirm2Button.setForeground(new java.awt.Color(255, 255, 255));
        confirm2Button.setText("Confirm");
        confirm2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirm2ButtonconfirmLabelActionPerformed(evt);
            }
        });

        resNameLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N

        resBdateLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N

        resSexLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N

        resEmailLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N

        resAddressLabel.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N

        cancelButton.setBackground(new java.awt.Color(46, 110, 76));
        cancelButton.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonconfirmLabelActionPerformed(evt);
            }
        });

        resPhoneNumberLabel.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N

        javax.swing.GroupLayout confirmationPanel2Layout = new javax.swing.GroupLayout(confirmationPanel2);
        confirmationPanel2.setLayout(confirmationPanel2Layout);
        confirmationPanel2Layout.setHorizontalGroup(
            confirmationPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmationPanel2Layout.createSequentialGroup()
                .addGroup(confirmationPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(confirmationPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(confirmationPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(confirmationPanel2Layout.createSequentialGroup()
                                .addComponent(fnameLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resNameLabel))
                            .addGroup(confirmationPanel2Layout.createSequentialGroup()
                                .addComponent(pnumberLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resPhoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(confirmationPanel2Layout.createSequentialGroup()
                                .addComponent(addressLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resAddressLabel))
                            .addGroup(confirmationPanel2Layout.createSequentialGroup()
                                .addComponent(bdateLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resBdateLabel))
                            .addGroup(confirmationPanel2Layout.createSequentialGroup()
                                .addComponent(sexLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resSexLabel1))
                            .addComponent(residentLabel2)
                            .addGroup(confirmationPanel2Layout.createSequentialGroup()
                                .addComponent(EmailLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(confirmationPanel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(confirm2Button)
                        .addGap(38, 38, 38)
                        .addComponent(cancelButton)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        confirmationPanel2Layout.setVerticalGroup(
            confirmationPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmationPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(residentLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(confirmationPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fnameLabel2)
                    .addComponent(resNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(confirmationPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sexLabel2)
                    .addComponent(resSexLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(confirmationPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bdateLabel2)
                    .addComponent(resBdateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(confirmationPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resEmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EmailLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(confirmationPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnumberLabel2)
                    .addComponent(resPhoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(confirmationPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resAddressLabel)
                    .addComponent(addressLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(confirmationPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirm2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout editResLayout = new javax.swing.GroupLayout(editRes);
        editRes.setLayout(editResLayout);
        editResLayout.setHorizontalGroup(
            editResLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editResLayout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(addPeoplePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(confirmationPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        editResLayout.setVerticalGroup(
            editResLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editResLayout.createSequentialGroup()
                .addComponent(confirmationPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editResLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(addPeoplePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        editResTabPanel.addTab("tab2", editRes);

        add(editResTabPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-67, 29, 1300, 540));
    }// </editor-fold>//GEN-END:initComponents

    private void LNameField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LNameField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LNameField2ActionPerformed

    private void FNameField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FNameField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FNameField2ActionPerformed

    private void MNameField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MNameField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MNameField2ActionPerformed

    private void SuffixField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuffixField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuffixField2ActionPerformed

    private void blockNumFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockNumFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blockNumFieldActionPerformed

    private void lotNumFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lotNumFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lotNumFieldActionPerformed

    private void phaseNumFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phaseNumFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phaseNumFieldActionPerformed

    private void SexCombo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SexCombo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SexCombo2ActionPerformed

    private void BdateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BdateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BdateFieldActionPerformed

    private void phoneNumberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumberFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumberFieldActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        Resident res = new Resident();
        lname=LNameField2.getText();
        fname=FNameField2.getText();
        mname=MNameField2.getText();
        suf=SuffixField2.getText();
        if(SexCombo2.getSelectedItem()!=null){
            sex=SexCombo2.getSelectedItem().toString();
        }
        bdate=BdateField.getText();
        email=emailField.getText();
        phone=phoneNumberField.getText();
        block = blockNumField.getText();
        lot = lotNumField.getText();
        phase = phaseNumField.getText();
        
       
        //gawing black ang text para pag nagreset
        LNameLabel1.setForeground(Color.black);
        FNameLabel1.setForeground(Color.black);
        MNameLabel1.setForeground(Color.black);
        SuffixLabel1.setForeground(Color.black);
        SexLabel1.setForeground(Color.black);
        BDateLabel.setForeground(Color.black);
        emailLabel.setForeground(Color.black);
        phoneNumberLabel.setForeground(Color.black);
        blockNum.setForeground(Color.black);
        lotNum.setForeground(Color.black);
        phaseNum.setForeground(Color.black);

        //check if may laman
        if(!"".equals(lname)&&!"".equals(fname)&&!"".equals(sex)&&!"".equals(bdate)&&!"".equals(email)
            &&!"".equals(phone)&&!"".equals(block)&&!"".equals(lot)
            &&!"".equals(phase)){

            ///check kung tama ang laman
            if(res.isAlpha(lname)){
                lnameChecker = true;
            }
            else{
                LNameLabel1.setForeground(Color.red);
            }
            
            if(res.isAlpha(fname)){
                fnameChecker = true;
            }
            else{
                FNameLabel1.setForeground(Color.red);
            }
            if(!MNameField2.getText().isEmpty()){
                if(res.isAlpha(mname)){
                    mnameChecker = true;
                }
                else
                MNameLabel1.setForeground(Color.red);
            }

            if(!SuffixField2.getText().isEmpty()){
                if(res.isAlpha(suf)){
                    sufChecker = true;
                }
                else
                SuffixLabel1.setForeground(Color.red);
            }
            
            if(SexCombo2.getSelectedItem()==null){
                SexLabel1.setForeground(Color.red);
            }

            if(res.validateJavaDate(bdate)){
                bdateChecker = true;
            }
            else{
                BDateLabel.setForeground(Color.red);
            }
            if(res.isValid(email)){
                emailChecker=true;
            }
            else{
                emailLabel.setForeground(Color.red);
            }
            if(!res.isAlpha(phone)&&phone.length()==11){
                phoneChecker=true;
            }
            else{
                phoneNumberLabel.setForeground(Color.red);
            }
            if(!res.isAlpha(block)&&block.length()<=2){
                blockChecker=true;
            }
            else{
                blockNum.setForeground(Color.red);
            }
            if(!res.isAlpha(lot)&&lot.length()<=2){
                lotChecker=true;
            }
            else{
                lotNum.setForeground(Color.red);
            }
            if(!res.isAlpha(phase)&&phase.length()==1){
                phaseChecker=true;
            }
            else{
                phaseNum.setForeground(Color.red);
            }
            
            if(lnameChecker==true&&fnameChecker==true&&mnameChecker==true&&sufChecker==true&&bdateChecker==true
                &&emailChecker==true&&phoneChecker==true&&blockChecker==true&&lotChecker==true&&phaseChecker==true){
                confirmationPanel2.setVisible(true);
                resNameLabel.setText(fname+" "+mname+" "+lname+" "+suf);
                resSexLabel1.setText(sex);
                resBdateLabel.setText(bdate);
                resPhoneNumberLabel.setText(phone);
                resEmailLabel.setText(email);
                resAddressLabel.setText("Block "+block+" Lot "+lot+" Phase "+phase+" ");
            }else if(lnameChecker==true&&fnameChecker==true&&mnameChecker==false&&sufChecker==true&&bdateChecker==true
                &&emailChecker==true&&phoneChecker==true&&blockChecker==true&&lotChecker==true&&phaseChecker==true){
                confirmationPanel2.setVisible(true);
                resNameLabel.setText(fname+" "+lname+" "+suf);
                resSexLabel1.setText(sex);
                resBdateLabel.setText(bdate);
                resPhoneNumberLabel.setText(phone);
                resEmailLabel.setText(email);
                resAddressLabel.setText("Block "+block+" Lot "+lot+" Phase "+phase+" ");
            }
            else if(lnameChecker==true&&fnameChecker==true&&mnameChecker==true&&sufChecker==false&&bdateChecker==true
                &&emailChecker==true&&phoneChecker==true&&blockChecker==true&&lotChecker==true&&phaseChecker==true){
                confirmationPanel2.setVisible(true);
                resNameLabel.setText(fname+" "+mname+" "+lname);
                resSexLabel1.setText(sex);
                resBdateLabel.setText(bdate);
                resPhoneNumberLabel.setText(phone);
                resEmailLabel.setText(email);
                resAddressLabel.setText("Block "+block+" Lot "+lot+" Phase "+phase+" ");
            }
            else if(lnameChecker==true&&fnameChecker==true&&mnameChecker==false&&sufChecker==false&&bdateChecker==true
                &&emailChecker==true&&phoneChecker==true&&blockChecker==true&&lotChecker==true&&phaseChecker==true){
                confirmationPanel2.setVisible(true);
                resNameLabel.setText(fname+" "+lname);
                resSexLabel1.setText(sex);
                resBdateLabel.setText(bdate);
                resPhoneNumberLabel.setText(phone);
                resEmailLabel.setText(email);
                resAddressLabel.setText("Block "+block+" Lot "+lot+" Phase "+phase+" ");
            }
            else
            JOptionPane.showMessageDialog(null, "Invalid Input", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "Kindly Fill Out Required Details", "Message", JOptionPane.INFORMATION_MESSAGE);
        }

        //reset to false
        lnameChecker = false;
        fnameChecker= false;
        mnameChecker= false;
        sufChecker= false;
        bdateChecker= false;
        emailChecker= false;
        phoneChecker= false;
        blockChecker= false;
        lotChecker= false;
        phaseChecker= false;
    }//GEN-LAST:event_submitButtonActionPerformed

    private void confirm2ButtonconfirmLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirm2ButtonconfirmLabelActionPerformed
        Resident res = new Resident();
        Security sec = new Security();
        
        //check kung may record     
//        int checkName=0;
        int checkEmail=0;
        int checkPhone=0;
        int checkLoc=0;
        int checkPhoneSec=0;
//        if(MNameField2.getText().isEmpty()&&SuffixField2.getText().isEmpty()){
//            checkName=res.checkName(lname, fname);
//        }
//        if(!LNameField2.getText().isEmpty()&&!FNameField2.getText().isEmpty()&&!MNameField2.getText().isEmpty()&&SuffixField2.getText().isEmpty()){
//            checkName=res.checkName(lname, fname,mname);
//        }
//        if(!LNameField2.getText().isEmpty()&&!FNameField2.getText().isEmpty()&&MNameField2.getText().isEmpty()&&!SuffixField2.getText().isEmpty()){
//            checkName=res.checkName(lname, fname,suf);
//        }
//        if(!LNameField2.getText().isEmpty()&&!FNameField2.getText().isEmpty()&&!MNameField2.getText().isEmpty()&&!SuffixField2.getText().isEmpty()){
//            checkName=res.checkName(lname, fname,mname,suf);
//        }
        
        checkEmail = res.checkEmailEdit(email,id2);
        checkPhone = res.checkPhoneEdit(phone,id2);
        checkPhoneSec=sec.checkPhone(phone);
        
        if(/*checkName==0&&*/checkEmail==0&&(checkPhone==0&&checkPhoneSec==0)){ 
                    //check kung may existing block,lot,phase
            checkLoc=res.checkLoc(block, lot, phase);
            System.out.println(checkLoc);
            
            if(checkLoc==1){
                    //update na sa sql                      
                        try {         
                            if("Male".equals(sex))
                                sexC ="M";
                            else
                                sexC="F";
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");

                        System.out.println("Connect to database successful!!");
                            System.out.println(lname);

                            int residentId = Integer.parseInt(id2);
                            String query = "UPDATE resident SET lastname=?,firstname=?,middlename=?,suffix=?,sex=?,birthdate=?,email=?,phonenumber=?,blocknum=?,lotnum=?,phasenum=? WHERE residentid ="+residentId;

                            JOptionPane.showMessageDialog(null, "Your changes have been successfully saved!", "Message", JOptionPane.INFORMATION_MESSAGE);
                            editSuccess=true;

                            PreparedStatement pst= conn.prepareStatement(query);

                            pst.setString(1, lname);
                            pst.setString(2, fname);
                            if(MNameField2.getText().isEmpty()){
                                pst.setString(3, null);
                            }
                            else
                                pst.setString(3, mname);


                            if(SuffixField2.getText().isEmpty()){
                                pst.setString(4, null);
                            }
                            else
                                pst.setString(4, suf);

                            pst.setString(5, sexC);
                            pst.setString(6, bdate);
                            pst.setString(7, email);
                            pst.setString(8, phone);
                            pst.setString(9, block);
                            pst.setString(10, lot); 
                            pst.setString(11, phase);    


                            pst.executeUpdate();
                            confirmationPanel2.setVisible(false);

                        conn.close();

                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                            Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, e);
                        }
                    
            }
            else{//kapag di pa existing
                JOptionPane.showMessageDialog(null, "Location does not exist", "Message", JOptionPane.INFORMATION_MESSAGE);
                blockNum.setForeground(Color.red);
                lotNum.setForeground(Color.red);
                phaseNum.setForeground(Color.red);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "This record already exist", "Message", JOptionPane.INFORMATION_MESSAGE);   
//            if(checkName==1){
//                LNameLabel1.setForeground(Color.red);
//                FNameLabel1.setForeground(Color.red);
//                MNameLabel1.setForeground(Color.red);
//            }
            if(checkEmail==1){
                emailLabel.setForeground(Color.red);
            }
            if(checkPhone==1||checkPhoneSec==1){
               phoneNumberLabel.setForeground(Color.red);
            }
                
        }
        if(editSuccess){
            editResTabPanel.setSelectedIndex(0);
            viewRes();
            editSuccess=false;
        }
            

    }//GEN-LAST:event_confirm2ButtonconfirmLabelActionPerformed

    private void cancelButtonconfirmLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonconfirmLabelActionPerformed
        viewRes();
        resNameLabel.setText("");
        resSexLabel1.setText("");
        resBdateLabel.setText("");
        resPhoneNumberLabel.setText("");
        resEmailLabel.setText("");
        resAddressLabel.setText("");
    }//GEN-LAST:event_cancelButtonconfirmLabelActionPerformed

    private void Searchtextfield1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Searchtextfield1MouseClicked
        Searchtextfield1.setText("");
    }//GEN-LAST:event_Searchtextfield1MouseClicked

    private void Searchtextfield1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Searchtextfield1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Searchtextfield1ActionPerformed

    private void Searchtextfield1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Searchtextfield1KeyReleased
       DefaultTableModel tb2ModelSearch = (DefaultTableModel) editResTable.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(tb2ModelSearch);
        editResTable.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(Searchtextfield1.getText()));
    }//GEN-LAST:event_Searchtextfield1KeyReleased

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed

        Searchtextfield1.setText("");
        DefaultTableModel VLRModelSearch = (DefaultTableModel) editResTable.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(VLRModelSearch);
        editResTable.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(Searchtextfield1.getText()));
        Searchtextfield1.setText("Search...");
    }//GEN-LAST:event_ClearActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
       editResTabPanel.setSelectedIndex(0);
    }//GEN-LAST:event_backButtonActionPerformed

    private void editResRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editResRefreshButtonActionPerformed
        //remove rows muna kase nadodoble pag di niremove
        viewRes();
    }//GEN-LAST:event_editResRefreshButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddressLabel;
    private javax.swing.JLabel BDateLabel;
    private javax.swing.JTextField BdateField;
    private java.awt.Button Clear;
    private javax.swing.JLabel EmailLabel2;
    private javax.swing.JTextField FNameField2;
    private javax.swing.JLabel FNameLabel1;
    private javax.swing.JLabel FullNameLabel;
    private javax.swing.JTextField LNameField2;
    private javax.swing.JLabel LNameLabel1;
    private javax.swing.JTextField MNameField2;
    private javax.swing.JLabel MNameLabel1;
    private java.awt.TextField Searchtextfield1;
    private javax.swing.JComboBox<String> SexCombo2;
    private javax.swing.JLabel SexLabel1;
    private javax.swing.JTextField SuffixField2;
    private javax.swing.JLabel SuffixLabel1;
    private javax.swing.JPanel addPeoplePanel;
    private javax.swing.JLabel addressLabel2;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel bdateLabel2;
    private javax.swing.JLabel blockNum;
    private javax.swing.JTextField blockNumField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirm2Button;
    private javax.swing.JPanel confirmationPanel2;
    private javax.swing.JPanel editRes;
    private javax.swing.JButton editResRefreshButton;
    private javax.swing.JTabbedPane editResTabPanel;
    private javax.swing.JTable editResTable;
    private javax.swing.JPanel editTable;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel fnameLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lotNum;
    private javax.swing.JTextField lotNumField;
    private javax.swing.JLabel phaseNum;
    private javax.swing.JTextField phaseNumField;
    private javax.swing.JTextField phoneNumberField;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JLabel pnumberLabel2;
    private javax.swing.JLabel resAddressLabel;
    private javax.swing.JLabel resBdateLabel;
    private javax.swing.JLabel resEmailLabel;
    private javax.swing.JLabel resNameLabel;
    private javax.swing.JLabel resPhoneNumberLabel;
    private javax.swing.JLabel resSexLabel1;
    private javax.swing.JLabel residentLabel2;
    private javax.swing.JLabel sexLabel2;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
