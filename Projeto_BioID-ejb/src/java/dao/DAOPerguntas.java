/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import to.TOBase;
import to.TOPerguntas;

/**
 *
 * @author daniel
 */
public class DAOPerguntas extends DAOBase{


    @Override
    public void editar(Connection c, TOBase t) throws Exception {
        super.editar(c, t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO perguntas(propriedade_idpropriedade, pergunta, resposta) VALUES (?, ?, ?)";
        
        TOPerguntas to = (TOPerguntas)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        /*p.add(to.getEndereco_idendereco());
        p.add(to.getUnidade_idunidade());
        p.add(to.getNomepropriedade());
        p.add(to.getArea());
        p.add(to.getUnidadedemedida());
        p.add(to.getAreautilizavel());
        p.add(to.getUnidadedemedidaau());*/
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }

    @Override
    public JSONArray listar(Connection c, TOBase t) throws Exception {
        return super.listar(c, t); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
