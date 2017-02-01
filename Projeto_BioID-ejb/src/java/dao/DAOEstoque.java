/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOEstoque;

/**
 *
 * @author daniel
 */
public class DAOEstoque extends DAOBase{

    
    @Override
    public TOBase get(Connection c, TOBase t, String metodo) throws Exception {

        
        ResultSet rs = null;
        
        try{
            String sql = null;
            //variavel sendo convertida para toUsuarios
            TOEstoque to = (TOEstoque)t;
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            switch(metodo){
                default:
                    sql = "SELECT * FROM estoque WHERE unidade_idunidade IN(?) AND cultivar_idcultivar IN (?)";
                    u.add(to.getUnidade_idunidade());
                    u.add(to.getCultivar_idcultivar()); 
                    break;
            }
            
            rs = Data.executeQuery(c, sql, u);
            
            
            if(rs.next()){
                return new TOEstoque(rs, metodo);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
    }

    @Override
    public long inserir(Connection c, TOBase t, String metodo) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = null;
  
        //variavel sendo convertida para toUsuarios
        TOEstoque to = (TOEstoque)t;
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        switch(metodo){
            default:    
                sql = "INSERT INTO estoque(unidade_idunidade, cultivar_idcultivar, unidademedida_idunidademedida, quantidade) VALUES (?, ?, ?, ?)";
                u.add(to.getUnidade_idunidade());
                u.add(to.getCultivar_idcultivar());
                u.add(to.getUnidademedida_idunidademedida());
                u.add(to.getQuantidade());
                break;
        }
        //passa por parametros a conexao e a lista de objetos da insercao
        return Data.executeUpdate(c, sql, u);
    }

    @Override
    public void editar(Connection c, TOBase t, String metodo) throws Exception {
        String sql = null;
        
        
        
        TOEstoque to = (TOEstoque)t;
        
        List<Object> p = new ArrayList<Object>();
        
        switch(metodo){
            default:
                sql = "UPDATE estoque set quantidade = ? WHERE unidade_idunidade IN(?) AND cultivar_idcultivar IN(?)";
                
                p.add(to.getQuantidade());
                p.add(to.getUnidade_idunidade());
                p.add(to.getCultivar_idcultivar());
                break;
        }
        
        
        Data.executeUpdate(c, sql, p);
    }

    @Override
    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {
        JSONArray  ja = new JSONArray();           
        
        
        ResultSet rs = null;
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            String sql = null;
            
            switch(metodo){
                default:
                    sql = "SELECT e.unidade_idunidade, e.cultivar_idcultivar, u.grandeza, e.quantidade, c.imagem, c.nomecultivar FROM estoque e"
                        + " INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar)"
                        + " INNER JOIN unidademedida u ON (u.idunidademedida = e.unidademedida_idunidademedida)"
                        + " WHERE unidade_idunidade IN(?)";

                    u.add(((TOEstoque) t).getUnidade_idunidade());
                    break;
            
            }
            
            
            rs = Data.executeQuery(c, sql, u);
            
            while (rs.next()){
                TOEstoque tc = new TOEstoque(rs, metodo);
                ja.put(tc.getJson(metodo));
            }
            
        }finally{
            rs.close();
        }
        return ja;
    }
    
//    @Override
//    public JSONArray listar(Connection c, TOBase t, String metodo) throws Exception {
//        JSONArray  ja = new JSONArray();           
//        
//        
//        ResultSet rs = null;
//        try{
//            //variavel com lista dos parametros
//            List<Object> u = new ArrayList<Object>();
//            String sql = null;
//            
//            if(metodo.equals("listar_estoqueunidade_select")){
//                sql = "SELECT e.cultivar_idcultivar, c.nomecultivar FROM estoque e "
//                        + "INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar) "
//                        + "WHERE unidade_idunidade IN(?) AND e.quantidade > 0";
//
//                u.add(((TOEstoque) t).getUnidade_idunidade());
//                
//            }else if(metodo.equals("listar_estoqueunidade")){
//                sql = "SELECT e.cultivar_idcultivar, c.nomecultivar, e.quantidade, u.grandeza FROM estoque e "
//                        + "INNER JOIN cultivar c ON (c.idcultivar = e.cultivar_idcultivar) "
//                        + " INNER JOIN unidademedida u ON (u.idunidademedida = e.unidademedida_idunidademedida)"
//                        + "WHERE e.unidade_idunidade IN(?)";
//
//                u.add(((TOEstoque) t).getUnidade_idunidade());
//            }
//            rs = Data.executeQuery(c, sql, u);
//            
//            while (rs.next()){
//                TOEstoque tc = new TOEstoque(rs, metodo);
//                ja.put(tc.getJson(metodo));
//            }
//            
//        }finally{
//            rs.close();
//        }
//        return ja;
//    }
    
    
    
    
}
