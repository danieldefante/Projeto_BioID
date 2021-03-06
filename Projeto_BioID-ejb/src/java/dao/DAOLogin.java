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
import to.TOBase;
import to.TOLogin;

/**
 *
 * @author daniel
 */
public class DAOLogin extends DAOBase{

    @Override
    public void inserirIDString(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = null;
        
        
        //variavel sendo convertida para toUsuarios
//        TOLogin to = ((TOLogin)t);
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        
        sql = "INSERT INTO login(pessoa_idpessoa, unidade_idunidade, usuario, senha) VALUES (?, ?, ?, ?)";
        u.add(((TOLogin)t).getPessoa_idpessoa());
        u.add(((TOLogin)t).getUnidade_idunidade());
        u.add(((TOLogin)t).getUsuario());
        u.add(((TOLogin)t).getSenha());
   
        
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        Data.executeUpdateString(c, sql, u);
    }

   
    @Override
    public TOBase get(Connection c, TOBase t, String metodo) throws Exception {
         //string com o comando sql para editar o banco de dados
        String sql = null;
        ResultSet rs = null;
        
        try{
            //variavel com lista dos parametros
            List<Object> u = new ArrayList<Object>();
            
            //testa o metodo a ser executado
            switch(metodo){
                case "VALIDACAO" :
                sql = "SELECT l.usuario, l.unidade_idunidade, p.nome, g.grupo "
                    + "FROM login l "
                    + "INNER JOIN pessoa p ON(p.idpessoa = l.pessoa_idpessoa) "
                    + "INNER JOIN grupos g ON(g.loginusuario = l.usuario) "
                    + "WHERE usuario IN(?) AND senha IN(?)";
                
                u.add(((TOLogin)t).getUsuario());
                u.add(((TOLogin)t).getSenha());
                break;
                case "GET_POR_USUARIO" :
                    sql = "SELECT * FROM login WHERE usuario IN(?)";
                
                    u.add(((TOLogin)t).getUsuario());
                    break;
                default:
                    sql = "SELECT * FROM login";
                    break;
            }
            
            rs = Data.executeQuery(c, sql, u);
            
            
            if(rs.next()){
                return new TOLogin(rs, metodo);
            }else{
                return null;
            }
        }finally{
            rs.close();
        }
        
    }

    @Override
    public void editar(Connection c, TOBase t, String metodo) throws Exception {
        String sql = null;
                
        
//        TOLogin to = (TOLogin)t;
        
        List<Object> u = new ArrayList<Object>();
        
        switch(metodo){
            default:
                sql = "update login set sessao = ? where idlogin = ? ";
                u.add(((TOLogin)t).getUsuario());
                u.add(((TOLogin)t).getSenha());
            break;
        }
        

        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto        
        Data.executeUpdate(c, sql, u);
    }

   
}
