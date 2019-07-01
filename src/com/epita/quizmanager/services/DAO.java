package com.epita.quizmanager.services;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
  protected Connection connection = null;
   
  public DAO(Connection conn){
    this.connection = conn;
  }
   
  public abstract void create(T obj);

  public abstract void update(T obj, T newObj);

  public abstract void delete(String value);

  public abstract List<T> find(String value);
}