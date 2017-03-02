/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TOBase;
import to.TOCidade;
import to.TOOutrosIDNome;

/**
 *
 * @author daniel
 */
public class DAOCidade extends DAOBase{



    @Override
    public JSONArray listar(Connection c, String dataJson) throws Exception {
        JSONArray  ja = new JSONArray();
        String sql = null;
        ResultSet rs = null;
        JSONObject k = new JSONObject(dataJson);
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
 
                     sql = "SELECT idcidade, nomecidade FROM cidade WHERE estado_idestado IN(?) ORDER BY nomecidade ASC";
                     u.add(k.getString("idestado"));
                    
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOCidade ts = new TOCidade(rs);
                ja.put(ts.getJson());
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    
    }
    
}
