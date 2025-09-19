/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.sql.*;


/**
 *
 * @author Paulex Sandoval
 * 
 **/

public class Facility {
    private int block, lot, phase;
    private String name, status, operationHours;

    public int getBlock() {  //Getter Block
        return block;
    }

    public int getLot() { //Getter Lot
        return lot;
    }

    public int getPhase() { //Getter Phase
        return phase;
    }

    public String getName() { //Getter FacilityName
        return name;
    }

    public String getStatus() { //Getter FacilityStatus
        return status;
    }

    public String getOperationHours() { //Getter OperationHours
        return operationHours;
    }
    
    public int checkStatus(int block, int lot, int phase){
        //return 0 if no record detected || return 1 if facility status is Open || return -1 if facility status is Closed || return 2 if catch error detected
        String SqlQuery = "select FacilityStatus from Facility where  F_BlockNum = ? and F_LotNum = ? and F_PhaseNum = ?";      
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","BSIT2AG1");
            PreparedStatement pst= conn.prepareStatement(SqlQuery);
            pst.setInt(1,block);
            pst.setInt(2,lot);
            pst.setInt(3,phase);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
//                System.out.println("No Phone Number Record");
                conn.close();
                return 0;
            }else{
                if("Open".equals(rs.getString(1))){
                    conn.close();
                    return 1;
                }else{
                    return -1;
                }
            }
        }catch(ClassNotFoundException | SQLException e){
            return 2;
        }
    }
    
    public int checkStatus(){
        //return 0 if no record detected || return 1 if facility status is Open || return -1 if facility status is Closed || return 2 if catch error detected
        String SqlQuery = "select FacilityStatus from Facility where  F_BlockNum = ? and F_LotNum= ? and F_PhaseNum= ?";      
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","BSIT2AG1");
            PreparedStatement pst= conn.prepareStatement(SqlQuery);
            pst.setInt(1,this.block);
            pst.setInt(2,this.lot);
            pst.setInt(3,this.phase);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                conn.close();
                return 0;
            }else{
                if("Open".equals(rs.getString(5))){
                    conn.close();
                    return 1;
                }else{
                    return -1;
                }
            }
        }catch(ClassNotFoundException | SQLException e){
            return 2;
        }
    }
    
        public int setInfo(int block, int lot, int phase){ //sets all Object Information based on the given Block Lot and Phase
        String SqlQuery = "select * from Facility where  BlockNum = '"+block+"' and LotNum= '"+lot+"' and PhaseNum= '"+phase+"'";      
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","BSIT2AG1");
            PreparedStatement pst= conn.prepareStatement(SqlQuery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                conn.close();
                return 0;
            }else{
                this.block = rs.getInt(1);
                this.lot = rs.getInt(2);
                this.phase = rs.getInt(3);
                this.name = rs.getString(4);
                this.status = rs.getString(5);
                this.operationHours = rs.getString(6);
                conn.close();
                return 1;
            }
        }catch(ClassNotFoundException | SQLException e){
            return 2;
        }
    }
}
