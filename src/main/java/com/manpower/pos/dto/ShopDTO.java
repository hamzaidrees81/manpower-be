package com.manpower.pos.dto;

import com.manpower.pos.enums.AliveStatus;
import lombok.Data;

import java.time.Instant;

@Data
public class ShopDTO {
    private Integer id;
    private String shopName;
    private String shopAddress;
    private String shopPhone1;
    private String shopPhone2;
    private String comments;
    private AliveStatus status;
    private Instant dateCreated;
    private Integer companyId;
}
