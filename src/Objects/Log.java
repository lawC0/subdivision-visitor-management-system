package Objects;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

/**
 *
 * @author Paulex Sandoval
 * 
 **/
public class Log {

    private int IdLog, IdVisitor, IdSecurity, Block, Lot, Phase;
    private String DatetimeIn, DatetimeOut;
    
    public Log(int IdVisitor, int IdSecurity) {
        this.IdLog = getNextID();
        this.IdVisitor = IdVisitor;
        this.IdSecurity = IdSecurity;
    }

    public Log(){
    }
    
    public int getIdLog() {  //Getter VisitID
        return IdLog;
    }

    public int getIdVisitor() { //Getter VisitorID
        return IdVisitor;
    }

    public int getIdSecurity() { //Getter SecurityID
        return IdSecurity;
    }

    public int getBlock() { //Getter Block
        return Block;
    }

    public int getLot() { //Getter Lot
        return Lot;
    }

    public int getPhase() { //Getter Phase
        return Phase;
    }

    public String getDatetimeIn() { //Getter DateTimeIn
        return DatetimeIn;
    }

    public String getDatetimeOut() { //Getter  DataTimeOut
        return DatetimeOut;
    }

    public int setInfo(int IdVisitor) {
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
        String SqlQuery = "select * from visitor_log where VisitID = (Select max(VisitID) from visitor_log where VisitorID = ?) ";
        try { //Opens Database
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");
            PreparedStatement pst = conn.prepareStatement(SqlQuery);
            pst.setInt(1, IdVisitor);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) { //Record Detected
                this.IdLog = rs.getInt(1);
                this.DatetimeIn = rs.getString(2);
                this.DatetimeOut = rs.getString(3);
                this.IdVisitor = rs.getInt(4);
                this.Block = rs.getInt(5);
                this.Lot = rs.getInt(6);
                this.Phase = rs.getInt(7);
                this.IdSecurity = rs.getInt(8);
                conn.close();
                return 1;
            } else {
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            return 2;
        }
    }
    
    public void updateDateTime(){ //Gets current Date and Time and Enters for DateTimeIn or DateTimeOut
        LocalDateTime thisDate = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String newDate = thisDate.format(dateFormat);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");
            if(DatetimeIn == null){
                PreparedStatement pst = conn.prepareStatement("update Visitor_Log set VisitDateTimeIn = ? where VisitID = ?");
                pst.setString(1, newDate);
                pst.setInt(2,IdLog);
                pst.executeUpdate();
                this.DatetimeIn = newDate;
            }else if(DatetimeOut == null){
                PreparedStatement pst = conn.prepareStatement("update Visitor_Log set VisitDateTimeOut = ? where VisitID = ?");
                pst.setString(1, newDate);
                pst.setInt(2,IdLog);
                pst.executeUpdate();
                this.DatetimeOut = newDate;
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public final int getNextID(){ //Gets next Possible LogID
        String SqlQuery = "select MAX(VisitID) from Visitor_Log";
        int out = 0;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","BSIT2AG1");
            PreparedStatement pst= conn.prepareStatement(SqlQuery);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                out = rs.getInt(1);
                System.out.println(out);
            out++;
            }
        }catch(ClassNotFoundException | SQLException e){
            return -1;
        }
        return out;
    }
    
    
    public void display(){
        System.out.println(IdLog+IdVisitor+IdSecurity+Block+Lot+Phase+DatetimeIn+DatetimeOut);
    }

}
