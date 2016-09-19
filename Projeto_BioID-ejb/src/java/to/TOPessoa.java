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
 * @author daniel
 */
public class TOPessoa extends TOBase{
    private long idpessoa;
    private long endereco_idendereco;
    private long escolaridade_idescolaridade;
    private long estadocivil_idestadocivil;
    private String nome;
    private String sobrenome;
    private String apelido;
    private String cpf;
    private String rg;
    private String datanascimento;
    private String sexo;
    private String telefone1;
    private String telefone2;
    private String email;

    public long getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(long idpessoa) {
        this.idpessoa = idpessoa;
    }
    
    public long getEndereco_idendereco() {
        return endereco_idendereco;
    }

    public void setEndereco_idendereco(long endereco_idendereco) {
        this.endereco_idendereco = endereco_idendereco;
    }



    public long getEscolaridade_idescolaridade() {
        return escolaridade_idescolaridade;
    }

    public void setEscolaridade_idescolaridade(long escolaridade_idescolaridade) {
        this.escolaridade_idescolaridade = escolaridade_idescolaridade;
    }

    public long getEstadocivil_idestadocivil() {
        return estadocivil_idestadocivil;
    }

    public void setEstadocivil_idestadocivil(long estadocivil_idestadocivil) {
        this.estadocivil_idestadocivil = estadocivil_idestadocivil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    
    
    public TOPessoa() {
    }

    public TOPessoa(long idpessoa, long endereco_idendereco, long escolaridade_idescolaridade, long estadocivil_idestadocivil, String nome, String sobrenome, String apelido, String cpf, String rg, String datanascimento, String sexo, String telefone1, String telefone2, String email) {
        this.idpessoa = idpessoa;
        this.endereco_idendereco = endereco_idendereco;
        this.escolaridade_idescolaridade = escolaridade_idescolaridade;
        this.estadocivil_idestadocivil = estadocivil_idestadocivil;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.rg = rg;
        this.datanascimento = datanascimento;
        this.sexo = sexo;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.email = email;
    }

   
    
     //retorna consulta do banco de dados tipo resultset
    public TOPessoa (ResultSet rs) throws Exception{
        this.idpessoa = rs.getLong("idpessoa");
        this.endereco_idendereco = rs.getLong("endereco_idendereco");
        this.escolaridade_idescolaridade = rs.getLong("escolaridade_idescolaridade");
        this.estadocivil_idestadocivil = rs.getLong("estadocivil_idestadocivil");
        this.nome = rs.getString("nome");
        this.sobrenome = rs.getString("sobrenome");
        this.apelido = rs.getString("apelido");
        this.cpf = rs.getString("cpf");
        this.rg = rs.getString("rg");
        this.datanascimento = rs.getString("datanascimento");
        this.sexo = rs.getString("sexo");
        this.telefone1 = rs.getString("telefone1");
        this.telefone2 = rs.getString("telefone2");
        this.email = rs.getString("email");
    }


    
    
    
    //retorna um json
    @Override
    public JSONObject getJson() throws Exception {
        //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
        
        //populando o objeto j
        j.put("idpessoa", idpessoa);
        j.put("endereco_idendereco", endereco_idendereco);
        j.put("estadocivil_idestadocivil", escolaridade_idescolaridade);
        j.put("nome", nome);
        j.put("sobrenome", sobrenome);
        j.put("apelido", apelido);
        j.put("cpf", cpf);
        j.put("rg", rg);
        j.put("datanascimento", datanascimento);
        j.put("sexo", sexo);
        j.put("telefone1", telefone1);
        j.put("telefone2", telefone2);
        j.put("email", email);
        
        return j;
   
    }

    
    
    public TOPessoa listarAgricultoresUnidade(ResultSet rs) throws Exception{
        this.nome = rs.getString("nome");
        this.sobrenome = rs.getString("sobrenome");
        
        return this;  
    }

    @Override
    public JSONObject getJsonSimples() throws Exception {
        //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
        
        j.put("nome", nome);
        j.put("sobrenome", sobrenome);
        
        return j;
    }
    
    
}