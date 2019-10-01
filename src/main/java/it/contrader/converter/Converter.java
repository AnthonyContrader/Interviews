package it.contrader.converter;

public interface Converter<Entity,DTO>{
	
	public Entity converterToEntity(DTO dto);
	
	public DTO converterToDTO(Entity entity);
	
}