package com.alura.literature.mapper;

public interface IConverterData {
  <T> T obtenerDatos(String json, Class<T> clase);

}
