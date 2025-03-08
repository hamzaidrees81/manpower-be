package com.manpower.common;

import lombok.Getter;

import java.util.Objects;

public class Contants {

  public enum AssetType {
    MANPOWER(1),
    ITEM(2);
    private final int value;
    AssetType(int value) {
      this.value = value;
    }

    public static AssetType fromValue(int value) {
      for (AssetType assetType : AssetType.values()) {
        if (assetType.getValue() == value) {
          return assetType;
        }
      }
      throw new IllegalArgumentException("Invalid role value: " + value);
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

    public enum Claims{
      USER_ID,
      COMPANY_ID
    }
  }

  @Getter
  public enum InvoiceStatus {
    DELETED((byte) 0),
    UNPAID((byte) 1),
    PAID((byte) 2);

    private final Byte value;
    InvoiceStatus(Byte value) {
      this.value = value;
    }

    public static InvoiceStatus fromValue(Byte value) {
      for (InvoiceStatus rate : InvoiceStatus.values()) {
        if (rate.getValue() == value) {
          return rate;
        }
      }
      throw new IllegalArgumentException("Invalid InvoiceStatus value: " + value);
    }
  }

  @Getter
  public enum AssetProjectStatus {
    Active((byte) 1),
    Inactive((byte) 0);

    private final Byte value;
    AssetProjectStatus(Byte value) {
      this.value = value;
    }

    public static AssetProjectStatus fromValue(Byte value) {
      for (AssetProjectStatus assetProjectStatus : AssetProjectStatus.values()) {
        if (Objects.equals(assetProjectStatus.getValue(), value)) {
          return assetProjectStatus;
        }
      }
      throw new IllegalArgumentException("Invalid AssetProjectStatus value: " + value);
    }
  }
}
