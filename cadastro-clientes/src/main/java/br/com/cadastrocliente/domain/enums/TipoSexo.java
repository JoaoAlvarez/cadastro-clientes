package br.com.cadastrocliente.domain.enums;

public enum TipoSexo {
	
	HOMEM(1,"Homem"),
	MULHER(2,"Mulher"),
	OUTRO(3,"Outro");
	
	private int cod;
	private String descricao;
	
	private TipoSexo(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoSexo toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoSexo x : TipoSexo.values()) {
			if(cod.equals(x.getCod())){
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Invalido:"+cod);
	}
}
