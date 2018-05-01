package connector ;

import org.neo4j.jdbc.Connection;
import org.neo4j.jdbc.ResultSet;
import org.neo4j.jdbc.BaseDriver;
import org.neo4j.jdbc.PreparedStatement;

public class Connector {

    private final Driver driver;
    private String password;

    {
        password = "neo4j";
    }

    public static void initConn() {

        // Connecting

        try (Connection con = DriverManager.getConnection("jdbc:neo4j:bolt://localhost:7687", 'neo4j', password))
        {
            System.out.println("Attempting to initiate Neo4J connection...");
            // * Query
            String query = "MATCH (n) RETURN (n)";

            try (PreparedStatement stmt = con.prepareStatement(query))
            {

                try (ResultSet rs = stmt.execute())
                {
                    while (rs.next())
                    {
                        System.out.println(rs.getString("f.name")+" is "+rs.getInt("f.age"));
                    }
                }
            }
        }

    } //END initConn()

} //END Connector