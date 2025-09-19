package Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Paulex Sandoval
 *
 *
 */
public class Visitor {

    private int ID, zipCode;
    private String lName, fName, mName, suffix, sex, houseNum, street, barangay, municipality, province, status;

    public boolean isIDmatch(int ID) { //Checks if Input matches with ObjectID
        try {
            return ID == this.ID;
        } catch (NullPointerException e) {
            return false;
        }

    }

    public int getID() { //Getter ID
        return ID;
    }

    public int getZipCode() { //Getter ZipCode
        return zipCode;
    }

    public String getlName() { //Getter LastName
        return lName;
    }

    public String getfName() { //Getter FIrstName
        return fName;
    }

    public String getmName() { //Getter MiddleName
        return mName;
    }

    public String getSuffix() { //Getter Suffix
        return suffix;
    }

    public String getSex() { //Getter Sex
        return sex;
    }

    public String getHouseNum() { //Getter HouseNumber
        return houseNum;
    }

    public String getStreet() { //Getter Street
        return street;
    }

    public String getBarangay() { //Getter Barangay
        return barangay;
    }

    public String getMunicipality() { //Getter Municipality/City
        return municipality;
    }

    public String getProvince() { //Getter Province
        return province;
    }

    public String getStatus() { //Getter VisitorStatus
        return status;
    }

    public int setInfo(int ID) { //sets all Object Information based on the given VisitorID
        //return 0 if no record does not Exist || return 1 if Object Information was changed || return 2 if catch error detected
        String SqlQuery = "select * from visitor where  VisitorID = ?";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");
            PreparedStatement pst = conn.prepareStatement(SqlQuery);
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                System.out.println("No Visitor Record");
                conn.close();
                return 0;
            } else {
                this.ID = rs.getInt("VisitorID");
                this.lName = rs.getString("LastName");
                this.fName = rs.getString("FirstName");
                this.mName = rs.getString("MiddleName");
                this.suffix = rs.getString("Suffix");
                this.sex = rs.getString("Sex");
                this.status = rs.getString("VisitorStatus");
                this.houseNum = rs.getString("HouseNum");
                this.street = rs.getString("Street");
                this.barangay = rs.getString("Barangay");
                this.municipality = rs.getString("MunicipalityCity");
                this.province = rs.getString("Province");
                this.zipCode = rs.getInt("ZipCode");
                System.out.println("RECORD AVAL");
                conn.close();
                return 1;
            }
        } catch (ClassNotFoundException | SQLException e) {
            return 2;
        }
    }

    public int setInfo(String lName, String fName) { //sets all Object Information based on the given Last Name and First Name
        //return 0 if no record does not Exist || return 1 if Object Information was changed || return 2 if catch error detected || return 3 if Name Contains Non Alphabetic Characters
        if (lName.matches("[ A-Za-z]+$") && fName.matches("[ A-Za-z]+$")) {
            String SqlQuery = "select * from visitor where  LastName = '" + lName + "' and FirstName = '" + fName + "' and Suffix is null";
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");
                PreparedStatement pst = conn.prepareStatement(SqlQuery);
                ResultSet rs = pst.executeQuery();
                if (!rs.next()) {
                    System.out.println("No Visitor Record");
                    conn.close();
                    return 0;
                } else {
                    System.out.println("VisitorID: " + String.valueOf(rs.getString("VisitorID")));
                    this.ID = rs.getInt("VisitorID");
                    this.lName = rs.getString("LastName");
                    this.fName = rs.getString("FirstName");
                    this.mName = rs.getString("MiddleName");
                    this.suffix = rs.getString("Suffix");
                    this.sex = rs.getString("Sex");
                    this.status = rs.getString("VisitorStatus");
                    this.houseNum = rs.getString("HouseNum");
                    this.street = rs.getString("Street");
                    this.barangay = rs.getString("Barangay");
                    this.municipality = rs.getString("MunicipalityCity");
                    this.province = rs.getString("Province");
                    this.zipCode = rs.getInt("ZipCode");
                    conn.close();
                    return 1;
                }
            } catch (ClassNotFoundException | SQLException e) {
                return 2;
            }
        } else {
            return 3;
        }
    }

    public int setInfo(String lName, String fName, String mName) { //sets all Object Information based on the given Last Name, First Name, and (MiddleName or Suffix)
        //return 0 if no record does not Exist || return 1 if Object Information was changed || return 2 if catch error detected || return 3 if Name Contains Non Alphabetic Characters
        if (lName.matches("[ A-Za-z]+$") && fName.matches("[ A-Za-z]+$") && mName.matches("[ A-Za-z]+$")) {
            String SqlQuery = "select * from visitor where  LastName = '" + lName + "' and FirstName = '" + fName + "' and (MiddleName = '" + mName + "' or Suffix = '" + mName + "')";
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");
                PreparedStatement pst = conn.prepareStatement(SqlQuery);
                ResultSet rs = pst.executeQuery();
                if (!rs.next()) {
                    System.out.println("No Visitor Record");
                    conn.close();
                    return 0;
                } else {
                    System.out.println("VisitorID: " + String.valueOf(rs.getString("VisitorID")));
                    this.ID = rs.getInt("VisitorID");
                    this.lName = rs.getString("LastName");
                    this.fName = rs.getString("FirstName");
                    this.mName = rs.getString("MiddleName");
                    this.suffix = rs.getString("Suffix");
                    this.sex = rs.getString("Sex");
                    this.status = rs.getString("VisitorStatus");
                    this.houseNum = rs.getString("HouseNum");
                    this.street = rs.getString("Street");
                    this.barangay = rs.getString("Barangay");
                    this.municipality = rs.getString("MunicipalityCity");
                    this.province = rs.getString("Province");
                    this.zipCode = rs.getInt("ZipCode");
                    conn.close();
                    return 1;
                }
            } catch (ClassNotFoundException | SQLException e) {
                return 2;
            }
        } else {
            return 3;
        }
    }

    public int setInfo(String lName, String fName, String mName, String suffix) {  //sets all Object Information based on the given Last Name, First Name, MiddleName, and Suffix
        //return 0 if no record does not Exist || return 1 if Object Information was changed || return 2 if catch error detected || return 3 if Name Contains Non Alphabetic Characters
        if (lName.matches("[ A-Za-z]+$") && fName.matches("[ A-Za-z]+$") && mName.matches("[ A-Za-z]+$") && suffix.matches("[ A-Za-z]+$")) {
            String SqlQuery = "select * from visitor where  LastName = '" + lName + "' and FirstName = '" + fName + "' and MiddleName = '" + mName + "' and Suffix = " + suffix + "'";
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");
                PreparedStatement pst = conn.prepareStatement(SqlQuery);
                ResultSet rs = pst.executeQuery();
                if (!rs.next()) {
                    System.out.println("No Visitor Record");
                    conn.close();
                    return 0;
                } else {
                    System.out.println("VisitorID: " + String.valueOf(rs.getString("VisitorID")));
                    this.ID = rs.getInt("VisitorID");
                    this.lName = rs.getString("LastName");
                    this.fName = rs.getString("FirstName");
                    this.mName = rs.getString("MiddleName");
                    this.suffix = rs.getString("Suffix");
                    this.sex = rs.getString("Sex");
                    this.status = rs.getString("VisitorStatus");
                    this.houseNum = rs.getString("HouseNum");
                    this.street = rs.getString("Street");
                    this.barangay = rs.getString("Barangay");
                    this.municipality = rs.getString("MunicipalityCity");
                    this.province = rs.getString("Province");
                    this.zipCode = rs.getInt("ZipCode");
                    conn.close();
                    return 1;
                }
            } catch (ClassNotFoundException | SQLException e) {
                return 2;
            }
        } else {
            return 3;
        }
    }

    public int checkName(String lName, String fName) {
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected || return 3 if Name Contains Non Alphabetic Characters
        if (lName.matches("[ A-Za-z]+$") && fName.matches("[ A-Za-z]+$")) {
            String SqlQuery = "select * from visitor where  LastName = '" + lName + "' and FirstName = '" + fName + "' and MiddleName is null and Suffix is null";
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");
                PreparedStatement pst = conn.prepareStatement(SqlQuery);
                ResultSet rs = pst.executeQuery();
                if (!rs.next()) {
                    System.out.println("No Visitor Record");
                    conn.close();
                    return 0;
                } else {
                    System.out.println("VisitorID: " + String.valueOf(rs.getString("VisitorID")));
                    conn.close();
                    return 1;
                }
            } catch (ClassNotFoundException | SQLException e) {
                return 2;
            }
        } else {
            return 3;
        }
    }

    public int checkName(String lName, String fName, String mName) {
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected || return 3 if Name Contains Non Alphabetic Characters
        if (lName.matches("[ A-Za-z]+$") && fName.matches("[ A-Za-z]+$") && mName.matches("[ A-Za-z]+$")) {
            String SqlQuery = "select * from visitor where  LastName = '" + lName + "' and FirstName = '" + fName + "' and (MiddleName = '" + mName + "' or Suffix = '" + mName + "')";
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");
                PreparedStatement pst = conn.prepareStatement(SqlQuery);
                ResultSet rs = pst.executeQuery();
                if (!rs.next()) {
                    System.out.println("No Visitor Record");
                    conn.close();
                    return 0;
                } else {
                    System.out.println("VisitorID: " + String.valueOf(rs.getString("VisitorID")));
                    conn.close();
                    return 1;
                }
            } catch (ClassNotFoundException | SQLException e) {
                return 2;
            }
        } else {
            return 3;
        }
    }
    
    public boolean isNameMatching(String lName, String fName, String mName, String suffix){ //Checking Name Matches with Visitor Object
        lName = lName.isBlank()? null : lName;
        fName = fName.isBlank() ? null : fName;
        mName = mName.isBlank() ? null : mName;
        suffix = suffix.isBlank() ? null : suffix;
        return (lName == null ? this.lName == null : lName.equals(this.lName)) && (fName == null ? this.fName == null : fName.equals(this.fName)) && (mName == null ? this.mName == null : mName.equals(this.mName)) && (suffix == null ? this.suffix == null : suffix.equals(this.suffix));
    }
    
    public int checkName(String lName, String fName, String mName, String suffix) {
        //return 0 if no record detected || return 1 if record detected || return 2 if catch error detected || return 3 if Name Contains Non Alphabetic Characters
        if (lName.matches("[ A-Za-z]+$") && fName.matches("[ A-Za-z]+$") && mName.matches("[ A-Za-z]+$") && suffix.matches("[ A-Za-z]+$")) {
            String SqlQuery = "select * from visitor where  LastName = '" + lName + "' and FirstName = '" + fName + "' and MiddleName = '" + mName + "' and Suffix = " + suffix + "'";
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");
                PreparedStatement pst = conn.prepareStatement(SqlQuery);
                ResultSet rs = pst.executeQuery();
                if (!rs.next()) {
                    System.out.println("No Visitor Record");
                    conn.close();
                    return 0;
                } else {
                    System.out.println("VisitorID: " + String.valueOf(rs.getString("VisitorID")));
                    conn.close();
                    return 1;
                }
            } catch (ClassNotFoundException | SQLException e) {
                return 2;
            }
        } else {
            return 3;
        }
    }

    public int getNextID() { //Checks for Next Available IDNumber
        String SqlQuery = "select MAX(VisitorID) from Visitor";
        int out = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\NETBEANSCONNECT:1433;databaseName=VisitorMS;encrypt=true;trustServerCertificate=true", "sa", "BSIT2AG1");
            PreparedStatement pst = conn.prepareStatement(SqlQuery);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                out = rs.getInt(1);
                System.out.println(out);
                out++;
            }
        } catch (ClassNotFoundException | SQLException e) {
            return -1;
        }
        return out;
    }

    public void display() {
        System.out.println(ID + zipCode + lName + fName + mName + suffix + sex + houseNum + street + barangay + municipality + province + status);
    }

}
