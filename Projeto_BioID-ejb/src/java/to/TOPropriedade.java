/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author Aimee
 */
public class TOPropriedade extends TOBase {
    private long idpropriedade;
    private long endereco_idendereco;
    private long unidade_idunidade;
    private String nomepropriedade;
    private double area;
    private String unidadedemedida;
    private double areautilizavel;
    private String unidadedemedidaau;
    private String usuario;

    public long getIdpropriedade() {
        return idpropriedade;
    }

    public void setIdpropriedade(long idpropriedade) {
        this.idpropriedade = idpropriedade;
    }

    public long getEndereco_idendereco() {
        return endereco_idendereco;
    }

    public void setEndereco_idendereco(long endereco_idendereco) {
        this.endereco_idendereco = endereco_idendereco;
    }

    public long getUnidade_idunidade() {
        return unidade_idunidade;
    }

    public void setUnidade_idunidade(long unidade_idunidade) {
        this.unidade_idunidade = unidade_idunidade;
    }

    public String getNomepropriedade() {
        return nomepropriedade;
    }

    public void setNomepropriedade(String nomepropriedade) {
        this.nomepropriedade = nomepropriedade;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getUnidadedemedida() {
        return unidadedemedida;
    }

    public void setUnidadedemedida(String unidadedemedida) {
        this.unidadedemedida = unidadedemedida;
    }

    public double getAreautilizavel() {
        return areautilizavel;
    }

    public void setAreautilizavel(double areautilizavel) {
        this.areautilizavel = areautilizavel;
    }

    public String getUnidadedemedidaau() {
        return unidadedemedidaau;
    }

    public void setUnidadedemedidaau(String unidadedemedidaau) {
        this.unidadedemedidaau = unidadedemedidaau;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    
    public TOPropriedade() {
    }

    public TOPropriedade(long idpropriedade, long endereco_idendereco, long unidade_idunidade, String nomepropriedade, double area, String unidadedemedida, double areautilizavel, String unidadedemedidaau) {
        this.idpropriedade = idpropriedade;
        this.endereco_idendereco = endereco_idendereco;
        this.unidade_idunidade = unidade_idunidade;
        this.nomepropriedade = nomepropriedade;
        this.area = area;
        this.unidadedemedida = unidadedemedida;
        this.areautilizavel = areautilizavel;
        this.unidadedemedidaau = unidadedemedidaau;
    }

    
 
    public TOPropriedade(ResultSet rs) throws Exception{
        this.idpropriedade = rs.getLong("idpropriedade");
        this.endereco_idendereco = rs.getLong("endereco_idendereco");
        this.unidade_idunidade = rs.getLong("unidade_idunidade");
        this.nomepropriedade = rs.getString("nomepropriedade");
        this.area = rs.getDouble("area");
        this.unidadedemedida = rs.getString("unidadedemedida");
        this.areautilizavel = rs.getDouble("areautilizavel");
        this.unidadedemedidaau = rs.getString("unidadedemedidaau");
        
    }
    @Override
    public JSONObject getJson() throws Exception {
         //variavel para retorno do json contendo as informacoes do produto
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idpropriedade", idpropriedade);
        j.put("endereco_idendereco", endereco_idendereco);
        j.put("unidade_idunidade", unidade_idunidade);
        j.put("nomepropriedade", nomepropriedade);
        j.put("area", area);
        j.put("unidadedemedida", unidadedemedida);
        j.put("areautilizavel", areautilizavel);
        j.put("unidadedemedidaau", unidadedemedidaau);
        
        return j;
    }
    
    
}