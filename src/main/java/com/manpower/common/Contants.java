package com.manpower.common;

public class Contants {

  public enum AssetType {
    MANPOWER(1),
    ITEM(2);
    private final int value;
    AssetType(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

}
