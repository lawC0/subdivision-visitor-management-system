
package Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Security extends Resident {
//     public int checkName(String lName, String fName){
//        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
//        String SqlQuery = "select * from security_personnel where  LastName = '"+lName+"' and FirstName = '"+fName+"' and Suffix is null";
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
//        String SqlQuery = "select * from security_personnel where  LastName = '"+lName+"' and FirstName = '"+fName+"' and (MiddleName = '"+mName+"' or Suffix = '"+mName+"')";
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
//        String SqlQuery = "select * from security_personnel where  LastName = '"+lName+"' and FirstName = '"+fName+"' and MiddleName = '"+mName+"' and Suffix = "+suffix+"'";
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
    
  
     @Override
     public int checkPhone(String phone){
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected
        String SqlQuery = "select * from security_personnel where  phonenumber = '"+phone+"'";
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
