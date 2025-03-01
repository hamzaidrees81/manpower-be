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

  public enum Role {
    ADMIN(1),
    EMPLOYEE(2);

    private final int value;
    Role(int value) {
      this.value = value;
    }
    public int getValue() {
      return value;
    }

    public static Role fromValue(int value) {
      for (Role role : Role.values()) {
        if (role.getValue() == value) {
          return role;
        }
      }
      throw new IllegalArgumentException("Invalid role value: " + value);
    }
  }


  public enum RateType {
    REGULAR(1),
    OVERTIME(2);

    private final int value;
    RateType(int value) {
      this.value = value;
    }
    public int getValue() {
      return value;
    }

    public static RateType fromValue(int value) {
      for (RateType rate : RateType.values()) {
        if (rate.getValue() == value) {
          return rate;
        }
      }
      throw new IllegalArgumentException("Invalid RateType value: " + value);
    }
  }

}
