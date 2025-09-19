
package Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Resident {
    
    int resID,block, lot, phase;

    public int getResID() {
        return resID;
    }

    public int getBlock() {
        return block;
    }

    public int getLot() {
        return lot;
    }

    public int getPhase() {
        return phase;
    }
    
    public int setInfo(String lName, String fName){
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
        String SqlQuery = "select * from resident where  LastName = '"+lName+"' and FirstName = '"+fName+"' and Suffix is null";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","BSIT2AG1");
            PreparedStatement pst= conn.prepareStatement(SqlQuery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                conn.close();
                return 0;
            }else{
                this.resID = rs.getInt(1);
                this.block = rs.getInt(10);
                this.lot = rs.getInt(11);
                this.phase = rs.getInt(12);
                conn.close();
                return 1;
            }
        }catch(ClassNotFoundException | SQLException e){
            return 2;
        }
    }
    
    public int setInfo(String lName, String fName, String mName){
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
        String SqlQuery = "select * from resident where  LastName = '"+lName+"' and FirstName = '"+fName+"' and (MiddleName = '"+mName+"' or Suffix = '"+mName+"')";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","BSIT2AG1");
            PreparedStatement pst= conn.prepareStatement(SqlQuery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                conn.close();
                return 0;
            }else{
                this.resID = rs.getInt(1);
                this.block = rs.getInt(10);
                this.lot = rs.getInt(11);
                this.phase = rs.getInt(12);
                conn.close();
                return 1;
            }
        }catch(ClassNotFoundException | SQLException e){
            return 2;
        }
    }
    
    public int setInfo(String lName, String fName, String mName, String suffix){
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
        String SqlQuery = "select * from resident where  LastName = '"+lName+"' and FirstName = '"+fName+"' and MiddleName = '"+mName+"' and Suffix = "+suffix+"'";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","BSIT2AG1");
            PreparedStatement pst= conn.prepareStatement(SqlQuery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                conn.close();
                return 0;
            }else{
                this.resID = rs.getInt(1);
                this.block = rs.getInt(10);
                this.lot = rs.getInt(11);
                this.phase = rs.getInt(12);
                conn.close();
                return 1;
            }
        }catch(ClassNotFoundException | SQLException e){
            return 2;
        }
    }
    
    
    public boolean isAlpha(String name) {
        return name.matches("^[ A-Za-z]+$");
    }
    
    public boolean validateJavaDate(String strDate){
	
        SimpleDateFormat dateFormat 
            = new SimpleDateFormat("yyyy-MM-dd"); 
        dateFormat.setLenient(false); 
  
        try { 
            dateFormat.parse(strDate);
            
                return true;
        } 
        catch (ParseException e) { 
            return false;
        } 
   }
    
    public boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }
    
    
    
//    public int checkName(String lName, String fName){
//        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
//        String SqlQuery = "select * from resident where  LastName = '"+lName+"' and FirstName = '"+fName+"' and Suffix is null";
//        try{
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","java");
//            PreparedStatement pst= conn.prepareStatement(SqlQuery);
//            ResultSet rs = pst.executeQuery();
//            if(!rs.next()){
////                System.out.println("No Visitor Record");
//                conn.close();
//                return 0;
//            }else{
////                System.out.println("VisitorID: "+String.valueOf(rs.getString("VisitorID")));
//                conn.close();
//                return 1;
//            }
//        }catch(ClassNotFoundException | SQLException e){
//            return 2;
//        }
//    }
//    
//    public int checkName(String lName, String fName, String mName){
//        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
//        String SqlQuery = "select * from resident where  LastName = '"+lName+"' and FirstName = '"+fName+"' and (MiddleName = '"+mName+"' or Suffix = '"+mName+"')";
//        try{
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","java");
//            PreparedStatement pst= conn.prepareStatement(SqlQuery);
//            ResultSet rs = pst.executeQuery();
//            if(!rs.next()){
////                System.out.println("No Visitor Record");
//                conn.close();
//                return 0;
//            }else{
////                System.out.println("VisitorID: "+String.valueOf(rs.getString("VisitorID")));
//                conn.close();
//                return 1;
//            }
//        }catch(ClassNotFoundException | SQLException e){
//            return 2;
//        }
//    }
//    
//    public int checkName(String lName, String fName, String mName, String suffix){
//        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
//        String SqlQuery = "select * from resident where  LastName = '"+lName+"' and FirstName = '"+fName+"' and MiddleName = '"+mName+"' and Suffix = "+suffix+"'";
//        try{
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","java");
//            PreparedStatement pst= conn.prepareStatement(SqlQuery);
//            ResultSet rs = pst.executeQuery();
//            if(!rs.next()){
////                System.out.println("No Visitor Record");
//                conn.close();
//                return 0;
//            }else{
////                System.out.println("VisitorID: "+String.valueOf(rs.getString("VisitorID")));
//                conn.close();
//                return 1;
//            }
//        }catch(ClassNotFoundException | SQLException e){
//            return 2;
//        }
//    }
    
    public int checkEmail(String email){
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
        String SqlQuery = "select * from resident where  email = '"+email+"'";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","BSIT2AG1");
            PreparedStatement pst= conn.prepareStatement(SqlQuery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
//                System.out.println("No Email Record");
                conn.close();
                return 0;
            }else{

                conn.close();
                return 1;
            }
        }catch(ClassNotFoundException | SQLException e){
            return 2;
        }
    }
    
     public int checkPhone(String phone){
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
        String SqlQuery = "select * from resident where  phonenumber = '"+phone+"'";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","BSIT2AG1");
            PreparedStatement pst= conn.prepareStatement(SqlQuery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
//                System.out.println("No Phone Number Record");
                conn.close();
                return 0;
            }else{

                conn.close();
                return 1;
            }
        }catch(ClassNotFoundException | SQLException e){
            return 2;
        }
    }
     
     public int checkLoc(String block,String lot,String phase){
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected

        
        String SqlQuery = "select * from LOCATION_TB where  BlockNum = '"+block+"' and LotNum= '"+lot+"' and PhaseNum= '"+phase+"'";      
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","BSIT2AG1");
            PreparedStatement pst= conn.prepareStatement(SqlQuery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
//                System.out.println("No Location Record");
                conn.close();
                return 0;
            }else{

                conn.close();
                return 1;
            }
        }catch(ClassNotFoundException | SQLException e){
            return 2;
        }
    }
     
    public int checkEmailEdit(String email,String id){
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
        String SqlQuery = "select * from resident where  email = '"+email+"'AND residentid !="+id;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","BSIT2AG1");
            PreparedStatement pst= conn.prepareStatement(SqlQuery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
//                System.out.println("No Email Record");
                conn.close();
                return 0;
            }else{

                conn.close();
                return 1;
            }
        }catch(ClassNotFoundException | SQLException e){
            return 2;
        }
    }
    
     public int checkPhoneEdit(String phone, String id){
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
        String SqlQuery = "select * from resident where  phonenumber = '"+phone+"' AND residentid !="+id;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa","BSIT2AG1");
            PreparedStatement pst= conn.prepareStatement(SqlQuery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
//                System.out.println("No Phone Number Record");
                conn.close();
                return 0;
            }else{

                conn.close();
                return 1;
            }
        }catch(ClassNotFoundException | SQLException e){
            return 2;
        }
    }
     
     
}
