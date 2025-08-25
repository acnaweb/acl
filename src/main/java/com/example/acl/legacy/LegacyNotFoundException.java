package com.example.acl.legacy;
public class LegacyNotFoundException extends RuntimeException {
  public LegacyNotFoundException(String id){ super("Pedido não encontrado no legado: " + id); }
}