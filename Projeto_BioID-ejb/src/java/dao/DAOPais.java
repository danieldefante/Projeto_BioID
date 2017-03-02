
package dao;

import fw.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOPais;

/**
 *
 * @author Daniel
 */
public class DAOPais extends DAOBase{



    @Override
    public JSONArray listar(Connection c) throws Exception {
        JSONArray  ja = new JSONArray();
        String sql = null;
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            sql = "SELECT idpais, nomepais FROM pais ORDER BY nomepais ASC";
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOPais ts = new TOPais(rs);
                ja.put(ts.getJson());
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    
    }
    
}
