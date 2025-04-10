package com.manpower.common;

import lombok.Getter;

import java.util.Objects;

public class Contants {

  @Getter
  public enum AssetType {
    MANPOWER(1),
    ITEM(2);
    private final int value;

    AssetType(int value) {
      this.value = value;
    }

    public static AssetType fromValue(int value) {
      for (AssetType assetType : AssetType.values()) {
        if (assetType.value == value) {
          return assetType;
        }
      }
      throw new IllegalArgumentException("Invalid role value: " + value);
    }
  }

  @Getter
  public enum AssetOwnership {
    SELF(1),
    RENTAL(2);
    private final int value;

    AssetOwnership(int value) {
      this.value = value;
    }

    public static Contants.AssetOwnership fromValue(int value) {
      for (Contants.AssetOwnership assetType : Contants.AssetOwnership.values()) {
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

    public enum Claims {
      USER_ID,
      COMPANY_ID
    }
  }

  public enum StatusInt {
    ACTIVE(1),
    DISABLED(0);

    private final int value;

    StatusInt(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    public static StatusInt fromValue(int value) {
      for (StatusInt status : StatusInt.values()) {
        if (status.getValue() == value) {
          return status;
        }
      }
      throw new IllegalArgumentException("Invalid CompanyStatus value: " + value);
    }
  }

  @Getter
  public enum SponsorshipType
  {
    PERCENTAGE,
    FIXED;
  }

  @Getter
  public enum SponsorshipDeterminant //how will this sponsorship apply on invoice
  {
    REVENUE,
    PROFIT
  }

  @Getter
  public enum SponsorshipBasis //apply on all projects or only this project
  {
    ASSET_BASED,
    PROJECT_BASED
  }

  @Getter
  public enum PaymentStatusString {
    DELETED,
    UNPAID,
    PAID,
    ALL;
  }


  @Getter
  public enum PaymentStatus {
    DELETED((byte) 0),
    UNPAID((byte) 1),
    PAID((byte) 2),
    ALL((byte)-1);

    private final Byte value;
    PaymentStatus(Byte value) {
      this.value = value;
    }

    public static PaymentStatus fromValue(Byte value) {
      for (PaymentStatus rate : PaymentStatus.values()) {
        if (rate.getValue() == value) {
          return rate;
        }
      }
      throw new IllegalArgumentException("Invalid InvoiceStatus value: " + value);
    }
  }

  @Getter
  public enum isDefaultAccount {
    NO(null),
    YES((byte) 1);

    private final Byte value;
    isDefaultAccount(Byte value) {
      this.value = value;
    }

    public static isDefaultAccount fromValue(Byte value) {
      for (isDefaultAccount rate : isDefaultAccount.values()) {
        if (Objects.equals(rate.getValue(), value)) {
          return rate;
        }
      }
      throw new IllegalArgumentException("Invalid account value: " + value);
    }
  }

  @Getter
  public enum AssetProjectStatus {
    ACTIVE((byte) 1),
    INACTIVE((byte) 0);

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

  @Getter
  public enum Status {
    DELETED((byte) 0),
    ACTIVE((byte) 1);

    private final Byte value;
    Status(Byte value) {
      this.value = value;
    }

    public static Status fromValue(Byte value) {
      for (Status rate : Status.values()) {
        if (Objects.equals(rate.getValue(), value)) {
          return rate;
        }
      }
      throw new IllegalArgumentException("Invalid status value: " + value);
    }
  }

  public enum PaidToType {
    ASSET, SPONSOR
  }

}
