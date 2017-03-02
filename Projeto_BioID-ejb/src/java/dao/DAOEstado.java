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
import to.TOEstado;

/**
 *
 * @author daniel
 */
public class DAOEstado extends DAOBase{



    @Override
    public JSONArray listar(Connection c, String dataJson) throws Exception {
        JSONArray  ja = new JSONArray();
        String sql = null;
        ResultSet rs = null;
        JSONObject k = new JSONObject(dataJson);
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            sql = "SELECT idestado, nomeestado FROM estado WHERE pais_idpais IN(?) ORDER BY nomeestado ASC";
            u.add(k.getString("idpais"));
                   
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOEstado ts = new TOEstado(rs);
                ja.put(ts.getJson());
            }
            
                        
        }finally{
            rs.close();
        }
        return ja;
    
    }
    
}
