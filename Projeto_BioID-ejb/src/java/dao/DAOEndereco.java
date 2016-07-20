/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import to.TOBase;
import to.TOEndereco;

/**
 *
 * @author daniel
 */
public class DAOEndereco extends DAOBase{

    @Override
    public void inserir(Connection c, TOBase t) throws Exception {
        //string com o comando sql para editar o banco de dados
        String sql = "INSERT INTO endereco(cidade_idcidade, rua, gps_lat, gps_log, bairro, complemento, cep, numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        //variavel sendo convertida para toUsuarios
        TOEndereco to = (TOEndereco)t;
        //variavel com lista dos parametros
        List<Object> u = new ArrayList<Object>();
        
        
        u.add(to.getCidade_idCidade());
        u.add(to.getRua());
        u.add(to.getGps_Lat());
        u.add(to.getGps_Log());
        u.add(to.getBairro());
        u.add(to.getComplemento());
        u.add(to.getCep());
        u.add(to.getNumero());
        
        //passa por parametros a conexao e a lista de objetos da insercao de um novo produto
        Data.executeUpdate(c, sql, u);
    }
    
}