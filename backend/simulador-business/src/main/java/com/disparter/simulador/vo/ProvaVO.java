package com.disparter.simulador.vo;

import java.io.Serializable;

public class ProvaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long              id;
    private String            codigo;
    private String            cnpjEmpresa;
    private String            tituloDocumento;
    private Long              idCategoria;
    private String            crc32;
    private String            nomeArquivo;
    private String            extensaoArquivo;
    private Long              dataDocumentoTime;
    private Long              validadeInformadaTime;
    private String            mimeType;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getTituloDocumento() {
        return tituloDocumento;
    }

    public void setTituloDocumento(String tituloDocumento) {
        this.tituloDocumento = tituloDocumento;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCrc32() {
        return crc32;
    }

    public void setCrc32(String crc32) {
        this.crc32 = crc32;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getExtensaoArquivo() {
        return extensaoArquivo;
    }

    public void setExtensaoArquivo(String extensaoArquivo) {
        this.extensaoArquivo = extensaoArquivo;
    }

    public Long getDataDocumentoTime() {
        return dataDocumentoTime;
    }

    public void setDataDocumentoTime(Long dataDocumentoTime) {
        this.dataDocumentoTime = dataDocumentoTime;
    }

    public Long getValidadeInformadaTime() {
        return validadeInformadaTime;
    }

    public void setValidadeInformadaTime(Long validadeInformadaTime) {
        this.validadeInformadaTime = validadeInformadaTime;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
