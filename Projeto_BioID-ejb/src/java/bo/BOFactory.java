/*
 faz o crud de qualquer classe que herde de tobase e daobase
 */
package bo;

import fw.Data;
import to.TOBase;
import java.sql.Connection;
import org.json.JSONArray;
import dao.DAOBase;

/**
 *
 * @author Daniel
 */
public class BOFactory {
       
    
    public static long inserir(DAOBase d, TOBase t, String metodo) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            return d.inserir(c, t, metodo);
        }finally{
            c.close();
        }
    }
    
    public static void inserirIDString(DAOBase d, TOBase t) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.inserirIDString(c, t);
        }finally{
            c.close();
        }
    }

    
    public static void editar(DAOBase d, TOBase t, String metodo) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.editar(c, t, metodo);
        }finally{
            c.close();
        }
    }

    public static void excluir(DAOBase d, TOBase t, String metodo) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            d.excluir(c, t, metodo);
        }finally{
            c.close();
        }
    }
    
    
    public static TOBase get(DAOBase d, TOBase t, String metodo) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.get(c, t, metodo);
        }finally{
            c.close();
        }
    }
    
    public static JSONArray listar(DAOBase d) throws Exception{;;
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.listar(c);
        }finally{
            c.close();
        }
    }
    
    public static JSONArray listar(DAOBase d, String dataJson) throws Exception{;;
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            
            return d.listar(c, dataJson);
        }finally{
            c.close();
        }
    }
    
    
    public static JSONArray listar(DAOBase d, TOBase t, String metodo) throws Exception{
        Connection c = null;
        
        try{
            c =  Data.openConnection();
            return d.listar(c, t, metodo);
        }finally{
            c.close();
        }
    }   
       



 
    
    
}
