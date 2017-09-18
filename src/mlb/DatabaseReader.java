package mlb;
/**
 * @author Roman Yasinovskyy
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseReader {
    private Connection db_connection;
    private final String SQLITEDBPATH = "jdbc:sqlite:data/mlb.sqlite";
    
    public DatabaseReader() { }
    /**
     * Connect to a database (file)
     */
    public void connect() {
        try {
            this.db_connection = DriverManager.getConnection(SQLITEDBPATH);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseReaderGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Disconnect from a database (file)
     */
    public void disconnect() {
        try {
            this.db_connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseReaderGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Populate the list of divisions
     * @param divisions
     */
    public void getDivisions(ArrayList<String> divisions) {
        
        this.connect();
        try {
            // TODO: Write an SQL statement to retrieve a league (conference) and a division
            String sql = "select distinct division, conference FROM team";
            // TODO: Add all 6 combinations to the ArrayList divisions
            Statement stat = db_connection.createStatement();
            ResultSet results = stat.executeQuery(sql);
            
            while (results.next()) {
                divisions.add(results.getString("conference") + " | " + results.getString("division"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
    }
    /**
     * Read all teams from the database
     * @param confDiv
     * @param teams
     */
    public void getTeams(String confDiv, ArrayList<String> teams) {
        Statement stat;
        ResultSet results;
        String conference = confDiv.split(" | ")[0];
        String division = confDiv.split(" | ")[2];
        
        this.connect();
        try {
            stat = this.db_connection.createStatement();
            String sql = "select name from team where team.conference = '" + conference + "' and team.division = '" + division + "';";
            
            results = stat.executeQuery(sql);
            
            while (results.next()) {
                teams.add(results.getString("name"));
            }

            results.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
    }
    /**
     * @param teamName
     * @return Team info
     */
    public Team getTeamInfo(String teamName) {
        Address address;
        Team team = null;
        this.connect();
        try {
            Statement statement = this.db_connection.createStatement();

            String idSql = "select * from team where name = '" + teamName + "';";
            ResultSet idRes = statement.executeQuery(idSql);            

            int teamId = idRes.getInt(1);
            String name = idRes.getString("id");
            String abbr = idRes.getString("abbr");
            String teamString = idRes.getString("name");
            String conf = idRes.getString("conference");
            String division = idRes.getString("division");
            byte[] logo = idRes.getBytes("logo");

            team = new Team(name, abbr, teamString, conf, division);    
            team.setLogo(logo);
            
            String addressSql = "select * from address where team = " + teamId + ";";
            ResultSet addressRes = statement.executeQuery(addressSql);
            String site = addressRes.getString("site");
            String street = addressRes.getString("street");
            String city = addressRes.getString("city");
            String state = addressRes.getString("state");
            String zip = addressRes.getString("zip");
            String url = addressRes.getString("url");
            String phone = addressRes.getString("phone");
            String team_id = Integer.toString(teamId);
            
            address = new Address(team_id, site, street, city, state, zip, phone, url);
            team.setAddress(address);
            
            String rosterSql = "select * from player where team =" + teamId + ";";
            ResultSet rosterRes = statement.executeQuery(rosterSql);
            
            ArrayList<Player> rosterObj = new ArrayList<Player>();
            
            while (rosterRes.next()) {
                Player tmp = new Player(rosterRes.getString("id"),rosterRes.getString("name"), 
                                        rosterRes.getString("team"), rosterRes.getString("position"));
 
                rosterObj.add(tmp);
            }
            team.setRoster(rosterObj);
            

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.disconnect();
        }
        return team;
    }
}
