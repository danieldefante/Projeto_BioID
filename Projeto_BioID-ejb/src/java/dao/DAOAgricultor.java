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
import to.TOAgricultor;

/**
 *
 * @author Aimee
 */
public class DAOAgricultor extends DAOBase{

    @Override
    public long inserir(Connection c, TOBase t) throws Exception {
        String sql = "INSERT INTO agricultor(pessoa_idpessoa, qtdedeintegrantes, qtdedecriancas, qtdedegravidas)VALUES (?, ?, ?, ?)";
        
        TOAgricultor to = (TOAgricultor)t;
        
        List<Object> p = new ArrayList<Object>();
        
        
        p.add(to.getPessoa_idpessoa());
        p.add(to.getQtdedeintegrantes());
        p.add(to.getQtdedecriancas());
        p.add(to.getQtdedegravida());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        return Data.executeUpdate(c, sql, p);
    }

}
