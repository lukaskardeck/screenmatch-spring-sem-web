package br.com.lukas.screenmatch.service;

public interface IDataConvert {
    <T> T getData(String json, Class<T> tClass);
}
