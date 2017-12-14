/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ConexionBD {
    public String Driver;
    public String db;
    public String url;
    public String user;
    public String pass;
    Properties pp = new Properties();
public ConexionBD()
        
    {
        try {
            pp.load(new BufferedReader(new FileReader("ajustes.properties")));
            String bdd, user, pass;
            bdd=pp.getProperty("bdd");
            user=pp.getProperty("user");
            pass=pp.getProperty("pass");
            this.Driver="org.gjt.mm.mysql.Driver";
            this.db=bdd;
            this.url="jdbc:mysql://localhost:3306/"+bdd;
            this.user= user;
            this.pass= pass;
        } catch (IOException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDriver() {
        return Driver;
    }

    public String getDb() {
        return db;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    
    
}
